package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

/**
 * An implementation (RFC 0067, D53): how a component in a role is drawn in a style - CSS of its own, or a delegation to
 * another implementation ({@link StylingRes}). Nothing else: it holds no parts - those are a declaration's
 * ({@link CastingDeclaration}) - so an implementation is the same value whether its component is a leaf or a composite,
 * a part or on its own. Its style, component and role are in its type - the role root's for the component standalone.
 *
 * <pre>{@code
 * public record PlainButtonAction() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.PlainButton, HouseDoing.Action> {
 *     @Override public StylingRes<BaseScheme, Editorial, HouseControls.PlainButton, HouseDoing.Action> style() {
 *         return new StylingRes.RootDelegation<>(new PlainButton());
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

    /** Its CSS, or a delegation: the same component in the same role, of its style or the one below; or standalone, of its style. */
    StylingRes<Sc, S, C, R> style();
}
