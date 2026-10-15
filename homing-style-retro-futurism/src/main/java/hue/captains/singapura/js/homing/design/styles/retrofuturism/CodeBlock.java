package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/**
 * The block of code in retro-futurism: lines of code kept as written, set into the page - recessed, in the text's ink,
 * a hairline round them - in the system's monospace. Whole: every property its look has (D9).
 */
public record CodeBlock() implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseRegions.CodeBlock, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseRegions.CodeBlock> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Recessed(), new HouseColours.Text(), new HouseColours.Border());
    }
}
