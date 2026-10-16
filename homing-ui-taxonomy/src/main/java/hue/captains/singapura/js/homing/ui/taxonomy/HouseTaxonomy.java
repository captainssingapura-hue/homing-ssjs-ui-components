package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ReadTaxonomy;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtensions;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomySource;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The house's taxonomy: its common components - the base tree - and its modules' - the panes, the preferences,
 * the site's chrome and the rest - all defined here, whether or not a module's implementation is on the classpath; the
 * implementation of each, and Base's look of it, live in its own jar (RFC 0067, appendix The Style Jars, D40). A
 * downstream extends the house with a {@link hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension
 * TaxonomyExtension}, in the tree if and only if it is on the classpath. All are read into one
 * levelled tree. The branches need no listing - they are reached through the components' parents.
 * Every component is listed, those that play a role in another and those that stand alone; each
 * declares its parts as slots over the {@link HouseRoleCatalogue}.
 *
 * <p>Beside the tree, the house's role catalogue, its {@link HouseStates tree of states} and its
 * {@link HouseEvents tree of events}: not cut by module, but whole - covering the common cases, so a
 * component is built from their words, and a module adds one of its own only where none says what it
 * means.</p>
 */
public record HouseTaxonomy() implements StatelessFunctionalObject, TaxonomySource {

    public static final HouseTaxonomy INSTANCE = new HouseTaxonomy();

    /** The house's components, family by family: the base tree, then the modules'. */
    public List<Component<?>> common() {
        return List.of(
                // controls: the buttons, the handles, the rest
                HouseControls.PlainButton.INSTANCE, HouseControls.PrimaryButton.INSTANCE, HouseControls.SecondaryButton.INSTANCE,
                HouseControls.DangerButton.INSTANCE, HouseControls.WarningButton.INSTANCE, HouseControls.SuccessButton.INSTANCE,
                HouseControls.ToggleButton.INSTANCE, HouseControls.IconButton.INSTANCE, HouseControls.CloseButton.INSTANCE,
                HouseControls.SliderKnob.INSTANCE, HouseControls.ToggleKnob.INSTANCE, HouseControls.Grip.INSTANCE, HouseControls.Lip.INSTANCE,
                HouseControls.Link.INSTANCE, HouseControls.Crumb.INSTANCE, HouseControls.Range.INSTANCE, HouseControls.TextBox.INSTANCE, HouseControls.Checkbox.INSTANCE,
                HouseControls.Switch.INSTANCE, HouseControls.Slider.INSTANCE, HouseControls.Select.INSTANCE,
                HouseControls.PaneThumb.INSTANCE, HouseControls.Tab.INSTANCE, HouseControls.Brand.INSTANCE, HouseControls.PreferencesButton.INSTANCE,
                // items
                HouseItems.MenuItem.INSTANCE, HouseItems.ListRow.INSTANCE, HouseItems.ChoiceOption.INSTANCE,
                // containers
                HouseContainers.PlainCard.INSTANCE, HouseContainers.ClickableCard.INSTANCE, HouseContainers.SummaryView.INSTANCE, HouseContainers.SliderGroup.INSTANCE,
                HouseContainers.Panel.INSTANCE, HouseContainers.Dialog.INSTANCE, HouseContainers.FloatingPane.INSTANCE,
                HouseContainers.ContextMenu.INSTANCE, HouseContainers.Scrim.INSTANCE,
                HouseContainers.EdgeStrip.INSTANCE, HouseContainers.ActionBar.INSTANCE, HouseContainers.Trail.INSTANCE,
                HouseContainers.TabStrip.INSTANCE, HouseContainers.Ground.INSTANCE, HouseContainers.HeaderCell.INSTANCE, HouseContainers.DataCell.INSTANCE, HouseContainers.SiteBar.INSTANCE, HouseContainers.TitleBar.INSTANCE, HouseContainers.PanelBar.INSTANCE, HouseContainers.FloatLayer.INSTANCE,
                // regions
                HouseRegions.Section.INSTANCE, HouseRegions.StripTail.INSTANCE, HouseRegions.SliderRack.INSTANCE, HouseRegions.WidgetSlot.INSTANCE,
                // text
                HouseText.Label.INSTANCE, HouseText.Heading1.INSTANCE, HouseText.Heading2.INSTANCE, HouseText.Heading3.INSTANCE,
                HouseText.Heading4.INSTANCE, HouseText.Heading5.INSTANCE, HouseText.Heading6.INSTANCE,
                HouseText.Code.INSTANCE, HouseText.SuccessBadge.INSTANCE, HouseText.WarningBadge.INSTANCE, HouseText.DangerBadge.INSTANCE,
                HouseRegions.Quote.INSTANCE, HouseRegions.CodeBlock.INSTANCE, HouseRegions.Table.INSTANCE, HouseText.Kicker.INSTANCE, HouseText.Caption.INSTANCE,
                HouseText.Lede.INSTANCE, HouseText.Readout.INSTANCE, HouseText.TextCell.INSTANCE, HouseText.Badge.INSTANCE, HouseText.Pill.INSTANCE,
                HouseText.Wordmark.INSTANCE, HouseText.Strong.INSTANCE, HouseText.Muted.INSTANCE,
                // marks and tracks
                HouseMarks.Icon.INSTANCE, HouseMarks.Detent.INSTANCE, HouseMarks.Tick.INSTANCE, HouseMarks.Separator.INSTANCE, HouseMarks.BoundaryLine.INSTANCE,
                HouseMarks.Delimiter.INSTANCE, HouseMarks.Lamp.INSTANCE, HouseMarks.BrandMark.INSTANCE, HouseMarks.Indicator.INSTANCE, HouseMarks.DropMark.INSTANCE,
                HouseMarks.SliderRail.INSTANCE, HouseMarks.SliderGroove.INSTANCE, HouseMarks.ToggleGroove.INSTANCE, HouseMarks.Fill.INSTANCE,
                // the modules' components: defined here, each implemented - and drawn by Base - in its own jar (D40)
                HouseSite.MpaChrome.INSTANCE, HouseSite.ThemeWidget.INSTANCE,
                HousePreferences.PreferenceField.INSTANCE, HousePreferences.ChoiceWidget.INSTANCE, HousePreferences.ScaleWidget.INSTANCE,
                HousePreferences.ToggleWidget.INSTANCE, HousePreferences.ListMasterWidget.INSTANCE,
                HousePreferences.OverviewWidget.INSTANCE, HousePreferences.PreferencesView.INSTANCE, HousePreferences.SettingRow.INSTANCE,
                HousePanes.PaneThumbs.INSTANCE, HousePanes.AddTab.INSTANCE, HousePanes.TabOpener.INSTANCE, HousePanes.TabPicker.INSTANCE,
                HousePanes.MultiTabPane.INSTANCE, HousePanes.SingleTabPane.INSTANCE, HousePanes.TabPane.INSTANCE,
                HouseSplitGrid.SplitGrid.INSTANCE, HouseSplitGrid.SplitGridMirror.INSTANCE, HouseSplitGrid.GridCell.INSTANCE,
                HouseControlPanel.ControlPanel.INSTANCE,
                HousePanZoom.SvgPanZoom.INSTANCE, HousePanZoom.PanZoomBar.INSTANCE,
                HouseMenus.ContextMenuSteward.INSTANCE,
                HouseMonitors.FocusMonitor.INSTANCE, HouseMonitors.StewardMonitor.INSTANCE);
    }

    /** Every component of the house, then each downstream extension's on this classpath. */
    public List<Component<?>> components() { return TaxonomyExtensions.INSTANCE.components(HouseTaxonomy.class, common()); }

    /**
     * The house's taxonomy, read with its role catalogue and its trees of states and of events - each
     * whole in the house, and extended only where a module has a word of its own - or refused with
     * every problem in them.
     */
    @Override public Taxonomy read() {
        var extensions = TaxonomyExtensions.INSTANCE;
        return new ReadTaxonomy().read(components(),
                extensions.roles(HouseTaxonomy.class, HouseRoleCatalogue.INSTANCE.roles()),
                extensions.states(HouseTaxonomy.class, HouseStates.ALL),
                extensions.events(HouseTaxonomy.class, HouseEvents.ALL));
    }

    /**
     * The house's taxonomy with the extensions an instance registers, explicitly and in the order registered - each
     * declaring its own components, roles, states and events - and nothing found on the classpath. Refused when an
     * extension extends another taxonomy, is registered twice, or declares what the tree cannot read.
     */
    public Taxonomy read(List<? extends TaxonomyExtension> extensions) {
        return TaxonomyExtensions.INSTANCE.read(HouseTaxonomy.class, common(), HouseRoleCatalogue.INSTANCE.roles(),
                HouseStates.ALL, HouseEvents.ALL, extensions);
    }
}
