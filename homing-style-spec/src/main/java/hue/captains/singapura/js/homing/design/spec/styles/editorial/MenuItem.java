package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** editorial's implementation of menu-item: CSS of its own. */
public record MenuItem() implements CastingStylePerRole<BaseScheme, Editorial, HouseItems.MenuItem, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseItems.MenuItem, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.AccentWashSoft(), new BaseColours.AccentWashStrong());
    }
}
