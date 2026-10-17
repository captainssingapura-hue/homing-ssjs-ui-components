package hue.captains.singapura.js.homing.design.spec.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** retro-futurism's declaration of tab: its own implementation, and a spec for each of its slots. */
public record TabDeclaration() implements CastingDeclaration<BaseScheme, RetroFuturism, HouseControls.Tab, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, RetroFuturism, HouseControls.Tab, RoleRoot> own() { return new Tab(); }

    @Override public PartsSpec<BaseScheme, RetroFuturism, HouseControls.Tab> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseControls.Tab.INSTANCE, HouseIcons.WithinIcon.INSTANCE, HouseShaping.Keys.INSTANCE),
                new PartSpec.SameRole<>(HouseControls.Tab.INSTANCE, HouseControls.CloseButton.INSTANCE, HouseDoing.Close.INSTANCE, new Target.Declared<>(new CloseButtonCloseDeclaration()))));
    }
}
