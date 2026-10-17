package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** The plain button in neumorphism: a button that does one ordinary thing. Whole: every property its look has (D9). */
public record PlainButton()
        implements CastingStyle<BaseScheme, Neumorphism, HouseControls.PlainButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.PlainButton> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Ring(),
                new BaseColours.ShadeFirm());
    }
}
