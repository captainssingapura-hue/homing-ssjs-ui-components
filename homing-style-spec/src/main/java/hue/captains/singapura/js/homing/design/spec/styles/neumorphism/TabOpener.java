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
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** neumorphism's look of tab-opener: base's, by delegation. */
public record TabOpener() implements CastingStylePerRole<BaseScheme, Neumorphism, HousePanes.TabOpener, RoleRoot> {
    @Override public StylingRes<BaseScheme, Neumorphism, HousePanes.TabOpener, RoleRoot> style() {
        return new StylingRes.ParentDelegation<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TabOpener());
    }

    @Override public PartsSpec<BaseScheme, Neumorphism, HousePanes.TabOpener> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HousePanes.TabOpener.INSTANCE, HouseText.Kicker.INSTANCE, HouseSaying.Prompt.INSTANCE, new KickerPrompt()),
                new PartSpec.Cast<>(HousePanes.TabOpener.INSTANCE, HouseControls.PlainButton.INSTANCE, HouseDoing.Choice.INSTANCE, new PlainButtonChoice())));
    }
}
