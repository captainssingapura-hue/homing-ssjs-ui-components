package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.EdgeStripModule;
import hue.captains.singapura.js.homing.ui.elements.Elements;

import java.util.List;

/** {@code EdgeStripSpecimen}: the house's edge strip in action - shown when the hand reaches a box's foot, held, let go. */
public record EdgeStripSpecimenModule() implements DomModule<EdgeStripSpecimenModule> {

    public static final EdgeStripSpecimenModule INSTANCE = new EdgeStripSpecimenModule();

    public record EdgeStripSpecimen() implements BranchComponent<EdgeStripSpecimenModule> {
        @Override public String summary() { return "The house's edge strip in action: a box whose tools wait at its foot, shown when the hand reaches the lip and gone after it leaves, held shown and let go."; }
    }

    @Override
    public ImportsFor<EdgeStripSpecimenModule> imports() {
        return ImportsFor.<EdgeStripSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new EdgeStripModule.EdgeStripBuilder()), EdgeStripModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_host(), new SpecimenStyles.sp_text(),
                        new SpecimenStyles.sp_row()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<EdgeStripSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new EdgeStripSpecimen())); }
}
