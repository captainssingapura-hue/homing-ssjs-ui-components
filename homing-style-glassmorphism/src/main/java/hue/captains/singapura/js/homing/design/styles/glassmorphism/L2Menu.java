package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The level-2 menu in glassmorphism: a menu opened where it is asked - raised, framed, its corner the style's, casting
 * its shadow, in the style's face, what is behind it blurred; its items and their lines its parts'. Whole: every
 * property its look has (D9).
 */
public record L2Menu()
        implements CastingStyle<BaseScheme, Glassmorphism, HouseContainers.L2Menu, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.L2Menu> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(),
                new BaseColours.ShadeThick());
    }
}
