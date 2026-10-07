package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.focus.StewardMonitorModule;

import java.util.List;

/** {@code StewardMonitorSpecimen}: the house's steward monitor in action - where this page's keys are, as one lamp. */
public record StewardMonitorSpecimenModule() implements DomModule<StewardMonitorSpecimenModule> {

    public static final StewardMonitorSpecimenModule INSTANCE = new StewardMonitorSpecimenModule();

    public record StewardMonitorSpecimen() implements BranchComponent<StewardMonitorSpecimenModule> {
        @Override public String summary() { return "The house's steward monitor in action, on its own page: where the keys are as one lamp - held, lent or away - and the first invariant broken, in the danger colour while one is."; }
    }

    @Override
    public ImportsFor<StewardMonitorSpecimenModule> imports() {
        return ImportsFor.<StewardMonitorSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new StewardMonitorModule.StewardMonitor()), StewardMonitorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_row()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<StewardMonitorSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new StewardMonitorSpecimen())); }
}
