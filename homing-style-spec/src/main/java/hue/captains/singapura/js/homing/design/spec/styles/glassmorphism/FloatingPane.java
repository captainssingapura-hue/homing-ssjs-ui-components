package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** glassmorphism's implementation of floating-pane: CSS of its own. */
public record FloatingPane() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseContainers.FloatingPane, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseContainers.FloatingPane, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Ring(), new BaseColours.ShadeFirm(), new BaseColours.ShadeHeavy(), new BaseColours.ShadeSolid(), new BaseColours.ShadeThick(), new BaseColours.GlowMedium());
    }
}
