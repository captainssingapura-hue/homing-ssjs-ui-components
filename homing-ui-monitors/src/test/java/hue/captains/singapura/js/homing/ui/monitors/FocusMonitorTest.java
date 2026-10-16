package hue.captains.singapura.js.homing.ui.monitors;

import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookManifestModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;

/**
 * The monitor over the real party and the real steward, on a fake DOM: one
 * row per node, indented by depth; the holder's row lit and named; a change
 * of structure or of holder redraws; a holder outside the tree is named
 * below it. A widget, shown in a slot as its holders show it: disposed by the
 * slot, it stops listening, and the slot takes it out and dissolves its branch.
 */
class FocusMonitorTest extends UiMonitorsJsTest {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/";

    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), attrs = {}, props = {};
            var node = { tag: tag, tagName: tag.toUpperCase(), children: [], parentNode: null, textContent: "",
                get firstChild() { return this.children[0] || null; },
                classList: { add: function () { for (var i = 0; i < arguments.length; i++) classes.add(arguments[i]); }, remove: function () { for (var i = 0; i < arguments.length; i++) classes.delete(arguments[i]); }, contains: function (c) { return classes.has(c); } },
                appendChild: function (c) { this.children.push(c); c.parentNode = this; return c; },
                removeChild: function (c) { var i = this.children.indexOf(c); if (i >= 0) this.children.splice(i, 1); c.parentNode = null; return c; },
                insertBefore: function (c, ref) { var i = this.children.indexOf(ref); this.children.splice(i < 0 ? this.children.length : i, 0, c); c.parentNode = this; return c; },
                setAttribute: function (k, v) { attrs[k] = String(v); }, getAttribute: function (k) { return attrs[k] == null ? null : attrs[k]; },
                removeAttribute: function (k) { delete attrs[k]; },
                addEventListener: function () {}, removeEventListener: function () {},
                style: { setProperty: function (k, v) { props[k] = v; }, getPropertyValue: function (k) { return props[k] || ""; } },
                has: function (c) { return classes.has(c); }, prop: function (k) { return props[k]; },
                text: function () { return this.children.map(function (c) { return c.textContent; }).filter(Boolean).join(" "); } };
            return node;
        }
        var makers = new Set();
        var DomOpsParty = { isElementMaker: function (f) { return makers.has(f); } };
        function fakeBranch(name) {
            var kids = {};
            return { name: name, createElement: function (n, tag) { var e = el(tag); e.name = n; return e; },
                     elementMaker: function (n) { var self = this, mk = function (tag) { makers.delete(mk); return self.createElement(n, tag); }; makers.add(mk); return mk; },
                     createBranch: function (n) { var b = fakeBranch(n); kids[n] = b; return b; }, dissolveBranch: function (n) { delete kids[n]; log.push("dissolved:" + n); },
                     dissolve: function () { log.push("dissolved:" + name); }, activate: function () {} };
        }
        var css = { addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); }, removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); } };
        ["fm_tree", "fm_row", "fm_row_holder", "fm_kind", "fm_name", "fm_component", "fm_outside"].forEach(function (c) { globalThis[c] = c; });
        var focusListeners = 0;
        var document = { body: el("body"), activeElement: null, addEventListener: function () { focusListeners++; }, removeEventListener: function () { focusListeners--; } };
        document.activeElement = document.body;
        class Dock { constructor() {} }
        class Widget { constructor() {} keyDown(ev) { return true; } }
        var host = el("div");
        var slot = new WidgetSlot({ branch: fakeBranch("slot"), host: host });
        var monitor = slot.show("monitor", FocusMonitor, {});
        function rows() { return monitor.root.children.filter(function (r) { return r.has("fm_row"); }).map(function (r) { return r.prop("--fm-depth") + ":" + r.text() + (r.has("fm_row_holder") ? "*" : ""); }); }
        function outside() { var o = monitor.root.children.filter(function (r) { return r.has("fm_outside"); }); return o.length ? o[0].textContent : null; }
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        js.eval("js", "var console = { error: function (m) { throw new Error(m); } };");
        loadModule(DIR + "component/party/PartyModule.js");
        loadModule(DIR + "component/keyboard/FocusPartyModule.js");
        loadModule(DIR + "component/keyboard/KeyboardSecretaryModule.js");
        loadModule(DIR + "component/keyboard/KeyboardEventsModule.js");
        loadModule(DIR + "component/keyboard/KeyboardWalkModule.js");
        loadModule(DIR + "component/keyboard/KeyboardShortcutsModule.js");
        loadModule(DIR + "component/keyboard/KeyboardChordsModule.js");
        loadModule(DIR + "component/keyboard/KeyboardMarkModule.js");
        loadModule(DIR + "component/keyboard/KeyboardHomeModule.js");
        loadModule(DIR + "component/keyboard/KeyboardStewardModule.js");
        loadModule(DIR + "component/WidgetSlotModule.js");
        // each row the house's list row: its castings, its states, its look and the simple component it is
        js.eval("js", String.join("\n", CastingsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", StatesAndEventsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", LookManifestModule.INSTANCE.selfContent(null)));
        js.eval("js", "var style_sheet = 'style-sheet', palette_sheet = 'palette-sheet', ICONS = {}; var CssClassManagerInstance = { cls: function (n) { return n; }, addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); }, removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); }, hasClass: function (e, c) { return e.classList.contains(c); } };");
        loadModule(DIR + "server/StyledLeaves.js");
        loadModule(DIR + "ui/look/LookModule.js");
        for (String m : new String[] {"SimpleComponentModule.js", "ItemBehaviorModule.js", "ListRowModule.js"})
            loadModule(DIR + "ui/elements/" + m);
        loadModule(DIR + "ui/monitors/FocusMonitorModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String rows() { return eval("rows().join(' | ')").asString(); }

    @Test
    void anEmptyTreeIsTheRootAlone_andNoOneHoldsTheKeys() {
        assertEquals("0:root no one holds the keys", rows());
        assertTrue(eval("outside() === null").asBoolean());
        assertTrue(eval("monitor.holderRow() === null").asBoolean());
    }

    @Test
    void everyNodeIsARowAtItsDepth_andTheHoldersRowIsLit() {
        eval("var dock = focusParty.root.createBranch('dock', new Dock()); var w = dock.join('widget', new Widget()); var c = focusParty.root.join('card', new Widget());");
        assertEquals("0:root no one holds the keys | 1:branch dock Dock | 2:leaf widget Widget | 1:leaf card Widget", rows(), "redrawn on every join");
        eval("KeyboardStewardInstance.claim(w)");
        assertEquals("0:root | 1:branch dock Dock | 2:leaf widget Widget* | 1:leaf card Widget", rows(), "the holder's row lit, the root's caption gone");
        assertEquals("widget", eval("monitor.holderRow().children[1].textContent").asString());
        assertEquals("true", eval("monitor.holderRow().getAttribute('aria-current')").asString(), "the holder's row is the current one");
        eval("KeyboardStewardInstance.claim(c)");
        assertEquals("0:root | 1:branch dock Dock | 2:leaf widget Widget | 1:leaf card Widget*", rows(), "the keys moved: the row moved with them");
        eval("focusParty.root.createBranch('desk', new Dock()).adopt(w)");
        assertEquals("0:root | 1:branch dock Dock | 1:leaf card Widget* | 1:branch desk Dock | 2:leaf widget Widget", rows(), "a move redraws");
        eval("c.leave()");
        assertEquals("0:root no one holds the keys | 1:branch dock Dock | 1:branch desk Dock | 2:leaf widget Widget", rows(), "the holder left: no one holds, and the row is gone");
    }

    /** A walk on: the row it rests on says the offer, the holder's still says held, and the offer withdrawn leaves one mark. */
    @Test
    void theRowAWalkRestsOnSaysTheOffer() {
        eval("var w = focusParty.root.join('widget', new Widget()), c = focusParty.root.join('card', new Widget()); KeyboardStewardInstance.claim(w); KeyboardStewardInstance.offer(c)");
        assertEquals("card", eval("monitor.candidateRow().children[1].textContent").asString());
        assertEquals("candidate", eval("monitor.candidateRow().getAttribute('data-keys')").asString());
        assertEquals("held", eval("monitor.holderRow().getAttribute('data-keys')").asString(), "the keys have not moved");
        eval("KeyboardStewardInstance.withdraw()");
        assertTrue(eval("monitor.candidateRow() === null").asBoolean(), "the offer off: one mark left, the holder's");
        eval("w.leave(); c.leave()");
    }

    /** A grafted party: its proxy a graft row naming the party, its members under it; detached, gone. */
    @Test
    void aGraftedPartyIsARowOfItsOwn_itsMembersUnderIt() {
        eval("var p = focusParties.mobile('widget-1'); var m = p.root.join('grid', new Widget()); focusParty.root.graft('widget', p)");
        assertEquals("0:root no one holds the keys | 1:graft widget party widget-1 | 2:leaf grid Widget", rows(), "redrawn on the graft");
        eval("KeyboardStewardInstance.claim(m)");
        assertEquals("0:root | 1:graft widget party widget-1 | 2:leaf grid Widget*", rows(), "a member of a grafted party holds, and its row is lit");
        eval("focusParty.root.detach('widget')");
        assertEquals("0:root no one holds the keys", rows(), "detached: the party a stray again, and not on the page's tree");
        eval("p.dissolve()");
    }

    @Test
    void aHolderOutsideTheTreeIsNamedBelowIt() {
        eval("KeyboardStewardInstance.join('legacy/slider', {}); KeyboardStewardInstance.claim('legacy/slider')");
        assertEquals("0:root", rows());
        assertEquals("held outside the tree: legacy/slider", eval("outside()").asString());
        eval("KeyboardStewardInstance.leave('legacy/slider')");
        assertTrue(eval("outside() === null").asBoolean());
    }

    @Test
    void disposedByItsSlot_itStopsListening() {
        assertEquals("1", eval("String(host.children.length)").asString(), "shown: the slot put its root in the host");
        eval("log = []; slot.dispose('monitor'); focusParty.root.join('late', new Widget())");
        assertEquals("0", eval("String(host.children.length)").asString(), "the tree taken out of the host, by the slot");
        assertEquals(0, eval("focusListeners").asInt(), "the document let go");
        assertEquals("dissolved:monitor", eval("log.join(' ')").asString(), "its branch dissolved by the slot, once, and no redraw after");
        eval("focusParty.root.dissolve()");
    }
}
