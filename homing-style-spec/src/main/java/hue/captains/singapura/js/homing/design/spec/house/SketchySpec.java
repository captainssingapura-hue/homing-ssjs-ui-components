package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.spec.Implemented;
import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControlPanel;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** sketchy's implementations, in the typed spec stack (RFC 0067, D53): each top-level component it styles; the rest are Base's. */
public record SketchySpec() implements SpecModule<BaseScheme, Sketchy> {

    public static final SketchySpec INSTANCE = new SketchySpec();

    @Override public Sketchy style() { return Sketchy.INSTANCE; }

    @Override public List<Implemented<BaseScheme, Sketchy, ?>> implementations() {
        return List.of(
                new Implemented<>(HouseControls.PlainButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                new Implemented<>(HouseControls.PrimaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PrimaryButton()),
                new Implemented<>(HouseControls.SecondaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SecondaryButton()),
                new Implemented<>(HouseControls.DangerButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.DangerButton()),
                new Implemented<>(HouseControls.WarningButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.WarningButton()),
                new Implemented<>(HouseControls.SuccessButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SuccessButton()),
                new Implemented<>(HouseControls.ToggleButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleButton()),
                new Implemented<>(HouseControls.CloseButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.CloseButton()),
                new Implemented<>(HouseControls.IconButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.IconButton()),
                new Implemented<>(HouseText.Heading1.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading1()),
                new Implemented<>(HouseText.Heading2.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading2()),
                new Implemented<>(HouseText.Heading3.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading3()),
                new Implemented<>(HouseText.Heading4.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading4()),
                new Implemented<>(HouseText.Heading5.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading5()),
                new Implemented<>(HouseText.Heading6.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading6()),
                new Implemented<>(HouseText.Kicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker()),
                new Implemented<>(HouseText.Caption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                new Implemented<>(HouseText.Lede.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Lede()),
                new Implemented<>(HouseText.Label.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Label()),
                new Implemented<>(HouseText.Readout.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Readout()),
                new Implemented<>(HouseText.Badge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Badge()),
                new Implemented<>(HouseText.Pill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Pill()),
                new Implemented<>(HouseText.Code.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Code()),
                new Implemented<>(HouseText.SuccessBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SuccessBadge()),
                new Implemented<>(HouseText.WarningBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.WarningBadge()),
                new Implemented<>(HouseText.DangerBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.DangerBadge()),
                new Implemented<>(HouseText.Wordmark.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Wordmark()),
                new Implemented<>(HouseControls.Link.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Link()),
                new Implemented<>(HouseControls.Crumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Crumb()),
                new Implemented<>(HouseControls.TextBox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.TextBox()),
                new Implemented<>(HouseControls.Checkbox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Checkbox()),
                new Implemented<>(HouseControls.Range.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Range()),
                new Implemented<>(HouseControls.Select.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Select()),
                new Implemented<>(HouseContainers.Scrim.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Scrim()),
                new Implemented<>(HouseMarks.Separator.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Separator()),
                new Implemented<>(HouseMarks.Lamp.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Lamp()),
                new Implemented<>(HouseMarks.Detent.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Detent()),
                new Implemented<>(HouseMarks.Tick.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Tick()),
                new Implemented<>(HouseItems.MenuItem.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.MenuItem()),
                new Implemented<>(HouseItems.ListRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ListRow()),
                new Implemented<>(HouseItems.ChoiceOption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ChoiceOption()),
                new Implemented<>(HouseContainers.PlainCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainCard()),
                new Implemented<>(HouseContainers.ClickableCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ClickableCard()),
                new Implemented<>(HouseContainers.SummaryView.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SummaryView()),
                new Implemented<>(HouseMarks.SliderRail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderRail()),
                new Implemented<>(HouseMarks.SliderGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderGroove()),
                new Implemented<>(HouseMarks.Fill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Fill()),
                new Implemented<>(HouseControls.SliderKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderKnob()),
                new Implemented<>(HouseMarks.ToggleGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleGroove()),
                new Implemented<>(HouseControls.ToggleKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleKnob()),
                new Implemented<>(HouseControls.Grip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Grip()),
                new Implemented<>(HouseControls.Switch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Switch()),
                new Implemented<>(HouseControls.PaneThumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PaneThumb()),
                new Implemented<>(HouseControls.Slider.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Slider()),
                new Implemented<>(HouseContainers.SliderGroup.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderGroup()),
                new Implemented<>(HouseContainers.Panel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Panel()),
                new Implemented<>(HouseContainers.EdgeStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.EdgeStrip()),
                new Implemented<>(HouseContainers.Ground.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Ground()),
                new Implemented<>(HouseContainers.SiteBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SiteBar()),
                new Implemented<>(HouseControls.Brand.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Brand()),
                new Implemented<>(HouseContainers.Trail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Trail()),
                new Implemented<>(HouseRegions.Quote.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Quote()),
                new Implemented<>(HouseRegions.CodeBlock.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.CodeBlock()),
                new Implemented<>(HouseIcons.CloseIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.CloseIcon()),
                new Implemented<>(HouseIcons.PinIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PinIcon()),
                new Implemented<>(HouseIcons.CheckIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.CheckIcon()),
                new Implemented<>(HouseIcons.GripIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.GripIcon()),
                new Implemented<>(HouseIcons.DiscloseIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.DiscloseIcon()),
                new Implemented<>(HouseIcons.SettingsIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SettingsIcon()),
                new Implemented<>(HouseContainers.Dialog.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Dialog()),
                new Implemented<>(HouseContainers.FloatingPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.FloatingPane()),
                new Implemented<>(HouseContainers.FloatLayer.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.FloatLayer()),
                new Implemented<>(HouseContainers.ContextMenu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ContextMenu()),
                new Implemented<>(HousePanes.MultiTabPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.MultiTabPane()),
                new Implemented<>(HouseContainers.TabStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.TabStrip()),
                new Implemented<>(HouseControls.Tab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Tab()),
                new Implemented<>(HousePanes.TabOpener.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.TabOpener()),
                new Implemented<>(HousePanes.TabPicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.TabPicker()),
                new Implemented<>(HousePanes.AddTab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.AddTab()),
                new Implemented<>(HousePanZoom.PanZoomBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PanZoomBar()),
                new Implemented<>(HousePreferences.PreferenceField.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PreferenceField()),
                new Implemented<>(HousePreferences.ChoiceWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ChoiceWidget()),
                new Implemented<>(HousePreferences.ToggleWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleWidget()),
                new Implemented<>(HousePreferences.ScaleWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ScaleWidget()),
                new Implemented<>(HousePreferences.SettingRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SettingRow()),
                new Implemented<>(HousePreferences.OverviewWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.OverviewWidget()),
                new Implemented<>(HousePreferences.ListMasterWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ListMasterWidget()),
                new Implemented<>(HouseSite.MpaChrome.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.MpaChrome()),
                new Implemented<>(HouseControls.PreferencesButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PreferencesButton()),
                new Implemented<>(HouseSite.ThemeWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ThemeWidget()),
                new Implemented<>(HouseControlPanel.ControlPanel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ControlPanel()));
    }
}
