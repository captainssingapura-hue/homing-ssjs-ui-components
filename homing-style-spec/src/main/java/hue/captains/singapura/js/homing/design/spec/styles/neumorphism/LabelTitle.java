package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

/** neumorphism's implementation of label_title: CSS of its own - its content apart, the colours it draws with typed here. */
public record LabelTitle() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseText.Label, HouseSaying.Title> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseText.Label, HouseSaying.Title> style() {
        return new StylingRes.CssPlaceHolder<>();
    }
}
