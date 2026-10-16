package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** The text box in neumorphism: words the user types, set into the page. Whole: every property its look has (D9). */
public record TextBox() implements CastingStyle<BaseScheme, Neumorphism, HouseControls.TextBox, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseControls.TextBox> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Recessed(), new HouseColours.Text(), new HouseColours.TextFaded(),
                new HouseColours.Border(), new HouseColours.Ring());
    }
}
