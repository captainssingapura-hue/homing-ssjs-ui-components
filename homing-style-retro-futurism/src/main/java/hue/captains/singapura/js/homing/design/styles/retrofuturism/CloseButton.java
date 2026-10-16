package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The close button in retro-futurism: the button that closes what holds it: bare, turning to danger under the hand.
 * Whole: every property its look has (D9).
 */
public record CloseButton()
        implements CastingStyle<BaseScheme, RetroFuturism, HouseControls.CloseButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.CloseButton> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.OnDanger(), new BaseColours.Border(), new BaseColours.Ring(),
                new BaseColours.Danger(), new BaseColours.DangerPressed());
    }
}
