package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A style's complete sheet as it is served (RFC 0067, D53): one rule for each implementation with CSS of its own an
 * element wears in the style, under its real class, its content's marks filled in - each once, however many elements
 * wear it. Its pair is the style's {@link PathMapping}: the page's css manager puts each path's class on the element,
 * and this sheet draws the class.
 *
 * @param style the style's token
 * @param rules each rule, in the order the elements first wear it
 * @param css   the whole file: its head, then every rule
 */
public record StyleSheet(String style, List<Rule> rules, String css) implements ValueObject {

    public StyleSheet {
        Objects.requireNonNull(style, "StyleSheet.style");
        rules = List.copyOf(rules);
        Objects.requireNonNull(css, "StyleSheet.css");
    }

    /**
     * One implementation's rule as served.
     *
     * @param realClass the class it is served under: {@code styled-<layer>___<implementation>}
     * @param end       the implementation, with the layer it is of and the casting it is reached as
     * @param paths   how many of the style's paths wear it
     * @param css       its content, the marks filled in for its class
     */
    public record Rule(String realClass, Trace.CssStep end, int paths, String css) implements ValueObject {
        public Rule {
            Objects.requireNonNull(realClass, "Rule.realClass");
            Objects.requireNonNull(end, "Rule.end");
            Objects.requireNonNull(css, "Rule.css");
        }
    }

    /** The rule of a real class, if the sheet has one. */
    public Optional<Rule> rule(String realClass) { return rules.stream().filter(r -> r.realClass().equals(realClass)).findFirst(); }
}
