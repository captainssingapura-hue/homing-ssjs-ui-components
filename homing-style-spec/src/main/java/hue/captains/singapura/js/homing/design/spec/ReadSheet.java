package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.Extent;
import hue.captains.singapura.js.homing.design.Growth;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.styling.CastingCss;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A style's complete sheet ({@link StyleSheet}), read off the registry as it is served: for each implementation with CSS
 * of its own an element wears in the style, its content as the registry's resolver finds it, the marks filled in for its
 * real class ({@link CastingCss#filled(String, String, String)}) - {@code $casting} the class, {@code &$word}
 * {@code <class>--<word>}, a keyframes of its own {@code <class>-<name>}. Refused, every missing content named, unless
 * every one is found: a sheet is served whole or not at all. And only for a selectable style: the base layer has no sheet
 * of its own - its rules are in each sheet that wears them.
 */
public record ReadSheet() implements StatelessFunctionalObject {

    public static final ReadSheet INSTANCE = new ReadSheet();

    public StyleSheet read(SpecRegistry spec, DesignStyle<?> style) {
        PathMapping mapping = ReadPathMapping.INSTANCE.read(spec, style);
        Map<String, Integer> wearing = new HashMap<>();
        for (String c : mapping.classes().values()) wearing.merge(c, 1, Integer::sum);
        var rules = new ArrayList<StyleSheet.Rule>();
        var missing = new ArrayList<String>();
        for (Trace.CssStep end : spec.cssEnds(style)) {
            String real = ReadPathMapping.INSTANCE.realClass(new SpecRegistry.Worn(end.layer(), end.impl()));
            switch (spec.content(end.impl())) {
                case CssResolution.Found f -> rules.add(new StyleSheet.Rule(real, end, wearing.getOrDefault(real, 0),
                        CastingCss.INSTANCE.filled(f.text(), real, real).strip()));
                case CssResolution r -> missing.add(r.resource() + ": " + r.getClass().getSimpleName());
            }
        }
        if (!missing.isEmpty())
            throw new IllegalStateException(style.token() + "'s sheet is not served: " + missing.size() + " contents not to be had\n  "
                    + String.join("\n  ", missing));
        return new StyleSheet(style.token(), rules, css(style, rules));
    }

    private static String css(DesignStyle<?> style, List<StyleSheet.Rule> rules) {
        var css = new StringBuilder();
        css.append("/* ").append(style.token()).append(" - its sheet in the typed spec stack (RFC 0067, D53): one class for each implementation\n")
           .append("   with CSS of its own an element wears in ").append(style.token()).append(", its content's marks filled in. Generated; do not edit. */\n")
           .append(Extent.PROPERTY).append('\n');
        for (Growth g : Growth.values()) css.append(g.property()).append('\n');
        for (StyleSheet.Rule r : rules) {
            Trace.CssStep end = r.end();
            css.append("\n/* ").append(r.realClass()).append(": ").append(end.layer().token()).append("'s ")
               .append(end.impl().getClass().getSimpleName()).append(", ").append(end.casting().token())
               .append(" - worn by ").append(r.paths()).append(r.paths() == 1 ? " path" : " paths").append(" */\n")
               .append(r.css()).append('\n');
        }
        return css.toString();
    }
}
