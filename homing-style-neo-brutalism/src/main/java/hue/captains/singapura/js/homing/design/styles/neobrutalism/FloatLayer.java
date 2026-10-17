package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The float layer in neo-brutalism: the desk floating panes are stacked on - recessed below the page. The keys' marks
 * on it draw nothing, as before. Whole: every property its look has (D9).
 */
public record FloatLayer()
        implements CastingStyle<BaseScheme, NeoBrutalism, HouseContainers.FloatLayer, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.FloatLayer> style() {
        return new StylingRes.CssContent<>(new BaseColours.Recessed());
    }
}
