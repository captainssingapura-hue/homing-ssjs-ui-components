package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModules;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.component.taxonomy.VirtualComponent;
import hue.captains.singapura.js.homing.component.taxonomy.WithRoles;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
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
 * Reads the typed spec stack (RFC 0067, D53, D54, D55) and holds it to its rules, refusing it with every problem at once:
 *
 * <ul>
 *   <li>the base modules: one base layer, written over nothing; their components a taxonomy;</li>
 *   <li>each style: written over the base or a style registered before it;</li>
 *   <li>each module: naming only component modules registered, and assigning only components they bring - a jar styles
 *       nothing it does not say it styles;</li>
 *   <li>one assignment a (layer, component); the base assigning every component, each by the module that brings it;</li>
 *   <li>every assignment of a component whole: its castings exactly - on its own, and in each role of its set
 *       ({@code WithRoles}, D55), which the taxonomy holds every slot to - so an extension reaches no casting of a
 *       component it does not bring, and base draws every casting it could be asked for;</li>
 *   <li>each implementation a stateless record, listing each of its colours once.</li>
 * </ul>
 *
 * <p>That an assignment names only its own component's implementations, of its own layer, is the compiler's: an
 * implementation of another component, or of another layer, does not fit.</p>
 */
public record ReadSpec() implements StatelessFunctionalObject {

    public static final ReadSpec INSTANCE = new ReadSpec();

    /** The registry of the base modules and the style modules, with the instance's words and the resolver of their content, registered explicitly. */
    public SpecRegistry read(List<? extends BaseSpecModule<?, ?>> bases, List<? extends SpecModule<?, ?>> styles,
                             List<? extends Role<?>> roles, List<? extends StateType<?>> states, List<? extends EventType<?>> events,
                             CssResolver resolver) {
        var problems = new ArrayList<String>();
        if (bases.isEmpty()) refuse(List.of("no base module: the base layer assigns every component"));
        DesignStyle<?> base = bases.get(0).style();
        for (BaseSpecModule<?, ?> m : bases)
            if (m.style().getClass() != base.getClass())
                problems.add(name(m) + " is " + m.style().token() + "'s, and the base is " + base.token() + ": an instance has one base");
        if (base instanceof Derives<?, ?> d) problems.add("the base, " + base.token() + ", is written over " + d.base().token() + ": a base is written over nothing");
        List<ComponentModule> registered = bases.stream().<ComponentModule>map(BaseSpecModule::components).toList();
        Taxonomy taxonomy;
        try {
            taxonomy = ComponentModules.INSTANCE.read(registered, roles, states, events);
        } catch (RuntimeException e) {
            problems.add(e.getMessage());
            refuse(problems);
            throw e;
        }
        Map<Component<?>, ComponentModule> bringers = ComponentModules.INSTANCE.bringers(registered, taxonomy);
        var top = new LinkedHashSet<Component<?>>();
        for (ComponentModule m : registered)   // the layouts' styling is never a unit on a page: no top-level component
            for (Component<?> c : m.components()) if (!(c instanceof hue.captains.singapura.js.homing.component.taxonomy.LayoutStyling<?>)) top.add(c);

        var assignments = new LinkedHashMap<DesignStyle<?>, Map<Component<?>, ComponentAssignment<?, ?, ?>>>();
        assignments.put(base, new LinkedHashMap<>());
        var order = new ArrayList<DesignStyle<?>>(List.of(base));
        var modules = new ArrayList<SpecModule<?, ?>>(bases);
        modules.addAll(styles);
        for (int i = 0; i < modules.size(); i++) {
            SpecModule<?, ?> m = modules.get(i);
            DesignStyle<?> s = m.style();
            if (i >= bases.size()) {
                if (s.getClass() == base.getClass()) { problems.add(name(m) + " is of the base: the base comes in base modules"); continue; }
                if (!assignments.containsKey(s)) {
                    if (!(s instanceof Derives<?, ?> d) || !assignments.containsKey(d.base()))
                        problems.add(s.token() + " is written over no style registered before it: a style is written over the base, or one registered over it");
                    assignments.put(s, new LinkedHashMap<>());
                    order.add(s);
                }
            }
            Map<Component<?>, ComponentAssignment<?, ?, ?>> mine = assignments.get(s);
            for (ComponentModule cm : m.modules())
                if (!registered.contains(cm)) problems.add(name(m) + " names " + name(cm) + ", no component module registered");
            for (ComponentAssignment<?, ?, ?> a : m.assignments()) {
                Component<?> c = a.component();
                ComponentModule by = bringers.get(c);
                if (by == null) { problems.add(s.token() + ": " + name(m) + " assigns " + c.token() + ", no component of the taxonomy"); continue; }
                if (!m.modules().contains(by))
                    problems.add(s.token() + ": " + name(m) + " assigns " + c.token() + ", which " + name(by)
                            + " brings - no module it names: a jar assigns only the components of the modules it names");
                if (mine.putIfAbsent(c, a) != null) problems.add(c.token() + " is assigned twice in " + s.token() + ": a component has one assignment a layer");
                for (CssImplementation<?, ?, ?> impl : a.implementations()) {
                    record(impl.getClass(), problems);
                    colours(impl, problems);
                }
            }
        }

        Map<Component<?>, ComponentAssignment<?, ?, ?>> onBase = assignments.get(base);
        for (Component<?> c : taxonomy.components())
            if (!onBase.containsKey(c) && !(c instanceof VirtualComponent<?>) && !(c instanceof hue.captains.singapura.js.homing.component.taxonomy.LayoutStyling<?>))
                problems.add(c.token() + " has no assignment in the base: the base assigns every component");
        for (DesignStyle<?> s : order)
            for (ComponentAssignment<?, ?, ?> a : assignments.get(s).values()) {
                Set<Casting> want = castings(a.component());
                Set<Casting> have = new LinkedHashSet<>(a.castings());
                var missing = new ArrayList<String>();
                for (Casting k : want) if (!have.contains(k)) missing.add(k.token());
                var extra = new ArrayList<String>();
                for (Casting k : have) if (!want.contains(k)) extra.add(k.token());
                if (!missing.isEmpty())
                    problems.add(s.token() + " assigns " + a.component().token() + " and not " + String.join(", ", missing)
                            + ": a layer assigns every casting of a component it assigns - on its own, and in each role of its set");
                if (!extra.isEmpty())
                    problems.add(s.token() + " assigns " + String.join(", ", extra) + ", which " + a.component().token()
                            + "'s role set does not give: a component is cast only in the roles of its set");
            }
        if (!problems.isEmpty()) refuse(problems);
        return new SpecRegistry(taxonomy, base, List.copyOf(top), order, assignments, resolver);
    }

    /** A component's castings, its own (D55): on its own, and in each role of its set - none, for a component only ever placed. */
    private static Set<Casting> castings(Component<?> c) {
        var out = new LinkedHashSet<Casting>();
        out.add(new Casting(c));
        if (c instanceof WithRoles<?> w) for (Role<?> r : w.roleSet().roles()) out.add(new Casting(c, r));
        return out;
    }

    /** The colours of an implementation: each once. */
    private static void colours(CssImplementation<?, ?, ?> impl, List<String> problems) {
        var seen = new HashSet<Class<?>>();
        for (ColorLeaf<?, ?, ?> c : impl.colours())
            if (!seen.add(c.getClass())) problems.add(impl.getClass().getName() + " lists " + c.token() + " twice: each colour once");
    }

    private static void record(Class<?> type, List<String> problems) {
        if (!type.isRecord() || type.getRecordComponents().length > 0)
            problems.add(type.getName() + " is no stateless record: an implementation is a value, a record of no components");
    }

    private static String name(Object module) { return module.getClass().getSimpleName(); }

    private static void refuse(List<String> problems) {
        throw new IllegalArgumentException("the typed spec is refused:\n  " + String.join("\n  ", problems));
    }
}
