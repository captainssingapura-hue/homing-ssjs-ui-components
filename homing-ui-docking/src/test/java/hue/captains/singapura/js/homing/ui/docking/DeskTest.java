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
 * of its own, the same chip and the same pane, one tab in transit. The desk's
 * hand carries a chip as a chip: torn off its rail by a drag, captured onto a
 * dock's rail when its centre comes near one, the float gone; a float's bar's
 * own ground moves the window and lands nowhere. Every step is data on one sink, in order. The fake party holds the
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
                contains: function (c) { for (var n = c; n; n = n.parentNode) if (n === this) return true; return false; },
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
            mtp_empty = "mtp_empty", mtp_dock_target = "mtp_dock_target", mtp_strip_current = "mtp_strip_current";
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
        function press(target, x, y, on) { (on || target).fire("pointerdown", { button: 0, pointerId: 5, clientX: x, clientY: y, target: target, timeStamp: 0 }); }
        // the desk's hand holds the pointer on its own box: the moves and the release are heard there
        function along(x, y, t) { host.fire("pointermove", { pointerId: 5, clientX: x, clientY: y, timeStamp: t }); }
        function up(x, y, t) { host.fire("pointerup", { pointerId: 5, clientX: x, clientY: y, timeStamp: t }); }
        function rects(d, lefts) { chips(d).forEach(function (c, i) { var l = lefts[i]; c.rect = { left: l, top: 0, right: l + 80, bottom: 30, width: 80, height: 30 }; }); }
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
        loadModule("/homing/js/hue/captains/singapura/js/homing/component/keyboard/KeyboardMarkModule.js");
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
        loadModule(P + "panes/SingleTabPaneModule.js");
        loadModule(P + "panes/TabTearModule.js");
        loadModule(P + "docking/FloaterModule.js");
        loadModule(P + "docking/DeskHandModule.js");
        loadModule(P + "panes/TabPaneModule.js");
        loadModule(P + "panes/TabRegisterModule.js");
        loadModule(P + "docking/DeskModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String log() { return eval("log.join(' ')").asString(); }

    /** A pane no desk holds: the strip's own hand slides the chip along its rail and nowhere else, however the hand wanders. */
    @Test
    void aDragAlongTheStripReorders_andNeverLeavesTheRail() {
        eval("var A = dock('lone', { left: 0, top: 0, right: 400, bottom: 300 }); A.take(open('t1', 'One')); A.take(open('t2', 'Two')); log.length = 0;"
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
        assertEquals("active:lone:t2 capture:div TabMoved", log(), "the press activates, then a move and nothing else: nothing floated");
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

        /**
         * A DOCKED CHIP IS THE DESK'S HAND'S: within the escape it slides along its rail and lands where it is
         * nearest - a wander up or down is nothing - exactly as the strip's own hand would have it.
         */
        @Test
        void aDockedChipSlidesAlongItsRail_byTheDesksHand() {
            eval("var z = desk.open({ id: 'z', title: 'Z', make: mk('wz') }, A), y = desk.open({ id: 'y', title: 'Y', make: mk('wy') }, A); rects(A, [20, 100]); log.length = 0;"
               + "press(y.chip, 120, 15); along(110, 40, 10); along(60, 0, 20);");
            assertEquals("capture:div", eval("log.filter(function (l) { return l.indexOf('capture') === 0; }).join()").asString(), "the pointer held on the desk's own box");
            assertEquals("-60px", eval("y.chip.prop('--mtp-drag-x')").asString(), "slid along the rail, held 20 px in, nearer Z's slot than its own; the hand 10 px below the strip");
            eval("up(60, 0, 30);");
            assertEquals("y,z", eval("A.tabs().join(',')").asString(), "landed where it was nearest");
            assertEquals(0, eval("desk.floats().length").asInt(), "nothing torn");
        }

        /**
         * TORN BY A DRAG: past the escape a docked chip is torn off into a float of its own at the breach - one
         * TabMoved - which waits there while the hand flies on, and goes to the hand once the flight settles.
         * Brought over another dock's strip, its centre within the capture, it is CAPTURED: moved into that dock
         * where its centre is along the strip, the float gone, and the slide going on there; let go, it lands.
         */
        @Test
        void aDockedChipPulledOut_isTornIntoAFloat_andCapturedOntoAnotherDock() {
            eval("var a = desk.open({ id: 'a', title: 'A', make: mk('wa') }, B), z = desk.open({ id: 'z', title: 'Z', make: mk('wz') }, A); layered();"
               + "rects(B, [420]); rects(A, [20]); log.length = 0;"
               + "press(a.chip, 440, 15); for (var t = 10; t <= 50; t += 10) along(440, 15 + 2 * t, t);");
            assertTrue(eval("!B.has('a') && desk.floats().length === 1 && desk.floats()[0].host.has('a')").asBoolean(), "torn: in a float of its own");
            assertTrue(log().contains("TabMoved"), log());
            eval("var f = desk.floats()[0], at = f.frame.bounds(); for (t = 60; t <= 100; t += 10) along(440, 15 + 2 * t, t);");
            assertEquals(eval("at.x + ',' + at.y").asString(), eval("var b = f.frame.bounds(); b.x + ',' + b.y").asString(), "in flight: the float waits at the breach");
            eval("for (t = 110; t <= 220; t += 10) desk.hand.tick(t);");
            assertEquals("follow", eval("desk.hand.tear.phase()").asString(), "the hand at rest: the flight settled");
            assertEquals(160, eval("f.frame.bounds().y - at.y").asInt(), "and the float gone to the hand, resting 160 px on from the breach");
            eval("log.length = 0; along(300, 200, 230); along(60, 20, 240);");
            assertTrue(eval("A.has('a') && f.closed() && a.host() === A && a.widget.focus.in === A.focus").asBoolean(), "captured onto A, the float gone");
            assertEquals("z,a", eval("A.tabs().join(',')").asString(), "where its centre is along A's strip: past Z's middle");
            assertTrue(log().indexOf("TabMoved") >= 0 && log().indexOf("TabMoved") < log().indexOf("closed:"), "the move said first, then the float it left: " + log());
            assertEquals("rail", eval("desk.hand.tear.phase()").asString(), "and on A's rail, still in the hand");
            eval("up(60, 20, 250);");
            assertTrue(eval("desk.hand.held() === null && A.activeTab() === 'a'").asBoolean(), "let go: landed, the one A shows");
        }

        @Test
        void anOpenTheHostRefuses_leavesNothingBehind() {
            eval("var full = new MultiTabPane(page.createBranch('full'), { host: el('div'), slotId: 'full' }); desk.open({ title: 'X', make: mk('same') }, full); log.length = 0;");
            var ex = assertThrows(PolyglotException.class, () -> eval("desk.open({ title: 'Y', make: mk('same') }, full)"));
            assertTrue(ex.getMessage().contains("would not take"), ex.getMessage());
            assertEquals(1, eval("desk.register.count()").asInt(), "the one it opened for the refusal is closed again");
            assertEquals("same:disposed", log(), "its widget's member name is taken there: refused, and nothing left behind");
        }

        /** THE LIMIT IS THE DESK'S: spent, an open beyond it is refused and every dock's plus greyed — a dock added while full too; a close gives the room back. */
        @Test
        void theDesksBudget_greysEveryPlus_andAnOpenBeyondItIsRefused() {
            loadModule(P + "panes/TabSourceModule.js");
            eval("var small = new Desk(page.createBranch('small'), { host: host, budget: 2, focusName: 'small' });"
               + "var C = dock('c', { left: 0, top: 300, right: 400, bottom: 600 }), D = dock('d', { left: 400, top: 300, right: 800, bottom: 600 });"
               + "small.addDock(C); small.addDock(D);"
               + "var src = new TabSource(page.createBranch('src'), { desk: small, kinds: [ { id: 'note', make: function (b, p) { b.activate('w'); return widget('wn', p.focus); } } ] });"
               + "var one = small.open({ title: '1', make: mk('s1') }, C);");
            assertTrue(eval("C.canAdd() && D.canAdd() && src.canAdd(C)").asBoolean(), "room for one more");
            eval("small.open({ title: '2', make: mk('s2') }, D);");
            assertTrue(eval("!C.canAdd() && !D.canAdd() && !src.canAdd(C)").asBoolean(), "full: every plus greyed, and the source says so");
            var ex = assertThrows(PolyglotException.class, () -> eval("small.open({ title: '3', make: mk('s3') }, C)"));
            assertTrue(ex.getMessage().contains("budget of 2"), ex.getMessage());
            eval("var E = dock('e', { left: 0, top: 600, right: 400, bottom: 700 }); small.addDock(E);");
            assertFalse(eval("E.canAdd()").asBoolean(), "a dock that joins a full desk is greyed at once");
            eval("one.close();");
            assertTrue(eval("C.canAdd() && D.canAdd() && E.canAdd()").asBoolean(), "a close gives the room back");
            eval("small.open({ title: '3', make: mk('s3') }, C); small.removeDock(E);");
            assertTrue(eval("!C.canAdd() && E.canAdd()").asBoolean(), "a dock the desk let go is no longer the desk's to grey");
            assertEquals("Infinity", eval("String(desk.register.budget())").asString(), "a desk told no budget has no limit");
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
     * THE FLOATER (RFC 0066 E3, appendix "tab-panes", §7): a frame on the desk
     * around ONE tab in transit — a single-tab pane, never a second dock. One
     * bar, the tab's chip, and the whole of it, chip and all, moves the frame
     * but for the chip's cross, which closes the tab and the float with it; a
     * second tab refused, by a take or a move; never offered a tab by a drag;
     * a press in it lands in the tab; not closed by the layer's Escape; gone
     * when its tab-pane leaves; named for a reader by its tab.
     */    @Nested
    class TheFloater {

        @BeforeEach
        void aMeasuredLayer() {
            eval("""
                layered();
                log.length = 0;
                """);
        }

        /** A float brought back under its old name keeps it; the next float is named past every name on the desk; the floats read bottom first. */
        @Test
        void aFloatKeepsTheNameItComesBackUnder_andTheNextIsNamedPastIt() {
            assertEquals(0, eval("desk.floats().length").asInt(), "no layer yet: no floats, and none made by asking");
            eval("var back = desk.float({ id: 'float-2', x: 40, y: 30, w: 300, h: 200 }); back.take(open('a'));"
               + "var one = desk.float({ x: 60, y: 50 }); one.take(open('b'));"
               + "var two = desk.float({ x: 80, y: 70 }); two.take(open('c'));");
            assertEquals("float-2 float-1 float-3", eval("back.id + ' ' + one.id + ' ' + two.id").asString());
            assertEquals("float-2,float-1,float-3", eval("desk.floats().map(function (f) { return f.id; }).join(',')").asString(), "bottom first");
            eval("desk.layer.raise('float-2');");
            assertEquals("float-1,float-3,float-2", eval("desk.floats().map(function (f) { return f.id; }).join(',')").asString());
            assertEquals("40,30,300,200", eval("var b = back.frame.bounds(); [b.x, b.y, b.w, b.h].join(',')").asString());
        }

        @Test
        void aFloatIsAFrameWithOneBar_itsTabsChip() {
            eval("var f = desk.float({ x: 40, y: 30, w: 300, h: 200, addable: true }); var a = open('a'); f.take(a);");
            assertEquals("opened:" + id() + " raised:" + id() + " active:" + id() + ":a", log(), "the layer's frame, then what its host shows, on the one sink");
            assertTrue(eval("f.frame.head === null && f.frame.root.children.length === 2 && f.frame.body.children[0] === f.host.el").asBoolean(), "no head: the frame's body holds the host");
            assertTrue(eval("f.host.el.children[0] === f.host.bar() && f.host.bar().has('mtp_strip')").asBoolean(), "one bar, on the dock's strip");
            assertTrue(eval("f.host.bar().children.length === 1 && f.host.bar().children[0] === a.chip").asBoolean(), "the chip alone on it: no rail, no plus - even asked for - no count, no cross of its own");
            assertEquals("true", eval("a.chip.getAttribute('aria-selected')").asString());
            assertEquals("100%", eval("f.host.bar().prop('--chip-fit')").asString(), "the chip never wider than its window");
            assertEquals("A", eval("f.frame.root.getAttribute('aria-label') + ''").asString(), "named for a reader by its tab");
            eval("a.title('Alpha');");
            assertEquals("Alpha", eval("f.frame.root.getAttribute('aria-label') + ''").asString(), "and renamed with it");
        }

        /** ONE TAB IN TRANSIT: a second is refused by a take and by a move, before anything leaves. */
        @Test
        void aFloatCarriesOneTab_aSecondIsRefused_byATakeOrAMove() {
            eval("var f = desk.float({ x: 40, y: 30 }); f.take(open('a')); var b = open('b'); A.take(b); log.length = 0;");
            var ex = assertThrows(PolyglotException.class, () -> eval("f.take(open('c'))"));
            assertTrue(ex.getMessage().contains("carries its one tab"), ex.getMessage());
            assertFalse(eval("f.host.admits(b) || f.host.canTake(b)").asBoolean(), "it admits nothing while it carries one");
            ex = assertThrows(PolyglotException.class, () -> eval("desk.move(b, f.host)"));
            assertTrue(ex.getMessage().contains("would not take"), ex.getMessage());
            assertTrue(eval("A.has('b') && b.host() === A && f.host.tabs().join(',') === 'a'").asBoolean(), "nothing left, nothing joined");
        }

        /** THE BAR'S OWN GROUND MOVES THE WINDOW, and docks nowhere: dragged over a dock's strip and let go, the float only moved. */
        @Test
        void theBarsGroundMovesTheFloat_asAWindow_andLandsNowhere() {
            eval("var f = desk.float({ x: 40, y: 30, w: 300, h: 200 }); var a = open('a'); f.take(a); var bar = f.host.bar(); log.length = 0;");
            eval("press(bar, 60, 40); bar.fire('pointermove', { clientX: 600, clientY: 15 }); bar.fire('pointerup', { type: 'pointerup', clientX: 600, clientY: 15 });");
            assertEquals("580,5", eval("var b = f.frame.bounds(); b.x + ',' + b.y").asString(), "the frame moved with the hand, over B's strip");
            assertTrue(eval("f.host.has('a') && !B.has('a') && !f.closed()").asBoolean(), "and landed nowhere");
            assertEquals("capture:div moved:" + id(), log(), "the window's own drag: no TabMoved");
            eval("log.length = 0; press(a.chip._close, 600, 15, bar); bar.fire('pointermove', { clientX: 660, clientY: 60 });");
            assertEquals("580,5", eval("var b = f.frame.bounds(); b.x + ',' + b.y").asString(), "a press on the chip's cross moves nothing");
        }

        /**
         * THE CHIP IS CARRIED AS A CHIP: a float dragged by its chip starts afloat, the frame following the hand; a
         * float's own bar, or another float's, is no landing; let go off every dock, it stays afloat, said moved
         * once; brought over a dock's strip, it is captured there at once, the float gone.
         */
        @Test
        void aFloatDraggedByItsChip_followsTheHand_andIsCapturedOntoADock() {
            eval("var a = open('a'), b = open('b'); A.take(a); A.take(b); var g = desk.detach(b, { x: 100, y: 300 }), f = desk.detach(a, { x: 500, y: 400 });"
               + "f.frame.root.rect = { left: 440, top: 386, right: 760, bottom: 606, width: 320, height: 220 }; a.chip.rect = { left: 448, top: 390, right: 528, bottom: 418, width: 80, height: 28 };"
               + "g.host.bar().rect = { left: 40, top: 286, right: 360, bottom: 316, width: 320, height: 30 };"
               + "var bar = f.host.bar(); log.length = 0; press(a.chip, 460, 400, bar);");
            assertEquals("follow", eval("desk.hand.tear.phase()").asString(), "afloat from the press");
            eval("along(470, 300, 10);");
            assertEquals("450,286", eval("var r = f.frame.bounds(); r.x + ',' + r.y").asString(), "the frame follows, held where the chip was taken: 470-20, 300-14");
            eval("along(220, 300, 20);");
            assertTrue(eval("g.host.has('b') && f.host.has('a') && !f.closed()").asBoolean(), "another float's bar is no landing");
            eval("up(220, 450, 30);");
            assertTrue(eval("f.host.has('a') && !f.closed()").asBoolean(), "let go off every dock: still afloat");
            assertTrue(log().endsWith("moved:" + id()), "said moved, once: " + log());
            assertFalse(log().contains("TabMoved"), log());
            eval("f.frame.root.rect = { left: 200, top: 436, right: 520, bottom: 656, width: 320, height: 220 }; a.chip.rect = { left: 208, top: 440, right: 288, bottom: 468, width: 80, height: 28 };"
               + "log.length = 0; press(a.chip, 220, 450, bar); along(600, 20, 40);");
            assertTrue(eval("B.has('a') && a.host() === B && f.closed() && !desk.layer.has(f.id)").asBoolean(), "over B's strip: captured there at once, the float gone");
            assertTrue(log().indexOf("TabMoved") < log().indexOf("closed:"), "the move said first: " + log());
            eval("up(600, 20, 50);");
            assertTrue(eval("B.contentElOf('a') === a.pane && a.widget.focus.in === B.focus && desk.hand.held() === null").asBoolean(), "landed: its own pane in B, its membership adopted there");
        }

        /** THE KEYS GO TO THE TAB: the pane is a road, never a place — granted, it lands in the tab; it holds nothing a widget yields; the chip and the bar say when the keys are inside. */
        @Test
        void aPressInAFloatLandsInItsTab_thePaneHoldsNothing() {
            eval("var f = desk.float({ x: 40, y: 30 }); var a = open('a'); f.take(a); log.length = 0; f.host.granted('claim');");
            assertEquals("a:activate", log(), "granted, it lands in the tab at once");
            eval("log.length = 0; f.host.granted('native');");
            assertEquals("", log(), "the browser's own focus arriving is left where it is");
            assertFalse(eval("f.host.wouldHold()").asBoolean(), "a yield from the tab passes it by: there is no bar to come back to");
            eval("f.host.within(true);");
            assertTrue(eval("a.chip._mark.has('mtp_chip_mark_on') && f.host.bar().has('mtp_strip_current') && !a.chip.has('mtp_chip_lifted')").asBoolean(), "the keys inside: the chip marked, the bar lit, nothing lifted");
            eval("f.host.within(false);");
            assertFalse(eval("a.chip._mark.has('mtp_chip_mark_on') || f.host.bar().has('mtp_strip_current')").asBoolean());
        }
        /** DETACH: a tab-pane off its dock into a float of its own, as it is — the same chip, the same pane — reported as one move; its bar at the point, kept within the layer. */
        @Test
        void detachFloatsATabPaneAsItIs_inAFloatOfItsOwn_reportedAsAMove() {
            eval("var a = open('a'); A.take(a); var chip = a.chip, pane = a.pane; log.length = 0; var f = desk.detach(a, { x: 160, y: 90 });");
            assertTrue(eval("f.host.has('a') && !A.has('a') && a.host() === f.host && a.chip === chip && a.pane === pane").asBoolean(), "the same tab-pane, in a float of its own");
            assertTrue(eval("f.host.bar().children.indexOf(chip) >= 0").asBoolean(), "its own chip, the float's one bar");
            assertEquals("100,76", eval("var b = f.frame.bounds(); b.x + ',' + b.y").asString(), "its bar at the point, the grip's offset in: 160-60, 90-14");
            assertTrue(log().contains("TabMoved"), log());
            assertTrue(eval("desk.docks().indexOf(f.host) >= 0").asBoolean(), "a float's host is listed with the docks while it lasts");
            eval("var b = open('b'); A.take(b); var g = desk.detach(b, { x: -50, y: -50 });");
            assertEquals("0,0", eval("var r = g.frame.bounds(); r.x + ',' + r.y").asString(), "kept within the layer");
        }

        /**
         * THE TAB MENU is the desk's to answer when it is handed the steward: Detach floats the tab under its
         * own chip, Close closes it, and a pinned tab is offered neither. And a detach with no point is the
         * same: under the chip.
         */
        @Test
        void theTabMenuIsAnsweredByTheDesk_andADetachWithNoPointGoesUnderTheChip() {
            eval("""
                var menus = { h: {}, handle: function (k, h) { this.h[k] = h; return this; } };
                var d2 = new Desk(page.createBranch("desk2"), { host: host, onEvent: sink, menus: menus });
                var C = new MultiTabPane(page.createBranch("c"), { host: el("div"), slotId: "c", onEvent: sink }); d2.addDock(C);
                var lr = d2.layer.root; lr.rect = { left: 0, top: 0, right: 800, bottom: 600, width: 800, height: 600 }; lr.clientWidth = 800; lr.clientHeight = 600;
                var x = d2.register.open({ id: "x", title: "X", make: mk("x") }); C.take(x);
                x.chip.rect = { left: 200, top: 40, right: 260, bottom: 70, width: 60, height: 30 };
                """);
            assertEquals("function", eval("typeof menus.h[MultiTabPane.MENU].pick").asString(), "the pane's own kind, answered by the desk");
            eval("menus.h.tab.pick('detach', { tab: x, pane: C, anchor: x.chip })");
            assertTrue(eval("x.host() !== C && !C.has('x')").asBoolean(), "floated");
            assertEquals("200,70", eval("var fl = d2.docks().filter(function (h) { return h !== C; })[0]; var b = d2._floaters.get(fl).frame.bounds(); b.x + ',' + b.y").asString(), "the float's corner at the chip's: under it");
            assertTrue(eval("menus.h.tab.state('detach', { tab: x, pane: fl }).disabled && !menus.h.tab.state('close', { tab: x, pane: fl }).disabled").asBoolean(), "afloat: Detach off, Close on");
            eval("menus.h.tab.pick('detach', { tab: x, pane: fl, anchor: x.chip })");
            assertTrue(eval("x.host() === fl && d2.floats().length === 1").asBoolean(), "a Detach asked for afloat anyway does nothing");
            eval("var y = d2.register.open({ id: 'y', title: 'Y', make: mk('y') }); C.take(y); menus.h.tab.pick('close', { tab: y, pane: C })");
            assertFalse(eval("C.has('y')").asBoolean(), "closed");
            assertTrue(eval("menus.h.tab.state('detach', { tab: { pinned: true } }).disabled && !menus.h.tab.state('close', { tab: {} }).disabled").asBoolean(), "a pinned tab is offered neither");
            eval("d2.dispose()");
        }

        /** A close asked for from the tab menu goes to the tab-pane's owner, when it has a hook: the desk closes nothing then. */
        @Test
        void theTabMenusClose_goesToTheOwnersHook_whenItHasOne() {
            eval("""
                var menus = { h: {}, handle: function (k, h) { this.h[k] = h; return this; } };
                var d2 = new Desk(page.createBranch("desk2"), { host: host, onEvent: sink, menus: menus });
                var C = new MultiTabPane(page.createBranch("c"), { host: el("div"), slotId: "c", onEvent: sink }); d2.addDock(C);
                var y = d2.register.open({ id: "y", title: "Y", make: mk("y"), onCloseRequested: function (tp) { log.push("asked:" + tp.id); } }); C.take(y);
                log.length = 0; menus.h.tab.pick('close', { tab: y, pane: C });
                """);
            assertEquals("asked:y", log());
            assertTrue(eval("C.has('y') && !y.closed()").asBoolean(), "still open, where it was");
            eval("d2.dispose()");
        }

        /** An UNMOUNT is its owner's: the tab-pane out of its host, open in the register, resting in the desk - and the host says nothing. */
        @Test
        void unmountTakesATabPaneOutOfItsHost_andNothingIsSaid() {
            eval("var a = open('a'), b = open('b'); desk.move(a, A); desk.move(b, A); log.length = 0;");
            assertTrue(eval("desk.unmount(a) === A").asBoolean(), "the host it left");
            assertTrue(eval("!A.has('a') && a.host() === null && !a.closed() && desk.register.has('a') && a.widget.focus.in === desk.rest").asBoolean(),
                    "in no host, open, its widget at rest in the desk");
            assertEquals("active:a:b", log(), "the neighbour shown, and no TabRemoved, no TabMoved");
            assertTrue(eval("desk.unmount(a) === null").asBoolean(), "in no host: nothing to do");
            eval("var f = desk.float({ x: 40, y: 30 }); desk.move(b, f.host); log.length = 0; desk.unmount(b);");
            assertTrue(eval("f.closed() && b.host() === null && desk.register.has('b')").asBoolean(), "a float it leaves empty goes; the tab-pane stays open");
            assertFalse(log().contains("TabRemoved") || log().contains("TabMoved"), log());
        }

        @Test
        void aMoveIsRefusedBeforeAnythingLeaves() {
            eval("var a = open('a'); A.take(a);"
               + "var full = new MultiTabPane(page.createBranch('full'), { host: el('div'), slotId: 'full' });"
               + "full.take(desk.register.open({ id: 'x', title: 'X', make: mk('a') })); log.length = 0;");   // its widget joined as 'a': a's name is taken there
            var ex = assertThrows(PolyglotException.class, () -> eval("desk.move(a, full)"));
            assertTrue(ex.getMessage().contains("would not take"), ex.getMessage());
            assertTrue(eval("A.has('a') && a.host() === A && !full.has('a')").asBoolean(), "nothing left");
            assertEquals("", log(), "and nothing was said");
        }

        /** SHOW: a tab-pane brought to the front where it is — its host shows it, a float holding it raised — and nothing else: no keys given. */
        @Test
        void showBringsATabPaneToTheFrontWhereItIs() {
            eval("var a = open('a'), b = open('b'); A.take(a); A.take(b); A.switchTab('a');"
               + "var f = desk.float({ x: 10, y: 10 }); f.take(open('c')); var g = desk.float({ x: 20, y: 20 }); g.take(open('d')); log.length = 0;");
            assertTrue(eval("desk.show(b) === A && A.activeTab() === 'b'").asBoolean(), "in a dock: shown there, the host answered");
            assertFalse(log().contains("raised"), "a dock has no frame to raise: " + log());
            eval("log.length = 0; desk.show(desk.register.get('c'));");
            assertTrue(log().contains("raised:" + id()), "in a float under another: its frame raised - " + log());
            assertFalse(log().contains(":activate"), "and no widget handed the keys");
            assertTrue(eval("desk.show(open('loose')) === null && desk.show(null) === null").asBoolean(), "in no host: nothing to show");
        }

        /**
         * TOLD WHERE THE FOCUS IS (RFC 0066 E3, keyboard §17.5): the float on the desk's layer holding the marker
         * comes to the front, whoever put the focus there; away, or in a dock, nothing is raised.
         */
        @Test
        void toldTheFocusIsInAFloatUnderAnother_theDeskRaisesIt() {
            eval("var f = desk.float({ x: 10, y: 10 }); f.take(open('c')); var g = desk.float({ x: 20, y: 20 }); g.take(open('d')); log.length = 0;");
            eval("desk.within(true, { id: 'x', state: 'away', root: f.host.el })");
            assertFalse(log().contains("raised"), "away: nothing raised - " + log());
            eval("desk.within(true, { id: 'x', state: 'lent', root: A.el })");
            assertFalse(log().contains("raised"), "in a dock: no frame to raise - " + log());
            eval("desk.within(true, { id: 'x', state: 'lent', root: f.host.el })");
            assertEquals("raised:" + eval("f.id").asString(), eval("log.filter(function (l) { return l.indexOf('raised') === 0; }).join()").asString(), "the float holding it");
        }

        /** THE CHIP'S CROSS closes its tab, as on a dock, and the float, empty, goes with it. */
        @Test
        void theChipsCrossClosesItsTab_andTheFloatWithIt() {
            eval("var f = desk.float({ x: 40, y: 30 }); var a = open('a'); f.take(a); log.length = 0; a.chip._close.fire('click', {});");
            assertEquals(0, eval("desk.register.count()").asInt(), "the tab-pane closed");
            assertTrue(eval("f.closed() && !desk.layer.has(f.id)").asBoolean(), "and the frame gone");
            assertEquals("a:disposed TabRemoved closed:" + id(), log());
        }

        /**
         * A CLOSE BY CALL asks the tab-pane, as a chip's cross does: one whose owner closes it in its own order is
         * left to it - here, one owner that keeps its tab. The float goes once it is empty, and not before.
         */
        @Test
        void closeAsksItsTabPane_andTheFloatGoesOnlyOnceEmpty() {
            eval("var asked = [];"
               + "function owned(id, keeps) { return desk.register.open({ id: id, title: id.toUpperCase(), make: mk(id), onCloseRequested: function (tp) {"
               + "  asked.push(tp.id); if (!keeps) { desk.unmount(tp); tp.close(); } } }); }"
               + "var f = desk.float({ x: 40, y: 30 }); f.take(owned('a', false)); var g = desk.float({ x: 60, y: 50 }); g.take(owned('b', true)); log.length = 0;"
               + "f.close(); g.close();");
            assertEquals("a,b", eval("asked.join(',')").asString(), "each owner asked");
            assertTrue(eval("f.closed() && !desk.register.has('a')").asBoolean(), "the one its owner closed: gone, and its float");
            assertTrue(eval("!g.closed() && g.host.tabs().join(',') === 'b'").asBoolean(), "the one kept: kept, and the float stays while it carries it");
            eval("desk.unmount(desk.register.get('b'));");
            assertTrue(eval("g.closed() && !desk.layer.has(g.id)").asBoolean(), "and goes once empty");
        }
        @Test
        void aFloatWhoseLastTabPaneLeavesIsGone_closedOrMovedAway() {
            eval("var f = desk.float({ x: 40, y: 30 }); var a = open('a'); f.take(a); f.host.letGo(a);");
            assertTrue(eval("f.closed() && !desk.layer.has(f.id) && a.host() === null && desk.register.has('a')").asBoolean(), "moved away: the float is gone, the tab-pane not");
            assertTrue(eval("a.widget.focus.in === desk.rest").asBoolean(), "its membership at rest in the desk's branch, not gone with the float's host");
            eval("var g = desk.float({ x: 40, y: 30 }); g.take(a); a.close();");
            assertTrue(eval("g.closed() && !desk.layer.has(g.id) && !desk.register.has('a')").asBoolean(), "closed: the float is gone with it");
        }

        /** The gallery's widgets join under their branch's name: two of one kind, each in a float of its own; an id is any string. */
        @Test
        void twoWidgetsOfOneKind_eachInAFloatOfItsOwn() {
            eval("function byBranch(b, t) { b.activate('w'); var w = { root: el('w'), activate: function () {} }; w.focus = t.focus.join(b.name, w); return w; }"
               + "var f = desk.float({ x: 40, y: 30 }); f.take(desk.register.open({ id: 'p:1', make: byBranch })); var g = desk.float({ x: 60, y: 50 }); g.take(desk.register.open({ id: 'p:2', make: byBranch }));");
            assertEquals("p:1 p:2", eval("f.host.tabs().join(',') + ' ' + g.host.tabs().join(',')").asString());
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
