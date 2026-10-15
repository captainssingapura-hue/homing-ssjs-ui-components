package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

/**
 * The pane of one tab in the base layer: a pane in a region of the page - raised, in the text's ink; the keys' rings as
 * a pane's are drawn, a candidate dashed, lent thin, held whole. A module's leaf: every style's alike. Whole: every
 * property its look has (D9).
 */
public record SingleTabPane() implements CastingStyle<ColorScheme.Base, Base, HousePanes.SingleTabPane, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HousePanes.SingleTabPane> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Text(), new HouseColours.Accent(),
                new HouseColours.KeysCandidate(), new HouseColours.KeysLent());
    }
}
