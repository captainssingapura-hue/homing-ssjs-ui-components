package hue.captains.singapura.js.homing.components.conformance;

import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.semantic.FlattenSheet;
import hue.captains.singapura.js.homing.design.semantic.ReadAnswers;
import hue.captains.singapura.js.homing.design.semantic.ResolutionCases;
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
 * The house's buttons resolved under a sketch of a theme: the walk of core's cases, over the house
 * taxonomy - a danger button's own corner found under the button's, Go on the primary button, the
 * picker's roles falling back to the button's look, treatments common to every role, the dialog's
 * window set aside as functional.
 */
class HouseResolutionTest {

    private static final Taxonomy HOUSE = HouseTaxonomy.INSTANCE.read();

    @Test
    void theHousesCases_resolveAsWritten() {
        var cases = ResolutionCases.INSTANCE;
        var report = cases.run(lines("house-cases.txt"), cases.java(HOUSE, HouseResolutionTest::lines));
        assertEquals(13, report.cases(), "every case read");
        assertEquals(List.of(), report.failures(), () -> String.join("\n", report.failures()));
    }

    /** The sketch flattened: every casting the house has on every CSS target, nothing unanswered. */
    @Test
    void theSketch_flattensWhole() {
        var flat = FlattenSheet.INSTANCE.flatten(HOUSE, ReadAnswers.INSTANCE.read(HOUSE, lines("house-sketch.answers")), "house-sketch.answers");
        assertEquals(List.of(), flat.unanswered(), "every target answered at the root");
        assertEquals(HOUSE.castings().size() * 27, flat.looks(), "every casting on every CSS target but the glyph, a silence");
        assertTrue(flat.css().contains("\n.primary-button_go__type-weight {\n    font-weight: 700;\n}\n"), "Go on the primary button, answered itself");
        assertTrue(flat.css().contains("/* from primary-button__color-surface */\n.primary-button_go__color-surface {\n"), "the rank copied to the role");
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
