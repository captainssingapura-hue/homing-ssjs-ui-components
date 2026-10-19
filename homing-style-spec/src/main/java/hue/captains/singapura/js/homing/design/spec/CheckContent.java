package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.design.semantic.ColorTree;
import hue.captains.singapura.js.homing.design.styling.CheckDeclaration;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Build time only (RFC 0067, D53, D54): a jar of content held to a registry. Every implementation an assignment names -
 * worn by an element yet or not - has its content where the registry's resolver looks - there, read, UTF-8 - held whole
 * as core holds an implementation's CSS ({@link CheckDeclaration#content}): its marks in their forms; filled in for its
 * component on its own, a casting's rule, the states being the component's whatever role it is in; its colours - those it lists, typed - the tree's, seen by its style's scheme, and imported
 * both ways (D32), its own spectra anchored at the tree's (D41). And nothing the jar ships is left over - each file it
 * ships is the content of one of them. Run in the content jar's own test; two copies on a classpath put together are
 * {@link DuplicateContent}'s to find.
 */
public record CheckContent() implements StatelessFunctionalObject {

    public static final CheckContent INSTANCE = new CheckContent();

    /** The resource names every implementation with CSS of its own the registry holds has its content at. */
    public Set<String> expected(SpecRegistry spec) {
        ClasspathCssResolver classpath = classpath(spec);
        var out = new LinkedHashSet<String>();
        for (CssImplementation<?, ?, ?> impl : spec.cssImplementations()) out.add(classpath.resource(impl.getClass()));
        return out;
    }

    /** Every implementation's content: found, and held whole against the colour tree - every problem, each naming its file. */
    public List<String> content(SpecRegistry spec, ColorTree tree) {
        classpath(spec);
        var problems = new ArrayList<String>();
        for (SpecRegistry.Worn end : spec.implementations()) {
            CssImplementation<?, ?, ?> impl = end.impl();
            switch (spec.content(impl)) {
                case CssResolution.Found f -> problems.addAll(CheckDeclaration.INSTANCE.content(
                        spec.taxonomy(), tree, end.layer(), new Casting(end.component()), f.resource(), f.text(), impl.colours()));
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
