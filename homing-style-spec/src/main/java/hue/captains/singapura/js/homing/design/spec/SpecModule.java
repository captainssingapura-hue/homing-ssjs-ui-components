package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;

import java.util.List;

/**
 * A jar's standalones in one style (RFC 0067, D53): for each top-level component it styles, and each component of their
 * design a functional role places, its one implementation or declaration standalone; everything else is reached by
 * pointing - a declaration's specs, an implementation's delegations - wherever it lives.
 *
 * @param <Sc> the style's scheme
 * @param <S>  the style
 */
public interface SpecModule<Sc extends ColorScheme, S extends DesignStyle<Sc>> {

    /** The style its standalones are of. */
    S style();

    /** Each component it gives a standalone, with that standalone. */
    List<StandaloneEntry<Sc, S, ?>> standalones();
}
