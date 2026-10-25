package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/** What an item does: a press while it is usable is its click; the pointer coming onto one that can be pointed at points at it. */
public record ItemBehaviorModule() implements DomModule<ItemBehaviorModule> {

    /** {@code new ItemBehavior(el, item)}. */
    public record ItemBehavior() implements Exportable._Class<ItemBehaviorModule> {}

    public static final ItemBehaviorModule INSTANCE = new ItemBehaviorModule();

    @Override
    public ImportsFor<ItemBehaviorModule> imports() {
        return ImportsFor.noImports();
    }

    @Override public ExportsOf<ItemBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new ItemBehavior())); }
}
