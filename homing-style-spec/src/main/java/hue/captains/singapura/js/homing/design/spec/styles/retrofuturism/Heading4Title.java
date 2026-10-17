package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** retro-futurism's look of heading4_title: CSS of its own. */
public record Heading4Title() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseText.Heading4, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseText.Heading4, HouseSaying.Title> style() {
        return new StylingRes.CssContent<>(new BaseColours.HeadingInk());
    }
}
