package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The panel in sketchy: a named region that fills what holds it - raised, framed, its corner the style's; flat at every
 * elevation, sketchy drawing no depth; its edge in the accent while it is the current one. Whole: every property its
 * look has (D9).
 */
public record Panel() implements CastingStyle<ColorScheme.Base, Sketchy, HouseContainers.Panel, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.Panel> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Text(), new HouseColours.Border(), new HouseColours.Accent());
    }
}
