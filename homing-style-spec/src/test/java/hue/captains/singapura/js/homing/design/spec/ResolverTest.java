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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The resolver, apart from any content: in every style each element's content looked for at its implementation's
 * canonical name, and nothing there - no jar of content yet; a content there read, or why it could not be. Two copies of
 * one content are refused at build time, on the classpath put together, never by the resolver. And every element's trace
 * ending at the implementation it wears.
 */
class ResolverTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    /** The resolver the house registered: the classpath, under its prefix. */
    private static final ClasspathCssResolver RESOLVER = (ClasspathCssResolver) SPEC.resolver();

    /** A test's own implementation, its content in the test's resources, under the house's prefix. */
    record Sampled() implements CssImplementation<BaseScheme, Base, HouseControls.PlainButton, RoleRoot> {}

    @Test
    void everyElementInEveryStyle_nothingIsThere() {
        int looked = 0;
        for (DesignStyle<?> s : SPEC.styles())
            for (ElementPath e : SPEC.elements()) {
                CssImplementation<?, ?, ?, ?> worn = SPEC.wears(s, e);
                CssResolution r = SPEC.content(s, e);
                assertInstanceOf(CssResolution.Nothing.class, r, s.token() + " " + e.token());
                assertEquals("homing-css/" + worn.getClass().getName().replace('.', '/') + ".css", r.resource(), s.token() + " " + e.token());
                looked++;
            }
        System.out.println("[resolver] " + looked + " elements' content looked for: nothing is there");
    }

    @Test
    void contentThere_isRead() {
        var found = assertInstanceOf(CssResolution.Found.class, RESOLVER.resolve(new Sampled()));
        assertTrue(found.text().contains("$casting"), found.text());
        assertEquals("homing-css/hue/captains/singapura/js/homing/design/spec/ResolverTest$Sampled.css", found.resource());
    }

    @Test
    void thePrefix_isRegistered_andHeldToItsRules() {
        assertEquals(new ClasspathCssResolver("homing-css/"), SPEC.resolver());
        assertEquals("a/b/hue/captains/singapura/js/homing/design/spec/ResolverTest$Sampled.css", new ClasspathCssResolver("a/b/").resource(Sampled.class));
        for (String fine : List.of("homing-css/", "a/b/", "META-INF/homing/css/")) new ClasspathCssResolver(fine);
        var refused = assertThrows(IllegalArgumentException.class, () -> new ClasspathCssResolver("/a\\b//"));
        for (String said : List.of("starts with a slash", "holds a backslash", "holds a directory ''"))
            assertTrue(refused.getMessage().contains(said), refused.getMessage());
        for (String bad : List.of("", "homing-css", "./css/", "css/../x/")) assertThrows(IllegalArgumentException.class, () -> new ClasspathCssResolver(bad), bad);
        assertThrows(IllegalArgumentException.class, () -> new ClasspathCssResolver(null));
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

    /** A sheet is served whole or not at all: here, with no content on the classpath, every style's is refused, every content named. */
    @Test
    void aSheet_withoutItsContent_isNotServed() {
        for (DesignStyle<?> s : SPEC.styles()) {
            var refused = assertThrows(IllegalStateException.class, () -> ReadSheet.INSTANCE.read(SPEC, s));
            assertTrue(refused.getMessage().contains(SPEC.cssEnds(s).size() + " contents not to be had"), refused.getMessage().lines().findFirst().orElseThrow());
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
