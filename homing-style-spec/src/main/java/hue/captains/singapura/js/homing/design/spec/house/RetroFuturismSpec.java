package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.spec.Implemented;
import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
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

/** retro-futurism's implementations, in the typed spec stack (RFC 0067, D53): each top-level component it styles; the rest are Base's. */
public record RetroFuturismSpec() implements SpecModule<BaseScheme, RetroFuturism> {

    public static final RetroFuturismSpec INSTANCE = new RetroFuturismSpec();

    @Override public RetroFuturism style() { return RetroFuturism.INSTANCE; }

    @Override public List<Implemented<BaseScheme, RetroFuturism, ?>> implementations() {
        return List.of(
                new Implemented<>(HouseControls.PlainButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PlainButton()),
                new Implemented<>(HouseControls.PrimaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PrimaryButton()),
                new Implemented<>(HouseControls.SecondaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SecondaryButton()),
                new Implemented<>(HouseControls.DangerButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.DangerButton()),
                new Implemented<>(HouseControls.WarningButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.WarningButton()),
                new Implemented<>(HouseControls.SuccessButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SuccessButton()),
                new Implemented<>(HouseControls.ToggleButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ToggleButton()),
                new Implemented<>(HouseControls.CloseButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.CloseButton()),
                new Implemented<>(HouseControls.IconButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.IconButton()),
                new Implemented<>(HouseText.Heading1.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Heading1()),
                new Implemented<>(HouseText.Heading2.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Heading2()),
                new Implemented<>(HouseText.Heading3.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Heading3()),
                new Implemented<>(HouseText.Heading4.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Heading4()),
                new Implemented<>(HouseText.Heading5.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Heading5()),
                new Implemented<>(HouseText.Heading6.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Heading6()),
                new Implemented<>(HouseText.Kicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Kicker()),
                new Implemented<>(HouseText.Caption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Caption()),
                new Implemented<>(HouseText.Lede.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Lede()),
                new Implemented<>(HouseText.Label.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Label()),
                new Implemented<>(HouseText.Readout.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Readout()),
                new Implemented<>(HouseText.Badge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Badge()),
                new Implemented<>(HouseText.Pill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Pill()),
                new Implemented<>(HouseText.Code.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Code()),
                new Implemented<>(HouseText.SuccessBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SuccessBadge()),
                new Implemented<>(HouseText.WarningBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.WarningBadge()),
                new Implemented<>(HouseText.DangerBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.DangerBadge()),
                new Implemented<>(HouseText.Wordmark.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Wordmark()),
                new Implemented<>(HouseControls.Link.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Link()),
                new Implemented<>(HouseControls.Crumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Crumb()),
                new Implemented<>(HouseControls.TextBox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.TextBox()),
                new Implemented<>(HouseControls.Checkbox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Checkbox()),
                new Implemented<>(HouseControls.Range.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Range()),
                new Implemented<>(HouseControls.Select.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Select()),
                new Implemented<>(HouseContainers.Scrim.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Scrim()),
                new Implemented<>(HouseMarks.Separator.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Separator()),
                new Implemented<>(HouseMarks.Lamp.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Lamp()),
                new Implemented<>(HouseMarks.Detent.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Detent()),
                new Implemented<>(HouseMarks.Tick.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Tick()),
                new Implemented<>(HouseItems.MenuItem.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.MenuItem()),
                new Implemented<>(HouseItems.ListRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ListRow()),
                new Implemented<>(HouseItems.ChoiceOption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ChoiceOption()),
                new Implemented<>(HouseContainers.PlainCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PlainCard()),
                new Implemented<>(HouseContainers.ClickableCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ClickableCard()),
                new Implemented<>(HouseContainers.SummaryView.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SummaryView()),
                new Implemented<>(HouseMarks.SliderRail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SliderRail()),
                new Implemented<>(HouseMarks.SliderGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SliderGroove()),
                new Implemented<>(HouseMarks.Fill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Fill()),
                new Implemented<>(HouseControls.SliderKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SliderKnob()),
                new Implemented<>(HouseMarks.ToggleGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ToggleGroove()),
                new Implemented<>(HouseControls.ToggleKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ToggleKnob()),
                new Implemented<>(HouseControls.Grip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Grip()),
                new Implemented<>(HouseControls.Switch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Switch()),
                new Implemented<>(HouseControls.PaneThumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PaneThumb()),
                new Implemented<>(HouseControls.Slider.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Slider()),
                new Implemented<>(HouseContainers.SliderGroup.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SliderGroup()),
                new Implemented<>(HouseContainers.Panel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Panel()),
                new Implemented<>(HouseContainers.EdgeStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.EdgeStrip()),
                new Implemented<>(HouseContainers.Ground.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Ground()),
                new Implemented<>(HouseContainers.SiteBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SiteBar()),
                new Implemented<>(HouseControls.Brand.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Brand()),
                new Implemented<>(HouseContainers.Trail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Trail()),
                new Implemented<>(HouseRegions.Quote.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Quote()),
                new Implemented<>(HouseRegions.CodeBlock.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.CodeBlock()),
                new Implemented<>(HouseIcons.CloseIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.CloseIcon()),
                new Implemented<>(HouseContainers.Dialog.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Dialog()),
                new Implemented<>(HouseContainers.FloatingPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.FloatingPane()),
                new Implemented<>(HouseContainers.FloatLayer.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.FloatLayer()),
                new Implemented<>(HouseContainers.ContextMenu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ContextMenu()),
                new Implemented<>(HousePanes.MultiTabPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.MultiTabPane()),
                new Implemented<>(HouseContainers.TabStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.TabStrip()),
                new Implemented<>(HouseControls.Tab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Tab()),
                new Implemented<>(HousePanes.TabOpener.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.TabOpener()),
                new Implemented<>(HousePanes.TabPicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.TabPicker()),
                new Implemented<>(HousePanes.AddTab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.AddTab()),
                new Implemented<>(HousePanZoom.PanZoomBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PanZoomBar()),
                new Implemented<>(HousePreferences.PreferenceField.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PreferenceField()),
                new Implemented<>(HousePreferences.ChoiceWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ChoiceWidget()),
                new Implemented<>(HousePreferences.ToggleWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ToggleWidget()),
                new Implemented<>(HousePreferences.ScaleWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ScaleWidget()),
                new Implemented<>(HousePreferences.SettingRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SettingRow()),
                new Implemented<>(HousePreferences.OverviewWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.OverviewWidget()),
                new Implemented<>(HousePreferences.ListMasterWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ListMasterWidget()),
                new Implemented<>(HouseSite.MpaChrome.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.MpaChrome()),
                new Implemented<>(HouseControls.PreferencesButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PreferencesButton()),
                new Implemented<>(HouseSite.ThemeWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ThemeWidget()),
                new Implemented<>(HouseControlPanel.ControlPanel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ControlPanel()));
    }
}
