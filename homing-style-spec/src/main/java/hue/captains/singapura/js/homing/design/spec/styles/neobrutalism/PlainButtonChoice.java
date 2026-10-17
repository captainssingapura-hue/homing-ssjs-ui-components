package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** neo-brutalism's look of plain-button_choice: as its component on its own, by delegation. */
public record PlainButtonChoice() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseControls.PlainButton, HouseDoing.Choice> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseControls.PlainButton, HouseDoing.Choice> style() {
        return new StylingRes.RootDelegation<>(new PlainButton());
    }
}
