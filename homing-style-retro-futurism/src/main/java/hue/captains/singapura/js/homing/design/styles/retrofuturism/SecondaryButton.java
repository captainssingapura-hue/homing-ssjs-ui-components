package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The secondary button in retro-futurism: an action beside the primary one, in the accent as far as its degree. Whole:
 * every property its look has (D9).
 */
public record SecondaryButton()
        implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseControls.SecondaryButton, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.SecondaryButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.OnAccent(), new HouseColours.AccentEdge(), new HouseColours.Ring(),
                new HouseColours.GlowDim(), new HouseColours.GlowMedium(), new HouseColours.GlowStrong(),
                new HouseColours.GlowFull(), new HouseColours.SecondaryGround());
    }
}
