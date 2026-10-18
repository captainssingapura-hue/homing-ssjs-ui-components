package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** retro-futurism's declaration of multi-tab-pane: its own implementation, and a spec for each of its slots. */
public record MultiTabPaneDeclaration() implements CastingDeclaration<BaseScheme, RetroFuturism, HousePanes.MultiTabPane, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, RetroFuturism, HousePanes.MultiTabPane, RoleRoot> own() { return new MultiTabPane(); }

    @Override public PartsSpec<BaseScheme, RetroFuturism, HousePanes.MultiTabPane> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HousePanes.MultiTabPane.INSTANCE, HouseContainers.TabStrip.INSTANCE, HouseShaping.Tabs.INSTANCE),
                new PartSpec.SameRole<>(HousePanes.MultiTabPane.INSTANCE, HousePanes.TabPicker.INSTANCE, HouseDoing.Picker.INSTANCE, new Target.Declared<>(new TabPickerPickerDeclaration())),
                new PartSpec.SameRole<>(HousePanes.MultiTabPane.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Note.INSTANCE, new Target.Impl<>(new CaptionNote()))));
    }
}
