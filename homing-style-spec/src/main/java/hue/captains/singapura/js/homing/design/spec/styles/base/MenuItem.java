package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

import java.util.List;

/** base's implementation of menu-item: CSS of its own - its content apart, the colours it draws with typed here. */
public record MenuItem() implements CssImplementation<BaseScheme, Base, HouseItems.MenuItem> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.AccentWashSoft(), new BaseColours.AccentWashStrong());
    }
}
