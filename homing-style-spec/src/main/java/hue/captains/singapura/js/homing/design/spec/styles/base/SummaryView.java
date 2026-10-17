package hue.captains.singapura.js.homing.design.spec.styles.base;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** base's look of summary-view: CSS of its own. */
public record SummaryView() implements CastingStylePerRole<BaseScheme, Base, HouseContainers.SummaryView, RoleRoot> {
    @Override public StylingRes<BaseScheme, Base, HouseContainers.SummaryView, RoleRoot> style() {
        return new StylingRes.CssContent<>();
    }

    @Override public PartsSpec<BaseScheme, Base, HouseContainers.SummaryView> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseContainers.SummaryView.INSTANCE, HouseText.Heading4.INSTANCE, HouseSaying.Title.INSTANCE, new Heading4Title()),
                new PartSpec.Cast<>(HouseContainers.SummaryView.INSTANCE, HouseText.Badge.INSTANCE, HouseSaying.Tag.INSTANCE, new BadgeTag()),
                new PartSpec.Cast<>(HouseContainers.SummaryView.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Summary.INSTANCE, new CaptionSummary()),
                new PartSpec.Cast<>(HouseContainers.SummaryView.INSTANCE, HouseControls.Link.INSTANCE, HouseDoing.Open.INSTANCE, new LinkOpen())));
    }
}
