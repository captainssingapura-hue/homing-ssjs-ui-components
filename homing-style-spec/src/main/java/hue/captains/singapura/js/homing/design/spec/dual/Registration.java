package hue.captains.singapura.js.homing.design.spec.dual;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.List;
import java.util.Objects;

/**
 * What an instance registers, as its trees' duals are read from it (RFC 0071): the components taxonomy its component
 * modules bring, the top-level components they list - the units a handle starts at - and the styles a page may wear.
 *
 * @param taxonomy the registered components, their parts, roles, states and events
 * @param top      the top-level components, in the modules' order
 * @param styles   the selectable styles, in the registry's order; base is never one
 */
public record Registration(Taxonomy taxonomy, List<Component<?>> top, List<DesignStyle<?>> styles) implements ValueObject {

    public Registration {
        Objects.requireNonNull(taxonomy, "Registration.taxonomy");
        top = List.copyOf(top);
        styles = List.copyOf(styles);
    }
}
