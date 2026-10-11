package hue.captains.singapura.js.homing.ui.icons;

import hue.captains.singapura.js.homing.ui.taxonomy.HouseLexicon;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The table and the sheet are the lexicon's: the table names every word the house has on this
 * classpath by its token, each to the class that shows it, and imports the sheet with it; the sheet
 * draws every word in the style a theme names - the same in every palette of a style.
 */
class IconsTableTest {

    @Test
    void theTableIsGeneratedFromTheLexicon_byToken() {
        var body = IconsModule.INSTANCE.selfContent(null);
        assertEquals("void lexicon_sheet;", body.get(2), "the sheet loads wherever the table does");
        assertEquals("const ICONS = Object.freeze({ \"add\": \"word-add\", \"remove\": \"word-remove\", \"close\": \"word-close\", \"reset\": \"word-reset\","
                   + " \"rotate\": \"word-rotate\", \"flip\": \"word-flip\", \"pin\": \"word-pin\", \"check\": \"word-check\", \"grip\": \"word-grip\","
                   + " \"disclose\": \"word-disclose\", \"settings\": \"word-settings\", \"size\": \"word-size\", \"aspect\": \"word-aspect\","
                   + " \"extent\": \"word-extent\", \"level\": \"word-level\" });", body.get(3));
        assertEquals(HouseLexicon.INSTANCE.read().words().stream().map(w -> w.token()).toList(), IconsModule.INSTANCE.names());
        var imported = IconsModule.INSTANCE.imports().getAllImports().values().stream().flatMap(mi -> mi.allImports().stream())
                .map(e -> e.getClass().getSimpleName()).toList();
        assertEquals(List.of("lexicon_sheet"), imported);
    }

    @Test
    void theSheetDrawsEveryWord_inTheStyleTheThemeNames() {
        String editorial = LexiconSheet.INSTANCE.css("editorial");
        assertTrue(editorial.contains("/* from base */\n.word-close::before {\n    content: \"✕\";\n}\n"), editorial);
        String brutal = LexiconSheet.INSTANCE.css("neo-brutalism");
        assertTrue(brutal.contains(".word-close::before {\n    content: \"╳\";\n}\n"), "its own cross, written by the style itself");
        assertEquals(LexiconSheet.INSTANCE.css("sketchy"), LexiconSheet.INSTANCE.css("sketchy_forest"), "a word is the style's, never the palette's");
        assertEquals(15, editorial.split("::before \\{").length - 1, "every word, once");
        assertEquals(LexiconSheet.INSTANCE.css("editorial"), LexiconSheet.INSTANCE.css(null), "no theme named: the house's default");
    }
}
