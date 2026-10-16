package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScale;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/**
 * The secondary button in sketchy: an action beside the primary one, in the accent as far as its degree. Whole: every
 * property its look has (D9).
 */
public record SecondaryButton()
        implements CastingStyle<BaseScheme, Sketchy, HouseControls.SecondaryButton, RoleRoot> {
    /** Its ground along the degree: nothing at -1, each palette's neutral at 0, the accent at 1 (D41). */
    public record ButtonGround() implements ColorScale<BaseScheme, BaseColours.Scales> {
        @Override public List<Knot<BaseScheme>> knots() {
            return List.of(new Knot<>(new BaseColours.Clear(), -1), new Knot<>(new BaseColours.SecondaryNeutral(), 0),
                           new Knot<>(new BaseColours.Accent(), 1));
        }
    }

    /** Its label along the degree: the text's to 0.4, the ink on the accent from 0.6 - turned where its ground turns (D41). */
    public record ButtonLabel() implements ColorScale<BaseScheme, BaseColours.Scales> {
        @Override public List<Knot<BaseScheme>> knots() {
            return List.of(new Knot<>(new BaseColours.Text(), -1), new Knot<>(new BaseColours.Text(), 0.4),
                           new Knot<>(new BaseColours.OnAccent(), 0.6), new Knot<>(new BaseColours.OnAccent(), 1));
        }
    }

    @Override public StylingRes<BaseScheme, HouseControls.SecondaryButton> style() {
        return new StylingRes.CssContent<>(
                new ButtonLabel(), new BaseColours.AccentEdge(), new BaseColours.Ring(),
                new ButtonGround());
    }
}
