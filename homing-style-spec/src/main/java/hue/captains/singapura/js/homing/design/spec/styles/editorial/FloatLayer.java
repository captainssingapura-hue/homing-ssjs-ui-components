package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/** editorial's implementation of float-layer: CSS of its own - its content apart, the colours it draws with typed here. */
public record FloatLayer() implements CssImplementation<BaseScheme, Editorial, HouseContainers.FloatLayer> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Recessed());
    }
}
