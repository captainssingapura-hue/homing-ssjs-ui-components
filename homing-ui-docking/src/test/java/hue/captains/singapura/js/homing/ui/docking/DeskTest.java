package hue.captains.singapura.js.homing.ui.docking;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The desk over a fake DOM with rectangles (RFC 0066 E3, appendix
 * "tab-panes"): two docks side by side, and one desk over them whose register
 * owns every tab-pane. A drag along a strip stays on its rail; a tab-pane
 * opened into a host is said to have arrived; detached, it floats in a frame
 * of its own, the same chip and the same pane; a float of one dragged over a
 * dock is offered, and let go over a strip lands there, shown, the float
 * gone. Every step is data on one sink, in order. The fake party holds the
 * real one's rule for a name.
 */
class DeskTest extends JsModuleTestBase {

    private static final String P = "/homing/js/hue/captains/singapura/js/homing/ui/";

    // Elements that know their children, classes, attributes, inline
    // properties and a rectangle the test sets; a party branch; a css
    // manager over classList; the typed class names as strings.
    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), attrs = {}, props = {};
            var node = { tag: tag, children: [], parentNode: null, listeners: {}, textContent: "", rect: { left: 0, top: 0, right: 0, bottom: 0, width: 0, height: 0 },
                clientWidth: 0, scrollLeft: 0,
                style: { setProperty: function (k, v) { props[k] = v; }, removeProperty: function (k) { delete props[k]; }, getPropertyValue: function (k) { return props[k] == null ? "" : props[k]; } },
                classList: { add: function () { for (var i = 0; i < arguments.length; i++) classes.add(arguments[i]); },
                             remove: function () { for (var i = 0; i < arguments.length; i++) classes.delete(arguments[i]); },
                             contains: function (c) { return classes.has(c); },
                             toggle: function (c, f) { if (f) classes.add(c); else classes.delete(c); } },
                appendChild: function (c) { if (c.parentNode) c.parentNode.removeChild(c); this.children.push(c); c.parentNode = this; return c; },
                insertBefore: function (c, ref) { if (c.parentNode) c.parentNode.removeChild(c); var i = ref ? this.children.indexOf(ref) : -1; if (i < 0) this.children.push(c); else this.children.splice(i, 0, c); c.parentNode = this; return c; },
                removeChild: function (c) { var i = this.children.indexOf(c); if (i >= 0) this.children.splice(i, 1); c.parentNode = null; return c; },
                setAttribute: function (k, v) { attrs[k] = String(v); }, getAttribute: function (k) { return attrs[k] == null ? null : attrs[k]; },
                removeAttribute: function (k) { delete attrs[k]; },
                addEventListener: function (t, fn) { (this.listeners[t] = this.listeners[t] || []).push(fn); },
                removeEventListener: function (t, fn) { var l = this.listeners[t] || []; var i = l.indexOf(fn); if (i >= 0) l.splice(i, 1); },
                contains: function (c) { return c === this; },
                getBoundingClientRect: function () { return this.rect; },
                setPointerCapture: function () { log.push("capture:" + this.tag); }, releasePointerCapture: function () {},
                fire: function (t, ev) { var e = ev || {}; e.type = t; e.stopPropagation = e.stopPropagation || function () {}; e.preventDefault = e.preventDefault || function () {}; (this.listeners[t] || []).slice().forEach(function (fn) { fn(e); }); },
                has: function (c) { return classes.has(c); },
                prop: function (k) { return props[k]; } };
            return node;
        }
        // the party's rule: a name of letters, digits, _ and -, free on its branch until dissolved
        var VALID = /^[A-Za-z0-9_-]+$/;
        function fakeBranch(name) {
            return { name: name, kids: new Map(), names: new Set(),
                createElement: function (n, tag) { if (!VALID.test(n) || this.names.has(n)) throw new RangeError('createElement: "' + n + '" is not a free, valid name'); this.names.add(n); return el(tag); },
                createBranch: function (n) { if (!VALID.test(n) || this.kids.has(n)) throw new RangeError('createBranch: "' + n + '" is not a free, valid name'); var b = fakeBranch(n); b.parent = this; this.kids.set(n, b); return b; },
                dissolve: function () { if (this.parent) this.parent.kids.delete(name); },
                activate: function (owner) { this.owner = String(owner); } };
        }
        var crypto = { randomUUID: (function () { var n = 0; return function () { return "u" + (++n); }; })() };
        var css = { addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); },
                    removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); },
                    toggleClass: function (e, c, f) { e.classList.toggle(c, f); },
                    hasClass: function (e, c) { return e.classList.contains(c); },
                    extent: function (e, t) { if (t == null) e.style.removeProperty("--extent"); else e.style.setProperty("--extent", String(t)); },
                    size: function (e, s) { if (s == null) e.style.removeProperty("--size"); else e.style.setProperty("--size", String(s)); },
                    aspect: function (e, a) { if (a == null) e.style.removeProperty("--aspect"); else e.style.setProperty("--aspect", String(a)); } };
        var fp_desk = "fp_desk", fp_desk_layer = "fp_desk_layer", fp_frame = "fp_frame", fp_hoverable = "fp_hoverable", fp_held = "fp_held", fp_active = "fp_active",
            fp_head = "fp_head", fp_head_held = "fp_head_held", fp_icon = "fp_icon", fp_icon_on = "fp_icon_on", fp_title = "fp_title", fp_close = "fp_close", fp_body = "fp_body", fp_grip = "fp_grip";
        var mtp_pane = "mtp_pane", mtp_strip = "mtp_strip", mtp_rail = "mtp_rail", mtp_strip_loose = "mtp_strip_loose", mtp_chip = "mtp_chip", mtp_chip_label = "mtp_chip_label", mtp_chip_icon = "mtp_chip_icon", mtp_chip_icon_on = "mtp_chip_icon_on", mtp_chip_mark = "mtp_chip_mark", mtp_chip_mark_on = "mtp_chip_mark_on", mtp_chip_lifted = "mtp_chip_lifted", mtp_chip_dragging = "mtp_chip_dragging", mtp_chip_shifted = "mtp_chip_shifted",
            mtp_chip_seated = "mtp_chip_seated",
            mtp_chip_close = "mtp_chip_close", mtp_drop_mark = "mtp_drop_mark", mtp_strip_tail = "mtp_strip_tail", mtp_add = "mtp_add", mtp_rail_add = "mtp_rail_add", mtp_bar_close = "mtp_bar_close", mtp_add_mark = "mtp_add_mark", mtp_add_off = "mtp_add_off",
            mtp_pill = "mtp_pill", mtp_content = "mtp_content", mtp_tab_content = "mtp_tab_content", mtp_tab_content_hidden = "mtp_tab_content_hidden",
            mtp_empty = "mtp_empty", mtp_dock_target = "mtp_dock_target";
        var console = { error: function (m, e) { log.push("error:" + m); } };
        var page = fakeBranch("page");
        var host = el("div");
        // a widget by the law: a member of the focus branch it was handed, with activate()
        function widget(key, into) { var w = { root: el("w-" + key), activate: function () { log.push(key + ":activate"); }, dispose: function () { log.push(key + ":disposed"); if (w.focus.in) w.focus.leave(); } }; w.focus = into.join(key, w); return w; }
        var sink = function (ev) {
            switch (ev.kind) {
                case "Opened": log.push("opened:" + ev.id); break;
                case "Released": log.push("released:" + ev.id); break;
                case "Closed": log.push("closed:" + ev.id); break;
                case "Raised": log.push("raised:" + ev.id); break;
                case "Moved": log.push("moved:" + ev.id); break;
                case "TabActivated": log.push("active:" + ev.slotId + ":" + ev.tabId); break;
                case "TabAdded": log.push("added:" + ev.slotId + ":" + ev.tab.id); break;
                default: log.push(ev.kind);
            } };
        function dock(slotId, rect) {
            var h = el("div");
            var d = new MultiTabPane(page.createBranch(slotId), { host: h, slotId: slotId, onEvent: sink });
            d.el.rect = rect;
            d.el.children[0].rect = { left: rect.left, top: rect.top, right: rect.right, bottom: rect.top + 30, width: rect.right - rect.left, height: 30 };
            return d;
        }
        var A = dock("a", { left: 0, top: 0, right: 400, bottom: 300 });
        var B = dock("b", { left: 400, top: 0, right: 800, bottom: 300 });
        // THE DESK over both: its register owns every tab-pane, its focus branch is where a widget rests
        var desk = new Desk(page.createBranch("thedesk"), { host: host, onEvent: sink });
        desk.addDock(A); desk.addDock(B);
        function mk(key) { return function (b, t) { b.activate("w"); return widget(key, t.focus); }; }
        function open(id, title) { return desk.register.open({ id: id, title: title || id.toUpperCase(), make: mk(id) }); }
        // the float layer, made and measured: 800 by 600 over the host
        function layered() { var r = desk.layer.root; r.rect = { left: 0, top: 0, right: 800, bottom: 600, width: 800, height: 600 }; r.clientWidth = 800; r.clientHeight = 600; return desk.layer; }
        function press(target, x, y, on) { (on || target).fire("pointerdown", { button: 0, pointerId: 5, clientX: x, clientY: y, target: target }); }
        function chips(d) { return d.el.children[0].children[0].children.filter(function (c) { return c.has("mtp_chip"); }); }   // strip, rail, chips
        function chipOf(d, title) { return chips(d).find(function (c) { return c._label.textContent === title; }); }
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        loadModule("/homing/js/hue/captains/singapura/js/homing/component/party/PartyModule.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/component/keyboard/FocusPartyModule.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/component/keyboard/KeyboardSecretaryModule.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/component/keyboard/KeyboardEventsModule.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/component/keyboard/KeyboardWalkModule.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/component/keyboard/KeyboardShortcutsModule.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/component/keyboard/KeyboardChordsModule.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/component/keyboard/KeyboardStewardModule.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/component/keyboard/KeysModule.js");
        loadModule(P + "floating/FloatEventsModule.js");
        loadModule(P + "floating/FloatingPaneModule.js");
        loadModule(P + "floating/FloatLayerModule.js");
        loadModule(P + "panes/PaneEventsModule.js");
        loadModule(P + "panes/TabDragModule.js");
        loadModule(P + "panes/TabFitModule.js");
        loadModule(P + "panes/TabWindowModule.js");
        loadModule(P + "panes/TabHandModule.js");
        loadModule(P + "panes/TabChipModule.js");
        loadModule(P + "panes/TabStripModule.js");
        loadModule(P + "panes/PaneKeysModule.js");
        loadModule(P + "panes/PaneMenusModule.js");
        loadModule(P + "panes/PaneTabsModule.js");
        loadModule(P + "panes/MultiTabPaneModule.js");
        loadModule(P + "docking/FloaterModule.js");
        loadModule(P + "panes/TabPaneModule.js");
        loadModule(P + "panes/TabRegisterModule.js");
        loadModule(P + "docking/DeskModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String log() { return eval("log.join(' ')").asString(); }

    @Test
    void aDragAlongTheStripReorders_andNeverLeavesTheRail() {
        eval("A.take(open('t1', 'One')); A.take(open('t2', 'Two')); log.length = 0;"
           + "chips(A).forEach(function (c, i) { c.rect = { left: 40 + 80 * i, top: 0, right: 120 + 80 * i, bottom: 30, width: 80, height: 30 }; });");
        // press on Two (its slot 120..200, One's 40..120), 20px in; wander far to the right, far above and below the strip, then back over One's slot
        eval("var c = chipOf(A, 'Two'); c.fire('pointerdown', { button: 0, pointerId: 7, clientX: 140, clientY: 15, target: c });"
           + "c.fire('pointermove', { clientX: 146, clientY: 15 }); c.fire('pointermove', { clientX: 900, clientY: 15 });");
        assertEquals("0px", eval("c.prop('--mtp-drag-x')").asString(), "far right: kept within the row, on its own slot");
        assertTrue(eval("c.has('mtp_chip_dragging') && !c.has('mtp_chip_seated') && A.el.children[0].has('mtp_strip_loose')").asBoolean(), "in the hand: the design's word for a thing dragged, not for a thing seated");
        eval("c.fire('pointermove', { clientX: 110, clientY: -200 }); c.fire('pointermove', { clientX: 110, clientY: 400 });");
        assertEquals("-30px", eval("c.prop('--mtp-drag-x')").asString(), "far above, far below: the rail; part way to One's slot, still nearest its own");
        assertFalse(eval("chipOf(A, 'One').has('mtp_chip_shifted')").asBoolean());
        eval("c.fire('pointermove', { clientX: 50, clientY: 20 });");
        assertEquals("-80px", eval("c.prop('--mtp-drag-x')").asString(), "over One's slot, kept at the row's start");
        assertEquals("80px", eval("chipOf(A, 'One').prop('--mtp-shift-x')").asString(), "One steps aside, one pitch right, live");
        eval("c.fire('pointerup', { clientX: 50, clientY: 20 });");
        assertEquals("active:a:t2 capture:div TabMoved", log(), "the press activates, then a move and nothing else: nothing floated");
        assertEquals("t2,t1", eval("A.tabs().join(',')").asString(), "the drag reordered the dock");
        assertTrue(eval("!c.has('mtp_chip_dragging') && c.has('mtp_chip_seated') && !A.el.children[0].has('mtp_strip_loose') && c.prop('--mtp-drag-x') == null && !chipOf(A, 'One').has('mtp_chip_shifted') && chipOf(A, 'One').prop('--mtp-shift-x') == null").asBoolean(), "let go, nothing of the drag remains; seated again");
        assertTrue(eval("desk._layer === null").asBoolean(), "no float was wanted, so there is no float layer");
    }

    /**
     * THE DESK, the whole (RFC 0066 E3, appendix "tab-panes", §3): a register of
     * its own and a focus branch of its own where widgets rest; tab-panes opened
     * into a host and said to have arrived, shown as asked; the float layer made
     * only when a float is wanted, so a lone pane is a desk with one host;
     * detach, and a float of one dragged onto a dock; an open the host refuses
     * leaves nothing; dispose closes every tab-pane and folds the floats.
     */
    @Nested
    class TheDesk {

        @BeforeEach
        void quiet() { eval("var made = 0; log.length = 0;"); }

        @Test
        void aTabPaneOpenedIntoAHost_isSaidToHaveArrived_andShownAsAsked() {
            eval("var a = desk.open({ title: 'A', make: mk('wa') }, B);");
            assertEquals("tab-1", eval("a.id").asString(), "named by the desk's register");
            assertTrue(eval("B.has('tab-1') && a.host() === B && a.widget.focus.in === B.focus && desk.register.get('tab-1') === a").asBoolean());
            assertTrue(log().contains("added:b:tab-1"), log());
            assertTrue(log().indexOf("added:b:tab-1") < log().indexOf("active:b:tab-1"), "the arrival said first, then the pane shows it: " + log());
            eval("var b = desk.open({ title: 'B', make: mk('wb') }, B, null, 'front'); var c = desk.open({ title: 'C', make: mk('wc') }, B, null, 'focus');");
            assertEquals("tab-3", eval("B.activeTab()").asString(), "in front");
            assertTrue(log().contains("wc:activate"), "and with the keys: " + log());
            assertThrows(PolyglotException.class, () -> eval("desk.open({ make: mk('wd') }, B, null, 'sideways')"));
        }

        @Test
        void aLoneDeskMakesNoFloatLayer_untilAFloatIsWanted() {
            eval("desk.open({ title: 'A', make: mk('wa') }, B);");
            assertTrue(eval("desk._layer === null").asBoolean(), "a desk with docks and no float: no layer");
            eval("desk.float({ x: 10, y: 10 });");
            assertTrue(eval("desk._layer !== null && desk.layer.root.parentNode === host").asBoolean(), "the first float makes it, over the host");
        }

        /**
         * THE KEYBOARD WALK PASSES THE DESK BY: its docks and floats under it in the focus tree,
         * and none of it offered — not the desk, not a pane, not what a pane shows, not what rests
         * in it. A dock removed goes back where it was, and every dock when the desk goes.
         */
        @Test
        void theKeyboardWalkPassesTheDeskBy_withItsDocksUnderIt() {
            eval("desk.open({ title: 'A', make: mk('wa') }, B); var f = desk.float({ x: 10, y: 10 }); desk.open({ title: 'F', make: mk('wf') }, f.host);"
               + "desk.register.open({ title: 'Z', make: mk('wz') }); var other = focusParty.root.join('other', {});"
               + "function offered() { return focusParty.walk().filter(KeyboardWalk.offerable).map(function (m) { return m.name; }).join(','); }");
            assertTrue(eval("A.focus.owner.in === desk.focus && B.focus.owner.in === desk.focus && f.host.focus.owner.in === desk.focus").asBoolean(),
                    "the docks and the float, under the desk");
            assertEquals("other", eval("offered()").asString(), "of everything on the page, only what is not the desk's");
            eval("desk.removeDock(A);");
            assertTrue(eval("A.focus.owner.in === focusParty.root").asBoolean(), "a dock removed goes back where it was");
            assertEquals("other,a", eval("offered()").asString(), "and is in the walk again");
            eval("desk.dispose();");
            assertTrue(eval("B.focus.owner.in === focusParty.root && !!B.focus.owner.in").asBoolean(), "the desk gone, its docks back where they were");
        }

        @Test
        void aWidgetRestsInTheDesksOwnFocusBranch_whileNoHostHoldsIt() {
            eval("var a = desk.open({ title: 'A', make: mk('wa') }, B); B.letGo(a);");
            assertTrue(eval("desk.focus.name === 'thedesk' && a.widget.focus.in === desk.rest && desk.rest.owner.in === desk.focus").asBoolean(),
                    "a branch of the desk's own, named for it, apart from its docks");
            eval("desk.dispose();");
            assertFalse(eval("!!desk.focus.owner.in || !!desk.rest.owner.in").asBoolean(), "left when the desk goes");
        }

        @Test
        void detach_andAFloatOfOneDraggedOntoADock_landsShown() {
            eval("var a = desk.open({ title: 'A', make: mk('wa') }, B); desk.open({ title: 'Z', make: mk('wz') }, A); layered(); log.length = 0; var f = desk.detach(a, { x: 500, y: 400 });");
            assertTrue(eval("f.host.has('tab-1') && !B.has('tab-1')").asBoolean());
            eval("var bar = f.host.bar(); press(bar, 520, 410); bar.fire('pointermove', { clientX: 10, clientY: 10 });");
            assertTrue(eval("A.el.has('mtp_dock_target')").asBoolean(), "A lit");
            eval("bar.fire('pointerup', { type: 'pointerup', clientX: 10, clientY: 10 });");
            assertTrue(eval("A.has('tab-1') && f.closed() && a.host() === A").asBoolean(), "landed in A; the float gone");
            assertEquals("tab-1", eval("A.activeTab()").asString(), "the one A shows: the hand put it there to look at it");
            assertTrue(log().indexOf("TabMoved") < log().lastIndexOf("closed:"), log());
        }

        @Test
        void anOpenTheHostRefuses_leavesNothingBehind() {
            eval("var full = new MultiTabPane(page.createBranch('full'), { host: el('div'), slotId: 'full', budget: 1 }); desk.open({ title: 'X', make: mk('wx') }, full); log.length = 0;");
            var ex = assertThrows(PolyglotException.class, () -> eval("desk.open({ title: 'Y', make: mk('wy') }, full)"));
            assertTrue(ex.getMessage().contains("would not take"), ex.getMessage());
            assertEquals(1, eval("desk.register.count()").asInt(), "the one it opened for the refusal is closed again");
            assertEquals("wy:disposed", log());
        }

        @Test
        void disposeClosesEveryTabPane_andTheFloatsFoldWithThem() {
            eval("desk.open({ title: 'A', make: mk('wa') }, B); var f = desk.float({ x: 10, y: 10 }); desk.open({ title: 'F', make: mk('wf') }, f.host); desk.dispose();");
            assertTrue(eval("desk.register.count() === 0 && !B.has('tab-1') && f.closed()").asBoolean());
        }

        @Test
        void aSourceHandedTheDesk_opensThereAndThePlaceIsTheDesks() {
            loadModule(P + "panes/TabSourceModule.js");
            eval("var src = new TabSource(page.createBranch('src'), { desk: desk, kinds: [ { id: 'note', title: 'Notes', make: function (b, p) { b.activate('w'); return widget('wn' + (++made), p.focus); } } ] });"
               + "log.length = 0; var r = src.add(B, 'note', 'quiet');");
            assertTrue(eval("desk.register.get(r.tab.id) === r.tab && B.has(r.tab.id)").asBoolean());
            assertTrue(log().contains("added:b:" + eval("r.tab.id").asString()), "the desk said it arrived: " + log());
        }
    }

    /**
     * THE FLOATER (RFC 0066 E3, appendix "tab-panes", §7, the sequence's step
     * 3): a frame on the desk around a host of its own, as a browser's window;
     * one bar, the host's strip, whose ground moves the frame and whose cross
     * closes it and every tab-pane in it; offered to a dock while it holds one
     * tab-pane, never while it holds more; not closed by the layer's Escape;
     * gone when its last tab-pane leaves; named for a reader by the tab it shows.
     */
    @Nested
    class TheFloater {

        @BeforeEach
        void aMeasuredLayer() {
            eval("""
                layered();
                function tailOf(f) { var bar = f.host.bar(); return bar.children[bar.children.length - 1]; }
                log.length = 0;
                """);
        }

        @Test
        void aFloatIsAFrameWithOneBar_theHostsStrip() {
            eval("var f = desk.float({ x: 40, y: 30, w: 300, h: 200 }); f.take(open('a'));");
            assertEquals("opened:" + id() + " raised:" + id() + " active:" + id() + ":a", log(), "the layer's frame, then what its host shows, on the one sink");
            assertTrue(eval("f.frame.head === null && f.frame.root.children.length === 2 && f.frame.body.children[0] === f.host.el").asBoolean(), "no head: the frame's body holds the host");
            assertTrue(eval("f.host.el.children[0] === f.host.bar()").asBoolean(), "the host's strip is the one bar");
            assertTrue(eval("tailOf(f).children[tailOf(f).children.length - 1].has('mtp_bar_close')").asBoolean(), "its cross at the bar's end");
            assertEquals("A", eval("f.frame.root.getAttribute('aria-label') + ''").asString(), "named for a reader by the tab it shows");
            eval("f.take(open('b')); f.host.switchTab('b');");
            assertEquals("B", eval("f.frame.root.getAttribute('aria-label') + ''").asString());
        }

        @Test
        void theBarsGroundMovesTheFloat_aPressThroughAChipDoesNot() {
            eval("var f = desk.float({ x: 40, y: 30, w: 300, h: 200 }); var a = open('a'); f.take(a); f.take(open('b')); var bar = f.host.bar(); log.length = 0;");
            eval("press(a.chip, 60, 40, bar); bar.fire('pointermove', { clientX: 200, clientY: 200 }); bar.fire('pointerup', { type: 'pointerup', clientX: 200, clientY: 200 });");
            assertEquals("40,30", eval("var b = f.frame.bounds(); b.x + ',' + b.y").asString(), "a press that came through a chip is the chip's");
            eval("log.length = 0; press(bar, 60, 40); bar.fire('pointermove', { clientX: 160, clientY: 90 }); bar.fire('pointerup', { type: 'pointerup', clientX: 160, clientY: 90 });");
            assertEquals("140,80", eval("var b = f.frame.bounds(); b.x + ',' + b.y").asString(), "a press on the ground, and the drag after it, moves the frame");
            assertEquals("capture:div moved:" + id(), log(), "reported once, by the layer; and offered to no dock");
        }

        @Test
        void aFloatOfManyIsNeverOfferedToADock_itMovesAsAWindowDoes() {
            eval("var f = desk.float({ x: 500, y: 400, w: 300, h: 200 }); f.take(open('a')); f.take(open('b')); var bar = f.host.bar(); log.length = 0;"
               + "press(bar, 520, 410); bar.fire('pointermove', { clientX: 600, clientY: 20 });");
            assertFalse(eval("B.el.has('mtp_dock_target')").asBoolean(), "over B's strip, B is not lit: a float of two is not one tab");
            eval("bar.fire('pointerup', { type: 'pointerup', clientX: 600, clientY: 20 });");
            assertTrue(eval("desk.layer.has(f.id) && f.host.tabs().join(',') === 'a,b' && B.tabs().join(',') === ''").asBoolean(), "still afloat, its tabs in it");
        }

        /** DETACH: a tab-pane off its dock into a float of its own, as it is — the same chip, the same pane — reported as one move; its bar at the point, kept within the layer. */
        @Test
        void detachFloatsATabPaneAsItIs_inAFloatOfItsOwn_reportedAsAMove() {
            eval("var a = open('a'); A.take(a); var chip = a.chip, pane = a.pane; log.length = 0; var f = desk.detach(a, { x: 160, y: 90 });");
            assertTrue(eval("f.host.has('a') && !A.has('a') && a.host() === f.host && a.chip === chip && a.pane === pane").asBoolean(), "the same tab-pane, in a float of its own");
            assertTrue(eval("f.host.bar().children[0].children.indexOf(chip) >= 0").asBoolean(), "its own chip on the float's one bar");
            assertEquals("100,76", eval("var b = f.frame.bounds(); b.x + ',' + b.y").asString(), "its bar at the point, the grip's offset in: 160-60, 90-14");
            assertTrue(log().contains("TabMoved"), log());
            assertTrue(eval("desk.docks().indexOf(f.host) >= 0").asBoolean(), "a float is a dock for as long as it lasts");
            eval("var b = open('b'); A.take(b); var g = desk.detach(b, { x: -50, y: -50 });");
            assertEquals("0,0", eval("var r = g.frame.bounds(); r.x + ',' + r.y").asString(), "kept within the layer");
        }

        @Test
        void aMoveIsRefusedBeforeAnythingLeaves() {
            eval("var a = open('a'); A.take(a);"
               + "var full = new MultiTabPane(page.createBranch('full'), { host: el('div'), slotId: 'full', budget: 1 }); full.take(open('x')); log.length = 0;");
            var ex = assertThrows(PolyglotException.class, () -> eval("desk.move(a, full)"));
            assertTrue(ex.getMessage().contains("would not take"), ex.getMessage());
            assertTrue(eval("A.has('a') && a.host() === A && !full.has('a')").asBoolean(), "nothing left");
            assertEquals("", log(), "and nothing was said");
        }

        /** DRAG TO MOVE: a float of one is that tab in the hand — offered to the docks it passes, and let go over a strip, the tab-pane lands there and the float is gone. */
        @Test
        void aFloatOfOneDraggedOverADocksStrip_landsItsTabThere_andIsGone() {
            eval("var a = open('a'); A.take(a); var f = desk.detach(a, { x: 500, y: 400 }); var bar = f.host.bar(); log.length = 0;"
               + "press(bar, 520, 410); bar.fire('pointermove', { clientX: 600, clientY: 20 });");
            assertTrue(eval("B.el.has('mtp_dock_target')").asBoolean(), "B lit while the tab is offered");
            assertFalse(eval("A.el.has('mtp_dock_target')").asBoolean());
            eval("bar.fire('pointermove', { clientX: 600, clientY: 200 });");
            assertFalse(eval("B.el.has('mtp_dock_target')").asBoolean(), "content is not a landing: docks may tile a box");
            eval("bar.fire('pointermove', { clientX: 600, clientY: 20 }); bar.fire('pointerup', { type: 'pointerup', clientX: 600, clientY: 20 });");
            assertTrue(eval("B.has('a') && a.host() === B && f.closed() && !desk.layer.has(f.id)").asBoolean(), "landed in B, and the float is gone");
            assertTrue(log().contains("TabMoved"), log());
            assertTrue(log().indexOf("TabMoved") < log().indexOf("closed:"), "the move said first, then the float it left empty gone: " + log());
            assertFalse(eval("B.el.has('mtp_dock_target') || desk.docks().indexOf(f.host) >= 0").asBoolean(), "the offer is over, and the float no dock");
            assertTrue(eval("B.contentElOf('a') === a.pane && a.widget.focus.in === B.focus").asBoolean(), "its own pane in B's content, its membership adopted there");
        }

        @Test
        void aFloatOfOneLetGoOffEveryDock_staysAfloat_theOfferWithdrawn() {
            eval("var a = open('a'); A.take(a); var f = desk.detach(a, { x: 500, y: 400 }); var bar = f.host.bar(); log.length = 0;"
               + "press(bar, 520, 410); bar.fire('pointermove', { clientX: 600, clientY: 20 }); bar.fire('pointermove', { clientX: 600, clientY: 500 });"
               + "bar.fire('pointerup', { type: 'pointerup', clientX: 600, clientY: 500 });");
            assertTrue(eval("desk.layer.has(f.id) && f.host.has('a') && !f.closed()").asBoolean(), "still afloat, holding its tab");
            assertFalse(eval("B.el.has('mtp_dock_target')").asBoolean(), "the offer was withdrawn when the pointer left");
            assertFalse(log().contains("TabMoved"), "nothing moved: " + log());
        }

        /** The float in the hand has its own bar under the hand the whole way: it is never offered to itself. */
        @Test
        void aFloatIsNeverOfferedToItself() {
            eval("var a = open('a'); A.take(a); var f = desk.detach(a, { x: 500, y: 400 });"
               + "f.host.el.rect = { left: 440, top: 386, right: 760, bottom: 606, width: 320, height: 220 }; f.host.bar().rect = { left: 440, top: 386, right: 760, bottom: 416, width: 320, height: 30 };"
               + "var bar = f.host.bar(); press(bar, 520, 400); bar.fire('pointermove', { clientX: 530, clientY: 400 });");
            assertFalse(eval("f.host.el.has('mtp_dock_target')").asBoolean(), "its own strip, under the hand, is not a landing");
            eval("bar.fire('pointerup', { type: 'pointerup', clientX: 530, clientY: 400 });");
            assertTrue(eval("f.host.has('a') && !f.closed()").asBoolean(), "let go there: it only moved");
        }

        @Test
        void aFloatOfOneLetGoOverAnotherFloatsStrip_joinsIt() {
            eval("var a = open('a'), b = open('b'); A.take(a); A.take(b); var g = desk.detach(b, { x: 100, y: 300 }), f = desk.detach(a, { x: 500, y: 400 });"
               + "g.host.el.rect = { left: 40, top: 286, right: 360, bottom: 506, width: 320, height: 220 }; g.host.bar().rect = { left: 40, top: 286, right: 360, bottom: 316, width: 320, height: 30 };"
               + "var bar = f.host.bar(); press(bar, 520, 410); bar.fire('pointermove', { clientX: 300, clientY: 300 });");
            assertTrue(eval("g.host.el.has('mtp_dock_target')").asBoolean(), "the other float is offered it");
            assertFalse(eval("f.host.el.has('mtp_dock_target')").asBoolean(), "never its own");
            eval("bar.fire('pointerup', { type: 'pointerup', clientX: 300, clientY: 300 });");
            assertTrue(eval("g.host.tabs().join(',') === 'b,a' && f.closed() && !g.closed()").asBoolean(), "joined it; the empty one gone");
        }

        @Test
        void theCrossClosesTheFloat_andEveryTabPaneInIt() {
            eval("var f = desk.float({ x: 40, y: 30 }); f.take(open('a')); f.take(open('b')); log.length = 0;"
               + "var cross = tailOf(f).children[tailOf(f).children.length - 1]; cross.fire('click', {});");
            assertEquals(0, eval("desk.register.count()").asInt(), "every tab-pane in it closed");
            assertTrue(eval("f.closed() && !desk.layer.has(f.id)").asBoolean(), "and the frame gone");
            assertTrue(log().startsWith("a:disposed TabRemoved"), log());
            assertTrue(log().contains("b:disposed TabRemoved closed:" + id()), log());
        }

        @Test
        void aFloatWhoseLastTabPaneLeavesIsGone_closedOrMovedAway() {
            eval("var f = desk.float({ x: 40, y: 30 }); var a = open('a'); f.take(a); f.host.letGo(a);");
            assertTrue(eval("f.closed() && !desk.layer.has(f.id) && a.host() === null && desk.register.has('a')").asBoolean(), "moved away: the float is gone, the tab-pane not");
            assertTrue(eval("a.widget.focus.in === desk.rest").asBoolean(), "its membership at rest in the desk's branch, not gone with the float's host");
            eval("var g = desk.float({ x: 40, y: 30 }); g.take(a); a.close();");
            assertTrue(eval("g.closed() && !desk.layer.has(g.id) && !desk.register.has('a')").asBoolean(), "closed: the float is gone with it");
        }

        /** The gallery's widgets join under their branch's name: two of them in one float, each on a branch named as its tab-pane is; an id is any string. */
        @Test
        void twoWidgetsOfOneKindShareAFloat() {
            eval("function byBranch(b, t) { b.activate('w'); var w = { root: el('w'), activate: function () {} }; w.focus = t.focus.join(b.name, w); return w; }"
               + "var f = desk.float({ x: 40, y: 30 }); f.take(desk.register.open({ id: 'p:1', make: byBranch })); f.take(desk.register.open({ id: 'p:2', make: byBranch }));");
            assertEquals("p:1,p:2", eval("f.host.tabs().join(',')").asString());
        }

        /** A float holds what came to it: no plus on its bar unless asked for. */
        @Test
        void aFloatHasNoPlus_unlessAskedFor() {
            eval("var f = desk.float({ x: 40, y: 30 }); f.take(open('a')); var g = desk.float({ x: 40, y: 30, addable: true }); g.take(open('b'));"
               + "function plus(fl) { return fl.host.bar().children.filter(function (c) { return c.has('mtp_rail_add'); }).length; }");
            assertEquals(0, eval("plus(f)").asInt(), "a float: no plus");
            assertEquals(1, eval("plus(g)").asInt(), "asked for: one");
        }

        @Test
        void theLayersEscapeDoesNotCloseAFloat_sinceClosingOneClosesItsTabs() {
            eval("var f = desk.float({ x: 40, y: 30 }); f.take(open('a'));");
            assertFalse(eval("desk.layer.key({ key: 'Escape' })").asBoolean());
            assertTrue(eval("desk.layer.has(f.id) && desk.register.has('a')").asBoolean());
        }

        private String id() { return eval("f.id").asString(); }
    }
}
