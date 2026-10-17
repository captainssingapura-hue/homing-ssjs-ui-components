package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** neo-futurism's look of section: CSS of its own. */
public record Section() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseRegions.Section, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseRegions.Section, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
