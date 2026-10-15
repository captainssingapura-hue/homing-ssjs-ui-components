package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

/** RetroFuturism's slider group adjust: as its slider group on its own, by delegation. */
public record SliderGroupAdjust()
        implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseContainers.SliderGroup, HouseDoing.Adjust> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.SliderGroup> style() {
        return new StylingRes.Delegation<>(new SliderGroup());
    }
}
