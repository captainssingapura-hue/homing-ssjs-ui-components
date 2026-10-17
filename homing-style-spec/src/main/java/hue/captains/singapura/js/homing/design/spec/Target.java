package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.Leaf;
import hue.captains.singapura.js.homing.component.taxonomy.RoleNode;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;

/**
 * What a spec pointer reaches (RFC 0067, D53), by what the component is: a leaf, its implementation; a composite, its
 * declaration. The bound is the compiler's - a leaf's declaration, or a composite's bare implementation, does not fit.
 *
 * @param <Sc> the style's scheme
 * @param <S>  the style
 * @param <P>  the component reached
 * @param <R>  the role it is reached in
 */
public sealed interface Target<Sc extends ColorScheme, S extends DesignStyle<Sc>, P extends Component<?>, R extends RoleNode>
        extends ValueObject permits Target.Impl, Target.Declared {

    /**
     * A leaf: its implementation.
     *
     * @param impl the leaf's implementation, in the role
     */
    record Impl<Sc extends ColorScheme, S extends DesignStyle<Sc>, P extends Leaf<?>, R extends RoleNode>(
            CastingStylePerRole<Sc, S, P, R> impl) implements Target<Sc, S, P, R> {
        public Impl {
            Objects.requireNonNull(impl, "Impl.impl");
        }
    }

    /**
     * A composite: its declaration.
     *
     * @param declaration the composite's declaration, in the role
     */
    record Declared<Sc extends ColorScheme, S extends DesignStyle<Sc>, P extends Composite<?>, R extends RoleNode>(
            CastingDeclaration<Sc, S, P, R> declaration) implements Target<Sc, S, P, R> {
        public Declared {
            Objects.requireNonNull(declaration, "Declared.declaration");
        }
    }
}
