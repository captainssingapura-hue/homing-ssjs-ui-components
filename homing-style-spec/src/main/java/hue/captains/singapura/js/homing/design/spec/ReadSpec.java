package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModules;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;
import hue.captains.singapura.js.homing.component.taxonomy.Part;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.component.taxonomy.VirtualComponent;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayDeque;
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
 *   <li>the base modules: one base layer, written over nothing; their components a taxonomy; a standalone in the base for
 *       every top-level component, and for every component a functional role places; each module's standalones of its
 *       own design;</li>
 *   <li>each style: written over the base or a style registered before it;</li>
 *   <li>one standalone a (style, component): the module's, and no other implementation of the component standalone in
 *       that style - a root delegation, a declaration's own, reach that one;</li>
 *   <li>every declaration reached complete - one spec for every slot of its composite, of the kind the slot's role says,
 *       and nothing else - its nesting ending, never round; every implementation reached ending at CSS of its own, never
 *       round; each a stateless record.</li>
 * </ul>
 *
 * <p>That a spec reaches exactly its declaration's style, part and role, that a leaf is reached through its
 * implementation and a composite through its declaration, and that a delegation stays within style, part and role, is the
 * compiler's.</p>
 */
public record ReadSpec() implements StatelessFunctionalObject {

    public static final ReadSpec INSTANCE = new ReadSpec();

    /** The registry of the base modules and the style modules, with the instance's words. */
    public SpecRegistry read(List<? extends BaseSpecModule<?, ?>> bases, List<? extends SpecModule<?, ?>> styles,
                             List<? extends Role<?>> roles, List<? extends StateType<?>> states, List<? extends EventType<?>> events) {
        var problems = new ArrayList<String>();
        if (bases.isEmpty()) refuse(List.of("no base module: the base layer gives every top-level component its standalone"));
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
        var placed = new LinkedHashSet<Component<?>>();
        for (Part<?, ?> p : taxonomy.parts()) for (Component<?> b : p.bases()) if (new Casting(b, p.role()).functional()) placed.add(b);

        var standalones = new LinkedHashMap<DesignStyle<?>, Map<Component<?>, Target<?, ?, ?, ?>>>();
        var onBase = new LinkedHashMap<Component<?>, Target<?, ?, ?, ?>>();
        for (BaseSpecModule<?, ?> m : bases) {
            Set<Component<?>> design = design(taxonomy, m.components().components());
            for (StandaloneEntry<?, ?, ?> e : m.standalones()) {
                if (!design.contains(e.component()))
                    problems.add(name(m) + " gives " + e.component().token() + " a standalone, no component of its design");
                else if (onBase.putIfAbsent(e.component(), e.to()) != null)
                    problems.add(e.component().token() + " has two standalones in the base: a component has one");
            }
        }
        for (Component<?> c : top)
            if (!onBase.containsKey(c) && !(c instanceof VirtualComponent<?>))
                problems.add(c.token() + " has no standalone in the base: the base gives every top-level component one");
        for (Component<?> c : placed)
            if (!onBase.containsKey(c)) problems.add(c.token() + " is placed by a functional role, and has no standalone in the base");
        standalones.put(base, onBase);
        var order = new ArrayList<DesignStyle<?>>(List.of(base));
        for (SpecModule<?, ?> m : styles) {
            DesignStyle<?> s = m.style();
            if (s.getClass() == base.getClass()) { problems.add(name(m) + " is of the base: the base comes in base modules"); continue; }
            Map<Component<?>, Target<?, ?, ?, ?>> mine = standalones.get(s);
            if (mine == null) {
                if (!(s instanceof Derives<?, ?> d) || !standalones.containsKey(d.base()))
                    problems.add(s.token() + " is written over no style registered before it: a style is written over the base, or one registered over it");
                mine = new LinkedHashMap<>();
                standalones.put(s, mine);
                order.add(s);
            }
            for (StandaloneEntry<?, ?, ?> e : m.standalones()) {
                if (!taxonomy.components().contains(e.component()))
                    problems.add(s.token() + ": " + name(m) + " gives " + e.component().token() + " a standalone, no component of the taxonomy");
                else if (mine.putIfAbsent(e.component(), e.to()) != null)
                    problems.add(e.component().token() + " has two standalones in " + s.token() + ": a component has one");
            }
        }

        var reached = new LinkedHashMap<Object, Casting>();
        for (var layer : standalones.entrySet())
            for (var e : layer.getValue().entrySet())
                reach(e.getValue(), new Casting(e.getKey()), layer.getKey(), taxonomy, standalones, reached, new ArrayDeque<>(), problems);
        if (!problems.isEmpty()) refuse(problems);
        return new SpecRegistry(taxonomy, base, List.copyOf(top), order, standalones);
    }

    /** The components of a module's design: its own, and every part of theirs, down. */
    private static Set<Component<?>> design(Taxonomy taxonomy, List<? extends Component<?>> components) {
        var out = new LinkedHashSet<Component<?>>(components);
        var todo = new ArrayDeque<Component<?>>(components);
        while (!todo.isEmpty())
            for (Part<?, ?> p : taxonomy.partsOf(todo.poll())) for (Component<?> b : p.bases()) if (out.add(b)) todo.add(b);
        return out;
    }

    private static void reach(Target<?, ?, ?, ?> target, Casting as, DesignStyle<?> layer, Taxonomy taxonomy,
                              Map<DesignStyle<?>, Map<Component<?>, Target<?, ?, ?, ?>>> standalones, Map<Object, Casting> reached,
                              ArrayDeque<Object> path, List<String> problems) {
        switch (target) {
            case Target.Impl<?, ?, ?, ?> i -> impl(i.impl(), as, layer, standalones, reached, problems);
            case Target.Declared<?, ?, ?, ?> d -> declaration(d.declaration(), as, layer, taxonomy, standalones, reached, path, problems);
        }
    }

    /** A declaration reached as a casting, held: a stateless record, complete, its nesting never round; then what it reaches. */
    private static void declaration(CastingDeclaration<?, ?, ?, ?> declaration, Casting as, DesignStyle<?> layer, Taxonomy taxonomy,
                                    Map<DesignStyle<?>, Map<Component<?>, Target<?, ?, ?, ?>>> standalones, Map<Object, Casting> reached,
                                    ArrayDeque<Object> path, List<String> problems) {
        Class<?> type = declaration.getClass();
        if (path.contains(type)) {
            problems.add(type.getName() + " nests in itself: " + path + " -> " + type.getSimpleName());
            return;
        }
        Casting before = reached.putIfAbsent(type, as);
        if (before != null) {
            if (!before.equals(as)) problems.add(type.getName() + " is reached as " + before.token() + " and as " + as.token() + ": a declaration is of one casting");
            return;
        }
        record(type, problems);
        var expected = new LinkedHashMap<Casting, Slots.Slot>();
        for (Slots.Slot s : Slots.INSTANCE.of(taxonomy, as.cast())) expected.put(s.slot(), s);
        PartsSpec<?, ?, ?> parts;
        try {
            parts = declaration.parts();
        } catch (RuntimeException e) {
            problems.add(type.getName() + "'s parts are refused: " + e.getMessage());
            return;
        }
        var given = parts.bySlot();
        var missing = new ArrayList<String>();
        for (var s : expected.entrySet()) {
            PartSpec<?, ?, ?> p = given.get(s.getKey());
            if (p == null) missing.add(s.getKey().token());
            else if ((p instanceof PartSpec.Standalone<?, ?, ?, ?, ?>) != s.getValue().placed())
                problems.add(type.getName() + " points " + s.getKey().token() + (s.getValue().placed() ? " in its role: the role only places it, standalone" : " standalone: its role has a look of its own"));
        }
        if (!missing.isEmpty()) problems.add(type.getName() + ", " + as.token() + ", points none of " + String.join(", ", missing) + ": a declaration points every slot");
        for (Casting c : given.keySet()) if (!expected.containsKey(c)) problems.add(type.getName() + " points " + c.token() + ", no slot of " + as.cast().token());
        for (PartSpec<?, ?, ?> p : parts.parts())
            if (!p.owner().equals(as.cast())) problems.add(type.getName() + "'s specs are " + p.owner().token() + "'s, and it declares " + as.cast().token());
        path.push(type);
        for (PartSpec<?, ?, ?> p : parts.parts())
            if (p instanceof PartSpec.SameRole<?, ?, ?, ?, ?> s && expected.containsKey(s.slot()))
                reach(s.to(), s.casting(), layer, taxonomy, standalones, reached, path, problems);
        path.pop();
        impl(declaration.own(), as, layer, standalones, reached, problems);
    }

    /**
     * An implementation reached as a casting, held: a stateless record; standalone, the one its layer gives; its
     * delegations ending at CSS, never round.
     */
    private static void impl(CastingStylePerRole<?, ?, ?, ?> impl, Casting as, DesignStyle<?> layer,
                             Map<DesignStyle<?>, Map<Component<?>, Target<?, ?, ?, ?>>> standalones, Map<Object, Casting> reached,
                             List<String> problems) {
        var passed = new HashSet<Class<?>>();
        CastingStylePerRole<?, ?, ?, ?> at = impl;
        Casting atAs = as;
        DesignStyle<?> atLayer = layer;
        while (at != null) {
            Class<?> type = at.getClass();
            if (!passed.add(type)) {
                problems.add(impl.getClass().getName() + " delegates round to " + type.getName());
                return;
            }
            Casting before = reached.putIfAbsent(type, atAs);
            if (before != null && !before.equals(atAs))
                problems.add(type.getName() + " is reached as " + before.token() + " and as " + atAs.token() + ": an implementation is of one casting");
            if (before == null) {
                record(type, problems);
                if (atAs.standalone()) standaloneHeld(at, atAs, atLayer, standalones, problems);
            }
            switch (at.style()) {
                case StylingRes.CssContent<?, ?, ?, ?> c -> at = null;
                case StylingRes.Delegation<?, ?, ?, ?> d -> at = d.to();
                case StylingRes.RootDelegation<?, ?, ?, ?> r -> { at = r.to(); atAs = atAs.alone(); }
                case StylingRes.ParentDelegation<?, ?, ?, ?, ?> p -> {
                    at = p.to();
                    atLayer = atLayer instanceof Derives<?, ?> d ? d.base() : atLayer;
                }
                case null -> { problems.add(type.getName() + " gives nothing: CSS of its own, or a delegation"); at = null; }
            }
        }
    }

    /** An implementation of a component standalone, in a layer: the one the layer's standalone of the component wears. */
    private static void standaloneHeld(CastingStylePerRole<?, ?, ?, ?> impl, Casting as, DesignStyle<?> layer,
                                       Map<DesignStyle<?>, Map<Component<?>, Target<?, ?, ?, ?>>> standalones, List<String> problems) {
        Target<?, ?, ?, ?> entry = standalones.getOrDefault(layer, Map.of()).get(as.cast());
        if (entry == null) {
            problems.add(impl.getClass().getName() + " implements " + as.cast().token() + " standalone in " + layer.token()
                    + ", which gives it no standalone: a component has one standalone a style, listed by its module");
            return;
        }
        CastingStylePerRole<?, ?, ?, ?> one = switch (entry) {
            case Target.Impl<?, ?, ?, ?> i -> i.impl();
            case Target.Declared<?, ?, ?, ?> d -> d.declaration().own();
        };
        if (one.getClass() != impl.getClass())
            problems.add(impl.getClass().getName() + " implements " + as.cast().token() + " standalone in " + layer.token() + ", and its standalone is "
                    + one.getClass().getName() + ": a component has one standalone implementation a style");
    }

    private static void record(Class<?> type, List<String> problems) {
        if (!type.isRecord() || type.getRecordComponents().length > 0)
            problems.add(type.getName() + " is no stateless record: a declaration or an implementation is a value, a record of no components");
    }

    private static String name(Object module) { return module.getClass().getSimpleName(); }

    private static void refuse(List<String> problems) {
        throw new IllegalArgumentException("the typed spec is refused:\n  " + String.join("\n  ", problems));
    }
}
