package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** The text box in the base layer: words the user types, set into the page. Whole: every property its look has (D9). */
public record TextBox() implements CastingStyle<BaseScheme, Base, HouseControls.TextBox, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.TextBox> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Recessed(), new BaseColours.Text(), new BaseColours.TextFaded(),
                new BaseColours.Border(), new BaseColours.Ring());
    }
}
