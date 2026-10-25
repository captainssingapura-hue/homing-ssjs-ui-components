package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Part;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A component's part slots as a declaration must point them (RFC 0067, D53): every player of every part it declares, in
 * the part's role - a functional one marked, its part placed, worn standalone.
 */
public record Slots() implements StatelessFunctionalObject {

    public static final Slots INSTANCE = new Slots();

    /**
     * One slot.
     *
     * @param part   the player
     * @param role   the role the part is in
     * @param placed whether the role only places it: it then wears its standalone
     */
    public record Slot(Component<?> part, Role<?> role, boolean placed) implements ValueObject {
        /** The casting its look is of. */
        public Casting casting() { return placed ? new Casting(part) : new Casting(part, role); }

        /** The slot itself: the part in the role its owner declares. */
        public Casting slot() { return new Casting(part, role); }
    }

    /** A component's slots, in its parts' order. */
    public List<Slot> of(Taxonomy taxonomy, Component<?> component) {
        var out = new ArrayList<Slot>();
        for (Part<?, ?> p : taxonomy.partsOf(component))
            for (Component<?> b : p.bases()) out.add(new Slot(b, p.role(), new Casting(b, p.role()).functional()));
        return List.copyOf(out);
    }
}
