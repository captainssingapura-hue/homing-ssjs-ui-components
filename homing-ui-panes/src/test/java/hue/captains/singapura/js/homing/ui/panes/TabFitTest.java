package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The bar's arithmetic, headless: a row that fits is left at the design's
 * width; one that does not is squeezed, all alike, to a width at which a
 * whole number of tabs fills the room exactly; below the floor it becomes a
 * window over the row, which moves as little as it can and never off an end.
 */
class TabFitTest extends JsModuleTestBase {

    private static final String P = "/homing/js/hue/captains/singapura/js/homing/ui/panes/";

    @BeforeEach
    void load() {
        js = buildContext();
        loadModule(P + "TabFitModule.js");
    }

    private Value eval(String src) { return js.eval("js", src); }
    private String row(String args) { return eval("var r = TabFit.row(" + args + "); String(r.width) + '/' + r.per").asString(); }

    @Test
    void aRowThatFits_keepsTheDesignsWidth_andShowsEveryTab() {
        assertEquals("null/4", row("4, 800, 200, 80"), "four of 200 in 800: exactly");
        assertEquals("null/3", row("3, 800, 200, 80"));
        assertEquals("null/4", row("4, 799.6, 200, 80"), "half a pixel is not a squeeze");
    }

    @Test
    void aRowThatDoesNot_isSqueezedAllAlike_whileEveryTabStillClearsTheFloor() {
        assertEquals("160/5", row("5, 800, 200, 80"), "five share the room: 160 each, all shown");
        assertEquals("80/10", row("10, 800, 200, 80"), "at the floor, all ten still fit");
    }

    @Test
    void belowTheFloor_aWholeNumberOfTabsFillsTheRoom_andTheRestAreBehindAWindow() {
        assertEquals("80/10", row("12, 800, 200, 80"), "ten at the floor fill 800 exactly; two are behind the window");
        assertEquals("81.1/9", eval("var r = TabFit.row(12, 730, 200, 80); r.width.toFixed(1) + '/' + r.per").asString(),
                "730 holds nine at the floor: widened to 730/9 so nothing is cut down the middle");
        assertEquals("true", eval("var r = TabFit.row(40, 1234, 180, 70); String(Math.abs(r.width * r.per - 1234) < 1e-9)").asString(),
                "per tabs at width are the room, to the pixel");
    }

    @Test
    void theFloorIsTheDesignsWidthUnlessSaid_andABarNarrowerThanOneTabShowsOne() {
        assertEquals("200/4", row("6, 800, 200, 0"), "no floor given: the natural width is the floor");
        assertEquals("50/1", row("3, 50, 200, 80"), "narrower than the floor: one tab, the bar's width");
    }

    @Test
    void nothingToMeasure_isNoWindow() {
        assertEquals("null/1", row("0, 800, 200, 80"), "no tabs");
        assertEquals("null/3", row("3, 0, 200, 80"), "not measured yet: the strip leaves it alone");
        assertEquals("null/3", row("3, 800, 0, 80"));
    }

    @Test
    void theWindowMovesAsLittleAsItCan_toPutATabInIt() {
        // ten tabs, four shown
        assertEquals("2", eval("String(TabFit.window(2, 4, 4, 10))").asString(), "already inside: it stays");
        assertEquals("1", eval("String(TabFit.window(2, 1, 4, 10))").asString(), "off the near end: to the tab itself");
        assertEquals("4", eval("String(TabFit.window(2, 7, 4, 10))").asString(), "off the far end: the window that ends on it");
        assertEquals("6", eval("String(TabFit.window(9, 9, 4, 10))").asString(), "never past the last full window");
        assertEquals("0", eval("String(TabFit.window(-3, 0, 4, 10))").asString(), "never before the first");
        assertEquals("0", eval("String(TabFit.window(3, 2, 5, 5))").asString(), "every tab shown: no window to move");
    }

    @Test
    void aStepMovesTheWindowByWholeTabs_neverOffEitherEnd() {
        assertEquals("3,0,6,6", eval("[TabFit.step(2, 1, 4, 10), TabFit.step(2, -5, 4, 10), TabFit.step(5, 3, 4, 10), TabFit.step(6, 1, 4, 10)].join(',')").asString());
        assertEquals("0", eval("String(TabFit.step(0, 2, 6, 6))").asString(), "every tab shown: nowhere to step");
    }
}
