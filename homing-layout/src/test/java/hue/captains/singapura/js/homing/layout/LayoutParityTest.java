package hue.captains.singapura.js.homing.layout;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The JavaScript algebra held to the Java one, over random walks of every edit: after each step
 * the two write the same term. And any term read by both is read to the same layout.
 */
class LayoutParityTest extends JsModuleTestBase {

    private static final LayoutAlgebra A = LayoutAlgebra.INSTANCE;
    private Value step, read;

    @BeforeEach
    void load() {
        js = buildContext();
        loadModule("/homing/js/hue/captains/singapura/js/homing/layout/LayoutModule.js");
        js.eval("js", """
                function step(text, op, a, b, c) {
                    const l = Layout.parse(text);
                    const r = op === "subdivide" ? Layout.subdivide(l, a, b, c)
                            : op === "remove" ? Layout.remove(l, a)
                            : op === "swap" ? Layout.swap(l, a, b)
                            : op === "move" ? Layout.move(l, a, b, c)
                            : op === "transpose" ? Layout.transpose(l)
                            : op === "mirror" ? Layout.mirror(l)
                            : op === "from" ? Layout.from(JSON.parse(JSON.stringify(l)))
                            : null;
                    return Layout.text(r);
                }
                function read(text) { return Layout.text(Layout.parse(text)); }
                """);
        step = js.getBindings("js").getMember("step");
        read = js.getBindings("js").getMember("read");
    }

    private static String side(Side s) { return s.name().toLowerCase(); }

    @Test
    void everyEdit_writesTheSameTerm_inJavaAndInJavaScript() {
        var r = new Random(17);
        for (int run = 0; run < 300; run++) {
            Layout l = A.whole("c0");
            int fresh = 1;
            for (int s = 0; s < 25; s++) {
                var cells = l.cells();
                CellId c = cells.get(r.nextInt(cells.size())), d = cells.get(r.nextInt(cells.size()));
                Side sd = Side.values()[r.nextInt(4)];
                int pick = r.nextInt(10);
                String expected, got;
                if (pick < 4 || cells.size() < 2) {
                    CellId n = new CellId("c" + fresh++);
                    Layout next = l.subdivide(c, sd, n);
                    got = step.execute(l.text(), "subdivide", c.value(), side(sd), n.value()).asString();
                    expected = next.text();
                    l = next;
                } else if (pick < 6) {
                    Layout next = l.remove(c).orElseThrow();
                    got = step.execute(l.text(), "remove", c.value(), null, null).asString();
                    expected = next.text();
                    l = next;
                } else if (pick < 7) {
                    Layout next = l.swap(c, d);
                    got = step.execute(l.text(), "swap", c.value(), d.value(), null).asString();
                    expected = next.text();
                    l = next;
                } else if (pick < 8 && !c.equals(d)) {
                    Layout next = l.move(c, d, sd);
                    got = step.execute(l.text(), "move", c.value(), d.value(), side(sd)).asString();
                    expected = next.text();
                    l = next;
                } else if (pick < 9) {
                    expected = l.transpose().text();
                    got = step.execute(l.text(), "transpose", null, null, null).asString();
                } else {
                    expected = l.mirror().text();
                    got = step.execute(l.text(), "mirror", null, null, null).asString();
                }
                assertEquals(expected, got);
                assertEquals(l.text(), step.execute(l.text(), "from", null, null, null).asString(), "a layout as data, read back");
            }
        }
        assertEquals("ε", step.execute("c", "remove", "c", null, null).asString(), "the last cell leaves nothing");
    }

    @Test
    void anyTerm_isReadToTheSameLayout() {
        var r = new Random(19);
        for (int run = 0; run < 2000; run++) {
            var ids = new ArrayList<String>();
            for (int k = 0, n = 1 + r.nextInt(8); k < n; k++) ids.add("x" + k);
            String t = term(r, ids);
            assertEquals(A.parse(t).map(Layout::text).orElse("ε"), read.execute(t).asString(), t);
        }
    }

    private static String term(Random r, List<String> ids) {
        if (ids.isEmpty()) return "ε";
        if (ids.size() == 1) return r.nextInt(5) == 0 ? (r.nextBoolean() ? "H" : "V") + "(ε, " + ids.get(0) + ")" : ids.get(0);
        var parts = new ArrayList<String>();
        var rest = new ArrayList<>(ids);
        while (!rest.isEmpty()) {
            int take = 1 + r.nextInt(rest.size());
            parts.add(term(r, new ArrayList<>(rest.subList(0, take))));
            rest.subList(0, take).clear();
        }
        if (parts.size() == 1) parts.add("ε");
        return (r.nextBoolean() ? "H" : "V") + "(" + String.join(", ", parts) + ")";
    }
}
