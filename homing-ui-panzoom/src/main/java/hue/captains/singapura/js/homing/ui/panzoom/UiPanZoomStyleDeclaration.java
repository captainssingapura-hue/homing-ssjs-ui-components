package hue.captains.singapura.js.homing.ui.panzoom;

import hue.captains.singapura.js.homing.design.styles.base.*;
import hue.captains.singapura.js.homing.design.styles.StyleDeclaration;
import hue.captains.singapura.js.homing.ui.styles.Base;

import java.util.List;

/**
 * homing-ui-panzoom's declaration of what it provides for styles (RFC 0067, appendix The Style Jars): Base's
 * implementation of every casting the jar brings into being - its components', the parts it draws inline, the castings
 * its parts add - 5, each a class of its own in hue.captains.singapura.js.homing.design.styles.base.
 */
public record UiPanZoomStyleDeclaration() implements StyleDeclaration {

    public static final UiPanZoomStyleDeclaration INSTANCE = new UiPanZoomStyleDeclaration();

    @Override public List<Provided<?, ?>> provided() {
        return List.of(new Provided<>(Base.INSTANCE, List.of(
                new SvgPanZoom(),
                new PanZoomBar(),
                new PlainButtonZoomOut(),
                new PlainButtonZoomIn(),
                new PlainButtonFit())));
    }
}
