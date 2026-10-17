package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** retro-futurism's implementation of menu-item: CSS of its own. */
public record MenuItem() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseItems.MenuItem, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseItems.MenuItem, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.AccentWashSoft(), new BaseColours.AccentWashStrong());
    }
}
