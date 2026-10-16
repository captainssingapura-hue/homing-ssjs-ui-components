package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** The slider groove in sketchy: the line its value is set along. Whole: every property its look has (D9). */
public record SliderGroove() implements CastingStyle<BaseScheme, Sketchy, HouseMarks.SliderGroove, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.SliderGroove> style() {
        return new StylingRes.CssContent<>(new HouseColours.WashSoft(), new HouseColours.GrooveEdge());
    }
}
