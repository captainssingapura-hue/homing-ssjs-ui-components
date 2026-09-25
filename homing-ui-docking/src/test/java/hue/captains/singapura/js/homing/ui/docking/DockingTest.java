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
 * The protocol over a fake DOM with rectangles: a chip pulled off a strip
 * floats under the same hand, widget and all, and the dock has lost it; a
 * floating pane dragged over a dock is offered — the dock lit, the mark
 * where the tab would land — and dropped there becomes its tab; released
 * elsewhere it stays afloat. Every step is data on one sink, in order. A tab
 * of any id floats, and a float is whole or not at all: refused, the tab is
 * where it was. The fake party holds the real one's rule for a name.
 */
class DockingTest extends JsModuleTestBase {

    private static final String P = "/homing/js/hue/captains/singapura/js/homing/ui/";

    // Elements that know their children, classes, attributes, inline
    // properties and a rectangle the test sets; a party branch; a css
    // manager over classList; the typed class names as strings.
    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), attrs = {}, props = {};
            var node = { tag: tag, children: [], parentNode: null, listeners: {}, textContent: "", rect: { left: 0, top: 0, right: 0, bottom: 0, width: 0, height: 0 },
                // the bar measures itself to squeeze the row: a style bag and a scroll box, unread by anything else here
                style: { props: {}, setProperty: function (k, v) { this.props[k] = v; }, removeProperty: function (k) { delete this.props[k]; } },
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
        // a widget by the law: a member of the dock the tab is first added to, with activate(); afloat, a widget of the desk's
        function widget(key, into) { var w = { root: el("w-" + key), activate: function () { log.push(key + ":activate"); }, dispose: function () { log.push(key + ":disposed"); if (w.focus.in) w.focus.leave(); } }; w.focus = (into || A.focus).join(key, w); return w; }
        var sink = function (ev) {
            switch (ev.kind) {
                case "Opened": log.push("opened:" + ev.id); break;
                case "Released": log.push("released:" + ev.id); break;
                case "Closed": log.push("closed:" + ev.id); break;
                case "Raised": log.push("raised:" + ev.id); break;
                case "Moved": log.push("moved:" + ev.id); break;
                case "Docked": log.push("docked:" + ev.tabId + "@" + ev.slotId + "#" + ev.index); break;
                case "Undocked": log.push("undocked:" + ev.tabId + "<" + ev.slotId); break;
                case "TabAttached": log.push("attached:" + ev.slotId + ":" + ev.tab.id + "@" + ev.atIndex); break;
                case "TabActivated": log.push("active:" + ev.slotId + ":" + ev.tabId); break;
                case "TabAdded": log.push("added:" + ev.slotId + ":" + ev.tab.id); break;
                default: log.push(ev.kind);
            } };
        var docking = new Docking(page.createBranch("docking"), { host: host, onEvent: sink });
        docking.desk.root.rect = { left: 0, top: 0, right: 800, bottom: 600, width: 800, height: 600 };
        docking.desk.root.clientWidth = 800; docking.desk.root.clientHeight = 600;
        function dock(slotId, rect) {
            var h = el("div");
            var d = new MultiTabPane(page.createBranch(slotId), { host: h, slotId: slotId, onEvent: sink });
            d.el.rect = rect;
            d.el.children[0].rect = { left: rect.left, top: rect.top, right: rect.right, bottom: rect.top + 30, width: rect.right - rect.left, height: 30 };
            docking.addDock(d);
            return d;
        }
        var A = dock("a", { left: 0, top: 0, right: 400, bottom: 300 });
        var B = dock("b", { left: 400, top: 0, right: 800, bottom: 300 });
        var afloat = focusParty.root.createBranch("afloat", {});   // where a widget opened on the desk joins, until the desk holds a branch of its own
        A.addTab({ id: "t1", title: "One", widget: widget("w1") });
        A.addTab({ id: "t2", title: "Two", widget: widget("w2") });
        log.length = 0;
        function chips(d) { return d.el.children[0].children[0].children.filter(function (c) { return c.has("mtp_chip"); }); }   // strip, rail, chips
        chips(A).forEach(function (c, i) { c.rect = { left: 40 + 80 * i, top: 0, right: 120 + 80 * i, bottom: 30, width: 80, height: 30 }; });
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
        loadModule(P + "docking/DockEventsModule.js");
        loadModule(P + "docking/FloaterModule.js");
        loadModule(P + "panes/TabPaneModule.js");
        loadModule(P + "panes/TabRegisterModule.js");
        loadModule(P + "docking/DeskModule.js");
        loadModule(P + "docking/DockingModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String log() { return eval("log.join(' ')").asString(); }

    @Test
    void aDragAlongTheStripReorders_andNeverLeavesTheRail() {
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
        assertEquals("active:a:t2 capture:div TabMoved", log(), "the press activates, then a move and nothing else: no float opened, nothing undocked");
        assertEquals("t2,t1", eval("A.tabs().join(',')").asString(), "the drag reordered the dock");
        assertTrue(eval("!c.has('mtp_chip_dragging') && c.has('mtp_chip_seated') && !A.el.children[0].has('mtp_strip_loose') && c.prop('--mtp-drag-x') == null && !chipOf(A, 'One').has('mtp_chip_shifted') && chipOf(A, 'One').prop('--mtp-shift-x') == null").asBoolean(), "let go, nothing of the drag remains; seated again");
        assertEquals("0", eval("docking.desk.panes().length + ''").asString());
    }

    @Test
    void aTabUndockedByCallFloatsUnderTheHandAtTheGrab() {
        // a holder undocks by call with the pointer event and the grab — the press's offset within the chip — as the strip will once it pulls a tab off
        eval("docking.undock(A, { id: 't2' }, { clientX: 160, clientY: 90, pointerId: 7 }, { x: 20, y: 15 });");
        assertEquals("opened:t2 raised:t2 undocked:t2<a capture:header", log(), "detached — the active tab stays, so nothing else is activated; the desk opens it, then the undock is said; the hand is taken over");
        assertEquals("t1", eval("A.tabs().join(',')").asString(), "the dock lost the tab");
        assertEquals("w-w2", eval("docking.desk.pane('t2').body.children[0].tag").asString(), "the widget travelled, root and all");
        assertTrue(eval("docking.desk.pane('t2').root.has('fp_held')").asBoolean(), "the float is in the hand");
        assertEquals("140,75", eval("var b = docking.desk.pane('t2').bounds(); b.x + ',' + b.y").asString(), "the frame sits under the hand at the grab: the press's 20 and 15 within the chip");
    }

    @Test
    void aTabUndockedAtAPoint_aMenusPick_floatsWithNoHand() {
        eval("docking.undockAt(A, { id: 't2' }, { x: 160, y: 90 });");
        assertEquals("opened:t2 raised:t2 undocked:t2<a", log(), "the desk opens it, the undock is said, and no hand is taken");
        assertEquals("t1", eval("A.tabs().join(',')").asString(), "the dock lost the tab");
        assertEquals("w-w2", eval("docking.desk.pane('t2').body.children[0].tag").asString(), "the widget travelled");
        assertFalse(eval("docking.desk.pane('t2').root.has('fp_held')").asBoolean(), "not in any hand");
        assertEquals("100,76", eval("var b = docking.desk.pane('t2').bounds(); b.x + ',' + b.y").asString(), "its head at the point, the grip's offset in: 160-60, 90-14");
        eval("docking.undockAt(A, { id: 't1' }, { x: -50, y: -50 });");
        assertEquals("0,0", eval("var c = docking.desk.pane('t1').bounds(); c.x + ',' + c.y").asString(), "kept within the desk");
    }

    /**
     * A tab that floats comes back as ITSELF: the holder's own object, with
     * whatever the holder wrote on it, its icon in the floating pane's head
     * while it is up, and the name the pane had when it came down.
     */
    @Test
    void aTabThatFloatsComesBackAsItself_withItsIconAndTheNameItHadAfloat() {
        eval("var orig = { id: 't3', title: 'Three', icon: el('i'), widget: widget('w3'), mine: 'kept' }; A.addTab(orig);");
        eval("docking.undockAt(A, { id: 't3' }, { x: 100, y: 100 });");
        assertTrue(eval("docking.desk.pane('t3').icon() === orig.icon").asBoolean(), "the icon rides in the floating pane's head");
        eval("docking.desk.pane('t3').title('Three, renamed'); docking.dock('t3', B);");
        assertTrue(eval("B._tabs.find(function (e) { return e.id === 't3'; }).tab === orig").asBoolean(), "the dock took the tab that left, not a copy of it");
        assertEquals("kept", eval("orig.mine").asString(), "with what its holder wrote on it");
        assertEquals("Three, renamed", eval("orig.title").asString(), "and the name it had afloat");
        assertTrue(eval("chipOf(B, 'Three, renamed')._icon.children[0] === orig.icon").asBoolean(), "its icon back on a chip");
        assertFalse(eval("docking._carried.has('t3')").asBoolean(), "nothing is held once it is docked");
    }

    /** The bug: a tab whose id the party would not take as a name was taken off its dock, and then refused by the desk. */
    @Test
    void aTabOfAnyIdFloats_andComesBack() {
        eval("A.addTab({ id: 'picker:3', title: 'Picker', widget: widget('wp') }); log.length = 0;");
        eval("docking.undockAt(A, { id: 'picker:3' }, { x: 100, y: 100 });");
        assertEquals("opened:picker:3 raised:picker:3 undocked:picker:3<a", log());
        assertEquals("t1,t2", eval("A.tabs().join(',')").asString());
        eval("log.length = 0; docking.dock('picker:3', B);");
        assertTrue(log().contains("docked:picker:3@b#0"), log());
        assertEquals("picker:3", eval("B.tabs().join(',')").asString());
    }

    @Test
    void aFloatTheDeskRefusesLeavesTheTabWhereItWas_activeAsItWas() {
        eval("A.switchTab('t2'); var open = docking.desk.open; docking.desk.open = function () { throw new Error('refused'); }; log.length = 0;");
        var ex = assertThrows(PolyglotException.class, () -> eval("docking.undockAt(A, { id: 't2' }, { x: 100, y: 100 })"));
        assertTrue(ex.getMessage().contains("refused"), ex.getMessage());
        eval("docking.desk.open = open;");
        assertEquals("t1,t2", eval("A.tabs().join(',')").asString(), "back at its index");
        assertEquals("t2", eval("A.activeTab()").asString(), "and the one shown, as it was");
        assertTrue(eval("A.contentElOf('t2').children[0] === A.widgetOf('t2').root").asBoolean(), "its widget in its panel");
        assertFalse(eval("docking._carried.has('t2')").asBoolean(), "nothing carried");
        assertFalse(log().contains("undocked"), "nothing said undocked: " + log());
    }

    @Test
    void aTabAlreadyAfloatUnderTheIdIsRefused_beforeAnythingMoves() {
        eval("docking.undockAt(A, { id: 't1' }, { x: 100, y: 100 }); B.addTab({ id: 't1', title: 'Other one', widget: widget('wx', B.focus) }); log.length = 0;");
        var ex = assertThrows(PolyglotException.class, () -> eval("docking.undockAt(B, { id: 't1' }, { x: 100, y: 100 })"));
        assertTrue(ex.getMessage().contains("already afloat"), ex.getMessage());
        assertEquals("t1", eval("B.tabs().join(',')").asString());
        assertEquals("", log(), "not a thing moved, not a thing said");
    }

    @Test
    void aTabClosedAfloatIsForgotten() {
        eval("docking.undockAt(A, { id: 't2' }, { x: 100, y: 100 }); docking.desk.close('t2');");
        assertFalse(eval("docking._carried.has('t2')").asBoolean());
    }

    @Test
    void aFloatDraggedOverADockIsOffered_andDroppedThereBecomesItsTab() {
        eval("docking.desk.open({ id: 'f', title: 'Float', widget: widget('wf', afloat), x: 500, y: 400 }); log.length = 0;");
        eval("var head = docking.desk.pane('f').root.children[0]; head.fire('pointerdown', { button: 0, pointerId: 3, clientX: 520, clientY: 410, target: head });");
        // over B's strip: offered, B lit; over its content: not a landing
        eval("head.fire('pointermove', { clientX: 600, clientY: 20 });");
        assertTrue(eval("B.el.has('mtp_dock_target')").asBoolean(), "B is lit while the tab is offered");
        assertFalse(eval("A.el.has('mtp_dock_target')").asBoolean());
        eval("head.fire('pointermove', { clientX: 600, clientY: 200 });");
        assertFalse(eval("B.el.has('mtp_dock_target')").asBoolean(), "content is not a landing: docks may tile a box");
        // over A's strip, left of every chip: offered at 0 with the mark first
        eval("head.fire('pointermove', { clientX: 10, clientY: 10 });");
        assertTrue(eval("A.el.has('mtp_dock_target')").asBoolean());
        assertFalse(eval("B.el.has('mtp_dock_target')").asBoolean(), "the offer moved");
        assertEquals("mtp_drop_mark", eval("A.el.children[0].children[0].children[0].has('mtp_drop_mark') ? 'mtp_drop_mark' : A.el.children[0].children[0].children[0].tag").asString(), "the mark is first in the rail");
        eval("log.length = 0; head.fire('pointerup', { clientX: 10, clientY: 10 });");
        assertEquals("released:f attached:a:f@0 docked:f@a#0", log());
        assertEquals("f,t1,t2", eval("A.tabs().join(',')").asString());
        assertFalse(eval("docking.desk.has('f')").asBoolean());
        assertFalse(eval("A.el.has('mtp_dock_target')").asBoolean(), "the offer is over");
        assertEquals("w-wf", eval("A.contentElOf('f').children[0].tag").asString(), "the widget is in the dock's panel");
        assertEquals("a", eval("A.widgetOf('f').focus.in.name").asString(), "and its membership adopted into the dock's branch");
    }

    @Test
    void aFloatLetGoOffEveryDockStaysAfloat() {
        eval("docking.desk.open({ id: 'f', title: 'Float', widget: widget('wf', afloat), x: 500, y: 400 }); log.length = 0;");
        eval("var head = docking.desk.pane('f').root.children[0]; head.fire('pointerdown', { button: 0, pointerId: 3, clientX: 520, clientY: 410, target: head });"
           + "head.fire('pointermove', { clientX: 600, clientY: 20 }); head.fire('pointermove', { clientX: 600, clientY: 500 }); head.fire('pointerup', { clientX: 600, clientY: 500 });");
        assertEquals("capture:header moved:f", log());
        assertTrue(eval("docking.desk.has('f')").asBoolean());
        assertFalse(eval("B.el.has('mtp_dock_target')").asBoolean(), "the offer was withdrawn when the pointer left");
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
        void aDeskOverTheDocks() {
            eval("""
                var desk = new Desk(page.createBranch("thedesk"), { host: host, onEvent: sink });
                desk.addDock(A); desk.addDock(B);
                var made = 0;
                function mk(key) { return function (b, t) { b.activate("w"); return widget(key, t.focus); }; }
                function press(target, x, y, on) { (on || target).fire("pointerdown", { button: 0, pointerId: 6, clientX: x, clientY: y, target: target }); }
                log.length = 0;
                """);
        }

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

        @Test
        void aWidgetRestsInTheDesksOwnFocusBranch_whileNoHostHoldsIt() {
            eval("var a = desk.open({ title: 'A', make: mk('wa') }, B); B.letGo(a);");
            assertTrue(eval("desk.focus.name === 'thedesk' && a.widget.focus.in === desk.focus").asBoolean(), "a branch of its own, named for the desk");
            eval("desk.dispose();");
            assertFalse(eval("!!desk.focus.owner.in").asBoolean(), "left when the desk goes");
        }

        @Test
        void detach_andAFloatOfOneDraggedOntoADock_landsShown() {
            eval("var a = desk.open({ title: 'A', make: mk('wa') }, B); desk.open({ title: 'Z', make: mk('wz') }, A); desk.layer.root.rect = { left: 0, top: 0, right: 800, bottom: 600, width: 800, height: 600 };"
               + "desk.layer.root.clientWidth = 800; desk.layer.root.clientHeight = 600; log.length = 0; var f = desk.detach(a, { x: 500, y: 400 });");
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
     * closes it and every tab-pane in it; never offered to a dock; not closed
     * by the desk's Escape; gone when its last tab-pane leaves; named for a
     * reader by the tab it shows.
     */
    @Nested
    class TheFloater {

        @BeforeEach
        void aRegisterAndItsTabPanes() {
            eval("""
                var register = new TabRegister(page.createBranch("tabs"), { focus: afloat });   // the desk's focus branch: where a tab-pane rests
                function open(id) { return register.open({ id: id, title: id.toUpperCase(), make: function (b, t) { b.activate("w"); return widget(id, t.focus); } }); }
                function tailOf(f) { var bar = f.host.bar(); return bar.children[bar.children.length - 1]; }
                function press(target, x, y, on) { (on || target).fire("pointerdown", { button: 0, pointerId: 5, clientX: x, clientY: y, target: target }); }
                """);
        }

        @Test
        void aFloatIsAFrameWithOneBar_theHostsStrip() {
            eval("log.length = 0; var f = docking.float({ x: 40, y: 30, w: 300, h: 200 }); f.take(open('a'));");
            assertEquals("opened:" + id() + " raised:" + id() + " active:" + id() + ":a", log(), "the desk's frame, then what its host shows, on the one sink");
            assertTrue(eval("f.frame.head === null && f.frame.root.children.length === 2 && f.frame.body.children[0] === f.host.el").asBoolean(), "no head: the frame's body holds the host");
            assertTrue(eval("f.host.el.children[0] === f.host.bar()").asBoolean(), "the host's strip is the one bar");
            assertTrue(eval("tailOf(f).children[tailOf(f).children.length - 1].has('mtp_bar_close')").asBoolean(), "its cross at the bar's end");
            assertEquals("A", eval("f.frame.root.getAttribute('aria-label') + ''").asString(), "named for a reader by the tab it shows");
            eval("f.take(open('b')); f.host.switchTab('b');");
            assertEquals("B", eval("f.frame.root.getAttribute('aria-label') + ''").asString());
        }

        @Test
        void theBarsGroundMovesTheFloat_aPressThroughAChipDoesNot() {
            eval("var f = docking.float({ x: 40, y: 30, w: 300, h: 200 }); var a = open('a'); f.take(a); var bar = f.host.bar(); log.length = 0;");
            eval("press(a.chip, 60, 40, bar); bar.fire('pointermove', { clientX: 200, clientY: 200 }); bar.fire('pointerup', { type: 'pointerup', clientX: 200, clientY: 200 });");
            assertEquals("40,30", eval("var b = f.frame.bounds(); b.x + ',' + b.y").asString(), "a press that came through a chip is the chip's");
            eval("log.length = 0; press(bar, 60, 40); bar.fire('pointermove', { clientX: 160, clientY: 90 }); bar.fire('pointerup', { type: 'pointerup', clientX: 160, clientY: 90 });");
            assertEquals("140,80", eval("var b = f.frame.bounds(); b.x + ',' + b.y").asString(), "a press on the ground, and the drag after it, moves the frame");
            assertEquals("capture:div moved:" + id(), log(), "reported once, by the desk; and offered to no dock");
        }

        @Test
        void aFloatOfManyIsNeverOfferedToADock_itMovesAsAWindowDoes() {
            eval("var f = docking.float({ x: 500, y: 400, w: 300, h: 200 }); f.take(open('a')); f.take(open('b')); var bar = f.host.bar(); log.length = 0;"
               + "press(bar, 520, 410); bar.fire('pointermove', { clientX: 600, clientY: 20 });");
            assertFalse(eval("B.el.has('mtp_dock_target')").asBoolean(), "over B's strip, B is not lit: a float of two is not one tab");
            eval("bar.fire('pointerup', { type: 'pointerup', clientX: 600, clientY: 20 });");
            assertTrue(eval("docking.desk.has(f.id) && f.host.tabs().join(',') === 'a,b' && B.tabs().join(',') === ''").asBoolean(), "still afloat, its tabs in it");
        }

        /** DETACH: a tab-pane off its dock into a float of its own, as it is — the same chip, the same pane — reported as one move. */
        @Test
        void detachFloatsATabPaneAsItIs_inAFloatOfItsOwn_reportedAsAMove() {
            eval("var a = open('a'); A.take(a); var chip = a.chip, pane = a.pane; log.length = 0; var f = docking.detach(a, { x: 160, y: 90 });");
            assertTrue(eval("f.host.has('a') && !A.has('a') && a.host() === f.host && a.chip === chip && a.pane === pane").asBoolean(), "the same tab-pane, in a float of its own");
            assertTrue(eval("f.host.bar().children[0].children.indexOf(chip) >= 0").asBoolean(), "its own chip on the float's one bar");
            assertEquals("100,76", eval("var b = f.frame.bounds(); b.x + ',' + b.y").asString(), "its bar at the point, the grip's offset in");
            assertTrue(log().contains("TabMoved"), log());
            assertTrue(eval("docking.docks().indexOf(f.host) >= 0").asBoolean(), "a float is a dock for as long as it lasts");
        }

        @Test
        void aMoveIsRefusedBeforeAnythingLeaves() {
            eval("var a = open('a'); A.take(a); B.addTab({ id: 'b1', title: 'B1', widget: widget('wb1', B.focus) }); log.length = 0;"
               + "var full = new MultiTabPane(page.createBranch('full'), { host: el('div'), slotId: 'full', budget: 1 }); full.addTab({ id: 'x', title: 'X', widget: widget('wx', full.focus) });");
            var ex = assertThrows(PolyglotException.class, () -> eval("docking.move(a, full)"));
            assertTrue(ex.getMessage().contains("would not take"), ex.getMessage());
            assertTrue(eval("A.has('a') && a.host() === A && !full.has('a')").asBoolean(), "nothing left");
            assertEquals("", log(), "and nothing was said");
        }

        /** DRAG TO MOVE: a float of one is that tab in the hand — offered to the docks it passes, and let go over a strip, the tab-pane lands there and the float is gone. */
        @Test
        void aFloatOfOneDraggedOverADocksStrip_landsItsTabThere_andIsGone() {
            eval("var a = open('a'); A.take(a); var f = docking.detach(a, { x: 500, y: 400 }); var bar = f.host.bar(); log.length = 0;"
               + "press(bar, 520, 410); bar.fire('pointermove', { clientX: 600, clientY: 20 });");
            assertTrue(eval("B.el.has('mtp_dock_target')").asBoolean(), "B lit while the tab is offered");
            eval("bar.fire('pointermove', { clientX: 600, clientY: 200 });");
            assertFalse(eval("B.el.has('mtp_dock_target')").asBoolean(), "content is not a landing");
            eval("bar.fire('pointermove', { clientX: 600, clientY: 20 }); bar.fire('pointerup', { type: 'pointerup', clientX: 600, clientY: 20 });");
            assertTrue(eval("B.has('a') && a.host() === B && f.closed() && !docking.desk.has(f.id)").asBoolean(), "landed in B, and the float is gone");
            assertTrue(log().contains("TabMoved"), log());
            assertTrue(log().indexOf("TabMoved") < log().indexOf("closed:"), "the move said first, then the float it left empty gone: " + log());
            assertFalse(eval("B.el.has('mtp_dock_target') || docking.docks().indexOf(f.host) >= 0").asBoolean(), "the offer is over, and the float no dock");
            assertTrue(eval("B.contentElOf('a') === a.pane && a.widget.focus.in === B.focus").asBoolean(), "its own pane in B's content, its membership adopted there");
        }

        /** The float in the hand has its own bar under the hand the whole way: it is never offered to itself. */
        @Test
        void aFloatIsNeverOfferedToItself() {
            eval("var a = open('a'); A.take(a); var f = docking.detach(a, { x: 500, y: 400 });"
               + "f.host.el.rect = { left: 440, top: 386, right: 760, bottom: 606, width: 320, height: 220 }; f.host.bar().rect = { left: 440, top: 386, right: 760, bottom: 416, width: 320, height: 30 };"
               + "var bar = f.host.bar(); press(bar, 520, 400); bar.fire('pointermove', { clientX: 530, clientY: 400 });");
            assertFalse(eval("f.host.el.has('mtp_dock_target')").asBoolean(), "its own strip, under the hand, is not a landing");
            eval("bar.fire('pointerup', { type: 'pointerup', clientX: 530, clientY: 400 });");
            assertTrue(eval("f.host.has('a') && !f.closed()").asBoolean(), "let go there: it only moved");
        }

        @Test
        void aFloatOfOneLetGoOverAnotherFloatsStrip_joinsIt() {
            eval("var a = open('a'), b = open('b'); A.take(a); A.take(b); var g = docking.detach(b, { x: 100, y: 300 }), f = docking.detach(a, { x: 500, y: 400 });"
               + "g.host.el.rect = { left: 40, top: 286, right: 360, bottom: 506, width: 320, height: 220 }; g.host.bar().rect = { left: 40, top: 286, right: 360, bottom: 316, width: 320, height: 30 };"
               + "var bar = f.host.bar(); press(bar, 520, 410); bar.fire('pointermove', { clientX: 300, clientY: 300 });");
            assertTrue(eval("g.host.el.has('mtp_dock_target')").asBoolean(), "the other float is offered it");
            assertFalse(eval("f.host.el.has('mtp_dock_target')").asBoolean(), "never its own");
            eval("bar.fire('pointerup', { type: 'pointerup', clientX: 300, clientY: 300 });");
            assertTrue(eval("g.host.tabs().join(',') === 'b,a' && f.closed() && !g.closed()").asBoolean(), "joined it; the empty one gone");
        }

        @Test
        void theCrossClosesTheFloat_andEveryTabPaneInIt() {
            eval("var f = docking.float({ x: 40, y: 30 }); f.take(open('a')); f.take(open('b')); log.length = 0;"
               + "var cross = tailOf(f).children[tailOf(f).children.length - 1]; cross.fire('click', {});");
            assertEquals(0, eval("register.count()").asInt(), "every tab-pane in it closed");
            assertTrue(eval("f.closed() && !docking.desk.has(f.id)").asBoolean(), "and the frame gone");
            assertTrue(log().startsWith("a:disposed TabRemoved"), log());
            assertTrue(log().contains("b:disposed TabRemoved closed:" + id()), log());
        }

        @Test
        void aFloatWhoseLastTabPaneLeavesIsGone_closedOrMovedAway() {
            eval("var f = docking.float({ x: 40, y: 30 }); var a = open('a'); f.take(a); f.host.letGo(a);");
            assertTrue(eval("f.closed() && !docking.desk.has(f.id) && a.host() === null && register.has('a')").asBoolean(), "moved away: the float is gone, the tab-pane not");
            assertTrue(eval("a.widget.focus.in === afloat").asBoolean(), "its membership at rest in the desk's branch, not gone with the float's host");
            eval("var g = docking.float({ x: 40, y: 30 }); g.take(a); a.close();");
            assertTrue(eval("g.closed() && !docking.desk.has(g.id) && !register.has('a')").asBoolean(), "closed: the float is gone with it");
        }

        /** The gallery's widgets join under their branch's name: two of them in one float, each on a branch named as its tab-pane is. */
        @Test
        void twoWidgetsOfOneKindShareAFloat() {
            eval("function byBranch(b, t) { b.activate('w'); var w = { root: el('w'), activate: function () {} }; w.focus = t.focus.join(b.name, w); return w; }"
               + "var f = docking.float({ x: 40, y: 30 }); f.take(register.open({ id: 'p:1', make: byBranch })); f.take(register.open({ id: 'p:2', make: byBranch }));");
            assertEquals("p:1,p:2", eval("f.host.tabs().join(',')").asString());
        }

        /** A float holds what came to it: no plus on its bar unless asked for. */
        @Test
        void aFloatHasNoPlus_unlessAskedFor() {
            eval("var f = docking.float({ x: 40, y: 30 }); f.take(open('a')); var g = docking.float({ x: 40, y: 30, addable: true }); g.take(open('b'));"
               + "function plus(fl) { return fl.host.bar().children.filter(function (c) { return c.has('mtp_rail_add'); }).length; }");
            assertEquals(0, eval("plus(f)").asInt(), "a float: no plus");
            assertEquals(1, eval("plus(g)").asInt(), "asked for: one");
        }

        @Test
        void theDesksEscapeDoesNotCloseAFloat_sinceClosingOneClosesItsTabs() {
            eval("var f = docking.float({ x: 40, y: 30 }); f.take(open('a'));");
            assertFalse(eval("docking.desk.key({ key: 'Escape' })").asBoolean());
            assertTrue(eval("docking.desk.has(f.id) && register.has('a')").asBoolean());
        }

        private String id() { return eval("f.id").asString(); }
    }
}

