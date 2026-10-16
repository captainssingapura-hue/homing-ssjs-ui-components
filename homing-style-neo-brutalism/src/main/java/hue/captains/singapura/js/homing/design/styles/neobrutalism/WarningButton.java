package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The warning button in neo-brutalism: an action that needs a second thought. Whole: every property its look has (D9).
 */
public record WarningButton()
        implements CastingStyle<BaseScheme, NeoBrutalism, HouseControls.WarningButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.WarningButton> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Inverted(), new BaseColours.WarningInk(), new BaseColours.Border(),
                new BaseColours.Ring(), new BaseColours.WarningGround());
    }
}
