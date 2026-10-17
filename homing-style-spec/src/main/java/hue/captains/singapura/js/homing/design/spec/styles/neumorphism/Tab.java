package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** neumorphism's implementation of tab: CSS of its own. */
public record Tab() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.Tab, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseControls.Tab, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.OnInverted(), new BaseColours.Border(), new BaseColours.Title(), new BaseColours.AccentWashFaint(), new BaseColours.AccentWashSoft(), new BaseColours.ShadeFirm());
    }
}
