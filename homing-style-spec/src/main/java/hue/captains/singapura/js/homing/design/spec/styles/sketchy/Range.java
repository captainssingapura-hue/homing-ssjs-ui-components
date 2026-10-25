package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** sketchy's implementation of range: CSS of its own - its content apart, the colours it draws with typed here. */
public record Range() implements CssImplementation<BaseScheme, Sketchy, HouseControls.Range> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Accent(), new BaseColours.Ring(), new BaseColours.WashSoft(), new BaseColours.GrooveEdge(), new BaseColours.Raised());
    }
}
