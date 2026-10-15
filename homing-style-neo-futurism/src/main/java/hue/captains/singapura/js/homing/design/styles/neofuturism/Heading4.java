package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The heading 4 in neo-futurism: a heading at the fourth level. Whole: every property its look has (D9). */
public record Heading4() implements CastingStyle<ColorScheme.Base, NeoFuturism, HouseText.Heading4, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Heading4> style() {
        return new StylingRes.CssContent<>(new HouseColours.HeadingInk());
    }
}
