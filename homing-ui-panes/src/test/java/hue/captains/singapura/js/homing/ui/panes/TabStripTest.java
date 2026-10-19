package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookManifestModule;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The strip alone over a fake DOM that knows its rectangles: the drag is a
 * rail — the chip follows the hand along the row at the press's offset,
 * within the slots, never off the rail however the hand wanders; the
 * chips it passes step aside and step back; let go, it lands on the slot
 * it is nearest and the strip says so; every step is taken back at once.
 */
@org.junit.jupiter.api.Disabled("RFC 0071 port: these load the old simple component as a script - props, Look, companions; the base is ported, so they wait to be ported or retired with their modules, the components lab holding what they checked (the port journal)")
class TabStripTest extends UiPanesJsTest {

    private static final String P = "/homing/js/hue/captains/singapura/js/homing/ui/panes/";
    private static final String ELEMENTS = "/homing/js/hue/captains/singapura/js/homing/ui/elements/";

    // three chips of 80 at a pitch of 80 on a strip 30 tall; the second is pinned in one test
    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), attrs = {}, props = {};
            var node = { tag: tag, tagName: String(tag).toUpperCase(), children: [], parentNode: null, listeners: {}, textContent: "", rect: { left: 0, top: 0, width: 0, height: 0 }, cancelled: 0,
                // the bar measures itself to squeeze the row: a style bag and a scroll box, unread by anything else here
                style: { props: {}, setProperty: function (k, v) { this.props[k] = v; }, removeProperty: function (k) { delete this.props[k]; } },
                clientWidth: 0, scrollLeft: 0,
                style: { setProperty: function (k, v) { props[k] = v; }, removeProperty: function (k) { delete props[k]; }, getPropertyValue: function (k) { return props[k] == null ? "" : props[k]; } },
                classList: { add: function () { for (var i = 0; i < arguments.length; i++) classes.add(arguments[i]); },
                             remove: function () { for (var i = 0; i < arguments.length; i++) classes.delete(arguments[i]); },
                             contains: function (c) { return classes.has(c); }, toggle: function (c, f) { if (f) classes.add(c); else classes.delete(c); } },
                appendChild: function (c) { if (c.parentNode) c.parentNode.removeChild(c); this.children.push(c); c.parentNode = this; return c; },
                insertBefore: function (c, ref) { if (c.parentNode) c.parentNode.removeChild(c); var i = ref ? this.children.indexOf(ref) : -1; if (i < 0) this.children.push(c); else this.children.splice(i, 0, c); c.parentNode = this; return c; },
                removeChild: function (c) { var i = this.children.indexOf(c); if (i >= 0) this.children.splice(i, 1); c.parentNode = null; return c; },
                setAttribute: function (k, v) { attrs[k] = String(v); }, getAttribute: function (k) { return attrs[k] == null ? null : attrs[k]; }, removeAttribute: function (k) { delete attrs[k]; },
                addEventListener: function (t, fn) { (this.listeners[t] = this.listeners[t] || []).push(fn); },
                removeEventListener: function (t, fn) { var l = this.listeners[t] || []; var i = l.indexOf(fn); if (i >= 0) l.splice(i, 1); },
                contains: function (c) { return c === this; },
                getBoundingClientRect: function () { return this.rect; },
                getAnimations: function () { var self = this; return [{ cancel: function () { self.cancelled++; } }]; },
                animate: function (frames, opts) { log.push("settle:" + this.title + ":" + frames[0].translate + "->" + frames[1].translate + "/" + opts.duration); },
                setPointerCapture: function () {}, releasePointerCapture: function () {},
                fire: function (t, ev) { var e = ev || {}; e.type = t; e.stopPropagation = e.stopPropagation || function () {}; e.preventDefault = e.preventDefault || function () {}; (this.listeners[t] || []).slice().forEach(function (fn) { fn(e); }); },
                has: function (c) { return classes.has(c); }, prop: function (k) { return props[k]; } };
            return node;
        }
        function fakeBranch(name) { return { name: name, createElement: function (n, tag) { return el(tag); }, createBranch: function (n) { return fakeBranch(n); }, dissolve: function () {}, activate: function () {} }; }
        // a maker is single-use, and known for one: the strip's plus, its count and the crosses are made by their own makers
        var makers = new Set();
        var DomOpsParty = { isElementMaker: function (f) { return makers.has(f); } };
        var _branchOf = fakeBranch;
        fakeBranch = function () {
            var b = _branchOf.apply(null, arguments);
            b.elementMaker = function (n) { var mk = function (tag) { makers.delete(mk); return b.createElement(n, tag); }; makers.add(mk); return mk; };
            return b;
        };
        var css = { addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); },
                    removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); },
                    toggleClass: function (e, c, f) { e.classList.toggle(c, f); },
                    size: function (e, s) { if (s == null) e.style.removeProperty("--size"); else e.style.setProperty("--size", String(s)); },
                    aspect: function (e, a) { if (a == null) e.style.removeProperty("--aspect"); else e.style.setProperty("--aspect", String(a)); },
                    extent: function (e, t) { if (t == null) e.style.removeProperty("--extent"); else e.style.setProperty("--extent", String(t)); } };
        css.cls = function (name) { return name; };   // the new manager names its classes through the old
        var getComputedStyle = function () { return { transitionDuration: "0.16s, 0.16s" }; };
        var mtp_strip = "mtp_strip", mtp_rail = "mtp_rail", mtp_strip_loose = "mtp_strip_loose", mtp_chip = "mtp_chip", mtp_chip_label = "mtp_chip_label", mtp_chip_icon = "mtp_chip_icon", mtp_chip_icon_on = "mtp_chip_icon_on", mtp_chip_mark = "mtp_chip_mark", mtp_chip_mark_on = "mtp_chip_mark_on", mtp_chip_lifted = "mtp_chip_lifted",
            mtp_chip_dragging = "mtp_chip_dragging", mtp_chip_shifted = "mtp_chip_shifted",
            mtp_chip_close = "mtp_chip_close", mtp_drop_mark = "mtp_drop_mark", mtp_strip_tail = "mtp_strip_tail", mtp_add = "mtp_add", mtp_rail_add = "mtp_rail_add", mtp_add_mark = "mtp_add_mark", mtp_pill = "mtp_pill";
        var strip = new TabStrip(fakeBranch("strip"), { onDrop: function (c, dest) { log.push("drop:" + c._label.textContent + "@" + dest); } });
        strip.el.rect = { left: 0, top: 0, width: 400, height: 30 };
        function make(names, pinned) {
            return names.map(function (n, i) {
                var c = strip.chip({ id: n.toLowerCase(), title: n, pinned: pinned && i === 0 }, { onSelect: function () { log.push("select:" + n); }, onClose: function () {} });
                c.el.rect = { left: 40 + 80 * i, top: 0, width: 80, height: 30 };   // a tab's element, where it lies
                return c;
            });
        }
        var chips = make(["A", "B", "C"], false);
        strip.arrange(chips);
        var A = chips[0].el, B = chips[1].el, C = chips[2].el;   // the hand works on the tabs' elements
        function x(c) { return c.prop("--mtp-drag-x") == null ? "-" : c.prop("--mtp-drag-x"); }
        function shifts() { return chips.map(function (t) { var c = t.el; return c.has("mtp_chip_shifted") ? c.prop("--mtp-shift-x") : "."; }).join(" "); }
        // the look's reflection of a thing dragged, and the classes that place it
        function state(c) { return (c.getAttribute("data-dragging") === null ? [] : ["dragging"]).concat(["mtp_chip_dragging", "mtp_chip_shifted"].filter(function (k) { return c.has(k); })).join("+") || "seated"; }
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        // the chips' close button: its castings and its looks, generated, before its declaration is read
        js.eval("js", String.join("\n", CastingsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", LookManifestModule.INSTANCE.selfContent(null)));
        js.eval("js", "var style_sheet = 'style-sheet', palette_sheet = 'palette-sheet'; if (typeof CssClassManagerInstance === 'undefined') globalThis.CssClassManagerInstance = { cls: function (n) { return n; }, addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); }, removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); }, hasClass: function (e, c) { return e.classList.contains(c); } };"); loadModule("/homing/js/hue/captains/singapura/js/homing/server/StyledLeaves.js");
        js.eval("js", String.join("\n", hue.captains.singapura.js.homing.ui.look.IconsModule.INSTANCE.selfContent(null)));
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/look/LookModule.js");   // the look, the design's: beside the elements, not one of them
        for (String m : new String[] {"SimpleComponentModule.js", "ButtonBehaviorModule.js", "ButtonModule.js", "CloseButtonModule.js"})
            loadModule(ELEMENTS + m);
        TextsInJs.load(js, this::loadModule);   // and the pill that is its count
        loadModule("/homing/js/hue/captains/singapura/js/homing/component/keyboard/KeysModule.js");
        loadModule(P + "TabDragModule.js");
        loadModule(P + "TabFitModule.js");
        loadModule(P + "TabWindowModule.js");
        loadModule(P + "TabHandModule.js");
        js.eval("js", "if (typeof ic_base === 'undefined') globalThis.ic_base = 'ic_base';");   // the house's icon, the chip's within mark
        if (js.eval("js", "typeof Icon === 'undefined'").asBoolean()) loadModule("/homing/js/hue/captains/singapura/js/homing/ui/icons/IconModule.js");
        loadModule(P + "TabBehaviorModule.js");
        loadModule(P + "TabModule.js");
        // where a dragged tab would land: the house's drop mark
        if (js.eval("js", "typeof MarkBehavior === 'undefined'").asBoolean()) loadModule("/homing/js/hue/captains/singapura/js/homing/ui/elements/MarkBehaviorModule.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/elements/DropMarkModule.js");
        loadModule(P + "TabStripModule.js");
        js.eval("js", TextsInJs.withCss(SHIM));
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String log() { return eval("log.join(' ')").asString(); }

    /**
     * The tab, a component of its own (the owner, 14 Oct 2026): its casting's class and its role; the hand down on it
     * its Press, which its holder selects it by; the keys said on it as its Keyboard state - lifted by its look while
     * held, its mark part of the way, at full while lent, gone while away; its cross told from the rest of it.
     */
    @Test
    void aTab_aComponentOfItsOwn_itsStatesItsClasses_itsPress() {
        assertTrue(eval("A.has('styled-tab') && A.getAttribute('role') === 'tab' && chips[0].el === A").asBoolean(), "the strip holds the tab; its element wears the tab's casting");
        eval("log.length = 0; A.fire('pointerdown', { button: 0, pointerId: 7, clientX: 60, clientY: 10, target: A }); A.fire('pointerup', { clientX: 60, clientY: 10 });");
        assertEquals("select:A", log(), "its Press: its holder selects it");
        eval("strip.select(chips, chips[1])");
        assertTrue(eval("B.has('styled-tab--selected') && B.getAttribute('aria-selected') === 'true' && !A.has('styled-tab--selected')").asBoolean(), "in use, one at a time");
        eval("strip.keys(chips[1], 'held')");
        assertEquals("held 0.45 true", eval("chips[1].keys + ' ' + chips[1]._mark.prop('--extent') + ' ' + B.has('styled-tab--held')").asString(),
                "the cursor on its strip: lifted by its look, its mark part of the way");
        eval("strip.keys(chips[1], 'lent')");
        assertEquals("lent 1 false", eval("chips[1].keys + ' ' + chips[1]._mark.prop('--extent') + ' ' + B.has('styled-tab--held')").asString(),
                "the keys inside what it holds: down, its mark at full");
        eval("strip.keys(null, null)");
        assertTrue(eval("chips[1].keys === 'away' && !chips[1]._mark.has('mtp_chip_mark_on') && B.getAttribute('data-keys') === null").asBoolean(), "away: no mark");
        assertTrue(eval("chips[0].crosses(chips[0]._close) && !chips[0].crosses(A)").asBoolean(), "its cross told from the rest of it");
        // the castings the tab makes of its parts, back in the tab's jar (D51), where they are
        assertTrue(eval("chips[0]._mark.has('styled-within-icon')").asBoolean(), "its keys mark: the icon the tab casts as its keys - a shaping role, so the icon's own class");
        assertTrue(eval("chips[0]._closer.el.has('styled-close-button_close')").asBoolean(), "its cross: the close button as Close, the casting's class");
    }

    @Test
    void theChipFollowsTheHandAlongTheRail_neverOffIt_theOthersSteppingAsideLive() {
        assertEquals("seated", eval("state(A)").asString());
        // press A 20 in; 6 along starts the drag; the hand wanders 200 down and 50 up: only x matters
        eval("A.fire('pointerdown', { button: 0, pointerId: 1, clientX: 60, clientY: 10, target: A }); A.fire('pointermove', { clientX: 66, clientY: 10 });");
        assertEquals("dragging+mtp_chip_dragging", eval("state(A)").asString(), "in the hand: the look's state for a thing dragged, and over the row");
        assertTrue(eval("strip.el.has('mtp_strip_loose')").asBoolean());
        eval("A.fire('pointermove', { clientX: 90, clientY: 210 });");
        assertEquals("30px", eval("x(A)").asString(), "30 along, the 200 down nothing");
        assertEquals(". . .", eval("shifts()").asString(), "under half a pitch: nobody moves");
        eval("A.fire('pointermove', { clientX: 110, clientY: -40 });");
        assertEquals("50px", eval("x(A)").asString());
        assertEquals(". -80px .", eval("shifts()").asString(), "past half a pitch: B steps one pitch left, live");
        eval("A.fire('pointermove', { clientX: 900, clientY: 10 });");
        assertEquals("160px", eval("x(A)").asString(), "kept within the rail: the last slot");
        assertEquals(". -80px -80px", eval("shifts()").asString());
        eval("A.fire('pointermove', { clientX: 125, clientY: 10 });");
        assertEquals("65px", eval("x(A)").asString());
        assertEquals(". -80px .", eval("shifts()").asString(), "back under two: C steps back");
    }

    @Test
    void letGo_theChipSettlesOntoItsSlot_everyStepTakenBackAtOnce_andTheStripSaysWhereItLanded() {
        eval("A.fire('pointerdown', { button: 0, pointerId: 1, clientX: 60, clientY: 10, target: A }); A.fire('pointermove', { clientX: 66, clientY: 10 }); A.fire('pointermove', { clientX: 150, clientY: 10 }); log.length = 0;");
        eval("A.fire('pointerup', { clientX: 150, clientY: 10 });");
        assertEquals("drop:A@1 settle:A:10px 0->0 0/160", log(), "landed on B's slot, then the last leg: from 10 past the slot onto it, in the design's 160ms");
        assertEquals("seated", eval("state(A)").asString());
        assertEquals("-", eval("x(A)").asString());
        assertEquals(". . .", eval("shifts()").asString(), "B's step taken back");
        assertEquals("1", eval("String(B.cancelled)").asString(), "and not eased: B is where the arrangement puts it, not sliding there");
        assertTrue(eval("!strip.el.has('mtp_strip_loose')").asBoolean());
        // a drag that goes nowhere says nothing, and a cancelled one neither
        eval("log.length = 0; A.fire('pointerdown', { button: 0, pointerId: 2, clientX: 60, clientY: 10, target: A }); A.fire('pointermove', { clientX: 66, clientY: 10 }); A.fire('pointerup', { clientX: 66, clientY: 10 });");
        assertEquals("select:A settle:A:6px 0->0 0/160", log(), "its own slot: no drop, the settle only");
        eval("log.length = 0; A.fire('pointerdown', { button: 0, pointerId: 3, clientX: 60, clientY: 10, target: A }); A.fire('pointermove', { clientX: 150, clientY: 10 }); A.fire('pointercancel', {});");
        assertEquals("select:A settle:A:90px 0->0 0/160", log(), "cancelled: back onto its own slot, no drop");
        assertEquals(". . .", eval("shifts()").asString());
    }

    @Test
    void aPinnedChipIsNeitherDraggedNorPassed() {
        eval("chips = make(['P', 'Q', 'R'], true); strip.arrange(chips); var Pc = chips[0].el, Q = chips[1].el; log.length = 0;");
        eval("Pc.fire('pointerdown', { button: 0, pointerId: 1, clientX: 60, clientY: 10, target: Pc }); Pc.fire('pointermove', { clientX: 200, clientY: 10 }); Pc.fire('pointerup', { clientX: 200, clientY: 10 });");
        assertEquals("select:P", log(), "pinned: selected, never dragged");
        eval("log.length = 0; Q.fire('pointerdown', { button: 0, pointerId: 2, clientX: 140, clientY: 10, target: Q }); Q.fire('pointermove', { clientX: 134, clientY: 10 }); Q.fire('pointermove', { clientX: 0, clientY: 10 });");
        assertEquals("0px", eval("x(Q)").asString(), "never before the pinned one: held at its own slot");
        assertEquals(". . .", eval("shifts()").asString());
        eval("Q.fire('pointerup', { clientX: 0, clientY: 10 });");
        assertEquals("select:Q", log(), "on its own slot already: nothing to settle");
    }
}
