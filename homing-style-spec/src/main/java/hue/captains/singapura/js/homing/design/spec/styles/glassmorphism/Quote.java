package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** glassmorphism's implementation of quote: CSS of its own. */
public record Quote() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseRegions.Quote, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseRegions.Quote, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.TextFaded(), new BaseColours.Border());
    }
}
