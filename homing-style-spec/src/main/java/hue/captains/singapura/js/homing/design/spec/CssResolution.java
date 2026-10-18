package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.tao.ontology.ValueObject;

import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * What a resolver found of an implementation's CSS content (RFC 0067, D53): the resource at the implementation's
 * canonical name - found once, there being nothing there, or found in several places, which is refused where the
 * content is held.
 */
public sealed interface CssResolution extends ValueObject permits CssResolution.Found, CssResolution.Nothing, CssResolution.Several {

    /** The resource the content is to be: {@code hue/captains/singapura/js/homing/design/spec/styles/editorial/PlainButton.css}. */
    String resource();

    /**
     * Found, once.
     *
     * @param resource the resource
     * @param where    where it was found: the jar, or the directory, that gives it
     * @param text     its text
     */
    record Found(String resource, URL where, String text) implements CssResolution {
        public Found {
            Objects.requireNonNull(resource, "Found.resource");
            Objects.requireNonNull(where, "Found.where");
            Objects.requireNonNull(text, "Found.text");
        }
    }

    /**
     * Nothing is there: no jar gives the content.
     *
     * @param resource the resource looked for
     */
    record Nothing(String resource) implements CssResolution {
        public Nothing {
            Objects.requireNonNull(resource, "Nothing.resource");
        }
    }

    /**
     * Found in several places: two jars give the one content, and which one is meant cannot be said.
     *
     * @param resource the resource
     * @param where    each place it was found
     */
    record Several(String resource, List<URL> where) implements CssResolution {
        public Several {
            Objects.requireNonNull(resource, "Several.resource");
            where = List.copyOf(where);
            if (where.size() < 2) throw new IllegalArgumentException(resource + ": several is two or more");
        }
    }
}
