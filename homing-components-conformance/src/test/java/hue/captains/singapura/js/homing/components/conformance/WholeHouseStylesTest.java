package hue.captains.singapura.js.homing.components.conformance;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.styling.StyleManager;
import hue.captains.singapura.js.homing.design.styling.Styling;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The whole house's own instance (RFC 0067, appendix The Style Jars): with every jar of the house on this classpath -
 * every module that adds castings among them - the house's registration held to the central manager's rules: Base's
 * implementation for every casting, one per (style, casting), every style a jar provides for registered; and every
 * served sheet whole, a class for every casting, every mark filled in. Each jar holds its own declaration alone.
 */
class WholeHouseStylesTest {

    private static final Taxonomy HOUSE = HouseTaxonomy.INSTANCE.read(HouseTaxonomy.MODULES);

    @Test
    void theHousesInstance_holdsToTheCentralRules_overTheWholeHouse() {
        Styling styling = HouseStyles.INSTANCE.styling();
        StyleManager manager = styling.manager();
        assertEquals(161, HOUSE.castings().size(), "the pacman icon's among them, the icon button's in add gone with its roles (21 Oct); the rooms no castings, their owners' own; the slider group and its casting as what adjusts dropped, the panel, its bar and the kicker as a title; the twelve widgets no components, and the thirteen castings only a widget's role made (20 Oct); the text cell on its own, as a title and as a value (D42); each icon once, its look its own whatever role it plays (D52); the whole house's castings, the house's own and every extension's");
        assertEquals(HOUSE.castings().size(), manager.implementations(Base.INSTANCE).size(), "Base's for every one of them, from the jar that adds it");
        for (DesignStyle<?> s : styling.styles()) {
            String sheet = styling.styleSheet(s.token());
            assertTrue(!sheet.contains("$"), s.token() + ": every mark filled in");
            for (Casting c : HOUSE.castings()) assertTrue(sheet.contains("\n.styled-" + c.token() + " {"), s.token() + ": " + c.token());
        }
    }

    @Test
    void everyCasting_isOfAFamily() {
        for (var c : HOUSE.castings())
            assertEquals(true, HOUSE.head(c.cast()).isPresent(), c.token() + " is of no family");
    }
}
