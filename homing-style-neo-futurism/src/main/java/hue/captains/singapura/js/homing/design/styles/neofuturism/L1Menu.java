package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The level-1 menu in neo-futurism: a menu opened where it is asked - raised, framed, its corner the style's, casting
 * its shadow, in the style's face; its items and their lines its parts'. Whole: every property its look has (D9).
 */
public record L1Menu()
        implements CastingStyle<BaseScheme, NeoFuturism, HouseContainers.L1Menu, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.L1Menu> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(),
                new BaseColours.ShadeThick(), new BaseColours.GlowSoft());
    }
}
