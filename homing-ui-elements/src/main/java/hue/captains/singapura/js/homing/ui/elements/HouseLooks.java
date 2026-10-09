package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.Carrier;
import hue.captains.singapura.js.homing.design.State;
import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.AnswerSet;
import hue.captains.singapura.js.homing.design.semantic.FlattenSheet;
import hue.captains.singapura.js.homing.design.semantic.ReadAnswers;
import hue.captains.singapura.js.homing.design.semantic.ResolveSemanticClass;
import hue.captains.singapura.js.homing.design.semantic.Resolution;
import hue.captains.singapura.js.homing.design.semantic.SemanticTokens;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The house's looks under its one theme so far - the sketch, {@code homing/looks/house-sketch.answers}
 * - read once and flattened once: the sheet {@link HouseLookSheet} serves, and what
 * {@link LookManifestModule} tells the page of it. Every theme is the sketch until the house
 * answers more.
 */
final class HouseLooks {

    private HouseLooks() {}

    static final String ANSWERS = "house-sketch.answers";

    static final Taxonomy TAXONOMY = HouseTaxonomy.INSTANCE.read();

    static final AnswerSet SKETCH = ReadAnswers.INSTANCE.read(TAXONOMY, lines("/homing/looks/" + ANSWERS));

    static final FlattenSheet.Flattened SHEET = FlattenSheet.INSTANCE.flatten(TAXONOMY, SKETCH, ANSWERS);

    /** Every CSS target, in the tree's order: a look is worn on each. */
    static List<String> targets() {
        return Target.leaves().stream().filter(t -> t.carrier() == Carrier.CSS).map(Target::token).toList();
    }

    /** Every component the sheet treats, and its treatments: {@code effect-opacity--disabled}, each a class it has a rule for. */
    static Map<String, List<String>> treated() {
        var tokens = SemanticTokens.INSTANCE;
        var out = new LinkedHashMap<String, List<String>>();
        for (Component<?> leaf : TAXONOMY.components()) {
            var mine = new ArrayList<String>();
            for (State s : State.values()) {
                if (s == State.REST) continue;
                for (Target t : Target.leaves()) {
                    if (t.carrier() != Carrier.CSS || !t.states().contains(s)) continue;
                    Resolution r = ResolveSemanticClass.INSTANCE.resolve(TAXONOMY, SKETCH, new Casting(leaf), s, t);
                    if (r.outcome() instanceof Resolution.Answered a && !SKETCH.body(a.node(), s, t).isBlank())
                        mine.add(tokens.target(t) + "--" + tokens.state(s));
                }
            }
            if (!mine.isEmpty()) out.put(leaf.token(), mine);
        }
        return out;
    }

    private static List<String> lines(String resource) {
        try (InputStream in = HouseLooks.class.getResourceAsStream(resource)) {
            if (in == null) throw new IllegalStateException("no " + resource + " on the classpath");
            return new String(in.readAllBytes(), StandardCharsets.UTF_8).lines().toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
