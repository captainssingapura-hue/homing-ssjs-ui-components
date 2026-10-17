package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** sketchy's look of heading4_title: CSS of its own. */
public record Heading4Title() implements CastingStylePerRole<BaseScheme, Sketchy, HouseText.Heading4, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseText.Heading4, HouseSaying.Title> style() {
        return new StylingRes.CssContent<>(new BaseColours.HeadingInk());
    }
}
