package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.semantic.CheckScheme;
import hue.captains.singapura.js.homing.design.semantic.SchemeRegistry;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The house's scheme jar held, as it is built, to what only its declarations' types tell (RFC 0067, D49): BaseScheme a
 * stateless singleton from scratch; BaseColours declaring it, each of its leaves and the branches that come in with them
 * a stateless record of it, saying the parent its type names, in this jar. Nothing at run time reads any of it.
 */
class BaseColoursTest {

    @Test
    void theHousesScheme_andItsDeclaration_sayWhatTheirTypesName() {
        SchemeRegistry house = SchemeRegistry.of(List.of(BaseColours.INSTANCE));
        assertEquals(List.of(), CheckScheme.INSTANCE.check(BaseColours.INSTANCE, house));
        assertEquals(BaseColours.TREE.nodes(), house.tree(BaseScheme.INSTANCE).nodes(), "the jar's own tree, as the registry reads it");
    }
}
