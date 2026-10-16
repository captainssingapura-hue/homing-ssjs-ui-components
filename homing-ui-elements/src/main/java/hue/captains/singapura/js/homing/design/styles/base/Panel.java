package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The panel in the base layer: a named region that fills what holds it - raised, framed, its corner square; flat at
 * every elevation, Base drawing no depth; its edge in the accent while it is the current one. Whole: every property its
 * look has (D9).
 */
public record Panel() implements CastingStyle<BaseScheme, Base, HouseContainers.Panel, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.Panel> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Text(), new HouseColours.Border(), new HouseColours.Accent());
    }
}
