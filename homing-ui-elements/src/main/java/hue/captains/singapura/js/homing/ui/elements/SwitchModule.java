package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/**
 * The switch: a setting on or off, a knob sliding in a groove, the whole a control - a simple component on its own
 * {@code <button>}, its groove and knob the house's parts, its state riding on its button.
 */
public record SwitchModule() implements DomModule<SwitchModule>, ImplFor<HouseControls.Switch> {

    /** {@code new Switch(branch, new FixedSwitch(), place?)}; {@code root}, {@code checked}, {@code enabled}, {@code text}, {@code setOnCheckedChanged}, {@code setOnCommit}. */
    public record Switch() implements BranchComponent<SwitchModule> {
        @Override public String summary() { return "A setting on or off: a knob sliding in a groove, the whole a control."; }
    }

    public static final SwitchModule INSTANCE = new SwitchModule();

    @Override
    public ImportsFor<SwitchModule> imports() {
        return ImportsFor.<SwitchModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new InputBehaviorsModule.TurnBehavior()), InputBehaviorsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ToggleGrooveModule.ToggleGroove()), ToggleGrooveModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ToggleKnobModule.ToggleKnob()), ToggleKnobModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new ElementStyles.el_switch(),
                        new ElementStyles.el_switch_groove(),
                        new ElementStyles.el_switch_knob()
                ), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SwitchModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Switch()));
    }
}
