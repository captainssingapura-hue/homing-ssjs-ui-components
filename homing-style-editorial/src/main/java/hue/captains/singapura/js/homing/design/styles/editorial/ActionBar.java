package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The action bar in editorial: a row of actions at the foot of what it ends - raised, capped off by a rule along its
 * top. Whole: every property its look has (D9).
 */
public record ActionBar() implements CastingStyle<ColorScheme.Base, Editorial, HouseContainers.ActionBar, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.ActionBar> style() {
        return new StylingRes.CssContent<>(new HouseColours.Raised(), new HouseColours.Border());
    }
}
