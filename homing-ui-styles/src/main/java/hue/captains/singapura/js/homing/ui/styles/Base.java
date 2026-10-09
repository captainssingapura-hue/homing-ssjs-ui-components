package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.State;
import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Implementation;

import java.util.List;
import java.util.Map;

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
import static hue.captains.singapura.js.homing.ui.styles.Say.RULE;
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
 * has to: a control is pointed at, a button's type grows a step of 1.25 per unit of size, and a
 * control that cannot be used neither lifts nor casts a shadow. Every house style is written over
 * it, and answers only what makes it itself.
 */
public record Base() implements DesignStyle {

    public static final Base INSTANCE = new Base();

    @Override public List<Implementation<?>> implementations() { return ANSWERS; }

    private static final List<Implementation<?>> ANSWERS = List.of(
            // ── the roots: every target that is not colour ──
            rest(ROOT, CORNER, "border-radius: 0;"),
            rest(ROOT, RULE, "border-width: 0; border-style: solid; outline-style: none;"),
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
            when(CONTROL, TRANSFORM, Map.of(State.DISABLED, "transform: none;")));
}
