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
 * is in exactly one of three lists: a {@link #specimens() specimen} that builds it live; realized,
 * its specimen {@link #pending() still to come}; or {@link #unrealized() realized by nothing} yet,
 * so its meaning is all there is to show. Held against the house's taxonomy by its test, so a leaf
 * added to the house is placed here before anything ships.
 */
public record HouseSpecimens() implements StatelessFunctionalObject {

    public static final HouseSpecimens INSTANCE = new HouseSpecimens();

    /** The leaves seen in action, each with the class that builds it live. */
    public List<Specimen> specimens() {
        var button = new ModuleImports<>(List.of(new ButtonSpecimenModule.ButtonSpecimen()), ButtonSpecimenModule.INSTANCE);
        return List.of(
                // the six buttons: one button, each in its own colour
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
                        new ModuleImports<>(List.of(new EdgeStripSpecimenModule.EdgeStripSpecimen()), EdgeStripSpecimenModule.INSTANCE)));
    }

    /** Realized - a class of the house's, or core's, builds each - and their specimens still to come. */
    public List<Component<?>> pending() {
        return List.of(
                // overlays, menus and splits
                HouseContainers.Dialog.INSTANCE, HouseContainers.ContextMenu.INSTANCE, HouseContainers.ContextMenuSteward.INSTANCE,
                HouseContainers.FloatingPane.INSTANCE, HouseContainers.FloatLayer.INSTANCE, HouseContainers.SplitPane.INSTANCE,
                HouseContainers.SplitGrid.INSTANCE, HouseContainers.SplitGridMirror.INSTANCE,
                // panes and docking
                HouseContainers.DockGrid.INSTANCE, HouseContainers.MultiTabPane.INSTANCE, HouseContainers.SingleTabPane.INSTANCE,
                HouseContainers.TabPane.INSTANCE, HouseContainers.TabStrip.INSTANCE, HouseContainers.TabPicker.INSTANCE,
                HouseContainers.TabOpener.INSTANCE, HouseContainers.AddTab.INSTANCE, HouseContainers.PaneThumbs.INSTANCE,
                // pictures and monitors
                HouseContainers.PanZoomBar.INSTANCE, HouseContainers.SvgPanZoom.INSTANCE, HouseContainers.FocusMonitor.INSTANCE,
                HouseContainers.StewardMonitor.INSTANCE,
                // preferences and the page's chrome
                HouseContainers.MpaChrome.INSTANCE, HouseControls.PreferencesButton.INSTANCE, HouseContainers.PreferencesView.INSTANCE,
                HouseContainers.PreferenceField.INSTANCE, HouseContainers.ChoiceWidget.INSTANCE, HouseContainers.ThemeWidget.INSTANCE,
                HouseContainers.ScaleWidget.INSTANCE, HouseContainers.ToggleWidget.INSTANCE, HouseContainers.ListMasterWidget.INSTANCE,
                HouseContainers.OverviewWidget.INSTANCE,
                // core's
                HouseRegions.WidgetSlot.INSTANCE);
    }

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
