package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The tab strip in sketchy: a pane's tabs in a row - raised, ruled along its foot in the accent, as tall as a tab and
 * its room, the hint of the accent across it while its pane is the one worked in. Its room is its tail's. Whole: every
 * property its look has (D9).
 */
public record TabStrip() implements CastingStyle<ColorScheme.Base, Sketchy, HouseContainers.TabStrip, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.TabStrip> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Accent(), new HouseColours.AccentTintHint());
    }
}
