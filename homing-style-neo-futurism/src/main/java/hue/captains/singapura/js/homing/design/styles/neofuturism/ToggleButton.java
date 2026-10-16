package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The toggle button in neo-futurism: a button that is on or off: on, tinted with the accent and edged with it. Whole:
 * every property its look has (D9).
 */
public record ToggleButton()
        implements CastingStyle<BaseScheme, NeoFuturism, HouseControls.ToggleButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.ToggleButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Text(), new HouseColours.Border(),
                new HouseColours.AccentEdge(), new HouseColours.Ring(), new HouseColours.AccentTint(),
                new HouseColours.ShadeSoft(), new HouseColours.GlowFaint(), new HouseColours.GlowDim(),
                new HouseColours.GlowSoft(), new HouseColours.GlowBright());
    }
}
