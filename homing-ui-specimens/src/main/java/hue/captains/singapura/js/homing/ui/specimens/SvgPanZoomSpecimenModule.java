package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.panzoom.SvgPanZoomModule;

import java.util.List;

/** {@code SvgPanZoomSpecimen}: the house's pan-zoom view in action - a drawing fitted to its box, zoomed and panned. */
public record SvgPanZoomSpecimenModule() implements DomModule<SvgPanZoomSpecimenModule> {

    public static final SvgPanZoomSpecimenModule INSTANCE = new SvgPanZoomSpecimenModule();

    public record SvgPanZoomSpecimen() implements BranchComponent<SvgPanZoomSpecimenModule>, NeedKeyboard {
        @Override public String summary() { return "The house's pan-zoom view in action: a drawing fitted whole to its box, zoomed about the pointer, panned once zoomed in, fitted again - every change of the zoom said as a share of fit."; }
        @Override public List<KeyBinding> keys() { return SvgPanZoomModule.SvgPanZoom.KEYS; }
    }

    @Override
    public ImportsFor<SvgPanZoomSpecimenModule> imports() {
        return ImportsFor.<SvgPanZoomSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new SvgPanZoomModule.SvgPanZoom()), SvgPanZoomModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenDrawingsModule.SPECIMEN_DRAWINGS()), SpecimenDrawingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_host(), new SpecimenStyles.sp_row(),
                        new SpecimenStyles.sp_fill()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<SvgPanZoomSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new SvgPanZoomSpecimen())); }
}
