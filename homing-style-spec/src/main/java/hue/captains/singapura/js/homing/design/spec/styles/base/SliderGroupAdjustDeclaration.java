package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** base's declaration of slider-group_adjust: its own implementation, and a spec for each of its slots. */
public record SliderGroupAdjustDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseContainers.SliderGroup, HouseDoing.Adjust> {
    @Override public CastingStylePerRole<BaseScheme, Base, HouseContainers.SliderGroup, HouseDoing.Adjust> own() { return new SliderGroupAdjust(); }

    @Override public PartsSpec<BaseScheme, Base, HouseContainers.SliderGroup> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseContainers.SliderGroup.INSTANCE, HouseText.Heading4.INSTANCE, HouseSaying.Title.INSTANCE, new Target.Impl<>(new Heading4Title())),
                new PartSpec.Standalone<>(HouseContainers.SliderGroup.INSTANCE, HouseRegions.SliderRack.INSTANCE, HouseShaping.Body.INSTANCE),
                new PartSpec.Standalone<>(HouseContainers.SliderGroup.INSTANCE, HouseControls.Slider.INSTANCE, HouseShaping.Member.INSTANCE)));
    }
}
