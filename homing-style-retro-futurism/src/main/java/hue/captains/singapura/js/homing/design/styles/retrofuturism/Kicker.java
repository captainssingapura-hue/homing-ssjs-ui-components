package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The kicker in retro-futurism: the small line set above a title. Whole: every property its look has (D9). */
public record Kicker() implements CastingStyle<BaseScheme, RetroFuturism, HouseText.Kicker, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Kicker> style() {
        return new StylingRes.CssContent<>(new HouseColours.KickerInk());
    }
}
