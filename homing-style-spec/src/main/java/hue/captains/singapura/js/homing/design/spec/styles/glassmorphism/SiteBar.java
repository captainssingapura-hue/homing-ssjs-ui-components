package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** glassmorphism's implementation of site-bar: CSS of its own. */
public record SiteBar() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseContainers.SiteBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseContainers.SiteBar, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Inverted(), new BaseColours.OnBar(), new BaseColours.Accent());
    }
}
