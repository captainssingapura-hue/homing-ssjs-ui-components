package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-brutalism's look of select_how: as its component on its own, by delegation. */
public record SelectHow() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseControls.Select, HouseDoing.How> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseControls.Select, HouseDoing.How> style() {
        return new StylingRes.RootDelegation<>(new Select());
    }
}
