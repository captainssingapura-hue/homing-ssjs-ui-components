package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** RetroFuturism's readout value: as its readout on its own, by delegation. */
public record ReadoutValue()
        implements CastingStyle<ColorScheme.Base, RetroFuturism, HouseText.Readout, HouseSaying.Value> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Readout> style() {
        return new StylingRes.Delegation<>(new Readout());
    }
}
