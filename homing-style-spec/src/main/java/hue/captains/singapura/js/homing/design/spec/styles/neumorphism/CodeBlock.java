package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** neumorphism's implementation of code-block: CSS of its own. */
public record CodeBlock() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseRegions.CodeBlock, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseRegions.CodeBlock, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Recessed(), new BaseColours.Text(), new BaseColours.Border());
    }
}
