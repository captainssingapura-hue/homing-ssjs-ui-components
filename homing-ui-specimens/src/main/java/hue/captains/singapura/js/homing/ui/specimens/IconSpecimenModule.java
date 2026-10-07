package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.icons.IconModule;

import java.util.List;

/** {@code IconSpecimen}: the house's icon in action - stepped through its vocabulary, cleared at its width, refusing a word it lacks. */
public record IconSpecimenModule() implements DomModule<IconSpecimenModule> {

    public static final IconSpecimenModule INSTANCE = new IconSpecimenModule();

    public record IconSpecimen() implements BranchComponent<IconSpecimenModule> {
        @Override public String summary() { return "The house's icon in action: one mark stepped through the whole vocabulary, cleared to a blank that keeps its width, and refusing a word the vocabulary lacks."; }
    }

    @Override
    public ImportsFor<IconSpecimenModule> imports() {
        return ImportsFor.<IconSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_row(), new SpecimenStyles.sp_text()),
                        SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<IconSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new IconSpecimen())); }
}
