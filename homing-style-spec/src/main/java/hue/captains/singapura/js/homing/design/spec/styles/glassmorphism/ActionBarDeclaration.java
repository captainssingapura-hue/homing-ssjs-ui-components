package hue.captains.singapura.js.homing.design.spec.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;

import java.util.List;

/** glassmorphism's declaration of action-bar: its own implementation, and a spec for each of its slots. */
public record ActionBarDeclaration() implements CastingDeclaration<BaseScheme, Glassmorphism, HouseContainers.ActionBar, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Glassmorphism, HouseContainers.ActionBar, RoleRoot> own() { return new ActionBar(); }

    @Override public PartsSpec<BaseScheme, Glassmorphism, HouseContainers.ActionBar> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseContainers.ActionBar.INSTANCE, HouseControls.PrimaryButton.INSTANCE, HouseDoing.Go.INSTANCE, new Target.Impl<>(new PrimaryButtonGo())),
                new PartSpec.SameRole<>(HouseContainers.ActionBar.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Action.INSTANCE, new Target.Impl<>(new PlainButtonAction()))));
    }
}
