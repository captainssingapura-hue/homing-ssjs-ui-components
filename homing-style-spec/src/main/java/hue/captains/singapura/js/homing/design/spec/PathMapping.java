package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A style's flat mapping for the page's css manager (RFC 0067, D53): every path a page's code may hold - the top-level
 * component, then each slot down to the element, so that two owners declaring a part differently hold two paths - at
 * the real class of the implementation with CSS it wears in the style. Built in Java, whole: the css manager resolves
 * nothing, and a style switch swaps the classes of the paths whose class differs.
 *
 * @param style   the style's token
 * @param classes each path's real class, in the page's order
 */
public record PathMapping(String style, Map<String, String> classes) implements ValueObject {

    public PathMapping {
        Objects.requireNonNull(style, "PathMapping.style");
        classes = Collections.unmodifiableMap(new LinkedHashMap<>(classes));
    }

    /** The paths whose class differs in another style's mapping: what a switch to it re-classes. */
    public List<String> changedIn(PathMapping next) {
        if (!classes.keySet().equals(next.classes().keySet()))
            throw new IllegalArgumentException(style + " and " + next.style() + " map other paths: every mapping says every path");
        var out = new ArrayList<String>();
        classes.forEach((h, c) -> { if (!c.equals(next.classes().get(h))) out.add(h); });
        return List.copyOf(out);
    }

    /** Every real class it maps to, each once. */
    public List<String> realClasses() { return List.copyOf(new LinkedHashSet<>(classes.values())); }

    /** The module the page loads for the style: one frozen {@code STYLE_CLASSES}, plain data. */
    public String script() {
        var lines = new ArrayList<String>();
        classes.forEach((h, c) -> lines.add("        " + quoted(h) + ": " + quoted(c)));
        return "// Generated (RFC 0067, D53): " + style + "'s classes - every path a page's code may hold, its top-level component then\n"
                + "// each slot down to the element, at the real class of the implementation with CSS it wears in " + style + ".\n"
                + "const STYLE_CLASSES = Object.freeze({\n    style: " + quoted(style) + ",\n    paths: Object.freeze({\n"
                + String.join(",\n", lines) + "\n    })\n});\n";
    }

    private static String quoted(String s) { return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\""; }
}
