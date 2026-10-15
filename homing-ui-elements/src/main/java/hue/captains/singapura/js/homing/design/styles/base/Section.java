package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/**
 * The section in the base layer: a stretch of a container's room given one job - a panel's body - its room round what
 * it holds, none in the base layer, as before. Whole: every property its look has (D9).
 */
public record Section() implements CastingStyle<ColorScheme.Base, Base, HouseRegions.Section, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseRegions.Section> style() {
        return new StylingRes.CssContent<>();
    }
}
