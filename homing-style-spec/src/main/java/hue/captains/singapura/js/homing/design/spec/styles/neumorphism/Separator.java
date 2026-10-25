package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

import java.util.List;

/** neumorphism's implementation of separator: CSS of its own - its content apart, the colours it draws with typed here. */
public record Separator() implements CssImplementation<BaseScheme, Neumorphism, HouseMarks.Separator> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Hairline());
    }
}
