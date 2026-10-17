package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The warning button in glassmorphism: an action that needs a second thought. Whole: every property its look has (D9).
 */
public record WarningButton()
        implements CastingStyle<BaseScheme, Glassmorphism, HouseControls.WarningButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.WarningButton> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.WarningInk(), new BaseColours.Border(), new BaseColours.Ring(),
                new BaseColours.ShadeSoft(), new BaseColours.ShadeFirm(), new BaseColours.Sheen(),
                new BaseColours.WarningGround());
    }
}
