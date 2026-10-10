package hue.captains.singapura.js.homing.ui.splitgrid;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The mirror over a fake DOM: it draws the layout it is told from the
 * geometry at its scale, and nothing it measured; the cursor goes to the
 * first cell, moves by the arrows to the cell beside by the workspace's rule
 * and stops at an outer edge, goes where the pointer presses, follows a
 * re-arrangement that removed its cell, and says every move as data. It
 * wears the house's look: its own casting, and each cell a pane's thumbnail
 * cast as a choice, named in a kicker, current by aria-current alone.
 */
class SplitGridMirrorTest extends JsModuleTestBase {

    private static final String P = "/homing/js/hue/captains/singapura/js/homing/ui/splitgrid/";

    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), attrs = {}, props = {};
            return { tag: tag, tagName: tag.toUpperCase(), textContent: "", children: [], parentNode: null, listeners: {}, tabIndex: -1,
                classList: { add: function () { for (var i = 0; i < arguments.length; i++) classes.add(arguments[i]); },
                             remove: function () { for (var i = 0; i < arguments.length; i++) classes.delete(arguments[i]); },
                             contains: function (c) { return classes.has(c); }, toggle: function (c, f) { if (f) classes.add(c); else classes.delete(c); } },
                style: { setProperty: function (k, v) { props[k] = v; }, getPropertyValue: function (k) { return props[k] || ""; } },
                get firstChild() { return this.children[0] || null; },
                appendChild: function (c) { if (c.parentNode) c.parentNode.removeChild(c); this.children.push(c); c.parentNode = this; return c; },
                removeChild: function (c) { var i = this.children.indexOf(c); if (i >= 0) this.children.splice(i, 1); c.parentNode = null; return c; },
                setAttribute: function (k, v) { attrs[k] = String(v); }, getAttribute: function (k) { return attrs[k] == null ? null : attrs[k]; },
                removeAttribute: function (k) { delete attrs[k]; }, hasAttribute: function (k) { return k in attrs; },
                addEventListener: function (t, fn) { (this.listeners[t] = this.listeners[t] || []).push(fn); },
                focus: function () { log.push("focus"); },
                fire: function (t, ev) { var e = ev || {}; e.preventDefault = function () {}; e.stopPropagation = function () {}; (this.listeners[t] || []).slice().forEach(function (fn) { fn(e); }); },
                has: function (c) { return classes.has(c); }, prop: function (k) { return props[k]; } };
        }
        // as the party: a name, of an element or a branch, is held until the branch dissolves; a maker is single-use, and known for one
        var makers = new Set();
        var DomOpsParty = { isElementMaker: function (f) { return makers.has(f); } };
        function fakeBranch(name) {
            var elements = new Set(), branches = new Set();
            return { name: name,
                     createElement: function (n, tag) { if (elements.has(n)) throw new RangeError('name "' + n + '" is already in use on branch "' + name + '"'); elements.add(n); return el(tag); },
                     elementMaker: function (n) { var self = this, mk = function (tag) { makers.delete(mk); return self.createElement(n, tag); }; makers.add(mk); return mk; },
                     createBranch: function (n) { if (branches.has(n)) throw new RangeError('a branch named "' + n + '" already exists on "' + name + '"'); branches.add(n); return fakeBranch(n); },
                     dissolve: function () {}, activate: function () {} };
        }
        var css = { addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); },
                    removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); },
                    toggleClass: function (e, c, f) { e.classList.toggle(c, f); } };
        var sgm_root = "sgm_root", sgm_cell = "sgm_cell", sgm_name = "sgm_name";
        var console = { error: function (m) { log.push("error:" + m); } };
        var host = el("div"), branch = fakeBranch("page");
        var LAYOUT = { kind: "split", orientation: "horizontal", children: [
            { node: { kind: "cell", id: "nav" }, ratio: 1 },
            { node: { kind: "split", orientation: "vertical", children: [
                { node: { kind: "cell", id: "demo" }, ratio: 3 },
                { node: { kind: "cell", id: "explain" }, ratio: 1 } ] }, ratio: 3 } ] };
        var mirror = new SplitGridMirror(branch.createBranch("mirror"), { host: host, scale: 0.5, onEvent: function (ev) { log.push(ev.kind === "CursorMoved" ? "cursor:" + ev.cellId + "/" + ev.by : ev.kind); } });
        mirror.reflect(LAYOUT, { w: 1007, h: 607 });
        function boxes() { return mirror.el.children.map(function (b) { return b.getAttribute("data-cell") + (b.getAttribute("aria-current") === "true" ? "*" : "") + "@" + [b.prop("--sgm-x"), b.prop("--sgm-y"), b.prop("--sgm-w"), b.prop("--sgm-h")].join(","); }).join(" "); }
        function key(k) { return mirror.key({ key: k }); }   // from whoever holds the keys for the mirror: no keydown listener of its own
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        loadModule("/homing/js/hue/captains/singapura/js/homing/component/keyboard/KeysModule.js");
        loadModule(P + "SplitGridEventsModule.js");
        loadModule(P + "SplitGridTreeModule.js");
        loadModule(P + "SplitGridGeometryModule.js");
        TextsInJs.load(js, this::loadModule);   // the look, and the kicker each cell's name is
        loadModule(P + "SplitGridMirrorModule.js");
        js.eval("js", TextsInJs.withCss(SHIM));
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String log() { return eval("log.join(' ')").asString(); }

    @Test
    void itDrawsTheLayoutFromTheGeometry_atItsScale_withTheCursorOnTheFirstCell() {
        assertEquals("504px x 304px", eval("mirror.el.prop('--sgm-w') + ' x ' + mirror.el.prop('--sgm-h')").asString());
        assertEquals("nav*@0px,0px,126px,304px demo@126px,0px,377px,227px explain@126px,228px,377px,76px", eval("boxes()").asString());
        assertEquals("cursor:nav/call", log());
        assertEquals("0", eval("String(mirror.el.tabIndex)").asString(), "focusable");
        eval("mirror.scale(0.25);");
        assertEquals("252px x 152px", eval("mirror.el.prop('--sgm-w') + ' x ' + mirror.el.prop('--sgm-h')").asString());
        assertEquals("nav*@0px,0px,63px,152px demo@63px,0px,189px,114px explain@63px,114px,189px,38px", eval("boxes()").asString(), "redrawn at the new scale, the cursor kept");
    }

    @Test
    void theArrowsMoveTheCursorByTheWorkspacesRule_andStopAtAnEdge() {
        assertEquals("0", eval("String((mirror.el.listeners.keydown || []).length)").asString(), "the keys come through the party");
        assertEquals("true,true,true,true,true", eval("log.length = 0; [key('ArrowRight'), key('ArrowDown'), key('ArrowDown'), key('ArrowLeft'), key('ArrowUp')].join()").asString(), "an arrow is taken, at an edge too");
        assertFalse(eval("key('Enter')").asBoolean(), "another key is left");
        assertEquals("cursor:demo/right cursor:explain/down cursor:nav/left", log(), "down at the bottom and up at the top move nothing and say nothing");
        assertEquals("nav", eval("mirror.cursor()").asString());
        assertTrue(eval("mirror.el.children[0].getAttribute('aria-current') === 'true' && !mirror.el.children[1].hasAttribute('aria-current') && !mirror.el.children[2].hasAttribute('aria-current')").asBoolean(),
                "the current cell says so, and no other says anything: its look is current by the attribute's presence");
    }

    @Test
    void aPressPutsTheCursorThere_andAReflectThatLostTheCellMovesItToTheFirst() {
        eval("log.length = 0; mirror.el.children[2].fire('pointerdown', { button: 0 });");
        assertEquals("focus cursor:explain/pointer", log());
        eval("log.length = 0; mirror.reflect({ kind: 'split', orientation: 'horizontal', children: [ { node: { kind: 'cell', id: 'nav' } }, { node: { kind: 'cell', id: 'demo' } } ] }, { w: 1007, h: 607 });");
        assertEquals("cursor:nav/call", log());
        assertEquals("nav*@0px,0px,252px,304px demo@252px,0px,252px,304px", eval("boxes()").asString());
        eval("log.length = 0; mirror.cursor('demo'); mirror.cursor('demo');");
        assertEquals("cursor:demo/call", log(), "a call moves it once");
    }

    /** Its look is the house's: the mirror in its own casting, each cell a pane's thumbnail cast as a choice, named by its owner or by its id; its own classes only place. */
    @Test
    void itWearsTheHousesLook_eachCellAPanesThumbnail_namedInAKicker() {
        assertTrue(eval("mirror.el.has('sgm_root') && mirror.el.has('split-grid-mirror__color-background') && mirror.el.has('split-grid-mirror__shape-outline--focus')").asBoolean(),
                "placed by its own class, worn as itself, and ringed as a control is when focused");
        var cell = "mirror.el.children[1]";
        assertTrue(eval(cell + ".has('sgm_cell') && " + cell + ".has('pane-thumb_choice__color-background') && " + cell + ".has('pane-thumb__color-background--current')").asBoolean(),
                "a cell is placed by its own class and worn as a pane's thumbnail cast as a choice, with its current treatment");
        assertEquals("nav demo explain", eval("mirror.el.children.map(function (b) { return b.children[0].textContent; }).join(' ')").asString(), "named by its id, unless said");
        assertTrue(eval(cell + ".children[0].has('kicker_name__type-face') && " + cell + ".children[0].has('sgm_name')").asBoolean(), "its name a kicker cast as the name, placed on one line");
        eval("var named = new SplitGridMirror(branch.createBranch('named'), { host: host, labelOf: function (id) { return id.toUpperCase(); } }); named.reflect(LAYOUT, { w: 1007, h: 607 });");
        assertEquals("NAV DEMO EXPLAIN", eval("named.el.children.map(function (b) { return b.children[0].textContent; }).join(' ')").asString(), "named as its owner says");
    }

    @Test
    void twoIdsOfOneSafeSpellingAreTwoBoxes() {
        eval("mirror.reflect({ kind: 'split', orientation: 'horizontal', children: [ { node: { kind: 'cell', id: 'a:b' } }, { node: { kind: 'cell', id: 'a_b' } } ] }, { w: 1007, h: 607 });");
        assertEquals("a:b*@0px,0px,252px,304px a_b@252px,0px,252px,304px", eval("boxes()").asString());
    }
}
