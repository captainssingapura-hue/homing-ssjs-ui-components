package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The panel bar in neumorphism: heads a panel - its room round its title and its controls, no rule under it; nothing of
 * its own ground. Whole: every property its look has (D9).
 */
public record PanelBar() implements CastingStyle<BaseScheme, Neumorphism, HouseContainers.PanelBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.PanelBar> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent());
    }
}
