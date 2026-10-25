package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.tao.ontology.ValueObject;

import java.net.URL;
import java.util.Objects;

/**
 * What a resolver found of an implementation's CSS content (RFC 0067, D53), at the implementation's canonical name:
 * the content, nothing there, or where it is but could not be had. Two copies of one content are no case here: they are
 * refused when the classpath is built ({@link DuplicateContent}), so the name finds one.
 */
public sealed interface CssResolution extends ValueObject
        permits CssResolution.Found, CssResolution.Nothing, CssResolution.Unreadable, CssResolution.NotUtf8 {

    /** The resource the content is to be: {@code hue/captains/singapura/js/homing/design/spec/styles/editorial/PlainButton.css}. */
    String resource();

    /**
     * Found, and read.
     *
     * @param resource the resource
     * @param where    where it is: in the jar, or the directory, that gives it
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
     * Nothing is there: nothing on the classpath gives the content.
     *
     * @param resource the resource looked for
     */
    record Nothing(String resource) implements CssResolution {
        public Nothing {
            Objects.requireNonNull(resource, "Nothing.resource");
        }
    }

    /**
     * There, and it could not be read.
     *
     * @param resource the resource
     * @param where    where it is
     * @param reason   what went wrong, as the reading said
     */
    record Unreadable(String resource, URL where, String reason) implements CssResolution {
        public Unreadable {
            Objects.requireNonNull(resource, "Unreadable.resource");
            Objects.requireNonNull(where, "Unreadable.where");
            Objects.requireNonNull(reason, "Unreadable.reason");
        }
    }

    /**
     * There, read, and not UTF-8: its bytes are no text, and none is made of them by guessing.
     *
     * @param resource the resource
     * @param where    where it is
     */
    record NotUtf8(String resource, URL where) implements CssResolution {
        public NotUtf8 {
            Objects.requireNonNull(resource, "NotUtf8.resource");
            Objects.requireNonNull(where, "NotUtf8.where");
        }
    }
}
