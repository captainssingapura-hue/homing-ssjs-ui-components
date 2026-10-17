package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.design.spec.StandaloneEntry;
import hue.captains.singapura.js.homing.design.spec.Target;
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

/** neo-futurism's standalones, in the typed spec stack (RFC 0067, D53): each component it draws otherwise than Base; the rest are Base's. */
public record NeoFuturismSpec() implements SpecModule<BaseScheme, NeoFuturism> {

    public static final NeoFuturismSpec INSTANCE = new NeoFuturismSpec();

    @Override public NeoFuturism style() { return NeoFuturism.INSTANCE; }

    @Override public List<StandaloneEntry<BaseScheme, NeoFuturism, ?>> standalones() {
        return List.of(
                new StandaloneEntry<>(HouseControls.PlainButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton())),
                new StandaloneEntry<>(HouseControls.PrimaryButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PrimaryButton())),
                new StandaloneEntry<>(HouseControls.SecondaryButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SecondaryButton())),
                new StandaloneEntry<>(HouseControls.DangerButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.DangerButton())),
                new StandaloneEntry<>(HouseControls.WarningButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.WarningButton())),
                new StandaloneEntry<>(HouseControls.SuccessButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SuccessButton())),
                new StandaloneEntry<>(HouseControls.ToggleButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleButton())),
                new StandaloneEntry<>(HouseControls.CloseButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CloseButtonDeclaration())),
                new StandaloneEntry<>(HouseControls.IconButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.IconButtonDeclaration())),
                new StandaloneEntry<>(HouseText.Heading1.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading1())),
                new StandaloneEntry<>(HouseText.Heading2.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading2())),
                new StandaloneEntry<>(HouseText.Heading3.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading3())),
                new StandaloneEntry<>(HouseText.Heading4.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading4())),
                new StandaloneEntry<>(HouseText.Heading5.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading5())),
                new StandaloneEntry<>(HouseText.Heading6.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading6())),
                new StandaloneEntry<>(HouseText.Kicker.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker())),
                new StandaloneEntry<>(HouseText.Caption.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption())),
                new StandaloneEntry<>(HouseText.Lede.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Lede())),
                new StandaloneEntry<>(HouseText.Label.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Label())),
                new StandaloneEntry<>(HouseText.Readout.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Readout())),
                new StandaloneEntry<>(HouseText.Badge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Badge())),
                new StandaloneEntry<>(HouseText.Pill.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Pill())),
                new StandaloneEntry<>(HouseText.Code.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Code())),
                new StandaloneEntry<>(HouseText.SuccessBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SuccessBadge())),
                new StandaloneEntry<>(HouseText.WarningBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.WarningBadge())),
                new StandaloneEntry<>(HouseText.DangerBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.DangerBadge())),
                new StandaloneEntry<>(HouseText.Wordmark.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Wordmark())),
                new StandaloneEntry<>(HouseControls.Link.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Link())),
                new StandaloneEntry<>(HouseControls.Crumb.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Crumb())),
                new StandaloneEntry<>(HouseControls.TextBox.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.TextBox())),
                new StandaloneEntry<>(HouseControls.Checkbox.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Checkbox())),
                new StandaloneEntry<>(HouseControls.Range.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Range())),
                new StandaloneEntry<>(HouseControls.Select.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Select())),
                new StandaloneEntry<>(HouseContainers.Scrim.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Scrim())),
                new StandaloneEntry<>(HouseMarks.Separator.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Separator())),
                new StandaloneEntry<>(HouseMarks.Lamp.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Lamp())),
                new StandaloneEntry<>(HouseMarks.Detent.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Detent())),
                new StandaloneEntry<>(HouseMarks.Tick.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Tick())),
                new StandaloneEntry<>(HouseItems.MenuItem.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.MenuItemDeclaration())),
                new StandaloneEntry<>(HouseItems.ListRow.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ListRow())),
                new StandaloneEntry<>(HouseItems.ChoiceOption.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ChoiceOptionDeclaration())),
                new StandaloneEntry<>(HouseContainers.PlainCard.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainCard())),
                new StandaloneEntry<>(HouseContainers.ClickableCard.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ClickableCard())),
                new StandaloneEntry<>(HouseContainers.SummaryView.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SummaryViewDeclaration())),
                new StandaloneEntry<>(HouseMarks.SliderRail.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderRail())),
                new StandaloneEntry<>(HouseMarks.SliderGroove.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderGroove())),
                new StandaloneEntry<>(HouseMarks.Fill.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Fill())),
                new StandaloneEntry<>(HouseControls.SliderKnob.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderKnob())),
                new StandaloneEntry<>(HouseMarks.ToggleGroove.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleGroove())),
                new StandaloneEntry<>(HouseControls.ToggleKnob.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleKnob())),
                new StandaloneEntry<>(HouseControls.Grip.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Grip())),
                new StandaloneEntry<>(HouseControls.Switch.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SwitchDeclaration())),
                new StandaloneEntry<>(HouseControls.PaneThumb.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PaneThumbDeclaration())),
                new StandaloneEntry<>(HouseControls.Slider.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderDeclaration())),
                new StandaloneEntry<>(HouseContainers.SliderGroup.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderGroupDeclaration())),
                new StandaloneEntry<>(HouseContainers.Panel.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PanelDeclaration())),
                new StandaloneEntry<>(HouseContainers.EdgeStrip.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.EdgeStripDeclaration())),
                new StandaloneEntry<>(HouseContainers.Ground.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Ground())),
                new StandaloneEntry<>(HouseContainers.SiteBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SiteBarDeclaration())),
                new StandaloneEntry<>(HouseControls.Brand.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.BrandDeclaration())),
                new StandaloneEntry<>(HouseContainers.Trail.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.TrailDeclaration())),
                new StandaloneEntry<>(HouseRegions.Quote.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Quote())),
                new StandaloneEntry<>(HouseRegions.CodeBlock.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CodeBlock())),
                new StandaloneEntry<>(HouseIcons.CloseIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CloseIcon())),
                new StandaloneEntry<>(HouseContainers.FloatingPane.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.FloatingPaneDeclaration())),
                new StandaloneEntry<>(HouseContainers.FloatLayer.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.FloatLayerDeclaration())),
                new StandaloneEntry<>(HouseContainers.ContextMenu.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ContextMenuDeclaration())),
                new StandaloneEntry<>(HousePanes.MultiTabPane.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.MultiTabPaneDeclaration())),
                new StandaloneEntry<>(HouseContainers.TabStrip.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.TabStripDeclaration())),
                new StandaloneEntry<>(HouseControls.Tab.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.TabDeclaration())),
                new StandaloneEntry<>(HousePanes.TabOpener.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.TabOpenerDeclaration())),
                new StandaloneEntry<>(HousePanes.TabPicker.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.TabPickerDeclaration())),
                new StandaloneEntry<>(HousePanes.AddTab.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.AddTabDeclaration())),
                new StandaloneEntry<>(HousePanZoom.PanZoomBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PanZoomBarDeclaration())),
                new StandaloneEntry<>(HousePreferences.PreferenceField.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PreferenceFieldDeclaration())),
                new StandaloneEntry<>(HousePreferences.ChoiceWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ChoiceWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ToggleWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ScaleWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ScaleWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.SettingRow.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SettingRowDeclaration())),
                new StandaloneEntry<>(HousePreferences.OverviewWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.OverviewWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ListMasterWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ListMasterWidgetDeclaration())),
                new StandaloneEntry<>(HouseSite.MpaChrome.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.MpaChromeDeclaration())),
                new StandaloneEntry<>(HouseControls.PreferencesButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PreferencesButtonDeclaration())),
                new StandaloneEntry<>(HouseSite.ThemeWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ThemeWidgetDeclaration())),
                new StandaloneEntry<>(HouseControlPanel.ControlPanel.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ControlPanelDeclaration())),
                new StandaloneEntry<>(HouseRegions.SliderRack.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderRack())),
                new StandaloneEntry<>(HouseContainers.PanelBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PanelBarDeclaration())),
                new StandaloneEntry<>(HouseRegions.Section.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Section())),
                new StandaloneEntry<>(HouseContainers.ActionBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ActionBarDeclaration())),
                new StandaloneEntry<>(HouseContainers.TitleBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.TitleBarDeclaration())));
    }
}
