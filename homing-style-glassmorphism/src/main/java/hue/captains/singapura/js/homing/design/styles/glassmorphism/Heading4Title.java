package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** Glassmorphism's heading4 title: as its heading4 on its own, by delegation. */
public record Heading4Title()
        implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseText.Heading4, HouseSaying.Title> {
    @Override public StylingRes<ColorScheme.Base, HouseText.Heading4> style() {
        return new StylingRes.Delegation<>(new Heading4());
    }
}
