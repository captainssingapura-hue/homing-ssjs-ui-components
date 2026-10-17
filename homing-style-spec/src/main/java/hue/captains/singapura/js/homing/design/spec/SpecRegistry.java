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
 * worn; then the implementation reached, its delegations followed to the implementation with CSS of its own.
 *
 * @param taxonomy    the base modules' components, their parts with them
 * @param base        the base layer
 * @param top         every top-level component, in the base modules' order
 * @param styles      the base layer, then each style in the order registered
 * @param standalones each style's standalones, by component
 */
public record SpecRegistry(Taxonomy taxonomy, DesignStyle<?> base, List<Component<?>> top, List<DesignStyle<?>> styles,
                           Map<DesignStyle<?>, Map<Component<?>, Target<?, ?, ?, ?>>> standalones) implements ValueObject {

    public SpecRegistry {
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
    public CastingStylePerRole<?, ?, ?, ?> impl(DesignStyle<?> style, ElementPath element) {
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
    public CastingStylePerRole<?, ?, ?, ?> css(CastingStylePerRole<?, ?, ?, ?> impl) {
        CastingStylePerRole<?, ?, ?, ?> at = impl;
        var passed = new HashSet<Class<?>>();
        while (true) {
            if (!passed.add(at.getClass())) throw new IllegalArgumentException(impl.getClass().getName() + " delegates round");
            switch (at.style()) {
                case StylingRes.CssContent<?, ?, ?, ?> c -> { return at; }
                case StylingRes.Delegation<?, ?, ?, ?> d -> at = d.to();
                case StylingRes.RootDelegation<?, ?, ?, ?> r -> at = r.to();
                case StylingRes.ParentDelegation<?, ?, ?, ?, ?> p -> at = p.to();
            }
        }
    }

    /** The implementation with CSS of its own an element wears in a style. */
    public CastingStylePerRole<?, ?, ?, ?> wears(DesignStyle<?> style, ElementPath element) { return css(impl(style, element)); }

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
