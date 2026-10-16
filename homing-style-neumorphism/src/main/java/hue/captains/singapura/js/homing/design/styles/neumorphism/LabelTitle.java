package hue.captains.singapura.js.homing.design.styles.neumorphism;

import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** The label as title in neumorphism: words that name what they stand by. Whole: every property its look has (D9). */
public record LabelTitle() implements CastingStyle<BaseScheme, Neumorphism, HouseText.Label, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, HouseText.Label> style() {
        return new StylingRes.CssContent<>();
    }
}
