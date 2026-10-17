package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** neo-futurism's implementation of text-box: CSS of its own. */
public record TextBox() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseControls.TextBox, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseControls.TextBox, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Recessed(), new BaseColours.Text(), new BaseColours.TextFaded(), new BaseColours.Border(), new BaseColours.Ring());
    }
}
