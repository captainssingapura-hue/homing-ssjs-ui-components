package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/**
 * The danger button in sketchy: an action that destroys or cannot be undone: danger at 1, safety turned the other way.
 * Whole: every property its look has (D9).
 */
public record DangerButton() implements CastingStyle<ColorScheme.Base, Sketchy, HouseControls.DangerButton, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.DangerButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Ring(), new HouseColours.DangerEdge(), new HouseColours.DangerGround(),
                new HouseColours.DangerWriting());
    }
}
