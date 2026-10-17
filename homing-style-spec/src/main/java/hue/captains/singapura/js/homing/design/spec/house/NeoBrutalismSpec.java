package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.spec.Implemented;
import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoBrutalism;
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

/** neo-brutalism's implementations, in the typed spec stack (RFC 0067, D53): each top-level component it styles; the rest are Base's. */
public record NeoBrutalismSpec() implements SpecModule<BaseScheme, NeoBrutalism> {

    public static final NeoBrutalismSpec INSTANCE = new NeoBrutalismSpec();

    @Override public NeoBrutalism style() { return NeoBrutalism.INSTANCE; }

    @Override public List<Implemented<BaseScheme, NeoBrutalism, ?>> implementations() {
        return List.of(
                new Implemented<>(HouseControls.PlainButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.PlainButton()),
                new Implemented<>(HouseControls.PrimaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.PrimaryButton()),
                new Implemented<>(HouseControls.SecondaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.SecondaryButton()),
                new Implemented<>(HouseControls.DangerButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.DangerButton()),
                new Implemented<>(HouseControls.WarningButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.WarningButton()),
                new Implemented<>(HouseControls.SuccessButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.SuccessButton()),
                new Implemented<>(HouseControls.ToggleButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.ToggleButton()),
                new Implemented<>(HouseControls.CloseButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.CloseButton()),
                new Implemented<>(HouseControls.IconButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.IconButton()),
                new Implemented<>(HouseText.Heading1.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Heading1()),
                new Implemented<>(HouseText.Heading2.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Heading2()),
                new Implemented<>(HouseText.Heading3.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Heading3()),
                new Implemented<>(HouseText.Heading4.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Heading4()),
                new Implemented<>(HouseText.Heading5.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Heading5()),
                new Implemented<>(HouseText.Heading6.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Heading6()),
                new Implemented<>(HouseText.Kicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Kicker()),
                new Implemented<>(HouseText.Caption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Caption()),
                new Implemented<>(HouseText.Lede.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Lede()),
                new Implemented<>(HouseText.Label.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Label()),
                new Implemented<>(HouseText.Readout.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Readout()),
                new Implemented<>(HouseText.Badge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Badge()),
                new Implemented<>(HouseText.Pill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Pill()),
                new Implemented<>(HouseText.Code.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Code()),
                new Implemented<>(HouseText.SuccessBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.SuccessBadge()),
                new Implemented<>(HouseText.WarningBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.WarningBadge()),
                new Implemented<>(HouseText.DangerBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.DangerBadge()),
                new Implemented<>(HouseText.Wordmark.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Wordmark()),
                new Implemented<>(HouseControls.Link.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Link()),
                new Implemented<>(HouseControls.Crumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Crumb()),
                new Implemented<>(HouseControls.TextBox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.TextBox()),
                new Implemented<>(HouseControls.Checkbox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Checkbox()),
                new Implemented<>(HouseControls.Range.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Range()),
                new Implemented<>(HouseControls.Select.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Select()),
                new Implemented<>(HouseContainers.Scrim.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Scrim()),
                new Implemented<>(HouseMarks.Separator.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Separator()),
                new Implemented<>(HouseMarks.Lamp.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Lamp()),
                new Implemented<>(HouseMarks.Detent.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Detent()),
                new Implemented<>(HouseMarks.Tick.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Tick()),
                new Implemented<>(HouseItems.MenuItem.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.MenuItem()),
                new Implemented<>(HouseItems.ListRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.ListRow()),
                new Implemented<>(HouseItems.ChoiceOption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.ChoiceOption()),
                new Implemented<>(HouseContainers.PlainCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.PlainCard()),
                new Implemented<>(HouseContainers.ClickableCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.ClickableCard()),
                new Implemented<>(HouseContainers.SummaryView.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.SummaryView()),
                new Implemented<>(HouseMarks.SliderRail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.SliderRail()),
                new Implemented<>(HouseMarks.SliderGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.SliderGroove()),
                new Implemented<>(HouseMarks.Fill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Fill()),
                new Implemented<>(HouseControls.SliderKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.SliderKnob()),
                new Implemented<>(HouseMarks.ToggleGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.ToggleGroove()),
                new Implemented<>(HouseControls.ToggleKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.ToggleKnob()),
                new Implemented<>(HouseControls.Grip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Grip()),
                new Implemented<>(HouseControls.Switch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Switch()),
                new Implemented<>(HouseControls.PaneThumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.PaneThumb()),
                new Implemented<>(HouseControls.Slider.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Slider()),
                new Implemented<>(HouseContainers.SliderGroup.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.SliderGroup()),
                new Implemented<>(HouseContainers.Panel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Panel()),
                new Implemented<>(HouseContainers.EdgeStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.EdgeStrip()),
                new Implemented<>(HouseContainers.Ground.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Ground()),
                new Implemented<>(HouseContainers.SiteBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.SiteBar()),
                new Implemented<>(HouseControls.Brand.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Brand()),
                new Implemented<>(HouseContainers.Trail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Trail()),
                new Implemented<>(HouseRegions.Quote.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Quote()),
                new Implemented<>(HouseRegions.CodeBlock.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.CodeBlock()),
                new Implemented<>(HouseIcons.CloseIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.CloseIcon()),
                new Implemented<>(HouseContainers.Dialog.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Dialog()),
                new Implemented<>(HouseContainers.FloatingPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.FloatingPane()),
                new Implemented<>(HouseContainers.FloatLayer.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.FloatLayer()),
                new Implemented<>(HouseContainers.ContextMenu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.ContextMenu()),
                new Implemented<>(HousePanes.MultiTabPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.MultiTabPane()),
                new Implemented<>(HouseContainers.TabStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.TabStrip()),
                new Implemented<>(HouseControls.Tab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.Tab()),
                new Implemented<>(HousePanes.TabOpener.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.TabOpener()),
                new Implemented<>(HousePanes.TabPicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.TabPicker()),
                new Implemented<>(HousePanes.AddTab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.AddTab()),
                new Implemented<>(HousePanZoom.PanZoomBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.PanZoomBar()),
                new Implemented<>(HousePreferences.PreferenceField.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.PreferenceField()),
                new Implemented<>(HousePreferences.ChoiceWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.ChoiceWidget()),
                new Implemented<>(HousePreferences.ToggleWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.ToggleWidget()),
                new Implemented<>(HousePreferences.ScaleWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.ScaleWidget()),
                new Implemented<>(HousePreferences.SettingRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.SettingRow()),
                new Implemented<>(HousePreferences.OverviewWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.OverviewWidget()),
                new Implemented<>(HousePreferences.ListMasterWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.ListMasterWidget()),
                new Implemented<>(HouseSite.MpaChrome.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.MpaChrome()),
                new Implemented<>(HouseControls.PreferencesButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.PreferencesButton()),
                new Implemented<>(HouseSite.ThemeWidget.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.ThemeWidget()),
                new Implemented<>(HouseControlPanel.ControlPanel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neobrutalism.ControlPanel()));
    }
}
