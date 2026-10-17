package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** editorial's implementation of select_what: as its component standalone, by delegation. */
public record SelectWhat() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.Select, HouseDoing.What> {
    @Override public StylingRes<BaseScheme, Editorial, HouseControls.Select, HouseDoing.What> style() {
        return new StylingRes.RootDelegation<>(new Select());
    }
}
