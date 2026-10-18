package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;

/**
 * An implementation with no CSS of its own (RFC 0067, D53): it is drawn as another implementation is, bounded by style,
 * part and role and by nothing else - the same component in the same role, of the same style
 * ({@link SameRoleDelegation}) or the style it is written over ({@link ParentDelegation}); or, for one of a role, the
 * same component's standalone, of the same style ({@link RootDelegation}). The bound is in the types, so a delegation to
 * another component, a role other than its own or the standalone, or a style it is not written over does not compile.
 *
 * @param <Sc> its style's colour scheme
 * @param <S>  its style
 * @param <C>  the component it draws
 * @param <R>  the role it draws it in
 */
public sealed interface Delegation<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>, R extends RoleNode>
        extends CastingImplementation<Sc, S, C, R> permits SameRoleDelegation, RootDelegation, ParentDelegation {

    /** The implementation it is drawn as: of the same component, each kind narrowing the rest. */
    CastingImplementation<?, ?, C, ?> to();
}
