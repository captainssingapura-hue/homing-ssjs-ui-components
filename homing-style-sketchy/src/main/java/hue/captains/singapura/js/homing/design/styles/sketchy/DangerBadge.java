package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/**
 * The danger badge in sketchy: danger said by a badge, its ink on its wash. Whole: every property its look has (D9).
 */
public record DangerBadge() implements CastingStyle<BaseScheme, Sketchy, HouseText.DangerBadge, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseText.DangerBadge> style() {
        return new StylingRes.CssContent<>(new HouseColours.DangerInk(), new HouseColours.DangerWash());
    }
}
