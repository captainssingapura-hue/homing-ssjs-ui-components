package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The separator in editorial: a line between groups - its foot ruled in the colour it is given. Whole: every property
 * its look has (D9).
 */
public record Separator() implements CastingStyle<BaseScheme, Editorial, HouseMarks.Separator, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.Separator> style() {
        return new StylingRes.CssContent<>(new HouseColours.Hairline());
    }
}
