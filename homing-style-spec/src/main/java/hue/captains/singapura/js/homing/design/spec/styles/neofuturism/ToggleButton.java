package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;

/** neo-futurism's look of toggle-button: CSS of its own. */
public record ToggleButton() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseControls.ToggleButton, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseControls.ToggleButton, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.AccentEdge(), new BaseColours.Ring(), new BaseColours.AccentTint(), new BaseColours.ShadeSoft(), new BaseColours.GlowFaint(), new BaseColours.GlowDim(), new BaseColours.GlowSoft(), new BaseColours.GlowBright());
    }
}
