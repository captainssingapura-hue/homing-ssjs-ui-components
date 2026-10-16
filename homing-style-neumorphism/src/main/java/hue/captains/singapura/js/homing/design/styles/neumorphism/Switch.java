package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The switch in neumorphism: a setting on or off - the button its groove and its words sit in; its groove and knob are
 * its parts'. Whole: every property its look has (D9).
 */
public record Switch() implements CastingStyle<BaseScheme, Neumorphism, HouseControls.Switch, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Switch> style() {
        return new StylingRes.CssContent<>(new HouseColours.Ring());
    }
}
