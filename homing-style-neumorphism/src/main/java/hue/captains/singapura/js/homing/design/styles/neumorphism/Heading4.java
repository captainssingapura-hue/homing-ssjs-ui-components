package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The heading 4 in neumorphism: a heading at the fourth level. Whole: every property its look has (D9). */
public record Heading4() implements CastingStyle<BaseScheme, Neumorphism, HouseText.Heading4, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Heading4> style() {
        return new StylingRes.CssContent<>(new BaseColours.HeadingInk());
    }
}
