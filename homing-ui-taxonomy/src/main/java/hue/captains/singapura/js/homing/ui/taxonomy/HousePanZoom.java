package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.Leaf;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;

import java.util.List;

/**
 * The house's pan and zoom: a picture that pans and zooms, and the bar that drives it. Defined here, with the house's
 * other components, whether or not its implementation is on the classpath; implemented - and drawn by Base - in {@code
 * homing-ui-panzoom} (RFC 0067, appendix The Style Jars, D40).
 */
public final class HousePanZoom {

    private HousePanZoom() {}

    /** A picture that pans and zooms. The drawing is the caller's. */
    public record SvgPanZoom() implements Leaf<HouseBranches.View> {
        public static final SvgPanZoom INSTANCE = new SvgPanZoom();
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
    }

    /** A picture's zoom, read out, with buttons to change it. It drives a view it is given. */
    public record PanZoomBar() implements Composite<HouseBranches.Bar> {
        public static final PanZoomBar INSTANCE = new PanZoomBar();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.ZoomOut.INSTANCE).one(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.ZoomIn.INSTANCE).one(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Fit.INSTANCE).one(),
                           DSL.part(HouseText.Readout.INSTANCE).as(HouseSaying.Value.INSTANCE).one());
        }
    }
}
