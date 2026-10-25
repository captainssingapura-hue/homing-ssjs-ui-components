package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A style's complete sheet as it is served (RFC 0071, RFC 0067 D54): one rule for each implementation a casting wears in
 * the style, its selector the classes of the castings that wear it - each casting's class in exactly one rule - its
 * content's marks filled in. Elements' classes are the same in every style, so a style is swapped by swapping its sheet,
 * and nothing on an element: what a style draws, and which casting it draws alike, is the sheet's alone.
 *
 * @param style the style's token
 * @param rules each rule, in the order its implementation is first worn
 * @param css   the whole file: its head, then every rule
 */
public record StyleSheet(String style, List<Rule> rules, String css) implements ValueObject {
    public StyleSheet {
        Objects.requireNonNull(style, "StyleSheet.style");
        rules = List.copyOf(rules);
        Objects.requireNonNull(css, "StyleSheet.css");
    }

    /**
     * One implementation's rule: the castings that wear it, and its content filled in for them.
     *
     * @param name     the implementation's name in the style, its keyframes' prefix: {@code editorial___plain-button}
     * @param end      the implementation, with the layer it is of and its component
     * @param castings the classes of the castings that wear it, in the castings' order
     * @param css      its content, the marks filled in
     */
    public record Rule(String name, SpecRegistry.Worn end, List<String> castings, String css) implements ValueObject {
        public Rule {
            Objects.requireNonNull(name, "Rule.name");
            Objects.requireNonNull(end, "Rule.end");
            castings = List.copyOf(castings);
            Objects.requireNonNull(css, "Rule.css");
        }
    }

    /** The rule a casting's class is selected by, if the sheet has one. */
    public Optional<Rule> rule(String castingClass) { return rules.stream().filter(r -> r.castings().contains(castingClass)).findFirst(); }
}
