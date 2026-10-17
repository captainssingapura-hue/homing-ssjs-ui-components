package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** retro-futurism's implementation of code-block: CSS of its own. */
public record CodeBlock() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseRegions.CodeBlock, RoleRoot> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseRegions.CodeBlock, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Recessed(), new BaseColours.Text(), new BaseColours.Border());
    }
}
