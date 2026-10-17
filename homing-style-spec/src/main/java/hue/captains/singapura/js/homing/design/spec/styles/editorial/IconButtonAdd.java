package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** editorial's implementation of icon-button_add: as its component standalone, by delegation. */
public record IconButtonAdd() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.IconButton, HouseDoing.Add> {
    @Override public StylingRes<BaseScheme, Editorial, HouseControls.IconButton, HouseDoing.Add> style() {
        return new StylingRes.RootDelegation<>(new IconButton());
    }
}
