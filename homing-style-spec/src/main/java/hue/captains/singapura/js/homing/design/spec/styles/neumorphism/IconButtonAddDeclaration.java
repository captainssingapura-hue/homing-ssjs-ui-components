package hue.captains.singapura.js.homing.design.spec.styles.neumorphism;

import hue.captains.singapura.js.homing.design.spec.CastingDeclaration;
import hue.captains.singapura.js.homing.design.spec.CastingStylePerRole;
import hue.captains.singapura.js.homing.design.spec.PartSpec;
import hue.captains.singapura.js.homing.design.spec.PartsSpec;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;

import java.util.List;

/** neumorphism's declaration of icon-button_add: its own implementation, and a spec for each of its slots. */
public record IconButtonAddDeclaration() implements CastingDeclaration<BaseScheme, Neumorphism, HouseControls.IconButton, HouseDoing.Add> {
    @Override public CastingStylePerRole<BaseScheme, Neumorphism, HouseControls.IconButton, HouseDoing.Add> own() { return new IconButtonAdd(); }

    @Override public PartsSpec<BaseScheme, Neumorphism, HouseControls.IconButton> parts() {
        return new PartsSpec<>(List.of(
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.AddIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.RemoveIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.CloseIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.ResetIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.RotateIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.FlipIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.PinIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.CheckIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.GripIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.DiscloseIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.SettingsIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.SizeIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.AspectIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.ExtentIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.LevelIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.ColumnIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.RowIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.MergeIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.DetachIcon.INSTANCE, HouseShaping.Face.INSTANCE),
                new PartSpec.Standalone<>(HouseControls.IconButton.INSTANCE, HouseIcons.WithinIcon.INSTANCE, HouseShaping.Face.INSTANCE)));
    }
}
