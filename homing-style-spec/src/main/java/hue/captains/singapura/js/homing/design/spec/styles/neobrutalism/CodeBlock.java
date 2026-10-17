package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

/** neo-brutalism's implementation of code-block: CSS of its own. */
public record CodeBlock() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseRegions.CodeBlock, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseRegions.CodeBlock, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Recessed(), new BaseColours.Text(), new BaseColours.Border());
    }
}
