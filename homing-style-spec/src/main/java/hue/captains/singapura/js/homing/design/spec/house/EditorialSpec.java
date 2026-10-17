package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.design.spec.StandaloneEntry;
import hue.captains.singapura.js.homing.design.spec.Target;
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

/** editorial's standalones, in the typed spec stack (RFC 0067, D53): each component it draws otherwise than Base; the rest are Base's. */
public record EditorialSpec() implements SpecModule<BaseScheme, Editorial> {

    public static final EditorialSpec INSTANCE = new EditorialSpec();

    @Override public Editorial style() { return Editorial.INSTANCE; }

    @Override public List<StandaloneEntry<BaseScheme, Editorial, ?>> standalones() {
        return List.of(
                new StandaloneEntry<>(HouseControls.PlainButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton())),
                new StandaloneEntry<>(HouseControls.PrimaryButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.PrimaryButton())),
                new StandaloneEntry<>(HouseControls.SecondaryButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.SecondaryButton())),
                new StandaloneEntry<>(HouseControls.DangerButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.DangerButton())),
                new StandaloneEntry<>(HouseControls.WarningButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.WarningButton())),
                new StandaloneEntry<>(HouseControls.SuccessButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.SuccessButton())),
                new StandaloneEntry<>(HouseControls.ToggleButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleButton())),
                new StandaloneEntry<>(HouseControls.CloseButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.CloseButtonDeclaration())),
                new StandaloneEntry<>(HouseControls.IconButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.IconButtonDeclaration())),
                new StandaloneEntry<>(HouseText.Heading1.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading1())),
                new StandaloneEntry<>(HouseText.Heading2.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading2())),
                new StandaloneEntry<>(HouseText.Heading3.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading3())),
                new StandaloneEntry<>(HouseText.Heading4.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading4())),
                new StandaloneEntry<>(HouseText.Heading5.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading5())),
                new StandaloneEntry<>(HouseText.Heading6.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading6())),
                new StandaloneEntry<>(HouseText.Kicker.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker())),
                new StandaloneEntry<>(HouseText.Caption.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption())),
                new StandaloneEntry<>(HouseText.Lede.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Lede())),
                new StandaloneEntry<>(HouseText.Label.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Label())),
                new StandaloneEntry<>(HouseText.Readout.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Readout())),
                new StandaloneEntry<>(HouseText.Badge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Badge())),
                new StandaloneEntry<>(HouseText.Pill.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Pill())),
                new StandaloneEntry<>(HouseText.Code.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Code())),
                new StandaloneEntry<>(HouseText.SuccessBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.SuccessBadge())),
                new StandaloneEntry<>(HouseText.WarningBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.WarningBadge())),
                new StandaloneEntry<>(HouseText.DangerBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.DangerBadge())),
                new StandaloneEntry<>(HouseText.Wordmark.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Wordmark())),
                new StandaloneEntry<>(HouseControls.Link.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Link())),
                new StandaloneEntry<>(HouseControls.Crumb.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Crumb())),
                new StandaloneEntry<>(HouseControls.TextBox.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.TextBox())),
                new StandaloneEntry<>(HouseControls.Checkbox.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Checkbox())),
                new StandaloneEntry<>(HouseControls.Range.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Range())),
                new StandaloneEntry<>(HouseControls.Select.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Select())),
                new StandaloneEntry<>(HouseContainers.Scrim.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Scrim())),
                new StandaloneEntry<>(HouseMarks.Separator.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Separator())),
                new StandaloneEntry<>(HouseMarks.Lamp.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Lamp())),
                new StandaloneEntry<>(HouseMarks.Detent.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Detent())),
                new StandaloneEntry<>(HouseMarks.Tick.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Tick())),
                new StandaloneEntry<>(HouseItems.MenuItem.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.MenuItemDeclaration())),
                new StandaloneEntry<>(HouseItems.ListRow.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.ListRow())),
                new StandaloneEntry<>(HouseItems.ChoiceOption.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.ChoiceOptionDeclaration())),
                new StandaloneEntry<>(HouseContainers.PlainCard.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainCard())),
                new StandaloneEntry<>(HouseContainers.ClickableCard.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.ClickableCard())),
                new StandaloneEntry<>(HouseContainers.SummaryView.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.SummaryViewDeclaration())),
                new StandaloneEntry<>(HouseMarks.SliderRail.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderRail())),
                new StandaloneEntry<>(HouseMarks.SliderGroove.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderGroove())),
                new StandaloneEntry<>(HouseMarks.Fill.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Fill())),
                new StandaloneEntry<>(HouseControls.SliderKnob.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderKnob())),
                new StandaloneEntry<>(HouseMarks.ToggleGroove.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleGroove())),
                new StandaloneEntry<>(HouseControls.ToggleKnob.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleKnob())),
                new StandaloneEntry<>(HouseControls.Grip.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Grip())),
                new StandaloneEntry<>(HouseControls.Switch.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.SwitchDeclaration())),
                new StandaloneEntry<>(HouseControls.PaneThumb.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.PaneThumbDeclaration())),
                new StandaloneEntry<>(HouseControls.Slider.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderDeclaration())),
                new StandaloneEntry<>(HouseContainers.SliderGroup.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderGroupDeclaration())),
                new StandaloneEntry<>(HouseContainers.Panel.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.PanelDeclaration())),
                new StandaloneEntry<>(HouseContainers.EdgeStrip.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.EdgeStripDeclaration())),
                new StandaloneEntry<>(HouseContainers.Ground.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Ground())),
                new StandaloneEntry<>(HouseContainers.SiteBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.SiteBarDeclaration())),
                new StandaloneEntry<>(HouseControls.Brand.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.BrandDeclaration())),
                new StandaloneEntry<>(HouseContainers.Trail.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.TrailDeclaration())),
                new StandaloneEntry<>(HouseRegions.Quote.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Quote())),
                new StandaloneEntry<>(HouseRegions.CodeBlock.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.CodeBlock())),
                new StandaloneEntry<>(HouseContainers.FloatingPane.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.FloatingPaneDeclaration())),
                new StandaloneEntry<>(HouseContainers.FloatLayer.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.FloatLayerDeclaration())),
                new StandaloneEntry<>(HouseContainers.ContextMenu.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.ContextMenuDeclaration())),
                new StandaloneEntry<>(HousePanes.MultiTabPane.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.MultiTabPaneDeclaration())),
                new StandaloneEntry<>(HouseContainers.TabStrip.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.TabStripDeclaration())),
                new StandaloneEntry<>(HouseControls.Tab.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.TabDeclaration())),
                new StandaloneEntry<>(HousePanes.TabOpener.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.TabOpenerDeclaration())),
                new StandaloneEntry<>(HousePanes.TabPicker.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.TabPickerDeclaration())),
                new StandaloneEntry<>(HousePanes.AddTab.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.AddTabDeclaration())),
                new StandaloneEntry<>(HousePanZoom.PanZoomBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.PanZoomBarDeclaration())),
                new StandaloneEntry<>(HousePreferences.PreferenceField.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.PreferenceFieldDeclaration())),
                new StandaloneEntry<>(HousePreferences.ChoiceWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.ChoiceWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ToggleWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ScaleWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.ScaleWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.SettingRow.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.SettingRowDeclaration())),
                new StandaloneEntry<>(HousePreferences.OverviewWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.OverviewWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ListMasterWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.ListMasterWidgetDeclaration())),
                new StandaloneEntry<>(HouseSite.MpaChrome.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.MpaChromeDeclaration())),
                new StandaloneEntry<>(HouseControls.PreferencesButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.PreferencesButtonDeclaration())),
                new StandaloneEntry<>(HouseSite.ThemeWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.ThemeWidgetDeclaration())),
                new StandaloneEntry<>(HouseControlPanel.ControlPanel.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.ControlPanelDeclaration())),
                new StandaloneEntry<>(HouseRegions.SliderRack.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderRack())),
                new StandaloneEntry<>(HouseContainers.PanelBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.PanelBarDeclaration())),
                new StandaloneEntry<>(HouseRegions.Section.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.Section())),
                new StandaloneEntry<>(HouseContainers.ActionBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.ActionBarDeclaration())),
                new StandaloneEntry<>(HouseContainers.TitleBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.editorial.TitleBarDeclaration())));
    }
}
