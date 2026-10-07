package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.focus.FocusMonitorModule;

import java.util.List;

/** {@code FocusMonitorSpecimen}: the house's focus monitor in action - this page's focus tree, the holder of the keys lit. */
public record FocusMonitorSpecimenModule() implements DomModule<FocusMonitorSpecimenModule> {

    public static final FocusMonitorSpecimenModule INSTANCE = new FocusMonitorSpecimenModule();

    public record FocusMonitorSpecimen() implements BranchComponent<FocusMonitorSpecimenModule> {
        @Override public String summary() { return "The house's focus monitor in action, on its own page: the logical-focus tree as a tree view, the row of the member holding the keys lit, following every press."; }
    }

    @Override
    public ImportsFor<FocusMonitorSpecimenModule> imports() {
        return ImportsFor.<FocusMonitorSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new FocusMonitorModule.FocusMonitor()), FocusMonitorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_row(), new SpecimenStyles.sp_scroll()),
                        SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<FocusMonitorSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new FocusMonitorSpecimen())); }
}
