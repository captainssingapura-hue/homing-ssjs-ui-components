package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;

/**
 * A role drawn as its component is on its own, in the same style (RFC 0067, D53). Only an implementation of a role
 * delegates so - the component on its own is the role root's, and is never its own target.
 *
 * <pre>{@code
 * public record PlainButtonAction() implements RootDelegation<BaseScheme, Editorial, HouseControls.PlainButton, HouseDoing.Action> {
 *     @Override public CastingImplementation<BaseScheme, Editorial, HouseControls.PlainButton, RoleRoot> to() { return new PlainButton(); }
 * }
 * }</pre>
 *
 * @param <Sc> its style's colour scheme
 * @param <S>  its style
 * @param <C>  the component it draws
 * @param <R>  the role it draws it in: a role, never the root
 */
public non-sealed interface RootDelegation<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>, R extends Role<?>>
        extends Delegation<Sc, S, C, R> {

    @Override CastingImplementation<Sc, S, C, RoleRoot> to();
}
