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
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import java.util.List;

/** neo-futurism's look of add-tab: base's, by delegation. */
public record AddTab() implements CastingStylePerRole<BaseScheme, NeoFuturism, HousePanes.AddTab, RoleRoot> {
    @Override public StylingRes<BaseScheme, NeoFuturism, HousePanes.AddTab, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.AddTab());
    }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HousePanes.AddTab> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePanes.AddTab.INSTANCE, HousePanes.PaneThumbs.INSTANCE, HouseDoing.Where.INSTANCE, new PaneThumbsWhere()),
                new PartSpec.Cast<>(HousePanes.AddTab.INSTANCE, HouseControls.Select.INSTANCE, HouseDoing.What.INSTANCE, new SelectWhat()),
                new PartSpec.Cast<>(HousePanes.AddTab.INSTANCE, HouseControls.Select.INSTANCE, HouseDoing.How.INSTANCE, new SelectHow()),
                new PartSpec.Cast<>(HousePanes.AddTab.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Go.INSTANCE, new PlainButtonGo())));
    }
}
