package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.design.styling.InstalledStyling;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseLexicon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The lexicon's sheet in the house's styling, installed as a site installs it (RFC 0067, appendix The Style Jars): every
 * word in the style a theme names - the same in every palette of a style.
 */
class HouseLexiconSheetTest {

    @BeforeAll
    static void theHousesStyling_installed() { InstalledStyling.install(HouseStyles.INSTANCE.styling()); }

    @Test
    void theSheetDrawsEveryWord_inTheStyleTheThemeNames() {
        String editorial = LexiconSheet.INSTANCE.css("editorial");
        assertTrue(editorial.contains("/* from base */\n.word-close::before {\n    content: \"✕\";\n}\n"), editorial);
        String brutal = LexiconSheet.INSTANCE.css("neo-brutalism");
        assertTrue(brutal.contains(".word-close::before {\n    content: \"╳\";\n}\n"), "its own cross, written by the style itself");
        assertEquals(LexiconSheet.INSTANCE.css("sketchy"), LexiconSheet.INSTANCE.css("sketchy_forest"), "a word is the style's, never the palette's");
        assertEquals(HouseLexicon.INSTANCE.read().words().size(), editorial.split("::before \\{").length - 1,
                "every word on the classpath, once: the house's and every extension's");
        assertEquals(LexiconSheet.INSTANCE.css("editorial"), LexiconSheet.INSTANCE.css(null), "no theme named: the house's default");
    }
}
