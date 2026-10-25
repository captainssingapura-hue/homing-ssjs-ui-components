package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** editorial's implementation of toggle-button: CSS of its own - its content apart, the colours it draws with typed here. */
public record ToggleButton() implements CssImplementation<BaseScheme, Editorial, HouseControls.ToggleButton> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.AccentEdge(), new BaseColours.Ring(), new BaseColours.AccentTint(), new BaseColours.ShadeFaint(), new BaseColours.ShadeLight(), new BaseColours.ShadeMedium());
    }
}
