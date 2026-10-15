package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;

/** NeoFuturism's detent rest: as its detent on its own, by delegation. */
public record DetentRest() implements CastingStyle<ColorScheme.Base, NeoFuturism, HouseMarks.Detent, HouseSaying.Rest> {
    @Override public StylingRes<ColorScheme.Base, HouseMarks.Detent> style() {
        return new StylingRes.Delegation<>(new Detent());
    }
}
