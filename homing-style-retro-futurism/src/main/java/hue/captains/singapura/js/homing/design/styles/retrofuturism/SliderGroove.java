package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/** The slider groove in retro-futurism: the line its value is set along. Whole: every property its look has (D9). */
public record SliderGroove()
        implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseMarks.SliderGroove, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.SliderGroove> style() {
        return new StylingRes.CssContent<>(new HouseColours.WashSoft(), new HouseColours.GrooveEdge());
    }
}
