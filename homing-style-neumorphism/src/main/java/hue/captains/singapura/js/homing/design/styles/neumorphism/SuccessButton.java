package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The success button in neumorphism: an action that confirms what went well. Whole: every property its look has (D9).
 */
public record SuccessButton()
        implements CastingStyle<BaseScheme, Neumorphism, HouseControls.SuccessButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.SuccessButton> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.SuccessInk(), new BaseColours.Border(),
                new BaseColours.Ring(), new BaseColours.ShadeFirm(), new BaseColours.SuccessGround());
    }
}
