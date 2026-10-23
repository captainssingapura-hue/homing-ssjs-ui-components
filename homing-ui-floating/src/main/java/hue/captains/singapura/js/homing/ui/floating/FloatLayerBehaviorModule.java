package hue.captains.singapura.js.homing.ui.floating;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/** What a float layer does: the pointer down or the focus arriving in one of its floaters, told to it first (23 Oct). */
public record FloatLayerBehaviorModule() implements DomModule<FloatLayerBehaviorModule> {

    /** {@code new FloatLayerBehavior(el, layer)}. */
    public record FloatLayerBehavior() implements Exportable._Class<FloatLayerBehaviorModule> {}

    public static final FloatLayerBehaviorModule INSTANCE = new FloatLayerBehaviorModule();

    @Override public ImportsFor<FloatLayerBehaviorModule> imports() { return ImportsFor.<FloatLayerBehaviorModule>builder().build(); }

    @Override public ExportsOf<FloatLayerBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new FloatLayerBehavior())); }
}
