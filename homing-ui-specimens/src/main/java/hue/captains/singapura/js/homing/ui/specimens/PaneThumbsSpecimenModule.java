package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.panes.PaneThumbsModule;

import java.util.List;

/** {@code PaneThumbsSpecimen}: the house's pane thumbnails in action - a picture of where a dock's regions are, one picked by pointing. */
public record PaneThumbsSpecimenModule() implements DomModule<PaneThumbsSpecimenModule> {

    public static final PaneThumbsSpecimenModule INSTANCE = new PaneThumbsSpecimenModule();

    public record PaneThumbsSpecimen() implements BranchComponent<PaneThumbsSpecimenModule> {
        @Override public String summary() { return "The house's pane thumbnails in action: a picture of where a dock's regions are and how many tabs each holds, one picked by pointing at its picture, one shown and not choosable, a split taken in at once."; }
    }

    @Override
    public ImportsFor<PaneThumbsSpecimenModule> imports() {
        return ImportsFor.<PaneThumbsSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new PaneThumbsModule.PaneThumbs()), PaneThumbsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenDocksModule.SpecimenDocks()), SpecimenDocksModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_row(), new SpecimenStyles.sp_host()),
                        SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<PaneThumbsSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new PaneThumbsSpecimen())); }
}
