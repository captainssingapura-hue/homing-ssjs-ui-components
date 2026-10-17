package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** editorial's implementation of text-box: CSS of its own. */
public record TextBox() implements CastingStylePerRole<BaseScheme, Editorial, HouseControls.TextBox, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseControls.TextBox, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Recessed(), new BaseColours.Text(), new BaseColours.TextFaded(), new BaseColours.Border(), new BaseColours.Ring());
    }
}
