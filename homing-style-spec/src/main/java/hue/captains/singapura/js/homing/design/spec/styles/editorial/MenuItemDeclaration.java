package hue.captains.singapura.js.homing.design.spec.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingImplementation;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/** editorial's declaration of menu-item: its own implementation, and a spec for each of its slots. */
public record MenuItemDeclaration() implements CastingDeclaration<BaseScheme, Editorial, HouseItems.MenuItem, RoleRoot> {
    @Override public CastingImplementation<BaseScheme, Editorial, HouseItems.MenuItem, RoleRoot> own() { return new MenuItem(); }

    @Override public PartsSpec<BaseScheme, Editorial, HouseItems.MenuItem> parts() {
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
