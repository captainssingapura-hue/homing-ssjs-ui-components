package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;

/**
 * A component cast in a role, assigned (RFC 0067, D54): the role, and the implementation the component wears in it - one
 * of the component's own, of the assignment's layer, by the compiler: the one it wears on its own, where the role looks
 * as it does, or another of its own.
 *
 * @param role the role, one with a look of its own
 * @param to   the implementation the component wears in it
 */
public record RoleAssignment<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>>(Role<?> role, CssImplementation<Sc, S, C> to)
        implements ValueObject {
    public RoleAssignment {
        Objects.requireNonNull(role, "RoleAssignment.role");
        Objects.requireNonNull(to, "RoleAssignment.to");
    }
}
