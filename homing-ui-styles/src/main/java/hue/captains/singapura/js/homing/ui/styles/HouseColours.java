package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ColorScale;
import hue.captains.singapura.js.homing.design.color.ColorScheme.Base;
import hue.captains.singapura.js.homing.design.color.ConcreteColor;
import hue.captains.singapura.js.homing.design.color.CurrentColor;
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
 * leaves move onto files - the buttons first, then the ways, the text, the inputs, the tracks, the handles, the selectors, the items and the marks.
 *
 * <ul>
 *   <li><b>surfaces</b> - what things stand on: raised, the page's ground, recessed below it, inverted, the text's ink
 *       washed thin at each strength - under the hand for a mark alone, under code, under a pill - the scrollbar's
 *       thumb on the ground, and clear;</li>
 *   <li><b>inks</b> - what is written and drawn in, each the palette's, one for each purpose: the text's, and faded;
 *       on inverted, on the accent, on danger; a way's under the hand; a heading's, a kicker's; danger's, warning's and
 *       success's (D32);</li>
 *   <li><b>lines</b> - the hairline; a groove's edge; a line under the hand;</li>
 *   <li><b>current</b> - CSS's currentColor, the colour an element is given, at a share: for what does not care - a
 *       separator's line, a tick, a lamp at rest. No ink: an ink is the palette's (D31, D32);</li>
 *   <li><b>attention</b> - the house's own colour, the accent and its edge, the ring a focused control wears, the
 *       accent's tint on a raised face, and washed thin under a badge; the keys' marks - a candidate for them, the keys
 *       lent - in the accent;</li>
 *   <li><b>shadows</b> - what a box casts, at each strength the styles draw: the shade of the inverted layer, the sheen
 *       of the raised one, the glow of the house's colour;</li>
 *   <li><b>feedback</b> - danger, warning and success, the same in every house palette: fixed - a badge's wash of each
 *       among them; their inks are the inks';</li>
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
            Raised, Ground, Recessed, Inverted, Wash, WashFaint, WashSoft, ScrollThumb,
            Text, TextFaded, OnInverted, OnBar, OnAccent, OnDanger, WayHovered, HeadingInk, KickerInk, DangerInk, WarningInk, SuccessInk,
            Border, GrooveEdge, LineHovered,
            Title, Accent, AccentEdge, Ring, AccentTint, AccentTintFaint, AccentTintHint, AccentWash, AccentWashFaint, AccentWashLight, AccentWashSoft, AccentWashStrong, KeysCandidate, KeysLent,
            ShadeFaint, ShadeLight, ShadeSoft, ShadeMedium, ShadeModerate, ShadeFirm, ShadeStrong, ShadeDeep, ShadeHeavy, ShadeSolid, ShadeThick, ShadeDense, ShadeDark, ShadeDarker, ShadeDarkest, SheenSoft, Sheen,
            GlowTrace, GlowFaint, GlowLow, GlowDim, GlowGentle, GlowSoft, GlowMild, GlowMedium, GlowWarm, GlowStrong, GlowBright, GlowFull, GlowFlare {}

    public record Surfaces() implements L1_ColorBranch<Base> {}
    public record Inks() implements L1_ColorBranch<Base> {}
    public record Lines() implements L1_ColorBranch<Base> {}
    public record Current() implements L1_ColorBranch<Base> {}
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
    /** The text's ink washed fainter, at 8%: the ground code is set on. */
    public record WashFaint() implements Palettable<Base, Surfaces>, HouseColour {}
    /** At 10%: a pill's ground. */
    public record WashSoft() implements Palettable<Base, Surfaces>, HouseColour {}
    /** The scrollbar's thumb on the page's ground: the text's ink, 55% into the ground. */
    public record ScrollThumb() implements Palettable<Base, Surfaces>, HouseColour {}
    /** Nothing drawn: the same in every palette. */
    public record Clear() implements Fixed<Base, Surfaces> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("transparent"); }
    }

    // ── inks: what is written and drawn in, each the palette's (D32) ──────

    /** The ink of what is written. */
    public record Text() implements Palettable<Base, Inks>, HouseColour {}
    /** The text's ink, a fifth faded into what it stands on: a quieter line - a caption, a lede - and a way somewhere. */
    public record TextFaded() implements Palettable<Base, Inks>, HouseColour {}
    /** The ink written on the house's own colour and on inverted: the primary button's, the chosen tab's. */
    public record OnInverted() implements Palettable<Base, Inks>, HouseColour {}
    /** What is written on a bar laid in the inverted layer - a site's bar, a floating pane's head: the page's ground, on the dark. */
    public record OnBar() implements Palettable<Base, Inks>, HouseColour {}
    /** The ink written on the accent. */
    public record OnAccent() implements Palettable<Base, Inks>, HouseColour {}
    /** A way somewhere under the hand: the house's own colour leaning half into the faded text a way is written in. */
    public record WayHovered() implements Palettable<Base, Inks>, HouseColour {}
    /** A heading's ink: the house's own colour leaning half into the text's. */
    public record HeadingInk() implements Palettable<Base, Inks>, HouseColour {}
    /** A kicker's ink: the accent's edge leaning half into the text's. */
    public record KickerInk() implements Palettable<Base, Inks>, HouseColour {}
    /** The ink written on danger. */
    public record OnDanger() implements Palettable<Base, Inks>, HouseColour {}
    /** Danger's ink: words that warn of a loss. */
    public record DangerInk() implements Palettable<Base, Inks>, HouseColour {}
    /** Warning's ink. */
    public record WarningInk() implements Palettable<Base, Inks>, HouseColour {}
    /** Success's ink. */
    public record SuccessInk() implements Palettable<Base, Inks>, HouseColour {}

    // ── lines ─────────────────────────────────────────────────────────────

    /** The hairline: a button's border, and where a coloured button's ground ends at 0. */
    public record Border() implements Palettable<Base, Lines>, HouseColour {}
    /** A groove's edge: the text's ink at 45%. */
    public record GrooveEdge() implements Palettable<Base, Lines>, HouseColour {}
    /** A line under the hand: the accent half into the border - a boundary the hand may move. */
    public record LineHovered() implements Palettable<Base, Lines>, HouseColour {}

    // ── current: CSS's currentColor at a share, for what does not care (D31, D32) ──

    /** The whole of it: a knot a colour of meaning is mixed into, by the element's degree. */
    public record Whole() implements CurrentColor<Base, Current> { @Override public double share() { return 1; } }
    /** At 70%: a lamp at rest. */
    public record Quiet() implements CurrentColor<Base, Current> { @Override public double share() { return 0.70; } }
    /** At 80%: a trail's words, softer than what they are written on, whichever ink that is. */
    public record Soft() implements CurrentColor<Base, Current> { @Override public double share() { return 0.80; } }
    /** At 60%: a small mark's - the sign between a trail's steps, a scale's tick, a detent shown. */
    public record Muted() implements CurrentColor<Base, Current> { @Override public double share() { return 0.60; } }
    /** At 30%: a line between groups - a separator's. */
    public record Hairline() implements CurrentColor<Base, Current> { @Override public double share() { return 0.30; } }
    /** At 12%: a wash under the hand in the colour of whatever holds it - a preferences button on its bar. */
    public record Faint() implements CurrentColor<Base, Current> { @Override public double share() { return 0.12; } }

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
    /** The accent's faint tint on a raised face, 12%: a pane's thumbnail the cursor is at. */
    public record AccentTintFaint() implements Palettable<Base, Attention>, HouseColour {}
    /** A hint of the accent on a raised face, at 7%: a strip of tabs lit while its pane is worked in. */
    public record AccentTintHint() implements Palettable<Base, Attention>, HouseColour {}
    /** The accent washed thin, at 18%: a badge's ground. */
    public record AccentWash() implements Palettable<Base, Attention>, HouseColour {}
    /** The accent washed fainter, 8%: a tab's ground. */
    public record AccentWashFaint() implements Palettable<Base, Attention>, HouseColour {}
    /** At 12%: the one that is current, among items. */
    public record AccentWashLight() implements Palettable<Base, Attention>, HouseColour {}
    /** At 15%: a tab under the hand. */
    public record AccentWashSoft() implements Palettable<Base, Attention>, HouseColour {}
    /** At 28%: where a menu's cursor is. */
    public record AccentWashStrong() implements Palettable<Base, Attention>, HouseColour {}
    /** The keys' mark on a candidate for them: the accent at 45%. */
    public record KeysCandidate() implements Palettable<Base, Attention>, HouseColour {}
    /** The keys' mark where they are lent: the accent at 25%. */
    public record KeysLent() implements Palettable<Base, Attention>, HouseColour {}

    // ── shadows: the inverted layer's shade, the raised one's sheen, the house colour's glow, at each strength ──

    /** The inverted layer cast faintly - 4% - a whisper of lift. */
    public record ShadeFaint() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 8%: a box set down. */
    public record ShadeLight() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 10%: a soft lift. */
    public record ShadeSoft() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 12%: a box lifted under the hand. */
    public record ShadeMedium() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 14%: a panel lifted off the page, its far shadow. */
    public record ShadeModerate() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 16%: a moulded box's dark side. */
    public record ShadeFirm() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 18%: a tab in use, set on the row. */
    public record ShadeStrong() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 20%: a tab in the hand. */
    public record ShadeDeep() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 22%: a glass tab in the hand. */
    public record ShadeHeavy() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 30%: an edge strip's near shadow. */
    public record ShadeSolid() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 35%: an edge strip lifted off the page. */
    public record ShadeThick() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 45%: a panel lifted, cast hard. */
    public record ShadeDense() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 55%: a panel set in, cast hard. */
    public record ShadeDark() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 60%: an edge strip cast deep below its glow. */
    public record ShadeDarker() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 70%: a panel set deep into the page. */
    public record ShadeDarkest() implements Palettable<Base, Shadows>, HouseColour {}
    /** The raised layer's sheen at 45%: a glass panel's top edge lit. */
    public record SheenSoft() implements Palettable<Base, Shadows>, HouseColour {}
    /** The raised layer's sheen, at 60%: a glass edge lit from above. */
    public record Sheen() implements Palettable<Base, Shadows>, HouseColour {}
    /** The house colour glowing at 12%: a pane lit under the hand. */
    public record GlowTrace() implements Palettable<Base, Shadows>, HouseColour {}
    /** The house colour glowing faintly - 14% - a fine line of light. */
    public record GlowFaint() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 20%: a pane in the hand. */
    public record GlowLow() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 25%. */
    public record GlowDim() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 28%: the current pane's fine ring. */
    public record GlowGentle() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 30%. */
    public record GlowSoft() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 35%: the current pane's ring, moulded. */
    public record GlowMild() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 40%. */
    public record GlowMedium() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 45%. */
    public record GlowWarm() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 55%. */
    public record GlowStrong() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 60%. */
    public record GlowBright() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 70%: a neon edge. */
    public record GlowFull() implements Palettable<Base, Shadows>, HouseColour {}
    /** At 80%: a neon tab in the hand. */
    public record GlowFlare() implements Palettable<Base, Shadows>, HouseColour {}

    // ── feedback: the same in every house palette ─────────────────────────

    /** Danger said whole: a close under the hand. */
    public record Danger() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("#DC2626"); }
    }
    /** Danger pressed. */
    public record DangerPressed() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("#B91C1C"); }
    }
    /** Danger's tint: a danger button's ground. */
    public record DangerTint() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("rgba(220, 38, 38, .10)"); }
    }
    /** Danger's edge. */
    public record DangerEdge() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("rgba(220, 38, 38, .35)"); }
    }
    /** Warning's tint. */
    public record WarningTint() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("rgba(202, 138, 4, .12)"); }
    }
    /** Success's tint. */
    public record SuccessTint() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("rgba(34, 139, 34, .12)"); }
    }
    /** Danger's wash, at 12%: a danger badge's ground. */
    public record DangerWash() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("rgba(220, 38, 38, .12)"); }
    }
    /** Warning's wash, at 16%: a warning badge's ground. */
    public record WarningWash() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("rgba(202, 138, 4, .16)"); }
    }
    /** Success's wash, at 14%: a success badge's ground. */
    public record SuccessWash() implements Fixed<Base, Feedback> {
        @Override public ConcreteColor color() { return new ConcreteColor.Srgb("rgba(34, 139, 34, .14)"); }
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
    /** The keys mark's colour: the colour it is given at rest, the accent carried in by its degree - part of the way on a bar, whole in a tab. */
    public record KeysMark() implements ColorScale<Base, Scales> {
        @Override public List<Knot<Base>> knots() { return List.of(new Knot<>(new Whole(), -1), new Knot<>(new Whole(), 0), new Knot<>(new Accent(), 1)); }
    }
    public record SuccessGround() implements ColorScale<Base, Scales> {
        @Override public List<Knot<Base>> knots() { return List.of(new Knot<>(new DangerTint(), -1), new Knot<>(new Clear(), 0), new Knot<>(new SuccessTint(), 1)); }
    }

    @Override public ColorTree read() {
        return ReadColors.INSTANCE.read(HouseColours.class, List.of(
                new Surfaces(), new Inks(), new Lines(), new Current(), new Attention(), new Shadows(), new Feedback(), new Scales(),
                new Raised(), new Ground(), new Recessed(), new Inverted(), new Wash(), new WashFaint(), new WashSoft(), new ScrollThumb(), new Clear(),
                new Text(), new TextFaded(), new OnInverted(), new OnBar(), new OnAccent(), new WayHovered(), new HeadingInk(), new KickerInk(),
                new OnDanger(), new DangerInk(), new WarningInk(), new SuccessInk(),
                new Border(), new GrooveEdge(), new LineHovered(),
                new Whole(), new Soft(), new Quiet(), new Muted(), new Hairline(), new Faint(),
                new Title(), new Accent(), new AccentEdge(), new Ring(), new AccentTint(), new AccentTintFaint(), new AccentTintHint(), new AccentWash(), new AccentWashFaint(), new AccentWashLight(), new AccentWashSoft(), new AccentWashStrong(), new KeysCandidate(), new KeysLent(),
                new ShadeFaint(), new ShadeLight(), new ShadeSoft(), new ShadeMedium(), new ShadeModerate(), new ShadeFirm(), new ShadeStrong(), new ShadeDeep(), new ShadeHeavy(), new ShadeSolid(), new ShadeThick(), new ShadeDense(), new ShadeDark(), new ShadeDarker(), new ShadeDarkest(), new SheenSoft(), new Sheen(),
                new GlowTrace(), new GlowFaint(), new GlowLow(), new GlowDim(), new GlowGentle(), new GlowSoft(), new GlowMild(), new GlowMedium(), new GlowWarm(), new GlowStrong(), new GlowBright(), new GlowFull(), new GlowFlare(),
                new Danger(), new DangerPressed(), new DangerTint(), new DangerEdge(),
                new DangerWash(), new WarningWash(), new SuccessWash(),
                new WarningTint(), new SuccessTint(),
                new PrimaryGround(), new PrimaryInk(), new SecondaryGround(), new DangerGround(), new DangerWriting(),
                new WarningGround(), new SuccessGround(), new KeysMark()));
    }
}
