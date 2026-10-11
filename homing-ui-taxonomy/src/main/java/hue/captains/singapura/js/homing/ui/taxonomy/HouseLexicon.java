package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Lexicon;
import hue.captains.singapura.js.homing.component.taxonomy.LexiconExtensions;
import hue.captains.singapura.js.homing.component.taxonomy.LexiconSource;
import hue.captains.singapura.js.homing.component.taxonomy.ReadLexicon;
import hue.captains.singapura.js.homing.component.taxonomy.Word;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The house's lexicon: what its marks may mean - a taxonomy of its own, apart from the components that
 * show the marks. Its common words ({@link HouseWords}), which every deployment of the house has, then
 * those of every module on the classpath that extends it (a {@link
 * hue.captains.singapura.js.homing.component.taxonomy.LexiconExtension LexiconExtension}): the panes'
 * and the rest, each declared beside its code, in the lexicon if and only if its module is there. A
 * downstream extends it the same way. Every house style writes every word, Base first; a module writes
 * its own words in Base, and in any style that should draw them otherwise.
 */
public record HouseLexicon() implements StatelessFunctionalObject, LexiconSource {

    public static final HouseLexicon INSTANCE = new HouseLexicon();

    /** The house's common words, branch by branch. */
    public List<Word<?>> common() {
        return List.of(
                // what a control does
                HouseWords.Add.INSTANCE, HouseWords.Remove.INSTANCE, HouseWords.Close.INSTANCE, HouseWords.Reset.INSTANCE,
                HouseWords.Rotate.INSTANCE, HouseWords.Flip.INSTANCE, HouseWords.Pin.INSTANCE,
                // how it is
                HouseWords.Check.INSTANCE, HouseWords.Grip.INSTANCE,
                // more behind it
                HouseWords.Disclose.INSTANCE, HouseWords.Settings.INSTANCE,
                // what it is measured along
                HouseWords.Size.INSTANCE, HouseWords.Aspect.INSTANCE, HouseWords.Extent.INSTANCE, HouseWords.Level.INSTANCE);
    }

    /** Every word the house has on this classpath: the common ones, then each extension's. */
    public List<Word<?>> words() { return LexiconExtensions.INSTANCE.words(HouseLexicon.class, common()); }

    /** The house's lexicon, read, or refused with every problem in it. */
    @Override public Lexicon read() { return ReadLexicon.INSTANCE.read(words()); }
}
