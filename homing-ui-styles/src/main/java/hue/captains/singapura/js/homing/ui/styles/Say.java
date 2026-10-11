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
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
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
    static final Taxon ITEM = HouseBranches.Item.INSTANCE;

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
        var out = new ArrayList<Implementation<?>>();
        for (Taxon used : List.of(CONTROL, ITEM)) {   // a control, and an item - a menu's row - that cannot be used, alike
            out.add(when(used, OPACITY, Map.of(State.DISABLED, "opacity: " + opacity + ";")));
            out.add(when(used, CURSOR, Map.of(State.DISABLED, "cursor: " + cursor + ";")));
        }
        return List.copyOf(out);
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

    // ── the marks between things ─────────────────────────────────────────

    static final Taxon SEPARATOR = HouseMarks.Separator.INSTANCE;
    static final Taxon DELIMITER = HouseMarks.Delimiter.INSTANCE;

    /** How thick a style rules one group of rows off from the next: the separator's line, along its foot. */
    static List<Implementation<?>> separatorRule(String width) {
        return List.of(rest(SEPARATOR, BORDER, "border-width: 0 0 " + width + " 0; border-style: solid;"));
    }

    /** The marks between things in the house, whatever the style: a separator a hairline unless its style rules it thicker; a delimiter a slash. */
    static List<Implementation<?>> marksMeasure() {
        var out = new ArrayList<Implementation<?>>(separatorRule("1px"));
        out.add(rest(DELIMITER, GLYPH, "content: \"/\";"));
        return List.copyOf(out);
    }

    // ── a pane's thumbnail: a picture of a pane to pick it by - a part, flat in every state ──

    static final Taxon PANE_THUMB = HouseControls.PaneThumb.INSTANCE;

    /** How thick a style rules a pane's thumbnail round: a hairline unless its style rules it thicker. Flat: no shadow, no lift, in any state. */
    static List<Implementation<?>> thumbRule(String width) {
        return List.of(rest(PANE_THUMB, BORDER, "border-width: " + width + "; border-style: solid;"));
    }

    // ── a slider: its rail, the groove its knob runs in, the fill, the detent and its ticks, and the knob -
    //    every part flat, in every state; only colours change ──

    static final Taxon SLIDER = HouseControls.Slider.INSTANCE;
    static final Taxon GROOVE = HouseMarks.Groove.INSTANCE;
    static final Taxon FILL = HouseMarks.Fill.INSTANCE;
    static final Taxon DETENT = HouseMarks.Detent.INSTANCE;
    static final Taxon TICK = HouseMarks.Tick.INSTANCE;
    static final Taxon KNOB = HouseControls.Knob.INSTANCE;

    /** How a slider's lengths grow with its size: by 1.3 a unit, as they always have. */
    private static final String SLIDER_GROWTH = "1.3";

    private static String along(String inline, String block) {
        return "inline-size: " + grows(inline, SLIDER_GROWTH) + "; block-size: " + grows(block, SLIDER_GROWTH) + ";";
    }

    /**
     * A slider's measure in a style - each said along its track, then across, so a fader, whose
     * rail is stood up, turns them with it: the rail's length and height, the groove's thickness,
     * the knob's square, and a fader's cap - long across its track, low along it.
     */
    static List<Implementation<?>> sliderMeasure(String railLength, String railHeight, String groove, String knob, String capAlong, String capAcross) {
        return List.of(rest(SLIDER, EXTENT, along(railLength, railHeight)),
                       rest(GROOVE, EXTENT, "block-size: " + grows(groove, SLIDER_GROWTH) + ";"),
                       when(KNOB, EXTENT, Map.of(State.REST, along(knob, knob), State.VERTICAL, along(capAlong, capAcross))));
    }

    /** A slider's lines and corners in a style: the groove's, the knob's, and a fader's cap's corner. */
    static List<Implementation<?>> sliderShape(String grooveCorner, String grooveRule, String knobCorner, String knobRule, String capCorner) {
        return List.of(rest(GROOVE, CORNER, "border-radius: " + grooveCorner + ";"),
                       rest(GROOVE, BORDER, "border-width: " + grooveRule + "; border-style: solid;"),
                       when(KNOB, CORNER, Map.of(State.REST, "border-radius: " + knobCorner + ";", State.VERTICAL, "border-radius: " + capCorner + ";")),
                       rest(KNOB, BORDER, "border-width: " + knobRule + "; border-style: solid;"));
    }

    /** The marks on a slider's track: the detent - along the track, then across it - and a tick's line, the same way. */
    static List<Implementation<?>> sliderMarks(String detentAlong, String detentAcross, String tickAlong, String tickAcross) {
        return List.of(rest(DETENT, EXTENT, along(detentAlong, detentAcross)),
                       rest(TICK, EXTENT, along(tickAlong, tickAcross)));
    }

    /** A knob that is its mark alone: its face drawn as nothing, the mark on the track, the ring still on its box. */
    static List<Implementation<?>> markAlone() {
        return List.of(rest(KNOB, OPACITY, "opacity: 0;"));
    }

    /** The knob of the slider a group has its keys on: ringed as a focused control is, whether or not it has the focus. */
    static List<Implementation<?>> currentKnob(String focus) {
        return List.of(when(KNOB, OUTLINE, Map.of(State.CURRENT, outline(focus))));
    }

    /** What every style's slider says alike: its room round the row and between its parts, no text taken by the hand, a knob grabbed and its mark's size; the detent and the ticks as most styles mark them. */
    static List<Implementation<?>> sliderBase() {
        var out = new ArrayList<Implementation<?>>(List.of(
                rest(SLIDER, INSET, "padding-block: 4px; padding-inline: 0;"),
                rest(SLIDER, GAP, "gap: 12px;"),
                rest(SLIDER, Target.Affordance.Select.INSTANCE, "user-select: none;"),
                rest(KNOB, SCALE, "font-size: " + grows("16px", SLIDER_GROWTH) + "; line-height: 1;"),
                when(KNOB, CURSOR, Map.of(State.REST, "cursor: grab;", State.DRAGGING, "cursor: grabbing;"))));
        out.addAll(sliderMarks("2px", "12px", "1px", "7px"));
        return List.copyOf(out);
    }

    // ── a context menu: its frame - the owner, whose morphism is the menu's only one - and its rows, flat ──

    static final Taxon CONTEXT_MENU = HouseContainers.ContextMenu.INSTANCE;
    static final Taxon MENU_ITEM = HouseItems.MenuItem.INSTANCE;

    /** A menu's frame in a style: its corner and rule, the room above and below its rows, between them, and its least width. */
    static List<Implementation<?>> menuFrame(String corner, String rule, String insetBlock, String gap, String width) {
        return List.of(rest(CONTEXT_MENU, CORNER, "border-radius: " + corner + ";"),
                       rest(CONTEXT_MENU, BORDER, "border-width: " + rule + "; border-style: solid;"),
                       rest(CONTEXT_MENU, INSET, "padding-block: " + insetBlock + "; padding-inline: 0;"),
                       rest(CONTEXT_MENU, GAP, "gap: " + gap + ";"),
                       rest(CONTEXT_MENU, EXTENT, "min-width: " + width + ";"));
    }

    /** A menu's row in a style: its corner and its room, above and below, then at its sides. */
    static List<Implementation<?>> menuRow(String corner, String block, String inline) {
        return List.of(rest(MENU_ITEM, CORNER, "border-radius: " + corner + ";"),
                       rest(MENU_ITEM, INSET, "padding-block: " + block + "; padding-inline: " + inline + ";"));
    }

    /** A menu's frame lifted over the page, as a style casts it: in the palette's shadow colours, never its own. */
    static List<Implementation<?>> menuShadow(String shadow) {
        return List.of(rest(CONTEXT_MENU, SHADOW, "box-shadow: " + shadow + ";"));
    }

    /** What a menu's frame does to the page behind it, seen through it: frosted, in a style of glass. */
    static List<Implementation<?>> menuBackdrop(String backdrop) { return List.of(rest(CONTEXT_MENU, FILTER, "backdrop-filter: " + backdrop + ";")); }

    /** What every style's menu says alike: its text the label's face, none of it taken by the hand; a row pointed at, its parts ten pixels apart, in the label's size. */
    static List<Implementation<?>> menuBase() {
        return List.of(new Implementation<>(CONTEXT_MENU, FACE, new Answer.Redirect<>(LABEL)),
                       rest(CONTEXT_MENU, Target.Affordance.Select.INSTANCE, "user-select: none;"),
                       rest(MENU_ITEM, GAP, "gap: 10px;"),
                       new Implementation<>(MENU_ITEM, SCALE, new Answer.Redirect<>(LABEL)),
                       rest(MENU_ITEM, CURSOR, "cursor: pointer;"));
    }

    // ── a tab and its strip: the tab the owner of its morphism - lifted under the hand, when chosen, pressed or
    //    dragged - its cross flat; the strip a raised bar, ruled along its foot; where a dragged tab would land, a bar ──

    static final Taxon TAB = HouseControls.Tab.INSTANCE;
    static final Taxon TAB_STRIP = HouseContainers.TabStrip.INSTANCE;
    static final Taxon DROP_MARK = HouseMarks.DropMark.INSTANCE;

    /** How a tab's lengths grow with its size: by 1.3 a unit, as they always have. */
    private static final String TAB_GROWTH = "1.3";

    /** A tab's height: its width over its proportion, the proportion widened or narrowed by the element's aspect, by 1.5 a unit. */
    private static String tabHeight(String width, String proportion) {
        return "calc(" + grows(width, TAB_GROWTH) + " / (" + proportion + " * pow(1.5, var(--aspect, 0))))";
    }

    /**
     * A tab's measure in a style: its width, and its height written down - its width over its proportion - rather
     * than left to a ratio, which a crowded row squeezing the width would answer with a shorter tab; the room at its
     * sides and between its parts; and its strip one tab tall and the room for a lift, whether or not it holds one.
     */
    static List<Implementation<?>> tabMeasure(String width, String proportion, String inline, String gap) {
        return List.of(rest(TAB, EXTENT, "inline-size: " + grows(width, TAB_GROWTH) + "; block-size: " + tabHeight(width, proportion) + ";"),
                       rest(TAB, INSET, "padding-block: 0; padding-inline: " + grows(inline, TAB_GROWTH) + ";"),
                       rest(TAB, GAP, "gap: " + grows(gap, TAB_GROWTH) + ";"),
                       rest(TAB_STRIP, EXTENT, "min-height: calc(" + tabHeight(width, proportion) + " + 8px);"));
    }

    /** A tab's frame in a style: its corners - cut at the top - its rule, and the chosen one's where it is ruled heavier, else null; and its strip's rule along its foot. */
    static List<Implementation<?>> tabFrame(String corner, String rule, String chosenRule, String stripRule) {
        String style = "0".equals(rule) ? "none" : "solid";
        var rules = new java.util.EnumMap<State, String>(State.class);
        rules.put(State.REST, "border-width: " + rule + "; border-style: " + style + ";");
        if (chosenRule != null) rules.put(State.SELECTED, "border-width: " + chosenRule + "; border-style: solid;");
        return List.of(rest(TAB, CORNER, "border-radius: " + corner + ";"),
                       when(TAB, BORDER, rules),
                       rest(TAB_STRIP, BORDER, "border-width: 0 0 " + stripRule + " 0; border-style: solid;"));
    }

    /** A body for each of the states a tab moves in - under the hand, chosen, and pressed or dragged alike - each left out where null. */
    private static Map<State, String> tabStates(String property, String hover, String chosen, String held) {
        var out = new java.util.EnumMap<State, String>(State.class);
        if (hover != null) out.put(State.HOVER, property + ": " + hover + ";");
        if (chosen != null) out.put(State.SELECTED, property + ": " + chosen + ";");
        if (held != null) { out.put(State.DRAGGING, property + ": " + held + ";"); out.put(State.ACTIVE, property + ": " + held + ";"); }
        return out;
    }

    /**
     * How a tab moves in a style: what is eased and how - a tab stepping aside for the one in the hand among it,
     * by its translate - and how far it is lifted under the hand, chosen, and pressed or dragged, each null where
     * the style does not move it.
     */
    static List<Implementation<?>> tabMotion(String ease, String hover, String chosen, String held) {
        var out = new ArrayList<Implementation<?>>();
        out.add(rest(TAB, EASE, "transition: " + ease + ";"));
        var moves = tabStates("transform", hover, chosen, held);
        if (!moves.isEmpty()) out.add(when(TAB, TRANSFORM, moves));
        return List.copyOf(out);
    }

    /** How a tab is lifted off its strip in a style, in the palette's shadow colours: under the hand, chosen, and pressed or dragged. */
    static List<Implementation<?>> tabShadow(String hover, String chosen, String held) {
        return List.of(when(TAB, SHADOW, tabStates("box-shadow", hover, chosen, held)));
    }

    /**
     * What every style's tab says alike: its text the caption's size, none of it taken by the hand; pointed at, and
     * grabbed while it is dragged. Its strip's text kept from the hand too, and its strip's own room - the room its
     * tail keeps between the count and the cross, and round them - eight pixels.
     */
    static List<Implementation<?>> tabBase() {
        return List.of(new Implementation<>(TAB, SCALE, new Answer.Redirect<>(CAPTION)),
                       rest(TAB, Target.Affordance.Select.INSTANCE, "user-select: none;"),
                       when(TAB, CURSOR, Map.of(State.REST, "cursor: pointer;", State.DRAGGING, "cursor: grabbing;")),
                       rest(TAB_STRIP, Target.Affordance.Select.INSTANCE, "user-select: none;"),
                       rest(TAB_STRIP, GAP, "gap: 8px;"),
                       rest(TAB_STRIP, INSET, "padding-block: 0; padding-inline: 8px;"));
    }

    // ── a switch: a slider's groove that is a control, its knob in it, both flat; the groove turns to the accent when it
    //    is on, the knob slides along it ──

    static final Taxon SWITCH = HouseControls.Switch.INSTANCE;

    /**
     * What every style's switch says alike: no room inside it - its knob is placed in its groove - and twelve
     * pixels between it and its word; and how its parts move - the knob along the groove, by its translate, and the
     * groove's colour when it turns on - eased alike.
     */
    static List<Implementation<?>> switchBase() {
        return List.of(rest(SWITCH, INSET, "padding-block: 0; padding-inline: 0;"),
                       rest(SWITCH, GAP, "gap: 12px;"),
                       rest(KNOB, EASE, "transition: translate 160ms ease;"),
                       rest(GROOVE, EASE, "transition: background-color 160ms ease, border-color 160ms ease;"));
    }

    // ── the items: a row of a list - a choice, a listed row, a setting, a menu's row - clear at rest, the chosen one on
    //    the title colour, the cursor's tinted; ringed as a control is; its corner and rule a button's ──

    static final Taxon CHOICE_OPTION = HouseItems.ChoiceOption.INSTANCE;
    static final Taxon LIST_ROW = HouseItems.ListRow.INSTANCE;

    /** The items a hand points at - a choice, a listed row, a menu's row - each lit under it; a setting's row is only read. */
    static final List<Taxon> POINTED = List.of(CHOICE_OPTION, LIST_ROW, MENU_ITEM);

    /**
     * What every style's items say alike: a button's corner and rule, a control's ring - a row may have the keys - six
     * pixels above and below, ten between its parts, the label's text, its colours eased; a choice and a listed row
     * set in from their sides and pointed at, a listed row compact and in the caption's size - a list master's, a
     * monitor's (only the base taxonomy's castings are answered: a list row as a choice is an extension's).
     */
    static List<Implementation<?>> itemBase() {
        return List.of(new Implementation<>(ITEM, CORNER, new Answer.Redirect<>(BUTTON)),
                       new Implementation<>(ITEM, BORDER, new Answer.Redirect<>(BUTTON)),
                       new Implementation<>(ITEM, OUTLINE, new Answer.Redirect<>(CONTROL)),
                       rest(ITEM, INSET, "padding-block: 6px; padding-inline: 0;"),
                       rest(ITEM, GAP, "gap: 10px;"),
                       new Implementation<>(ITEM, FACE, new Answer.Redirect<>(LABEL)),
                       new Implementation<>(ITEM, SCALE, new Answer.Redirect<>(LABEL)),
                       rest(ITEM, EASE, "transition: background-color 160ms ease, border-color 160ms ease, color 160ms ease;"),
                       rest(CHOICE_OPTION, INSET, "padding-block: 7px; padding-inline: 10px;"),
                       rest(CHOICE_OPTION, CURSOR, "cursor: pointer;"),
                       rest(LIST_ROW, INSET, "padding-block: 4px; padding-inline: 8px;"),
                       rest(LIST_ROW, GAP, "gap: 8px;"),
                       rest(LIST_ROW, CURSOR, "cursor: pointer;"),
                       new Implementation<>(LIST_ROW, SCALE, new Answer.Redirect<>(CAPTION)));
    }

    // ── a range - the browser's own slider, in the accent - and a lamp, which says how something is: lit, quiet, or broken ──

    static final Taxon RANGE = HouseControls.Range.INSTANCE;
    static final Taxon LAMP = HouseMarks.Lamp.INSTANCE;

    /**
     * What every style's lamp says alike: a button's corner, a hairline round it, four pixels above and below and
     * eight at its sides, the caption's size, its colours eased as it changes.
     */
    static List<Implementation<?>> lampBase() {
        return List.of(new Implementation<>(LAMP, CORNER, new Answer.Redirect<>(BUTTON)),
                       rest(LAMP, BORDER, "border-width: 1px; border-style: solid;"),
                       rest(LAMP, INSET, "padding-block: 4px; padding-inline: 8px;"),
                       new Implementation<>(LAMP, SCALE, new Answer.Redirect<>(CAPTION)),
                       rest(LAMP, EASE, "transition: background-color 160ms ease, border-color 160ms ease, color 160ms ease;"));
    }

    /** Every answer of the groups, in order. */
    @SafeVarargs
    static List<Implementation<?>> answers(List<? extends Implementation<?>>... groups) {
        var out = new ArrayList<Implementation<?>>();
        for (var g : groups) out.addAll(g);
        return List.copyOf(out);
    }
}
