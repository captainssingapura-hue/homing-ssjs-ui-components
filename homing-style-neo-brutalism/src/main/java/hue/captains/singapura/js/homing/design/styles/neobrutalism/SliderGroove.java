package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** The slider groove in neo-brutalism: the line its value is set along. Whole: every property its look has (D9). */
public record SliderGroove()
        implements CastingStyle<BaseScheme, NeoBrutalism, HouseMarks.SliderGroove, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.SliderGroove> style() {
        return new StylingRes.CssContent<>(new HouseColours.WashSoft(), new HouseColours.GrooveEdge());
    }
}
