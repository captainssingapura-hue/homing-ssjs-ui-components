package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Neumorphism's range adjust: as its range on its own, by delegation. */
public record RangeAdjust()
        implements CastingStyle<ColorScheme.Base, Neumorphism, HouseControls.Range, HouseDoing.Adjust> {
    @Override public StylingRes<ColorScheme.Base, HouseControls.Range> style() {
        return new StylingRes.Delegation<>(new Range());
    }
}
