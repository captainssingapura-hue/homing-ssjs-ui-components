package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.Classed;
import hue.captains.singapura.js.homing.component.taxonomy.Flag;
import hue.captains.singapura.js.homing.component.taxonomy.Importances;
import hue.captains.singapura.js.homing.component.taxonomy.Part;
import hue.captains.singapura.js.homing.component.taxonomy.PartOnly;
import hue.captains.singapura.js.homing.component.taxonomy.Pseudo;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.StateValue;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.tree.NodeName;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A component's header ({@link ComponentHeader}), read off the taxonomy (RFC 0071, D19): its parts' players in their
 * roles, its declared state types with what each carries, its extents - nothing written by hand.
 */
public record ReadHeader() implements StatelessFunctionalObject {

    public static final ReadHeader INSTANCE = new ReadHeader();

    /** The header of one component of the taxonomy; top-level when the given list holds it, unless it is only ever a part (PartOnly, 23 Oct). */
    public ComponentHeader read(Taxonomy taxonomy, Collection<? extends Component<?>> top, Component<?> component) {
        var slots = new ArrayList<ComponentHeader.HeaderSlot>();
        var parts = new LinkedHashSet<Component<?>>();
        for (Part<?, ?> p : taxonomy.partsOf(component))
            for (Component<?> player : p.bases()) {
                slots.add(new ComponentHeader.HeaderSlot(player, p.role(), p.cardinality(), new Casting(player, p.role()).functional(),
                        p.bases().size()));
                parts.add(player);
            }
        var states = new ArrayList<ComponentHeader.HeaderState>();
        for (StateType<?> each : component.states() == null ? List.<StateType<?>>of() : component.states()) {
            if (!(each instanceof Classed<?> s)) {   // drawn by what it moves or shows: no values to class, no words, no level
                states.add(new ComponentHeader.HeaderState(each, List.of(), Map.of(), false, Optional.empty(), Optional.empty()));
                continue;
            }
            Map<StateValue, String> words = new LinkedHashMap<>();
            List<StateValue> allowed = s.values();
            for (StateValue v : allowed.subList(1, allowed.size())) words.put(v, s.word(v));
            Optional<String> level = Importances.INSTANCE.levelFor(component, s).map(l -> NodeName.ofType(l, "").value());
            states.add(new ComponentHeader.HeaderState(s, allowed, words, s instanceof Flag<?>,
                    s instanceof Pseudo p ? Optional.of(p.pseudoClass()) : Optional.empty(), level));
        }
        var chosen = new LinkedHashSet<Component<?>>();
        for (Component<?> part : parts) choices(taxonomy, part, chosen);
        chosen.removeAll(parts);
        return new ComponentHeader(component, component instanceof Composite<?>, top.contains(component) && !(component instanceof PartOnly), slots, List.copyOf(parts),
                states, taxonomy.extents(component), component.rooms(), List.copyOf(chosen));
    }

    /** The players of the choices a component's fixed type leaves, and theirs, all the way down: into the set given. */
    private void choices(Taxonomy taxonomy, Component<?> component, LinkedHashSet<Component<?>> into) {
        for (FixedTypes.Field f : FixedTypes.INSTANCE.fields(taxonomy, component))
            for (Component<?> player : f.players()) if (into.add(player)) choices(taxonomy, player, into);
    }

    /** Every component's header, the taxonomy's components in its order. */
    public List<ComponentHeader> readAll(Taxonomy taxonomy, Collection<? extends Component<?>> top) {
        // a leaf the document wears has no module to stamp: a style's rule on :root, no class, no dual (owner, 24 Oct)
        return taxonomy.components().stream().filter(c -> !taxonomy.wornByTheDocument(c)).map(c -> read(taxonomy, top, c)).toList();
    }
}
