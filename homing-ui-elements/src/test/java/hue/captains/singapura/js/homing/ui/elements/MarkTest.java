package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The marks made components (RFC 0067, appendix Two Classes per Leaf, phase 5): each a simple component on its own div,
 * wearing its casting's class and holding its states as their classes - the lamp lit and broken, the detent shown, the
 * boundary line upright and in the hand; a drop mark and a tick, seen and never said.
 */
class MarkTest extends JsModuleTestBase {

    private static final String DIR = "/homing/js/hue/captains/singapura/js/homing/ui/elements/";

    private static final String SHIM = """
        function el(tag) {
            var classes = new Set(), attrs = {};
            return { tagName: tag.toUpperCase(), textContent: "",
                classList: { add: function (c) { classes.add(c); }, remove: function (c) { classes.delete(c); }, contains: function (c) { return classes.has(c); } },
                has: function (c) { return classes.has(c); },
                setAttribute: function (k, v) { attrs[k] = String(v); }, getAttribute: function (k) { return k in attrs ? attrs[k] : null; },
                removeAttribute: function (k) { delete attrs[k]; }, addEventListener: function () {} };
        }
        var makers = new Set();
        var DomOpsParty = { isElementMaker: function (f) { return makers.has(f); } };
        var branch = { createElement: function (n, tag) { return el(tag); },
                       elementMaker: function (n) { var self = this, mk = function (tag) { makers.delete(mk); return self.createElement(n, tag); }; makers.add(mk); return mk; } };
        var css = { addClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.add(arguments[i]); },
                    removeClass: function (e) { for (var i = 1; i < arguments.length; i++) e.classList.remove(arguments[i]); } };
        var lamp = new Lamp(branch.elementMaker("lamp"), { text: "quiet" });
        var drop = new DropMark(branch.elementMaker("drop"), {});
        var detent = new Detent(branch.elementMaker("detent"), {});
        var tick = new Tick(branch.elementMaker("tick"), {});
        var line = new BoundaryLine(branch.elementMaker("line"), { orientation: "vertical" });
        """;

    @BeforeEach
    void load() {
        js = buildContext();
        TextsInJs.load(js, this::loadModule);   // the castings, the states, the look and the simple component
        for (String m : new String[] {"MarkBehaviorModule.js", "LampModule.js", "DropMarkModule.js", "DetentModule.js", "TickModule.js", "BoundaryLineModule.js"})
            loadModule(DIR + m);
        js.eval("js", TextsInJs.withCss(SHIM));
    }

    private Value eval(String src) { return js.eval("js", src); }
    private boolean is(String src) { return eval(src).asBoolean(); }

    @Test
    void theLamp_quietLitOrBroken_itsStatesItsClasses() {
        assertTrue(is("lamp.el.has('styled-lamp') && lamp.el.textContent === 'quiet' && !lamp.el.has('styled-lamp--current')"));
        eval("lamp.current = true");
        assertTrue(is("lamp.el.has('styled-lamp--current') && lamp.el.getAttribute('aria-current') === 'true'"), "lit");
        eval("lamp.invalid = true");
        assertTrue(is("lamp.el.has('styled-lamp--invalid') && lamp.el.getAttribute('aria-invalid') === 'true'"), "broken, and lit still: its precedence decides");
        eval("lamp.current = false; lamp.invalid = false");
        assertFalse(is("lamp.el.has('styled-lamp--current') || lamp.el.has('styled-lamp--invalid') || lamp.el.getAttribute('aria-current') !== null"));
    }

    @Test
    void theDetent_shownWhileWhatRestsOnItIsInTheHand() {
        assertTrue(is("detent.el.has('styled-detent') && !detent.el.has('styled-detent--dragging')"));
        eval("detent.dragging = true");
        assertTrue(is("detent.el.has('styled-detent--dragging') && detent.el.getAttribute('data-dragging') === ''"));
    }

    @Test
    void theBoundaryLine_aSeparator_uprightOrLying_inTheHandAsItsOwnerSays() {
        assertEquals("separator vertical", eval("line.el.getAttribute('role') + ' ' + line.el.getAttribute('aria-orientation')").asString());
        assertTrue(is("line.el.has('styled-boundary-line') && line.el.has('styled-boundary-line--vertical')"), "upright: its Orientation's class");
        eval("line.orientation = 'horizontal'");
        assertTrue(is("!line.el.has('styled-boundary-line--vertical') && line.el.getAttribute('aria-orientation') === 'horizontal'"), "lying, the rest: no class");
        eval("line.dragging = true");
        assertTrue(is("line.el.has('styled-boundary-line--dragging')"));
    }

    @Test
    void theDropMarkAndTheTick_seenNeverSaid() {
        assertTrue(is("drop.el.has('styled-drop-mark') && drop.el.getAttribute('aria-hidden') === 'true'"));
        assertTrue(is("tick.el.has('styled-tick') && tick.el.getAttribute('aria-hidden') === 'true'"));
    }
}
