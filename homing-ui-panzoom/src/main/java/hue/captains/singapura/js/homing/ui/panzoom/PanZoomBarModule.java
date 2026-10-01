package hue.captains.singapura.js.homing.ui.panzoom;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;

import java.util.List;

/** The controls of a pan-zoom view: out, the zoom said, in, fit - the elements' buttons, following the view they drive. */
public record PanZoomBarModule() implements DomModule<PanZoomBarModule> {

    public static final PanZoomBarModule INSTANCE = new PanZoomBarModule();

    /** A branch component: {@code new PanZoomBar(branch, view)}; root, dispose. */
    public record PanZoomBar() implements BranchComponent<PanZoomBarModule> {
        @Override public String summary() { return "The controls of a pan-zoom view: zoom out, the zoom as a share of fit, zoom in, back to fit."; }
    }

    @Override
    public ImportsFor<PanZoomBarModule> imports() {
        return ImportsFor.<PanZoomBarModule>builder()
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new PanZoomStyles.pz_bar(), new PanZoomStyles.pz_readout()), PanZoomStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<PanZoomBarModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new PanZoomBar())); }
}
