package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.State;
import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Implementation;

import java.util.List;
import java.util.Map;

import static hue.captains.singapura.js.homing.ui.styles.Say.BORDER;
import static hue.captains.singapura.js.homing.ui.styles.Say.answers;
import static hue.captains.singapura.js.homing.ui.styles.Say.bareFrame;
import static hue.captains.singapura.js.homing.ui.styles.Say.textMeasure;
import static hue.captains.singapura.js.homing.ui.styles.Say.waysMeasure;
import static hue.captains.singapura.js.homing.ui.styles.Say.marksMeasure;
import static hue.captains.singapura.js.homing.ui.styles.Say.boundaryRule;
import static hue.captains.singapura.js.homing.ui.styles.Say.thumbRule;
import static hue.captains.singapura.js.homing.ui.styles.Say.sliderBase;
import static hue.captains.singapura.js.homing.ui.styles.Say.menuBase;
import static hue.captains.singapura.js.homing.ui.styles.Say.tabBase;
import static hue.captains.singapura.js.homing.ui.styles.Say.switchBase;
import static hue.captains.singapura.js.homing.ui.styles.Say.itemBase;
import static hue.captains.singapura.js.homing.ui.styles.Say.lampBase;
import static hue.captains.singapura.js.homing.ui.styles.Say.barBase;
import static hue.captains.singapura.js.homing.ui.styles.Say.cardBase;
import static hue.captains.singapura.js.homing.ui.styles.Say.siteBase;
import static hue.captains.singapura.js.homing.ui.styles.Say.BUTTON;
import static hue.captains.singapura.js.homing.ui.styles.Say.CORNER;
import static hue.captains.singapura.js.homing.ui.styles.Say.CONTROL;
import static hue.captains.singapura.js.homing.ui.styles.Say.CURSOR;
import static hue.captains.singapura.js.homing.ui.styles.Say.EASE;
import static hue.captains.singapura.js.homing.ui.styles.Say.EXTENT;
import static hue.captains.singapura.js.homing.ui.styles.Say.FACE;
import static hue.captains.singapura.js.homing.ui.styles.Say.GAP;
import static hue.captains.singapura.js.homing.ui.styles.Say.INSET;
import static hue.captains.singapura.js.homing.ui.styles.Say.OPACITY;
import static hue.captains.singapura.js.homing.ui.styles.Say.ROOT;
import static hue.captains.singapura.js.homing.ui.styles.Say.OUTLINE;
import static hue.captains.singapura.js.homing.ui.styles.Say.SCALE;
import static hue.captains.singapura.js.homing.ui.styles.Say.SHADOW;
import static hue.captains.singapura.js.homing.ui.styles.Say.TRANSFORM;
import static hue.captains.singapura.js.homing.ui.styles.Say.grows;
import static hue.captains.singapura.js.homing.ui.styles.Say.rest;
import static hue.captains.singapura.js.homing.ui.styles.Say.when;

/**
 * The house's base: neutral, a look of no style of its own. It answers every root that is not
 * colour - the element as the browser would leave it, its font its container's - so that every
 * class resolves in a style written over it; and what every house style says alike, so that none
 * has to: a control is pointed at, a button's type grows a step of 1.25 per unit of size, a
 * control that cannot be used neither lifts nor casts a shadow, and a button that is a mark alone -
 * a close button's cross, an icon button's icon - goes without a frame, in the size of the text it
 * sits in, in a square box with room round the mark, and flat in every state - a part, whose
 * owner's morphism is the only one - unless a style frames it; and a pane's thumbnail is ruled round
 * with a hairline, as flat. Every house style is written over it, and answers only what makes it itself.
 */
public record Base() implements DesignStyle {

    public static final Base INSTANCE = new Base();

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    private static final List<Implementation<?>> ANSWERS = answers(List.of(
            // ── the roots: every target that is not colour ──
            rest(ROOT, CORNER, "border-radius: 0;"),
            rest(ROOT, BORDER, "border-width: 0; border-style: solid;"),
            rest(ROOT, OUTLINE, "outline-width: 0; outline-style: none; outline-offset: 0;"),
            rest(ROOT, Target.Shape.Background.INSTANCE, "background-image: none; background-size: auto; background-repeat: repeat; background-position: 0% 0%;"),
            rest(ROOT, SHADOW, "box-shadow: none;"),
            rest(ROOT, Target.Shape.Clip.INSTANCE, "clip-path: none;"),
            rest(ROOT, INSET, "padding-block: 0; padding-inline: 0;"),
            rest(ROOT, GAP, "gap: normal;"),
            rest(ROOT, EXTENT, "min-height: auto; min-width: auto;"),
            rest(ROOT, Target.Size.Proportion.INSTANCE, "aspect-ratio: auto;"),
            rest(ROOT, FACE, "font-family: inherit;"),
            rest(ROOT, Target.Type.Weight.INSTANCE, "font-weight: inherit;"),
            rest(ROOT, SCALE, "font-size: inherit; line-height: inherit;"),
            rest(ROOT, Target.Type.Treatment.INSTANCE, "letter-spacing: normal;"),
            rest(ROOT, Target.Type.Decoration.INSTANCE, "text-decoration-line: none;"),
            rest(ROOT, Target.Type.Glyph.INSTANCE, ""),
            rest(ROOT, EASE, "transition: none;"),
            rest(ROOT, TRANSFORM, "transform: none;"),
            rest(ROOT, Target.Motion.Animate.INSTANCE, "animation: none;"),
            rest(ROOT, OPACITY, "opacity: 1;"),
            rest(ROOT, Target.Effect.Filter.INSTANCE, "filter: none;"),
            rest(ROOT, Target.Effect.Blend.INSTANCE, "mix-blend-mode: normal;"),
            rest(ROOT, CURSOR, "cursor: auto;"),
            rest(ROOT, Target.Affordance.Select.INSTANCE, "user-select: auto;"),
            rest(ROOT, Target.Sound.Cue.INSTANCE, ""),
            rest(ROOT, Target.Asset.Icon.INSTANCE, ""),
            rest(ROOT, Target.Asset.Illustration.INSTANCE, ""),
            // ── what every house style says alike ──
            rest(CONTROL, CURSOR, "cursor: pointer;"),
            rest(BUTTON, SCALE, "font-size: " + grows("14px", "1.25") + "; line-height: 1.5;"),
            when(CONTROL, SHADOW, Map.of(State.DISABLED, "box-shadow: none;")),
            when(CONTROL, TRANSFORM, Map.of(State.DISABLED, "transform: none;"))),
            // ── a mark alone - a close button's cross, an icon button's icon - goes without a frame ──
            bareFrame(),
            // ── every text's measure: its size and leading, harvested from the house's own type ──
            textMeasure(),
            waysMeasure(),
            marksMeasure(),
            // ── a layout's boundaries a hairline, unless the style draws them thicker ──
            boundaryRule("1px"),
            // ── a pane's thumbnail ruled round with a hairline ──
            thumbRule("1px"),
            // ── a slider's room, its knob grabbed, and its detent and ticks as most styles mark them ──
            sliderBase(),
            // ── a menu's text the label's face, kept from the hand; its rows pointed at, in the label's size ──
            menuBase(),
            // ── a tab's text the caption's size, kept from the hand, pointed at and grabbed; its strip's text kept from the hand, its tail's room ──
            tabBase(),
            // ── a switch: no room inside it, its word kept apart, its parts eased as they move and turn ──
            switchBase(),
            // ── an item's corner and rule a button's, ringed as a control, its room and its text; a choice and a listed row pointed at ──
            itemBase(),
            // ── a lamp's corner, line, room and size ──
            lampBase(),
            // ── a row of actions' room, a trail's ──
            barBase(),
            // ── a card's proportion, ring and pointer; a group of sliders framed as a card ──
            cardBase(),
            // ── a brand's mark apart from its name; a preferences button framed as a button is ──
            siteBase());
}
