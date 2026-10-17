package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** glassmorphism's look of section: CSS of its own. */
public record Section() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseRegions.Section, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseRegions.Section, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }
}
