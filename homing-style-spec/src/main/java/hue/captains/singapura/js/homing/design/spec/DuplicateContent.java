package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Build time only (RFC 0067, D53): the contents given twice on a classpath - two jars, or a jar and a directory, giving
 * the one resource an implementation's content is at. Run in a test of whatever puts the classpath together - a site, a
 * lab, a jar of content - and refused there, so that the resolver, at run time, finds the one there is.
 */
public record DuplicateContent() implements StatelessFunctionalObject {

    public static final DuplicateContent INSTANCE = new DuplicateContent();

    /**
     * One content given more than once.
     *
     * @param resource the resource
     * @param where    each place that gives it: two or more
     */
    public record Copies(String resource, List<URL> where) implements ValueObject {
        public Copies {
            Objects.requireNonNull(resource, "Copies.resource");
            where = List.copyOf(where);
            if (where.size() < 2) throw new IllegalArgumentException(resource + ": copies are two or more");
        }
    }

    /**
     * Every implementation with CSS of its own the registry holds whose content the loader is given more than once, each
     * named as the registry's resolver names it - its prefix the one it was registered with. Refused for a registry whose
     * resolver does not look on the classpath: content given twice there is that resolver's to find.
     */
    public List<Copies> find(SpecRegistry spec, ClassLoader loader) {
        if (!(spec.resolver() instanceof ClasspathCssResolver classpath))
            throw new IllegalArgumentException("the registry's resolver, " + spec.resolver() + ", does not look on the classpath");
        var out = new ArrayList<Copies>();
        for (CssImplementation<?, ?, ?, ?> impl : spec.cssImplementations()) {
            String resource = classpath.resource(impl.getClass());
            try {
                List<URL> where = Collections.list(loader.getResources(resource));
                if (where.size() > 1) out.add(new Copies(resource, where));
            } catch (IOException e) {
                throw new UncheckedIOException(resource + " could not be looked for", e);
            }
        }
        return List.copyOf(out);
    }
}
