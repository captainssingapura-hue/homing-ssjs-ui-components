package hue.captains.singapura.js.homing.ui.panzoom;

import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/** What a pan-zoom view does: its viewport's wheel, drag, double press and scroll, told to the view (23 Oct); its keys are the view's own. */
public record SvgPanZoomBehaviorModule() implements DomModule<SvgPanZoomBehaviorModule> {

    /** {@code new SvgPanZoomBehavior(el, view)}; {@code hear(viewport)}. Its keys are no behaviour's: the view hears them itself, and declares them. */
    public record SvgPanZoomBehavior() implements Exportable._Class<SvgPanZoomBehaviorModule> {}

    public static final SvgPanZoomBehaviorModule INSTANCE = new SvgPanZoomBehaviorModule();

    @Override public ImportsFor<SvgPanZoomBehaviorModule> imports() { return ImportsFor.<SvgPanZoomBehaviorModule>builder().build(); }

    @Override public ExportsOf<SvgPanZoomBehaviorModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new SvgPanZoomBehavior())); }
}
