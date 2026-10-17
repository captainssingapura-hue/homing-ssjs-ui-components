package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** base's look of pane-thumb: CSS of its own. */
public record PaneThumb() implements CastingStylePerRole<BaseScheme, Base, HouseControls.PaneThumb, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseControls.PaneThumb, RoleRoot> style() {
        return new StylingRes.CssContent<>(new BaseColours.Raised(), new BaseColours.Border(), new BaseColours.Accent(), new BaseColours.AccentTintFaint());
    }

    @Override public PartsSpec<BaseScheme, Base, HouseControls.PaneThumb> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseControls.PaneThumb.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Name.INSTANCE, new KickerName())));
    }
}
