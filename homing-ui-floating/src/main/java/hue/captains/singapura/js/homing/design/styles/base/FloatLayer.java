package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The float layer in the base layer: the desk floating panes are stacked on - recessed below the page. The keys' marks
 * on it draw nothing, as before. Whole: every property its look has (D9).
 */
public record FloatLayer() implements CastingStyle<BaseScheme, Base, HouseContainers.FloatLayer, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.FloatLayer> style() {
        return new StylingRes.CssContent<>(new BaseColours.Recessed());
    }
}
