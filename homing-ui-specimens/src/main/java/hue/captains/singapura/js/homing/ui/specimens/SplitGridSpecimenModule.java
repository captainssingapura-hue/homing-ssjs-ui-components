package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.splitgrid.SplitGridModule;

import java.util.List;

/** {@code SplitGridSpecimen}: the house's split grid in action - rooms re-shared by their lines, split beside themselves, removed. */
public record SplitGridSpecimenModule() implements DomModule<SplitGridSpecimenModule> {

    public static final SplitGridSpecimenModule INSTANCE = new SplitGridSpecimenModule();

    public record SplitGridSpecimen() implements BranchComponent<SplitGridSpecimenModule> {
        @Override public String summary() { return "The house's split grid in action: rooms in rows and columns re-shared by dragging the lines between them, a room split beside itself, a room removed - the last cannot go - and the lines drawn or not."; }
    }

    @Override
    public ImportsFor<SplitGridSpecimenModule> imports() {
        return ImportsFor.<SplitGridSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new SplitGridModule.SplitGrid()), SplitGridModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_host(), new SpecimenStyles.sp_text(),
                        new SpecimenStyles.sp_row()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<SplitGridSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new SplitGridSpecimen())); }
}
