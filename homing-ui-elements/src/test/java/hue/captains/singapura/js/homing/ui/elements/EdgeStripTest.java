package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The edge strip: a lip at its host's foot and a strip over it, hidden until
 * the hand comes to the lip. The hand may cross onto the strip and step off it
 * for a moment; it goes a little after the hand has left both. A control
 * focused from the keyboard keeps it shown, a press does not, and a holder
 * keeps it shown whatever the hand does. It listens for no keys.
 */
class EdgeStripTest extends JsModuleTestBase {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/";

    private static final String SHIM = """
        var log = [];
        function el(tag) {
            var classes = new Set(), attrs = {};
            var node = { tag: tag, tagName: tag.toUpperCase(), nodeType: 1, children: [], parentNode: null, listeners: {}, visible: false,
                classList: { add: function () { for (var i = 0; i < arguments.length; i++) classes.add(arguments[i]); }, remove: function () { for (var i = 0; i < arguments.length; i++) classes.delete(arguments[i]); }, contains: function (c) { return classes.has(c); } },
                appendChild: function (c) { this.children.push(c); c.parentNode = this; return c; },
                contains: function (o) { for (var x = o; x; x = x.parentNode) if (x === this) return true; return false; },
                setAttribute: function (k, v) { attrs[k] = String(v); }, getAttribute: function (k) { return attrs[k] == null ? null : attrs[k]; },
                addEventListener: function (t, fn) { (this.listeners[t] = this.listeners[t] || []).push(fn); },
                fire: function (t, ev) { (this.listeners[t] || []).forEach(function (fn) { fn(ev || {}); }); },
                matches: function (sel) { return sel === ":focus-visible" && this.visible; },
                classes: function () { return Array.from(classes).join(" "); },
                listening: function (t) { return (this.listeners[t] || []).length; } };
            return node;
        }
        function fakeBranch(name) { return { name: name, createElement: function (n, tag) { var e = el(tag); e.name = n; return e; }, createBranch: function (n) { return fakeBranch(n); }, dissolve: function () { log.push("dissolved:" + name); }, activate: function () {} }; }
        var css = { addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); }, removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); },
                    toggleClass: function (e, c, on) { if (on) e.classList.add(c); else e.classList.remove(c); } };
        ["el_strip", "el_strip_hidden", "el_strip_lip"].forEach(function (c) { globalThis[c] = c; });
        // the clock, by hand: a timer runs only when the test says time has passed
        var timers = [], seq = 0;
        function setTimeout(fn, ms) { var t = { id: ++seq, fn: fn, ms: ms }; timers.push(t); return t.id; }
        function clearTimeout(id) { timers = timers.filter(function (t) { return t.id !== id; }); }
        function elapse() { var due = timers; timers = []; due.forEach(function (t) { t.fn(); }); }
        var page = fakeBranch("page");
        var host = el("div");
        var button = el("button");
        var strip = new EdgeStripBuilder().label("Workspace").host(host).build(page.createBranch("strip"));
        strip.root.appendChild(button);
        function hidden() { return strip.root.classList.contains("el_strip_hidden"); }
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        loadModule(DIR + "ui/elements/EdgeStripModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }

    /** The lip at the host's foot, the strip after it over that foot, named; hidden until asked for. */
    @Test
    void aLipAtTheFoot_andAStripOverIt_hiddenUntilAskedFor() {
        assertEquals("lip,strip", eval("host.children.map(function (c) { return c.name; }).join()").asString(), "the lip in the host's flow, the strip after it");
        assertTrue(eval("strip.lip.classList.contains('el_strip_lip') && strip.root.classList.contains('el_strip')").asBoolean());
        assertEquals("group", eval("strip.root.getAttribute('role')").asString());
        assertEquals("Workspace", eval("strip.root.getAttribute('aria-label')").asString(), "named for whoever cannot see it");
        assertTrue(eval("hidden() && !strip.isShown()").asBoolean(), "not there until the hand comes to the edge");
    }

    /** The hand on the lip shows it; off both, it goes — but only after the grace, so crossing onto the strip keeps it. */
    @Test
    void theHandOnTheLipShowsIt_andItGoesAfterTheHandHasLeftBoth() {
        eval("strip.lip.fire('pointerenter')");
        assertFalse(eval("hidden()").asBoolean(), "the hand at the edge shows it");
        // the hand crosses from the lip onto the strip: the lip is left, the strip entered, and the strip stays
        eval("strip.lip.fire('pointerleave'); strip.root.fire('pointerenter'); elapse();");
        assertFalse(eval("hidden()").asBoolean(), "crossing onto the strip does not lose it");
        // off the strip, and back before the grace is out: still there
        eval("strip.root.fire('pointerleave'); strip.root.fire('pointerenter'); elapse();");
        assertFalse(eval("hidden()").asBoolean(), "a step off and back keeps it");
        // off for good: there until the grace is out, then gone
        eval("strip.root.fire('pointerleave')");
        assertFalse(eval("hidden()").asBoolean(), "not at once");
        assertTrue(eval("timers.length === 1 && timers[0].ms > 0").asBoolean(), "a grace, not a snap");
        eval("elapse()");
        assertTrue(eval("hidden()").asBoolean(), "gone once the hand has left both");
    }

    /** A control focused from the keyboard keeps it shown until the focus leaves it; a press's focus keeps nothing. */
    @Test
    void theKeysKeepItShown_aPressDoesNot() {
        eval("button.visible = true; strip.root.fire('focusin', { target: button })");
        assertFalse(eval("hidden()").asBoolean(), "a control focused from the keyboard shows it");
        eval("strip.root.fire('focusout', { target: button, relatedTarget: button })");
        assertFalse(eval("hidden()").asBoolean(), "the focus moving inside it keeps it");
        eval("strip.root.fire('focusout', { target: button, relatedTarget: null })");
        assertTrue(eval("hidden()").asBoolean(), "the focus gone from it, it goes");
        eval("button.visible = false; strip.root.fire('focusin', { target: button })");
        assertTrue(eval("hidden()").asBoolean(), "a press focuses a button too, and keeps nothing shown");
    }

    /** Held, it stays whatever the hand does; let go, it goes once nothing else keeps it. */
    @Test
    void heldItStays_letGoItGoes() {
        eval("strip.hold(true)");
        assertTrue(eval("!hidden() && strip.isShown()").asBoolean(), "held: shown with no hand on it");
        eval("strip.lip.fire('pointerenter'); strip.lip.fire('pointerleave'); elapse();");
        assertFalse(eval("hidden()").asBoolean(), "the hand coming and going does not take it away");
        eval("strip.lip.fire('pointerenter'); strip.hold(false)");
        assertFalse(eval("hidden()").asBoolean(), "let go while the hand is on it: still shown");
        eval("strip.lip.fire('pointerleave'); elapse();");
        assertTrue(eval("hidden()").asBoolean(), "and gone once the hand has left");
    }

    /** It listens for no keys, and its branch goes with it, the grace with it. */
    @Test
    void itTakesNoKeys_andGoesWithItsBranch() {
        assertEquals(0, eval("strip.root.listening('keydown') + strip.lip.listening('keydown')").asInt(), "no keydown listener of its own, ever");
        eval("strip.lip.fire('pointerenter'); strip.lip.fire('pointerleave'); strip.dispose();");
        assertTrue(eval("timers.length === 0").asBoolean(), "the grace cancelled");
        assertEquals("dissolved:strip", eval("log.join()").asString());
    }
}
