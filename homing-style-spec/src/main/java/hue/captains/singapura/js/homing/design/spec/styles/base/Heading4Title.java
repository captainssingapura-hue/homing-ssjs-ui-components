package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** base's look of heading4_title: CSS of its own. */
public record Heading4Title() implements CastingStylePerRole<BaseScheme, Base, HouseText.Heading4, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, Base, HouseText.Heading4, HouseSaying.Title> style() {
        return new StylingRes.CssContent<>(new BaseColours.HeadingInk());
    }
}
