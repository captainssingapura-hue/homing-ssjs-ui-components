package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** Neumorphism's select how: as its select on its own, by delegation. */
public record SelectHow() implements CastingStyle<BaseScheme, Neumorphism, HouseControls.Select, HouseDoing.How> {
    @Override public StylingRes<BaseScheme, HouseControls.Select> style() {
        return new StylingRes.Delegation<>(new Select());
    }
}
