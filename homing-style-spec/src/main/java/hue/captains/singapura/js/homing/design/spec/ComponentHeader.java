package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Cardinality;
import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.Room;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.StateValue;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * A component's header (RFC 0071, D19): what the server stamps into the module that implements it, derived from the
 * taxonomy and from nothing else - what the component is, and nothing of how it is built. Its marker, its slots, exactly
 * its parts, its state types with what each carries, its events and its extents. The same wherever the component is made
 * and whatever role it plays; it names no class.
 *
 * @param component the component
 * @param composite whether it has parts
 * @param topLevel  whether its component module lists it: a unit of composition, at the root unless placed in a slot
 * @param slots     each player of each part it declares, in the part's role, in its parts' order
 * @param parts     its parts, each once, in its slots' order
 * @param states    the state types it declares, in the order it lists them, each with what it carries
 * @param events    the events it offers besides its states' changes
 * @param extents   the extents it varies along, its branches' first
 * @param rooms     its rooms: elements of its own inside it that are no parts, sharing its class (RFC 0067)
 */
public record ComponentHeader(Component<?> component, boolean composite, boolean topLevel, List<HeaderSlot> slots,
                              List<Component<?>> parts, List<HeaderState> states, List<EventType<?>> events,
                              List<ExtentAxis> extents, List<Room> rooms) implements ValueObject {

    public ComponentHeader {
        Objects.requireNonNull(component, "ComponentHeader.component");
        slots = List.copyOf(slots);
        parts = List.copyOf(parts);
        states = List.copyOf(states);
        events = List.copyOf(events);
        extents = List.copyOf(extents);
        rooms = List.copyOf(rooms);
    }

    /**
     * One slot: one player of one part the component declares, in the part's role - what a part's position is taken by.
     *
     * @param part         the player
     * @param role         the role it plays the part in
     * @param cardinality  how many the part is
     * @param placed       whether the role only places it, the part then wearing its own standalone
     * @param alternatives how many players the part has: one, or several to choose among
     */
    public record HeaderSlot(Component<?> part, Role<?> role, Cardinality cardinality, boolean placed, int alternatives)
            implements ValueObject {
        public HeaderSlot {
            Objects.requireNonNull(part, "HeaderSlot.part");
            Objects.requireNonNull(role, "HeaderSlot.role");
            Objects.requireNonNull(cardinality, "HeaderSlot.cardinality");
        }

        /** Its token, as a path holds it: {@code action-bar_actions}. */
        public String token() { return new Casting(part, role).token(); }
    }

    /**
     * One state type the component has, bound to it, with what the type carries for the page to make a state of it.
     *
     * @param type    the state type
     * @param allowed the states it allows, the one at rest first
     * @param words   the design word each state but the one at rest is answered by
     * @param flag    whether it is a yes or a no
     * @param browser the pseudo-class the browser draws it by, for a state the page never makes; none for the page's own
     * @param level   its importance for this component - its own level for it, where the component gives one, else the type's
     */
    public record HeaderState(StateType<?> type, List<StateValue> allowed, Map<StateValue, String> words, boolean flag,
                              Optional<String> browser, Optional<String> level) implements ValueObject {
        public HeaderState {
            Objects.requireNonNull(type, "HeaderState.type");
            allowed = List.copyOf(allowed);
            words = Collections.unmodifiableMap(new LinkedHashMap<>(words));
            Objects.requireNonNull(browser, "HeaderState.browser");
            Objects.requireNonNull(level, "HeaderState.level");
        }

        /** Its token: {@code enabled}. */
        public String token() { return type.name().value(); }

        /** The state at rest. */
        public StateValue rest() { return allowed.getFirst(); }
    }
}
