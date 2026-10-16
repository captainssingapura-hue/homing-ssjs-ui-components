package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/**
 * The block of code in the base layer: lines of code kept as written, set into the page - recessed, in the text's ink,
 * a hairline round them - in the system's monospace. Whole: every property its look has (D9).
 */
public record CodeBlock() implements CastingStyle<BaseScheme, Base, HouseRegions.CodeBlock, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseRegions.CodeBlock> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Recessed(), new HouseColours.Text(), new HouseColours.Border());
    }
}
