package hue.captains.singapura.js.homing.ui.monitors;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.Value;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookManifestModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The steward monitor over the real steward, on a fake DOM: the lamp says
 * where the focus is — the steward's marker, held, lent or away, and whose —
 * and the first invariant broken; redrawn on the steward's events and the
 * native focus moving. A widget, shown in a slot as its holders show it:
 * disposed by the slot, it stops listening. The steward was made before
 * this document, so the tests tell it the focus moved as its listener would.
 */
class StewardMonitorTest extends JsModuleTestBase {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/";

    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), attrs = {};
            var node = { tag: tag, tagName: tag.toUpperCase(), children: [], parentNode: null, textContent: "",
                classList: { add: function () { for (var i = 0; i < arguments.length; i++) classes.add(arguments[i]); }, remove: function () { for (var i = 0; i < arguments.length; i++) classes.delete(arguments[i]); }, contains: function (c) { return classes.has(c); } },
                appendChild: function (c) { this.children.push(c); c.parentNode = this; return c; },
                removeChild: function (c) { var i = this.children.indexOf(c); if (i >= 0) this.children.splice(i, 1); c.parentNode = null; return c; },
                setAttribute: function (k, v) { attrs[k] = String(v); }, getAttribute: function (k) { return attrs[k] == null ? null : attrs[k]; },
                removeAttribute: function (k) { delete attrs[k]; },
                addEventListener: function () {}, removeEventListener: function () {},
                has: function (c) { return classes.has(c); } };
            return node;
        }
        var makers = new Set();
        var DomOpsParty = { isElementMaker: function (f) { return makers.has(f); } };
        function fakeBranch(name) {
            return { name: name, createElement: function (n, tag) { var e = el(tag); e.name = n; return e; },
                     elementMaker: function (n) { var self = this, mk = function (tag) { makers.delete(mk); return self.createElement(n, tag); }; makers.add(mk); return mk; },
                     createBranch: function (n) { return fakeBranch(n); }, dissolveBranch: function (n) { log.push("dissolved:" + n); },
                     dissolve: function () { log.push("dissolved:" + name); }, activate: function () {} };
        }
        var css = { cls: function (n) { return n; }, addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); }, removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); },
                    toggleClass: function (e, c, on) { if (on) e.classList.add(c); else e.classList.remove(c); }, hasClass: function (e, c) { return e.classList.contains(c); },
                    size: function () {} };
        var CssClassManagerInstance = css;
        ["sm_lamp"].forEach(function (c) { globalThis[c] = c; });
        function focus(e) { document.activeElement = e; KeyboardStewardInstance._onFocusIn(); }
        var focusListeners = 0;
        var document = { body: el("body"), activeElement: null, addEventListener: function () { focusListeners++; }, removeEventListener: function () { focusListeners--; } };
        document.activeElement = document.body;
        class Widget { constructor() {} keyDown(ev) { return ev.key === "ArrowUp"; } }
        var host = el("div");
        var slot = new WidgetSlot({ branch: fakeBranch("slot"), host: host });
        var monitor = slot.show("monitor", StewardMonitor, {});
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
        // its body the house's lamp: its castings, its states, its look and the simple component it is
        js.eval("js", String.join("\n", CastingsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", StatesAndEventsModule.INSTANCE.selfContent(null)));
        js.eval("js", String.join("\n", LookManifestModule.INSTANCE.selfContent(null)));
        js.eval("js", "var style_sheet = 'style-sheet', palette_sheet = 'palette-sheet', ICONS = {};");
        loadModule(DIR + "server/StyledLeaves.js");
        loadModule(DIR + "ui/look/LookModule.js");
        for (String m : new String[] {"SimpleComponentModule.js", "MarkBehaviorModule.js", "LampModule.js"})
            loadModule(DIR + "ui/elements/" + m);
        loadModule(DIR + "ui/monitors/StewardMonitorModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }

    @Test
    void theLampSaysWhereTheFocusIs_heldLentOrAway_andWhose() {
        assertEquals("no one holds the keys", eval("monitor.lamp()").asString());
        assertTrue(eval("monitor.root.has('styled-lamp') && !monitor.root.has('styled-lamp--current') && monitor.root.getAttribute('aria-current') === null && monitor.root.getAttribute('aria-invalid') === null").asBoolean(), "a lamp, quiet: nothing holds");
        assertEquals("1", eval("String(host.children.length)").asString(), "the lamp alone");
        eval("var w = focusParty.root.join('widget', new Widget()); KeyboardStewardInstance.claim(w)");
        assertEquals("held by “widget”", eval("monitor.lamp()").asString(), "redrawn on the steward's events");
        assertEquals("true", eval("monitor.root.getAttribute('aria-current')").asString(), "lit: the keys held");
        assertTrue(eval("monitor.root.has('styled-lamp--current')").asBoolean(), "lit: its Current state's class held");
        eval("var sel = el('select'); sel.setAttribute('aria-label', 'which leaf'); focus(sel)");
        assertEquals("away on select “which leaf” — “widget” keeps the mark, nothing is routed", eval("monitor.lamp()").asString());
        assertTrue(eval("monitor.root.getAttribute('aria-current') === null").asBoolean(), "quiet: away");
        eval("var room = el('div'); KeyboardStewardInstance.enroll(room, w); var inp = el('input'); room.appendChild(inp); focus(inp)");
        assertEquals("lent by “widget” to input", eval("monitor.lamp()").asString(), "no name: the tag alone");
        assertEquals("true", eval("monitor.root.getAttribute('aria-current')").asString(), "lit: the keys lent");
        assertEquals(2, eval("focusListeners").asInt(), "focusin and focusout on the document");
        eval("w.leave(); focus(document.body)");
    }

    /** The invariants are read before the marker is: a move of the focus nobody was told of shows, once, in the danger colour. */
    @Test
    void aMissedMoveOfTheFocusShowsOnTheLamp() {
        eval("var w = focusParty.root.join('widget', new Widget()); var room = el('div'); KeyboardStewardInstance.enroll(room, w); var inp = el('input'); room.appendChild(inp); focus(inp)");
        eval("document.activeElement = document.body; monitor.refresh()");   // the control went, and no event said so
        assertTrue(eval("monitor.lamp()").asString().contains("✗ the marker is lent, and nothing has the browser's focus"), eval("monitor.lamp()").asString());
        assertTrue(eval("monitor.root.getAttribute('aria-invalid') === 'true' && monitor.root.has('styled-lamp--invalid') && monitor.broken().length === 1").asBoolean(), "broken: invalid");
        eval("monitor.refresh()");
        assertEquals("held by “widget”", eval("monitor.lamp()").asString(), "read again, and the steward has it right");
        assertFalse(eval("monitor.root.has('sm_lamp_broken')").asBoolean());
        eval("w.leave()");
    }

    /** A walk on: the lamp names the member the keys are offered to. */
    @Test
    void theLampNamesTheMemberAWalkOffersTheKeysTo() {
        eval("var a = focusParty.root.join('alpha', new Widget()), b = focusParty.root.join('beta', new Widget())");
        eval("KeyboardStewardInstance.offer(b)");
        assertEquals("no one holds the keys · offered to “beta”", eval("monitor.lamp()").asString());
        eval("KeyboardStewardInstance.withdraw()");
        assertEquals("no one holds the keys", eval("monitor.lamp()").asString());
        eval("a.leave(); b.leave()");
    }

    @Test
    void disposedByItsSlot_itStopsListening() {
        eval("log = []; slot.dispose('monitor')");
        assertEquals("0", eval("String(host.children.length)").asString(), "taken out of the host, by the slot");
        assertEquals(0, eval("focusListeners").asInt(), "the document let go");
        assertEquals("dissolved:monitor", eval("log.join(' ')").asString(), "its branch dissolved by the slot, once");
        eval("focusParty.root.dissolve()");
    }
}
