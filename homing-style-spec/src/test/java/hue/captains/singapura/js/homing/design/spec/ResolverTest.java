package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The resolver, apart from any content: in every style each element's content looked for at its implementation's
 * canonical name, and nothing there - no jar of content yet; a content there read, or why it could not be. Two copies of
 * one content are refused at build time, on the classpath put together, never by the resolver. And every element's trace
 * ending at the implementation it wears.
 */
class ResolverTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final ClasspathCssResolver RESOLVER = ClasspathCssResolver.INSTANCE;

    /** A test's own implementation, its content in the test's resources. */
    record Sampled() implements CssImplementation<BaseScheme, Base, HouseControls.PlainButton, RoleRoot> {}

    @Test
    void everyElementInEveryStyle_nothingIsThere() {
        int looked = 0;
        for (DesignStyle<?> s : SPEC.styles())
            for (ElementPath e : SPEC.elements()) {
                CssImplementation<?, ?, ?, ?> worn = SPEC.wears(s, e);
                CssResolution r = SPEC.content(s, e);
                assertInstanceOf(CssResolution.Nothing.class, r, s.token() + " " + e.token());
                assertEquals(worn.getClass().getName().replace('.', '/') + ".css", r.resource(), s.token() + " " + e.token());
                looked++;
            }
        System.out.println("[resolver] " + looked + " elements' content looked for: nothing is there");
    }

    @Test
    void contentThere_isRead() {
        var found = assertInstanceOf(CssResolution.Found.class, RESOLVER.resolve(new Sampled()));
        assertTrue(found.text().contains("$casting"), found.text());
        assertTrue(found.resource().endsWith("ResolverTest$Sampled.css"), found.resource());
    }

    @Test
    void contentThere_butNotToBeHad_saysWhy(@TempDir Path dir) throws IOException {
        URL gone = dir.resolve("gone.css").toUri().toURL();
        var unreadable = assertInstanceOf(CssResolution.Unreadable.class, RESOLVER.read("x.css", gone));
        assertTrue(unreadable.reason().contains("gone.css"), unreadable.reason());
        URL bytes = Files.write(dir.resolve("bytes.css"), new byte[]{'a', (byte) 0xC3, '(', 'b'}).toUri().toURL();
        assertInstanceOf(CssResolution.NotUtf8.class, RESOLVER.read("x.css", bytes));
    }

    @Test
    void theHouseClasspath_givesNoContentTwice() {
        List<DuplicateContent.Copies> copies = DuplicateContent.INSTANCE.find(SPEC, getClass().getClassLoader());
        assertEquals(List.of(), copies);
        System.out.println("[resolver] " + SPEC.cssImplementations().size() + " implementations with CSS of their own: no content given twice");
    }

    @Test
    void contentGivenTwice_isFound(@TempDir Path dir) throws IOException {
        CssImplementation<?, ?, ?, ?> one = SPEC.cssImplementations().getFirst();
        String resource = RESOLVER.resource(one.getClass());
        Path a = dir.resolve("a"), b = dir.resolve("b");
        for (Path root : List.of(a, b)) {
            Files.createDirectories(root.resolve(resource).getParent());
            Files.writeString(root.resolve(resource), "$casting { }");
        }
        try (var loader = new URLClassLoader(new URL[]{a.toUri().toURL(), b.toUri().toURL()}, null)) {
            List<DuplicateContent.Copies> copies = DuplicateContent.INSTANCE.find(SPEC, loader);
            assertEquals(1, copies.size());
            assertEquals(resource, copies.getFirst().resource());
            assertEquals(2, copies.getFirst().where().size());
        }
    }

    @Test
    void everyTrace_endsAtWhatTheElementWears() {
        int steps = 0;
        for (DesignStyle<?> s : SPEC.styles())
            for (ElementPath e : SPEC.elements()) {
                Trace t = SPEC.trace(s, e);
                assertEquals(SPEC.wears(s, e), t.end().impl(), s.token() + " " + e.token());
                assertEquals(Trace.Via.STANDALONE, t.steps().getFirst().via(), s.token() + " " + e.token());
                boolean placed = !e.parts().isEmpty() && e.parts().getLast().functional();
                assertEquals(placed, t.steps().stream().anyMatch(st -> st.via() == Trace.Via.PLACED && st.casting().equals(e.casting()))
                        || (placed && t.steps().stream().anyMatch(st -> st.via() == Trace.Via.PLACED)), s.token() + " " + e.token());
                steps += t.steps().size();
            }
        System.out.println("[resolver] " + SPEC.elements().size() * SPEC.styles().size() + " traces, " + steps + " steps");
    }
}
