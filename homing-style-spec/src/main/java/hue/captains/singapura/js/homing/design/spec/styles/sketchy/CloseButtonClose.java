package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** sketchy's look of close-button_close: as its component on its own, by delegation. */
public record CloseButtonClose() implements CastingStylePerRole<BaseScheme, Sketchy, HouseControls.CloseButton, HouseDoing.Close> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseControls.CloseButton, HouseDoing.Close> style() {
        return new StylingRes.RootDelegation<>(new CloseButton());
    }
}
