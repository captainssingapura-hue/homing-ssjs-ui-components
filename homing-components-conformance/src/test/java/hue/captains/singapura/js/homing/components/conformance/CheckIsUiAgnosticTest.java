package hue.captains.singapura.js.homing.components.conformance;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The CHECK is UI-agnostic: what defines the gate, what runs it and what
 * exports its report reach no studio and no viewer. The module also serves the
 * report through the framework's conformance studio (the viewer, port 8097),
 * which is a studio, and it sits beside the check for now; this holds the line
 * between them, so the viewer can be split out, or dropped, without the check
 * noticing.
 *
 * <p>Read off the sources, because an import is where the edge is made: a
 * check that named a studio type would compile as long as the viewer's
 * dependency is on the classpath, and only a split would find it.</p>
 */
class CheckIsUiAgnosticTest {

    private static final Path MAIN = Path.of("src/main/java/hue/captains/singapura/js/homing/components/conformance");
    private static final Path TEST = Path.of("src/test/java/hue/captains/singapura/js/homing/components/conformance");

    /** The check: its definition, its export, its gate. The viewer, ComponentsConformanceStudioServer, is not in it. */
    private static final List<Path> CHECK = List.of(
            MAIN.resolve("ComponentsConformance.java"),
            MAIN.resolve("ComponentsConformanceExport.java"),
            TEST.resolve("ComponentsConformanceTest.java"));

    /** A studio, the conformance studio, or the studio's designs registry. */
    private static final Pattern UI = Pattern.compile(
            "^import\\s+(static\\s+)?hue\\.captains\\.singapura\\.js\\.homing\\.(studio|conformance\\.studio)\\.", Pattern.MULTILINE);

    @Test
    void theCheckImportsNoStudioAndNoViewer() throws IOException {
        for (Path p : CHECK) {
            assertTrue(Files.exists(p), "the check's source is where it was: " + p);
            var hits = UI.matcher(Files.readString(p)).results().map(m -> m.group()).toList();
            assertEquals(List.of(), hits, p.getFileName() + " reaches a studio or the viewer");
        }
    }
}
