package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScale;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/**
 * The secondary button in retro-futurism: an action beside the primary one, in the accent as far as its degree. Whole:
 * every property its look has (D9).
 */
public record SecondaryButton()
        implements CastingStyle<BaseScheme, RetroFuturism, HouseControls.SecondaryButton, RoleRoot> {
    /** Its ground along the degree: nothing at -1, each palette's neutral at 0, the accent at 1 (D41). */
    public record ButtonGround() implements ColorScale<BaseScheme, HouseColours.Scales> {
        @Override public List<Knot<BaseScheme>> knots() {
            return List.of(new Knot<>(new HouseColours.Clear(), -1), new Knot<>(new HouseColours.SecondaryNeutral(), 0),
                           new Knot<>(new HouseColours.Accent(), 1));
        }
    }

    /** Its label along the degree: the text's to 0.4, the ink on the accent from 0.6 - turned where its ground turns (D41). */
    public record ButtonLabel() implements ColorScale<BaseScheme, HouseColours.Scales> {
        @Override public List<Knot<BaseScheme>> knots() {
            return List.of(new Knot<>(new HouseColours.Text(), -1), new Knot<>(new HouseColours.Text(), 0.4),
                           new Knot<>(new HouseColours.OnAccent(), 0.6), new Knot<>(new HouseColours.OnAccent(), 1));
        }
    }

    @Override public StylingRes<BaseScheme, HouseControls.SecondaryButton> style() {
        return new StylingRes.CssContent<>(
                new ButtonLabel(), new HouseColours.AccentEdge(), new HouseColours.Ring(),
                new HouseColours.GlowDim(), new HouseColours.GlowMedium(), new HouseColours.GlowStrong(),
                new HouseColours.GlowFull(), new ButtonGround());
    }
}
