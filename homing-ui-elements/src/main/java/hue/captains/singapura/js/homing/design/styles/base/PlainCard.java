package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The plain card in the base layer: a frame its caller fills - its ground, its edge, its corner, its shadow and its
 * measure; still under the hand. Whole: every property its look has (D9).
 */
public record PlainCard() implements CastingStyle<BaseScheme, Base, HouseContainers.PlainCard, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.PlainCard> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Border());
    }
}
