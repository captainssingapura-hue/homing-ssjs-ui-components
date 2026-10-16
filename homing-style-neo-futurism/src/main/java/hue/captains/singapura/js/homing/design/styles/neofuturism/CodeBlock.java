package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/**
 * The block of code in neo-futurism: lines of code kept as written, set into the page - recessed, in the text's ink, a
 * hairline round them - in the system's monospace. Whole: every property its look has (D9).
 */
public record CodeBlock() implements CastingStyle<BaseScheme, NeoFuturism, HouseRegions.CodeBlock, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseRegions.CodeBlock> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Recessed(), new BaseColours.Text(), new BaseColours.Border());
    }
}
