package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

/** retro-futurism's implementation of choice-option_style: as its component standalone, by delegation. */
public record ChoiceOptionStyle() implements CastingStylePerRole<BaseScheme, RetroFuturism, HouseItems.ChoiceOption, HouseDoing.Style> {
    @Override public StylingRes<BaseScheme, RetroFuturism, HouseItems.ChoiceOption, HouseDoing.Style> style() {
        return new StylingRes.RootDelegation<>(new ChoiceOption());
    }
}
