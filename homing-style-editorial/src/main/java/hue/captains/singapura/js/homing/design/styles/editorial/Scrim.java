package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The scrim in editorial: the veil over the page behind a dialog - nothing of its own drawn, what is behind it filtered
 * the style's way. Whole: every property its look has (D9).
 */
public record Scrim() implements CastingStyle<ColorScheme.Base, Editorial, HouseContainers.Scrim, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.Scrim> style() {
        return new StylingRes.CssContent<>();
    }
}
