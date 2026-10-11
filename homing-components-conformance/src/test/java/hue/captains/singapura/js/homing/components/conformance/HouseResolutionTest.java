package hue.captains.singapura.js.homing.components.conformance;

import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.semantic.Dressed;
import hue.captains.singapura.js.homing.design.semantic.FlattenSheet;
import hue.captains.singapura.js.homing.design.semantic.ResolutionCases;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house's buttons resolved in the house styles: the walk of core's cases, over the house
 * taxonomy - each style's own answers first, Base's for what it leaves, Harbour's colours; Go on the
 * primary button, the picker's roles falling back to the button's look, treatments common to every
 * role, the dialog's window set aside as functional.
 */
class HouseResolutionTest {

    private static final Taxonomy HOUSE = HouseTaxonomy.INSTANCE.read();

    @Test
    void theHousesCases_resolveAsWritten() {
        var cases = ResolutionCases.INSTANCE;
        var report = cases.run(lines("house-cases.txt"), cases.java(HOUSE, HouseResolutionTest::theme));
        assertEquals(34, report.cases(), "every case read");
        assertEquals(List.of(), report.failures(), () -> String.join("\n", report.failures()));
    }

    /** Editorial flattened: every casting the house has on every CSS target, each rule saying where its body came from. */
    @Test
    void editorial_flattensWhole() {
        var flat = FlattenSheet.INSTANCE.flatten(HOUSE, theme("editorial"), "editorial");
        assertEquals(List.of(), flat.unanswered(), "every target answered at the root");
        long marked = HOUSE.castings().stream().filter(c -> c.component() == hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks.Delimiter.INSTANCE).count();
        assertEquals(HOUSE.castings().size() * 32 + marked, flat.looks(), "every casting on every CSS target the semantic classes answer; the glyph a silence but the delimiter's sign - a close button's cross is the lexicon's word");
        assertTrue(flat.css().contains("/* from base: control__affordance-cursor */\n.primary-button_go__affordance-cursor {\n    cursor: pointer;\n}\n"), "what every style says alike");
        assertTrue(flat.css().contains("/* from harbour: primary-button__color-background */\n.primary-button_go__color-background {\n"), "the rank copied to the role");
    }

    private static Dressed theme(String name) {
        Dressed d = HouseStyles.THEMES.get(name);
        if (d == null) throw new IllegalArgumentException("no house style " + name);
        return d;
    }

    private static List<String> lines(String name) {
        try (InputStream in = HouseResolutionTest.class.getResourceAsStream("/resolution/" + name)) {
            if (in == null) throw new IllegalStateException("no resolution/" + name);
            return new String(in.readAllBytes(), StandardCharsets.UTF_8).lines().toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
