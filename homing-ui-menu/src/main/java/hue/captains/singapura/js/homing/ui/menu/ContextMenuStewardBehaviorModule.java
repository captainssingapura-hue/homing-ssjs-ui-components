package hue.captains.singapura.js.homing.ui.menu;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/** What the menus' steward does with the page while a menu is open: the press outside, swallowed whole; a scroll, a resize, the window's blur, told to it (23 Oct). */
public record ContextMenuStewardBehaviorModule() implements DomModule<ContextMenuStewardBehaviorModule> {

    /** {@code new ContextMenuStewardBehavior(el, steward)}; {@code listen(on)}. */
    public record ContextMenuStewardBehavior() implements Exportable._Class<ContextMenuStewardBehaviorModule> {}

    public static final ContextMenuStewardBehaviorModule INSTANCE = new ContextMenuStewardBehaviorModule();

    @Override public ImportsFor<ContextMenuStewardBehaviorModule> imports() { return ImportsFor.<ContextMenuStewardBehaviorModule>builder().build(); }

    @Override public ExportsOf<ContextMenuStewardBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new ContextMenuStewardBehavior())); }
}
