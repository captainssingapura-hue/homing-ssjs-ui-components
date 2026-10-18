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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * The instance's styles in the typed spec stack (RFC 0067, D53), as {@link ReadSpec} held them: the components taxonomy,
 * the base layer, and each style's standalones. What an element wears in a style is found by walking: the top-level
 * component's standalone - the style's, else the nearest below's - then down the element's slots, each through the
 * declaration that holds it: a same-role spec to its target, a standalone spec to the part's standalone in the style
 * worn; then the implementation reached, its delegations followed to the implementation with CSS of its own. Its content
 * is the registered resolver's to find.
 *
 * @param taxonomy    the base modules' components, their parts with them
 * @param base        the base layer
 * @param top         every top-level component, in the base modules' order
 * @param styles      the base layer, then each style in the order registered
 * @param standalones each style's standalones, by component
 * @param resolver    what finds an implementation's CSS content, registered explicitly, apart from any content
 */
public record SpecRegistry(Taxonomy taxonomy, DesignStyle<?> base, List<Component<?>> top, List<DesignStyle<?>> styles,
                           Map<DesignStyle<?>, Map<Component<?>, Target<?, ?, ?, ?>>> standalones, CssResolver resolver) implements ValueObject {

    public SpecRegistry {
        java.util.Objects.requireNonNull(resolver, "SpecRegistry.resolver");
        top = List.copyOf(top);
        styles = List.copyOf(styles);
        var copy = new LinkedHashMap<DesignStyle<?>, Map<Component<?>, Target<?, ?, ?, ?>>>();
        standalones.forEach((s, m) -> copy.put(s, Collections.unmodifiableMap(new LinkedHashMap<>(m))));
        standalones = Collections.unmodifiableMap(copy);
    }

    /** The style a style is written over, if it is. */
    public Optional<DesignStyle<?>> parent(DesignStyle<?> style) {
        return style instanceof Derives<?, ?> d ? Optional.of(d.base()) : Optional.empty();
    }

    /** The layer whose standalone a component wears in a style: the style, if it gives one, else the nearest below that does. */
    public DesignStyle<?> standaloneLayer(DesignStyle<?> style, Component<?> component) {
        for (Optional<DesignStyle<?>> at = Optional.of(style); at.isPresent(); at = parent(at.get()))
            if (standalones.getOrDefault(at.get(), Map.of()).containsKey(component)) return at.get();
        throw new IllegalArgumentException(component.token() + " has no standalone in " + style.token() + " nor below it");
    }

    /** A component's standalone in a style: the style's own, else the nearest below's. */
    public Target<?, ?, ?, ?> standalone(DesignStyle<?> style, Component<?> component) {
        return standalones.get(standaloneLayer(style, component)).get(component);
    }

    /** What an element's path reaches in a style, before any delegation: its component's implementation in its casting. */
    public CastingImplementation<?, ?, ?, ?> impl(DesignStyle<?> style, ElementPath element) {
        Target<?, ?, ?, ?> at = standalone(style, element.top());
        for (Casting slot : element.parts()) {
            if (!(at instanceof Target.Declared<?, ?, ?, ?> d))
                throw new IllegalArgumentException(element.token() + ": a leaf has no slot " + slot.token());
            PartSpec<?, ?, ?> p = d.declaration().parts().of(slot).orElseThrow(() -> new IllegalArgumentException(
                    d.declaration().getClass().getName() + " points no " + slot.token() + ", on the way to " + element.token()));
            at = switch (p) {
                case PartSpec.SameRole<?, ?, ?, ?, ?> s -> s.to();
                case PartSpec.Standalone<?, ?, ?, ?, ?> s -> standalone(style, s.part());
            };
        }
        return switch (at) {
            case Target.Impl<?, ?, ?, ?> i -> i.impl();
            case Target.Declared<?, ?, ?, ?> d -> d.declaration().own();
        };
    }

    /** The implementation with CSS of its own an implementation reaches, its delegations followed. */
    public CssImplementation<?, ?, ?, ?> css(CastingImplementation<?, ?, ?, ?> impl) {
        CastingImplementation<?, ?, ?, ?> at = impl;
        var passed = new HashSet<Class<?>>();
        while (true) {
            if (!passed.add(at.getClass())) throw new IllegalArgumentException(impl.getClass().getName() + " delegates round");
            switch (at) {
                case CssImplementation<?, ?, ?, ?> c -> { return c; }
                case Delegation<?, ?, ?, ?> d -> at = d.to();
            }
        }
    }

    /** The implementation with CSS of its own an element wears in a style. */
    public CssImplementation<?, ?, ?, ?> wears(DesignStyle<?> style, ElementPath element) { return worn(style, element).impl(); }

    /**
     * What an element wears in a style, with the layer it is of - found by walking, never off its type.
     *
     * @param layer the style the implementation is of: the style worn, or one below
     * @param impl  the implementation with CSS of its own
     */
    public record Worn(DesignStyle<?> layer, CssImplementation<?, ?, ?, ?> impl) implements ValueObject {}

    /** What an element wears in a style, and its layer: where its trace ends. */
    public Worn worn(DesignStyle<?> style, ElementPath element) {
        Trace.CssStep end = trace(style, element).end();
        return new Worn(end.layer(), end.impl());
    }

    /** How an element resolves in a style, step by step, to the implementation with CSS of its own. */
    public Trace trace(DesignStyle<?> style, ElementPath element) {
        var steps = new ArrayList<Trace.Step>();
        DesignStyle<?> layer = standaloneLayer(style, element.top());
        Target<?, ?, ?, ?> at = standalones.get(layer).get(element.top());
        Casting as = new Casting(element.top());
        Trace.Via via = Trace.Via.STANDALONE;
        for (Casting slot : element.parts()) {
            if (!(at instanceof Target.Declared<?, ?, ?, ?> d))
                throw new IllegalArgumentException(element.token() + ": a leaf has no slot " + slot.token());
            steps.add(new Trace.DeclarationStep(via, layer, as, d.declaration()));
            PartSpec<?, ?, ?> p = d.declaration().parts().of(slot).orElseThrow(() -> new IllegalArgumentException(
                    d.declaration().getClass().getName() + " points no " + slot.token() + ", on the way to " + element.token()));
            switch (p) {
                case PartSpec.SameRole<?, ?, ?, ?, ?> s -> { at = s.to(); as = s.casting(); via = Trace.Via.SAME_ROLE; }
                case PartSpec.Standalone<?, ?, ?, ?, ?> s -> {
                    layer = standaloneLayer(style, s.part());
                    at = standalones.get(layer).get(s.part());
                    as = new Casting(s.part());
                    via = Trace.Via.PLACED;
                }
            }
        }
        CastingImplementation<?, ?, ?, ?> impl;
        switch (at) {
            case Target.Impl<?, ?, ?, ?> i -> impl = i.impl();
            case Target.Declared<?, ?, ?, ?> d -> {
                steps.add(new Trace.DeclarationStep(via, layer, as, d.declaration()));
                impl = d.declaration().own();
                via = Trace.Via.OWN;
            }
        }
        var passed = new HashSet<Class<?>>();
        while (true) {
            if (!passed.add(impl.getClass())) throw new IllegalArgumentException(element.token() + " delegates round");
            switch (impl) {
                case CssImplementation<?, ?, ?, ?> c -> {
                    steps.add(new Trace.CssStep(via, layer, as, c));
                    return new Trace(style, element, steps);
                }
                case Delegation<?, ?, ?, ?> d -> {
                    steps.add(new Trace.DelegationStep(via, layer, as, d));
                    impl = d.to();
                    switch (d) {
                        case SameRoleDelegation<?, ?, ?, ?> s -> via = Trace.Via.DELEGATION;
                        case RootDelegation<?, ?, ?, ?> r -> { as = as.alone(); via = Trace.Via.ROOT_DELEGATION; }
                        case ParentDelegation<?, ?, ?, ?, ?> q -> { layer = parent(layer).orElseThrow(); via = Trace.Via.PARENT_DELEGATION; }
                    }
                }
            }
        }
    }

    /** What the registered resolver finds of an implementation's CSS content. */
    public CssResolution content(CssImplementation<?, ?, ?, ?> impl) { return resolver.resolve(impl); }

    /** What the registered resolver finds of the CSS content an element wears in a style. */
    public CssResolution content(DesignStyle<?> style, ElementPath element) { return content(wears(style, element)); }

    /** Every implementation with CSS of its own an element wears, in some style: each once, in the order first worn. */
    public List<CssImplementation<?, ?, ?, ?>> cssImplementations() {
        return cssEnds().stream().<CssImplementation<?, ?, ?, ?>>map(Trace.CssStep::impl).toList();
    }

    /**
     * Where every trace ends, each implementation with CSS of its own once, in the order first worn: with the layer it is
     * of and the casting it is reached as - one casting an implementation, as the registry was read.
     */
    public List<Trace.CssStep> cssEnds() {
        var out = new LinkedHashMap<Class<?>, Trace.CssStep>();
        for (DesignStyle<?> s : styles) for (Trace.CssStep end : cssEnds(s)) out.putIfAbsent(end.impl().getClass(), end);
        return List.copyOf(out.values());
    }

    /** Where every trace in a style ends: each implementation with CSS of its own an element wears in it, once, in the elements' order. */
    public List<Trace.CssStep> cssEnds(DesignStyle<?> style) {
        var out = new LinkedHashMap<Class<?>, Trace.CssStep>();
        for (ElementPath e : elements()) {
            Trace.CssStep end = trace(style, e).end();
            out.putIfAbsent(end.impl().getClass(), end);
        }
        return List.copyOf(out.values());
    }

    /** Every element: each top-level component, and every slot down from it - placed parts too - a part met again on its path not walked twice. */
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
