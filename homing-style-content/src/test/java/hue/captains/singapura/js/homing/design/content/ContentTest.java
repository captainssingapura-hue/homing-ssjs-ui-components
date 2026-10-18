package hue.captains.singapura.js.homing.design.content;

import hue.captains.singapura.js.homing.design.spec.CheckContent;
import hue.captains.singapura.js.homing.design.spec.DuplicateContent;
import hue.captains.singapura.js.homing.design.spec.SpecRegistry;
import hue.captains.singapura.js.homing.design.spec.house.HouseSpec;
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
 * read and in form; the jar ships nothing else; and the classpath put together here gives no content twice.
 */
class ContentTest {

    private static final SpecRegistry SPEC = HouseSpec.INSTANCE.registry();
    private static final Path SHIPPED = Path.of("src/main/resources");

    @Test
    void everyImplementation_hasItsContent_inForm() {
        List<String> problems = CheckContent.INSTANCE.content(SPEC);
        assertTrue(problems.isEmpty(), problems.size() + " problems:\n" + String.join("\n", problems.subList(0, Math.min(40, problems.size()))));
        System.out.println("[content] " + SPEC.cssImplementations().size() + " implementations with CSS of their own: each has its content, in form");
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
