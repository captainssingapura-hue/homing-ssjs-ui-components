package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** neo-brutalism's declaration of slider-group: its own implementation, and a spec for each of its slots. */
public record SliderGroupDeclaration() implements CastingDeclaration<BaseScheme, NeoBrutalism, HouseContainers.SliderGroup, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, NeoBrutalism, HouseContainers.SliderGroup, RoleRoot> own() { return new SliderGroup(); }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HouseContainers.SliderGroup> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseContainers.SliderGroup.INSTANCE, HouseText.Heading4.INSTANCE, HouseSaying.Title.INSTANCE, new Target.Impl<>(new Heading4Title())),
                new PartSpec.Standalone<>(HouseContainers.SliderGroup.INSTANCE, HouseRegions.SliderRack.INSTANCE, HouseShaping.Body.INSTANCE),
                new PartSpec.Standalone<>(HouseContainers.SliderGroup.INSTANCE, HouseControls.Slider.INSTANCE, HouseShaping.Member.INSTANCE)));
    }
}
