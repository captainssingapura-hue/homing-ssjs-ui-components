package hue.captains.singapura.js.homing.design.spec.styles.sketchy;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** sketchy's look of theme-widget: base's, by delegation. */
public record ThemeWidget() implements CastingStylePerRole<BaseScheme, Sketchy, HouseSite.ThemeWidget, RoleRoot> {
    @Override public StylingRes<BaseScheme, Sketchy, HouseSite.ThemeWidget, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ThemeWidget());
    }

    @Override public PartsSpec<BaseScheme, Sketchy, HouseSite.ThemeWidget> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseSite.ThemeWidget.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Status.INSTANCE, new KickerStatus()),
                new PartSpec.Cast<>(HouseSite.ThemeWidget.INSTANCE, HouseItems.ChoiceOption.INSTANCE, HouseDoing.Style.INSTANCE, new ChoiceOptionStyle()),
                new PartSpec.Cast<>(HouseSite.ThemeWidget.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Subtitle.INSTANCE, new KickerSubtitle()),
                new PartSpec.Cast<>(HouseSite.ThemeWidget.INSTANCE, HouseItems.ChoiceOption.INSTANCE, HouseDoing.Palette.INSTANCE, new ChoiceOptionPalette())));
    }
}
