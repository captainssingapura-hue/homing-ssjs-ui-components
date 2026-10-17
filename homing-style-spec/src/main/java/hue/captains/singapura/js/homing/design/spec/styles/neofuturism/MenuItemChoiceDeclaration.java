package hue.captains.singapura.js.homing.design.spec.styles.neofuturism;

import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** neo-futurism's declaration of menu-item_choice: its own implementation, and a spec for each of its slots. */
public record MenuItemChoiceDeclaration() implements CastingDeclaration<BaseScheme, NeoFuturism, HouseItems.MenuItem, HouseDoing.Choice> {
    @Override public CastingStylePerRole<BaseScheme, NeoFuturism, HouseItems.MenuItem, HouseDoing.Choice> own() { return new MenuItemChoice(); }

    @Override public PartsSpec<BaseScheme, NeoFuturism, HouseItems.MenuItem> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.AddIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.RemoveIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.CloseIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.ResetIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.RotateIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.FlipIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.PinIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.CheckIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.GripIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.DiscloseIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.SettingsIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.SizeIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.AspectIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.ExtentIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.LevelIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.ColumnIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.RowIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.MergeIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.DetachIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.WithinIcon.INSTANCE, HouseShaping.Leading.INSTANCE),
                new PartSpec.SameRole<>(HouseItems.MenuItem.INSTANCE, HouseText.Caption.INSTANCE, HouseSaying.Hint.INSTANCE, new Target.Impl<>(new CaptionHint())),
                new PartSpec.Standalone<>(HouseItems.MenuItem.INSTANCE, HouseIcons.DiscloseIcon.INSTANCE, HouseShaping.Disclose.INSTANCE)));
    }
}
