package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The pane against a shimmed DOM, party and css manager: tab-panes of a
 * register are taken, switched, moved, closed and let go, the strip follows
 * the state, a widget is disposed on a close and not on a let-go, and every
 * mutation the pane makes is reported with the studio pane's names and
 * shapes, in order.
 */
class MultiTabPaneTest extends JsModuleTestBase {

    private static final String EVENTS = "/homing/js/hue/captains/singapura/js/homing/ui/panes/PaneEventsModule.js";
    private static final String DRAG   = "/homing/js/hue/captains/singapura/js/homing/ui/panes/TabDragModule.js";
    private static final String FIT    = "/homing/js/hue/captains/singapura/js/homing/ui/panes/TabFitModule.js";
    private static final String WINDOW = "/homing/js/hue/captains/singapura/js/homing/ui/panes/TabWindowModule.js";
    private static final String HAND   = "/homing/js/hue/captains/singapura/js/homing/ui/panes/TabHandModule.js";
    private static final String STRIP  = "/homing/js/hue/captains/singapura/js/homing/ui/panes/TabStripModule.js";
    private static final String KEYS   = "/homing/js/hue/captains/singapura/js/homing/ui/panes/PaneKeysModule.js";
    private static final String MENUS  = "/homing/js/hue/captains/singapura/js/homing/ui/panes/PaneMenusModule.js";
    private static final String MODULE = "/homing/js/hue/captains/singapura/js/homing/ui/panes/MultiTabPaneModule.js";

    // Elements that know their children, classes and attributes; a party
    // branch that mints them; a css manager over classList; the typed class
    // names as the strings the page would resolve them to.
    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), attrs = {};
            var node = { tag: tag, children: [], parentNode: null, listeners: {},
                get firstChild() { return this.children.length ? this.children[0] : null; },
                // the bar measures itself to squeeze the row: a style bag and a scroll box, unread by anything else here
                style: { props: {}, setProperty: function (k, v) { this.props[k] = v; }, removeProperty: function (k) { delete this.props[k]; } },
                clientWidth: 0, scrollLeft: 0,
                // no layout in this DOM: zeroes, which the strip reads as "not measured yet" and leaves alone
                getBoundingClientRect: function () { return { left: 0, top: 0, right: 0, bottom: 0, width: 0, height: 0 }; },
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
                fire: function (t, ev) { (this.listeners[t] || []).slice().forEach(function (fn) { fn(ev || { stopPropagation: function () {}, preventDefault: function () {} }); }); },
                has: function (c) { return classes.has(c); } };
            return node;
        }
        function fakeBranch(name, deregister) {
            var kids = new Map(), names = new Set();
            return { name: name, dissolved: [],
                createElement: function (n, tag) { if (names.has(n)) throw new RangeError("name " + n + " is already in use on branch " + name); names.add(n); return el(tag); },
                createBranch: function (n) { if (kids.has(n)) throw new RangeError("branch " + n + " is already in use on " + name); var b = fakeBranch(n, function () { kids.delete(n); }); kids.set(n, b); return b; },
                dissolveBranch: function (n) { var b = kids.get(n); if (b) b.dissolve(); },
                dissolve: function () { kids.forEach(function (b) { b.dissolve(); }); this.dissolved.push(name); if (deregister) deregister(); },
                activate: function (owner) { this.owner = String(owner); } };
        }
        var css = { addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); },
                    removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); },
                    toggleClass: function (e, c, f) { e.classList.toggle(c, f); },
                    extent: function (e, t) { e._extent = t == null ? null : t; },
                    hasClass: function (e, c) { return e.classList.contains(c); },
                    size: function (e, s) { e.size = s; }, aspect: function (e, a) { e.aspect = a; } };
        var mtp_pane = "mtp_pane", mtp_strip = "mtp_strip", mtp_rail = "mtp_rail", mtp_chip = "mtp_chip", mtp_chip_label = "mtp_chip_label", mtp_chip_icon = "mtp_chip_icon", mtp_chip_icon_on = "mtp_chip_icon_on", mtp_chip_mark = "mtp_chip_mark", mtp_chip_mark_on = "mtp_chip_mark_on", mtp_chip_lifted = "mtp_chip_lifted",
            mtp_chip_dragging = "mtp_chip_dragging", mtp_chip_shifted = "mtp_chip_shifted", mtp_strip_loose = "mtp_strip_loose", mtp_chip_close = "mtp_chip_close", mtp_drop_mark = "mtp_drop_mark",
            mtp_chip_seated = "mtp_chip_seated", mtp_strip_current = "mtp_strip_current",
            mtp_strip_tail = "mtp_strip_tail", mtp_add = "mtp_add", mtp_rail_add = "mtp_rail_add", mtp_bar_close = "mtp_bar_close", mtp_add_mark = "mtp_add_mark", mtp_add_off = "mtp_add_off", mtp_pill = "mtp_pill",
            mtp_content = "mtp_content", mtp_tab_content = "mtp_tab_content", mtp_tab_content_hidden = "mtp_tab_content_hidden",
            mtp_empty = "mtp_empty", mtp_dock_target = "mtp_dock_target";
        var console = { error: function (m, e) { log.push("error:" + m); } };
        var host = el("div");
        var branch = fakeBranch("page");
        var kbEvents = [];
        KeyboardStewardInstance.on(function (e) { kbEvents.push(e.kind + ":" + (focusParty.find(e.id) ? focusParty.find(e.id).name : e.id)); });
        // a widget by the law: a member of the dock's branch (the pane's unless said), with activate(); its Escape yields
        function widget(key, into) {
            var w = { root: el("w-" + key), setActive: function (on) { log.push(key + ":" + (on ? "on" : "off")); },
                      activate: function () { log.push(key + ":activate"); KeyboardStewardInstance.claim(w.focus); },
                      keyDown: function (ev) { log.push(key + ":key:" + ev.key); if (ev.key === "Escape") { KeyboardStewardInstance.yield(w.focus); return true; } return ev.key === "ArrowUp"; },
                      dispose: function () { log.push(key + ":disposed"); w.focus.leave(); } };
            w.focus = (into || pane.focus).join(key, w);
            return w;
        }
        // the desk's register and the branch its widgets rest in: every tab here is a tab-pane opened there
        var crypto = { randomUUID: (function () { var n = 0; return function () { return "u" + (++n); }; })() };
        var elsewhere = focusParty.root.createBranch("elsewhere", { inWalk: function () { return false; } });   // a desk's: out of the keyboard walk
        var register = new TabRegister(branch.createBranch("tabs"), { focus: elsewhere });
        var reg2 = new TabRegister(branch.createBranch("tabs2"), { focus: elsewhere });   // another desk's: the same ids may be open there
        function open(id, extra, reg) {
            return (reg || register).open(Object.assign({ id: id, title: id.toUpperCase(),
                                                          make: function (b, t) { b.activate("widget"); return widget(id, t.focus); } }, extra || {}));
        }
        function put(id, extra, into) { var tp = open(id, extra); (into || pane).take(tp); return tp; }
        // a desk for a source: its register, and a move that is the host's take
        var desk = { register: register, move: function (tp, p, index) { return p.take(tp, index); } };
        function holder() { var h = KeyboardStewardInstance.holder(); return h ? focusParty.find(h).name : "none"; }
        var events = [];
        var paneBranch = branch.createBranch("mtp_s1");
        var pane = new MultiTabPane(paneBranch, { host: host, slotId: "s1", budget: 4,
            onEvent: function (ev) {
                events.push(ev);
                switch (ev.kind) {
                    case "AddRequested": log.push("add?" + ev.slotId); break;
                    case "TabAdded":     log.push("added:" + ev.slotId + ":" + ev.tab.id + "@" + ev.index); break;
                    case "TabRemoved":   log.push("removed:" + ev.slotId + ":" + ev.tab.id + "@" + ev.fromIndex); break;
                    case "TabMoved":     log.push("moved:" + ev.srcSlotId + ":" + ev.tab.id + "@" + ev.srcIndex + "->" + ev.destSlotId + "@" + ev.destIndex); break;
                    case "TabActivated": log.push("active:" + ev.slotId + ":" + ev.tabId); break;
                    case "DetachRequested": log.push("detach?" + ev.slotId + ":" + ev.tabId); break;
                    default: log.push("?" + ev.kind);
                } } });
        function chips() { return pane.el.children[0].children[0].children.filter(function (c) { return c.has("mtp_chip"); }).map(function (c) { return c._label.textContent; }).join(","); }
        function panels() { return pane.el.children[1].children.filter(function (c) { return c.has("mtp_tab_content"); }).map(function (c) { return c.children[0].tag + (c.has("mtp_tab_content_hidden") ? "-" : "+"); }).join(","); }
        function selected() { return pane.el.children[0].children[0].children.filter(function (c) { return c.getAttribute("aria-selected") === "true"; }).map(function (c) { return c._label.textContent; }).join(","); }
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
        loadModule(EVENTS);
        loadModule(DRAG);
        loadModule(FIT);
        loadModule(WINDOW);
        loadModule(HAND);
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/panes/TabChipModule.js");
        loadModule(STRIP);
        loadModule(KEYS);
        loadModule(MENUS);
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/panes/PaneTabsModule.js");
        loadModule(MODULE);
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/panes/TabPaneModule.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/panes/TabRegisterModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String log() { return eval("log.join(' ')").asString(); }
    private String chips() { return eval("chips()").asString(); }
    private String panels() { return eval("panels()").asString(); }

    @Test
    void theFirstTabBecomesActiveAndTheStripFollowsTheState() {
        eval("put('a'); put('b'); put('c')");
        assertEquals("A,B,C", chips());
        assertEquals("w-a+,w-b-,w-c-", panels());
        assertEquals("A", eval("selected()").asString());
        assertEquals("a", eval("pane.activeTab()").asString());
        assertEquals("active:s1:a", log(), "an arrival is the desk's to report: the pane says only what it shows");
        assertEquals("3 / 4", eval("pane.el.children[0].children.slice(-1)[0].children.filter(function (c) { return c.has('mtp_pill'); })[0].textContent").asString());
        assertTrue(eval("pane.el.children[1].children[0].has('mtp_tab_content_hidden')").asBoolean(), "the empty line is hidden once a tab is in");
    }

    /**
     * A tab's name is the holder's to give and the pane's to show: an icon of
     * the holder's before the label, and both changed later by call. Neither
     * is a change to the arrangement, so neither is reported.
     */
    @Test
    void aTabIsNamedByItsHolderWithAnIconAndCanBeRenamed() {
        eval("var fav = el('i'); fav.textContent = 'F'; put('a', { icon: fav }); put('b'); log = []");
        eval("var ca = pane.el.children[0].children[0].children[0], cb = pane.el.children[0].children[0].children[1]");
        assertTrue(eval("ca._icon.children[0] === fav && ca._icon.has('mtp_chip_icon_on')").asBoolean(), "the holder's icon sits in the chip, shown");
        assertTrue(eval("cb._icon.children.length === 0 && !cb._icon.has('mtp_chip_icon_on')").asBoolean(), "a tab with no icon shows none");
        assertEquals(0, eval("ca.children.indexOf(ca._icon)").asInt(), "the icon comes before the label");

        eval("pane.retitle('a', 'Report.md')");
        assertEquals("Report.md,B", chips());
        assertEquals("Report.md", eval("ca.title").asString(), "the tooltip says it too");
        assertEquals("Close Report.md", eval("ca._close.getAttribute('aria-label')").asString(), "and the cross says what it closes");
        assertEquals("Report.md", eval("pane.getState().tabs[0].title").asString(), "the pane's state carries the new name");

        eval("var fav2 = el('i'); pane.reicon('b', fav2)");
        assertTrue(eval("cb._icon.children[0] === fav2 && cb._icon.has('mtp_chip_icon_on')").asBoolean(), "an icon given later is shown");
        eval("pane.reicon('a', null)");
        assertTrue(eval("ca._icon.children.length === 0 && !ca._icon.has('mtp_chip_icon_on')").asBoolean(), "and taken away");
        assertEquals("", log(), "a name and an icon are not the arrangement: nothing is reported");
    }

    @Test
    void aSwitchShowsOnePanelAndSaysSo() {
        eval("put('a'); put('b'); log = []");
        eval("pane.switchTab('b')");
        assertEquals("w-a-,w-b+", panels());
        assertEquals("B", eval("selected()").asString());
        assertEquals("active:s1:b", log());
        eval("pane.switchTab('b')");
        assertEquals("active:s1:b", log(), "switching to the active tab says nothing");
        assertEquals("null,null", eval("[0, 1].map(function (i) { return String(pane.el.children[0].children[0].children[i].getAttribute('tabindex')); }).join(',')").asString(), "no chip takes native focus: the keys over the strip are the pane's");
    }

    @Test
    void aMoveReordersTheChipsAndReportsWhereTheTabWentWithThisSlotAsBothEnds() {
        eval("put('a'); put('b'); put('c'); log = []");
        assertTrue(eval("pane.moveTab('a', 2)").asBoolean());
        assertEquals("B,C,A", chips());
        assertEquals("b,c,a", eval("pane.tabs().join(',')").asString());
        assertEquals("moved:s1:a@0->s1@2", log());
        assertFalse(eval("pane.moveTab('a', 9)").asBoolean(), "clamped to the end, where it already is");
        assertTrue(eval("pane.moveTab('c', 0)").asBoolean());
        assertEquals("C,B,A", chips());
        assertEquals("a", eval("pane.activeTab()").asString(), "a move does not change the active tab");
        assertEquals("w-a+,w-b-,w-c-", panels(), "the panels stay where they were appended; only the chips move");
    }

    @Test
    void aCloseDisposesTheWidgetReportsThenActivatesTheNeighbour() {
        eval("put('a'); put('b'); put('c'); pane.switchTab('b'); log = []");
        eval("pane.removeTab('b')");
        assertEquals("A,C", chips());
        assertEquals("b:disposed removed:s1:b@1 active:s1:c", log());
        assertEquals("w-a-,w-c+", panels());
        eval("log = []; pane.removeTab('c')");
        assertEquals("c:disposed removed:s1:c@1 active:s1:a", log(), "no tab after it: the one before");
        eval("log = []; pane.removeTab('a')");
        assertEquals("a:disposed removed:s1:a@0", log());
        assertNull(eval("pane.activeTab()").asString());
        assertFalse(eval("pane.el.children[1].children[0].has('mtp_tab_content_hidden')").asBoolean(), "the empty line is back");
    }

    @Test
    void theSizeAndAspectReachEveryChip_andTheBar_nowAndLater() {
        eval("put('a'); pane.size(0.5); pane.aspect(-2); put('b')");
        assertEquals("0.5/-1 0.5/-1", eval("pane.el.children[0].children[0].children.filter(function (c) { return c.has('mtp_chip'); }).map(function (c) { return c.size + '/' + c.aspect; }).join(' ')").asString(), "clamped to −1..1; a chip made after gets them too");
        assertEquals("0.5/-1", eval("var s = pane.el.children[0]; s.size + '/' + s.aspect").asString(),
                     "and the bar, which keeps a tab's room while it holds none: an axis does not inherit, so the bar needs its own");
        eval("pane.size(null); pane.aspect(null)");
        assertEquals("null/null", eval("var c = pane.el.children[0].children[0].children[0]; c.size + '/' + c.aspect").asString(), "null gives the design's back");
        assertEquals("null/null", eval("var s = pane.el.children[0]; s.size + '/' + s.aspect").asString());
    }

    @Test
    void pinnedTabsSitFirstCannotCloseAndAreNotPassed() {
        eval("put('a'); put('p', { pinned: true }); put('b'); log = []");
        assertEquals("P,A,B", chips());
        assertEquals("3", eval("pane.el.children[0].children[0].children[0].children.length").toString(), "the icon's slot, the label and the within mark, and no cross on the pinned chip");
        assertEquals("4", eval("pane.el.children[0].children[0].children[1].children.length").toString(), "an unpinned chip: the icon's slot, the label, the mark and the cross");
        eval("pane.moveTab('b', 0)");
        assertEquals("P,B,A", chips(), "a drop never lands before the pinned");
        assertEquals("moved:s1:b@2->s1@1", log());
    }

    @Test
    void theBudgetIsAPreconditionAndTheAddButtonFollowsIt() {
        eval("put('a'); put('b'); put('c')");
        assertTrue(eval("pane.canAdd()").asBoolean());
        assertFalse(eval("pane.el.children[0].children.filter(function (c) { return c.has('mtp_rail_add'); })[0].has('mtp_add_off')").asBoolean());
        eval("put('d')");
        assertFalse(eval("pane.canAdd()").asBoolean());
        assertTrue(eval("pane.el.children[0].children.filter(function (c) { return c.has('mtp_rail_add'); })[0].has('mtp_add_off')").asBoolean());
        assertTrue(eval("pane.el.children[0].children.filter(function (c) { return c.has('mtp_rail_add'); })[0].disabled").asBoolean());
        var ex = assertThrows(PolyglotException.class, () -> eval("put('e')"));
        assertTrue(ex.getMessage().contains("budget of 4"), ex.getMessage());
        eval("log = []; pane.el.children[0].children.filter(function (c) { return c.has('mtp_rail_add'); })[0].fire('click')");
        assertEquals("", log(), "the add button does nothing when the budget is spent");
        eval("pane.removeTab('d'); log = []; pane.el.children[0].children.filter(function (c) { return c.has('mtp_rail_add'); })[0].fire('click')");
        assertEquals("add?s1", log());
        eval("pane.setAddEnabled(false)");
        assertFalse(eval("pane.canAdd()").asBoolean());
    }

    @Test
    void theCrossClosesAndAChipPressSwitches() {
        eval("put('a'); put('b'); log = []");
        eval("var chip = pane.el.children[0].children[0].children[1]; chip.fire('pointerdown', { button: 0, target: chip, clientX: 0, clientY: 0 })");
        assertEquals("active:s1:b", log(), "the press selects, before any release");
        eval("log = []; chip.fire('pointerdown', { button: 2, target: chip, clientX: 0, clientY: 0 }); chip.fire('click')");
        assertEquals("", log(), "a secondary button or a bare click is nothing");
        eval("log = []; pane.el.children[0].children[0].children[1]._close.fire('click')");   // the chip's cross
        assertEquals("b:disposed removed:s1:b@1 active:s1:a", log());
    }

    /**
     * Where the keys are is said twice: on the frame, and on the ACTIVE CHIP —
     * and the chip says it as lift and shift, not as a mark alone. The cursor
     * is on the bar: the chip is LIFTED off the row and its mark carries the
     * colour part of the way. The keys go into the tab: the chip is put back
     * down at its regular elevation and the same mark carries the same colour
     * at FULL. They leave: a chip like any other. One colour, two degrees,
     * and the elevation says which.
     */
    @Test
    void theActiveChipLiftsOnTheBar_andSitsDownWithTheColourAtFullWhenTheKeysAreInIt() {
        eval("put('a'); put('b'); pane.switchTab('a')");
        eval("""
            var chips = pane.el.children[0].children[0];   // the rail: the chips live in the window, not on the bar
            function chip(i) { return chips.children[i]; }
            function look(i) { var c = chip(i), m = c._mark;
                return (c.has('mtp_chip_lifted') ? 'lifted' : 'down') + '/' + (m.has('mtp_chip_mark_on') ? 'mark' : '-') + '/' + String(m._extent === undefined ? null : m._extent); }
            """);
        assertEquals("down/-/null", eval("look(0)").asString(), "nobody holds: a chip like any other");
        eval("pane.granted()");
        assertEquals("lifted/mark/0.45", eval("look(0)").asString(), "the cursor is on the bar: picked up, the colour part of the way");
        assertEquals("down/-/null", eval("look(1)").asString(), "and on no other chip");
        eval("pane.taken(); pane.within(true)");
        assertEquals("down/mark/1", eval("look(0)").asString(), "the keys are in the tab: put down, the colour at full");
        assertEquals("lent", eval("String(pane.el.getAttribute('data-keys'))").asString(), "the frame says lent: the keys are in the dock, lent to what the tab holds");
        eval("pane.switchTab('b')");
        assertEquals("down/-/null", eval("look(0)").asString(), "what is shown carries it");
        assertEquals("down/mark/1", eval("look(1)").asString());
        eval("pane.within(false); pane.granted()");
        assertEquals("lifted/mark/0.45", eval("look(1)").asString(), "back on the bar: picked up again");
        eval("pane.taken()");
        assertEquals("down/-/null", eval("look(0)").asString());
        assertEquals("down/-/null", eval("look(1)").asString(), "and gone");
        // the walk's offer is the frame's alone: a chip is not offered anything
        eval("pane.offered()");
        assertEquals("candidate", eval("String(pane.el.getAttribute('data-keys'))").asString());
        assertEquals("down/-/null", eval("look(0)").asString(), "the offer is the pane's, not a tab's");
        eval("pane.withdrawn()");
    }

    /**
     * The strip's own ground — the room the chips leave — asks for the kind the
     * PAGE named, bound to the pane alone: what it offers is about where the
     * pane sits, which the pane knows nothing of. A right-click that came
     * through a chip is the chip's, never the ground's; a pane told no kind
     * leaves the ground to the browser.
     */
    @Test
    void theStripsGroundAsksForTheKindThePageNamed_boundToThePaneAlone() {
        eval("""
            var asked = [];
            var steward = { open: function (kind, object, at, opts) { asked.push(kind + "@" + at.x + "," + at.y + (object.pane === withGround ? ":pane" : ":?") + (object.tab ? ":tab" : "") + (opts.anchor === withGround.el ? ":anchored" : "")); return kind !== "refused"; } };
            var withGround = new MultiTabPane(branch.createBranch("mtp_s3"), { host: el("div"), slotId: "s3", menus: steward, stripMenu: "split" });
            withGround.take(open("a"));
            var strip = withGround.el.children[0], chip = strip.children[0].children[0];   // the rail is the strip's first child; the chip is in it
            var onGround = { target: strip, clientX: 40, clientY: 8, defaulted: false, preventDefault: function () { this.defaulted = true; } };
            strip.fire("contextmenu", onGround);
            var throughAChip = { target: chip, clientX: 5, clientY: 8, defaulted: false, preventDefault: function () { this.defaulted = true; } };
            strip.fire("contextmenu", throughAChip);
            """);
        assertEquals("split@40,8:pane:anchored", eval("asked.join(' ')").asString(), "the page's kind, at the point, the pane bound and the frame the anchor; a chip's event is not the ground's");
        assertTrue(eval("onGround.defaulted && !throughAChip.defaulted").asBoolean(), "the browser's menu is suppressed only where the steward took it");
        assertTrue(eval("withGround.menuByGround({ x: 1, y: 2 })").asBoolean(), "by call, as a key would");
        eval("register.get('a').close(); withGround.dispose()");
        // a pane told no kind: the ground is the browser's
        eval("""
            var plain = new MultiTabPane(branch.createBranch("mtp_s4"), { host: el("div"), slotId: "s4", menus: steward });
            var e = { target: plain.el.children[0], clientX: 3, clientY: 4, defaulted: false, preventDefault: function () { this.defaulted = true; } };
            plain.el.children[0].fire("contextmenu", e);
            """);
        assertFalse(eval("e.defaulted").asBoolean(), "no kind named: nothing is asked and nothing is suppressed");
        assertFalse(eval("plain.menuByGround({ x: 1, y: 2 })").asBoolean());
        assertEquals("0", eval("String((plain.el.children[0].listeners.contextmenu || []).length)").asString(), "and no listener on the strip at all");
        eval("plain.dispose()");
    }

    /**
     * Given a steward, a chip asks it for the tab menu — on a right-click at the
     * point, on the ContextMenu key or Shift+F10 at the chip — bound to the pane,
     * the tab and the chip; the tab is selected first; the browser's menu is
     * suppressed only when the steward took the request. Without a steward a
     * right-click is nothing.
     */
    @Test
    void givenAStewardAChipAsksForTheTabMenu_boundToPaneTabAndChip() {
        eval("""
            var asked = [];
            var steward = { open: function (kind, object, at, opts) { asked.push(kind + ":" + object.tab.id + "@" + at.x + "," + at.y + (opts.keyboard ? ":kb" : "") + (opts.anchor === object.anchor && object.pane === withMenus ? ":bound" : "")); return object.tab.id !== "refused"; } };
            var withMenus = new MultiTabPane(branch.createBranch("mtp_s2"), { host: el("div"), slotId: "s2", menus: steward, onEvent: function (ev) { if (ev.kind === "TabActivated") log.push("active:" + ev.tabId); } });
            withMenus.take(open("a")); withMenus.take(open("refused")); log = [];
            var chipA = withMenus.el.children[0].children[0].children[0], chipR = withMenus.el.children[0].children[0].children[1];
            chipA.getBoundingClientRect = function () { return { left: 100, top: 10, right: 180, bottom: 40 }; };
            var ev1 = { clientX: 120, clientY: 30, defaulted: false, preventDefault: function () { this.defaulted = true; }, stopPropagation: function () {} };
            chipA.fire("contextmenu", ev1);
            var took2 = withMenus.keyDown({ key: "F10", shiftKey: true });   // while the pane holds the keys: the active tab's menu at its chip; no chip listens itself
            var ev3 = { clientX: 1, clientY: 2, defaulted: false, preventDefault: function () { this.defaulted = true; }, stopPropagation: function () {} };
            chipR.fire("contextmenu", ev3);
            """);
        assertEquals("tab:a@120,30:bound tab:a@112,38:kb:bound tab:refused@1,2:bound", eval("asked.join(' ')").asString(), "the kind, the tab, the point; the keyboard at the chip; bound to the pane, the tab and the chip");
        assertEquals("active:refused", log(), "the tab under the menu is selected first; a was active already");
        eval("register.get('a').close(); register.get('refused').close(); withMenus.dispose()");
        assertTrue(eval("ev1.defaulted && took2 && !ev3.defaulted").asBoolean(), "the browser's menu is suppressed only when the steward took it; the key taken likewise");
        assertEquals("0", eval("String((chipA.listeners.keydown || []).length)").asString(), "no keydown listener on a chip");
        assertEquals("tab", eval("MultiTabPane.MENU").asString());
        eval("var e = { clientX: 0, clientY: 0, defaulted: false, preventDefault: function () { this.defaulted = true; } }; put('x'); pane.el.children[0].children[0].children[0].fire('contextmenu', e);");
        assertFalse(eval("e.defaulted").asBoolean(), "no steward: a right-click on this pane's chip is nothing");
    }

    /**
     * The pane is a member holding the dock's branch: a press on the frame claims
     * it; while it holds, the keys are the container's own — arrows and Home/End
     * change the active tab at once, Shift+arrows reorder by one with the tab
     * staying active, Shift+Down asks to detach, Enter has the widget activate
     * itself, Escape is taken and kept - the dock is the floor; everything
     * else is left. Neither chip nor pane
     * listens for keys itself.
     */
    @Test
    void whileThePaneHoldsTheKeys_theyAreTheContainersOwn() {
        eval("put('a'); put('b'); put('c'); log = []; kbEvents = []");
        assertEquals("mtp_s1", eval("pane.focus.name").asString(), "the dock's branch, named after the pane's");
        assertEquals("a,b,c", eval("pane.focus.members.map(function (m) { return m.name; }).join()").asString(), "the widgets are its members");
        eval("pane.el.fire('pointerdown', { target: pane.el })");
        assertEquals("mtp_s1", eval("holder()").asString(), "a press on the frame: the pane holds");
        assertEquals("held", eval("String(pane.el.getAttribute('data-keys'))").asString(), "and says so, once, on the frame: the design answers it on the pane's own word");
        assertTrue(eval("pane.keyDown({ key: 'ArrowRight' })").asBoolean());
        assertEquals("b", eval("pane.activeTab()").asString());
        eval("pane.keyDown({ key: 'ArrowRight' }); pane.keyDown({ key: 'ArrowRight' })");
        assertEquals("c", eval("pane.activeTab()").asString(), "the end: no wrap");
        eval("pane.keyDown({ key: 'Home' })");
        assertEquals("a", eval("pane.activeTab()").asString());
        eval("pane.keyDown({ key: 'End' }); pane.keyDown({ key: 'ArrowLeft' })");
        assertEquals("b", eval("pane.activeTab()").asString());
        assertEquals("active:s1:b active:s1:c active:s1:a active:s1:c active:s1:b", log());
        eval("log = []; pane.keyDown({ key: 'ArrowLeft', shiftKey: true })");
        assertEquals("B,A,C", chips(), "Shift+Left: the active tab one slot left");
        assertEquals("moved:s1:b@1->s1@0", log());
        assertEquals("b", eval("pane.activeTab()").asString(), "still active");
        eval("log = []; pane.keyDown({ key: 'ArrowLeft', shiftKey: true })");
        assertEquals("", log(), "at the edge: nothing");
        eval("pane.keyDown({ key: 'ArrowDown', shiftKey: true })");
        assertEquals("detach?s1:b", log(), "Shift+Down asks; a holder with a desk does it");
        assertEquals("mtp_s1", eval("holder()").asString(), "the pane holds throughout");
        assertTrue(eval("pane.keyDown({ key: 'Enter' })").asBoolean());
        assertEquals("detach?s1:b b:activate", log(), "Enter: the widget activates itself");
        assertEquals("b", eval("holder()").asString(), "and holds - a claim of its own, not the pane's");
        assertEquals("lent", eval("String(pane.el.getAttribute('data-keys'))").asString(), "the pane does not hold them, and the steward told it they are within it: lent");
        eval("log = []; KeyboardStewardInstance._forward('KeyDown', { key: 'ArrowUp', target: null, preventDefault: function () {}, stopPropagation: function () {} })");
        assertEquals("b:key:ArrowUp", log(), "the keys are the widget's now, through the steward");
        eval("log = []; KeyboardStewardInstance.yield(pane.widgetOf('b').focus)");
        assertEquals("mtp_s1", eval("holder()").asString(), "the widget's yield: the pane catches");
        assertEquals("held", eval("String(pane.el.getAttribute('data-keys'))").asString());
        assertTrue(eval("pane.keyDown({ key: 'Escape' })").asBoolean(), "Escape is taken");
        assertEquals("mtp_s1", eval("holder()").asString(), "and kept: the dock is the floor, so one key cannot walk you out of the room by accident");
        assertTrue(eval("pane.keyDown({ key: 'Escape' })").asBoolean() && eval("holder()").asString().equals("mtp_s1"), "again, and again");
        eval("KeyboardStewardInstance.yield(pane.focus.owner)");
        assertEquals("none", eval("holder()").asString(), "a yield by call still gives them up: the way out is the holder's to call, not a key to press");
        assertFalse(eval("pane.keyDown({ key: 'x' })").asBoolean(), "anything else is left");
        assertEquals("0,0", eval("[(pane.el.children[0].children[0].children[0].listeners.keydown || []).length, (pane.el.listeners.keydown || []).length].join()").asString(), "no keydown listener on a chip or the pane");
        assertEquals("1", eval("String((pane.el.children[0].listeners.mousedown || []).length)").asString(), "the strip stops the press's default");
    }

    /**
     * The walk over the focus tree: the pane is offered the keys like any other
     * member and says so on its frame; of its widgets only the tab on show is
     * offered — the others are behind it, and the pane's own arrows are the way
     * to them. Enter takes the offer up.
     */
    @Test
    void theWalkIsOfferedThePane_andOfItsWidgetsOnlyTheTabOnShow() {
        eval("""
            put('a'); put('b'); put('c'); log = [];
            function cand() { var c = KeyboardStewardInstance.candidate(); return c ? focusParty.find(c).name : 'none'; }
            function tabKey() { KeyboardStewardInstance._forward('KeyDown', { key: 'Tab', target: null, preventDefault: function () {}, stopPropagation: function () {} }); }
            function enter() { KeyboardStewardInstance._forward('KeyDown', { key: 'Enter', target: null, preventDefault: function () {}, stopPropagation: function () {} }); }
            """);
        eval("tabKey()");
        assertEquals("mtp_s1", eval("cand()").asString(), "the pane is a member like any other");
        assertEquals("candidate", eval("String(pane.el.getAttribute('data-keys'))").asString(), "and says the offer on its frame");
        eval("tabKey()");
        assertEquals("a", eval("cand()").asString(), "the tab on show");
        assertEquals("null", eval("String(pane.el.getAttribute('data-keys'))").asString(), "the offer moved on");
        eval("tabKey()");
        assertEquals("mtp_s1", eval("cand()").asString(), "b and c are behind a: the walk steps over them and comes round");
        eval("pane.switchTab('c'); log = []; tabKey()");
        assertEquals("c", eval("cand()").asString(), "what the pane shows is asked afresh");
        eval("enter()");
        assertEquals("c", eval("holder()").asString(), "Enter takes the offer up");
        assertEquals("none", eval("cand()").asString(), "and the walk is over");
    }

    /** The law: a tab-pane's widget is a member of the dock's branch with activate(): take adopts its membership from wherever it was; a closed one's widget is out of the tree. */
    @Test
    void theLaw_aTabPanesWidgetIsAMemberOfTheDocksBranch() {
        eval("var other = focusParty.root.createBranch('other', {}); put('a'); pane.take(open('e', { focus: other }), 0)");
        assertEquals("a,e", eval("pane.focus.members.map(function (m) { return m.name; }).join()").asString(), "taken from another branch: adopted into this dock's");
        assertEquals("0", eval("String(other.members.length)").asString());
        eval("log = []; pane.removeTab('e')");
        assertEquals("a", eval("pane.focus.members.map(function (m) { return m.name; }).join()").asString(), "closed: out of the tree");
        eval("var f = open('f'); f.widget.dispose = null; pane.take(f); pane.removeTab('f')");
        assertEquals("a", eval("pane.focus.members.map(function (m) { return m.name; }).join()").asString(), "a widget that forgot to leave is left by its tab-pane");
        eval("var a = register.get('a'); pane.letGo(a)");
        assertEquals("", eval("pane.focus.members.map(function (m) { return m.name; }).join()").asString(), "let go: its membership back at rest in the desk's branch");
        assertTrue(eval("a.widget.focus.in === elsewhere").asBoolean());
        eval("other.owner.leave(); pane.dispose()");
        assertTrue(eval("pane.focus.owner.in === null").asBoolean(), "disposed: the pane left the tree, its branch dissolved");
    }

    @Test
    void refusesATabAlreadyHereAndAnUnknownId() {
        eval("put('a')");
        assertTrue(assertThrows(PolyglotException.class, () -> eval("pane.take(open('a', null, reg2))")).getMessage().contains("already"),
                "another desk's tab of the same id");
        assertTrue(assertThrows(PolyglotException.class, () -> eval("pane.switchTab('nope')")).getMessage().contains("no tab 'nope'"));
        assertEquals("1", eval("pane.count()").toString());
    }

    @Test
    void theStateIsTheStrip_andDisposeTakesThePaneDownOnceItsTabPanesHaveGone() {
        eval("put('a'); put('b', { pinned: true }); pane.switchTab('a')");
        assertEquals("{\"slotId\":\"s1\",\"activeTabId\":\"a\",\"tabs\":[{\"id\":\"b\",\"title\":\"B\",\"pinned\":true},{\"id\":\"a\",\"title\":\"A\",\"pinned\":false}]}",
                eval("JSON.stringify(pane.getState())").asString());
        eval("register.dispose(); log = []; pane.dispose()");
        assertEquals("", log(), "the tab-panes were the desk's, and went with it");
        assertEquals("0", eval("host.children.length").toString());
        assertEquals("mtp_s1", eval("paneBranch.dissolved[0]").asString(), "the branch it was given is dissolved");
        eval("pane.dispose()");
        assertEquals("", log(), "a second dispose is nothing");
    }

    /**
     * THE HOST (RFC 0066 E3, appendix "tab-panes", the sequence's step 2). A
     * tab-pane is held here and never owned: its own chip and pane placed as
     * they are, nothing minted and nothing dissolved; taken under the law and
     * the budget, in one host at a time; let go as it is, a neighbour shown;
     * its chip, travelling, armed only by the strip it is in, at that strip's
     * size; what its chip asks going to the pane it is in; a close reported
     * where it was, and a move not at all, since moves are the desk's.
     */
    @Nested
    class TheHost {

        @BeforeEach
        void anotherPane() {
            eval("""
                var other = new MultiTabPane(branch.createBranch("mtp_s9"), { host: el("div"), slotId: "s9", budget: 4 });
                function stripOf(p) { return p.el.children[0].children[0].children; }
                """);
        }

        @Test
        void aTabPaneIsTakenAsItIs_nothingMintedHere_itsMembershipAdopted_andShownIfNothingWas() {
            eval("pane.size(0.5); var a = open('a'); var minted = []; var ce = paneBranch.createElement, cb = paneBranch.createBranch;"
               + "paneBranch.createElement = function (n, t) { minted.push(n); return ce.call(this, n, t); };"
               + "paneBranch.createBranch = function (n) { minted.push(n); return cb.call(this, n); }; log.length = 0;");
            assertEquals(0, eval("pane.take(a)").asInt());
            assertEquals("", eval("minted.join(',')").asString(), "nothing minted on the pane's branch");
            assertTrue(eval("stripOf(pane).indexOf(a.chip) >= 0 && a.pane.parentNode === pane.el.children[1]").asBoolean(), "its own chip in the strip, its own pane in the content");
            assertEquals("A", chips());
            assertEquals("w-a+", panels(), "shown, since nothing was");
            assertEquals("active:s1:a", log(), "an arrival is the desk's to report: the pane says only what it shows");
            assertTrue(eval("a.host() === pane && a.widget.focus.in === pane.focus").asBoolean(), "its host set, its widget's membership adopted under the pane's branch");
            assertEquals(0.5, eval("a.chip.size").asDouble(), "the strip's size on the chip");
            eval("var b = open('b'); pane.take(b);");
            assertEquals("w-a+,w-b-", panels(), "a second arrives hidden");
            eval("var c = open('c'); other.take(c); pane.letGo(a); other.take(a);");
            assertTrue(eval("a.pane.has('mtp_tab_content_hidden') && !c.pane.has('mtp_tab_content_hidden')").asBoolean(),
                    "one that was shown where it was, arriving where another is shown, arrives hidden");
        }

        @Test
        void takeHoldsTheLawAndTheBudget_andATabPaneIsInOneHostAtATime() {
            eval("var a = open('a'); pane.take(a);");
            assertFalse(eval("other.canTake(a)").asBoolean(), "held here");
            var ex = assertThrows(PolyglotException.class, () -> eval("other.take(a)"));
            assertTrue(ex.getMessage().contains("held by another host"), ex.getMessage());
            eval("put('x'); var x = open('x', null, reg2);");
            assertFalse(eval("pane.canTake(x)").asBoolean(), "an id already in the pane");
            eval("put('y'); put('z'); var b = open('b');");
            assertFalse(eval("pane.canTake(b)").asBoolean(), "the budget spent");
            ex = assertThrows(PolyglotException.class, () -> eval("pane.take(b)"));
            assertTrue(ex.getMessage().contains("budget"), ex.getMessage());
            assertTrue(eval("other.canTake(b) && b.host() === null").asBoolean());
        }

        @Test
        void letGoTakesItOutAsItIs_nothingDissolved_aNeighbourShown_andAMoveIsNotReported() {
            eval("var a = open('a'), b = open('b'); pane.take(a); pane.take(b); log.length = 0;");
            assertTrue(eval("pane.letGo(a) === a").asBoolean());
            assertEquals("active:s1:b", log(), "a neighbour shown; the move itself is the desk's to report");
            assertEquals("B", chips());
            assertTrue(eval("a.host() === null && a.chip.parentNode === null && a.pane.parentNode === null").asBoolean(), "out, as it is");
            assertTrue(eval("register.has('a') && a.chip.getAttribute('aria-selected') === 'false' && !a.chip.has('mtp_chip_lifted')").asBoolean(), "not dissolved, and its chip at rest");
            assertTrue(eval("a.widget.focus.in === elsewhere").asBoolean(), "its widget's membership back at rest in the desk's branch");
            assertTrue(eval("pane.letGo(a) === null").asBoolean(), "not here: nothing");
        }

        @Test
        void aChipThatTravelsIsArmedOnlyByTheStripItIsIn_atThatStripsSize() {
            eval("pane.size(0.5); var a = open('a'); var own = a.chip.listeners.pointerdown.length; pane.take(a);");
            assertEquals(eval("own + 1").asInt(), eval("a.chip.listeners.pointerdown.length").asInt(), "armed for this strip's rail");
            eval("pane.letGo(a); other.take(a);");
            assertEquals(eval("own + 1").asInt(), eval("a.chip.listeners.pointerdown.length").asInt(), "disarmed by the strip it left, armed by the one it is in");
            assertTrue(eval("a.chip.size == null && stripOf(other).indexOf(a.chip) >= 0").asBoolean(), "at that strip's size, which is none");
            eval("var p = open('p', { pinned: true }); var pinnedOwn = p.chip.listeners.pointerdown.length; other.take(p);");
            assertEquals(eval("pinnedOwn").asInt(), eval("p.chip.listeners.pointerdown.length").asInt(), "a pinned chip is not dragged");
            assertEquals("p,a", eval("other.tabs().join(',')").asString(), "and sits first");
        }

        @Test
        void whatItsChipAsksGoesToThePaneItIsIn() {
            eval("var a = open('a'), b = open('b'); pane.take(a); pane.take(b); log.length = 0; b.chip.fire('pointerdown', { button: 0, target: b.chip });");
            assertEquals("b", eval("pane.activeTab()").asString());
            assertEquals("active:s1:b", log());
            eval("""
                var asked = [];
                var steward = { open: function (kind, object, at, opts) { asked.push(kind + ":" + object.tab.id + (object.tab === c ? ":the-tab-pane" : "") + (object.pane === withMenus ? ":bound" : "")); return true; } };
                var withMenus = new MultiTabPane(branch.createBranch("mtp_s7"), { host: el("div"), slotId: "s7", menus: steward });
                var c = open('c'); withMenus.take(c);
                var ev = { clientX: 3, clientY: 4, defaulted: false, preventDefault: function () { this.defaulted = true; }, stopPropagation: function () {} };
                c.chip.fire("contextmenu", ev);
                """);
            assertEquals("tab:c:the-tab-pane:bound", eval("asked.join(' ')").asString(), "the pane's kind of menu, bound to the pane and the tab-pane itself");
            assertTrue(eval("ev.defaulted").asBoolean());
        }

        @Test
        void aTabPaneClosedWhileHeld_isReportedRemovedWhereItWas() {
            eval("var a = open('a'), b = open('b'); pane.take(a); pane.take(b); log.length = 0; a.close();");
            assertEquals("a:disposed removed:s1:a@0 active:s1:b", log(), "its widget disposed, then reported gone from here, as a closed tab always was");
            assertFalse(eval("register.has('a')").asBoolean());
            eval("log.length = 0; pane.removeTab('b');");
            assertEquals("b:disposed removed:s1:b@0", log(), "removeTab on a tab-pane is its close");
            assertEquals(0, eval("register.count()").asInt());
        }

        @Test
        void disposeRefusesWhileHoldingATabPane() {
            eval("var a = open('a'); pane.take(a);");
            var ex = assertThrows(PolyglotException.class, () -> eval("pane.dispose()"));
            assertTrue(ex.getMessage().contains("still holds tab-panes"), ex.getMessage());
            assertEquals("A", chips(), "nothing moved");
            eval("pane.letGo(a); pane.dispose();");
            assertTrue(eval("register.has('a') && a.host() === null").asBoolean(), "the tab-pane outlives the pane that held it");
        }

        /** A pane that is a window's content: its strip the one bar, with a cross at its end; its ground told from its chips and controls. */
        @Test
        void aPaneThatIsAWindow_hasACrossAtTheEndOfItsBar_andKnowsItsGround() {
            eval("var closed = 0; var win = new MultiTabPane(branch.createBranch('mtp_win'), { host: el('div'), slotId: 'win', onClose: function () { closed++; } });"
               + "var a = open('a'); win.take(a); var bar = win.bar(), tail = bar.children[bar.children.length - 1], cross = tail.children[tail.children.length - 1];");
            assertTrue(eval("bar === win.el.children[0] && cross.has('mtp_bar_close')").asBoolean(), "the strip is the bar; the cross is last in it");
            assertEquals("Close these tabs|-1", eval("cross.getAttribute('aria-label') + '|' + cross.getAttribute('tabindex')").asString());
            eval("cross.fire('click', { stopPropagation: function () {} });");
            assertEquals(1, eval("closed").asInt());
            assertTrue(eval("win.barGround(bar) && win.barGround(bar.children[0]) && win.barGround(tail) && win.barGround(tail.children[0])").asBoolean(), "the bar, the rail, the tail, the count: ground");
            assertFalse(eval("win.barGround(a.chip) || win.barGround(a.chip._label) || win.barGround(cross) || win.barGround(el('elsewhere'))").asBoolean(), "a chip, a part of one, a control, and what is not on the bar: not ground");
            assertEquals(0, eval("stripOf(pane).length === 0 ? 0 : pane.bar().children[pane.bar().children.length - 1].children.filter(function (c) { return c.has('mtp_bar_close'); }).length").asInt(), "a pane told no onClose has no cross");
        }

        @Test
        void aPaneSaysWhenItsLastTabHasLeft_howeverItLeft() {
            eval("var empties = 0; var win = new MultiTabPane(branch.createBranch('mtp_win2'), { host: el('div'), slotId: 'win2', onEmpty: function (p) { if (p === win) empties++; } });"
               + "var a = open('a'), b = open('b'); win.take(a); win.take(b); win.letGo(a);");
            assertEquals(0, eval("empties").asInt(), "one left: not empty");
            eval("b.close();");
            assertEquals(1, eval("empties").asInt(), "the last closed");
            eval("win.take(a); win.letGo(a);");
            assertEquals(2, eval("empties").asInt(), "the last let go");
        }

        /** Widgets that join under their branch's name, as the stack's do, share a host; one whose member name is taken is refused before anything moves. */
        @Test
        void widgetsNamedByTheirBranchShareAHost_aNameAlreadyThereIsRefusedBeforeAnythingMoves() {
            eval("function byBranch(b, t) { b.activate('w'); var w = { root: el('w'), activate: function () {} }; w.focus = t.focus.join(b.name, w); return w; }"
               + "var a = register.open({ id: 'a', make: byBranch }), b = register.open({ id: 'b', make: byBranch }); pane.take(a); pane.take(b);");
            assertEquals("a,b", eval("pane.tabs().join(',')").asString(), "two widgets of one kind, in one host");
            eval("function fixed(b, t) { b.activate('w'); var w = { root: el('w'), activate: function () {} }; w.focus = t.focus.join('same', w); return w; }"
               + "var c = register.open({ id: 'c', make: fixed }); other.take(c); var d = register.open({ id: 'd', make: fixed }); log.length = 0; var before = stripOf(other).length;");
            assertFalse(eval("other.canTake(d)").asBoolean());
            var ex = assertThrows(PolyglotException.class, () -> eval("other.take(d)"));
            assertTrue(ex.getMessage().contains("member name 'same'"), ex.getMessage());
            assertTrue(eval("other.tabs().join(',') === 'c' && stripOf(other).length === before && d.host() === null && d.widget.focus.in === elsewhere").asBoolean(), "nothing moved");
        }

        /** A source on a desk: every tab it makes is a tab-pane opened in the desk's register and put in by the desk's move; the kinds' makers handed the handle. */
        @Test
        void aSourceOnADesk_opensTabPanesThere_putInByTheDesksMove() {
            loadModule("/homing/js/hue/captains/singapura/js/homing/ui/panes/TabSourceModule.js");
            eval("""
                var placed = [];
                function kindMake(b, p) { b.activate("w"); var w = { root: el("w-" + p.id), activate: function () { log.push(p.id + ":activate"); }, dispose: function () { log.push(p.id + ":disposed"); } };
                                          w.focus = p.focus.join(b.name, w); w.tabOf = p.tab; return w; }
                var moving = { register: register, move: function (tp, p, index) { placed.push(tp.id + ">" + p.slotId); return p.take(tp, index); } };
                var src = new TabSource(branch.createBranch("src"), { desk: moving, kinds: [ { id: "note", title: "Notes", make: kindMake }, { id: "books", title: "Books", make: kindMake } ] });
                register.open({ id: "tab-1", make: function (b, t) { b.activate("w"); var w = { root: el("x"), activate: function () {} }; w.focus = t.focus.join(b.name, w); return w; } });
                log.length = 0;
                """);
            eval("var a = src.add(pane, 'note', 'quiet'), b = src.add(pane, 'note', 'focus');");
            assertEquals("tab-2,tab-3", eval("a.tab.id + ',' + b.tab.id").asString(), "the register's names, never one it holds: tab-1 was taken; nothing of the kind in them");
            assertEquals("Notes|Notes 2", eval("a.tab.title() + '|' + b.tab.title()").asString(), "the kind is in the title, counted up");
            assertTrue(eval("a.tab === register.get('tab-2') && a.tab.host() === pane && a.tab.widget.tabOf.id === 'tab-2'").asBoolean(), "a tab-pane in the register, held by the pane; the maker handed its handle");
            assertEquals("tab-2>s1,tab-3>s1", eval("placed.join(',')").asString(), "put in by the desk's move");
            assertEquals("tab-3", eval("pane.activeTab()").asString(), "the second in front, with the keys");
            assertTrue(log().contains("tab-3:activate"), log());
            assertThrows(PolyglotException.class, () -> eval("new TabSource(branch.createBranch('nodesk'), { kinds: [ { id: 'note', make: kindMake } ] })"),
                    "a source opens its tabs on a desk, or makes none");
        }

        @Test
        void aPlaceThatRefuses_leavesNothingBehind() {
            loadModule("/homing/js/hue/captains/singapura/js/homing/ui/panes/TabSourceModule.js");
            eval("""
                function kindMake(b, p) { b.activate("w"); var w = { root: el("w"), activate: function () {}, dispose: function () { log.push("disposed"); } }; w.focus = p.focus.join(b.name, w); return w; }
                var src = new TabSource(branch.createBranch("src"), { desk: { register: register, move: function () { throw new Error("no room there"); } }, kinds: [ { id: "note", make: kindMake } ] });
                log.length = 0;
                """);
            var ex = assertThrows(PolyglotException.class, () -> eval("src.add(pane, 'note')"));
            assertTrue(ex.getMessage().contains("no room there"), ex.getMessage());
            assertEquals(0, eval("register.count()").asInt(), "the tab-pane it opened is closed again");
            assertEquals("disposed", log());
        }

        /** An opener's tab becomes the kind picked IN PLACE: the same tab-pane, chip and pane; a new widget, the kind's name, shown with the keys. */
        @Test
        void becomeTurnsATabPaneIntoTheKindPicked_inPlace() {
            loadModule("/homing/js/hue/captains/singapura/js/homing/ui/panes/TabSourceModule.js");
            eval("""
                function kindMake(b, p) { b.activate("w"); var w = { root: el("w-" + p.title), activate: function () { log.push(p.title + ":activate"); }, dispose: function () { log.push(p.title + ":disposed"); } };
                                          w.focus = p.focus.join(b.name, w); return w; }
                var src = new TabSource(branch.createBranch("src"), { desk: desk, kinds: [ { id: "opener", title: "Open", listed: false, make: kindMake }, { id: "books", title: "Books", make: kindMake } ] });
                put("x");
                var op = src.add(pane, "opener", "quiet").tab, chip = op.chip, cell = op.pane; log.length = 0;
                """);
            assertEquals("tab-1", eval("op.id").asString(), "the opener's tab is a tab like any other: the register named it");
            assertEquals(1, eval("src.become(op.id, 'books')").asInt(), "where it was");
            assertTrue(eval("register.get(op.id) === op && op.chip === chip && op.pane === cell && pane.tabs().join(',') === 'x,' + op.id").asBoolean(), "the same tab-pane, in the same place");
            assertEquals("Books", eval("op.title()").asString());
            assertEquals("Open:disposed Books:activate", log().replaceAll("active:s1:tab-1 ", ""), "the opener gone, the books in front with the keys");
            assertTrue(eval("pane.widgetOf(op.id) === op.widget").asBoolean(), "the pane reads the new widget at once");
        }

        @Test
        void aHeldTabPanesNameIsItsOwn_andItMovesAlongTheRailLikeAnyTab() {
            eval("put('x'); var a = open('a'); pane.take(a); log.length = 0; pane.retitle('a', 'Renamed');");
            assertTrue(eval("a.title() === 'Renamed' && a.chip._label.textContent === 'Renamed'").asBoolean(), "the pane renames the tab-pane, which names itself");
            assertEquals("Renamed", eval("pane.getState().tabs[1].title").asString(), "the state asks the tab-pane its name");
            eval("pane.moveTab('a', 0);");
            assertEquals("moved:s1:a@1->s1@0", log());
            assertEquals("Renamed,X", chips());
        }
    }
}

