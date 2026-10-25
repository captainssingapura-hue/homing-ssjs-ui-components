package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/** sketchy's implementation of title-bar: CSS of its own - its content apart, the colours it draws with typed here. */
public record TitleBar() implements CssImplementation<BaseScheme, Sketchy, HouseContainers.TitleBar> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Inverted(), new BaseColours.OnInverted(), new BaseColours.OnBar(), new BaseColours.Border(), new BaseColours.Title());
    }
}
