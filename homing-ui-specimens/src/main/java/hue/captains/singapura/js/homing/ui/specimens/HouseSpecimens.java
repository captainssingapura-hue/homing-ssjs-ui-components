package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * Which of the house's leaves can be seen in action, and how. Every leaf of the house's taxonomy
 * is in exactly one of four lists: a {@link #specimens() specimen} that builds it live; {@link
 * #aroundIt() shown by the page around} the specimens - the workspace they stand in, the page's
 * chrome, the preferences it opens; realized, its specimen {@link #pending() still to come}; or
 * {@link #unrealized() realized by nothing} yet, so its meaning is all there is to show. Held
 * against the house's taxonomy by its test, so a leaf added to the house is placed here before
 * anything ships.
 */
public record HouseSpecimens() implements StatelessFunctionalObject {

    public static final HouseSpecimens INSTANCE = new HouseSpecimens();

    /** The leaves seen in action, each with the class that builds it live. */
    public List<Specimen> specimens() {
        var button = new ModuleImports<>(List.of(new ButtonSpecimenModule.ButtonSpecimen()), ButtonSpecimenModule.INSTANCE);
        return List.of(
                // the elements: the six buttons are one button, each in its own colour
                new Specimen(HouseControls.PlainButton.INSTANCE, button),
                new Specimen(HouseControls.PrimaryButton.INSTANCE, button),
                new Specimen(HouseControls.SecondaryButton.INSTANCE, button),
                new Specimen(HouseControls.DangerButton.INSTANCE, button),
                new Specimen(HouseControls.WarningButton.INSTANCE, button),
                new Specimen(HouseControls.SuccessButton.INSTANCE, button),
                new Specimen(HouseMarks.Icon.INSTANCE,
                        new ModuleImports<>(List.of(new IconSpecimenModule.IconSpecimen()), IconSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.SummaryCard.INSTANCE,
                        new ModuleImports<>(List.of(new SummaryCardSpecimenModule.SummaryCardSpecimen()), SummaryCardSpecimenModule.INSTANCE)),
                new Specimen(HouseControls.Slider.INSTANCE,
                        new ModuleImports<>(List.of(new SliderSpecimenModule.SliderSpecimen()), SliderSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.SliderGroup.INSTANCE,
                        new ModuleImports<>(List.of(new SliderGroupSpecimenModule.SliderGroupSpecimen()), SliderGroupSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.Panel.INSTANCE,
                        new ModuleImports<>(List.of(new PanelSpecimenModule.PanelSpecimen()), PanelSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.EdgeStrip.INSTANCE,
                        new ModuleImports<>(List.of(new EdgeStripSpecimenModule.EdgeStripSpecimen()), EdgeStripSpecimenModule.INSTANCE)),
                // overlays, menus and splits
                new Specimen(HouseContainers.Dialog.INSTANCE,
                        new ModuleImports<>(List.of(new DialogSpecimenModule.DialogSpecimen()), DialogSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.ContextMenu.INSTANCE,
                        new ModuleImports<>(List.of(new ContextMenuSpecimenModule.ContextMenuSpecimen()), ContextMenuSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.ContextMenuSteward.INSTANCE,
                        new ModuleImports<>(List.of(new ContextMenuStewardSpecimenModule.ContextMenuStewardSpecimen()), ContextMenuStewardSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.FloatingPane.INSTANCE,
                        new ModuleImports<>(List.of(new FloatingPaneSpecimenModule.FloatingPaneSpecimen()), FloatingPaneSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.FloatLayer.INSTANCE,
                        new ModuleImports<>(List.of(new FloatLayerSpecimenModule.FloatLayerSpecimen()), FloatLayerSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.SplitPane.INSTANCE,
                        new ModuleImports<>(List.of(new SplitPaneSpecimenModule.SplitPaneSpecimen()), SplitPaneSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.SplitGrid.INSTANCE,
                        new ModuleImports<>(List.of(new SplitGridSpecimenModule.SplitGridSpecimen()), SplitGridSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.SplitGridMirror.INSTANCE,
                        new ModuleImports<>(List.of(new SplitGridMirrorSpecimenModule.SplitGridMirrorSpecimen()), SplitGridMirrorSpecimenModule.INSTANCE)),
                // what the page around them does not show: the tab controls, the list master, the pictures, the monitors
                new Specimen(HouseContainers.TabOpener.INSTANCE,
                        new ModuleImports<>(List.of(new TabOpenerSpecimenModule.TabOpenerSpecimen()), TabOpenerSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.AddTab.INSTANCE,
                        new ModuleImports<>(List.of(new AddTabSpecimenModule.AddTabSpecimen()), AddTabSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.PaneThumbs.INSTANCE,
                        new ModuleImports<>(List.of(new PaneThumbsSpecimenModule.PaneThumbsSpecimen()), PaneThumbsSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.ListMasterWidget.INSTANCE,
                        new ModuleImports<>(List.of(new ListMasterWidgetSpecimenModule.ListMasterWidgetSpecimen()), ListMasterWidgetSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.SvgPanZoom.INSTANCE,
                        new ModuleImports<>(List.of(new SvgPanZoomSpecimenModule.SvgPanZoomSpecimen()), SvgPanZoomSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.PanZoomBar.INSTANCE,
                        new ModuleImports<>(List.of(new PanZoomBarSpecimenModule.PanZoomBarSpecimen()), PanZoomBarSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.FocusMonitor.INSTANCE,
                        new ModuleImports<>(List.of(new FocusMonitorSpecimenModule.FocusMonitorSpecimen()), FocusMonitorSpecimenModule.INSTANCE)),
                new Specimen(HouseContainers.StewardMonitor.INSTANCE,
                        new ModuleImports<>(List.of(new StewardMonitorSpecimenModule.StewardMonitorSpecimen()), StewardMonitorSpecimenModule.INSTANCE)));
    }

    /** Shown by the page around the specimens - the workspace they stand in, the page's chrome, the preferences it opens - and where. */
    public List<ShownAround> aroundIt() {
        return List.of(
                // the workspace
                new ShownAround(HouseContainers.DockGrid.INSTANCE, "the workspace itself: its regions, each a dock, the lines between them dragged to re-share"),
                new ShownAround(HouseContainers.MultiTabPane.INSTANCE, "each region of the workspace: its tabs, one in front"),
                new ShownAround(HouseContainers.TabStrip.INSTANCE, "the bar of tabs atop each region: its chips, its plus, the count of those out of sight"),
                new ShownAround(HouseContainers.TabPane.INSTANCE, "every tab of the workspace: its chip and what it holds, which travel together"),
                new ShownAround(HouseContainers.TabPicker.INSTANCE, "a region with no tab left: what it offers to open"),
                new ShownAround(HouseContainers.SingleTabPane.INSTANCE, "a tab torn off its bar: the float that carries it"),
                // the page's chrome
                new ShownAround(HouseContainers.MpaChrome.INSTANCE, "the bar at the top of the page: the site's name, the trail, the preferences"),
                new ShownAround(HouseControls.PreferencesButton.INSTANCE, "the preferences button at the end of the bar at the top of the page"),
                // the preferences it opens
                new ShownAround(HouseContainers.PreferencesView.INSTANCE, "the preferences, opened from the bar: the settings listed, the one chosen beside them"),
                new ShownAround(HouseRegions.WidgetSlot.INSTANCE, "the preferences' list and the setting beside it: each a slot a widget is loaded into"),
                new ShownAround(HouseContainers.PreferenceField.INSTANCE, "every setting in the preferences: where it belongs, its title, a note, the way back to its default"),
                new ShownAround(HouseContainers.OverviewWidget.INSTANCE, "a group of settings in the preferences: each setting listed with its value"),
                new ShownAround(HouseContainers.ThemeWidget.INSTANCE, "the theme in the preferences: a style, and a palette to wear it in"),
                new ShownAround(HouseContainers.ChoiceWidget.INSTANCE, "a setting in the preferences chosen from a few options"),
                new ShownAround(HouseContainers.ScaleWidget.INSTANCE, "a setting in the preferences set along a scale"),
                new ShownAround(HouseContainers.ToggleWidget.INSTANCE, "a setting in the preferences that is on or off"));
    }

    /** Realized, and their specimens still to come: none now. */
    public List<Component<?>> pending() { return List.of(); }

    /** Realized by nothing yet: an owner mints them, or nothing does. What they mean is all there is to show. */
    public List<Component<?>> unrealized() {
        return List.of(
                // controls
                HouseControls.ToggleButton.INSTANCE, HouseControls.IconButton.INSTANCE, HouseControls.CloseButton.INSTANCE,
                HouseControls.Knob.INSTANCE, HouseControls.Grip.INSTANCE, HouseControls.Divider.INSTANCE, HouseControls.Link.INSTANCE,
                HouseControls.Crumb.INSTANCE, HouseControls.Tab.INSTANCE, HouseControls.Range.INSTANCE, HouseControls.Select.INSTANCE,
                HouseControls.Switch.INSTANCE, HouseControls.PaneThumb.INSTANCE, HouseControls.Brand.INSTANCE,
                // items
                HouseItems.MenuItem.INSTANCE, HouseItems.ListRow.INSTANCE, HouseItems.ChoiceOption.INSTANCE, HouseItems.SettingRow.INSTANCE,
                // containers and regions
                HouseContainers.PlainCard.INSTANCE, HouseContainers.Scrim.INSTANCE, HouseContainers.ActionBar.INSTANCE,
                HouseContainers.Trail.INSTANCE, HouseRegions.Section.INSTANCE, HouseRegions.GridCell.INSTANCE,
                // text
                HouseText.Label.INSTANCE, HouseText.Heading.INSTANCE, HouseText.Kicker.INSTANCE, HouseText.Caption.INSTANCE,
                HouseText.Lede.INSTANCE, HouseText.Readout.INSTANCE, HouseText.Badge.INSTANCE, HouseText.Pill.INSTANCE,
                HouseText.Wordmark.INSTANCE,
                // marks and tracks
                HouseMarks.Detent.INSTANCE, HouseMarks.Tick.INSTANCE, HouseMarks.Separator.INSTANCE, HouseMarks.Lamp.INSTANCE,
                HouseMarks.DropMark.INSTANCE, HouseMarks.Indicator.INSTANCE, HouseMarks.Groove.INSTANCE, HouseMarks.Fill.INSTANCE);
    }
}
