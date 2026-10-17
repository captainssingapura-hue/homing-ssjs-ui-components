package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.spec.Implemented;
import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
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

/** neo-futurism's implementations, in the typed spec stack (RFC 0067, D53): each top-level component it styles; the rest are Base's. */
public record NeoFuturismSpec() implements SpecModule<BaseScheme, NeoFuturism> {

    public static final NeoFuturismSpec INSTANCE = new NeoFuturismSpec();

    @Override public NeoFuturism style() { return NeoFuturism.INSTANCE; }

    @Override public List<Implemented<BaseScheme, NeoFuturism, ?>> implementations() {
        return List.of(
                new Implemented<>(HouseControls.PlainButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                new Implemented<>(HouseControls.PrimaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PrimaryButton()),
                new Implemented<>(HouseControls.SecondaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SecondaryButton()),
                new Implemented<>(HouseControls.DangerButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.DangerButton()),
                new Implemented<>(HouseControls.WarningButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.WarningButton()),
                new Implemented<>(HouseControls.SuccessButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SuccessButton()),
                new Implemented<>(HouseControls.ToggleButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleButton()),
                new Implemented<>(HouseControls.CloseButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CloseButton()),
                new Implemented<>(HouseControls.IconButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.IconButton()),
                new Implemented<>(HouseText.Heading1.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading1()),
                new Implemented<>(HouseText.Heading2.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading2()),
                new Implemented<>(HouseText.Heading3.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading3()),
                new Implemented<>(HouseText.Heading4.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading4()),
                new Implemented<>(HouseText.Heading5.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading5()),
                new Implemented<>(HouseText.Heading6.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading6()),
                new Implemented<>(HouseText.Kicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker()),
                new Implemented<>(HouseText.Caption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                new Implemented<>(HouseText.Lede.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Lede()),
                new Implemented<>(HouseText.Label.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Label()),
                new Implemented<>(HouseText.Readout.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Readout()),
                new Implemented<>(HouseText.Badge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Badge()),
                new Implemented<>(HouseText.Pill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Pill()),
                new Implemented<>(HouseText.Code.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Code()),
                new Implemented<>(HouseText.SuccessBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SuccessBadge()),
                new Implemented<>(HouseText.WarningBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.WarningBadge()),
                new Implemented<>(HouseText.DangerBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.DangerBadge()),
                new Implemented<>(HouseText.Wordmark.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Wordmark()),
                new Implemented<>(HouseControls.Link.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Link()),
                new Implemented<>(HouseControls.Crumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Crumb()),
                new Implemented<>(HouseControls.TextBox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.TextBox()),
                new Implemented<>(HouseControls.Checkbox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Checkbox()),
                new Implemented<>(HouseControls.Range.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Range()),
                new Implemented<>(HouseControls.Select.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Select()),
                new Implemented<>(HouseContainers.Scrim.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Scrim()),
                new Implemented<>(HouseMarks.Separator.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Separator()),
                new Implemented<>(HouseMarks.Lamp.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Lamp()),
                new Implemented<>(HouseMarks.Detent.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Detent()),
                new Implemented<>(HouseMarks.Tick.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Tick()),
                new Implemented<>(HouseItems.MenuItem.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.MenuItem()),
                new Implemented<>(HouseItems.ListRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ListRow()),
                new Implemented<>(HouseItems.ChoiceOption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ChoiceOption()),
                new Implemented<>(HouseContainers.PlainCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainCard()),
                new Implemented<>(HouseContainers.ClickableCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ClickableCard()),
                new Implemented<>(HouseContainers.SummaryView.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SummaryView()),
                new Implemented<>(HouseMarks.SliderRail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderRail()),
                new Implemented<>(HouseMarks.SliderGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderGroove()),
                new Implemented<>(HouseMarks.Fill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Fill()),
                new Implemented<>(HouseControls.SliderKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderKnob()),
                new Implemented<>(HouseMarks.ToggleGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleGroove()),
                new Implemented<>(HouseControls.ToggleKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleKnob()),
                new Implemented<>(HouseControls.Grip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Grip()),
                new Implemented<>(HouseControls.Switch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Switch()),
                new Implemented<>(HouseControls.PaneThumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PaneThumb()),
                new Implemented<>(HouseControls.Slider.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Slider()),
                new Implemented<>(HouseContainers.SliderGroup.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderGroup()),
                new Implemented<>(HouseContainers.Panel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Panel()),
                new Implemented<>(HouseContainers.EdgeStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.EdgeStrip()),
                new Implemented<>(HouseContainers.Ground.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Ground()),
                new Implemented<>(HouseContainers.SiteBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SiteBar()),
                new Implemented<>(HouseControls.Brand.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Brand()),
                new Implemented<>(HouseContainers.Trail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Trail()),
                new Implemented<>(HouseRegions.Quote.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Quote()),
                new Implemented<>(HouseRegions.CodeBlock.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CodeBlock()),
                new Implemented<>(HouseIcons.CloseIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CloseIcon()),
                new Implemented<>(HouseContainers.Dialog.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Dialog()),
                new Implemented<>(HouseContainers.FloatingPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.FloatingPane()),
                new Implemented<>(HouseContainers.FloatLayer.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.FloatLayer()),
                new Implemented<>(HouseContainers.ContextMenu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ContextMenu()),
                new Implemented<>(HousePanes.MultiTabPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.MultiTabPane()),
                new Implemented<>(HouseContainers.TabStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.TabStrip()),
                new Implemented<>(HouseControls.Tab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Tab()),
                new Implemented<>(HousePanes.TabOpener.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.TabOpener()),
                new Implemented<>(HousePanes.TabPicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.TabPicker()),
                new Implemented<>(HousePanes.AddTab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.AddTab()),
                new Implemented<>(HousePanZoom.PanZoomBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PanZoomBar()),
                new Implemented<>(HousePreferences.PreferenceField.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PreferenceField()),
                new Implemented<>(HousePreferences.ChoiceWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ChoiceWidget()),
                new Implemented<>(HousePreferences.ToggleWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleWidget()),
                new Implemented<>(HousePreferences.ScaleWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ScaleWidget()),
                new Implemented<>(HousePreferences.SettingRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SettingRow()),
                new Implemented<>(HousePreferences.OverviewWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.OverviewWidget()),
                new Implemented<>(HousePreferences.ListMasterWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ListMasterWidget()),
                new Implemented<>(HouseSite.MpaChrome.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.MpaChrome()),
                new Implemented<>(HouseControls.PreferencesButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PreferencesButton()),
                new Implemented<>(HouseSite.ThemeWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ThemeWidget()),
                new Implemented<>(HouseControlPanel.ControlPanel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ControlPanel()));
    }
}
