package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScale;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/**
 * The secondary button in editorial: an action beside the primary one, in the accent as far as its degree. Whole: every
 * property its look has (D9).
 */
public record SecondaryButton()
        implements CastingStyle<ColorScheme.Base, Editorial, HouseControls.SecondaryButton, RoleRoot> {
    /** Its ground along the degree: nothing at -1, each palette's neutral at 0, the accent at 1 (D41). */
    public record ButtonGround() implements ColorScale<ColorScheme.Base, HouseColours.Scales> {
        @Override public List<Knot<ColorScheme.Base>> knots() {
            return List.of(new Knot<>(new HouseColours.Clear(), -1), new Knot<>(new HouseColours.SecondaryNeutral(), 0),
                           new Knot<>(new HouseColours.Accent(), 1));
        }
    }

    /** Its label along the degree: the text's to 0.4, the ink on the accent from 0.6 - turned where its ground turns (D41). */
    public record ButtonLabel() implements ColorScale<ColorScheme.Base, HouseColours.Scales> {
        @Override public List<Knot<ColorScheme.Base>> knots() {
            return List.of(new Knot<>(new HouseColours.Text(), -1), new Knot<>(new HouseColours.Text(), 0.4),
                           new Knot<>(new HouseColours.OnAccent(), 0.6), new Knot<>(new HouseColours.OnAccent(), 1));
        }
    }

    @Override public StylingRes<ColorScheme.Base, HouseControls.SecondaryButton> style() {
        return new StylingRes.CssContent<>(
                new ButtonLabel(), new HouseColours.AccentEdge(), new HouseColours.Ring(),
                new HouseColours.ShadeFaint(), new HouseColours.ShadeLight(), new HouseColours.ShadeMedium(),
                new ButtonGround());
    }
}
