package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The warning button in neumorphism: an action that needs a second thought. Whole: every property its look has (D9).
 */
public record WarningButton()
        implements CastingStyle<BaseScheme, Neumorphism, HouseControls.WarningButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.WarningButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.WarningInk(), new HouseColours.Border(),
                new HouseColours.Ring(), new HouseColours.ShadeFirm(), new HouseColours.WarningGround());
    }
}
