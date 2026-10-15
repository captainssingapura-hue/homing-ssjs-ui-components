package hue.captains.singapura.js.homing.design.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The close button in neo-brutalism: the button that closes what holds it: bare, turning to danger under the hand.
 * Whole: every property its look has (D9).
 */
public record CloseButton()
        implements CastingStyle<ColorScheme.Base, NeoBrutalism, HouseControls.CloseButton, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.CloseButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.OnDanger(), new HouseColours.Border(), new HouseColours.Ring(),
                new HouseColours.Danger(), new HouseColours.DangerPressed());
    }
}
