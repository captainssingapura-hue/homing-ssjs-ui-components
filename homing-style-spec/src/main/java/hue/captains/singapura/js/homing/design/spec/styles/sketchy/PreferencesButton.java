package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** sketchy's implementation of preferences-button: CSS of its own. */
public record PreferencesButton() implements CastingStylePerRole<BaseScheme, Sketchy, HouseControls.PreferencesButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseControls.PreferencesButton, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Faint(), new BaseColours.Ring());
    }
}
