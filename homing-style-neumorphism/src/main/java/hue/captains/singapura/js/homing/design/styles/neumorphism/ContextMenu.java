package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The context menu in neumorphism: a menu opened where it is asked - raised, framed, its corner the style's, casting
 * its shadow, in the style's face; its items and their lines its parts'. Whole: every property its look has (D9).
 */
public record ContextMenu()
        implements CastingStyle<ColorScheme.Base, Neumorphism, HouseContainers.ContextMenu, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.ContextMenu> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Text(), new HouseColours.Border(),
                new HouseColours.ShadeFirm());
    }
}
