package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.StandaloneEntry;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import hue.captains.singapura.js.homing.ui.taxonomy.UiElementsComponentModule;

import java.util.List;

/** UiElementsComponentModule's standalones in Base, in the typed spec stack (RFC 0067, D53): its components', and those of their design a functional role places. */
public record UiElementsBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiElementsBaseSpec INSTANCE = new UiElementsBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiElementsComponentModule.INSTANCE; }

    @Override public List<StandaloneEntry<BaseScheme, Base, ?>> standalones() {
        return List.of(
                new StandaloneEntry<>(HouseControls.PlainButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PlainButton())),
                new StandaloneEntry<>(HouseControls.PrimaryButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PrimaryButton())),
                new StandaloneEntry<>(HouseControls.SecondaryButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SecondaryButton())),
                new StandaloneEntry<>(HouseControls.DangerButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.DangerButton())),
                new StandaloneEntry<>(HouseControls.WarningButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.WarningButton())),
                new StandaloneEntry<>(HouseControls.SuccessButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SuccessButton())),
                new StandaloneEntry<>(HouseControls.ToggleButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ToggleButton())),
                new StandaloneEntry<>(HouseControls.CloseButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.CloseButtonDeclaration())),
                new StandaloneEntry<>(HouseControls.IconButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.IconButtonDeclaration())),
                new StandaloneEntry<>(HouseText.Heading1.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Heading1())),
                new StandaloneEntry<>(HouseText.Heading2.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Heading2())),
                new StandaloneEntry<>(HouseText.Heading3.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Heading3())),
                new StandaloneEntry<>(HouseText.Heading4.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Heading4())),
                new StandaloneEntry<>(HouseText.Heading5.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Heading5())),
                new StandaloneEntry<>(HouseText.Heading6.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Heading6())),
                new StandaloneEntry<>(HouseText.Kicker.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Kicker())),
                new StandaloneEntry<>(HouseText.Caption.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Caption())),
                new StandaloneEntry<>(HouseText.Lede.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Lede())),
                new StandaloneEntry<>(HouseText.Label.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Label())),
                new StandaloneEntry<>(HouseText.TextCell.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TextCell())),
                new StandaloneEntry<>(HouseText.Readout.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Readout())),
                new StandaloneEntry<>(HouseText.Badge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Badge())),
                new StandaloneEntry<>(HouseText.Pill.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Pill())),
                new StandaloneEntry<>(HouseText.Code.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Code())),
                new StandaloneEntry<>(HouseText.SuccessBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SuccessBadge())),
                new StandaloneEntry<>(HouseText.WarningBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.WarningBadge())),
                new StandaloneEntry<>(HouseText.DangerBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.DangerBadge())),
                new StandaloneEntry<>(HouseText.Wordmark.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Wordmark())),
                new StandaloneEntry<>(HouseControls.Link.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Link())),
                new StandaloneEntry<>(HouseControls.Crumb.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Crumb())),
                new StandaloneEntry<>(HouseControls.TextBox.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TextBox())),
                new StandaloneEntry<>(HouseControls.Checkbox.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Checkbox())),
                new StandaloneEntry<>(HouseControls.Range.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Range())),
                new StandaloneEntry<>(HouseControls.Select.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Select())),
                new StandaloneEntry<>(HouseContainers.Scrim.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Scrim())),
                new StandaloneEntry<>(HouseMarks.Separator.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Separator())),
                new StandaloneEntry<>(HouseMarks.Delimiter.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Delimiter())),
                new StandaloneEntry<>(HouseMarks.BrandMark.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.BrandMark())),
                new StandaloneEntry<>(HouseMarks.Lamp.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Lamp())),
                new StandaloneEntry<>(HouseMarks.DropMark.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.DropMark())),
                new StandaloneEntry<>(HouseMarks.Detent.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Detent())),
                new StandaloneEntry<>(HouseMarks.Tick.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Tick())),
                new StandaloneEntry<>(HouseMarks.BoundaryLine.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.BoundaryLine())),
                new StandaloneEntry<>(HouseItems.MenuItem.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.MenuItemDeclaration())),
                new StandaloneEntry<>(HouseItems.ListRow.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ListRow())),
                new StandaloneEntry<>(HouseItems.ChoiceOption.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ChoiceOptionDeclaration())),
                new StandaloneEntry<>(HouseContainers.PlainCard.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PlainCard())),
                new StandaloneEntry<>(HouseContainers.ClickableCard.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ClickableCard())),
                new StandaloneEntry<>(HouseContainers.SummaryView.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SummaryViewDeclaration())),
                new StandaloneEntry<>(HouseMarks.SliderRail.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SliderRail())),
                new StandaloneEntry<>(HouseMarks.SliderGroove.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SliderGroove())),
                new StandaloneEntry<>(HouseMarks.Fill.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Fill())),
                new StandaloneEntry<>(HouseControls.SliderKnob.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SliderKnob())),
                new StandaloneEntry<>(HouseMarks.ToggleGroove.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ToggleGroove())),
                new StandaloneEntry<>(HouseControls.ToggleKnob.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ToggleKnob())),
                new StandaloneEntry<>(HouseControls.Grip.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Grip())),
                new StandaloneEntry<>(HouseControls.Switch.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SwitchDeclaration())),
                new StandaloneEntry<>(HouseControls.PaneThumb.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PaneThumbDeclaration())),
                new StandaloneEntry<>(HouseControls.Slider.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SliderDeclaration())),
                new StandaloneEntry<>(HouseContainers.SliderGroup.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SliderGroupDeclaration())),
                new StandaloneEntry<>(HouseContainers.Panel.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PanelDeclaration())),
                new StandaloneEntry<>(HouseContainers.EdgeStrip.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.EdgeStripDeclaration())),
                new StandaloneEntry<>(HouseContainers.Ground.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Ground())),
                new StandaloneEntry<>(HouseContainers.SiteBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SiteBarDeclaration())),
                new StandaloneEntry<>(HouseControls.Brand.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.BrandDeclaration())),
                new StandaloneEntry<>(HouseContainers.Trail.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TrailDeclaration())),
                new StandaloneEntry<>(HouseRegions.Quote.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Quote())),
                new StandaloneEntry<>(HouseRegions.CodeBlock.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.CodeBlock())),
                new StandaloneEntry<>(HouseRegions.Table.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TableDeclaration())),
                new StandaloneEntry<>(HouseText.Strong.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Strong())),
                new StandaloneEntry<>(HouseText.Muted.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Muted())),
                new StandaloneEntry<>(HouseMarks.Indicator.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Indicator())),
                new StandaloneEntry<>(HouseIcons.AddIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.AddIcon())),
                new StandaloneEntry<>(HouseIcons.RemoveIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.RemoveIcon())),
                new StandaloneEntry<>(HouseIcons.CloseIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.CloseIcon())),
                new StandaloneEntry<>(HouseIcons.ResetIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ResetIcon())),
                new StandaloneEntry<>(HouseIcons.RotateIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.RotateIcon())),
                new StandaloneEntry<>(HouseIcons.FlipIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.FlipIcon())),
                new StandaloneEntry<>(HouseIcons.PinIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PinIcon())),
                new StandaloneEntry<>(HouseIcons.CheckIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.CheckIcon())),
                new StandaloneEntry<>(HouseIcons.GripIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.GripIcon())),
                new StandaloneEntry<>(HouseIcons.DiscloseIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.DiscloseIcon())),
                new StandaloneEntry<>(HouseIcons.SettingsIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SettingsIcon())),
                new StandaloneEntry<>(HouseIcons.SizeIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SizeIcon())),
                new StandaloneEntry<>(HouseIcons.AspectIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.AspectIcon())),
                new StandaloneEntry<>(HouseIcons.ExtentIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ExtentIcon())),
                new StandaloneEntry<>(HouseIcons.LevelIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.LevelIcon())),
                new StandaloneEntry<>(HouseIcons.ColumnIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ColumnIcon())),
                new StandaloneEntry<>(HouseIcons.RowIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.RowIcon())),
                new StandaloneEntry<>(HouseIcons.MergeIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.MergeIcon())),
                new StandaloneEntry<>(HouseIcons.DetachIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.DetachIcon())),
                new StandaloneEntry<>(HouseIcons.WithinIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.WithinIcon())),
                new StandaloneEntry<>(HouseRegions.SliderRack.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SliderRack())),
                new StandaloneEntry<>(HouseContainers.PanelBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PanelBarDeclaration())),
                new StandaloneEntry<>(HouseRegions.Section.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Section())),
                new StandaloneEntry<>(HouseControls.Lip.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.Lip())),
                new StandaloneEntry<>(HouseContainers.HeaderCell.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.HeaderCellDeclaration())),
                new StandaloneEntry<>(HouseContainers.DataCell.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.DataCellDeclaration())));
    }
}
