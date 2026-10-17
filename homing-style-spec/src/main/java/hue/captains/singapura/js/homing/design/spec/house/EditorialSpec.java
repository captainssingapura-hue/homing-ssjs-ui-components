package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.spec.Implemented;
import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControlPanel;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import java.util.List;

/** editorial's implementations, in the typed spec stack (RFC 0067, D53): each top-level component it styles; the rest are Base's. */
public record EditorialSpec() implements SpecModule<BaseScheme, Editorial> {

    public static final EditorialSpec INSTANCE = new EditorialSpec();

    @Override public Editorial style() { return Editorial.INSTANCE; }

    @Override public List<Implemented<BaseScheme, Editorial, ?>> implementations() {
        return List.of(
                new Implemented<>(HouseControls.PlainButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                new Implemented<>(HouseControls.PrimaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PrimaryButton()),
                new Implemented<>(HouseControls.SecondaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SecondaryButton()),
                new Implemented<>(HouseControls.DangerButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.DangerButton()),
                new Implemented<>(HouseControls.WarningButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.WarningButton()),
                new Implemented<>(HouseControls.SuccessButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SuccessButton()),
                new Implemented<>(HouseControls.ToggleButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleButton()),
                new Implemented<>(HouseControls.CloseButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.CloseButton()),
                new Implemented<>(HouseControls.IconButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.IconButton()),
                new Implemented<>(HouseText.Heading1.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading1()),
                new Implemented<>(HouseText.Heading2.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading2()),
                new Implemented<>(HouseText.Heading3.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading3()),
                new Implemented<>(HouseText.Heading4.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading4()),
                new Implemented<>(HouseText.Heading5.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading5()),
                new Implemented<>(HouseText.Heading6.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading6()),
                new Implemented<>(HouseText.Kicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker()),
                new Implemented<>(HouseText.Caption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                new Implemented<>(HouseText.Lede.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Lede()),
                new Implemented<>(HouseText.Label.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Label()),
                new Implemented<>(HouseText.Readout.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Readout()),
                new Implemented<>(HouseText.Badge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Badge()),
                new Implemented<>(HouseText.Pill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Pill()),
                new Implemented<>(HouseText.Code.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Code()),
                new Implemented<>(HouseText.SuccessBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SuccessBadge()),
                new Implemented<>(HouseText.WarningBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.WarningBadge()),
                new Implemented<>(HouseText.DangerBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.DangerBadge()),
                new Implemented<>(HouseText.Wordmark.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Wordmark()),
                new Implemented<>(HouseControls.Link.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Link()),
                new Implemented<>(HouseControls.Crumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Crumb()),
                new Implemented<>(HouseControls.TextBox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.TextBox()),
                new Implemented<>(HouseControls.Checkbox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Checkbox()),
                new Implemented<>(HouseControls.Range.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Range()),
                new Implemented<>(HouseControls.Select.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Select()),
                new Implemented<>(HouseContainers.Scrim.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Scrim()),
                new Implemented<>(HouseMarks.Separator.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Separator()),
                new Implemented<>(HouseMarks.Lamp.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Lamp()),
                new Implemented<>(HouseMarks.Detent.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Detent()),
                new Implemented<>(HouseMarks.Tick.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Tick()),
                new Implemented<>(HouseItems.MenuItem.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.MenuItem()),
                new Implemented<>(HouseItems.ListRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ListRow()),
                new Implemented<>(HouseItems.ChoiceOption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ChoiceOption()),
                new Implemented<>(HouseContainers.PlainCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainCard()),
                new Implemented<>(HouseContainers.ClickableCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ClickableCard()),
                new Implemented<>(HouseContainers.SummaryView.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SummaryView()),
                new Implemented<>(HouseMarks.SliderRail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderRail()),
                new Implemented<>(HouseMarks.SliderGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderGroove()),
                new Implemented<>(HouseMarks.Fill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Fill()),
                new Implemented<>(HouseControls.SliderKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderKnob()),
                new Implemented<>(HouseMarks.ToggleGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleGroove()),
                new Implemented<>(HouseControls.ToggleKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleKnob()),
                new Implemented<>(HouseControls.Grip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Grip()),
                new Implemented<>(HouseControls.Switch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Switch()),
                new Implemented<>(HouseControls.PaneThumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PaneThumb()),
                new Implemented<>(HouseControls.Slider.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Slider()),
                new Implemented<>(HouseContainers.SliderGroup.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderGroup()),
                new Implemented<>(HouseContainers.Panel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Panel()),
                new Implemented<>(HouseContainers.EdgeStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.EdgeStrip()),
                new Implemented<>(HouseContainers.Ground.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Ground()),
                new Implemented<>(HouseContainers.SiteBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SiteBar()),
                new Implemented<>(HouseControls.Brand.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Brand()),
                new Implemented<>(HouseContainers.Trail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Trail()),
                new Implemented<>(HouseRegions.Quote.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Quote()),
                new Implemented<>(HouseRegions.CodeBlock.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.CodeBlock()),
                new Implemented<>(HouseContainers.Dialog.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Dialog()),
                new Implemented<>(HouseContainers.FloatingPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.FloatingPane()),
                new Implemented<>(HouseContainers.FloatLayer.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.FloatLayer()),
                new Implemented<>(HouseContainers.ContextMenu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ContextMenu()),
                new Implemented<>(HousePanes.MultiTabPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.MultiTabPane()),
                new Implemented<>(HouseContainers.TabStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.TabStrip()),
                new Implemented<>(HouseControls.Tab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Tab()),
                new Implemented<>(HousePanes.TabOpener.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.TabOpener()),
                new Implemented<>(HousePanes.TabPicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.TabPicker()),
                new Implemented<>(HousePanes.AddTab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.AddTab()),
                new Implemented<>(HousePanZoom.PanZoomBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PanZoomBar()),
                new Implemented<>(HousePreferences.PreferenceField.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PreferenceField()),
                new Implemented<>(HousePreferences.ChoiceWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ChoiceWidget()),
                new Implemented<>(HousePreferences.ToggleWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleWidget()),
                new Implemented<>(HousePreferences.ScaleWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ScaleWidget()),
                new Implemented<>(HousePreferences.SettingRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SettingRow()),
                new Implemented<>(HousePreferences.OverviewWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.OverviewWidget()),
                new Implemented<>(HousePreferences.ListMasterWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ListMasterWidget()),
                new Implemented<>(HouseSite.MpaChrome.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.MpaChrome()),
                new Implemented<>(HouseControls.PreferencesButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PreferencesButton()),
                new Implemented<>(HouseSite.ThemeWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ThemeWidget()),
                new Implemented<>(HouseControlPanel.ControlPanel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ControlPanel()));
    }
}
