package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The secondary button in sketchy: an action beside the primary one, in the accent as far as its degree. Whole: every
 * property its look has (D9).
 */
public record SecondaryButton()
        implements CastingStyle<ColorScheme.Base, Sketchy, HouseControls.SecondaryButton, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.SecondaryButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.OnAccent(), new HouseColours.AccentEdge(), new HouseColours.Ring(),
                new HouseColours.SecondaryGround());
    }
}
