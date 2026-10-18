package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.List;
import java.util.Objects;

/**
 * How an element resolves in a style (RFC 0067, D53), step by step: from the top-level component's standalone, through
 * the declarations its slots are held in, to the implementation it wears, then along its delegations to the
 * implementation with CSS of its own - the placeholder whose content a resolver finds.
 *
 * @param style   the style worn
 * @param element the element
 * @param steps   each node reached, in order, with how it was reached; the last an implementation giving a placeholder
 */
public record Trace(DesignStyle<?> style, ElementPath element, List<Step> steps) implements ValueObject {

    public Trace {
        Objects.requireNonNull(style, "Trace.style");
        Objects.requireNonNull(element, "Trace.element");
        steps = List.copyOf(steps);
        if (steps.isEmpty() || !(steps.getLast() instanceof ImplStep last) || !last.placeholder())
            throw new IllegalArgumentException(element.token() + " in " + style.token() + ": a trace ends at an implementation giving a placeholder");
    }

    /** How a step was reached. */
    public enum Via {
        /** The top-level component's standalone, the style's or the layer below's. */
        STANDALONE,
        /** A slot in a role with a look of its own: its spec's target. */
        SAME_ROLE,
        /** A slot whose role only places its part: the part's standalone in the style worn. */
        PLACED,
        /** A declaration's own implementation. */
        OWN,
        /** A delegation to another implementation of the same role, in the same style. */
        DELEGATION,
        /** A delegation from a role's implementation to its component's standalone. */
        ROOT_DELEGATION,
        /** A delegation to the same role in the style written under. */
        PARENT_DELEGATION
    }

    /** One node reached. */
    public sealed interface Step extends ValueObject permits DeclarationStep, ImplStep {

        /** How it was reached. */
        Via via();

        /** The style it is of. */
        DesignStyle<?> layer();

        /** The casting it is of. */
        Casting casting();
    }

    /** A declaration reached. */
    public record DeclarationStep(Via via, DesignStyle<?> layer, Casting casting, CastingDeclaration<?, ?, ?, ?> declaration) implements Step {}

    /** An implementation reached. */
    public record ImplStep(Via via, DesignStyle<?> layer, Casting casting, CastingStylePerRole<?, ?, ?, ?> impl) implements Step {

        /** Whether it gives a placeholder: CSS of its own, its content apart. */
        public boolean placeholder() { return impl.style() instanceof StylingRes.CssPlaceHolder<?, ?, ?, ?>; }
    }

    /** The implementation it ends at, giving a placeholder. */
    public ImplStep end() { return (ImplStep) steps.getLast(); }
}
