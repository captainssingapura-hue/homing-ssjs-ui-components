package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The pane thumb in the base layer: a pane picked by a small picture of where it is - raised, and tinted where the
 * cursor is. Whole: every property its look has (D9).
 */
public record PaneThumb() implements CastingStyle<BaseScheme, Base, HouseControls.PaneThumb, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.PaneThumb> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Border(), new HouseColours.Accent(),
                new HouseColours.AccentTintFaint());
    }
}
