package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** neo-brutalism's look of slider-group_adjust: as its component on its own, by delegation. */
public record SliderGroupAdjust() implements CastingStylePerRole<BaseScheme, NeoBrutalism, HouseContainers.SliderGroup, HouseDoing.Adjust> {
    @Override public StylingRes<BaseScheme, NeoBrutalism, HouseContainers.SliderGroup, HouseDoing.Adjust> style() {
        return new StylingRes.RootDelegation<>(new SliderGroup());
    }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HouseContainers.SliderGroup> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseContainers.SliderGroup.INSTANCE, HouseText.Heading4.INSTANCE, HouseSaying.Title.INSTANCE, new Heading4Title()),
                new PartSpec.Placed<>(HouseContainers.SliderGroup.INSTANCE, HouseRegions.SliderRack.INSTANCE, HouseShaping.Body.INSTANCE, new SliderRack())));
    }
}
