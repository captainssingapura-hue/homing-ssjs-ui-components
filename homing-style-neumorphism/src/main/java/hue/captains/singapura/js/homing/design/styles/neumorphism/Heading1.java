package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The heading 1 in neumorphism: the heading of a page. Whole: every property its look has (D9). */
public record Heading1() implements CastingStyle<BaseScheme, Neumorphism, HouseText.Heading1, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Heading1> style() {
        return new StylingRes.CssContent<>(new HouseColours.HeadingInk());
    }
}
