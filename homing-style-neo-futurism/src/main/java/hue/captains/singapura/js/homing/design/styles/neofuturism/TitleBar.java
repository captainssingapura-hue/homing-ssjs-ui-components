package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The title bar in neo-futurism: heads a pane that floats - the inverted layer, the page's ground written on it in the
 * style's face, ruled along its foot; where the hand takes it, and in the house's colour while the hand has it. Whole:
 * every property its look has (D9).
 */
public record TitleBar() implements CastingStyle<BaseScheme, NeoFuturism, HouseContainers.TitleBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.TitleBar> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Inverted(), new BaseColours.OnInverted(), new BaseColours.OnBar(),
                new BaseColours.Border(), new BaseColours.Title());
    }
}
