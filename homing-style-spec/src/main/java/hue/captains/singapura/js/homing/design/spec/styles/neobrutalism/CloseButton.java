package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** neo-brutalism's implementation of close-button: CSS of its own. */
public record CloseButton() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseControls.CloseButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseControls.CloseButton, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.OnDanger(), new BaseColours.Border(), new BaseColours.Ring(), new BaseColours.Danger(), new BaseColours.DangerPressed());
    }
}
