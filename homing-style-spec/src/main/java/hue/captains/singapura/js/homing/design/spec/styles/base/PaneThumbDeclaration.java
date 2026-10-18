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
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** base's declaration of pane-thumb: its own implementation, and a spec for each of its slots. */
public record PaneThumbDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseControls.PaneThumb, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HouseControls.PaneThumb, RoleRoot> own() { return new PaneThumb(); }

    @Override public PartsSpec<BaseScheme, Base, HouseControls.PaneThumb> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseControls.PaneThumb.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Name.INSTANCE, new Target.Impl<>(new KickerName()))));
    }
}
