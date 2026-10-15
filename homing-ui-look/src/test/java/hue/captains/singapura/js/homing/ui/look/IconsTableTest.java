package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.ui.taxonomy.HouseLexicon;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The table is the lexicon's: it names every word the house has on this classpath by its token, each
 * to the class that shows it, and imports the sheet with it. The sheet - every word in the style a
 * theme names - is the installed styling's, and held where the house's styles are (HouseLexiconSheetTest).
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
}
