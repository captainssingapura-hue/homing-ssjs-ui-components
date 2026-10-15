package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The success button in glassmorphism: an action that confirms what went well. Whole: every property its look has (D9).
 */
public record SuccessButton()
        implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseControls.SuccessButton, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.SuccessButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.SuccessInk(), new HouseColours.Border(), new HouseColours.Ring(),
                new HouseColours.ShadeSoft(), new HouseColours.ShadeFirm(), new HouseColours.Sheen(),
                new HouseColours.SuccessGround());
    }
}
