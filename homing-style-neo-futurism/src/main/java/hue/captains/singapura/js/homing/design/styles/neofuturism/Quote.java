package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/**
 * The quote in neo-futurism: words quoted from elsewhere, ruled along their leading edge in the hairline, set in from
 * the rule, quieter than the text round them. Whole: every property its look has (D9).
 */
public record Quote() implements CastingStyle<BaseScheme, NeoFuturism, HouseRegions.Quote, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseRegions.Quote> style() {
        return new StylingRes.CssContent<>(new HouseColours.TextFaded(), new HouseColours.Border());
    }
}
