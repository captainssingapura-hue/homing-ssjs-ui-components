package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * The resolver of the classpath (RFC 0067, D53): an implementation's content is the resource at its class's canonical
 * name - {@code …/styles/editorial/PlainButton.css} - from whichever jar on its class's loader gives it. Every copy is
 * counted: none is nothing there, one is found, two or more are several.
 */
public record ClasspathCssResolver() implements CssResolver, StatelessFunctionalObject {

    public static final ClasspathCssResolver INSTANCE = new ClasspathCssResolver();

    @Override public CssResolution resolve(CastingStylePerRole<?, ?, ?, ?> impl) {
        if (!(impl.style() instanceof StylingRes.CssPlaceHolder<?, ?, ?, ?>))
            throw new IllegalArgumentException(impl.getClass().getName() + " delegates: it has no CSS of its own to resolve");
        Class<?> type = impl.getClass();
        String resource = resource(type);
        ClassLoader loader = type.getClassLoader() != null ? type.getClassLoader() : ClassLoader.getSystemClassLoader();
        try {
            return at(resource, Collections.list(loader.getResources(resource)));
        } catch (IOException e) {
            throw new UncheckedIOException(resource + " could not be looked for", e);
        }
    }

    /** The resource an implementation's content is to be: its class's name, a path, {@code .css}. */
    public String resource(Class<?> type) { return type.getName().replace('.', '/') + ".css"; }

    /** What the places a resource was found in come to. */
    CssResolution at(String resource, List<URL> found) {
        if (found.isEmpty()) return new CssResolution.Nothing(resource);
        if (found.size() > 1) return new CssResolution.Several(resource, found);
        URL where = found.getFirst();
        try (InputStream in = where.openStream()) {
            return new CssResolution.Found(resource, where, new String(in.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(resource + " could not be read at " + where, e);
        }
    }
}
