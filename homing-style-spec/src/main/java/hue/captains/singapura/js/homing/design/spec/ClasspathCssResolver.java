package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

/**
 * The resolver of the classpath (RFC 0067, D53): an implementation's content is the resource at its class's canonical
 * name - {@code …/styles/editorial/PlainButton.css} - the exact name, from its class's loader. One is found: two copies
 * are refused when the classpath is built ({@link DuplicateContent}), never here.
 */
public record ClasspathCssResolver() implements CssResolver, StatelessFunctionalObject {

    public static final ClasspathCssResolver INSTANCE = new ClasspathCssResolver();

    @Override public CssResolution resolve(CssImplementation<?, ?, ?, ?> impl) {
        Class<?> type = impl.getClass();
        String resource = resource(type);
        URL where = loader(type).getResource(resource);
        return where == null ? new CssResolution.Nothing(resource) : read(resource, where);
    }

    /** The resource an implementation's content is to be: its class's name, a path, {@code .css}. */
    public String resource(Class<?> type) { return type.getName().replace('.', '/') + ".css"; }

    /** The loader an implementation's content is looked for on: its class's. */
    public ClassLoader loader(Class<?> type) { return type.getClassLoader() != null ? type.getClassLoader() : ClassLoader.getSystemClassLoader(); }

    /** A resource that is there, read: its text, or why there is none. */
    CssResolution read(String resource, URL where) {
        byte[] bytes;
        try (InputStream in = where.openStream()) {
            bytes = in.readAllBytes();
        } catch (IOException e) {
            return new CssResolution.Unreadable(resource, where, e.toString());
        }
        try {
            String text = StandardCharsets.UTF_8.newDecoder()
                    .onMalformedInput(CodingErrorAction.REPORT)
                    .onUnmappableCharacter(CodingErrorAction.REPORT)
                    .decode(ByteBuffer.wrap(bytes)).toString();
            return new CssResolution.Found(resource, where, text);
        } catch (CharacterCodingException e) {
            return new CssResolution.NotUtf8(resource, where);
        }
    }
}
