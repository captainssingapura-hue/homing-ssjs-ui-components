package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

/**
 * The toggle groove in editorial: the pill its knob slides in, in the accent when on; its measure the switch's. Whole:
 * every property its look has (D9).
 */
public record ToggleGroove() implements CastingStyle<ColorScheme.Base, Editorial, HouseMarks.ToggleGroove, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.ToggleGroove> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.WashSoft(), new HouseColours.GrooveEdge(), new HouseColours.Accent());
    }
}
