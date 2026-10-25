package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.Extent;
import hue.captains.singapura.js.homing.design.Growth;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.tree.NodeName;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A style's sheet ({@link StyleSheet}), read off the registry as it is served (RFC 0071, RFC 0067 D54): for each
 * implementation a casting wears in the style, its content as the registry's resolver finds it, the marks filled in -
 * {@code $casting} the selector of the castings' classes that wear it ({@link StyleClasses}), {@code $casting__name} the
 * same for a room of the component's own - each casting's class and the room's name - {@code &$word} the state's
 * class, a keyframes of its own named after the implementation. Refused, every missing content named, unless every one is
 * found: a sheet is served whole or not at all. And only for a selectable style: the base layer has no sheet of its own -
 * its rules are in each sheet that wears them. A casting the document wears ({@link
 * hue.captains.singapura.js.homing.component.taxonomy.WornByTheDocument}) is ruled on {@code :root}, the document's own
 * element, under no class (owner, 24 Oct): its implementation is shared with no element's casting.
 */
public record ReadSheet() implements StatelessFunctionalObject {

    public static final ReadSheet INSTANCE = new ReadSheet();

    private static final String CASTING = "$casting";
    private static final Pattern ROOM = Pattern.compile("\\$casting__([a-z][a-z0-9]*(?:-[a-z0-9]+)*)");
    private static final Pattern STATE = Pattern.compile("&\\$([a-z][a-z0-9-]*)");
    private static final Pattern KEYFRAMES = Pattern.compile("@keyframes\\s+\\$([a-z][a-z0-9-]*)");
    private static final Pattern USE = Pattern.compile("\\$([a-z][a-z0-9-]*)");

    public StyleSheet read(SpecRegistry spec, DesignStyle<?> style) {
        if (!spec.selectable().contains(style))
            throw new IllegalArgumentException(style.token() + " is no style a page wears: the base layer stands alone nowhere");
        Map<Class<?>, List<String>> wearing = new LinkedHashMap<>();
        var onTheRoot = new java.util.HashSet<Class<?>>();   // the implementations a casting the document wears
        for (Casting k : spec.castings()) {
            Class<?> impl = spec.wears(style, k).getClass();
            wearing.computeIfAbsent(impl, x -> new ArrayList<>()).add(StyleClasses.INSTANCE.casting(k));
            if (k.component() instanceof Component<?> c && spec.taxonomy().wornByTheDocument(c)) onTheRoot.add(impl);
        }
        var rules = new ArrayList<StyleSheet.Rule>();
        var missing = new ArrayList<String>();
        for (SpecRegistry.Worn end : spec.worn(style)) {
            String name = name(end);
            List<String> castings = wearing.getOrDefault(end.impl().getClass(), List.of());
            boolean root = onTheRoot.contains(end.impl().getClass());
            if (root && castings.size() > 1)
                throw new IllegalStateException(style.token() + ": " + name + " is worn by a casting the document wears and by another - "
                        + String.join(", ", castings) + ": the document's rule is on :root, shared with no element's casting");
            switch (spec.content(end.impl())) {
                case CssResolution.Found f -> rules.add(new StyleSheet.Rule(name, end, castings, filled(f.text(), root ? ":root" : selector(castings), castings, name).strip()));
                case CssResolution r -> missing.add(r.resource() + ": " + r.getClass().getSimpleName());
            }
        }
        if (!missing.isEmpty())
            throw new IllegalStateException(style.token() + "'s sheet is not served: " + missing.size() + " contents not to be had\n  " + String.join("\n  ", missing));
        return new StyleSheet(style.token(), rules, css(style, rules));
    }

    /** An implementation's name in a sheet, its keyframes' prefix: its layer, then its own name - {@code editorial___plain-button}. */
    public String name(SpecRegistry.Worn worn) {
        return worn.layer().token() + "___" + NodeName.ofType(worn.impl().getClass(), "").value();
    }

    /** The selector of the castings that wear a rule: the one class, or every one of them in an {@code :is()}. */
    public String selector(List<String> castings) {
        return castings.size() == 1 ? "." + castings.getFirst() : ":is(" + String.join(", ", castings.stream().map(h -> "." + h).toList()) + ")";
    }

    /**
     * A content's marks filled in for the castings that wear it: {@code $casting} their selector, {@code $casting__name}
     * the selector of their rooms of that name, {@code &$word} the state's class, {@code $name} a keyframes named after
     * the implementation.
     */
    public String filled(String css, List<String> castings, String keyframes) { return filled(css, selector(castings), castings, keyframes); }

    /** The same, {@code $casting} the selector given: the castings' classes, or {@code :root} for a casting the document wears. */
    public String filled(String css, String selector, List<String> castings, String keyframes) {
        String out = KEYFRAMES.matcher(css).replaceAll(m -> "@keyframes " + Matcher.quoteReplacement(keyframes + "-" + m.group(1)));
        out = STATE.matcher(out).replaceAll(m -> Matcher.quoteReplacement("&." + StyleClasses.INSTANCE.state(m.group(1))));
        out = ROOM.matcher(out).replaceAll(m -> Matcher.quoteReplacement(selector(castings.stream().map(c -> c + "__" + m.group(1)).toList())));
        out = out.replace(CASTING, selector);
        out = USE.matcher(out).replaceAll(m -> Matcher.quoteReplacement(keyframes + "-" + m.group(1)));
        return out;
    }

    private static String css(DesignStyle<?> style, List<StyleSheet.Rule> rules) {
        var css = new StringBuilder();
        css.append("/* ").append(style.token()).append(" - its sheet (RFC 0071, RFC 0067 D54): one rule for each implementation a casting wears in ")
           .append(style.token()).append(",\n   its selector the castings that wear it. Generated; do not edit. */\n")
           .append(Extent.PROPERTY).append('\n');
        for (Growth g : Growth.values()) css.append(g.property()).append('\n');
        for (StyleSheet.Rule r : rules) {
            css.append("\n/* ").append(r.name()).append(": ").append(r.end().layer().token()).append("'s ").append(r.end().impl().getClass().getSimpleName())
               .append(" - worn by ").append(r.castings().size()).append(r.castings().size() == 1 ? " casting" : " castings").append(" */\n")
               .append(r.css()).append('\n');
        }
        return css.toString();
    }
}
