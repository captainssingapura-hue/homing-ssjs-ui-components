package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The toggle button in glassmorphism: a button that is on or off: on, tinted with the accent and edged with it. Whole:
 * every property its look has (D9).
 */
public record ToggleButton()
        implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseControls.ToggleButton, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.ToggleButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Text(), new HouseColours.Border(),
                new HouseColours.AccentEdge(), new HouseColours.Ring(), new HouseColours.AccentTint(),
                new HouseColours.ShadeSoft(), new HouseColours.ShadeFirm(), new HouseColours.Sheen());
    }
}
