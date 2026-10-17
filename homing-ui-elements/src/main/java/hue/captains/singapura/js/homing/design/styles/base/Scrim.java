package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The scrim in the base layer: the veil over the page behind a dialog - nothing of its own drawn, what is behind it
 * dimmed and softened. Whole: every property its look has (D9).
 */
public record Scrim() implements CastingStyle<BaseScheme, Base, HouseContainers.Scrim, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.Scrim> style() {
        return new StylingRes.CssContent<>();
    }
}
