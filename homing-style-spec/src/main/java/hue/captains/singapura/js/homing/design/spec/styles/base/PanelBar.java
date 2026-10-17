package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** base's look of panel-bar: CSS of its own. */
public record PanelBar() implements CastingStylePerRole<BaseScheme, Base, HouseContainers.PanelBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseContainers.PanelBar, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent());
    }

    @Override public PartsSpec<BaseScheme, Base, HouseContainers.PanelBar> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseContainers.PanelBar.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Title.INSTANCE, new KickerTitle())));
    }
}
