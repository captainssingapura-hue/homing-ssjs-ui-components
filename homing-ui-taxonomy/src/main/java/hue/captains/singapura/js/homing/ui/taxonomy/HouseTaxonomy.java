package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ReadTaxonomy;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The house's taxonomy: every component the house declares, read into one tree. The kinds need
 * no listing - they are reached through the components' parents - and the parts none either -
 * they are the roles the components name. Every component is listed, those that play a role in
 * another and those that stand alone.
 */
public record HouseTaxonomy() implements StatelessFunctionalObject {

    public static final HouseTaxonomy INSTANCE = new HouseTaxonomy();

    /** Every component the house declares, family by family. */
    public List<Component<?>> components() {
        return List.of(
                // controls: the buttons, the handles, the rest
                HouseControls.PlainButton.INSTANCE, HouseControls.PrimaryButton.INSTANCE, HouseControls.SecondaryButton.INSTANCE,
                HouseControls.DangerButton.INSTANCE, HouseControls.WarningButton.INSTANCE, HouseControls.SuccessButton.INSTANCE,
                HouseControls.ToggleButton.INSTANCE, HouseControls.IconButton.INSTANCE, HouseControls.CloseButton.INSTANCE,
                HouseControls.Knob.INSTANCE, HouseControls.Grip.INSTANCE, HouseControls.Divider.INSTANCE,
                HouseControls.Link.INSTANCE, HouseControls.Crumb.INSTANCE, HouseControls.Tab.INSTANCE, HouseControls.Range.INSTANCE,
                HouseControls.Switch.INSTANCE, HouseControls.PaneThumb.INSTANCE, HouseControls.Brand.INSTANCE,
                HouseControls.PreferencesButton.INSTANCE, HouseControls.Slider.INSTANCE,
                // items
                HouseItems.MenuItem.INSTANCE, HouseItems.ListRow.INSTANCE, HouseItems.ChoiceOption.INSTANCE,
                // containers
                HouseContainers.Card.INSTANCE, HouseContainers.SliderGroup.INSTANCE, HouseContainers.Panel.INSTANCE,
                HouseContainers.Dialog.INSTANCE, HouseContainers.PaneThumbs.INSTANCE, HouseContainers.AddTab.INSTANCE,
                HouseContainers.TabOpener.INSTANCE, HouseContainers.TabPicker.INSTANCE, HouseContainers.MpaChrome.INSTANCE,
                HouseContainers.FloatingPane.INSTANCE, HouseContainers.MultiTabPane.INSTANCE, HouseContainers.SingleTabPane.INSTANCE,
                HouseContainers.TabPane.INSTANCE, HouseContainers.ContextMenu.INSTANCE, HouseContainers.FloatLayer.INSTANCE,
                HouseContainers.Scrim.INSTANCE, HouseContainers.ContextMenuSteward.INSTANCE, HouseContainers.SplitPane.INSTANCE,
                HouseContainers.SplitGrid.INSTANCE, HouseContainers.SplitGridMirror.INSTANCE, HouseContainers.DockGrid.INSTANCE,
                HouseContainers.EdgeStrip.INSTANCE, HouseContainers.TabStrip.INSTANCE, HouseContainers.PanZoomBar.INSTANCE,
                HouseContainers.ActionBar.INSTANCE, HouseContainers.Trail.INSTANCE, HouseContainers.PreferenceField.INSTANCE,
                HouseContainers.ChoiceWidget.INSTANCE, HouseContainers.ThemeWidget.INSTANCE, HouseContainers.ScaleWidget.INSTANCE,
                HouseContainers.ToggleWidget.INSTANCE, HouseContainers.ListMasterWidget.INSTANCE, HouseContainers.OverviewWidget.INSTANCE,
                HouseContainers.SvgPanZoom.INSTANCE, HouseContainers.PreferencesView.INSTANCE, HouseContainers.FocusMonitor.INSTANCE,
                HouseContainers.StewardMonitor.INSTANCE,
                // regions
                HouseRegions.Section.INSTANCE, HouseRegions.GridCell.INSTANCE,
                // text
                HouseText.Label.INSTANCE, HouseText.Heading.INSTANCE, HouseText.Kicker.INSTANCE, HouseText.Caption.INSTANCE,
                HouseText.Lede.INSTANCE, HouseText.Readout.INSTANCE, HouseText.Badge.INSTANCE, HouseText.Pill.INSTANCE,
                HouseText.Wordmark.INSTANCE,
                // marks and tracks
                HouseMarks.Icon.INSTANCE, HouseMarks.Detent.INSTANCE, HouseMarks.Tick.INSTANCE, HouseMarks.Separator.INSTANCE,
                HouseMarks.Lamp.INSTANCE, HouseMarks.DropMark.INSTANCE, HouseMarks.Indicator.INSTANCE,
                HouseMarks.Groove.INSTANCE, HouseMarks.Fill.INSTANCE);
    }

    /** The house's taxonomy, read - or refused with every problem in it. */
    public Taxonomy read() { return new ReadTaxonomy().read(components()); }
}
