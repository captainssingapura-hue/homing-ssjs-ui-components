package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Lexicon;
import hue.captains.singapura.js.homing.component.taxonomy.LexiconExtensions;
import hue.captains.singapura.js.homing.component.taxonomy.WordRoot;
import hue.captains.singapura.tao.ontology.enforcer.OntologyEnforcer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The house's lexicon - its common words, which every deployment has - is read whole and refused
 * nothing; nothing extends it here, where no module that extends it is on the classpath. Every word
 * means something, and jOntology holds every node stateless, here at build time. The whole lexicon,
 * every extension read in, is held by the components conformance, where every module is.
 */
class HouseLexiconTest {

    private static final Lexicon HOUSE = HouseLexicon.INSTANCE.read();

    @Test
    void theCommonWords_aloneOnThisClasspath() {
        assertEquals(List.of(), LexiconExtensions.INSTANCE.of(HouseLexicon.class), "no module that extends the house is here");
        assertEquals(HouseLexicon.INSTANCE.common(), HOUSE.words());
    }

    @Test
    void fifteenWords_inFourBranches() {
        assertEquals(15, HOUSE.words().size());
        assertEquals(List.of(HouseWords.Acting.INSTANCE, HouseWords.Telling.INSTANCE, HouseWords.Leading.INSTANCE, HouseWords.Measuring.INSTANCE),
                HOUSE.children(WordRoot.INSTANCE));
        assertEquals(List.of("add", "remove", "close", "reset", "rotate", "flip", "pin"),
                HOUSE.children(HouseWords.Acting.INSTANCE).stream().map(n -> n.name().value()).toList());
    }

    @Test
    void everyNode_isStateless() {
        var nodes = new ArrayList<Object>(HOUSE.branches());
        nodes.addAll(HOUSE.words());
        var enforcer = new OntologyEnforcer();
        var violations = nodes.stream().map(Object::getClass).distinct()
                .flatMap(c -> enforcer.enforce(c).stream().map(v -> c.getName() + ": " + v.message()))
                .toList();
        assertEquals(List.of(), violations);
    }
}
