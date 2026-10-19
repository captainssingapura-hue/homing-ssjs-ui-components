package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * The typed spec stack, read and held (RFC 0067, D53, D54): the taxonomy, the base layer and the styles written over it,
 * each layer's assignment of every component it assigns, and the resolver of their content. What an element wears in a
 * style is its casting's, whoever owns it and wherever it is placed: the implementation the style's assignment of its
 * component gives the casting, else the nearest layer's below that assigns the component - the base assigning every one.
 *
 * @param taxonomy    the components, read from the base modules' component modules
 * @param base        the base layer
 * @param top         the top-level components: those the base modules' component modules list
 * @param styles      every layer, the base first, then each style in the order registered
 * @param assignments each layer's assignments, by component
 * @param resolver    what finds an implementation's content
 */
public record SpecRegistry(Taxonomy taxonomy, DesignStyle<?> base, List<Component<?>> top, List<DesignStyle<?>> styles,
                           Map<DesignStyle<?>, Map<Component<?>, ComponentAssignment<?, ?, ?>>> assignments, CssResolver resolver)
        implements ValueObject {

    public SpecRegistry {
        Objects.requireNonNull(taxonomy, "SpecRegistry.taxonomy");
        Objects.requireNonNull(base, "SpecRegistry.base");
        Objects.requireNonNull(resolver, "SpecRegistry.resolver");
        top = List.copyOf(top);
        styles = List.copyOf(styles);
        var copy = new LinkedHashMap<DesignStyle<?>, Map<Component<?>, ComponentAssignment<?, ?, ?>>>();
        assignments.forEach((s, m) -> copy.put(s, Collections.unmodifiableMap(new LinkedHashMap<>(m))));
        assignments = Collections.unmodifiableMap(copy);
    }

    /** The style a style is written over, if it is. */
    public Optional<DesignStyle<?>> parent(DesignStyle<?> style) {
        return style instanceof Derives<?, ?> d ? Optional.of(d.base()) : Optional.empty();
    }

    /** The layer whose assignment a component wears in a style: the style, if it assigns it, else the nearest below that does. */
    public DesignStyle<?> layer(DesignStyle<?> style, Component<?> component) {
        for (Optional<DesignStyle<?>> at = Optional.of(style); at.isPresent(); at = parent(at.get()))
            if (assignments.getOrDefault(at.get(), Map.of()).containsKey(component)) return at.get();
        throw new IllegalArgumentException(component.token() + " is assigned in " + style.token() + " nor below it");
    }

    /** A component's assignment in a style: the style's own, else the nearest below's. */
    public ComponentAssignment<?, ?, ?> assignment(DesignStyle<?> style, Component<?> component) {
        return assignments.get(layer(style, component)).get(component);
    }

    /**
     * What a casting wears in a style.
     *
     * @param layer     the layer whose assignment gives it: the style worn, or one below
     * @param component the component it draws
     * @param impl      the implementation
     */
    public record Worn(DesignStyle<?> layer, Component<?> component, CssImplementation<?, ?, ?> impl) implements ValueObject {
        public Worn {
            Objects.requireNonNull(layer, "Worn.layer");
            Objects.requireNonNull(component, "Worn.component");
            Objects.requireNonNull(impl, "Worn.impl");
        }
    }

    /** How a casting resolves in a style: the layers passed, and what it wears. A role that only places its part looks as the part on its own. */
    public Trace trace(DesignStyle<?> style, Casting casting) {
        Casting k = casting.standalone() || !casting.functional() ? casting : casting.alone();
        Component<?> c = k.cast();
        var passed = new ArrayList<DesignStyle<?>>();
        for (Optional<DesignStyle<?>> at = Optional.of(style); at.isPresent(); at = parent(at.get())) {
            ComponentAssignment<?, ?, ?> a = assignments.getOrDefault(at.get(), Map.of()).get(c);
            if (a == null) { passed.add(at.get()); continue; }
            CssImplementation<?, ?, ?> impl = a.of(k).orElseThrow(() -> new IllegalArgumentException(
                    k.token() + ": " + c.token() + " is cast in no role " + k.role().name().value() + " its assignment gives"));
            return new Trace(style, k, passed, new Worn(at.get(), c, impl));
        }
        throw new IllegalArgumentException(c.token() + " is assigned in " + style.token() + " nor below it");
    }

    /** What a casting wears in a style. */
    public Worn worn(DesignStyle<?> style, Casting casting) { return trace(style, casting).end(); }

    /** What an element wears in a style: its casting's. */
    public Worn worn(DesignStyle<?> style, ElementPath element) { return worn(style, element.casting()); }

    /** The implementation a casting wears in a style. */
    public CssImplementation<?, ?, ?> wears(DesignStyle<?> style, Casting casting) { return worn(style, casting).impl(); }

    /** The implementation an element wears in a style: its casting's. */
    public CssImplementation<?, ?, ?> wears(DesignStyle<?> style, ElementPath element) { return wears(style, element.casting()); }

    /** What the registered resolver finds of an implementation's CSS content. */
    public CssResolution content(CssImplementation<?, ?, ?> impl) { return resolver.resolve(impl); }

    /** What the registered resolver finds of the CSS content a casting wears in a style. */
    public CssResolution content(DesignStyle<?> style, Casting casting) { return content(wears(style, casting)); }

    /** What the registered resolver finds of the CSS content an element wears in a style. */
    public CssResolution content(DesignStyle<?> style, ElementPath element) { return content(wears(style, element)); }

    /**
     * Every implementation an assignment names, each once, with its layer and its component - in the layers' order, then
     * the assignments': what the layers must draw, whether or not an element of the taxonomy wears it yet.
     */
    public List<Worn> implementations() {
        var out = new LinkedHashMap<Class<?>, Worn>();
        for (DesignStyle<?> s : styles)
            for (ComponentAssignment<?, ?, ?> a : assignments.getOrDefault(s, Map.of()).values())
                for (CssImplementation<?, ?, ?> impl : a.implementations()) out.putIfAbsent(impl.getClass(), new Worn(s, a.component(), impl));
        return List.copyOf(out.values());
    }

    /** Every implementation with CSS of its own an assignment names, each once. */
    public List<CssImplementation<?, ?, ?>> cssImplementations() {
        return implementations().stream().<CssImplementation<?, ?, ?>>map(Worn::impl).toList();
    }

    /** What the castings wear in a style: each implementation once, in the castings' order. */
    public List<Worn> worn(DesignStyle<?> style) {
        var out = new LinkedHashMap<Class<?>, Worn>();
        for (Casting k : castings()) {
            Worn w = worn(style, k);
            out.putIfAbsent(w.impl().getClass(), w);
        }
        return List.copyOf(out.values());
    }

    /**
     * The styles a page may wear: every style registered over the base layer, in the order registered. The base layer is
     * never one - it stands alone nowhere, and is served only as the rules a style leaves to it, in that style's sheet.
     */
    public List<DesignStyle<?>> selectable() { return styles.stream().filter(s -> !s.equals(base)).toList(); }

    /** Every casting an element can be, each once, in the elements' order: what a style's sheet draws. */
    public List<Casting> castings() {
        var out = new LinkedHashSet<Casting>();
        for (ElementPath e : elements()) out.add(e.casting());
        return List.copyOf(out);
    }

    /**
     * Every element: each top-level component, and every slot down from it - placed parts too - a part met again on its
     * path not walked twice. Its owners and slots are the layout's; what it wears is its casting's.
     */
    public List<ElementPath> elements() {
        var out = new ArrayList<ElementPath>();
        for (Component<?> t : top) {
            var at = new ElementPath(t, List.of());
            out.add(at);
            walk(at, t, new HashSet<>(Set.of(t)), out);
        }
        return List.copyOf(out);
    }

    private void walk(ElementPath at, Component<?> component, Set<Component<?>> onPath, List<ElementPath> out) {
        for (Slots.Slot s : Slots.INSTANCE.of(taxonomy, component)) {
            ElementPath next = at.then(s.slot());
            out.add(next);
            if (onPath.add(s.part())) {
                walk(next, s.part(), onPath, out);
                onPath.remove(s.part());
            }
        }
    }
}
