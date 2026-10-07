package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.splitgrid.SplitGridMirrorModule;
import hue.captains.singapura.js.homing.ui.splitgrid.SplitGridModule;

import java.util.List;

/** {@code SplitGridMirrorSpecimen}: the house's split grid mirror in action - a grid drawn small, its cursor moved by a press or the arrows. */
public record SplitGridMirrorSpecimenModule() implements DomModule<SplitGridMirrorSpecimenModule> {

    public static final SplitGridMirrorSpecimenModule INSTANCE = new SplitGridMirrorSpecimenModule();

    public record SplitGridMirrorSpecimen() implements BranchComponent<SplitGridMirrorSpecimenModule>, NeedKeyboard {
        @Override public String summary() { return "The house's split grid mirror in action: a grid's shape drawn small beside it, a cursor put on a room by a press or moved by the arrows handed on, a room split in the grid reflected after."; }
        @Override public List<KeyBinding> keys() { return SplitGridMirrorModule.SplitGridMirror.KEYS; }
    }

    @Override
    public ImportsFor<SplitGridMirrorSpecimenModule> imports() {
        return ImportsFor.<SplitGridMirrorSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new SplitGridMirrorModule.SplitGridMirror()), SplitGridMirrorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SplitGridModule.SplitGrid()), SplitGridModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_row(), new SpecimenStyles.sp_host(),
                        new SpecimenStyles.sp_text()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<SplitGridMirrorSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new SplitGridMirrorSpecimen())); }
}
