package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScale;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/**
 * The primary button in sketchy: the one action a view leads with, in the house's own colour as far as its degree.
 * Whole: every property its look has (D9).
 */
public record PrimaryButton()
        implements CastingStyle<ColorScheme.Base, Sketchy, HouseControls.PrimaryButton, RoleRoot> {
    /** Its ground along the degree: nothing at -1, each palette's neutral at 0, the house colour at 1 (D41). */
    public record ButtonGround() implements ColorScale<ColorScheme.Base, HouseColours.Scales> {
        @Override public List<Knot<ColorScheme.Base>> knots() {
            return List.of(new Knot<>(new HouseColours.Clear(), -1), new Knot<>(new HouseColours.PrimaryNeutral(), 0),
                           new Knot<>(new HouseColours.Title(), 1));
        }
    }

    /** Its label along the degree: the text's to 0.4, the ink on the house colour from 0.6 - turned where its ground turns (D41). */
    public record ButtonLabel() implements ColorScale<ColorScheme.Base, HouseColours.Scales> {
        @Override public List<Knot<ColorScheme.Base>> knots() {
            return List.of(new Knot<>(new HouseColours.Text(), -1), new Knot<>(new HouseColours.Text(), 0.4),
                           new Knot<>(new HouseColours.OnInverted(), 0.6), new Knot<>(new HouseColours.OnInverted(), 1));
        }
    }

    @Override public StylingRes<ColorScheme.Base, HouseControls.PrimaryButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Title(), new HouseColours.Ring(), new ButtonGround(),
                new ButtonLabel());
    }
}
