package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;
import java.util.List;

/** neumorphism's look of mpa-chrome: base's, by delegation. */
public record MpaChrome() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseSite.MpaChrome, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseSite.MpaChrome, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.MpaChrome());
    }

    @Override public PartsSpec<BaseScheme, Neumorphism, HouseSite.MpaChrome> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseSite.MpaChrome.INSTANCE, HouseControls.PreferencesButton.INSTANCE, HouseDoing.Preferences.INSTANCE, new PreferencesButtonPreferences()),
                new PartSpec.Placed<>(HouseSite.MpaChrome.INSTANCE, HouseRegions.WidgetSlot.INSTANCE, HouseShaping.Page.INSTANCE, new WidgetSlot())));
    }
}
