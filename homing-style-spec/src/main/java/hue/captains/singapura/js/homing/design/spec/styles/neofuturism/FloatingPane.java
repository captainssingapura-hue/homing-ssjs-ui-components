package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import java.util.List;

/** neo-futurism's look of floating-pane: CSS of its own. */
public record FloatingPane() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseContainers.FloatingPane, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseContainers.FloatingPane, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Title(), new BaseColours.Ring(), new BaseColours.ShadeSoft(), new BaseColours.ShadeThick(), new BaseColours.GlowSoft(), new BaseColours.GlowMild(), new BaseColours.GlowStrong(), new BaseColours.GlowBright());
    }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HouseContainers.FloatingPane> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Placed<>(HouseContainers.FloatingPane.INSTANCE, HouseContainers.TitleBar.INSTANCE, HouseShaping.Head.INSTANCE, new TitleBar())));
    }
}
