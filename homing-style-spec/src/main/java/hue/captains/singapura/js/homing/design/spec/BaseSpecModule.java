package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;

/**
 * A component module with its standalones in the base layer (RFC 0067, D53): its components - their definition - and the
 * standalone of every one of them, and of every component of their design a functional role places, which a style leaves
 * to the base.
 *
 * @param <Sc> the base's scheme
 * @param <B>  the base layer
 */
public interface BaseSpecModule<Sc extends ColorScheme, B extends DesignStyle<Sc>> extends SpecModule<Sc, B> {

    /** The components it brings: their definition. */
    ComponentModule components();
}
