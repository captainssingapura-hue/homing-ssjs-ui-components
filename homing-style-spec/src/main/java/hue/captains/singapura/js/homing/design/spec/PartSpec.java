package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;

/**
 * One part slot of an owner's look pointed at a look (RFC 0067, D53), for the model's completeness and soundness: the
 * owner, the part, the role the owner casts it in, and the look it wears - of exactly the owner's look's style, by the
 * compiler. Two kinds, by what the role does:
 *
 * <ul>
 *   <li>{@link Cast} - the part cast in a role: it wears a look of that part in that role;</li>
 *   <li>{@link Placed} - the part a functional role only places: it wears a look of the part on its own. A top-level
 *       part a role only places is no spec of its owner's: it wears its own implementation.</li>
 * </ul>
 *
 * @param <Sc> the style's scheme
 * @param <S>  the style: the owner's look's, exactly
 * @param <O>  the owner
 */
public sealed interface PartSpec<Sc extends ColorScheme, S extends DesignStyle<Sc>, O extends Component<?>> extends ValueObject
        permits PartSpec.Cast, PartSpec.Placed {

    /** The owner. */
    O owner();

    /** The part. */
    Component<?> part();

    /** The role the owner casts it in. */
    Role<?> role();

    /** The look it wears. */
    CastingStylePerRole<Sc, S, ?, ?> look();

    /** The casting its look is of: the part in its role, or the part on its own when the role only places it. */
    Casting casting();

    /** The slot it points: the part in the role its owner declares - two slots may place one part, each its own. */
    default Casting slot() { return new Casting(part(), role()); }

    /**
     * The part cast in a role: it wears a look of exactly that part in exactly that role.
     *
     * @param owner the owner
     * @param part  the part
     * @param role  the role it is cast in, which is no functional one
     * @param look  its look
     */
    record Cast<Sc extends ColorScheme, S extends DesignStyle<Sc>, O extends Component<?>, P extends Component<?>, R extends Role<?>>(
            O owner, P part, R role, CastingStylePerRole<Sc, S, P, R> look) implements PartSpec<Sc, S, O> {
        public Cast {
            Objects.requireNonNull(owner, "Cast.owner");
            Objects.requireNonNull(part, "Cast.part");
            Objects.requireNonNull(role, "Cast.role");
            Objects.requireNonNull(look, "Cast.look");
            if (new Casting(part, role).functional())
                throw new IllegalArgumentException(owner.token() + "'s " + part.token() + " is placed by " + role.name().value()
                        + ", a role that only places it: a Placed spec, its look the part's on its own");
        }

        @Override public Casting casting() { return new Casting(part, role); }
    }

    /**
     * The part a functional role only places: it wears a look of the part on its own.
     *
     * @param owner the owner
     * @param part  the part, no top-level component
     * @param role  the functional role placing it
     * @param look  its look, of the part on its own
     */
    record Placed<Sc extends ColorScheme, S extends DesignStyle<Sc>, O extends Component<?>, P extends Component<?>, R extends Role<?>>(
            O owner, P part, R role, CastingStylePerRole<Sc, S, P, RoleRoot> look) implements PartSpec<Sc, S, O> {
        public Placed {
            Objects.requireNonNull(owner, "Placed.owner");
            Objects.requireNonNull(part, "Placed.part");
            Objects.requireNonNull(role, "Placed.role");
            Objects.requireNonNull(look, "Placed.look");
            if (!new Casting(part, role).functional())
                throw new IllegalArgumentException(owner.token() + "'s " + part.token() + " is cast as " + role.name().value()
                        + ", a role with a look of its own: a Cast spec, its look the part's in that role");
        }

        @Override public Casting casting() { return new Casting(part); }
    }
}
