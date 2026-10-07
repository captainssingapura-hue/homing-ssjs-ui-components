package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.KeysModule;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.docking.DeskModule;
import hue.captains.singapura.js.homing.ui.docking.DockGridModule;
import hue.captains.singapura.js.homing.ui.panes.TabSourceModule;

import java.util.List;

/**
 * {@code SpecimenDocks}: a specimen's room for tabs - a desk of its own, two regions of a dock grid
 * on it, and a source of plain tabs beside any kinds the specimen adds - so the controls that put
 * tabs somewhere have somewhere real to put them.
 */
public record SpecimenDocksModule() implements DomModule<SpecimenDocksModule> {

    public static final SpecimenDocksModule INSTANCE = new SpecimenDocksModule();

    public record SpecimenDocks() implements BranchComponent<SpecimenDocksModule> {
        @Override public String summary() { return "A specimen's room for tabs: a desk of its own, two regions of a dock grid on it, and a source of a note and a memo beside the kinds the specimen adds."; }
    }

    @Override
    public ImportsFor<SpecimenDocksModule> imports() {
        return ImportsFor.<SpecimenDocksModule>builder()
                .add(new ModuleImports<>(List.of(new DeskModule.Desk()), DeskModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new DockGridModule.DockGrid()), DockGridModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TabSourceModule.TabSource()), TabSourceModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new KeysModule.Keys()), KeysModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_text()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<SpecimenDocksModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new SpecimenDocks())); }
}
