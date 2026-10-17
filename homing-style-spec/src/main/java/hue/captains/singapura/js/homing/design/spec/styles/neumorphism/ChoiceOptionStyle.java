package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** neumorphism's implementation of choice-option_style: as its component standalone, by delegation. */
public record ChoiceOptionStyle() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseItems.ChoiceOption, HouseDoing.Style> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseItems.ChoiceOption, HouseDoing.Style> style() {
        return new StylingRes.RootDelegation<>(new ChoiceOption());
    }
}
