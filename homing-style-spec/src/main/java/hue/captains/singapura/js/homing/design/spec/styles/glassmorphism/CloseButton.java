package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** glassmorphism's implementation of close-button: CSS of its own - its content apart, the colours it draws with typed here. */
public record CloseButton() implements CssImplementation<BaseScheme, Glassmorphism, HouseControls.CloseButton> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.OnDanger(), new BaseColours.Border(), new BaseColours.Ring(), new BaseColours.Danger(), new BaseColours.DangerPressed());
    }
}
