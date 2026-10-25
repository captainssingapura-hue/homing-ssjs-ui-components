package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/**
 * The check menu item in neo-futurism: one choice of a menu - flat, tinted under the hand and where the menu's cursor is,
 * faded while off. Whole: every property its look has (D9).
 */
public record CheckMenuItem() implements CastingStyle<BaseScheme, NeoFuturism, HouseItems.CheckMenuItem, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseItems.CheckMenuItem> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.AccentWashSoft(),
                new BaseColours.AccentWashStrong());
    }
}
