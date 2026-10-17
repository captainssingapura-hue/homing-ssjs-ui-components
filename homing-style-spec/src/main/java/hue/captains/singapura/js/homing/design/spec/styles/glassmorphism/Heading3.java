package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** glassmorphism's implementation of heading3: CSS of its own. */
public record Heading3() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseText.Heading3, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseText.Heading3, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.HeadingInk());
    }
}
