package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.component.taxonomy.Word;
import hue.captains.singapura.js.homing.core.EsModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.core.ModuleNameResolver;
import hue.captains.singapura.js.homing.core.SelfContent;
import hue.captains.singapura.js.homing.core.StampedParams;
import hue.captains.singapura.js.homing.design.semantic.SemanticTokens;

import java.util.ArrayList;
import java.util.List;

/**
 * The table of icon words, generated from the house's lexicon: {@code ICONS}, a frozen object of word
 * token → the class a mark wears to show it ({@code word-close}), one entry per word - the house's,
 * then those of every module on the classpath that adds its own. The {@code Icon} component looks a
 * name up here, and so does any component that shows a word on its own mark; the sheet that draws the
 * words ({@link LexiconSheet}) is imported with the table, so it loads wherever a word is shown. A new
 * word is a record in a lexicon and a writing in a style, and nothing is written here by hand.
 */
public record IconsModule() implements EsModule<IconsModule>, SelfContent {

    /** Word token → class name: {@code { close: "word-close", … }}, frozen. */
    public record ICONS() implements Exportable._Constant<IconsModule> {}

    public static final IconsModule INSTANCE = new IconsModule();

    @Override
    public ImportsFor<IconsModule> imports() {
        return ImportsFor.<IconsModule>builder()
                .add(new ModuleImports<>(List.of(new LexiconSheet.lexicon_sheet()), LexiconSheet.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<IconsModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ICONS()));
    }

    /** The words a component may ask for, in the lexicon's order. */
    public List<String> names() { return HouseWordsWritten.LEXICON.words().stream().map(Word::token).toList(); }

    @Override
    public List<String> selfContent(ModuleNameResolver resolver) {
        var lines = new ArrayList<String>();
        lines.add("// Generated from the house's lexicon - do not hand-edit. The words a mark may show, each to the");
        lines.add("// class that shows it; the style worn writes the glyph, in the sheet imported with the table.");
        lines.add("void lexicon_sheet;");
        var sb = new StringBuilder("const ICONS = Object.freeze({");
        var words = HouseWordsWritten.LEXICON.words();
        for (int i = 0; i < words.size(); i++) {
            if (i > 0) sb.append(',');
            sb.append(' ').append(StampedParams.jsString(words.get(i).token())).append(": ")
              .append(StampedParams.jsString(SemanticTokens.INSTANCE.word(words.get(i))));
        }
        lines.add(sb.append(" });").toString());
        return lines;
    }
}
