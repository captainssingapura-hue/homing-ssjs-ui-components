package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The toggle knob in neo-brutalism: what shows which way it stands, sliding in its groove; its measure the switch's.
 * Whole: every property its look has (D9).
 */
public record ToggleKnob() implements CastingStyle<BaseScheme, NeoBrutalism, HouseControls.ToggleKnob, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.ToggleKnob> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised());
    }
}
