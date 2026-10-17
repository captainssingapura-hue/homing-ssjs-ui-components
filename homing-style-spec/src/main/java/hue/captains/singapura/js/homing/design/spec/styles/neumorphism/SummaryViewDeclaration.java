package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** neumorphism's declaration of summary-view: its own implementation, and a spec for each of its slots. */
public record SummaryViewDeclaration() implements CastingDeclaration<BaseScheme, Neumorphism, HouseContainers.SummaryView, RoleRoot> {
    @Override public CastingStylePerRole<BaseScheme, Neumorphism, HouseContainers.SummaryView, RoleRoot> own() { return new SummaryView(); }

    @Override public PartsSpec<BaseScheme, Neumorphism, HouseContainers.SummaryView> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.SameRole<>(HouseContainers.SummaryView.INSTANCE, HouseText.Heading4.INSTANCE, HouseSaying.Title.INSTANCE, new Target.Impl<>(new Heading4Title())),
                new PartSpec.SameRole<>(HouseContainers.SummaryView.INSTANCE, HouseText.Badge.INSTANCE, HouseSaying.Tag.INSTANCE, new Target.Impl<>(new BadgeTag())),
                new PartSpec.SameRole<>(HouseContainers.SummaryView.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Summary.INSTANCE, new Target.Impl<>(new CaptionSummary())),
                new PartSpec.SameRole<>(HouseContainers.SummaryView.INSTANCE, HouseControls.Link.INSTANCE, HouseDoing.Open.INSTANCE, new Target.Impl<>(new LinkOpen()))));
    }
}
