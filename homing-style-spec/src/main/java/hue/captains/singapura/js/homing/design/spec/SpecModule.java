package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;

import java.util.List;

/**
 * A jar's implementations in one style (RFC 0067, D53): one for each top-level component it styles, and nothing else -
 * every other look is reached by pointing, from the implementations' parts and the looks' delegations, wherever it lives.
 *
 * @param <Sc> the style's scheme
 * @param <S>  the style
 */
public interface SpecModule<Sc extends ColorScheme, S extends DesignStyle<Sc>> {

    /** The style its implementations are of. */
    S style();

    /** Each top-level component it styles, with its look on its own. */
    List<Implemented<Sc, S, ?>> implementations();
}
