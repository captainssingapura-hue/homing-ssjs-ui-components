package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.L1_WordBranch;
import hue.captains.singapura.js.homing.component.taxonomy.Word;
import hue.captains.singapura.js.homing.component.taxonomy.WordRoot;

/**
 * The house's words: what its marks may mean, filed by what the mark says - an act a control does, how
 * the thing it is on is, that there is more behind it, an axis it is measured along. Each names the
 * meaning, never the shape: {@code Disclose}, never a chevron. The common ones are here; a module adds
 * those only its components show, beside its code ({@link HouseLexicon}). Every house style writes
 * them, Base first. What each means is in {@code meanings/.../HouseWords.md}.
 */
public final class HouseWords {

    private HouseWords() {}

    // ── branches ─────────────────────────────────────────────────────────

    /** What a control does to the thing it is on. */
    public record Acting() implements L1_WordBranch<WordRoot> {
        public static final Acting INSTANCE = new Acting();
        @Override public WordRoot parent() { return WordRoot.INSTANCE; }
    }

    /** How the thing it is on is, or what the hand may do with it. */
    public record Telling() implements L1_WordBranch<WordRoot> {
        public static final Telling INSTANCE = new Telling();
        @Override public WordRoot parent() { return WordRoot.INSTANCE; }
    }

    /** That there is more behind it. */
    public record Leading() implements L1_WordBranch<WordRoot> {
        public static final Leading INSTANCE = new Leading();
        @Override public WordRoot parent() { return WordRoot.INSTANCE; }
    }

    /** An axis it is measured along. */
    public record Measuring() implements L1_WordBranch<WordRoot> {
        public static final Measuring INSTANCE = new Measuring();
        @Override public WordRoot parent() { return WordRoot.INSTANCE; }
    }

    // ── acting ───────────────────────────────────────────────────────────

    public record Add() implements Word<Acting> {
        public static final Add INSTANCE = new Add();
        @Override public Acting parent() { return Acting.INSTANCE; }
    }

    public record Remove() implements Word<Acting> {
        public static final Remove INSTANCE = new Remove();
        @Override public Acting parent() { return Acting.INSTANCE; }
    }

    public record Close() implements Word<Acting> {
        public static final Close INSTANCE = new Close();
        @Override public Acting parent() { return Acting.INSTANCE; }
    }

    public record Reset() implements Word<Acting> {
        public static final Reset INSTANCE = new Reset();
        @Override public Acting parent() { return Acting.INSTANCE; }
    }

    public record Rotate() implements Word<Acting> {
        public static final Rotate INSTANCE = new Rotate();
        @Override public Acting parent() { return Acting.INSTANCE; }
    }

    public record Flip() implements Word<Acting> {
        public static final Flip INSTANCE = new Flip();
        @Override public Acting parent() { return Acting.INSTANCE; }
    }

    public record Pin() implements Word<Acting> {
        public static final Pin INSTANCE = new Pin();
        @Override public Acting parent() { return Acting.INSTANCE; }
    }

    // ── telling ──────────────────────────────────────────────────────────

    public record Check() implements Word<Telling> {
        public static final Check INSTANCE = new Check();
        @Override public Telling parent() { return Telling.INSTANCE; }
    }

    public record Grip() implements Word<Telling> {
        public static final Grip INSTANCE = new Grip();
        @Override public Telling parent() { return Telling.INSTANCE; }
    }

    // ── leading ──────────────────────────────────────────────────────────

    public record Disclose() implements Word<Leading> {
        public static final Disclose INSTANCE = new Disclose();
        @Override public Leading parent() { return Leading.INSTANCE; }
    }

    public record Settings() implements Word<Leading> {
        public static final Settings INSTANCE = new Settings();
        @Override public Leading parent() { return Leading.INSTANCE; }
    }

    // ── measuring ────────────────────────────────────────────────────────

    public record Size() implements Word<Measuring> {
        public static final Size INSTANCE = new Size();
        @Override public Measuring parent() { return Measuring.INSTANCE; }
    }

    public record Aspect() implements Word<Measuring> {
        public static final Aspect INSTANCE = new Aspect();
        @Override public Measuring parent() { return Measuring.INSTANCE; }
    }

    public record Extent() implements Word<Measuring> {
        public static final Extent INSTANCE = new Extent();
        @Override public Measuring parent() { return Measuring.INSTANCE; }
    }

    public record Level() implements Word<Measuring> {
        public static final Level INSTANCE = new Level();
        @Override public Measuring parent() { return Measuring.INSTANCE; }
    }
}
