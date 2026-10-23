package hue.captains.singapura.js.homing.ui.menu;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.core.Exportable;

import java.util.List;

/** What a level of a context menu does with the pointer: no browser's menu over it; the pointer over an off row, and gone from it, told to it (23 Oct). */
public record MenuBehaviorModule() implements DomModule<MenuBehaviorModule> {

    /** {@code new MenuBehavior(el, menu)}. */
    public record MenuBehavior() implements Exportable._Class<MenuBehaviorModule> {}

    public static final MenuBehaviorModule INSTANCE = new MenuBehaviorModule();

    @Override public ImportsFor<MenuBehaviorModule> imports() { return ImportsFor.<MenuBehaviorModule>builder().build(); }

    @Override public ExportsOf<MenuBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new MenuBehavior())); }
}
