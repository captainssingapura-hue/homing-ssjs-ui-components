package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/** sketchy's implementation of site-bar: CSS of its own - its content apart, the colours it draws with typed here. */
public record SiteBar() implements CssImplementation<BaseScheme, Sketchy, HouseContainers.SiteBar> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Inverted(), new BaseColours.OnBar(), new BaseColours.Accent());
    }
}
