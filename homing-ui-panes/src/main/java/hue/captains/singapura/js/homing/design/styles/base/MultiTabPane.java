package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/**
 * The pane of many tabs in the base layer: a pane in a region of the page - raised, in the text's ink; the keys' rings
 * as a pane's are drawn, a candidate dashed, lent thin, held whole. A module's leaf: every style's alike. Whole: every
 * property its look has (D9).
 */
public record MultiTabPane() implements CastingStyle<BaseScheme, Base, HousePanes.MultiTabPane, RoleRoot> {
    @Override public StylingRes<BaseScheme, HousePanes.MultiTabPane> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Accent(),
                new BaseColours.KeysCandidate(), new BaseColours.KeysLent());
    }
}
