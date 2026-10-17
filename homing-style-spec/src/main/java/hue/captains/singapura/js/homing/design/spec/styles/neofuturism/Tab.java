package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** neo-futurism's implementation of tab: CSS of its own. */
public record Tab() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseControls.Tab, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseControls.Tab, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Text(), new BaseColours.OnInverted(), new BaseColours.Border(), new BaseColours.Title(), new BaseColours.AccentWashFaint(), new BaseColours.AccentWashSoft(), new BaseColours.ShadeSoft(), new BaseColours.GlowDim(), new BaseColours.GlowWarm(), new BaseColours.GlowStrong());
    }
}
