package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.List;
import java.util.Objects;

/**
 * How a casting resolves in a style (RFC 0067, D54): the layers asked, from the style worn down, until the first that
 * assigns its component - each one before it leaving the component, whole, to the layer it is written over - and the
 * implementation that layer's assignment gives the casting. Its owner and its slot play no part: they are the layout's.
 *
 * @param style   the style worn
 * @param casting the casting: a component on its own, or in a role with a look of its own
 * @param passed  the layers asked that do not assign its component, in the order asked
 * @param end     what it wears: the implementation, of the layer whose assignment gives it
 */
public record Trace(DesignStyle<?> style, Casting casting, List<DesignStyle<?>> passed, SpecRegistry.Worn end) implements ValueObject {
    public Trace {
        Objects.requireNonNull(style, "Trace.style");
        Objects.requireNonNull(casting, "Trace.casting");
        passed = List.copyOf(passed);
        Objects.requireNonNull(end, "Trace.end");
        if (passed.contains(end.layer())) throw new IllegalArgumentException(casting.token() + " in " + style.token() + ": a layer passed gives nothing");
    }
}
