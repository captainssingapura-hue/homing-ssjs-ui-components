package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** retro-futurism's implementation of panel-bar: CSS of its own. */
public record PanelBar() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseContainers.PanelBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseContainers.PanelBar, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent());
    }
}
