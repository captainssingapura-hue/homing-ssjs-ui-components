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
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** retro-futurism's declaration of tab-opener: its own implementation, and a spec for each of its slots. */
public record TabOpenerDeclaration() implements CastingDeclaration<BaseScheme, RetroFuturism, HousePanes.TabOpener, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, RetroFuturism, HousePanes.TabOpener, RoleRoot> own() { return new TabOpener(); }

    @Override public PartsSpec<BaseScheme, RetroFuturism, HousePanes.TabOpener> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HousePanes.TabOpener.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Prompt.INSTANCE, new Target.Impl<>(new KickerPrompt())),
                new PartSpec.SameRole<>(HousePanes.TabOpener.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Choice.INSTANCE, new Target.Impl<>(new PlainButtonChoice()))));
    }
}
