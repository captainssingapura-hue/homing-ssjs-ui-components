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
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import java.util.List;

/** neo-futurism's look of panel: CSS of its own. */
public record Panel() implements CastingStylePerRole<BaseScheme, NeoFuturism, HouseContainers.Panel, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HouseContainers.Panel, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.ShadeDense(), new BaseColours.ShadeDark(), new BaseColours.GlowSoft());
    }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HouseContainers.Panel> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Placed<>(HouseContainers.Panel.INSTANCE, HouseContainers.PanelBar.INSTANCE, HouseShaping.Head.INSTANCE, new PanelBar()),
                new PartSpec.Placed<>(HouseContainers.Panel.INSTANCE, HouseRegions.Section.INSTANCE, HouseShaping.Body.INSTANCE, new Section())));
    }
}
