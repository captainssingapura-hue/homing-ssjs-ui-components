package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** sketchy's implementation of icon-button_add: as its component standalone, by delegation. */
public record IconButtonAdd() implements CastingStylePerRole<BaseScheme, Sketchy, HouseControls.IconButton, HouseDoing.Add> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseControls.IconButton, HouseDoing.Add> style() {
        return new StylingRes.RootDelegation<>(new IconButton());
    }
}
