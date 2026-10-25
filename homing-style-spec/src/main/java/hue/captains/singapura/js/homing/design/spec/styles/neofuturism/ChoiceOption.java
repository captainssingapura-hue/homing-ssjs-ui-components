package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

import java.util.List;

/** neo-futurism's implementation of choice-option: CSS of its own - its content apart, the colours it draws with typed here. */
public record ChoiceOption() implements CssImplementation<BaseScheme, NeoFuturism, HouseItems.ChoiceOption> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.OnInverted(), new BaseColours.Border(), new BaseColours.Title(), new BaseColours.Ring(), new BaseColours.AccentWashSoft());
    }
}
