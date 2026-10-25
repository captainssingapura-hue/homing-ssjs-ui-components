package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** neo-brutalism's implementation of success-button: CSS of its own - its content apart, the colours it draws with typed here. */
public record SuccessButton() implements CssImplementation<BaseScheme, NeoBrutalism, HouseControls.SuccessButton> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Inverted(), new BaseColours.SuccessInk(), new BaseColours.Border(), new BaseColours.Ring(), new BaseColours.SuccessGround());
    }
}
