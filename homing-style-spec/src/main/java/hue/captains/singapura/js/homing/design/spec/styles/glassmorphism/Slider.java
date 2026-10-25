package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** glassmorphism's implementation of slider: CSS of its own - its content apart, the colours it draws with typed here. */
public record Slider() implements CssImplementation<BaseScheme, Glassmorphism, HouseControls.Slider> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of();
    }
}
