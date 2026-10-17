package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;

/**
 * A component module with its implementations in the base layer (RFC 0067, D53): its components - their definition - and
 * every one of them implemented in the base, which a style leaves to it.
 *
 * @param <Sc> the base's scheme
 * @param <B>  the base layer
 */
public interface BaseSpecModule<Sc extends ColorScheme, B extends DesignStyle<Sc>> extends SpecModule<Sc, B> {

    /** The components it brings: their definition. */
    ComponentModule components();
}
