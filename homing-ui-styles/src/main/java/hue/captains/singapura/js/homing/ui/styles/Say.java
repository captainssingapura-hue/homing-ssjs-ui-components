package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.component.taxonomy.Root;
import hue.captains.singapura.js.homing.component.taxonomy.Taxon;
import hue.captains.singapura.js.homing.design.State;
import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.Answer;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Shorthand for the house's answers: the nodes and targets they are written on, and the answer at rest or per state. */
final class Say {

    private Say() {}

    static final Taxon ROOT = Root.INSTANCE;
    static final Taxon CONTROL = HouseBranches.Control.INSTANCE;
    static final Taxon BUTTON = HouseBranches.Button.INSTANCE;

    static final Target.Shape.Corner CORNER = Target.Shape.Corner.INSTANCE;
    static final Target.Shape.Border BORDER = Target.Shape.Border.INSTANCE;
    static final Target.Shape.Outline OUTLINE = Target.Shape.Outline.INSTANCE;
    static final Target.Shape.Shadow SHADOW = Target.Shape.Shadow.INSTANCE;
    static final Target.Size.Inset INSET = Target.Size.Inset.INSTANCE;
    static final Target.Size.Gap GAP = Target.Size.Gap.INSTANCE;
    static final Target.Size.Extent EXTENT = Target.Size.Extent.INSTANCE;
    static final Target.Type.Face FACE = Target.Type.Face.INSTANCE;
    static final Target.Type.Scale SCALE = Target.Type.Scale.INSTANCE;
    static final Target.Motion.Ease EASE = Target.Motion.Ease.INSTANCE;
    static final Target.Motion.Transform TRANSFORM = Target.Motion.Transform.INSTANCE;
    static final Target.Effect.Opacity OPACITY = Target.Effect.Opacity.INSTANCE;
    static final Target.Affordance.Cursor CURSOR = Target.Affordance.Cursor.INSTANCE;

    /** The look alone: a body at rest. */
    static <T extends Target> Implementation<T> rest(Taxon node, T target, String body) {
        return new Implementation<>(node, target, new Answer.Own<>(body));
    }

    /** A body for each state answered. */
    static <T extends Target> Implementation<T> when(Taxon node, T target, Map<State, String> bodies) {
        return new Implementation<>(node, target, new Answer.Own<T>(bodies));
    }

    /** A length that grows a step of {@code ratio} per unit of the element's size. */
    static String grows(String length, String ratio) { return "calc(" + length + " * pow(" + ratio + ", var(--size)))"; }

    /** The button's measure, every length on one step: its inset, the gap between what it holds, its least width. */
    static List<Implementation<?>> measure(String block, String inline, String gap, String width, String step) {
        return List.of(
                rest(BUTTON, INSET, "padding-block: " + grows(block, step) + "; padding-inline: " + grows(inline, step) + ";"),
                rest(BUTTON, GAP, "gap: " + grows(gap, step) + ";"),
                rest(BUTTON, EXTENT, "min-width: " + grows(width, step) + ";"));
    }

    /** The ring a control wears: on focus, and the three marks of the keys - offered, lent, held. */
    static Implementation<Target.Shape.Outline> rings(String focus, String candidate, String lent, String held) {
        return when(CONTROL, OUTLINE, Map.of(State.FOCUS, outline(focus), State.CANDIDATE, outline(candidate),
                                          State.LENT, outline(lent), State.HELD, outline(held)));
    }

    /** {@code "2px solid -2px"} as the outline's width, style and offset. */
    private static String outline(String wso) {
        String[] w = wso.split(" ");
        return "outline-width: " + w[0] + "; outline-style: " + w[1] + "; outline-offset: " + w[2] + ";";
    }

    /** How a control looks when it cannot be used: its opacity, and the pointer over it. */
    static List<Implementation<?>> disabled(String opacity, String cursor) {
        return List.of(
                when(CONTROL, OPACITY, Map.of(State.DISABLED, "opacity: " + opacity + ";")),
                when(CONTROL, CURSOR, Map.of(State.DISABLED, "cursor: " + cursor + ";")));
    }

    static final Taxon TOGGLE = HouseControls.ToggleButton.INSTANCE;
    static final Taxon CLOSE = HouseControls.CloseButton.INSTANCE;
    static final Target.Type.Glyph GLYPH = Target.Type.Glyph.INSTANCE;

    /**
     * A toggle that is on is held pressed: the style's press, kept while it is on - its shadow and
     * its move, either null where the style's press has none.
     */
    static List<Implementation<?>> held(String shadow, String move) {
        var out = new ArrayList<Implementation<?>>();
        if (shadow != null) out.add(when(TOGGLE, SHADOW, Map.of(State.PRESSED, shadow)));
        if (move != null) out.add(when(TOGGLE, TRANSFORM, Map.of(State.PRESSED, move)));
        return List.copyOf(out);
    }

    /** The close button's mark - the style's own cross - drawn on its {@code ::before}. */
    static Implementation<?> closeMark(String glyph) { return rest(CLOSE, GLYPH, "content: \"" + glyph + "\";"); }

    static final Taxon ICON_BUTTON = HouseControls.IconButton.INSTANCE;

    /** The buttons that are a mark alone: a close button's cross, an icon button's icon. */
    static final List<Taxon> MARKS = List.of(CLOSE, ICON_BUTTON);

    /**
     * What frames a button or lifts it, which a mark alone goes without in every state: its border,
     * its inset, its shadow, its lift. A mark alone is always a part - a tab's cross, a strip's plus
     * - and its owner's morphism is the only one: a part that lifts inside a thing that lifts is
     * two levels of it, and noise.
     */
    static final List<Target> FRAME = List.of(BORDER, INSET, SHADOW, TRANSFORM);

    /**
     * The buttons that are a mark alone take Base's frame beneath the style's own frame for a
     * button: flat, in every state - no border, no inset, no shadow, no lift - in Base's box. Under
     * the hand only its colours change, the palette's. A style that frames them answers them itself
     * instead.
     */
    static List<Implementation<?>> bareMarks() {
        var out = new ArrayList<Implementation<?>>();
        for (Taxon mark : MARKS) {
            for (Target t : FRAME) out.add(fromBase(mark, t));
            out.add(fromBase(mark, EXTENT));
        }
        return List.copyOf(out);
    }

    private static <T extends Target> Implementation<T> fromBase(Taxon node, T target) {
        return new Implementation<>(node, target, new Answer.FromBase<>());
    }

    /**
     * Base's frame for a mark alone: the root's - nothing - in every state, and its type the root's,
     * so the mark takes the size of the text it sits in; and a square box a little larger than the
     * mark, so a ground under the hand has room round it.
     */
    static List<Implementation<?>> bareFrame() {
        var out = new ArrayList<Implementation<?>>();
        for (Taxon mark : MARKS) {
            for (Target t : FRAME) out.add(asTheRoot(mark, t));
            out.add(asTheRoot(mark, SCALE));
            out.add(rest(mark, EXTENT, "min-height: 1.5em; min-width: 1.5em;"));
        }
        return List.copyOf(out);
    }

    private static <T extends Target> Implementation<T> asTheRoot(Taxon node, T target) {
        return new Implementation<>(node, target, new Answer.Redirect<>(ROOT));
    }

    // ── the text leaves ──────────────────────────────────────────────────

    static final Taxon TEXT = HouseBranches.Text.INSTANCE;
    static final Taxon HEADING = HouseText.Heading.INSTANCE;
    static final Taxon KICKER = HouseText.Kicker.INSTANCE;
    static final Taxon CAPTION = HouseText.Caption.INSTANCE;
    static final Taxon LEDE = HouseText.Lede.INSTANCE;
    static final Taxon LABEL = HouseText.Label.INSTANCE;
    static final Taxon READOUT = HouseText.Readout.INSTANCE;
    static final Taxon BADGE = HouseText.Badge.INSTANCE;
    static final Taxon PILL = HouseText.Pill.INSTANCE;
    static final Taxon WORDMARK = HouseText.Wordmark.INSTANCE;
    static final Target.Type.Weight WEIGHT = Target.Type.Weight.INSTANCE;
    static final Target.Type.Treatment TREATMENT = Target.Type.Treatment.INSTANCE;

    /** A text leaf's type in a style: its face, its weight, its treatment - each left to what is above it when null. */
    static List<Implementation<?>> type(Taxon leaf, String face, String weight, String treatment) {
        var out = new ArrayList<Implementation<?>>();
        if (face != null) out.add(rest(leaf, FACE, "font-family: " + face + ";"));
        if (weight != null) out.add(rest(leaf, WEIGHT, "font-weight: " + weight + ";"));
        if (treatment != null) out.add(rest(leaf, TREATMENT, treatment));
        return List.copyOf(out);
    }

    /**
     * What every text's measure is in the house, whatever the style - harvested from the house's own
     * typography: each leaf's size and leading, growing with the element's size; a readout's and a
     * pill's figures of one width; a badge's and a pill's chip.
     */
    static List<Implementation<?>> textMeasure() {
        return List.of(
                rest(HEADING, SCALE, "font-size: " + grows("18px", "1.2") + "; line-height: 1.25;"),
                rest(LEDE, SCALE, "font-size: " + grows("17px", "1.2") + "; line-height: 1.55;"),
                rest(KICKER, SCALE, "font-size: " + grows("11px", "1.15") + "; line-height: 1.4;"),
                rest(CAPTION, SCALE, "font-size: " + grows("13px", "1.2") + "; line-height: 1.5;"),
                rest(LABEL, SCALE, "font-size: " + grows("14px", "1.25") + "; line-height: 1.5;"),
                rest(READOUT, SCALE, "font-size: " + grows("14px", "1.25") + "; line-height: 1.5;"),
                rest(READOUT, TREATMENT, "font-variant-numeric: tabular-nums;"),
                rest(BADGE, SCALE, "font-size: " + grows("11px", "1.15") + "; line-height: 1.4;"),
                rest(BADGE, TREATMENT, "letter-spacing: 0.06em; text-transform: uppercase;"),
                rest(BADGE, INSET, "padding-block: 0.15em; padding-inline: 0.6em;"),
                rest(BADGE, CORNER, "border-radius: 999px;"),
                rest(PILL, SCALE, "font-size: " + grows("12px", "1.15") + "; line-height: 1.5;"),
                rest(PILL, TREATMENT, "font-variant-numeric: tabular-nums;"),
                rest(PILL, INSET, "padding-block: 0; padding-inline: 0.5em;"),
                rest(PILL, CORNER, "border-radius: 999px;"),
                rest(WORDMARK, SCALE, "font-size: " + grows("22px", "1.2") + "; line-height: 1;"));
    }

    // ── the ways somewhere, and the veil ─────────────────────────────────

    static final Taxon LINK = HouseControls.Link.INSTANCE;
    static final Taxon CRUMB = HouseControls.Crumb.INSTANCE;
    static final Taxon SCRIM = HouseContainers.Scrim.INSTANCE;
    static final Target.Effect.Filter FILTER = Target.Effect.Filter.INSTANCE;
    static final Target.Type.Decoration DECORATION = Target.Type.Decoration.INSTANCE;

    /** The ways somewhere: a link, and a crumb of a trail - which a style eases and marks alike. */
    static final List<Taxon> WAYS = List.of(LINK, CRUMB);

    /** How a style veils the page behind a dialog: the scrim's backdrop filter. */
    static List<Implementation<?>> veil(String backdrop) { return List.of(rest(SCRIM, FILTER, "backdrop-filter: " + backdrop + ";")); }

    /** How a style eases a way's colour under the hand. */
    static List<Implementation<?>> waysEase(String transition) {
        var out = new ArrayList<Implementation<?>>();
        for (Taxon way : WAYS) out.add(rest(way, EASE, "transition: " + transition + ";"));
        return List.copyOf(out);
    }

    /** How a style marks a way under the hand: its decoration then, none at rest. */
    static List<Implementation<?>> waysMarked(String hover) {
        var out = new ArrayList<Implementation<?>>();
        for (Taxon way : WAYS) out.add(when(way, DECORATION, Map.of(State.HOVER, hover)));
        return List.copyOf(out);
    }

    /**
     * What the ways' measure is in the house, whatever the style: a link the size of the text it
     * sits in, grown by its own size; the crumb that is the page set heavier. (It is still pointed at,
     * as every control is: the cursor offers no current.)
     */
    static List<Implementation<?>> waysMeasure() {
        return List.of(
                rest(LINK, SCALE, "font-size: " + grows("1em", "1.2") + "; line-height: inherit;"),
                when(CRUMB, WEIGHT, Map.of(State.CURRENT, "font-weight: 600;")));
    }

    /** Every answer of the groups, in order. */
    @SafeVarargs
    static List<Implementation<?>> answers(List<? extends Implementation<?>>... groups) {
        var out = new ArrayList<Implementation<?>>();
        for (var g : groups) out.addAll(g);
        return List.copyOf(out);
    }
}
