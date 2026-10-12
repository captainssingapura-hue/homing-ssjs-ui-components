package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.core.DerivedCss;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.SelfRenderedCss;

import java.util.List;

/**
 * The house's lexicon as a sheet: every word a mark may show, the glyph the style worn writes for it,
 * drawn on the mark's {@code ::before} under the word's class ({@code FlattenLexicon}) - the house's
 * words and those of every module on the classpath that adds its own. A derived sheet (RFC 0064, D25):
 * it renders itself per theme, the same in every palette of a style, and the theme's owner fetches it
 * again on a switch - never the CSS manager.
 */
public record LexiconSheet() implements DerivedCss<LexiconSheet>, SelfRenderedCss {

    public static final LexiconSheet INSTANCE = new LexiconSheet();

    /** What a module imports the sheet by, so that it loads: no element wears it. */
    public record lexicon_sheet() implements Exportable._Constant<LexiconSheet> {}

    @Override public ExportsOf<LexiconSheet> exports() { return new ExportsOf<>(INSTANCE, List.of(new lexicon_sheet())); }

    @Override public String css(String themeSlug) { return HouseWordsWritten.sheet(themeSlug); }
}
