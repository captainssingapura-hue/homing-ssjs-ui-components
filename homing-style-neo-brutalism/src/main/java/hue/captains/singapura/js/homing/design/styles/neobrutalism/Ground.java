package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The ground in neo-brutalism: the surface a page stands on - its ground, the ink and the face of what is written on
 * it, and its scrollbar, its thumb the ink half into the ground. Whole: every property its look has (D9).
 */
public record Ground() implements CastingStyle<ColorScheme.Base, NeoBrutalism, HouseContainers.Ground, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.Ground> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Ground(), new HouseColours.ScrollThumb(), new HouseColours.Text());
    }
}
