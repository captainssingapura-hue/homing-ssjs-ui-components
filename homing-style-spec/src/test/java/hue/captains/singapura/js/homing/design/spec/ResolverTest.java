package hue.captains.singapura.js.homing.design.spec;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The resolver, apart from any content: in every style each element's content looked for at its implementation's
 * canonical name, and nothing there - no jar of content yet; a content found once read, found twice refused; a delegating
 * implementation never looked for. And every element's trace ending at the implementation it wears.
 */
class ResolverTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final ClasspathCssResolver RESOLVER = ClasspathCssResolver.INSTANCE;

    /** A test's own implementation, its content in the test's resources. */
    record Sampled() implements CastingStylePerRole<BaseScheme, Base, HouseControls.PlainButton, RoleRoot> {
        @Override public StylingRes<BaseScheme, Base, HouseControls.PlainButton, RoleRoot> style() { return new StylingRes.CssPlaceHolder<>(); }
    }

    /** A test's own delegating implementation. */
    record Delegating() implements CastingStylePerRole<BaseScheme, Base, HouseControls.PlainButton, HouseDoing.Action> {
        @Override public StylingRes<BaseScheme, Base, HouseControls.PlainButton, HouseDoing.Action> style() { return new StylingRes.RootDelegation<>(new Sampled()); }
    }

    @Test
    void everyElementInEveryStyle_nothingIsThere() {
        int looked = 0;
        for (DesignStyle<?> s : SPEC.styles())
            for (ElementPath e : SPEC.elements()) {
                CastingStylePerRole<?, ?, ?, ?> worn = SPEC.wears(s, e);
                CssResolution r = SPEC.content(s, e);
                assertInstanceOf(CssResolution.Nothing.class, r, s.token() + " " + e.token());
                assertEquals(worn.getClass().getName().replace('.', '/') + ".css", r.resource(), s.token() + " " + e.token());
                looked++;
            }
        System.out.println("[resolver] " + looked + " elements' content looked for: nothing is there");
    }

    @Test
    void contentFoundOnce_isRead() {
        CssResolution r = RESOLVER.resolve(new Sampled());
        var found = assertInstanceOf(CssResolution.Found.class, r);
        assertTrue(found.text().contains("$casting"), found.text());
        assertTrue(found.resource().endsWith("ResolverTest$Sampled.css"), found.resource());
    }

    @Test
    void contentFoundTwice_isSeveral(@TempDir Path dir) throws IOException {
        Path a = Files.writeString(dir.resolve("a.css"), "$casting { }"), b = Files.writeString(dir.resolve("b.css"), "$casting { }");
        List<URL> two = List.of(a.toUri().toURL(), b.toUri().toURL());
        var several = assertInstanceOf(CssResolution.Several.class, RESOLVER.at("x.css", two));
        assertEquals(2, several.where().size());
        assertInstanceOf(CssResolution.Nothing.class, RESOLVER.at("x.css", List.of()));
    }

    @Test
    void aDelegatingImplementation_isNeverLookedFor() {
        assertThrows(IllegalArgumentException.class, () -> RESOLVER.resolve(new Delegating()));
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
