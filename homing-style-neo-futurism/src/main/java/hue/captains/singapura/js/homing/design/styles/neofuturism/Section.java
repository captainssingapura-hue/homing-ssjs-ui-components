package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/**
 * The section in neo-futurism: a stretch of a container's room given one job - a panel's body - its room round what it
 * holds. Whole: every property its look has (D9).
 */
public record Section() implements CastingStyle<ColorScheme.Base, NeoFuturism, HouseRegions.Section, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseRegions.Section> style() {
        return new StylingRes.CssContent<>();
    }
}
