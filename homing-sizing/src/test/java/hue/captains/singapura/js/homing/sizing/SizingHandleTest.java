package hue.captains.singapura.js.homing.sizing;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The handle over a fake element: the hand moves the boundary from where it took it, held while it
 * has it, reported once on release and not at all when cancelled; the keys step it, leap with
 * Shift, go as far as it goes with Home and End, and only along its axis; disabled, it takes
 * nothing; it says where it stands. Its layout - here the test - keeps the rail.
 */
class SizingHandleTest extends JsModuleTestBase {

    private static final String SHIM = """
        var log = [];
        function el() {
            var classes = new Set(), attrs = {};
            return { listeners: {},
                classList: { add: function () { for (var i = 0; i < arguments.length; i++) classes.add(arguments[i]); } },
                setAttribute: function (k, v) { attrs[k] = String(v); }, getAttribute: function (k) { return k in attrs ? attrs[k] : null; },
                removeAttribute: function (k) { delete attrs[k]; }, hasAttribute: function (k) { return k in attrs; },
                addEventListener: function (t, fn) { (this.listeners[t] = this.listeners[t] || []).push(fn); },
                removeEventListener: function (t, fn) { var l = this.listeners[t] || []; var i = l.indexOf(fn); if (i >= 0) l.splice(i, 1); },
                setPointerCapture: function () {}, releasePointerCapture: function () {},
                fire: function (t, ev) { ev.type = t; (this.listeners[t] || []).slice().forEach(function (fn) { fn(ev); }); },
                has: function (c) { return classes.has(c); } };
        }
        var css = { addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); } };
        var sz_handle = "sz_handle", sz_handle_h = "sz_handle_h", sz_handle_v = "sz_handle_v";
        var line = el(), rail = Rail.of([1, 3]), room = 1000;
        var handle = new SizingHandle(line, { axis: "horizontal", index: 0,
            rail: function () { return rail; },
            measure: function () { return { room: room, least: [100, 100] }; },
            onMove: function (r) { rail = r; log.push("move:" + Rail.text(r)); },
            onDone: function (r) { log.push("done:" + Rail.text(r)); } });
        handle.reflect();
        function drag(from, to, end) {
            line.fire("pointerdown", { button: 0, clientX: from, clientY: 0, pointerId: 1 });
            var held = line.hasAttribute("data-dragging");
            line.fire("pointermove", { clientX: to, clientY: 0 });
            line.fire(end || "pointerup", {});
            return held;
        }
        function key(k, shift) { return handle.key({ key: k, shiftKey: !!shift }); }
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        loadModule("/homing/js/hue/captains/singapura/js/homing/sizing/RailModule.js");
        loadModule("/homing/js/hue/captains/singapura/js/homing/sizing/SizingHandleModule.js");
        js.eval("js", SHIM);
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String log() { return eval("var s = log.join(' '); log.length = 0; s").asString(); }

    @Test
    void armedOnTheLine_itBringsBehaviourAndSaysWhereItStands() {
        assertTrue(eval("line.has('sz_handle') && line.has('sz_handle_h') && !line.has('sz_handle_v')").asBoolean());
        assertEquals("25", eval("line.getAttribute('aria-valuenow')").asString());
        assertEquals("10", eval("line.getAttribute('aria-valuemin')").asString(), "the box before at its least: 100 of 1000");
        assertEquals("90", eval("line.getAttribute('aria-valuemax')").asString());
    }

    @Test
    void theHand_movesItFromWhereItTookIt_heldTillReleased_reportedOnce() {
        assertTrue(eval("drag(250, 500)").asBoolean(), "held while the hand has it");
        assertFalse(eval("line.hasAttribute('data-dragging')").asBoolean(), "and not after");
        assertEquals("move:500000 500000 done:500000 500000", log());
        assertEquals("50", eval("line.getAttribute('aria-valuenow')").asString());
        eval("drag(500, 2000)");
        assertEquals("move:900000 100000 done:900000 100000", log(), "stopped at the least of the box after it");
        eval("drag(900, 900)");
        assertEquals("", log(), "a press that moves nothing says nothing");
        eval("drag(900, 400, 'pointercancel')");
        assertEquals("move:400000 600000 move:900000 100000", log(), "cancelled: put back, and nothing reported");
        eval("line.fire('pointerdown', { button: 2, clientX: 0, clientY: 0, pointerId: 1 })");
        assertFalse(eval("line.hasAttribute('data-dragging')").asBoolean(), "only the main button takes it");
    }

    @Test
    void theKeys_stepLeapAndGoAsFarAsItGoes_alongItsAxisOnly() {
        assertTrue(eval("key('ArrowRight')").asBoolean());
        assertEquals("move:266000 734000 done:266000 734000", log(), "a step of 16 of 1000");
        eval("key('ArrowLeft', true)");
        assertEquals("move:202000 798000 done:202000 798000", log(), "a leap of 64");
        eval("key('Home')");
        assertEquals("move:100000 900000 done:100000 900000", log());
        eval("key('Home')");
        assertEquals("", log(), "nowhere further to go");
        assertTrue(eval("key('End')").asBoolean());
        assertEquals("move:900000 100000 done:900000 100000", log());
        assertFalse(eval("key('ArrowUp')").asBoolean(), "not along its axis: not taken");
        assertFalse(eval("key('a')").asBoolean());
    }

    @Test
    void disabled_itTakesNothing() {
        eval("handle.enabled(false)");
        assertEquals("true", eval("line.getAttribute('aria-disabled')").asString());
        eval("drag(250, 500)");
        assertFalse(eval("key('ArrowRight')").asBoolean());
        assertEquals("", log());
        eval("handle.enabled(true)");
        assertTrue(eval("line.getAttribute('aria-disabled') === null && key('ArrowRight')").asBoolean());
    }

    @Test
    void unlaidOut_itMovesNothing_andStandsWhereItIs() {
        eval("room = 0; handle.reflect();");
        assertEquals("0", eval("line.getAttribute('aria-valuemin')").asString());
        assertEquals("100", eval("line.getAttribute('aria-valuemax')").asString());
        eval("drag(250, 500)");
        assertEquals("", log());
    }
}
