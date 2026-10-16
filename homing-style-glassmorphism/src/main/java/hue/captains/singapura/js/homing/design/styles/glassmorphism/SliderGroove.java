package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** The slider groove in glassmorphism: the line its value is set along. Whole: every property its look has (D9). */
public record SliderGroove()
        implements CastingStyle<BaseScheme, Glassmorphism, HouseMarks.SliderGroove, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.SliderGroove> style() {
        return new StylingRes.CssContent<>(new BaseColours.WashSoft(), new BaseColours.GrooveEdge());
    }
}
