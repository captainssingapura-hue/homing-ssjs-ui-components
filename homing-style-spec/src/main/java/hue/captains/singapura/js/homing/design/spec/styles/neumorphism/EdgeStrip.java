package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import java.util.List;

/** neumorphism's look of edge-strip: CSS of its own. */
public record EdgeStrip() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseContainers.EdgeStrip, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseContainers.EdgeStrip, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(), new BaseColours.ShadeHeavy());
    }

    @Override public PartsSpec<BaseScheme, Neumorphism, HouseContainers.EdgeStrip> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Placed<>(HouseContainers.EdgeStrip.INSTANCE, HouseControls.Lip.INSTANCE, HouseShaping.Reveal.INSTANCE, new Lip())));
    }
}
