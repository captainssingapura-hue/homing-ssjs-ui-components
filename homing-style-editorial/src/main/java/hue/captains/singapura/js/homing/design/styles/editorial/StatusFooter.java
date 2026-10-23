package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The status footer in editorial: a bar of status along the foot of what it ends, read-only - raised, ruled along the edge it
 * comes from, casting its shadow over what it covers. It shows no state of its own. Whole: every property its look has
 * (D9).
 */
public record StatusFooter() implements CastingStyle<BaseScheme, Editorial, HouseContainers.StatusFooter, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.StatusFooter> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(),
                new BaseColours.ShadeSolid(), new BaseColours.ShadeDense());
    }
}
