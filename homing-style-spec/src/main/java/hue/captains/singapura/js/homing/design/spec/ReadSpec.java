package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModules;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.component.taxonomy.VirtualComponent;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Reads the typed spec stack (RFC 0067, D53) and holds it to its rules, refusing it with every problem at once:
 *
 * <ul>
 *   <li>the base modules: one base layer, written over nothing; their components a taxonomy; each listed component
 *       implemented once in the base, by its own module, and nothing else implemented;</li>
 *   <li>each style: written over the base or a style registered before it; implementing top-level components only,
 *       each once;</li>
 *   <li>every look reached - from an implementation, a spec or a delegation - a stateless record; its parts complete:
 *       one spec for every slot of its component, of the slot's kind, and nothing else; its delegations ending at CSS of
 *       its own, never round.</li>
 * </ul>
 *
 * <p>That a spec's look is of the owner's style exactly, and a delegation's of the same component in the same role in
 * the same style or the one it is written over, is the compiler's.</p>
 */
public record ReadSpec() implements StatelessFunctionalObject {

    public static final ReadSpec INSTANCE = new ReadSpec();

    /** The registry of the base modules and the style modules, with the instance's words. */
    public SpecRegistry read(List<? extends BaseSpecModule<?, ?>> bases, List<? extends SpecModule<?, ?>> styles,
                             List<? extends Role<?>> roles, List<? extends StateType<?>> states, List<? extends EventType<?>> events) {
        var problems = new ArrayList<String>();
        if (bases.isEmpty()) refuse(List.of("no base module: the base layer implements every top-level component"));
        DesignStyle<?> base = bases.get(0).style();
        for (BaseSpecModule<?, ?> m : bases)
            if (m.style().getClass() != base.getClass())
                problems.add(name(m) + " is " + m.style().token() + "'s, and the base is " + base.token() + ": an instance has one base");
        if (base instanceof Derives<?, ?> d) problems.add("the base, " + base.token() + ", is written over " + d.base().token() + ": a base is written over nothing");
        Taxonomy taxonomy;
        try {
            taxonomy = ComponentModules.INSTANCE.read(bases.stream().map(BaseSpecModule::components).toList(), roles, states, events);
        } catch (RuntimeException e) {
            problems.add(e.getMessage());
            refuse(problems);
            throw e;
        }
        var top = new LinkedHashSet<Component<?>>();
        for (BaseSpecModule<?, ?> m : bases) top.addAll(m.components().components());

        var implementations = new LinkedHashMap<DesignStyle<?>, Map<Component<?>, CastingStylePerRole<?, ?, ?, ?>>>();
        var onBase = new LinkedHashMap<Component<?>, CastingStylePerRole<?, ?, ?, ?>>();
        for (BaseSpecModule<?, ?> m : bases) {
            List<? extends Component<?>> listed = m.components().components();
            for (Implemented<?, ?, ?> i : m.implementations()) {
                if (!listed.contains(i.component()))
                    problems.add(name(m) + " implements " + i.component().token() + ", which it does not list: a module implements its own components");
                else if (onBase.putIfAbsent(i.component(), i.look()) != null)
                    problems.add(i.component().token() + " is implemented twice in the base");
            }
            for (Component<?> c : listed)
                if (!onBase.containsKey(c) && !(c instanceof VirtualComponent<?>))
                    problems.add(name(m) + " leaves " + c.token() + " unimplemented in the base: the base implements every top-level component");
        }
        implementations.put(base, onBase);
        var order = new ArrayList<DesignStyle<?>>(List.of(base));
        for (SpecModule<?, ?> m : styles) {
            DesignStyle<?> s = m.style();
            if (s.getClass() == base.getClass()) { problems.add(name(m) + " is of the base: the base comes in base modules"); continue; }
            Map<Component<?>, CastingStylePerRole<?, ?, ?, ?>> mine = implementations.get(s);
            if (mine == null) {
                if (!(s instanceof Derives<?, ?> d) || !implementations.containsKey(d.base()))
                    problems.add(s.token() + " is written over no style registered before it: a style is written over the base, or one registered over it");
                mine = new LinkedHashMap<>();
                implementations.put(s, mine);
                order.add(s);
            }
            for (Implemented<?, ?, ?> i : m.implementations()) {
                if (!top.contains(i.component()))
                    problems.add(s.token() + ": " + name(m) + " implements " + i.component().token() + ", no top-level component: a part's look is reached by pointing");
                else if (mine.putIfAbsent(i.component(), i.look()) != null)
                    problems.add(i.component().token() + " is implemented twice in " + s.token());
            }
        }

        var reached = new LinkedHashMap<Class<?>, Casting>();
        for (var e : implementations.entrySet())
            for (var i : e.getValue().entrySet()) reach(i.getValue(), new Casting(i.getKey()), taxonomy, top, reached, problems);
        if (!problems.isEmpty()) refuse(problems);
        return new SpecRegistry(taxonomy, base, List.copyOf(top), order, implementations);
    }

    /** A look reached as a casting, held: a stateless record, its parts complete, its delegations to CSS; then what it reaches. */
    private static void reach(CastingStylePerRole<?, ?, ?, ?> look, Casting as, Taxonomy taxonomy, Set<Component<?>> top,
                              Map<Class<?>, Casting> reached, List<String> problems) {
        Class<?> type = look.getClass();
        Casting before = reached.putIfAbsent(type, as);
        if (before != null) {
            if (!before.equals(as)) problems.add(type.getName() + " is reached as " + before.token() + " and as " + as.token() + ": a look is of one casting");
            return;
        }
        if (!type.isRecord() || type.getRecordComponents().length > 0)
            problems.add(type.getName() + " is no stateless record: a look is a value, a record of no components");
        var expected = new LinkedHashMap<Casting, Slots.Slot>();
        for (Slots.Slot s : Slots.INSTANCE.of(taxonomy, top, as.cast())) expected.put(s.slot(), s);
        PartsSpec<?, ?, ?> parts;
        try {
            parts = look.parts();
        } catch (RuntimeException e) {
            problems.add(type.getName() + "'s parts are refused: " + e.getMessage());
            return;
        }
        var given = parts.bySlot();
        var missing = new ArrayList<String>();
        for (var s : expected.entrySet()) {
            PartSpec<?, ?, ?> p = given.get(s.getKey());
            if (p == null) missing.add(s.getKey().token());
            else if ((p instanceof PartSpec.Placed<?, ?, ?, ?, ?>) != s.getValue().placed())
                problems.add(type.getName() + " points " + s.getKey().token() + " as " + (s.getValue().placed() ? "cast" : "placed")
                        + ", and its role " + (s.getValue().placed() ? "only places it" : "casts it"));
        }
        if (!missing.isEmpty()) problems.add(type.getName() + ", " + as.token() + ", points none of " + String.join(", ", missing)
                + ": a look points every slot of its component");
        for (Casting c : given.keySet())
            if (!expected.containsKey(c)) problems.add(type.getName() + " points " + c.token() + ", no slot of " + as.cast().token());
        for (PartSpec<?, ?, ?> p : parts.parts())
            if (!p.owner().equals(as.cast()))
                problems.add(type.getName() + "'s parts are " + p.owner().token() + "'s, and it draws " + as.cast().token());
        for (PartSpec<?, ?, ?> p : parts.parts()) if (expected.containsKey(p.slot())) reach(p.look(), p.casting(), taxonomy, top, reached, problems);
        var passed = new HashSet<Class<?>>(List.of(type));
        CastingStylePerRole<?, ?, ?, ?> at = look;
        while (true) {
            CastingStylePerRole<?, ?, ?, ?> next = switch (at.style()) {
                case StylingRes.CssContent<?, ?, ?, ?> c -> null;
                case StylingRes.Delegation<?, ?, ?, ?> d -> d.to();
                case StylingRes.ParentDelegation<?, ?, ?, ?, ?> p -> p.to();
                case null -> { problems.add(at.getClass().getName() + " gives nothing: CSS of its own, or a delegation"); yield null; }
            };
            if (next == null) break;
            if (!passed.add(next.getClass())) {
                problems.add(type.getName() + " delegates round to " + next.getClass().getName());
                break;
            }
            reach(next, as, taxonomy, top, reached, problems);
            at = next;
        }
    }

    private static String name(Object module) { return module.getClass().getSimpleName(); }

    private static void refuse(List<String> problems) {
        throw new IllegalArgumentException("the typed spec is refused:\n  " + String.join("\n  ", problems));
    }
}
