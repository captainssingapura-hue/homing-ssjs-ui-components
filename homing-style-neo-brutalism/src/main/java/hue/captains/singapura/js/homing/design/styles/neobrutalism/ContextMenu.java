package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The context menu in neo-brutalism: a menu opened where it is asked - raised, framed, its corner the style's, casting
 * its shadow, in the style's face; its items and their lines its parts'. Whole: every property its look has (D9).
 */
public record ContextMenu()
        implements CastingStyle<BaseScheme, NeoBrutalism, HouseContainers.ContextMenu, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.ContextMenu> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Inverted(), new BaseColours.Text(),
                new BaseColours.Border());
    }
}
