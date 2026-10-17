package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The pane thumb in editorial: a pane picked by a small picture of where it is - raised, and tinted where the cursor
 * is. Whole: every property its look has (D9).
 */
public record PaneThumb() implements CastingStyle<BaseScheme, Editorial, HouseControls.PaneThumb, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.PaneThumb> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Border(), new BaseColours.Accent(),
                new BaseColours.AccentTintFaint());
    }
}
