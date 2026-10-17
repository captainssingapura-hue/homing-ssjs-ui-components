package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** neo-brutalism's look of lamp: CSS of its own. */
public record Lamp() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseMarks.Lamp, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseMarks.Lamp, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.OnDanger(), new BaseColours.Border(), new BaseColours.Quiet(), new BaseColours.Accent(), new BaseColours.AccentWashLight(), new BaseColours.Danger());
    }
}
