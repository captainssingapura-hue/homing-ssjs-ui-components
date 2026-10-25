package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/** editorial's implementation of tab-strip: CSS of its own - its content apart, the colours it draws with typed here. */
public record TabStrip() implements CssImplementation<BaseScheme, Editorial, HouseContainers.TabStrip> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Raised(), new BaseColours.Accent(), new BaseColours.AccentTintHint());
    }
}
