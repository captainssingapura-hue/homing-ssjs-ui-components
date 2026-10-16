package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScale;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/**
 * The primary button in the base layer: the one action a view leads with, in the house's own colour as far as its
 * degree. Whole: every property its look has (D9).
 */
public record PrimaryButton() implements CastingStyle<BaseScheme, Base, HouseControls.PrimaryButton, RoleRoot> {
    /** Its ground along the degree: nothing at -1, each palette's neutral at 0, the house colour at 1 (D41). */
    public record ButtonGround() implements ColorScale<BaseScheme, BaseColours.Scales> {
        @Override public List<Knot<BaseScheme>> knots() {
            return List.of(new Knot<>(new BaseColours.Clear(), -1), new Knot<>(new BaseColours.PrimaryNeutral(), 0),
                           new Knot<>(new BaseColours.Title(), 1));
        }
    }

    /** Its label along the degree: the text's to 0.4, the ink on the house colour from 0.6 - turned where its ground turns (D41). */
    public record ButtonLabel() implements ColorScale<BaseScheme, BaseColours.Scales> {
        @Override public List<Knot<BaseScheme>> knots() {
            return List.of(new Knot<>(new BaseColours.Text(), -1), new Knot<>(new BaseColours.Text(), 0.4),
                           new Knot<>(new BaseColours.OnInverted(), 0.6), new Knot<>(new BaseColours.OnInverted(), 1));
        }
    }

    @Override public StylingRes<BaseScheme, HouseControls.PrimaryButton> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Title(), new BaseColours.Ring(), new ButtonGround(),
                new ButtonLabel());
    }
}
