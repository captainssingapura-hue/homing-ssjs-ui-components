package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's look of text-cell_title: as its component on its own, by delegation. */
public record TextCellTitle() implements CastingStylePerRole<BaseScheme, Base, HouseText.TextCell, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, Base, HouseText.TextCell, HouseSaying.Title> style() {
        return new StylingRes.RootDelegation<>(new TextCell());
    }
}
