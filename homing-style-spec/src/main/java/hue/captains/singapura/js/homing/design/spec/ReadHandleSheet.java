package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.Extent;
import hue.captains.singapura.js.homing.design.Growth;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A style's sheet keyed by handles (RFC 0071): what an element wears in a style is the sheet's to say, never the page's.
 * One rule for each implementation with CSS of its own an element wears in the style, its selector the classes of the
 * handles that wear it ({@link StyleClasses}) - each handle in exactly one rule - its content's marks filled in:
 * {@code $casting} the selector, {@code &$word} the state's class, a keyframes of its own named after the implementation.
 * Elements' classes are the same in every style, so a style is swapped by swapping its sheet, and nothing on an element.
 */
public record ReadHandleSheet() implements StatelessFunctionalObject {

    public static final ReadHandleSheet INSTANCE = new ReadHandleSheet();

    private static final String CASTING = "$casting";
    private static final Pattern STATE = Pattern.compile("&\\$([a-z][a-z0-9-]*)");
    private static final Pattern KEYFRAMES = Pattern.compile("@keyframes\\s+\\$([a-z][a-z0-9-]*)");
    private static final Pattern USE = Pattern.compile("\\$([a-z][a-z0-9-]*)");

    /**
     * A style's sheet keyed by handles.
     *
     * @param style the style's token
     * @param rules each rule, in the order its implementation is first worn
     * @param css   the whole file
     */
    public record Sheet(String style, List<Rule> rules, String css) implements ValueObject {
        public Sheet {
            Objects.requireNonNull(style, "Sheet.style");
            rules = List.copyOf(rules);
            Objects.requireNonNull(css, "Sheet.css");
        }
    }

    /**
     * One implementation's rule: the handles that wear it, and its content filled in for them.
     *
     * @param name    the implementation's name in the style, its keyframes' prefix: {@code styled-editorial___plain-button}
     * @param end     the implementation, with the layer it is of and the casting it is reached as
     * @param handles the classes of the handles that wear it, in the elements' order
     * @param css     its content, the marks filled in
     */
    public record Rule(String name, Trace.CssStep end, List<String> handles, String css) implements ValueObject {
        public Rule {
            Objects.requireNonNull(name, "Rule.name");
            Objects.requireNonNull(end, "Rule.end");
            handles = List.copyOf(handles);
            Objects.requireNonNull(css, "Rule.css");
        }
    }

    public Sheet read(SpecRegistry spec, DesignStyle<?> style) {
        if (!spec.selectable().contains(style))
            throw new IllegalArgumentException(style.token() + " is no style a page wears: the base layer stands alone nowhere");
        Map<Class<?>, List<String>> wearing = new LinkedHashMap<>();
        for (ElementPath e : spec.elements())
            wearing.computeIfAbsent(spec.wears(style, e).getClass(), k -> new ArrayList<>()).add(StyleClasses.INSTANCE.element(e));
        var rules = new ArrayList<Rule>();
        var missing = new ArrayList<String>();
        for (Trace.CssStep end : spec.cssEnds(style)) {
            String name = ReadHandles.INSTANCE.realClass(new SpecRegistry.Worn(end.layer(), end.impl()));
            List<String> handles = wearing.getOrDefault(end.impl().getClass(), List.of());
            switch (spec.content(end.impl())) {
                case CssResolution.Found f -> rules.add(new Rule(name, end, handles, filled(f.text(), selector(handles), name).strip()));
                case CssResolution r -> missing.add(r.resource() + ": " + r.getClass().getSimpleName());
            }
        }
        if (!missing.isEmpty())
            throw new IllegalStateException(style.token() + "'s sheet is not served: " + missing.size() + " contents not to be had\n  " + String.join("\n  ", missing));
        return new Sheet(style.token(), rules, css(style, rules));
    }

    /** The selector of the handles that wear a rule: the one class, or every one of them in an {@code :is()}. */
    public String selector(List<String> handles) {
        return handles.size() == 1 ? "." + handles.getFirst() : ":is(" + String.join(", ", handles.stream().map(h -> "." + h).toList()) + ")";
    }

    /** A content's marks filled in for a selector: {@code $casting} the selector, {@code &$word} the state's class, {@code $name} a keyframes named after the implementation. */
    public String filled(String css, String selector, String keyframes) {
        String out = KEYFRAMES.matcher(css).replaceAll(m -> "@keyframes " + Matcher.quoteReplacement(keyframes + "-" + m.group(1)));
        out = STATE.matcher(out).replaceAll(m -> Matcher.quoteReplacement("&." + StyleClasses.INSTANCE.state(m.group(1))));
        out = out.replace(CASTING, selector);
        out = USE.matcher(out).replaceAll(m -> Matcher.quoteReplacement(keyframes + "-" + m.group(1)));
        return out;
    }

    private static String css(DesignStyle<?> style, List<Rule> rules) {
        var css = new StringBuilder();
        css.append("/* ").append(style.token()).append(" - its sheet keyed by handles (RFC 0071): one rule for each implementation an element wears in ")
           .append(style.token()).append(",\n   its selector the handles that wear it. Generated; do not edit. */\n")
           .append(Extent.PROPERTY).append('\n');
        for (Growth g : Growth.values()) css.append(g.property()).append('\n');
        for (Rule r : rules) {
            css.append("\n/* ").append(r.name()).append(": ").append(r.end().layer().token()).append("'s ").append(r.end().impl().getClass().getSimpleName())
               .append(" - worn by ").append(r.handles().size()).append(r.handles().size() == 1 ? " handle" : " handles").append(" */\n")
               .append(r.css()).append('\n');
        }
        return css.toString();
    }
}
