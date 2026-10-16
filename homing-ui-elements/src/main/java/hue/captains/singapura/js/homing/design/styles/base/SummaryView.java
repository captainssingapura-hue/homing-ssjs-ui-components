package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The summary's view in the base layer: one thing summed up, laid out whole - a view with no frame of its own, shown on
 * whatever holds it. Its texts and its link wear their own looks; its layout is its own, the same in every style.
 * Whole: every property its look has (D9).
 */
public record SummaryView() implements CastingStyle<BaseScheme, Base, HouseContainers.SummaryView, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.SummaryView> style() {
        return new StylingRes.CssContent<>();
    }
}
