package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;

/**
 * One slot of a declaration, pointed (RFC 0067, D53), for the model's completeness and soundness: the owner, the part,
 * the role the owner declares it in. Two kinds, and the slot's role decides which - never the owner:
 *
 * <ul>
 *   <li>{@link SameRole} - a role with a look of its own: the part in exactly that role, of exactly the declaration's
 *       style - its implementation if a leaf, its declaration if a composite ({@link Target});</li>
 *   <li>{@link Standalone} - a functional role, which only places the part: it wears its standalone - the one
 *       implementation, or declaration, a component has standalone in a style, else its base layer's. No target: the
 *       owner has no say in a part it only places.</li>
 * </ul>
 *
 * @param <Sc> the style's scheme
 * @param <S>  the style: the declaration's, exactly
 * @param <O>  the owner
 */
public sealed interface PartSpec<Sc extends ColorScheme, S extends DesignStyle<Sc>, O extends Component<?>> extends ValueObject
        permits PartSpec.SameRole, PartSpec.Standalone {

    /** The owner. */
    O owner();

    /** The part. */
    Component<?> part();

    /** The role the owner declares it in. */
    Role<?> role();

    /** The slot: the part in the role its owner declares - two slots may place one part, each its own. */
    default Casting slot() { return new Casting(part(), role()); }

    /** The casting the part wears in it: in the role, or standalone where the role only places it. */
    Casting casting();

    /**
     * The part in a role with a look of its own: exactly that role, exactly the declaration's style.
     *
     * @param owner the owner
     * @param part  the part
     * @param role  the role, no functional one
     * @param to    the part's implementation if a leaf, its declaration if a composite, in that role
     */
    record SameRole<Sc extends ColorScheme, S extends DesignStyle<Sc>, O extends Component<?>, P extends Component<?>, R extends Role<?>>(
            O owner, P part, R role, Target<Sc, S, P, R> to) implements PartSpec<Sc, S, O> {
        public SameRole {
            Objects.requireNonNull(owner, "SameRole.owner");
            Objects.requireNonNull(part, "SameRole.part");
            Objects.requireNonNull(role, "SameRole.role");
            Objects.requireNonNull(to, "SameRole.to");
            if (new Casting(part, role).functional())
                throw new IllegalArgumentException(owner.token() + "'s " + part.token() + " is placed by " + role.name().value()
                        + ", a role that only places it: a Standalone spec");
        }

        @Override public Casting casting() { return new Casting(part, role); }
    }

    /**
     * The part a functional role only places: it wears its standalone, as the worn style gives it.
     *
     * @param owner the owner
     * @param part  the part
     * @param role  the functional role placing it
     */
    record Standalone<Sc extends ColorScheme, S extends DesignStyle<Sc>, O extends Component<?>, P extends Component<?>, R extends Role<?>>(
            O owner, P part, R role) implements PartSpec<Sc, S, O> {
        public Standalone {
            Objects.requireNonNull(owner, "Standalone.owner");
            Objects.requireNonNull(part, "Standalone.part");
            Objects.requireNonNull(role, "Standalone.role");
            if (!new Casting(part, role).functional())
                throw new IllegalArgumentException(owner.token() + "'s " + part.token() + " is cast as " + role.name().value()
                        + ", a role with a look of its own: a SameRole spec");
        }

        @Override public Casting casting() { return new Casting(part); }
    }
}
