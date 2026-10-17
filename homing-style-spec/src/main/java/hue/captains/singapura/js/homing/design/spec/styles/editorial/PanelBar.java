package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** editorial's look of panel-bar: CSS of its own. */
public record PanelBar() implements CastingStylePerRole<BaseScheme, Editorial, HouseContainers.PanelBar, RoleRoot> {
    @Override public StylingRes<BaseScheme, Editorial, HouseContainers.PanelBar, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Accent());
    }

    @Override public PartsSpec<BaseScheme, Editorial, HouseContainers.PanelBar> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseContainers.PanelBar.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Title.INSTANCE, new KickerTitle())));
    }
}
