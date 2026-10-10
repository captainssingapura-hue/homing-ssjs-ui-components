package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ReadTaxonomy;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtensions;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomySource;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The house's taxonomy: its common components - the base tree, which every deployment of the house
 * has - then those of every module on the classpath that extends it (a {@link
 * hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension TaxonomyExtension}): the
 * panes, the dock, the preferences and the rest, each declared beside its code, in the tree if and
 * only if its module is there. A downstream extends the house the same way. All are read into one
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

    /** The house's common components, family by family: the base tree. */
    public List<Component<?>> common() {
        return List.of(
                // controls: the buttons, the handles, the rest
                HouseControls.PlainButton.INSTANCE, HouseControls.PrimaryButton.INSTANCE, HouseControls.SecondaryButton.INSTANCE,
                HouseControls.DangerButton.INSTANCE, HouseControls.WarningButton.INSTANCE, HouseControls.SuccessButton.INSTANCE,
                HouseControls.ToggleButton.INSTANCE, HouseControls.IconButton.INSTANCE, HouseControls.CloseButton.INSTANCE,
                HouseControls.Knob.INSTANCE, HouseControls.Grip.INSTANCE, HouseControls.Divider.INSTANCE,
                HouseControls.Link.INSTANCE, HouseControls.Crumb.INSTANCE, HouseControls.Range.INSTANCE,
                HouseControls.Switch.INSTANCE, HouseControls.Slider.INSTANCE, HouseControls.Select.INSTANCE,
                // items
                HouseItems.MenuItem.INSTANCE, HouseItems.ListRow.INSTANCE, HouseItems.ChoiceOption.INSTANCE,
                // containers
                HouseContainers.PlainCard.INSTANCE, HouseContainers.SummaryCard.INSTANCE, HouseContainers.SliderGroup.INSTANCE,
                HouseContainers.Panel.INSTANCE, HouseContainers.Dialog.INSTANCE, HouseContainers.FloatingPane.INSTANCE,
                HouseContainers.ContextMenu.INSTANCE, HouseContainers.Scrim.INSTANCE, HouseContainers.SplitPane.INSTANCE,
                HouseContainers.EdgeStrip.INSTANCE, HouseContainers.ActionBar.INSTANCE, HouseContainers.Trail.INSTANCE,
                // regions
                HouseRegions.Section.INSTANCE, HouseRegions.WidgetSlot.INSTANCE,
                // text
                HouseText.Label.INSTANCE, HouseText.Heading.INSTANCE, HouseText.Kicker.INSTANCE, HouseText.Caption.INSTANCE,
                HouseText.Lede.INSTANCE, HouseText.Readout.INSTANCE, HouseText.Badge.INSTANCE, HouseText.Pill.INSTANCE,
                HouseText.Wordmark.INSTANCE,
                // marks and tracks
                HouseMarks.Icon.INSTANCE, HouseMarks.Detent.INSTANCE, HouseMarks.Tick.INSTANCE, HouseMarks.Separator.INSTANCE,
                HouseMarks.Delimiter.INSTANCE, HouseMarks.Lamp.INSTANCE, HouseMarks.Indicator.INSTANCE,
                HouseMarks.Groove.INSTANCE, HouseMarks.Fill.INSTANCE);
    }

    /** Every component the house declares on this classpath: the common ones, then each extension's. */
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
}
