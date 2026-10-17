package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The dialog in the base layer: no look of its own - its veil, its window and its row of actions draw; every style's
 * alike.
 */
public record Dialog() implements CastingStyle<BaseScheme, Base, HouseContainers.Dialog, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.Dialog> style() {
        return new StylingRes.CssContent<>();
    }
}
