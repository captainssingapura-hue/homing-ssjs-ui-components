package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The plain card in neumorphism: a frame its caller fills - its ground, its edge, its corner, its shadow and its
 * measure; still under the hand. Whole: every property its look has (D9).
 */
public record PlainCard() implements CastingStyle<ColorScheme.Base, Neumorphism, HouseContainers.PlainCard, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.PlainCard> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Border(), new HouseColours.ShadeFirm());
    }
}
