package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.design.spec.StandaloneEntry;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
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

/** neumorphism's standalones, in the typed spec stack (RFC 0067, D53): each component it draws otherwise than Base; the rest are Base's. */
public record NeumorphismSpec() implements SpecModule<BaseScheme, Neumorphism> {

    public static final NeumorphismSpec INSTANCE = new NeumorphismSpec();

    @Override public Neumorphism style() { return Neumorphism.INSTANCE; }

    @Override public List<StandaloneEntry<BaseScheme, Neumorphism, ?>> standalones() {
        return List.of(
                new StandaloneEntry<>(HouseControls.PlainButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton())),
                new StandaloneEntry<>(HouseControls.PrimaryButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PrimaryButton())),
                new StandaloneEntry<>(HouseControls.SecondaryButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SecondaryButton())),
                new StandaloneEntry<>(HouseControls.DangerButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.DangerButton())),
                new StandaloneEntry<>(HouseControls.WarningButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.WarningButton())),
                new StandaloneEntry<>(HouseControls.SuccessButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SuccessButton())),
                new StandaloneEntry<>(HouseControls.ToggleButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleButton())),
                new StandaloneEntry<>(HouseControls.CloseButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CloseButtonDeclaration())),
                new StandaloneEntry<>(HouseControls.IconButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.IconButtonDeclaration())),
                new StandaloneEntry<>(HouseText.Heading1.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading1())),
                new StandaloneEntry<>(HouseText.Heading2.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading2())),
                new StandaloneEntry<>(HouseText.Heading3.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading3())),
                new StandaloneEntry<>(HouseText.Heading4.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading4())),
                new StandaloneEntry<>(HouseText.Heading5.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading5())),
                new StandaloneEntry<>(HouseText.Heading6.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading6())),
                new StandaloneEntry<>(HouseText.Kicker.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker())),
                new StandaloneEntry<>(HouseText.Caption.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption())),
                new StandaloneEntry<>(HouseText.Lede.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Lede())),
                new StandaloneEntry<>(HouseText.Label.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Label())),
                new StandaloneEntry<>(HouseText.Readout.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Readout())),
                new StandaloneEntry<>(HouseText.Badge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Badge())),
                new StandaloneEntry<>(HouseText.Pill.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Pill())),
                new StandaloneEntry<>(HouseText.Code.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Code())),
                new StandaloneEntry<>(HouseText.SuccessBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SuccessBadge())),
                new StandaloneEntry<>(HouseText.WarningBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.WarningBadge())),
                new StandaloneEntry<>(HouseText.DangerBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.DangerBadge())),
                new StandaloneEntry<>(HouseText.Wordmark.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Wordmark())),
                new StandaloneEntry<>(HouseControls.Link.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Link())),
                new StandaloneEntry<>(HouseControls.Crumb.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Crumb())),
                new StandaloneEntry<>(HouseControls.TextBox.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.TextBox())),
                new StandaloneEntry<>(HouseControls.Checkbox.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Checkbox())),
                new StandaloneEntry<>(HouseControls.Range.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Range())),
                new StandaloneEntry<>(HouseControls.Select.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Select())),
                new StandaloneEntry<>(HouseContainers.Scrim.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Scrim())),
                new StandaloneEntry<>(HouseMarks.Separator.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Separator())),
                new StandaloneEntry<>(HouseMarks.Lamp.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Lamp())),
                new StandaloneEntry<>(HouseMarks.Detent.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Detent())),
                new StandaloneEntry<>(HouseMarks.Tick.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Tick())),
                new StandaloneEntry<>(HouseItems.MenuItem.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.MenuItemDeclaration())),
                new StandaloneEntry<>(HouseItems.ListRow.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ListRow())),
                new StandaloneEntry<>(HouseItems.ChoiceOption.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ChoiceOptionDeclaration())),
                new StandaloneEntry<>(HouseContainers.PlainCard.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainCard())),
                new StandaloneEntry<>(HouseContainers.ClickableCard.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ClickableCard())),
                new StandaloneEntry<>(HouseContainers.SummaryView.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SummaryViewDeclaration())),
                new StandaloneEntry<>(HouseMarks.SliderRail.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderRail())),
                new StandaloneEntry<>(HouseMarks.SliderGroove.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderGroove())),
                new StandaloneEntry<>(HouseMarks.Fill.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Fill())),
                new StandaloneEntry<>(HouseControls.SliderKnob.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderKnob())),
                new StandaloneEntry<>(HouseMarks.ToggleGroove.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleGroove())),
                new StandaloneEntry<>(HouseControls.ToggleKnob.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleKnob())),
                new StandaloneEntry<>(HouseControls.Grip.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Grip())),
                new StandaloneEntry<>(HouseControls.Switch.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SwitchDeclaration())),
                new StandaloneEntry<>(HouseControls.PaneThumb.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PaneThumbDeclaration())),
                new StandaloneEntry<>(HouseControls.Slider.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderDeclaration())),
                new StandaloneEntry<>(HouseContainers.SliderGroup.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderGroupDeclaration())),
                new StandaloneEntry<>(HouseContainers.Panel.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PanelDeclaration())),
                new StandaloneEntry<>(HouseContainers.EdgeStrip.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.EdgeStripDeclaration())),
                new StandaloneEntry<>(HouseContainers.Ground.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Ground())),
                new StandaloneEntry<>(HouseContainers.SiteBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SiteBarDeclaration())),
                new StandaloneEntry<>(HouseControls.Brand.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.BrandDeclaration())),
                new StandaloneEntry<>(HouseContainers.Trail.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.TrailDeclaration())),
                new StandaloneEntry<>(HouseRegions.Quote.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Quote())),
                new StandaloneEntry<>(HouseRegions.CodeBlock.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CodeBlock())),
                new StandaloneEntry<>(HouseIcons.CloseIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CloseIcon())),
                new StandaloneEntry<>(HouseContainers.FloatingPane.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.FloatingPaneDeclaration())),
                new StandaloneEntry<>(HouseContainers.FloatLayer.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.FloatLayerDeclaration())),
                new StandaloneEntry<>(HouseContainers.ContextMenu.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ContextMenuDeclaration())),
                new StandaloneEntry<>(HousePanes.MultiTabPane.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.MultiTabPaneDeclaration())),
                new StandaloneEntry<>(HouseContainers.TabStrip.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.TabStripDeclaration())),
                new StandaloneEntry<>(HouseControls.Tab.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.TabDeclaration())),
                new StandaloneEntry<>(HousePanes.TabOpener.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.TabOpenerDeclaration())),
                new StandaloneEntry<>(HousePanes.TabPicker.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.TabPickerDeclaration())),
                new StandaloneEntry<>(HousePanes.AddTab.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.AddTabDeclaration())),
                new StandaloneEntry<>(HousePanZoom.PanZoomBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PanZoomBarDeclaration())),
                new StandaloneEntry<>(HousePreferences.PreferenceField.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PreferenceFieldDeclaration())),
                new StandaloneEntry<>(HousePreferences.ChoiceWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ChoiceWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ToggleWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ScaleWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ScaleWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.SettingRow.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SettingRowDeclaration())),
                new StandaloneEntry<>(HousePreferences.OverviewWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.OverviewWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ListMasterWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ListMasterWidgetDeclaration())),
                new StandaloneEntry<>(HouseSite.MpaChrome.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.MpaChromeDeclaration())),
                new StandaloneEntry<>(HouseControls.PreferencesButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PreferencesButtonDeclaration())),
                new StandaloneEntry<>(HouseSite.ThemeWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ThemeWidgetDeclaration())),
                new StandaloneEntry<>(HouseControlPanel.ControlPanel.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ControlPanelDeclaration())),
                new StandaloneEntry<>(HouseRegions.SliderRack.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderRack())),
                new StandaloneEntry<>(HouseContainers.PanelBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PanelBarDeclaration())),
                new StandaloneEntry<>(HouseRegions.Section.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Section())),
                new StandaloneEntry<>(HouseContainers.ActionBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ActionBarDeclaration())),
                new StandaloneEntry<>(HouseContainers.TitleBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.TitleBarDeclaration())));
    }
}
