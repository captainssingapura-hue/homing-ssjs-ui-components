package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The warning button in glassmorphism: an action that needs a second thought. Whole: every property its look has (D9).
 */
public record WarningButton()
        implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseControls.WarningButton, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.WarningButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.WarningInk(), new HouseColours.Border(), new HouseColours.Ring(),
                new HouseColours.ShadeSoft(), new HouseColours.ShadeFirm(), new HouseColours.Sheen(),
                new HouseColours.WarningGround());
    }
}
