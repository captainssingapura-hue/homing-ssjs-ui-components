package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.panes.TabOpenerModule;

import java.util.List;

/** {@code TabOpenerSpecimen}: the house's tab opener in action - a chooser whose tab becomes what is picked. */
public record TabOpenerSpecimenModule() implements DomModule<TabOpenerSpecimenModule> {

    public static final TabOpenerSpecimenModule INSTANCE = new TabOpenerSpecimenModule();

    public record TabOpenerSpecimen() implements BranchComponent<TabOpenerSpecimenModule> {
        @Override public String summary() { return "The house's tab opener in action: what a new tab holds until you say what it should hold, the tab turned into what you pick - the same chip, in the same place."; }
    }

    @Override
    public ImportsFor<TabOpenerSpecimenModule> imports() {
        return ImportsFor.<TabOpenerSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new TabOpenerModule.TabOpener()), TabOpenerModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenDocksModule.SpecimenDocks()), SpecimenDocksModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_host(), new SpecimenStyles.sp_row()),
                        SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<TabOpenerSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new TabOpenerSpecimen())); }
}
