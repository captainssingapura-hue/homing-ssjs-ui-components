package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** neo-futurism's implementation of danger-button: CSS of its own - its content apart, the colours it draws with typed here. */
public record DangerButton() implements CssImplementation<BaseScheme, NeoFuturism, HouseControls.DangerButton> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Ring(), new BaseColours.ShadeSoft(), new BaseColours.GlowFaint(), new BaseColours.GlowDim(), new BaseColours.GlowSoft(), new BaseColours.GlowBright(), new BaseColours.DangerEdge(), new BaseColours.DangerGround(), new BaseColours.DangerWriting());
    }
}
