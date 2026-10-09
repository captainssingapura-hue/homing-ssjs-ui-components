package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.component.taxonomy.Root;
import hue.captains.singapura.js.homing.component.taxonomy.Taxon;
import hue.captains.singapura.js.homing.design.State;
import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.Answer;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

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

    /** Every answer of the groups, in order. */
    @SafeVarargs
    static List<Implementation<?>> answers(List<? extends Implementation<?>>... groups) {
        var out = new ArrayList<Implementation<?>>();
        for (var g : groups) out.addAll(g);
        return List.copyOf(out);
    }
}
