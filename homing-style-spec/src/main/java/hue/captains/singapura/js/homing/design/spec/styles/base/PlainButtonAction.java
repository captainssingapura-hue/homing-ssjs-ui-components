package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** base's implementation of plain-button_action: as its component standalone, by delegation. */
public record PlainButtonAction() implements CastingStylePerRole<BaseScheme, Base, HouseControls.PlainButton, HouseDoing.Action> {
    @Override public StylingRes<BaseScheme, Base, HouseControls.PlainButton, HouseDoing.Action> style() {
        return new StylingRes.RootDelegation<>(new PlainButton());
    }
}
