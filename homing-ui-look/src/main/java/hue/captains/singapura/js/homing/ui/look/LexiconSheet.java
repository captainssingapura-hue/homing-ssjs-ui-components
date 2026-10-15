package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.core.DerivedCss;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.SelfRenderedCss;
import hue.captains.singapura.js.homing.design.styling.InstalledStyling;

import java.util.List;
import java.util.Optional;

/**
 * The house's lexicon as a sheet: every word a mark may show, the glyph the style worn writes for it,
 * drawn on the mark's {@code ::before} under the word's class ({@code FlattenLexicon}) - the house's
 * words and those of every module on the classpath that adds its own. A derived sheet (RFC 0064, D25):
 * it renders itself per style, the same in every palette, and the theme's owner fetches it
 * again when the style changes - never the CSS manager.
 */
public record LexiconSheet() implements DerivedCss<LexiconSheet>, SelfRenderedCss {

    public static final LexiconSheet INSTANCE = new LexiconSheet();

    /** What a module imports the sheet by, so that it loads: no element wears it. */
    public record lexicon_sheet() implements Exportable._Constant<LexiconSheet> {}

    @Override public ExportsOf<LexiconSheet> exports() { return new ExportsOf<>(INSTANCE, List.of(new lexicon_sheet())); }

    /** A word is the style's, never the palette's: the sheet varies with the style alone. */
    @Override public VariesWith variesWith() { return VariesWith.STYLE; }

    @Override public Optional<String> fallback() { return InstalledStyling.installed().map(s -> s.defaultStyle().token()); }

    @Override public String css(String style) { return HouseWordsWritten.sheet(style); }
}
