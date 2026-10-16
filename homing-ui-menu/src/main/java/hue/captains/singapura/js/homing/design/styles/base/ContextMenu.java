package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The context menu in the base layer: a menu opened where it is asked - raised, framed, its corner square, casting
 * nothing, in the face it is opened on; its items and their lines its parts'. Whole: every property its look has (D9).
 */
public record ContextMenu() implements CastingStyle<BaseScheme, Base, HouseContainers.ContextMenu, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.ContextMenu> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Text(), new HouseColours.Border());
    }
}
