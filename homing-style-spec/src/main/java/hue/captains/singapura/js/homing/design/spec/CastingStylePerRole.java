package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;

/**
 * A look of a component in a role in a style (RFC 0067, D53): the role is in its type, as the style and the component
 * are - the role root's for the component on its own. A role means the same everywhere, so a look of one role never
 * stands for another role's: it gives CSS of its own, or delegates to the look of the same component in the same role,
 * of its style or the style it is written over - or, drawn as its component is, to the component's look on its own in
 * its style ({@link StylingRes}).
 *
 * <pre>{@code
 * public record PlainButtonAction() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.PlainButton, HouseDoing.Action> {
 *     @Override public StylingRes<BaseScheme, Editorial, HouseControls.PlainButton, HouseDoing.Action> style() {
 *         return new StylingRes.CssContent<>(new BaseColours.Border(), new BaseColours.Ring());
 *     }
 * }
 * }</pre>
 *
 * @param <Sc> its style's colour scheme
 * @param <S>  its style
 * @param <C>  the component it draws
 * @param <R>  the role it draws it in: {@code RoleRoot} on its own
 */
public non-sealed interface CastingStylePerRole<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>, R extends RoleNode>
        extends CastingStyle<Sc, S, C> {

    /** Its CSS, or a delegation: the same component in the same role, of its style or the one below; or the component on its own, of its style. */
    StylingRes<Sc, S, C, R> style();
}
