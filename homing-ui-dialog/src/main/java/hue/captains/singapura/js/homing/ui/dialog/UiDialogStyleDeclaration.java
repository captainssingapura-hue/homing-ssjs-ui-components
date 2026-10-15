package hue.captains.singapura.js.homing.ui.dialog;

import hue.captains.singapura.js.homing.design.styles.base.*;
import hue.captains.singapura.js.homing.design.styles.StyleDeclaration;
import hue.captains.singapura.js.homing.ui.styles.Base;

import java.util.List;

/**
 * homing-ui-dialog's declaration of what it provides for styles (RFC 0067, appendix The Style Jars): Base's
 * implementation of every casting the jar brings into being - its components', the parts it draws inline, the castings
 * its parts add - 2, each a class of its own in hue.captains.singapura.js.homing.design.styles.base.
 */
public record UiDialogStyleDeclaration() implements StyleDeclaration {

    public static final UiDialogStyleDeclaration INSTANCE = new UiDialogStyleDeclaration();

    @Override public List<Provided<?, ?>> provided() {
        return List.of(new Provided<>(Base.INSTANCE, List.of(
                new Dialog(),
                new ActionBar())));
    }
}
