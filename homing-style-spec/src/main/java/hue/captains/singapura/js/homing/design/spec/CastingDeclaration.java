package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

/**
 * A declaration (RFC 0067, D53): a composite in a role in a style, its structure alone - the implementation it itself
 * wears, and a spec for each of its slots, complete. It gives no CSS: implementations do. Its style, component and role
 * are in its type - the role root's for the component standalone - and every spec it holds reaches exactly its style, by
 * the compiler. A composite's part that is itself a composite is reached through its own declaration, so nesting ends at
 * the leaves.
 *
 * <pre>{@code
 * public record DialogDeclaration() implements CastingDeclaration<BaseScheme, Editorial, HouseContainers.Dialog, RoleRoot> {
 *     @Override public CastingImplementation<BaseScheme, Editorial, HouseContainers.Dialog, RoleRoot> own() { return new Dialog(); }
 *     @Override public PartsSpec<BaseScheme, Editorial, HouseContainers.Dialog> parts() {
 *         return new PartsSpec<>(List.of(
 *                 new PartSpec.Standalone<>(HouseContainers.Dialog.INSTANCE, HouseContainers.ActionBar.INSTANCE, HouseShaping.Actions.INSTANCE)));
 *     }
 * }
 * }</pre>
 *
 * @param <Sc> its style's colour scheme
 * @param <S>  its style
 * @param <C>  the composite it declares
 * @param <R>  the role it declares it in: {@code RoleRoot} standalone
 */
public interface CastingDeclaration<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Composite<?>, R extends RoleNode>
        extends StatelessFunctionalObject {

    /** The implementation the composite itself wears, in this role, of exactly this style. */
    CastingImplementation<Sc, S, C, R> own();

    /** A spec for every slot of the composite: one each, and nothing else. */
    PartsSpec<Sc, S, C> parts();
}
