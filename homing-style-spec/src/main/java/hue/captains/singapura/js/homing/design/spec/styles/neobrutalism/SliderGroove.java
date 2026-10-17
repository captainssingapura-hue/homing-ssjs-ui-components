package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** neo-brutalism's implementation of slider-groove: CSS of its own. */
public record SliderGroove() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseMarks.SliderGroove, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseMarks.SliderGroove, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.WashSoft(), new BaseColours.GrooveEdge());
    }
}
