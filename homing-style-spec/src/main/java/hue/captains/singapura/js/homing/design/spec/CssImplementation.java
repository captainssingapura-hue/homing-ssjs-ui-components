package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;

import java.util.List;

/**
 * An implementation with CSS of its own (RFC 0067, D53). Its CSS is content, held apart: the resource at its canonical
 * name - {@code …/styles/editorial/PlainButton.css} - which a resolver finds ({@link CssResolver}). What it keeps here,
 * typed, are the colours its CSS draws with.
 *
 * <pre>{@code
 * public record PlainButton() implements CssImplementation<BaseScheme, Editorial, HouseControls.PlainButton, RoleRoot> {
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
public non-sealed interface CssImplementation<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>, R extends RoleNode>
        extends CastingImplementation<Sc, S, C, R> {

    /** The colours its CSS draws with, each once - colours of the tree its scheme sees, by the compiler, and its own spectra (D41). */
    default List<ColorLeaf<? super Sc, ?, ?>> colours() { return List.of(); }
}
