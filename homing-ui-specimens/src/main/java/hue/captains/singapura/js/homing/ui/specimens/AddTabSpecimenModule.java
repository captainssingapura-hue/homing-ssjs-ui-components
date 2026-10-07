package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.panes.AddTabModule;

import java.util.List;

/** {@code AddTabSpecimen}: the house's add-tab control in action - where, what, how, and the tab added - over a room of its own. */
public record AddTabSpecimenModule() implements DomModule<AddTabSpecimenModule> {

    public static final AddTabSpecimenModule INSTANCE = new AddTabSpecimenModule();

    public record AddTabSpecimen() implements BranchComponent<AddTabSpecimenModule> {
        @Override public String summary() { return "The house's add-tab control in action: where, off a picture of the panes; what, off the kinds; how it arrives; then the tab added - over two regions of a dock of its own."; }
    }

    @Override
    public ImportsFor<AddTabSpecimenModule> imports() {
        return ImportsFor.<AddTabSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new AddTabModule.AddTab()), AddTabModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenDocksModule.SpecimenDocks()), SpecimenDocksModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_row(), new SpecimenStyles.sp_host()),
                        SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<AddTabSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new AddTabSpecimen())); }
}
