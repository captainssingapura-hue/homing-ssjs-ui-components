package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Neumorphism's heading4 title: as its heading4 on its own, by delegation. */
public record Heading4Title()
        implements CastingStyle<ColorScheme.Base, Neumorphism, HouseText.Heading4, HouseSaying.Title> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Heading4> style() {
        return new StylingRes.Delegation<>(new Heading4());
    }
}
