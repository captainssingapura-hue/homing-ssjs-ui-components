package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * A look's part slots, each pointed (RFC 0067, D53): one owner's, of one style, each slot once. Whether it is complete
 * - every part casting its component declares, and nothing else - is held against the taxonomy where the look is
 * registered.
 *
 * @param parts each slot's spec
 */
public record PartsSpec<Sc extends ColorScheme, S extends DesignStyle<Sc>, C extends Component<?>>(List<PartSpec<Sc, S, C>> parts)
        implements ValueObject {

    public PartsSpec {
        parts = List.copyOf(Objects.requireNonNull(parts, "PartsSpec.parts"));
        Component<?> owner = null;
        var seen = new LinkedHashMap<Casting, PartSpec<Sc, S, C>>();
        for (PartSpec<Sc, S, C> p : parts) {
            if (owner == null) owner = p.owner();
            else if (!owner.equals(p.owner()))
                throw new IllegalArgumentException("a parts spec is one owner's: " + owner.token() + " and " + p.owner().token());
            if (seen.putIfAbsent(p.slot(), p) != null)
                throw new IllegalArgumentException(owner.token() + " points " + p.slot().token() + " twice");
        }
    }

    /** The spec of a slot - a part in the role its owner declares - if it points it. */
    public Optional<PartSpec<Sc, S, C>> of(Casting slot) {
        return parts.stream().filter(p -> p.slot().equals(slot)).findFirst();
    }

    /** Each slot's spec, by the slot. */
    public Map<Casting, PartSpec<Sc, S, C>> bySlot() {
        var out = new LinkedHashMap<Casting, PartSpec<Sc, S, C>>();
        for (PartSpec<Sc, S, C> p : parts) out.put(p.slot(), p);
        return out;
    }
}
