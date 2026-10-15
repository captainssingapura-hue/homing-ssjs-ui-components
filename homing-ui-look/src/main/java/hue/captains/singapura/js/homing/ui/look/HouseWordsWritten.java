package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.component.taxonomy.Lexicon;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.FlattenLexicon;
import hue.captains.singapura.js.homing.design.styling.InstalledStyling;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseLexicon;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The house's lexicon as this classpath has it, read once, and each style's writing of it - each style
 * the installed styling serves - flattened the first time a theme of the style is asked for: what {@link LexiconSheet} serves and
 * {@link IconsModule} tables.
 */
final class HouseWordsWritten {

    private HouseWordsWritten() {}

    /** The house's words and every extension's on the classpath, in the lexicon's order. */
    static final Lexicon LEXICON = HouseLexicon.INSTANCE.read();

    /** Each style's sheet, by the style's token: a word is the style's, never the palette's. */
    private static final Map<String, String> SHEETS = new ConcurrentHashMap<>();

    /** The sheet for the theme a page is worn in, by its slug: its style's writing of every word. */
    static String sheet(String themeSlug) {
        DesignStyle<?> style = InstalledStyling.get().dressed(themeSlug).style();
        return SHEETS.computeIfAbsent(style.token(), t -> FlattenLexicon.INSTANCE.flatten(LEXICON, style, t + " - the house's lexicon").css());
    }
}
