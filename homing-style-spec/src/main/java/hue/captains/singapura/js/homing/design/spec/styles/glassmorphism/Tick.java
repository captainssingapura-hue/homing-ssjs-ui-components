package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** glassmorphism's implementation of tick: CSS of its own. */
public record Tick() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseMarks.Tick, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseMarks.Tick, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Muted());
    }
}
