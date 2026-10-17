package hue.captains.singapura.js.homing.sizing;

import hue.captains.singapura.js.homing.ssjs.test.JsModuleTestBase;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The JavaScript rail held to the Java one (Theorem 6.6): over random walks of every operation the
 * two write the same rail, to the millionth, and lay it out to the same pixels.
 */
class RailParityTest extends JsModuleTestBase {

    private static final Sizing S = Sizing.INSTANCE;
    private Value step, lay, of;

    @BeforeEach
    void load() {
        js = buildContext();
        loadModule("/homing/js/hue/captains/singapura/js/homing/sizing/RailModule.js");
        js.eval("js", """
                function step(text, op, a, b, c, d) {
                    const r = Rail.parse(text);
                    const out = op === "move" ? Rail.move(r, a, b, c, JSON.parse(d))
                              : op === "insert" ? Rail.insert(r, a, b)
                              : op === "remove" ? Rail.remove(r, a, b)
                              : op === "absorb" ? Rail.absorb(r, a, Rail.parse(b))
                              : null;
                    return Rail.text(out);
                }
                function lay(text, room, least) { return Rail.lay(Rail.parse(text), room, JSON.parse(least)); }
                function of(weights) { return Rail.text(Rail.of(JSON.parse(weights))); }
                """);
        step = js.getBindings("js").getMember("step");
        lay = js.getBindings("js").getMember("lay");
        of = js.getBindings("js").getMember("of");
    }

    private static String json(List<Double> xs) {
        var sb = new StringBuilder("[");
        for (int i = 0; i < xs.size(); i++) {
            if (i > 0) sb.append(',');
            sb.append(xs.get(i));
        }
        return sb.append(']').toString();
    }

    @Test
    void everyOperation_writesTheSameRail_inJavaAndInJavaScript() {
        var rnd = new Random(66);
        int compared = 0;
        for (int run = 0; run < 400; run++) {
            int n = 1 + rnd.nextInt(5);
            var w = new ArrayList<Double>();
            for (int k = 0; k < n; k++) w.add(rnd.nextInt(4) == 0 ? (double) (1 + rnd.nextInt(5)) : 0.01 + rnd.nextDouble() * 3);
            Rail r = S.of(w);
            assertEquals(r.text(), of.execute(json(w)).asString(), "weights " + w);
            for (int s = 0; s < 30; s++) {
                int pick = rnd.nextInt(10), size = r.size();
                Rail next;
                String got;
                if (pick < 5 && size >= 2) {
                    int i = rnd.nextInt(size - 1);
                    double d = (rnd.nextDouble() - 0.5) * 1500, room = 1 + rnd.nextDouble() * 1800;
                    var least = new ArrayList<Double>();
                    for (int k = 0; k < size; k++) least.add(rnd.nextInt(3) == 0 ? 0.0 : rnd.nextDouble() * 200);
                    next = r.move(i, d, room, least);
                    got = step.execute(r.text(), "move", i, d, room, json(least)).asString();
                    List<Double> laid = r.lay(room, least);
                    Value theirs = lay.execute(r.text(), room, json(least));
                    for (int k = 0; k < size; k++) assertEquals(laid.get(k), theirs.getArrayElement(k).asDouble(), "lay " + r.text() + " in " + room);
                } else if (pick < 7 && size < 9) {
                    int i = rnd.nextInt(size);
                    if (r.share(i) < 2) continue;
                    boolean before = rnd.nextBoolean();
                    next = r.insert(i, before);
                    got = step.execute(r.text(), "insert", i, before, null, null).asString();
                } else if (pick < 9 && size >= 2) {
                    int i = rnd.nextInt(size);
                    int toward = i == 0 ? 1 : i == size - 1 ? size - 2 : (rnd.nextBoolean() ? i - 1 : i + 1);
                    next = r.remove(i, toward);
                    got = step.execute(r.text(), "remove", i, toward, null, null).asString();
                } else {
                    int i = rnd.nextInt(size);
                    Rail inner = rnd.nextBoolean() ? S.even(1 + rnd.nextInt(3)) : S.of(0.01 + rnd.nextDouble(), 0.01 + rnd.nextDouble());
                    if (r.share(i) < inner.size()) continue;
                    next = r.absorb(i, inner);
                    got = step.execute(r.text(), "absorb", i, inner.text(), null, null).asString();
                }
                assertEquals(next.text(), got, "from " + r.text());
                r = next;
                compared++;
            }
        }
        assertEquals(true, compared > 8000, "compared " + compared);
    }
}
