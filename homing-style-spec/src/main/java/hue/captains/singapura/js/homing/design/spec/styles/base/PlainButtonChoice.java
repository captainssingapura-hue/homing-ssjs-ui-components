package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** base's look of plain-button_choice: as its component on its own, by delegation. */
public record PlainButtonChoice() implements CastingStylePerRole<BaseScheme, Base, HouseControls.PlainButton, HouseDoing.Choice> {
    @Override public StylingRes<BaseScheme, Base, HouseControls.PlainButton, HouseDoing.Choice> style() {
        return new StylingRes.RootDelegation<>(new PlainButton());
    }
}
