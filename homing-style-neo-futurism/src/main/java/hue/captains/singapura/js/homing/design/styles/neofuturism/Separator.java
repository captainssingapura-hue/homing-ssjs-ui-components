package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The separator in neo-futurism: a line between groups - its foot ruled in the colour it is given. Whole: every
 * property its look has (D9).
 */
public record Separator() implements CastingStyle<BaseScheme, NeoFuturism, HouseMarks.Separator, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.Separator> style() {
        return new StylingRes.CssContent<>(new BaseColours.Hairline());
    }
}
