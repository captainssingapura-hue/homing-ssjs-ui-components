package hue.captains.singapura.js.homing.design.content;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.semantic.ColorTree;
import hue.captains.singapura.js.homing.design.spec.CheckContent;
import hue.captains.singapura.js.homing.design.spec.CssResolution;
import hue.captains.singapura.js.homing.design.spec.DuplicateContent;
import hue.captains.singapura.js.homing.design.spec.SpecRegistry;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
import hue.captains.singapura.js.homing.design.styling.CheckDeclaration;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house's content held to the registry (RFC 0067, D53): every implementation with CSS of its own has its content,
 * read and held whole as core holds an implementation's CSS - its marks, its casting's rule, its colours both ways; the
 * jar ships nothing else; and the classpath put together here gives no content twice.
 */
class ContentTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final ColorTree TREE = BaseColours.TREE;
    private static final Path SHIPPED = Path.of("src/main/resources");

    @Test
    void everyImplementation_hasItsContent_heldWhole() {
        List<String> problems = CheckContent.INSTANCE.content(SPEC, TREE);
        assertTrue(problems.isEmpty(), problems.size() + " problems:\n" + String.join("\n", problems.subList(0, Math.min(40, problems.size()))));
        System.out.println("[content] " + SPEC.implementations().size() + " implementations: each has its content - marks, rule, colours both ways");
    }

    /** The check bites: an implementation's content held with one colour it uses left off its list is refused for it. */
    @Test
    void aColourUsedAndNotListed_isRefused() {
        SpecRegistry.Worn end = SPEC.implementations().stream().filter(e -> !e.impl().colours().isEmpty()).findFirst().orElseThrow();
        var found = (CssResolution.Found) SPEC.content(end.impl());
        List<? extends ColorLeaf<?, ?, ?>> listed = end.impl().colours();
        List<String> held = CheckDeclaration.INSTANCE.content(SPEC.taxonomy(), TREE, end.layer(), new Casting(end.component()), found.resource(), found.text(), listed);
        assertEquals(List.of(), held);
        List<String> short1 = CheckDeclaration.INSTANCE.content(SPEC.taxonomy(), TREE, end.layer(), new Casting(end.component()), found.resource(), found.text(),
                listed.subList(1, listed.size()));
        assertTrue(short1.stream().anyMatch(p -> p.contains("uses " + listed.getFirst().token() + ", which it does not import")), short1.toString());
    }

    @Test
    void theJar_shipsOnlyContent() throws IOException {
        List<String> shipped;
        try (Stream<Path> walk = Files.walk(SHIPPED)) {
            shipped = walk.filter(Files::isRegularFile).map(p -> SHIPPED.relativize(p).toString().replace('\\', '/')).sorted().toList();
        }
        List<String> problems = CheckContent.INSTANCE.leftOver(SPEC, shipped);
        assertTrue(problems.isEmpty(), problems.size() + " left over:\n" + String.join("\n", problems.subList(0, Math.min(40, problems.size()))));
        assertEquals(CheckContent.INSTANCE.expected(SPEC).size(), shipped.size(), "one file for each implementation with CSS of its own");
        System.out.println("[content] " + shipped.size() + " files shipped, none left over");
    }

    @Test
    void theClasspath_givesNoContentTwice() {
        assertEquals(List.of(), DuplicateContent.INSTANCE.find(SPEC, getClass().getClassLoader()));
    }
}
