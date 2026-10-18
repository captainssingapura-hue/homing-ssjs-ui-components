package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** neo-futurism's declaration of tab-strip: its own implementation, and a spec for each of its slots. */
public record TabStripDeclaration() implements CastingDeclaration<BaseScheme, NeoFuturism, HouseContainers.TabStrip, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, NeoFuturism, HouseContainers.TabStrip, RoleRoot> own() { return new TabStrip(); }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HouseContainers.TabStrip> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseContainers.TabStrip.INSTANCE, HouseControls.Tab.INSTANCE, HouseShaping.Chip.INSTANCE),
                new PartSpec.SameRole<>(HouseContainers.TabStrip.INSTANCE, HouseControls.IconButton.INSTANCE, HouseDoing.Add.INSTANCE, new Target.Declared<>(new IconButtonAddDeclaration())),
                new PartSpec.Standalone<>(HouseContainers.TabStrip.INSTANCE, HouseRegions.StripTail.INSTANCE, HouseShaping.Foot.INSTANCE),
                new PartSpec.SameRole<>(HouseContainers.TabStrip.INSTANCE, HouseText.Pill.INSTANCE, HouseSaying.Count.INSTANCE, new Target.Impl<>(new PillCount())),
                new PartSpec.SameRole<>(HouseContainers.TabStrip.INSTANCE, HouseControls.CloseButton.INSTANCE, HouseDoing.Close.INSTANCE, new Target.Declared<>(new CloseButtonCloseDeclaration())),
                new PartSpec.SameRole<>(HouseContainers.TabStrip.INSTANCE, HouseMarks.DropMark.INSTANCE, HouseSaying.Landing.INSTANCE, new Target.Impl<>(new DropMarkLanding()))));
    }
}
