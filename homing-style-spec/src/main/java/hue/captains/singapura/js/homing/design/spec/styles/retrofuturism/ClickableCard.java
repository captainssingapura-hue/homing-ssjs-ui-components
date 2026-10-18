package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/** retro-futurism's implementation of clickable-card: CSS of its own - its content apart, the colours it draws with typed here. */
public record ClickableCard() implements CssImplementation<BaseScheme, RetroFuturism, HouseContainers.ClickableCard, RoleRoot> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Raised(), new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.Ring(), new BaseColours.KeysCandidate(), new BaseColours.KeysLent(), new BaseColours.GlowDim(), new BaseColours.GlowMedium(), new BaseColours.GlowStrong(), new BaseColours.GlowFull());
    }
}
