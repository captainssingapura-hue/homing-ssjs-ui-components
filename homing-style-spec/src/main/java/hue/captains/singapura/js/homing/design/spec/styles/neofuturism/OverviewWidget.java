package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** neo-futurism's look of overview-widget: base's, by delegation. */
public record OverviewWidget() implements CastingStylePerRole<BaseScheme, NeoFuturism, HousePreferences.OverviewWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HousePreferences.OverviewWidget, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.OverviewWidget());
    }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HousePreferences.OverviewWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePreferences.OverviewWidget.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Category.INSTANCE, new KickerCategory()),
                new PartSpec.Cast<>(HousePreferences.OverviewWidget.INSTANCE, HouseText.Heading4.INSTANCE, HouseSaying.Title.INSTANCE, new Heading4Title()),
                new PartSpec.Cast<>(HousePreferences.OverviewWidget.INSTANCE, HouseText.Lede.INSTANCE, HouseSaying.Summary.INSTANCE, new LedeSummary()),
                new PartSpec.Cast<>(HousePreferences.OverviewWidget.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Reset.INSTANCE, new PlainButtonReset())));
    }
}
