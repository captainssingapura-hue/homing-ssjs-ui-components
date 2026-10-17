package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An element as the code makes it (RFC 0067, D53): a top-level component, and the slots down from it to the element -
 * each a part of the one before, in the role that one declares - none for the top-level component itself. What it wears
 * in a style is found by walking the path through the looks' parts.
 *
 * @param top   the top-level component
 * @param parts the slots down to the element, each a part of the one before in its declared role
 */
public record ElementPath(Component<?> top, List<Casting> parts) implements ValueObject {

    public ElementPath {
        Objects.requireNonNull(top, "ElementPath.top");
        parts = List.copyOf(Objects.requireNonNull(parts, "ElementPath.parts"));
    }

    /** The casting the element's look is of: its slot's - the part on its own where the role only places it - or the top-level component's. */
    public Casting casting() {
        if (parts.isEmpty()) return new Casting(top);
        Casting last = parts.getLast();
        return last.functional() ? last.alone() : last;
    }

    /** One slot further down. */
    public ElementPath then(Casting part) {
        var out = new ArrayList<>(parts);
        out.add(part);
        return new ElementPath(top, out);
    }

    /** {@code dialog > action-bar > plain-button_action}. */
    public String token() {
        var out = new StringBuilder(top.token());
        for (Casting c : parts) out.append(" > ").append(c.token());
        return out.toString();
    }
}
