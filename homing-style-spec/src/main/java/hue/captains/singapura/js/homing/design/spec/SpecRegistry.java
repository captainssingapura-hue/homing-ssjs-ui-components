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
 * the base layer, and each style's implementations of the top-level components it styles. What an element wears in a
 * style is found by walking: the top-level component's implementation - the style's, else its base layer's - then each
 * slot's spec down the element's path, then the look's delegations to the look with CSS of its own.
 *
 * @param taxonomy        the base modules' components, their parts with them
 * @param base            the base layer
 * @param top             every top-level component, in the base modules' order
 * @param styles          the base layer, then each style in the order registered
 * @param implementations each style's implementations, by top-level component
 */
public record SpecRegistry(Taxonomy taxonomy, DesignStyle<?> base, List<Component<?>> top, List<DesignStyle<?>> styles,
                           Map<DesignStyle<?>, Map<Component<?>, CastingStylePerRole<?, ?, ?, ?>>> implementations) implements ValueObject {

    public SpecRegistry {
        top = List.copyOf(top);
        styles = List.copyOf(styles);
        var copy = new LinkedHashMap<DesignStyle<?>, Map<Component<?>, CastingStylePerRole<?, ?, ?, ?>>>();
        implementations.forEach((s, m) -> copy.put(s, Collections.unmodifiableMap(new LinkedHashMap<>(m))));
        implementations = Collections.unmodifiableMap(copy);
    }

    /** The style a style is written over, if it is. */
    public Optional<DesignStyle<?>> parent(DesignStyle<?> style) {
        return style instanceof Derives<?, ?> d ? Optional.of(d.base()) : Optional.empty();
    }

    /** A top-level component's implementation in a style: the style's own, else the nearest it is written over's. */
    public CastingStylePerRole<?, ?, ?, ?> implementation(DesignStyle<?> style, Component<?> component) {
        for (Optional<DesignStyle<?>> at = Optional.of(style); at.isPresent(); at = parent(at.get())) {
            CastingStylePerRole<?, ?, ?, ?> own = implementations.getOrDefault(at.get(), Map.of()).get(component);
            if (own != null) return own;
        }
        throw new IllegalArgumentException(component.token() + " has no implementation in " + style.token() + " nor below it");
    }

    /** The look an element wears in a style, as pointed: perhaps a delegation. */
    public CastingStylePerRole<?, ?, ?, ?> look(DesignStyle<?> style, ElementPath element) {
        CastingStylePerRole<?, ?, ?, ?> at = implementation(style, element.top());
        for (Casting c : element.parts()) {
            CastingStylePerRole<?, ?, ?, ?> owner = at;
            at = owner.parts().of(c).orElseThrow(() -> new IllegalArgumentException(
                    owner.getClass().getName() + " points no " + c.token() + ", on the way to " + element.token())).look();
        }
        return at;
    }

    /** The look with CSS of its own a look reaches, its delegations followed. */
    public CastingStylePerRole<?, ?, ?, ?> css(CastingStylePerRole<?, ?, ?, ?> look) {
        CastingStylePerRole<?, ?, ?, ?> at = look;
        var passed = new HashSet<Class<?>>();
        while (true) {
            if (!passed.add(at.getClass())) throw new IllegalArgumentException(look.getClass().getName() + " delegates round");
            switch (at.style()) {
                case StylingRes.CssContent<?, ?, ?, ?> c -> { return at; }
                case StylingRes.Delegation<?, ?, ?, ?> d -> at = d.to();
                case StylingRes.RootDelegation<?, ?, ?, ?> r -> at = r.to();
                case StylingRes.ParentDelegation<?, ?, ?, ?, ?> p -> at = p.to();
            }
        }
    }

    /** The look with CSS of its own an element wears in a style. */
    public CastingStylePerRole<?, ?, ?, ?> wears(DesignStyle<?> style, ElementPath element) { return css(look(style, element)); }

    /** Every element: each top-level component, and every slot down from it, a part met again on its path not walked twice. */
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
        for (Slots.Slot s : Slots.INSTANCE.of(taxonomy, top, component)) {
            ElementPath next = at.then(s.slot());
            out.add(next);
            if (onPath.add(s.part())) {
                walk(next, s.part(), onPath, out);
                onPath.remove(s.part());
            }
        }
    }
}
