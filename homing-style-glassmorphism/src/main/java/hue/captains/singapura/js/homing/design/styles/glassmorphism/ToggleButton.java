package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The toggle button in glassmorphism: a button that is on or off: on, tinted with the accent and edged with it. Whole:
 * every property its look has (D9).
 */
public record ToggleButton()
        implements CastingStyle<BaseScheme, Glassmorphism, HouseControls.ToggleButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.ToggleButton> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(),
                new BaseColours.AccentEdge(), new BaseColours.Ring(), new BaseColours.AccentTint(),
                new BaseColours.ShadeSoft(), new BaseColours.ShadeFirm(), new BaseColours.Sheen());
    }
}
