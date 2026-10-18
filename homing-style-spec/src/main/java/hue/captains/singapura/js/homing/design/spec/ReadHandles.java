package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.tree.NodeName;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.LinkedHashMap;

/**
 * A style's flat mapping ({@link HandleMapping}), read off the registry: each element's handle at the real class of the
 * implementation with CSS it wears - {@code styled-<layer>___<implementation>}, the layer the style worn or one below,
 * the name the implementation's own: a class each CSS has once, wherever it is worn.
 */
public record ReadHandles() implements StatelessFunctionalObject {

    public static final ReadHandles INSTANCE = new ReadHandles();

    /** A selectable style's mapping; refused for the base layer, which no page wears on its own. */
    public HandleMapping read(SpecRegistry spec, DesignStyle<?> style) {
        if (!spec.selectable().contains(style))
            throw new IllegalArgumentException(style.token() + " is no style a page wears: the base layer stands alone nowhere - "
                    + "it is served only as the rules a style leaves to it");
        var classes = new LinkedHashMap<String, String>();
        for (ElementPath e : spec.elements()) {
            SpecRegistry.Worn w = spec.worn(style, e);
            String before = classes.putIfAbsent(e.handle(), realClass(w));
            if (before != null) throw new IllegalStateException(e.handle() + " is two elements' handle");
        }
        return new HandleMapping(style.token(), classes);
    }

    /** The real class of what is worn: its layer, then its implementation's name - {@code styled-editorial___plain-button}. */
    public String realClass(SpecRegistry.Worn worn) {
        return "styled-" + worn.layer().token() + "___" + NodeName.ofType(worn.impl().getClass(), "").value();
    }
}
