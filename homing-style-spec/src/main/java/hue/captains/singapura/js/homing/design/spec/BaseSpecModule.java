package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;

import java.util.List;

/**
 * A component module with its assignments in the base layer (RFC 0067, D53, D54): its components - their definition - and
 * the base's assignment of every component it brings, its own and the parts that come in with them: every casting of
 * each, on its own and in every role it is cast in. A component is cast only in the roles its base assignment gives.
 *
 * @param <Sc> the base's scheme
 * @param <B>  the base layer
 */
public interface BaseSpecModule<Sc extends ColorScheme, B extends DesignStyle<Sc>> extends SpecModule<Sc, B> {
    /** The components it brings: their definition. */
    ComponentModule components();
    /** It assigns what its own component module brings, and nothing else. */
    @Override default List<? extends ComponentModule> modules() { return List.of(components()); }
}
