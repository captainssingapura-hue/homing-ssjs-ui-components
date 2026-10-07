package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.panzoom.PanZoomBarModule;
import hue.captains.singapura.js.homing.ui.panzoom.SvgPanZoomModule;

import java.util.List;

/** {@code PanZoomBarSpecimen}: the house's pan-zoom bar in action - driving a drawing's zoom, and following it. */
public record PanZoomBarSpecimenModule() implements DomModule<PanZoomBarSpecimenModule> {

    public static final PanZoomBarSpecimenModule INSTANCE = new PanZoomBarSpecimenModule();

    public record PanZoomBarSpecimen() implements BranchComponent<PanZoomBarSpecimenModule> {
        @Override public String summary() { return "The house's pan-zoom bar in action: zoom out, the zoom read out as a share of fit, zoom in and fit, driving a drawing and following it when the drawing is zoomed by the hand."; }
    }

    @Override
    public ImportsFor<PanZoomBarSpecimenModule> imports() {
        return ImportsFor.<PanZoomBarSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new PanZoomBarModule.PanZoomBar()), PanZoomBarModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SvgPanZoomModule.SvgPanZoom()), SvgPanZoomModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenDrawingsModule.SPECIMEN_DRAWINGS()), SpecimenDrawingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_row(), new SpecimenStyles.sp_host(),
                        new SpecimenStyles.sp_fill()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<PanZoomBarSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new PanZoomBarSpecimen())); }
}
