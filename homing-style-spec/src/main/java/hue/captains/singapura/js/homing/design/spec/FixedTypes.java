package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Cardinality;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.FixedValue;
import hue.captains.singapura.js.homing.component.taxonomy.Part;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A component's fixed type, for the page (RFC 0071, D30): what is immutable for the component's life, one object of
 * the component's own type - the second argument of every constructor, {@code (make, fixed, place?)}. Made in the
 * components' dual beside the component's node - {@code FixedPlainButton} beside {@code ComponentPlainButton} - and
 * imported by every header that names the component, its own and its owners', so an owner has the type of every part it
 * makes. It extends the kit's {@code Fixed}, which checks what it is given and freezes what it makes; the component takes
 * an instance of its own type alone.
 *
 * <p>Its fields are derived, never declared (RFC 0071 port, question 5): one for each static part that leaves a choice,
 * in the order of its owner's slots, named for its role, each the part's own fixed object - whether an optional one is
 * there (its fixed object, or none), how many (as many fixed objects), which of several players (that player's fixed
 * object); and a part that leaves no choice of its own but whose own fixed type has fields, nested. A dynamic part has
 * no field: its owner makes and drops it while it lives. A component whose static parts leave nothing to choose has a
 * type with no fields, refusing any argument.</p>
 *
 * <p>After its fields, its values (owner, 23 Oct): what it is made with that is no part's choice, declared by the
 * component itself, immutable for its life - one plain object after its fields, or nothing for every default, checked
 * and frozen by the kit. A part whose own type has values leaves a choice as one with fields does: its owner's type has a
 * field for it. A value is never named as a field.</p>
 */
public record FixedTypes() implements StatelessFunctionalObject {

    public static final FixedTypes INSTANCE = new FixedTypes();

    /**
     * One field of a fixed type: a static part's choice.
     *
     * @param name    its role's name, as the page says it: {@code note}
     * @param least   the fewest fixed objects it holds; as many as its part's count
     * @param most    the most, or none for no most - one and at most one: one fixed object, or none
     * @param players the components that may play it, each's fixed object accepted
     */
    public record Field(String name, int least, Integer most, List<Component<?>> players) implements ValueObject {
        public Field {
            Objects.requireNonNull(name, "Field.name");
            players = List.copyOf(players);
            if (players.isEmpty()) throw new IllegalArgumentException(name + ": a field played by none");
        }
        /** One fixed object, or none where the part is optional: {@code 0..1} or {@code 1}. */
        public boolean single() { return most != null && most == 1; }
    }

    /** A component's fixed type's name: {@code Fixed}, then the component's - {@code FixedPlainButton}. */
    public String name(Component<?> component) { return "Fixed" + JsLiterals.INSTANCE.name(component.token()); }

    /** The fields of a component's fixed type, derived from its static parts, in the order of its slots. */
    public List<Field> fields(Taxonomy taxonomy, Component<?> component) {
        if (component instanceof hue.captains.singapura.js.homing.component.taxonomy.LayoutStyling<?>) return List.of();   // never made: nothing fixed
        return fields(taxonomy, component, new HashSet<>());
    }

    private List<Field> fields(Taxonomy taxonomy, Component<?> component, Set<Component<?>> above) {
        if (!above.add(component)) throw new IllegalArgumentException(component.token() + " holds itself, through its static parts: no fixed type ends");
        var out = new ArrayList<Field>();
        for (Part<?, ?> p : taxonomy.partsOf(component)) {
            Cardinality count = p.cardinality();
            if (count.dynamic()) continue;
            List<Component<?>> players = new ArrayList<>(p.bases());
            boolean several = players.size() > 1;
            boolean nested = !several && (!fields(taxonomy, players.get(0), new HashSet<>(above)).isEmpty() || !players.get(0).fixedValues().isEmpty());
            String name = JsLiterals.INSTANCE.camel(p.role().name().value());
            switch (count) {
                case Cardinality.Fixed f -> { if (several || nested) out.add(new Field(name, f.count(), f.count(), players)); }
                case Cardinality.Varying v -> out.add(new Field(name, v.min(), v.max() instanceof Cardinality.AtMost(int most) ? most : null, players));
            }
        }
        above.remove(component);
        for (FixedValue v : component.fixedValues())
            if (out.stream().anyMatch(f -> f.name().equals(v.name()))) throw new IllegalArgumentException(component.token() + ": its value " + v.name() + " is named as one of its fields");
        return List.copyOf(out);
    }

    /**
     * A component's fixed type, as its dual declares it: the kit's {@code Fixed} for the component's node, named in the
     * dual - {@code class FixedPlainButton extends Fixed}, saying its component ({@code static COMPONENT}), by which its
     * owner places a part it makes, and handing on what it is given - with its fields where its static
     * parts leave a choice, each the kit's {@code FixedField} for it to check: its name, its count - its fewest and its most,
     * {@code null} for none - and its players' fixed types, looked up as an object is made.
     *
     * @param node the component's node's name in the dual: {@code PlainButton}
     */
    public TreeDual.Companion companion(Taxonomy taxonomy, Component<?> component, String node) {
        String n = name(component);
        var js = JsLiterals.INSTANCE;
        List<Field> fields = fields(taxonomy, component);
        String declared = fields.isEmpty() ? "" : "static FIELDS = [" + String.join(", ", fields.stream().map(f -> "new FixedField("
                + js.string(f.name()) + ", new Count(" + f.least() + ", " + (f.most() == null ? "null" : f.most()) + "), () => ["
                + String.join(", ", f.players().stream().map(this::name).toList()) + "])").toList()) + "]; ";
        List<FixedValue> values = component.fixedValues();
        if (!values.isEmpty()) declared += "static VALUES = " + value(new FixedValue.Group("values", values)) + "; ";
        return new TreeDual.Companion(n, "class " + n + " extends Fixed { static COMPONENT = " + node + "; " + declared + "constructor(...given) { super(" + node + ", given); } }");
    }

    /** A value as the kit checks it: {@code new FixedWhole("w", 160, false)}, a group's values within it. */
    private String value(FixedValue v) {
        var js = JsLiterals.INSTANCE;
        return switch (v) {
            case FixedValue.Whole w -> "new FixedWhole(" + js.string(w.name()) + ", " + w.byDefault() + ", " + w.orNone() + ")";
            case FixedValue.YesNo y -> "new FixedYesNo(" + js.string(y.name()) + ", " + y.byDefault() + ", " + y.orNone() + ")";
            case FixedValue.Text t -> "new FixedText(" + js.string(t.name()) + ", " + (t.byDefault() == null ? "null" : js.string(t.byDefault())) + ", " + t.orNone() + ")";
            case FixedValue.OneOf o -> "new FixedOneOf(" + js.string(o.name()) + ", [" + String.join(", ", o.names().stream().map(js::string).toList()) + "], "
                    + (o.byDefault() == null ? "null" : js.string(o.byDefault())) + ", " + o.orNone() + ")";
            case FixedValue.Element e -> "new FixedElement(" + js.string(e.name()) + ")";
            case FixedValue.Group g -> "new FixedGroup(" + js.string(g.name()) + ", [" + String.join(", ", g.values().stream().map(this::value).toList()) + "])";
        };
    }
}
