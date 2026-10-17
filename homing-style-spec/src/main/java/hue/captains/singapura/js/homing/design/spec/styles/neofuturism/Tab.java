package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import java.util.List;

/** neo-futurism's look of tab: CSS of its own. */
public record Tab() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseControls.Tab, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseControls.Tab, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Text(), new BaseColours.OnInverted(), new BaseColours.Border(), new BaseColours.Title(), new BaseColours.AccentWashFaint(), new BaseColours.AccentWashSoft(), new BaseColours.ShadeSoft(), new BaseColours.GlowDim(), new BaseColours.GlowWarm(), new BaseColours.GlowStrong());
    }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HouseControls.Tab> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseControls.Tab.INSTANCE, HouseControls.CloseButton.INSTANCE, HouseDoing.Close.INSTANCE, new CloseButtonClose())));
    }
}
