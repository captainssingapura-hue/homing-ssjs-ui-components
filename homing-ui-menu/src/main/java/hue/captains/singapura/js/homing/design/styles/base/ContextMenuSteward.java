package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMenus;

/**
 * The menus' steward in the base layer: no look of its own - the layer its menus open on, which they draw; every
 * style's alike.
 */
public record ContextMenuSteward()
        implements CastingStyle<ColorScheme.Base, Base, HouseMenus.ContextMenuSteward, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseMenus.ContextMenuSteward> style() {
        return new StylingRes.CssContent<>();
    }
}
