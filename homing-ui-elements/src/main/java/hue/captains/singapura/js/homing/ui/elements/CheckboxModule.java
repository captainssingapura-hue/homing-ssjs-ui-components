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

/** The checkbox: a simple component on a {@code <input>}, riding on the browser's own; worn in its own look. One thing on or off, by a mark in a box: the browser's own. */
public record CheckboxModule() implements DomModule<CheckboxModule>, ImplFor<HouseControls.Checkbox> {

    /** An element component: {@code new Checkbox(make, new FixedCheckbox(), place?)}; {@code onChange(StateChecked, listener)}; {@code checked}, {@code enabled}. */
    public record Checkbox() implements ElementComponent<CheckboxModule> {
        @Override public String tag() { return "input"; }
        @Override public String summary() { return "One thing on or off, by a mark in a box: the browser's own."; }
    }


    public static final CheckboxModule INSTANCE = new CheckboxModule();

    @Override
    public ImportsFor<CheckboxModule> imports() {
        return ImportsFor.<CheckboxModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new InputBehaviorsModule.CheckBehavior()), InputBehaviorsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ElementStyles.el_checkbox()), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<CheckboxModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Checkbox()));
    }
}
