package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A component's assignment in a layer (RFC 0067, D54): the implementation each of its castings wears - the component on
 * its own, and in each role of its role set (D55) - each one of the component's own, of exactly this layer, by the
 * compiler. Whole: a layer that assigns a component assigns every casting of it; a layer that does not leaves the
 * component, whole, to the layer it is written over. Two castings meant alike name one implementation; no casting can
 * name another component's, and nothing delegates.
 *
 * <pre>{@code
 * new ComponentAssignment<>(HouseText.Label.INSTANCE, new Label(), List.of(
 *         new RoleAssignment<>(HouseSaying.Title.INSTANCE, new LabelTitle()),
 *         new RoleAssignment<>(HouseSaying.Name.INSTANCE, new Label())))
 * }</pre>
 *
 * @param component the component
 * @param alone     the implementation it wears on its own
 * @param roles     each role with a look of its own it is cast in, each once, and the implementation it wears in it
 */
public record ComponentAssignment<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>>(
        C component, CssImplementation<Sc, S, C> alone, List<RoleAssignment<Sc, S, C>> roles) implements ValueObject {

    public ComponentAssignment {
        Objects.requireNonNull(component, "ComponentAssignment.component");
        Objects.requireNonNull(alone, "ComponentAssignment.alone");
        roles = List.copyOf(Objects.requireNonNull(roles, "ComponentAssignment.roles"));
        var seen = new HashSet<Role<?>>();
        for (RoleAssignment<Sc, S, C> r : roles) {
            if (!seen.add(r.role()))
                throw new IllegalArgumentException(component.token() + " is assigned twice in " + r.role().name().value() + ": a casting once");
            if (new Casting(component, r.role()).functional())
                throw new IllegalArgumentException(component.token() + " is assigned in " + r.role().name().value()
                        + ", a role that only places it: there it wears its look on its own");
        }
    }

    /** Its castings: the component on its own, then in each role. */
    public List<Casting> castings() {
        var out = new ArrayList<Casting>();
        out.add(new Casting(component));
        for (RoleAssignment<Sc, S, C> r : roles) out.add(new Casting(component, r.role()));
        return List.copyOf(out);
    }

    /** The implementation a casting of its component wears: on its own, or in a role it assigns. */
    public Optional<CssImplementation<Sc, S, C>> of(Casting casting) {
        if (!casting.component().equals(component)) return Optional.empty();
        if (casting.standalone()) return Optional.of(alone);
        return roles.stream().filter(r -> r.role().equals(casting.role())).map(RoleAssignment::to).findFirst();
    }

    /** Every implementation it names, each once: the one on its own first. */
    public List<CssImplementation<Sc, S, C>> implementations() {
        var out = new LinkedHashSet<CssImplementation<Sc, S, C>>();
        out.add(alone);
        for (RoleAssignment<Sc, S, C> r : roles) out.add(r.to());
        return List.copyOf(out);
    }
}
