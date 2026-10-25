package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.semantic.Validation;
import hue.captains.singapura.js.homing.design.semantic.Validations;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A rule the classpath resolver's prefix is held to (RFC 0067, D53): a function of the prefix, saying what it finds. The
 * resolver is made only when every rule finds nothing ({@link ClasspathCssResolver}); the rules are applied apart, every
 * problem of every one kept ({@link Validation}).
 */
public interface PrefixRule extends StatelessFunctionalObject {

    /** What the rule finds of the prefix; nothing of a prefix not given, which {@link Given} finds. */
    Validation check(String prefix);

    /** The rules a resolver's prefix is held to, applied each apart. */
    List<PrefixRule> RULES = List.of(new Given(), new FromTheRoot(), new ADirectory(), new ForwardSlashes(), new NamedSegments());

    /** A prefix is given. */
    record Given() implements PrefixRule {
        @Override public Validation check(String prefix) {
            return Validations.INSTANCE.of(prefix == null ? List.of("no prefix is given") : List.of());
        }
    }

    /** It is from the classpath's root, as a resource name is: no leading slash. */
    record FromTheRoot() implements PrefixRule {
        @Override public Validation check(String prefix) {
            return Validations.INSTANCE.of(prefix != null && prefix.startsWith("/")
                    ? List.of("'" + prefix + "' starts with a slash: a resource name is from the classpath's root, with none") : List.of());
        }
    }

    /** It names a directory: it ends with a slash. */
    record ADirectory() implements PrefixRule {
        @Override public Validation check(String prefix) {
            return Validations.INSTANCE.of(prefix != null && !prefix.endsWith("/")
                    ? List.of("'" + prefix + "' does not end with a slash: the prefix is a directory, the content under it") : List.of());
        }
    }

    /** Its separators are forward slashes, as a resource name's are. */
    record ForwardSlashes() implements PrefixRule {
        @Override public Validation check(String prefix) {
            return Validations.INSTANCE.of(prefix != null && prefix.contains("\\")
                    ? List.of("'" + prefix + "' holds a backslash: a resource name's separator is a forward slash") : List.of());
        }
    }

    /** Each directory in it is named: none empty, none '.' or '..'. */
    record NamedSegments() implements PrefixRule {
        @Override public Validation check(String prefix) {
            var problems = new ArrayList<String>();
            if (prefix != null && !prefix.isEmpty()) {
                String inner = prefix.startsWith("/") ? prefix.substring(1) : prefix;
                inner = inner.endsWith("/") ? inner.substring(0, inner.length() - 1) : inner;
                for (String segment : inner.split("/", -1))
                    if (segment.isEmpty() || segment.equals(".") || segment.equals(".."))
                        problems.add("'" + prefix + "' holds a directory '" + segment + "': each directory is named");
            }
            return Validations.INSTANCE.of(problems);
        }
    }
}
