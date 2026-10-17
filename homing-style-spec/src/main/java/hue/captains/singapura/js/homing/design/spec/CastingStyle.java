package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * A look of a component in a style (RFC 0067, D53): a stateless value, its style and its component in its type, meaning
 * nothing until a module's implementation points at it. Every look is of a role - {@link CastingStylePerRole}, the
 * component on its own the role root's - and this is the view of it that leaves the role out: what it says of its
 * component's parts.
 *
 * @param <Sc> its style's colour scheme
 * @param <S>  its style
 * @param <C>  the component it draws
 */
public sealed interface CastingStyle<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>>
        extends StatelessFunctionalObject permits CastingStylePerRole {

    /**
     * Each part slot of its component pointed at a look of exactly its own style - the compiler holds it - one spec for
     * every part casting the component declares, and nothing else; none for a component of no parts.
     */
    default PartsSpec<Sc, S, C> parts() { return new PartsSpec<>(List.of()); }
}
