package hue.captains.singapura.js.homing.ui.focus;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The steward monitor over the real steward, on a fake DOM: the lamp says
 * where the focus is — the steward's marker, held, lent or away, and whose —
 * and the first invariant broken; redrawn on the steward's events and the
 * native focus moving; dispose stops listening. The steward was made before
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
        function fakeBranch(name) {
            return { name: name, createElement: function (n, tag) { var e = el(tag); e.name = n; return e; },
                     dissolve: function () { log.push("dissolved:" + name); }, activate: function () {} };
        }
        var css = { addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); }, removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); },
                    toggleClass: function (e, c, on) { if (on) e.classList.add(c); else e.classList.remove(c); } };
        ["sm_lamp", "sm_lamp_dormant", "sm_lamp_broken"].forEach(function (c) { globalThis[c] = c; });
        function focus(e) { document.activeElement = e; KeyboardStewardInstance._onFocusIn(); }
        var focusListeners = 0;
        var document = { body: el("body"), activeElement: null, addEventListener: function () { focusListeners++; }, removeEventListener: function () { focusListeners--; } };
        document.activeElement = document.body;
        class Widget { constructor() {} keyDown(ev) { return ev.key === "ArrowUp"; } }
        var host = el("div");
        var monitor = new StewardMonitor(fakeBranch("monitor"), { host: host });
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
        loadModule(DIR + "component/keyboard/KeyboardStewardModule.js");
        loadModule(DIR + "ui/focus/StewardMonitorModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }

    @Test
    void theLampSaysWhereTheFocusIs_heldLentOrAway_andWhose() {
        assertEquals("no one holds the keys", eval("monitor.lamp()").asString());
        assertFalse(eval("monitor.root.has('sm_lamp_dormant') || monitor.root.has('sm_lamp_broken')").asBoolean());
        assertEquals("1", eval("String(host.children.length)").asString(), "the lamp alone");
        eval("var w = focusParty.root.join('widget', new Widget()); KeyboardStewardInstance.claim(w)");
        assertEquals("held by “widget”", eval("monitor.lamp()").asString(), "redrawn on the steward's events");
        eval("var sel = el('select'); sel.setAttribute('aria-label', 'which leaf'); focus(sel)");
        assertEquals("away on select “which leaf” — “widget” keeps the mark, nothing is routed", eval("monitor.lamp()").asString());
        assertTrue(eval("monitor.root.has('sm_lamp_dormant')").asBoolean());
        eval("var room = el('div'); KeyboardStewardInstance.enroll(room, w); var inp = el('input'); room.appendChild(inp); focus(inp)");
        assertEquals("lent by “widget” to input", eval("monitor.lamp()").asString(), "no name: the tag alone");
        assertFalse(eval("monitor.root.has('sm_lamp_dormant')").asBoolean());
        assertEquals(2, eval("focusListeners").asInt(), "focusin and focusout on the document");
        eval("w.leave(); focus(document.body)");
    }

    /** The invariants are read before the marker is: a move of the focus nobody was told of shows, once, in the danger colour. */
    @Test
    void aMissedMoveOfTheFocusShowsOnTheLamp() {
        eval("var w = focusParty.root.join('widget', new Widget()); var room = el('div'); KeyboardStewardInstance.enroll(room, w); var inp = el('input'); room.appendChild(inp); focus(inp)");
        eval("document.activeElement = document.body; monitor.refresh()");   // the control went, and no event said so
        assertTrue(eval("monitor.lamp()").asString().contains("✗ the marker is lent, and nothing has the browser's focus"), eval("monitor.lamp()").asString());
        assertTrue(eval("monitor.root.has('sm_lamp_broken') && monitor.broken().length === 1").asBoolean());
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
    void disposeStopsListening() {
        eval("log = []; monitor.dispose()");
        assertEquals("0", eval("String(host.children.length)").asString(), "taken out of the host");
        assertEquals(0, eval("focusListeners").asInt(), "the document let go");
        assertTrue(eval("log.join(' ')").asString().endsWith("dissolved:monitor"));
        eval("focusParty.root.dissolve()");
    }
}
