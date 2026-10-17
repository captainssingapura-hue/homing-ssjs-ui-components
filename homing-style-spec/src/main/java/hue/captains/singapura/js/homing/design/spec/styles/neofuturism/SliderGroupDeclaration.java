package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** neo-futurism's declaration of slider-group: its own implementation, and a spec for each of its slots. */
public record SliderGroupDeclaration() implements CastingDeclaration<BaseScheme, NeoFuturism, HouseContainers.SliderGroup, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, NeoFuturism, HouseContainers.SliderGroup, RoleRoot> own() { return new SliderGroup(); }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HouseContainers.SliderGroup> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseContainers.SliderGroup.INSTANCE, HouseText.Heading4.INSTANCE, HouseSaying.Title.INSTANCE, new Target.Impl<>(new Heading4Title())),
                new PartSpec.Standalone<>(HouseContainers.SliderGroup.INSTANCE, HouseRegions.SliderRack.INSTANCE, HouseShaping.Body.INSTANCE),
                new PartSpec.Standalone<>(HouseContainers.SliderGroup.INSTANCE, HouseControls.Slider.INSTANCE, HouseShaping.Member.INSTANCE)));
    }
}
