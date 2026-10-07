package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.split.SplitPaneModule;

import java.util.List;

/** {@code SplitPaneSpecimen}: the house's split pane in action - regions split and split again, re-shared by a dragged divider or by call. */
public record SplitPaneSpecimenModule() implements DomModule<SplitPaneSpecimenModule> {

    public static final SplitPaneSpecimenModule INSTANCE = new SplitPaneSpecimenModule();

    public record SplitPaneSpecimen() implements BranchComponent<SplitPaneSpecimenModule> {
        @Override public String summary() { return "The house's split pane in action: a list beside a detail over its notes, the room shared by ratio, re-shared by a dragged divider - each region kept at its least - or by call."; }
    }

    @Override
    public ImportsFor<SplitPaneSpecimenModule> imports() {
        return ImportsFor.<SplitPaneSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new SplitPaneModule.SplitPane()), SplitPaneModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_host(), new SpecimenStyles.sp_text(),
                        new SpecimenStyles.sp_row()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<SplitPaneSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new SplitPaneSpecimen())); }
}
