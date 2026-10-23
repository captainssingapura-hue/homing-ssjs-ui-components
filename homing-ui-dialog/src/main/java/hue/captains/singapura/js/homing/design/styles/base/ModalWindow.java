package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/** The modal window in the base layer: no look of its own - its veil and its window draw; every style's alike. */
public record ModalWindow() implements CastingStyle<BaseScheme, Base, HouseContainers.ModalWindow, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.ModalWindow> style() {
        return new StylingRes.CssContent<>();
    }
}
