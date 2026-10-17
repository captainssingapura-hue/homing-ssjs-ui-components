package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;

/**
 * A component's standalone in a style (RFC 0067, D53): the one implementation - a leaf's - or declaration - a
 * composite's - it has standalone in the style. A component has one standalone a style; a style that gives none uses its
 * base layer's. A module lists the standalones of its top-level components, and of any component of their design a
 * functional role places.
 *
 * @param component the component
 * @param to        its implementation if a leaf, its declaration if a composite, standalone
 */
public record StandaloneEntry<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>>(C component, Target<Sc, S, C, RoleRoot> to)
        implements ValueObject {

    public StandaloneEntry {
        Objects.requireNonNull(component, "StandaloneEntry.component");
        Objects.requireNonNull(to, "StandaloneEntry.to");
    }
}
