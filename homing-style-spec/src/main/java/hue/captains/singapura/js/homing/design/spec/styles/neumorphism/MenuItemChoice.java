package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** neumorphism's look of menu-item_choice: as its component on its own, by delegation. */
public record MenuItemChoice() implements CastingStylePerRole<BaseScheme, Neumorphism, HouseItems.MenuItem, HouseDoing.Choice> {
    @Override public StylingRes<BaseScheme, Neumorphism, HouseItems.MenuItem, HouseDoing.Choice> style() {
        return new StylingRes.RootDelegation<>(new MenuItem());
    }

    @Override public PartsSpec<BaseScheme, Neumorphism, HouseItems.MenuItem> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Cast<>(HouseItems.MenuItem.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Hint.INSTANCE, new CaptionHint())));
    }
}
