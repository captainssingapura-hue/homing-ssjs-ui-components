package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** retro-futurism's implementation of toggle-button: CSS of its own - its content apart, the colours it draws with typed here. */
public record ToggleButton() implements CssImplementation<BaseScheme, RetroFuturism, HouseControls.ToggleButton> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.AccentEdge(), new BaseColours.Ring(), new BaseColours.AccentTint(), new BaseColours.GlowDim(), new BaseColours.GlowMedium(), new BaseColours.GlowStrong(), new BaseColours.GlowFull());
    }
}
