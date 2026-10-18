package hue.captains.singapura.js.homing.design.spec.styles.neobrutalism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** neo-brutalism's declaration of panel-bar: its own implementation, and a spec for each of its slots. */
public record PanelBarDeclaration() implements CastingDeclaration<BaseScheme, NeoBrutalism, HouseContainers.PanelBar, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, NeoBrutalism, HouseContainers.PanelBar, RoleRoot> own() { return new PanelBar(); }

    @Override public PartsSpec<BaseScheme, NeoBrutalism, HouseContainers.PanelBar> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseContainers.PanelBar.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Title.INSTANCE, new Target.Impl<>(new KickerTitle()))));
    }
}
