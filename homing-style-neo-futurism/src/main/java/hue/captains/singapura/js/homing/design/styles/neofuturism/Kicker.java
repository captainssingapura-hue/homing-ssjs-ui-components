package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The kicker in neo-futurism: the small line set above a title. Whole: every property its look has (D9). */
public record Kicker() implements CastingStyle<BaseScheme, NeoFuturism, HouseText.Kicker, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.Kicker> style() {
        return new StylingRes.CssContent<>(new BaseColours.KickerInk());
    }
}
