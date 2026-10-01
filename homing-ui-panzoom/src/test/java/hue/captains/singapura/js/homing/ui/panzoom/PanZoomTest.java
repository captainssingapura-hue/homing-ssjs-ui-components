package hue.captains.singapura.js.homing.ui.panzoom;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The arithmetic of a view over content, headless: content in the flow fits its viewport at a
 * scale of one; a zoom about a point keeps that point still; the content can be panned only
 * until its edge meets the viewport's, and is centred on an axis where it is the smaller; the
 * scale stays between fit and the most; a re-measure keeps the zoom; content smaller than a
 * larger viewport fits by growing.
 */
class PanZoomTest extends JsModuleTestBase {

    @BeforeEach
    void load() {
        loadModule("/homing/js/hue/captains/singapura/js/homing/ui/panzoom/PanZoomModule.js");
    }

    private String eval(String src) { return js.eval("js", src).asString(); }

    /** A view as one line: scale, x, y, zoom, and which of fitted, most, pannable hold. */
    private String view(String expr) {
        return eval("(function (v) { return [v.scale, v.x, v.y, v.zoom].map(function (n) { return Math.round(n * 1000) / 1000; }).join(' ')"
                + " + (v.fitted ? ' fitted' : '') + (v.most ? ' most' : '') + (v.pannable ? ' pannable' : ''); })(" + expr + ")");
    }

    @Test
    void contentInTheFlow_fitsAtOne_centredAndNotPannable() {
        eval("var pz = new PanZoom(); ''");
        assertEquals("1 0 0 1 fitted", view("pz.measure({ w: 400, h: 100 }, { w: 400, h: 100 })"));
    }

    @Test
    void aZoomAboutAPoint_keepsThatPointStill() {
        eval("var pz = new PanZoom(); pz.measure({ w: 400, h: 100 }, { w: 400, h: 100 }); ''");
        assertEquals("2 -100 -50 2 pannable", view("pz.zoomBy(2, { x: 100, y: 50 })"),
                "the point at (100, 50) is the content's (100, 50) before and its (50, 25) at twice the scale: still under the pointer");
        assertEquals("2 -400 -100 2 pannable", view("pz.zoomBy(1, { x: 400, y: 100 }) && pz.panBy(-1000, -1000)"),
                "panned past the corner: held where the content's corner meets the viewport's");
        assertEquals("2 0 0 2 pannable", view("pz.panBy(5000, 5000)"), "and the other way, the other corner");
    }

    @Test
    void theScaleStays_betweenFitAndTheMost() {
        eval("var pz = new PanZoom({ most: 4 }); pz.measure({ w: 200, h: 100 }, { w: 200, h: 100 }); ''");
        assertEquals("1 0 0 1 fitted", view("pz.zoomBy(0.5)"), "never smaller than fit");
        assertEquals("4 -300 -150 4 most pannable", view("pz.zoomBy(100)"), "never past the most - about the centre when no point is said");
        assertEquals("1 0 0 1 fitted", view("pz.fit()"));
        assertEquals("2.5 -150 -75 2.5 pannable", view("pz.zoomTo(2.5)"));
    }

    @Test
    void aReMeasure_keepsTheZoom_andReClampsTheOffset() {
        eval("var pz = new PanZoom(); pz.measure({ w: 400, h: 100 }, { w: 400, h: 100 }); pz.zoomBy(2, { x: 400, y: 100 }); ''");
        assertEquals("2 -200 -50 2 pannable", view("pz.measure({ w: 200, h: 50 }, { w: 200, h: 50 })"),
                "the column narrowed: the same zoom, the offset held inside the new edges");
    }

    @Test
    void contentSmallerThanALargerViewport_growsToFit_centredOnTheSpareAxis() {
        eval("var pz = new PanZoom(); ''");
        assertEquals("2 0 50 1 fitted", view("pz.measure({ w: 800, h: 300 }, { w: 400, h: 100 })"),
                "twice as wide fits at two; the height left over is shared above and below");
        assertTrue(eval("String(pz.zoomBy(2).pannable)").equals("true"));
    }

    @Test
    void unmeasured_isFitAtOne() {
        assertEquals("1 0 0 1 fitted", view("new PanZoom().view()"));
        assertEquals("1 0 0 1 fitted", view("new PanZoom().measure({ w: 0, h: 0 }, { w: 300, h: 100 })"), "a hidden viewport measures nothing");
    }
}
