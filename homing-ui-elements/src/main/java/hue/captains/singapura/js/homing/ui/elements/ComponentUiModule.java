package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.server.CssClassManager;

import java.util.List;

/**
 * The standard UI of a component: the only thing that touches how its element looks - the classes
 * of its state point, the reflections of its state, its degrees - made for one casting and keeping
 * it. What {@code css.for(component, role)} returns once the manager has it; written by hand
 * until it is polished, generated from the declarations after.
 */
public record ComponentUiModule() implements DomModule<ComponentUiModule> {

    /** {@code new ComponentUi(el, Leaf, role)}; {@code show(state)}, {@code degree(name, n)}, {@code text(words)}. */
    public record ComponentUi() implements Exportable._Class<ComponentUiModule> {}

    public static final ComponentUiModule INSTANCE = new ComponentUiModule();

    @Override
    public ImportsFor<ComponentUiModule> imports() {
        return ImportsFor.<ComponentUiModule>builder()
                .add(new ModuleImports<>(List.of(new CssClassManager.CssClassManagerInstance()), CssClassManager.INSTANCE))
                .build();
    }

    @Override public ExportsOf<ComponentUiModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new ComponentUi())); }
}
