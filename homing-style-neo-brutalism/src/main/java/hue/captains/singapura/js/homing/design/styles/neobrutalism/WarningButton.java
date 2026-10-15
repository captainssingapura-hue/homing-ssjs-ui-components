package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The warning button in neo-brutalism: an action that needs a second thought. Whole: every property its look has (D9).
 */
public record WarningButton()
        implements CastingStyle<ColorScheme.Base, NeoBrutalism, HouseControls.WarningButton, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.WarningButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Inverted(), new HouseColours.WarningInk(), new HouseColours.Border(),
                new HouseColours.Ring(), new HouseColours.WarningGround());
    }
}
