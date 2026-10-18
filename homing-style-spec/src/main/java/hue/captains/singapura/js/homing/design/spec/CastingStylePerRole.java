package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * An implementation (RFC 0067, D53): how a component in a role is drawn in a style - CSS of its own, held apart as a
 * placeholder, or a delegation to another implementation ({@link StylingRes}) - and, for CSS of its own, the colours it
 * draws with, typed by its scheme. Nothing else: it holds no parts - those are a declaration's ({@link CastingDeclaration})
 * - and no CSS: that is content, a resource at its canonical name. Its style, component and role are in its type - the
 * role root's for the component standalone.
 *
 * <pre>{@code
 * public record PlainButton() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.PlainButton, RoleRoot> {
 *     @Override public StylingRes<BaseScheme, Editorial, HouseControls.PlainButton, RoleRoot> style() {
 *         return new StylingRes.CssPlaceHolder<>();
 *     }
 *     @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
 *         return List.of(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Ring());
 *     }
 * }
 * }</pre>
 *
 * @param <Sc> its style's colour scheme
 * @param <S>  its style
 * @param <C>  the component it draws
 * @param <R>  the role it draws it in: {@code RoleRoot} standalone
 */
public interface CastingStylePerRole<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>, R extends RoleNode>
        extends StatelessFunctionalObject {

    /** Its CSS, held apart, or a delegation: the same component in the same role, of its style or the one below; or standalone, of its style. */
    StylingRes<Sc, S, C, R> style();

    /**
     * The colours its CSS draws with, each once - colours of the tree its scheme sees, by the compiler, and its own
     * spectra (D41): the palette's part of the implementation. None for one that delegates: it has no CSS of its own.
     */
    default List<ColorLeaf<? super Sc, ?, ?>> colours() { return List.of(); }
}
