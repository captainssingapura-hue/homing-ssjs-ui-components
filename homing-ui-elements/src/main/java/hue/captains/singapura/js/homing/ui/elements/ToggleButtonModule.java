package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.ElementComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** The toggle button: the button's declarations on one more axis, on, which a press turns over; worn in its own look. */
public record ToggleButtonModule() implements DomModule<ToggleButtonModule>, ImplFor<HouseControls.ToggleButton> {

    /** An element component: {@code new ToggleButton(el, { label, pressed, size }, role)} on the element its companion makes; {@code setOnPressedChanged(handler)}, {@code setOnClick(handler)}; {@code on}, {@code enabled}, {@code size}, {@code label}. */
    public record ToggleButton() implements ElementComponent<ToggleButtonModule> {
        @Override public String tag() { return "button"; }
        @Override public String summary() { return "A button that is on or off: a press turns it over, and on it stays pressed."; }
    }

    /** Its companion: {@code ToggleButtonFactory.create(branch, name)} makes the element it is realized on. */
    public record ToggleButtonFactory() implements Exportable._Constant<ToggleButtonModule> {}

    public static final ToggleButtonModule INSTANCE = new ToggleButtonModule();

    @Override
    public ImportsFor<ToggleButtonModule> imports() {
        return ImportsFor.<ToggleButtonModule>builder()
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.STATE()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponentFactory()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ButtonModule.Button()), ButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ToggleButtonBehaviorModule.ToggleButtonBehavior()), ToggleButtonBehaviorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ToggleButtonModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ToggleButton(), new ToggleButtonFactory()));
    }
}
