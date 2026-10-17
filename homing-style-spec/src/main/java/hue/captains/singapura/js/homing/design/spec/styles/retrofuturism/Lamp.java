package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** retro-futurism's implementation of lamp: CSS of its own. */
public record Lamp() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseMarks.Lamp, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseMarks.Lamp, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Text(), new BaseColours.OnDanger(), new BaseColours.Quiet(), new BaseColours.Accent(), new BaseColours.AccentWashLight(), new BaseColours.Danger());
    }
}
