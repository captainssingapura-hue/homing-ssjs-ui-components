package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The close button in sketchy: the button that closes what holds it: bare, turning to danger under the hand. Whole:
 * every property its look has (D9).
 */
public record CloseButton() implements CastingStyle<BaseScheme, Sketchy, HouseControls.CloseButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.CloseButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.OnDanger(), new HouseColours.Border(), new HouseColours.Ring(),
                new HouseColours.Danger(), new HouseColours.DangerPressed());
    }
}
