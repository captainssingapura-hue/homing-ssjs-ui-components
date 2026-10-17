package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/**
 * The section in glassmorphism: a stretch of a container's room given one job - a panel's body - its room round what it
 * holds. Whole: every property its look has (D9).
 */
public record Section() implements CastingStyle<BaseScheme, Glassmorphism, HouseRegions.Section, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseRegions.Section> style() {
        return new StylingRes.CssContent<>();
    }
}
