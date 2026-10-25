package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;

import java.util.List;

/** retro-futurism's implementation of quote: CSS of its own - its content apart, the colours it draws with typed here. */
public record Quote() implements CssImplementation<BaseScheme, RetroFuturism, HouseRegions.Quote> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.TextFaded(), new BaseColours.Border());
    }
}
