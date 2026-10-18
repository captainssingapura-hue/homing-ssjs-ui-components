package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;

import java.util.List;

/** base's declaration of mpa-chrome: its own implementation, and a spec for each of its slots. */
public record MpaChromeDeclaration() implements CastingDeclaration<BaseScheme, Base, HouseSite.MpaChrome, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Base, HouseSite.MpaChrome, RoleRoot> own() { return new MpaChrome(); }

    @Override public PartsSpec<BaseScheme, Base, HouseSite.MpaChrome> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseSite.MpaChrome.INSTANCE, HouseContainers.SiteBar.INSTANCE, HouseShaping.Head.INSTANCE),
                new PartSpec.SameRole<>(HouseSite.MpaChrome.INSTANCE, HouseControls.PreferencesButton.INSTANCE, HouseDoing.Preferences.INSTANCE, new Target.Declared<>(new PreferencesButtonPreferencesDeclaration())),
                new PartSpec.Standalone<>(HouseSite.MpaChrome.INSTANCE, HouseRegions.WidgetSlot.INSTANCE, HouseShaping.Page.INSTANCE)));
    }
}
