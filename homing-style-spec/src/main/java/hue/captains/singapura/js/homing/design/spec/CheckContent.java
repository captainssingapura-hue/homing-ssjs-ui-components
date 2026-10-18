package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.styling.CastingCss;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Build time only (RFC 0067, D53): a jar of content held to a registry. Every implementation with CSS of its own the
 * registry holds has its content where the registry's resolver looks - there, read, UTF-8 - and its marks in their forms
 * ({@code $casting { … }}, {@code &$word}, {@code @keyframes $name}; no class named); and nothing the jar ships is left
 * over - each file it ships is the content of one of them. Run in the content jar's own test; two copies on a classpath
 * put together are {@link DuplicateContent}'s to find.
 */
public record CheckContent() implements StatelessFunctionalObject {

    public static final CheckContent INSTANCE = new CheckContent();

    /** The resource names every implementation with CSS of its own the registry holds has its content at. */
    public Set<String> expected(SpecRegistry spec) {
        ClasspathCssResolver classpath = classpath(spec);
        var out = new LinkedHashSet<String>();
        for (CssImplementation<?, ?, ?, ?> impl : spec.cssImplementations()) out.add(classpath.resource(impl.getClass()));
        return out;
    }

    /** Every implementation's content: found, and its marks in form - every problem, each naming its file. */
    public List<String> content(SpecRegistry spec) {
        classpath(spec);
        var problems = new ArrayList<String>();
        for (CssImplementation<?, ?, ?, ?> impl : spec.cssImplementations()) {
            switch (spec.content(impl)) {
                case CssResolution.Found f -> problems.addAll(CastingCss.INSTANCE.marks(f.resource(), f.text()).problems());
                case CssResolution.Nothing n -> problems.add(n.resource() + ": nothing is there - an implementation with CSS of its own has its content");
                case CssResolution.Unreadable u -> problems.add(u.resource() + ": there at " + u.where() + ", and not to be read: " + u.reason());
                case CssResolution.NotUtf8 b -> problems.add(b.resource() + ": there at " + b.where() + ", and not UTF-8");
            }
        }
        return List.copyOf(problems);
    }

    /** What a jar ships that is no implementation's content: each file, by its resource name, left over. */
    public List<String> leftOver(SpecRegistry spec, Collection<String> shipped) {
        Set<String> expected = expected(spec);
        var problems = new ArrayList<String>();
        for (String s : shipped)
            if (!expected.contains(s)) problems.add(s + ": no implementation with CSS of its own has its content here - a jar of content ships only content");
        return List.copyOf(problems);
    }

    private static ClasspathCssResolver classpath(SpecRegistry spec) {
        if (!(spec.resolver() instanceof ClasspathCssResolver classpath))
            throw new IllegalArgumentException("the registry's resolver, " + spec.resolver() + ", does not look on the classpath");
        return classpath;
    }
}
