package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The toggle groove in retro-futurism: the pill its knob slides in, in the accent when on; its measure the switch's.
 * Whole: every property its look has (D9).
 */
public record ToggleGroove()
        implements CastingStyle<BaseScheme, RetroFuturism, HouseMarks.ToggleGroove, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseMarks.ToggleGroove> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.WashSoft(), new BaseColours.GrooveEdge(), new BaseColours.Accent());
    }
}
