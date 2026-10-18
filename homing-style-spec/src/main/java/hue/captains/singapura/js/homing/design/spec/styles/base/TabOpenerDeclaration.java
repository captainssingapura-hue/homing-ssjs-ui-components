package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** base's declaration of tab-opener: its own implementation, and a spec for each of its slots. */
public record TabOpenerDeclaration() implements CastingDeclaration<BaseScheme, Base, HousePanes.TabOpener, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HousePanes.TabOpener, RoleRoot> own() { return new TabOpener(); }

    @Override public PartsSpec<BaseScheme, Base, HousePanes.TabOpener> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HousePanes.TabOpener.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Prompt.INSTANCE, new Target.Impl<>(new KickerPrompt())),
                new PartSpec.SameRole<>(HousePanes.TabOpener.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Choice.INSTANCE, new Target.Impl<>(new PlainButtonChoice()))));
    }
}
