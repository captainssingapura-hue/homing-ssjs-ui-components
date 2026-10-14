package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ColorScale;
import hue.captains.singapura.js.homing.design.color.ColorScheme.Base;
import hue.captains.singapura.js.homing.design.color.ConcreteColor;
import hue.captains.singapura.js.homing.design.color.Fixed;
import hue.captains.singapura.js.homing.design.color.L1_ColorBranch;
import hue.captains.singapura.js.homing.design.color.Palettable;
import hue.captains.singapura.js.homing.design.semantic.ColorSource;
import hue.captains.singapura.js.homing.design.semantic.ColorTree;
import hue.captains.singapura.js.homing.design.semantic.ReadColors;

import java.util.List;

/**
 * The house's colour tree (RFC 0067, appendix Semantic Colours and Flavours, D16; appendix Two Classes per Leaf): every
 * colour a house leaf's file names, each named for what it is for, in the base scheme. Grown family by family as the
 * leaves move onto files - the buttons first.
 *
 * <ul>
 *   <li><b>surfaces</b> - what things stand on: raised, the page's ground, recessed below it, inverted, a wash under the
 *       hand for a mark alone, and clear;</li>
 *   <li><b>writing</b> - the inks: the text's, on inverted, on the accent;</li>
 *   <li><b>lines</b> - the hairline;</li>
 *   <li><b>attention</b> - the house's own colour, the accent and its edge, the ring a focused control wears, the
 *       accent's tint on a raised face;</li>
 *   <li><b>shadows</b> - what a box casts, at each strength the styles draw: the shade of the inverted layer, the sheen
 *       of the raised one, the glow of the house's colour;</li>
 *   <li><b>feedback</b> - danger, warning and success, the same in every house palette: fixed;</li>
 *   <li><b>scales</b> - what a coloured button's ground and ink move along, by its degree on the colour axis: the word at
 *       1, neutral at 0, the meaning turned the other way at -1.</li>
 * </ul>
 *
 * Every palettable colour is a {@link HouseColour}, one sealed set, so every house palette answers every one of them by
 * the compiler (D14).
 */
public record HouseColours() implements ColorSource {

    public static final HouseColours INSTANCE = new HouseColours();

    /** The tree, read once. */
    public static final ColorTree TREE = INSTANCE.read();

    /** Every palettable colour of the house: one sealed set, so a palette's switch over it is exhaustive. */
    public sealed interface HouseColour permits
            Raised, Ground, Recessed, Inverted, Wash,
            Text, OnInverted, OnAccent,
            Border,
            Title, Accent, AccentEdge, Ring, AccentTint,
            ShadeFaint, ShadeLight, ShadeSoft, ShadeMedium, ShadeFirm, Sheen,
            GlowFaint, GlowDim, GlowSoft, GlowMedium, GlowStrong, GlowBright, GlowFull {}

    public record Surfaces() implements L1_ColorBranch<Base> {}
    public record Writing() implements L1_ColorBranch<Base> {}
    public record Lines() implements L1_ColorBranch<Base> {}
    public record Attention() implements L1_ColorBranch<Base> {}
    public record Shadows() implements L1_ColorBranch<Base> {}
    public record Feedback() implements L1_ColorBranch<Base> {}
    public record Scales() implements L1_ColorBranch<Base> {}

    // ── surfaces ──────────────────────────────────────────────────────────

    /** A layer lifted off the page: a button's face, and the lit side of a shadow. */
    public record Raised() implements Palettable<Base, Surfaces>, HouseColour {}
    /** The page's ground: what everything on a page stands on. */
    public record Ground() implements Palettable<Base, Surfaces>, HouseColour {}
    /** A layer pressed below the page: the desk panes float over, a text box set into it. */
    public record Recessed() implements Palettable<Base, Surfaces>, HouseColour {}
    /** The darkest layer: a bar the page's ground is written on, and what a box casts. */
    public record Inverted() implements Palettable<Base, Surfaces>, HouseColour {}
    /** A wash under the hand for a mark alone - an icon button's - that shows on a raised face or the page's ground. */
    public record Wash() implements Palettable<Base, Surfaces>, HouseColour {}
    /** Nothing drawn: the same in every palette. */
    public record Clear() implements Fixed<Base, Surfaces> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("transparent"); }
    }

    // ── writing ───────────────────────────────────────────────────────────

    /** The ink of what is written. */
    public record Text() implements Palettable<Base, Writing>, HouseColour {}
    /** The ink written on the house's own colour and on inverted: the primary button's, the chosen tab's. */
    public record OnInverted() implements Palettable<Base, Writing>, HouseColour {}
    /** The ink written on the accent. */
    public record OnAccent() implements Palettable<Base, Writing>, HouseColour {}

    // ── lines ─────────────────────────────────────────────────────────────

    /** The hairline: a button's border, and where a coloured button's ground ends at 0. */
    public record Border() implements Palettable<Base, Lines>, HouseColour {}

    // ── attention ─────────────────────────────────────────────────────────

    /** The house's own colour: the primary button's ground, and the glow. */
    public record Title() implements Palettable<Base, Attention>, HouseColour {}
    /** The accent: the secondary button's ground. */
    public record Accent() implements Palettable<Base, Attention>, HouseColour {}
    /** The accent's edge: the secondary button's border, a toggle's that is on. */
    public record AccentEdge() implements Palettable<Base, Attention>, HouseColour {}
    /** The ring a control wears while its focus is shown: the accent leaning into the hairline. */
    public record Ring() implements Palettable<Base, Attention>, HouseColour {}
    /** The accent's tint on a raised face: a toggle that is on. */
    public record AccentTint() implements Palettable<Base, Attention>, HouseColour {}

    // ── shadows: the inverted layer's shade, the raised one's sheen, the house colour's glow, at each strength ──

    /** The inverted layer cast faintly - 4% - a whisper of lift. */
    public record ShadeFaint() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 8%: a box set down. */
    public record ShadeLight() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 10%: a soft lift. */
    public record ShadeSoft() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 12%: a box lifted under the hand. */
    public record ShadeMedium() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 16%: a moulded box's dark side. */
    public record ShadeFirm() implements Palettable<Base, Shadows>, HouseColour {}
    /** The raised layer's sheen, at 60%: a glass edge lit from above. */
    public record Sheen() implements Palettable<Base, Shadows>, HouseColour {}
    /** The house colour glowing faintly - 14% - a fine line of light. */
    public record GlowFaint() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 25%. */
    public record GlowDim() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 30%. */
    public record GlowSoft() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 40%. */
    public record GlowMedium() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 55%. */
    public record GlowStrong() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 60%. */
    public record GlowBright() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 70%: a neon edge. */
    public record GlowFull() implements Palettable<Base, Shadows>, HouseColour {}

    // ── feedback: the same in every house palette ─────────────────────────

    /** Danger said whole: a close under the hand. */
    public record Danger() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("#DC2626"); }
    }
    /** Danger pressed. */
    public record DangerPressed() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("#B91C1C"); }
    }
    /** The ink written on danger. */
    public record OnDanger() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("#FFFFFF"); }
    }
    /** Danger's tint: a danger button's ground. */
    public record DangerTint() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("rgba(220, 38, 38, .10)"); }
    }
    /** Danger's edge. */
    public record DangerEdge() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("rgba(220, 38, 38, .35)"); }
    }
    /** Danger's ink. */
    public record DangerInk() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("#7F1D1D"); }
    }
    /** Warning's tint. */
    public record WarningTint() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("rgba(202, 138, 4, .12)"); }
    }
    /** Warning's ink. */
    public record WarningInk() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("#713F12"); }
    }
    /** Success's tint. */
    public record SuccessTint() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("rgba(34, 139, 34, .12)"); }
    }
    /** Success's ink. */
    public record SuccessInk() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("#1B5E20"); }
    }

    // ── scales: a coloured button's ground and ink, by its degree ──────────

    /** The primary button's ground: nothing at -1, the hairline at 0, the house colour at 1. */
    public record PrimaryGround() implements ColorScale<Base, Scales> {
        @Override public List<Knot<Base>> knots() { return List.of(new Knot<>(new Clear(), -1), new Knot<>(new Border(), 0), new Knot<>(new Title(), 1)); }
    }
    /** The primary button's ink: the text's below 0, the ink on the house colour at 1. */
    public record PrimaryInk() implements ColorScale<Base, Scales> {
        @Override public List<Knot<Base>> knots() { return List.of(new Knot<>(new Text(), -1), new Knot<>(new Text(), 0), new Knot<>(new OnInverted(), 1)); }
    }
    /** The secondary button's ground: nothing at -1, the hairline at 0, the accent at 1. */
    public record SecondaryGround() implements ColorScale<Base, Scales> {
        @Override public List<Knot<Base>> knots() { return List.of(new Knot<>(new Clear(), -1), new Knot<>(new Border(), 0), new Knot<>(new Accent(), 1)); }
    }
    /** The danger button's ground: success's tint turned the other way, nothing at 0, danger's tint at 1. */
    public record DangerGround() implements ColorScale<Base, Scales> {
        @Override public List<Knot<Base>> knots() { return List.of(new Knot<>(new SuccessTint(), -1), new Knot<>(new Clear(), 0), new Knot<>(new DangerTint(), 1)); }
    }
    /** The danger button's ink: success's turned the other way, the text's at 0, danger's at 1. */
    public record DangerWriting() implements ColorScale<Base, Scales> {
        @Override public List<Knot<Base>> knots() { return List.of(new Knot<>(new SuccessInk(), -1), new Knot<>(new Text(), 0), new Knot<>(new DangerInk(), 1)); }
    }
    /** The warning button's ground: success's tint turned the other way, nothing at 0, warning's tint at 1. */
    public record WarningGround() implements ColorScale<Base, Scales> {
        @Override public List<Knot<Base>> knots() { return List.of(new Knot<>(new SuccessTint(), -1), new Knot<>(new Clear(), 0), new Knot<>(new WarningTint(), 1)); }
    }
    /** The success button's ground: danger's tint turned the other way, nothing at 0, success's tint at 1. */
    public record SuccessGround() implements ColorScale<Base, Scales> {
        @Override public List<Knot<Base>> knots() { return List.of(new Knot<>(new DangerTint(), -1), new Knot<>(new Clear(), 0), new Knot<>(new SuccessTint(), 1)); }
    }

    @Override public ColorTree read() {
        return ReadColors.INSTANCE.read(HouseColours.class, List.of(
                new Surfaces(), new Writing(), new Lines(), new Attention(), new Shadows(), new Feedback(), new Scales(),
                new Raised(), new Ground(), new Recessed(), new Inverted(), new Wash(), new Clear(),
                new Text(), new OnInverted(), new OnAccent(),
                new Border(),
                new Title(), new Accent(), new AccentEdge(), new Ring(), new AccentTint(),
                new ShadeFaint(), new ShadeLight(), new ShadeSoft(), new ShadeMedium(), new ShadeFirm(), new Sheen(),
                new GlowFaint(), new GlowDim(), new GlowSoft(), new GlowMedium(), new GlowStrong(), new GlowBright(), new GlowFull(),
                new Danger(), new DangerPressed(), new OnDanger(), new DangerTint(), new DangerEdge(), new DangerInk(),
                new WarningTint(), new WarningInk(), new SuccessTint(), new SuccessInk(),
                new PrimaryGround(), new PrimaryInk(), new SecondaryGround(), new DangerGround(), new DangerWriting(),
                new WarningGround(), new SuccessGround()));
    }
}
