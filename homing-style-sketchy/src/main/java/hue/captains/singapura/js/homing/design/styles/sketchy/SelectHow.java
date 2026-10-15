package hue.captains.singapura.js.homing.design.styles.sketchy;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Sketchy's select how: as its select on its own, by delegation. */
public record SelectHow() implements CastingStyle<ColorScheme.Base, Sketchy, HouseControls.Select, HouseDoing.How> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Select> style() {
        return new StylingRes.Delegation<>(new Select());
    }
}
