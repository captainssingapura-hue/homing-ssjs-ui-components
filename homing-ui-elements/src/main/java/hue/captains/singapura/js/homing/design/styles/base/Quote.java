package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/**
 * The quote in the base layer: words quoted from elsewhere, ruled along their leading edge in the hairline, set in from
 * the rule, quieter than the text round them. Whole: every property its look has (D9).
 */
public record Quote() implements CastingStyle<ColorScheme.Base, Base, HouseRegions.Quote, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseRegions.Quote> style() {
        return new StylingRes.CssContent<>(new HouseColours.TextFaded(), new HouseColours.Border());
    }
}
