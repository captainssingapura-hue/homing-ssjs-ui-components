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
 * exports its report reach no studio and no viewer. The viewer was split out:
 * the report is browsed downstream, by the studio repo's conformance studio
 * (homing-conformance-studio-components, port 8097), which reads it from this
 * module's jar; this holds the line, so no viewer grows back into the check.
 *
 * <p>Read off the sources, because an import is where the edge is made: a
 * check that named a studio type would compile as long as some studio is on
 * the classpath, and only a clean classpath would find it.</p>
 */
class CheckIsUiAgnosticTest {

    private static final Path MAIN = Path.of("src/main/java/hue/captains/singapura/js/homing/components/conformance");
    private static final Path TEST = Path.of("src/test/java/hue/captains/singapura/js/homing/components/conformance");

    /** The check: its definition, its export, its gate. The module holds nothing else. */
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
