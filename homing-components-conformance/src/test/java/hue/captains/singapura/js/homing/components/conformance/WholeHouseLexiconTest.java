package hue.captains.singapura.js.homing.components.conformance;

import hue.captains.singapura.js.homing.component.taxonomy.Lexicon;
import hue.captains.singapura.js.homing.component.taxonomy.LexiconExtensions;
import hue.captains.singapura.js.homing.component.taxonomy.Word;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.FlattenLexicon;
import hue.captains.singapura.js.homing.design.semantic.WriteWord;
import hue.captains.singapura.js.homing.ui.panes.PaneWords;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseLexicon;
import hue.captains.singapura.tao.ontology.enforcer.OntologyEnforcer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The whole house's lexicon, every module on the classpath: the common words, then the words each
 * module adds beside its code - the panes' - read whole; every house style writes every one, a module's
 * words written in Base by the module itself; and every node stateless, held here at build time.
 */
class WholeHouseLexiconTest {

    private static final Lexicon HOUSE = HouseLexicon.INSTANCE.read();

    @Test
    void theCommonWords_thenEachModulesOwn() {
        assertEquals(List.of(PaneWords.INSTANCE), LexiconExtensions.INSTANCE.of(HouseLexicon.class), "the panes add theirs");
        assertEquals(HouseLexicon.INSTANCE.common(), HOUSE.words().subList(0, 15));
        assertEquals(List.of("column", "row", "merge", "detach", "within"), HOUSE.words().subList(15, HOUSE.words().size()).stream().map(Word::token).toList());
        assertTrue(HOUSE.branches().contains(PaneWords.Arranging.INSTANCE), "and a branch of their own");
    }

    @Test
    void everyHouseStyle_writesEveryWord_theModulesWordsInBase() {
        for (DesignStyle s : HouseStyles.STYLES) {
            assertEquals(List.of(), WriteWord.INSTANCE.problems(HOUSE, s), s.token());
            assertEquals(List.of(), FlattenLexicon.INSTANCE.flatten(HOUSE, s, s.token()).unwritten(), s.token());
        }
        assertEquals("base", WriteWord.INSTANCE.write(HouseStyles.STYLES.get(0), PaneWords.Within.INSTANCE).orElseThrow().by().token(),
                "the module writes its words in Base, which every style is written over");
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
