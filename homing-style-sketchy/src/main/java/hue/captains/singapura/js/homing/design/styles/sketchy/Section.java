package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/**
 * The section in sketchy: a stretch of a container's room given one job - a panel's body - its room round what it
 * holds. Whole: every property its look has (D9).
 */
public record Section() implements CastingStyle<BaseScheme, Sketchy, HouseRegions.Section, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseRegions.Section> style() {
        return new StylingRes.CssContent<>();
    }
}
