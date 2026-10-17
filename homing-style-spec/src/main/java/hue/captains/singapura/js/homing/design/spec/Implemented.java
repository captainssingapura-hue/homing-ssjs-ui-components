package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;

/**
 * A top-level component's implementation in a style (RFC 0067, D53): the component, and its look on its own - which
 * points its parts. The component is said, never read off the look's type.
 *
 * @param component the top-level component
 * @param look      its look on its own, in the module's style
 */
public record Implemented<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>>(
        C component, CastingStylePerRole<Sc, S, C, RoleRoot> look) implements ValueObject {

    public Implemented {
        Objects.requireNonNull(component, "Implemented.component");
        Objects.requireNonNull(look, "Implemented.look");
    }
}
