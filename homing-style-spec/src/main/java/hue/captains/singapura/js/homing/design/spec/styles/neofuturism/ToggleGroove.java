package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;

import java.util.List;

/** neo-futurism's implementation of toggle-groove: CSS of its own - its content apart, the colours it draws with typed here. */
public record ToggleGroove() implements CssImplementation<BaseScheme, NeoFuturism, HouseMarks.ToggleGroove> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.WashSoft(), new BaseColours.GrooveEdge(), new BaseColours.Accent());
    }
}
