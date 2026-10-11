package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.taxonomy.L1_WordBranch;
import hue.captains.singapura.js.homing.component.taxonomy.LexiconExtension;
import hue.captains.singapura.js.homing.component.taxonomy.LexiconSource;
import hue.captains.singapura.js.homing.component.taxonomy.Word;
import hue.captains.singapura.js.homing.component.taxonomy.WordRoot;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseLexicon;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseWords;

import java.util.List;

/**
 * The words only the panes show, as this module adds them to the house's lexicon: how a pane's room is
 * parted and given back, a tab taken out to float, and where the keys are. In the lexicon if and only if
 * this module is on the classpath; written in Base by the module itself ({@link PaneWritings}), so every
 * house style draws them.
 */
public record PaneWords() implements LexiconExtension {

    public static final PaneWords INSTANCE = new PaneWords();

    @Override public Class<? extends LexiconSource> extending() { return HouseLexicon.class; }

    /** Every word this module adds. */
    @Override public List<Word<?>> words() {
        return List.of(Column.INSTANCE, Row.INSTANCE, Merge.INSTANCE, Detach.INSTANCE, Within.INSTANCE);
    }

    /** How a room is parted among the panes in it. */
    public record Arranging() implements L1_WordBranch<WordRoot> {
        public static final Arranging INSTANCE = new Arranging();
        @Override public WordRoot parent() { return WordRoot.INSTANCE; }
    }

    public record Column() implements Word<Arranging> {
        public static final Column INSTANCE = new Column();
        @Override public Arranging parent() { return Arranging.INSTANCE; }
    }

    public record Row() implements Word<Arranging> {
        public static final Row INSTANCE = new Row();
        @Override public Arranging parent() { return Arranging.INSTANCE; }
    }

    public record Merge() implements Word<HouseWords.Acting> {
        public static final Merge INSTANCE = new Merge();
        @Override public HouseWords.Acting parent() { return HouseWords.Acting.INSTANCE; }
    }

    public record Detach() implements Word<HouseWords.Acting> {
        public static final Detach INSTANCE = new Detach();
        @Override public HouseWords.Acting parent() { return HouseWords.Acting.INSTANCE; }
    }

    public record Within() implements Word<HouseWords.Telling> {
        public static final Within INSTANCE = new Within();
        @Override public HouseWords.Telling parent() { return HouseWords.Telling.INSTANCE; }
    }
}
