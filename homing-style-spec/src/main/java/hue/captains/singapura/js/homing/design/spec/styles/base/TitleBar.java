package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** base's implementation of title-bar: CSS of its own. */
public record TitleBar() implements CastingStylePerRole<BaseScheme, Base, HouseContainers.TitleBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseContainers.TitleBar, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Inverted(), new BaseColours.OnInverted(), new BaseColours.OnBar(), new BaseColours.Border(), new BaseColours.Title());
    }
}
