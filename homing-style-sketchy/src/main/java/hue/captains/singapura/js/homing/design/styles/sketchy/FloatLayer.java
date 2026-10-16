package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The float layer in sketchy: the desk floating panes are stacked on - recessed below the page. The keys' marks on it
 * draw nothing, as before. Whole: every property its look has (D9).
 */
public record FloatLayer() implements CastingStyle<BaseScheme, Sketchy, HouseContainers.FloatLayer, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.FloatLayer> style() {
        return new StylingRes.CssContent<>(new HouseColours.Recessed());
    }
}
