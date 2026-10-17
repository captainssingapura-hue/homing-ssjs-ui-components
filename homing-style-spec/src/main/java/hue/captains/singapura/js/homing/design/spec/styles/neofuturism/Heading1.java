package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neo-futurism's implementation of heading1: CSS of its own. */
public record Heading1() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseText.Heading1, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseText.Heading1, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.HeadingInk());
    }
}
