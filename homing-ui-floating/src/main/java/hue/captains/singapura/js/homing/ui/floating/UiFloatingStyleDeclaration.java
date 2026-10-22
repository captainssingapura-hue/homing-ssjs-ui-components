package hue.captains.singapura.js.homing.ui.floating;

import hue.captains.singapura.js.homing.design.styles.base.*;
import hue.captains.singapura.js.homing.design.styles.StyleDeclaration;
import hue.captains.singapura.js.homing.ui.styles.Base;

import java.util.List;

/**
 * homing-ui-floating's declaration of what it provides for styles (RFC 0067, appendix The Style Jars): Base's
 * implementation of every casting the jar brings into being - its components', the parts it draws inline, the castings
 * its parts add - 1, each a class of its own in hue.captains.singapura.js.homing.design.styles.base. The floating pane and
 * its head are the panes' now, beside the chip they share (23 Oct).
 */
public record UiFloatingStyleDeclaration() implements StyleDeclaration {

    public static final UiFloatingStyleDeclaration INSTANCE = new UiFloatingStyleDeclaration();

    @Override public List<Provided<?, ?>> provided() {
        return List.of(new Provided<>(Base.INSTANCE, List.of(
                new FloatLayer())));
    }
}
