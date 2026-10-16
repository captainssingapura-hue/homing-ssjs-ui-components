package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.styling.CheckDeclaration;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * homing-style-glassmorphism's declaration held alone (RFC 0067, appendix The Style Jars): the jar - no two
 * implementations of one casting, nothing of it left out - and each implementation by itself, a test of its own.
 */
class GlassmorphismStyleDeclarationTest {

    @TestFactory
    Stream<DynamicTest> theJar_andEachImplementation_heldAlone() {
        CheckDeclaration.Checked checked = CheckDeclaration.INSTANCE.check(
                GlassmorphismStyleDeclaration.INSTANCE, HouseTaxonomy.INSTANCE.read(HouseTaxonomy.MODULES), BaseColours.TREE);
        return Stream.concat(
                Stream.of(DynamicTest.dynamicTest("the jar", () -> assertEquals(List.of(), checked.problems()))),
                checked.implementations().stream()
                        .map(i -> DynamicTest.dynamicTest(i.name(), () -> assertEquals(List.of(), i.problems()))));
    }
}
