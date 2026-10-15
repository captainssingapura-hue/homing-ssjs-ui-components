package hue.captains.singapura.js.homing.design.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** The text box in the base layer: words the user types, set into the page. Whole: every property its look has (D9). */
public record TextBox() implements CastingStyle<ColorScheme.Base, Base, HouseControls.TextBox, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.TextBox> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Recessed(), new HouseColours.Text(), new HouseColours.TextFaded(),
                new HouseColours.Border(), new HouseColours.Ring());
    }
}
