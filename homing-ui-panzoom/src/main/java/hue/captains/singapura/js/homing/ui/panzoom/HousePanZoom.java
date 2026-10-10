package hue.captains.singapura.js.homing.ui.panzoom;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomySource;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/**
 * The house's pan and zoom, as this module adds them to the house's taxonomy: a picture that
 * pans and zooms, and the bar that drives it. In the tree if and only if this module is on the
 * classpath.
 */
public record HousePanZoom() implements TaxonomyExtension {

    public static final HousePanZoom INSTANCE = new HousePanZoom();

    @Override public Class<? extends TaxonomySource> extending() { return HouseTaxonomy.class; }

    /** Every component this module declares. */
    @Override public List<Component<?>> components() {
        return List.of(SvgPanZoom.INSTANCE, PanZoomBar.INSTANCE);
    }

    /** A picture that pans and zooms. The drawing is the caller's. */
    public record SvgPanZoom() implements Component<HouseBranches.View> {
        public static final SvgPanZoom INSTANCE = new SvgPanZoom();
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
    }

    /** A picture's zoom, read out, with buttons to change it. It drives a view it is given. */
    public record PanZoomBar() implements Component<HouseBranches.Bar> {
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
