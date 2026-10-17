package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** editorial's implementation of close-button: CSS of its own. */
public record CloseButton() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.CloseButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseControls.CloseButton, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.OnDanger(), new BaseColours.Border(), new BaseColours.Ring(), new BaseColours.Danger(), new BaseColours.DangerPressed());
    }
}
