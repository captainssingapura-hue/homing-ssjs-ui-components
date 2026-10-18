package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.design.semantic.Validation;
import hue.captains.singapura.js.homing.design.semantic.Validations;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

/**
 * The resolver of the classpath (RFC 0067, D53): an implementation's content is the resource under the resolver's
 * prefix at its class's canonical name - {@code homing-css/…/styles/editorial/PlainButton.css} - the exact name, from
 * its class's loader. The prefix is given where the resolver is registered ({@link ReadSpec}), and held to its rules
 * ({@link PrefixRule#RULES}) when made. One is found: two copies are refused when the classpath is built
 * ({@link DuplicateContent}), never here.
 *
 * @param prefix the directory content is under, from the classpath's root: {@code homing-css/}
 */
public record ClasspathCssResolver(String prefix) implements CssResolver, ValueObject {

    public ClasspathCssResolver {
        final String given = prefix;
        Validation found = Validations.INSTANCE.all(PrefixRule.RULES, rule -> rule.check(given));
        if (found instanceof Validation.Invalid invalid)
            throw new IllegalArgumentException("the resolver's prefix is refused:\n  " + String.join("\n  ", invalid.problems()));
    }

    @Override public CssResolution resolve(CssImplementation<?, ?, ?, ?> impl) {
        Class<?> type = impl.getClass();
        String resource = resource(type);
        URL where = loader(type).getResource(resource);
        return where == null ? new CssResolution.Nothing(resource) : read(resource, where);
    }

    /** The resource an implementation's content is to be: the prefix, then its class's name as a path, {@code .css}. */
    public String resource(Class<?> type) { return prefix + type.getName().replace('.', '/') + ".css"; }

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
