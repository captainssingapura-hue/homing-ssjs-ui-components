package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The panel bar in editorial: heads a panel - its room round its title and its controls, ruled under it in the accent;
 * nothing of its own ground. Whole: every property its look has (D9).
 */
public record PanelBar() implements CastingStyle<ColorScheme.Base, Editorial, HouseContainers.PanelBar, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.PanelBar> style() {
        return new StylingRes.CssContent<>(new HouseColours.Accent());
    }
}
