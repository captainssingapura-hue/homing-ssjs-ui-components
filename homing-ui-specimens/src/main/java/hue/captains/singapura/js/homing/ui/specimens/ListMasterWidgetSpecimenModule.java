package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.preferences.ListMasterWidgetModule;

import java.util.List;

/** {@code ListMasterWidgetSpecimen}: the house's list master in action - a site's settings listed by where they belong, one chosen. */
public record ListMasterWidgetSpecimenModule() implements DomModule<ListMasterWidgetSpecimenModule> {

    public static final ListMasterWidgetSpecimenModule INSTANCE = new ListMasterWidgetSpecimenModule();

    public record ListMasterWidgetSpecimen() implements BranchComponent<ListMasterWidgetSpecimenModule>, NeedKeyboard {
        @Override public String summary() { return "The house's list master in action: a site's settings listed by where they belong, depth as indentation, one chosen by a press or moved to by the keys handed on."; }
        @Override public List<KeyBinding> keys() { return ListMasterWidgetModule.ListMasterWidget.KEYS; }
    }

    @Override
    public ImportsFor<ListMasterWidgetSpecimenModule> imports() {
        return ImportsFor.<ListMasterWidgetSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new ListMasterWidgetModule.ListMasterWidget()), ListMasterWidgetModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<ListMasterWidgetSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new ListMasterWidgetSpecimen())); }
}
