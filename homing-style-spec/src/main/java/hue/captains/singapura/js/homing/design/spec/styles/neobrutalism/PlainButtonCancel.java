package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-brutalism's look of plain-button_cancel: as its component on its own, by delegation. */
public record PlainButtonCancel() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseControls.PlainButton, HouseDoing.Cancel> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseControls.PlainButton, HouseDoing.Cancel> style() {
        return new StylingRes.RootDelegation<>(new PlainButton());
    }
}
