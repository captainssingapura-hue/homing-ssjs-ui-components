package hue.captains.singapura.js.homing.ui.panzoom;

import hue.captains.singapura.js.homing.core.EsModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;

import java.util.List;

/**
 * The arithmetic of a view over content: {@code PanZoom} - a scale between fit and the most, an
 * offset that never loses the content, a zoom about a point. Pure: whatever shows the content
 * measures it and applies what it says.
 */
public record PanZoomModule() implements EsModule<PanZoomModule> {

    public static final PanZoomModule INSTANCE = new PanZoomModule();

    public record PanZoom() implements Exportable._Class<PanZoomModule> {}

    @Override public ImportsFor<PanZoomModule> imports() { return ImportsFor.noImports(); }

    @Override
    public ExportsOf<PanZoomModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new PanZoom())); }
}
