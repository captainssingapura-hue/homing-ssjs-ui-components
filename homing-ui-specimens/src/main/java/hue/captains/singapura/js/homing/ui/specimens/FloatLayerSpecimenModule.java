package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.Key;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.floating.FloatLayerModule;

import java.util.List;

/** {@code FloatLayerSpecimen}: the house's float layer in action - notes stacked, raised by a press, the front one closed by Escape. */
public record FloatLayerSpecimenModule() implements DomModule<FloatLayerSpecimenModule> {

    public static final FloatLayerSpecimenModule INSTANCE = new FloatLayerSpecimenModule();

    public record FloatLayerSpecimen() implements BranchComponent<FloatLayerSpecimenModule>, NeedKeyboard {
        @Override public String summary() { return "The house's float layer in action: notes opened onto it, cascading and stacking, a press raising one behind, Escape - the keys handed on - closing the one in front."; }
        @Override public List<KeyBinding> keys() { return List.of(KeyBinding.of(Key.ESCAPE, "the note in front closed, handed on to the layer")); }
    }

    @Override
    public ImportsFor<FloatLayerSpecimenModule> imports() {
        return ImportsFor.<FloatLayerSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new FloatLayerModule.FloatLayer()), FloatLayerModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_host(), new SpecimenStyles.sp_text(),
                        new SpecimenStyles.sp_row()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<FloatLayerSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new FloatLayerSpecimen())); }
}
