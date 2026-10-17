package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The tab strip in editorial: a pane's tabs in a row - raised, ruled along its foot in the accent, as tall as a tab and
 * its room, the hint of the accent across it while its pane is the one worked in. Its room is its tail's. Whole: every
 * property its look has (D9).
 */
public record TabStrip() implements CastingStyle<BaseScheme, Editorial, HouseContainers.TabStrip, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.TabStrip> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Accent(), new BaseColours.AccentTintHint());
    }
}
