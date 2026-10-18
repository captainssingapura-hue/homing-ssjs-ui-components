package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorLeaf;
import hue.captains.singapura.js.homing.design.spec.CssImplementation;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

import java.util.List;

/** neo-futurism's implementation of tab: CSS of its own - its content apart, the colours it draws with typed here. */
public record Tab() implements CssImplementation<BaseScheme, NeoFuturism, HouseControls.Tab, RoleRoot> {
    @Override public List<ColorLeaf<? super BaseScheme, ?, ?>> colours() {
        return List.of(new BaseColours.Text(), new BaseColours.OnInverted(), new BaseColours.Border(), new BaseColours.Title(), new BaseColours.AccentWashFaint(), new BaseColours.AccentWashSoft(), new BaseColours.ShadeSoft(), new BaseColours.GlowDim(), new BaseColours.GlowWarm(), new BaseColours.GlowStrong());
    }
}
