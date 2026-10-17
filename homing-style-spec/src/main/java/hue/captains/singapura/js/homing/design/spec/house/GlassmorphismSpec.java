package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.design.spec.StandaloneEntry;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
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

/** glassmorphism's standalones, in the typed spec stack (RFC 0067, D53): each component it draws otherwise than Base; the rest are Base's. */
public record GlassmorphismSpec() implements SpecModule<BaseScheme, Glassmorphism> {

    public static final GlassmorphismSpec INSTANCE = new GlassmorphismSpec();

    @Override public Glassmorphism style() { return Glassmorphism.INSTANCE; }

    @Override public List<StandaloneEntry<BaseScheme, Glassmorphism, ?>> standalones() {
        return List.of(
                new StandaloneEntry<>(HouseControls.PlainButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.PlainButton())),
                new StandaloneEntry<>(HouseControls.PrimaryButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.PrimaryButton())),
                new StandaloneEntry<>(HouseControls.SecondaryButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.SecondaryButton())),
                new StandaloneEntry<>(HouseControls.DangerButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.DangerButton())),
                new StandaloneEntry<>(HouseControls.WarningButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.WarningButton())),
                new StandaloneEntry<>(HouseControls.SuccessButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.SuccessButton())),
                new StandaloneEntry<>(HouseControls.ToggleButton.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.ToggleButton())),
                new StandaloneEntry<>(HouseControls.CloseButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.CloseButtonDeclaration())),
                new StandaloneEntry<>(HouseControls.IconButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.IconButtonDeclaration())),
                new StandaloneEntry<>(HouseText.Heading1.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Heading1())),
                new StandaloneEntry<>(HouseText.Heading2.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Heading2())),
                new StandaloneEntry<>(HouseText.Heading3.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Heading3())),
                new StandaloneEntry<>(HouseText.Heading4.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Heading4())),
                new StandaloneEntry<>(HouseText.Heading5.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Heading5())),
                new StandaloneEntry<>(HouseText.Heading6.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Heading6())),
                new StandaloneEntry<>(HouseText.Kicker.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Kicker())),
                new StandaloneEntry<>(HouseText.Caption.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Caption())),
                new StandaloneEntry<>(HouseText.Lede.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Lede())),
                new StandaloneEntry<>(HouseText.Label.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Label())),
                new StandaloneEntry<>(HouseText.Readout.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Readout())),
                new StandaloneEntry<>(HouseText.Badge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Badge())),
                new StandaloneEntry<>(HouseText.Pill.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Pill())),
                new StandaloneEntry<>(HouseText.Code.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Code())),
                new StandaloneEntry<>(HouseText.SuccessBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.SuccessBadge())),
                new StandaloneEntry<>(HouseText.WarningBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.WarningBadge())),
                new StandaloneEntry<>(HouseText.DangerBadge.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.DangerBadge())),
                new StandaloneEntry<>(HouseText.Wordmark.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Wordmark())),
                new StandaloneEntry<>(HouseControls.Link.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Link())),
                new StandaloneEntry<>(HouseControls.Crumb.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Crumb())),
                new StandaloneEntry<>(HouseControls.TextBox.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.TextBox())),
                new StandaloneEntry<>(HouseControls.Checkbox.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Checkbox())),
                new StandaloneEntry<>(HouseControls.Range.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Range())),
                new StandaloneEntry<>(HouseControls.Select.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Select())),
                new StandaloneEntry<>(HouseContainers.Scrim.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Scrim())),
                new StandaloneEntry<>(HouseMarks.Separator.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Separator())),
                new StandaloneEntry<>(HouseMarks.Lamp.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Lamp())),
                new StandaloneEntry<>(HouseMarks.Detent.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Detent())),
                new StandaloneEntry<>(HouseMarks.Tick.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Tick())),
                new StandaloneEntry<>(HouseItems.MenuItem.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.MenuItemDeclaration())),
                new StandaloneEntry<>(HouseItems.ListRow.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.ListRow())),
                new StandaloneEntry<>(HouseItems.ChoiceOption.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.ChoiceOptionDeclaration())),
                new StandaloneEntry<>(HouseContainers.PlainCard.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.PlainCard())),
                new StandaloneEntry<>(HouseContainers.ClickableCard.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.ClickableCard())),
                new StandaloneEntry<>(HouseContainers.SummaryView.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.SummaryViewDeclaration())),
                new StandaloneEntry<>(HouseMarks.SliderRail.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.SliderRail())),
                new StandaloneEntry<>(HouseMarks.SliderGroove.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.SliderGroove())),
                new StandaloneEntry<>(HouseMarks.Fill.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Fill())),
                new StandaloneEntry<>(HouseControls.SliderKnob.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.SliderKnob())),
                new StandaloneEntry<>(HouseMarks.ToggleGroove.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.ToggleGroove())),
                new StandaloneEntry<>(HouseControls.ToggleKnob.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.ToggleKnob())),
                new StandaloneEntry<>(HouseControls.Grip.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Grip())),
                new StandaloneEntry<>(HouseControls.Switch.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.SwitchDeclaration())),
                new StandaloneEntry<>(HouseControls.PaneThumb.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.PaneThumbDeclaration())),
                new StandaloneEntry<>(HouseControls.Slider.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.SliderDeclaration())),
                new StandaloneEntry<>(HouseContainers.SliderGroup.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.SliderGroupDeclaration())),
                new StandaloneEntry<>(HouseContainers.Panel.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.PanelDeclaration())),
                new StandaloneEntry<>(HouseContainers.EdgeStrip.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.EdgeStripDeclaration())),
                new StandaloneEntry<>(HouseContainers.Ground.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Ground())),
                new StandaloneEntry<>(HouseContainers.SiteBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.SiteBarDeclaration())),
                new StandaloneEntry<>(HouseControls.Brand.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.BrandDeclaration())),
                new StandaloneEntry<>(HouseContainers.Trail.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.TrailDeclaration())),
                new StandaloneEntry<>(HouseRegions.Quote.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Quote())),
                new StandaloneEntry<>(HouseRegions.CodeBlock.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.CodeBlock())),
                new StandaloneEntry<>(HouseIcons.CloseIcon.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.CloseIcon())),
                new StandaloneEntry<>(HouseContainers.FloatingPane.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.FloatingPaneDeclaration())),
                new StandaloneEntry<>(HouseContainers.FloatLayer.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.FloatLayerDeclaration())),
                new StandaloneEntry<>(HouseContainers.ContextMenu.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.ContextMenuDeclaration())),
                new StandaloneEntry<>(HousePanes.MultiTabPane.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.MultiTabPaneDeclaration())),
                new StandaloneEntry<>(HouseContainers.TabStrip.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.TabStripDeclaration())),
                new StandaloneEntry<>(HouseControls.Tab.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.TabDeclaration())),
                new StandaloneEntry<>(HousePanes.TabOpener.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.TabOpenerDeclaration())),
                new StandaloneEntry<>(HousePanes.TabPicker.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.TabPickerDeclaration())),
                new StandaloneEntry<>(HousePanes.AddTab.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.AddTabDeclaration())),
                new StandaloneEntry<>(HousePanZoom.PanZoomBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.PanZoomBarDeclaration())),
                new StandaloneEntry<>(HousePreferences.PreferenceField.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.PreferenceFieldDeclaration())),
                new StandaloneEntry<>(HousePreferences.ChoiceWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.ChoiceWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ToggleWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.ToggleWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ScaleWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.ScaleWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.SettingRow.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.SettingRowDeclaration())),
                new StandaloneEntry<>(HousePreferences.OverviewWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.OverviewWidgetDeclaration())),
                new StandaloneEntry<>(HousePreferences.ListMasterWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.ListMasterWidgetDeclaration())),
                new StandaloneEntry<>(HouseSite.MpaChrome.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.MpaChromeDeclaration())),
                new StandaloneEntry<>(HouseControls.PreferencesButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.PreferencesButtonDeclaration())),
                new StandaloneEntry<>(HouseSite.ThemeWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.ThemeWidgetDeclaration())),
                new StandaloneEntry<>(HouseControlPanel.ControlPanel.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.ControlPanelDeclaration())),
                new StandaloneEntry<>(HouseRegions.SliderRack.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.SliderRack())),
                new StandaloneEntry<>(HouseContainers.PanelBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.PanelBarDeclaration())),
                new StandaloneEntry<>(HouseRegions.Section.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.Section())),
                new StandaloneEntry<>(HouseContainers.ActionBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.ActionBarDeclaration())),
                new StandaloneEntry<>(HouseContainers.TitleBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.glassmorphism.TitleBarDeclaration())));
    }
}
