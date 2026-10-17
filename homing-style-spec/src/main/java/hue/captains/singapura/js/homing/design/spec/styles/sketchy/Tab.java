package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** sketchy's implementation of tab: CSS of its own. */
public record Tab() implements CastingStylePerRole<BaseScheme, Sketchy, HouseControls.Tab, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseControls.Tab, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Text(), new BaseColours.OnInverted(), new BaseColours.Border(), new BaseColours.Title(), new BaseColours.AccentWashFaint(), new BaseColours.AccentWashSoft());
    }
}
