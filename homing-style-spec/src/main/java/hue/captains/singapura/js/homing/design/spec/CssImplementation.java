package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * An implementation (RFC 0067, D53, D54): how a component is drawn in a layer, with CSS of its own. Its CSS is content,
 * held apart: the resource at its canonical name - {@code …/styles/editorial/PlainButton.css} - which a resolver finds
 * ({@link CssResolver}). What it keeps here, typed, are the colours its CSS draws with.
 *
 * <p>It is a target of its component, and of no other: any casting of the component - on its own, or in a role it is
 * cast in - may be assigned it ({@link ComponentAssignment}); no casting of another component can be. Its layer and its
 * component are in its type; no role is.</p>
 *
 * <pre>{@code
 * public record PlainButton() implements CssImplementation<BaseScheme, Editorial, HouseControls.PlainButton> {
 *     @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
 *         return List.of(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Ring());
 *     }
 * }
 * }</pre>
 *
 * @param <Sc> its layer's colour scheme
 * @param <S>  its layer: the base, or a style
 * @param <C>  the component it draws
 */
public interface CssImplementation<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>>
        extends StatelessFunctionalObject {
    /** The colours its CSS draws with, each once - colours of the tree its scheme sees, by the compiler, and its own spectra (D41). */
    default List<ColorLeaf<? super Sc, ?, ?>> colours() { return List.of(); }
}
