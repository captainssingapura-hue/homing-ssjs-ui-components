package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The switch in neo-brutalism: a setting on or off - the button its groove and its words sit in; its groove and knob
 * are its parts'. Whole: every property its look has (D9).
 */
public record Switch() implements CastingStyle<BaseScheme, NeoBrutalism, HouseControls.Switch, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.Switch> style() {
        return new StylingRes.CssContent<>(new BaseColours.Ring());
    }
}
