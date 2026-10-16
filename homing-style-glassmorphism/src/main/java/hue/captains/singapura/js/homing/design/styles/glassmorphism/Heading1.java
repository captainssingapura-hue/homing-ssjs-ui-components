package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The heading 1 in glassmorphism: the heading of a page. Whole: every property its look has (D9). */
public record Heading1() implements CastingStyle<BaseScheme, Glassmorphism, HouseText.Heading1, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Heading1> style() {
        return new StylingRes.CssContent<>(new HouseColours.HeadingInk());
    }
}
