package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The range in neo-brutalism: a value along a scale, the browser's own, drawn as neo-brutalism's own slider is - its groove, its fill to where it stands, its knob - by each engine's own parts. Whole: every property its look
 * has (D9).
 */
public record Range() implements CastingStyle<BaseScheme, NeoBrutalism, HouseControls.Range, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Range> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent(), new BaseColours.Ring(), new BaseColours.WashSoft(), new BaseColours.GrooveEdge(), new BaseColours.Raised());
    }
}
