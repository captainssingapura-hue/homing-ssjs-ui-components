package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The panel bar in neo-brutalism: heads a panel - its room round its title and its controls, ruled under it in the
 * accent; nothing of its own ground. Whole: every property its look has (D9).
 */
public record PanelBar() implements CastingStyle<BaseScheme, NeoBrutalism, HouseContainers.PanelBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.PanelBar> style() {
        return new StylingRes.CssContent<>(new HouseColours.Accent());
    }
}
