package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** sketchy's implementation of quote: CSS of its own. */
public record Quote() implements CastingStylePerRole<BaseScheme, Sketchy, HouseRegions.Quote, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseRegions.Quote, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.TextFaded(), new BaseColours.Border());
    }
}
