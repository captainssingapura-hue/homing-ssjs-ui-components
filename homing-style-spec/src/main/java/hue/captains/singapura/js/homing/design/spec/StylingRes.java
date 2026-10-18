package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;

/**
 * What an implementation gives (RFC 0067, D53): CSS of its own - a placeholder, the virtual layer holding no CSS - or a
 * delegation, bounded by style, part and role, and by nothing else: the implementation of the same component in the same
 * role, of the same style or the style it is written over; or, for one of a role, the same component's standalone, of the
 * same style - a role drawn as its component is. The bound is in the types, so a delegation to another role, another
 * component or a style it is not written over does not compile.
 *
 * @param <Sc> the scheme of the style answering
 * @param <S>  the style answering
 * @param <C>  the component drawn
 * @param <R>  the role it is drawn in
 */
public sealed interface StylingRes<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>, R extends RoleNode>
        extends ValueObject permits StylingRes.CssPlaceHolder, StylingRes.Delegation, StylingRes.RootDelegation, StylingRes.ParentDelegation {

    /**
     * CSS of its own, held apart: the implementation says only that it has some. Its content is a resource at the
     * implementation's canonical name, from any jar on the classpath; the colours it draws with are the implementation's,
     * typed ({@link CastingStylePerRole#colours()}). A record of no components: every one is equal, a singleton in value.
     */
    record CssPlaceHolder<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>, R extends RoleNode>()
            implements StylingRes<Sc, S, C, R> {}

    /**
     * Another look of the same component in the same role, in the same style: no CSS of its own.
     *
     * @param to the look delegated to
     */
    record Delegation<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>, R extends RoleNode>(
            CastingStylePerRole<Sc, S, C, R> to) implements StylingRes<Sc, S, C, R> {
        public Delegation {
            Objects.requireNonNull(to, "Delegation.to");
        }
    }

    /**
     * A role's look drawn as its component's on its own, in the same style: no CSS of its own. Only a look of a role
     * delegates so - the component on its own is the role root's, and is never its own target.
     *
     * @param to the same component's look on its own, in the same style
     */
    record RootDelegation<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>, R extends Role<?>>(
            CastingStylePerRole<Sc, S, C, RoleRoot> to) implements StylingRes<Sc, S, C, R> {
        public RootDelegation {
            Objects.requireNonNull(to, "RootDelegation.to");
        }
    }

    /**
     * The look of the same component in the same role in the style this one is written over: no CSS of its own. The style
     * says which it is written over in its type ({@code Derives}), so a look of any other style does not fit.
     *
     * @param to the parent style's look delegated to
     */
    record ParentDelegation<Sc extends ColorScheme, B extends DesignStyle<? super Sc>, S extends Derives<Sc, B>, C extends Component<?>, R extends RoleNode>(
            CastingStylePerRole<?, B, C, R> to) implements StylingRes<Sc, S, C, R> {
        public ParentDelegation {
            Objects.requireNonNull(to, "ParentDelegation.to");
        }
    }
}
