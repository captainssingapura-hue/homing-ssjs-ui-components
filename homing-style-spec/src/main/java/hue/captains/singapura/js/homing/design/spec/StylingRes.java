package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * What a look gives (RFC 0067, D53): its own CSS, or a delegation - bounded by style, part and role, and by nothing
 * else: the look of the same component in the same role, of the same style or the style it is written over. The bound
 * is in the types, so a delegation to another role, another component or a style it is not written over does not compile.
 *
 * @param <Sc> the scheme of the style answering
 * @param <S>  the style answering
 * @param <C>  the component drawn
 * @param <R>  the role it is drawn in
 */
public sealed interface StylingRes<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>, R extends RoleNode>
        extends ValueObject permits StylingRes.CssContent, StylingRes.Delegation, StylingRes.ParentDelegation {

    /**
     * Its own CSS - the resource at its class's canonical name - and every colour of the tree that CSS uses, each one the
     * style's scheme sees, by the compiler.
     *
     * @param colours each once, in the order it lists them; none for a look that uses no colour of the tree
     */
    record CssContent<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>, R extends RoleNode>(
            List<ColorLeaf<? super Sc, ?, ?>> colours) implements StylingRes<Sc, S, C, R> {
        public CssContent {
            colours = List.copyOf(Objects.requireNonNull(colours, "CssContent.colours"));
            var seen = new HashSet<Class<?>>();
            for (ColorLeaf<? super Sc, ?, ?> c : colours)
                if (!seen.add(c.getClass())) throw new IllegalArgumentException("CssContent imports " + c.token() + " twice");
        }

        /** Its colours, listed. */
        @SafeVarargs
        public CssContent(ColorLeaf<? super Sc, ?, ?>... colours) { this(List.of(colours)); }
    }

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
