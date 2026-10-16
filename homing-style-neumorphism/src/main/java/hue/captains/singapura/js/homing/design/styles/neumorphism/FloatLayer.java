package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The float layer in neumorphism: the desk floating panes are stacked on - recessed below the page. The keys' marks on
 * it draw nothing, as before. Whole: every property its look has (D9).
 */
public record FloatLayer()
        implements CastingStyle<BaseScheme, Neumorphism, HouseContainers.FloatLayer, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.FloatLayer> style() {
        return new StylingRes.CssContent<>(new HouseColours.Recessed());
    }
}
