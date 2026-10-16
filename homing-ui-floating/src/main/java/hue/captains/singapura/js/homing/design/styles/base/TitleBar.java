package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The title bar in the base layer: heads a pane that floats - the inverted layer, the page's ground written on it,
 * ruled along its foot; where the hand takes it, and in the house's colour while the hand has it. Whole: every property
 * its look has (D9).
 */
public record TitleBar() implements CastingStyle<BaseScheme, Base, HouseContainers.TitleBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.TitleBar> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Inverted(), new HouseColours.OnInverted(), new HouseColours.OnBar(),
                new HouseColours.Border(), new HouseColours.Title());
    }
}
