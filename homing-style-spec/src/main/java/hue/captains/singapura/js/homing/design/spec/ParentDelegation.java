package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;

/**
 * Drawn as the same component in the same role is in the style this one is written over (RFC 0067, D53). The style
 * says which it is written over in its type ({@code Derives}), so an implementation of any other style does not fit.
 *
 * @param <Sc> its style's colour scheme
 * @param <B>  the style its style is written over
 * @param <S>  its style
 * @param <C>  the component it draws
 * @param <R>  the role it draws it in
 */
public non-sealed interface ParentDelegation<Sc extends ColorScheme, B extends DesignStyle<? super Sc>, S extends Derives<Sc, B>, C extends Component<?>, R extends RoleNode>
        extends Delegation<Sc, S, C, R> {

    @Override CastingImplementation<?, B, C, R> to();
}
