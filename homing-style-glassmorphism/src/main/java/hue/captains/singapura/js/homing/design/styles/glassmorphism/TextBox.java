package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** The text box in glassmorphism: words the user types, set into the page. Whole: every property its look has (D9). */
public record TextBox() implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseControls.TextBox, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.TextBox> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Recessed(), new HouseColours.Text(), new HouseColours.TextFaded(),
                new HouseColours.Border(), new HouseColours.Ring());
    }
}
