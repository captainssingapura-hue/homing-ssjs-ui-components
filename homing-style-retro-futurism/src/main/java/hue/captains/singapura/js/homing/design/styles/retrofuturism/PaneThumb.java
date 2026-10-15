package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The pane thumb in retro-futurism: a pane picked by a small picture of where it is - raised, and tinted where the
 * cursor is. Whole: every property its look has (D9).
 */
public record PaneThumb() implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseControls.PaneThumb, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.PaneThumb> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Border(), new HouseColours.Accent(),
                new HouseColours.AccentTintFaint());
    }
}
