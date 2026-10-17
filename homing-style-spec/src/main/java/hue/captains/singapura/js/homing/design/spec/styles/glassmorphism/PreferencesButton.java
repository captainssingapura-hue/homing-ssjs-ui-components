package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** glassmorphism's implementation of preferences-button: CSS of its own. */
public record PreferencesButton() implements CastingStylePerRole<BaseScheme, Glassmorphism, HouseControls.PreferencesButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, Glassmorphism, HouseControls.PreferencesButton, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Faint(), new BaseColours.Ring());
    }
}
