package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** retro-futurism's implementation of tab: CSS of its own. */
public record Tab() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseControls.Tab, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseControls.Tab, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Text(), new BaseColours.OnInverted(), new BaseColours.Border(), new BaseColours.Title(), new BaseColours.AccentWashFaint(), new BaseColours.AccentWashSoft(), new BaseColours.GlowSoft(), new BaseColours.GlowFull(), new BaseColours.GlowFlare());
    }
}
