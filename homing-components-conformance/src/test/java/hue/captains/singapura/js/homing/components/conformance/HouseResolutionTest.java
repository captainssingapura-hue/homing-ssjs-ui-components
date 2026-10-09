package hue.captains.singapura.js.homing.components.conformance;

import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.semantic.ResolutionCases;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(12, report.cases(), "every case read");
        assertEquals(List.of(), report.failures(), () -> String.join("\n", report.failures()));
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
