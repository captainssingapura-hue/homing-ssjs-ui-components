package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The edge strip in neo-brutalism: a strip along an edge, shown when its lip is reached - raised, ruled along the edge
 * it comes from, casting its shadow over what it covers. It shows no state of its own. Whole: every property its look
 * has (D9).
 */
public record EdgeStrip() implements CastingStyle<BaseScheme, NeoBrutalism, HouseContainers.EdgeStrip, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.EdgeStrip> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Inverted(), new HouseColours.Text(),
                new HouseColours.Border());
    }
}
