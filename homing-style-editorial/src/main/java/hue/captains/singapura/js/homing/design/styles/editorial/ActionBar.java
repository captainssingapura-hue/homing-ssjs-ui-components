package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The action bar in editorial: a row of actions at the foot of what it ends - raised, capped off by a rule along its
 * top. Whole: every property its look has (D9).
 */
public record ActionBar() implements CastingStyle<BaseScheme, Editorial, HouseContainers.ActionBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.ActionBar> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Border());
    }
}
