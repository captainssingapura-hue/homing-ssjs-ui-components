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
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** neumorphism's look of tab-strip: CSS of its own. */
public record TabStrip() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseContainers.TabStrip, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseContainers.TabStrip, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Accent(), new BaseColours.AccentTintHint());
    }

    @Override public PartsSpec<BaseScheme, Neumorphism, HouseContainers.TabStrip> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseContainers.TabStrip.INSTANCE, HouseControls.IconButton.INSTANCE, HouseDoing.Add.INSTANCE, new IconButtonAdd()),
                new PartSpec.Placed<>(HouseContainers.TabStrip.INSTANCE, HouseRegions.StripTail.INSTANCE, HouseShaping.Foot.INSTANCE, new StripTail()),
                new PartSpec.Cast<>(HouseContainers.TabStrip.INSTANCE, HouseText.Pill.INSTANCE, HouseSaying.Count.INSTANCE, new PillCount()),
                new PartSpec.Cast<>(HouseContainers.TabStrip.INSTANCE, HouseControls.CloseButton.INSTANCE, HouseDoing.Close.INSTANCE, new CloseButtonClose()),
                new PartSpec.Cast<>(HouseContainers.TabStrip.INSTANCE, HouseMarks.DropMark.INSTANCE, HouseSaying.Landing.INSTANCE, new DropMarkLanding())));
    }
}
