package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** The plain button in editorial: a button that does one ordinary thing. Whole: every property its look has (D9). */
public record PlainButton() implements CastingStyle<ColorScheme.Base, Editorial, HouseControls.PlainButton, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.PlainButton> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Text(), new HouseColours.Border(), new HouseColours.Ring(),
                new HouseColours.ShadeFaint(), new HouseColours.ShadeLight(), new HouseColours.ShadeMedium());
    }
}
