package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.design.styles.base.*;
import hue.captains.singapura.js.homing.design.styles.StyleDeclaration;
import hue.captains.singapura.js.homing.ui.styles.Base;

import java.util.List;

/**
 * homing-preferences's declaration of what it provides for styles (RFC 0067, appendix The Style Jars): Base's
 * implementation of every casting the jar brings into being - its components', the parts it draws inline, the castings
 * its parts add - 16, each a class of its own in hue.captains.singapura.js.homing.design.styles.base.
 */
public record UiPreferencesStyleDeclaration() implements StyleDeclaration {

    public static final UiPreferencesStyleDeclaration INSTANCE = new UiPreferencesStyleDeclaration();

    @Override public List<Provided<?, ?>> provided() {
        return List.of(new Provided<>(Base.INSTANCE, List.of(
                new SettingRow(),
                new CaptionValue())));
    }
}
