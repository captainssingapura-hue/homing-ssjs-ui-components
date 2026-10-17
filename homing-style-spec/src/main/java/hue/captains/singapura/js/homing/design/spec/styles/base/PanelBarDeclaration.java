package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** base's declaration of panel-bar: its own implementation, and a spec for each of its slots. */
public record PanelBarDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseContainers.PanelBar, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Base, HouseContainers.PanelBar, RoleRoot> own() { return new PanelBar(); }

    @Override public PartsSpec<BaseScheme, Base, HouseContainers.PanelBar> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseContainers.PanelBar.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Title.INSTANCE, new Target.Impl<>(new KickerTitle()))));
    }
}
