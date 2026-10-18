package hue.captains.singapura.js.homing.design.spec.page;

import hue.captains.singapura.js.homing.design.spec.ReadDual;
import hue.captains.singapura.js.homing.design.spec.dual.DualKit;
import hue.captains.singapura.js.homing.design.spec.dual.DualTree;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The style manager's module (RFC 0071) touches no document: its code, its comments aside, says no word of a document's
 * API, and it imports nothing but the kit and its trees' duals. That it works with none is the swap lab's run in Node.
 */
class StyleManagerScriptTest {

    @Test
    void itSaysNoWordOfADocument_andImportsOnlyTheKitAndTheDuals() {
        String code = StyleManagerScript.INSTANCE.script().replaceAll("(?s)/\\*.*?\\*/", "").replaceAll("(?m)//.*$", "");
        var problems = new ArrayList<String>();
        for (String w : StyleManagerScript.INSTANCE.unsaid())
            if (Pattern.compile("\\b" + w + "\\b").matcher(code).find()) problems.add("says " + w);
        List<String> addresses = new ArrayList<>(List.of(DualKit.INSTANCE.address()));
        for (DualTree<?> t : ReadDual.INSTANCE.trees()) addresses.add(t.address());
        for (String line : code.lines().filter(l -> l.startsWith("import ")).toList())
            if (addresses.stream().noneMatch(a -> line.endsWith("\"" + a + "\";"))) problems.add("imports " + line);
        assertEquals(List.of(), problems);
    }
}
