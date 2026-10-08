package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.ReadTaxonomy;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.RoleCatalogue;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * The house's role catalogue (RFC 0067, appendix "The Role Catalogue"): the words its components
 * name their parts with, filed by what each part does for its owner - {@link HouseSaying Saying},
 * {@link HouseDoing Doing}, {@link HouseShaping Shaping}, eighteen branches beneath them, and
 * sixty-seven roles. A component library adds its own words under any branch; the catalogue
 * refuses two of one name.
 */
public record HouseRoleCatalogue() implements StatelessFunctionalObject {

    public static final HouseRoleCatalogue INSTANCE = new HouseRoleCatalogue();

    /** Every role the house files, branch by branch. */
    public List<Role<?>> roles() {
        return List.of(
                // Saying: naming, describing, guiding, reporting, signalling, scaling
                HouseSaying.Title.INSTANCE, HouseSaying.Subtitle.INSTANCE, HouseSaying.Name.INSTANCE, HouseSaying.Category.INSTANCE,
                HouseSaying.Symbol.INSTANCE,
                HouseSaying.Summary.INSTANCE, HouseSaying.Note.INSTANCE, HouseSaying.Tag.INSTANCE,
                HouseSaying.Hint.INSTANCE, HouseSaying.Prompt.INSTANCE,
                HouseSaying.Value.INSTANCE, HouseSaying.Level.INSTANCE, HouseSaying.Count.INSTANCE, HouseSaying.Status.INSTANCE,
                HouseSaying.Keys.INSTANCE, HouseSaying.Landing.INSTANCE, HouseSaying.Disclose.INSTANCE,
                HouseSaying.Span.INSTANCE, HouseSaying.Rest.INSTANCE, HouseSaying.Notch.INSTANCE, HouseSaying.Figure.INSTANCE,
                // Doing: choosing, committing, changing, viewing, managing, going
                HouseDoing.Choice.INSTANCE, HouseDoing.Style.INSTANCE, HouseDoing.Palette.INSTANCE, HouseDoing.What.INSTANCE,
                HouseDoing.How.INSTANCE, HouseDoing.Where.INSTANCE, HouseDoing.Picker.INSTANCE,
                HouseDoing.Go.INSTANCE, HouseDoing.Cancel.INSTANCE, HouseDoing.Action.INSTANCE,
                HouseDoing.Toggle.INSTANCE, HouseDoing.Adjust.INSTANCE, HouseDoing.Thumb.INSTANCE, HouseDoing.Reset.INSTANCE,
                HouseDoing.ZoomIn.INSTANCE, HouseDoing.ZoomOut.INSTANCE, HouseDoing.Fit.INSTANCE,
                HouseDoing.Close.INSTANCE, HouseDoing.Add.INSTANCE,
                HouseDoing.Open.INSTANCE, HouseDoing.Home.INSTANCE, HouseDoing.Preferences.INSTANCE, HouseDoing.Path.INSTANCE,
                HouseDoing.Step.INSTANCE,
                // Shaping: bands, layers, contents, members, dividers, handles
                HouseShaping.Head.INSTANCE, HouseShaping.Actions.INSTANCE, HouseShaping.Tabs.INSTANCE,
                HouseShaping.Window.INSTANCE, HouseShaping.Veil.INSTANCE, HouseShaping.Float.INSTANCE, HouseShaping.Popup.INSTANCE,
                HouseShaping.Page.INSTANCE, HouseShaping.Host.INSTANCE, HouseShaping.Index.INSTANCE, HouseShaping.Detail.INSTANCE,
                HouseShaping.Specimen.INSTANCE, HouseShaping.Setting.INSTANCE, HouseShaping.Layout.INSTANCE,
                HouseShaping.Member.INSTANCE, HouseShaping.Entry.INSTANCE, HouseShaping.Dock.INSTANCE, HouseShaping.Cell.INSTANCE,
                HouseShaping.Seam.INSTANCE, HouseShaping.Break.INSTANCE,
                HouseShaping.Reveal.INSTANCE, HouseShaping.Chip.INSTANCE);
    }

    /** The catalogue, read on its own - its branches reached through the roles - or refused with every problem in it. */
    public RoleCatalogue read() { return new ReadTaxonomy().read(List.of(), roles()).catalogue(); }
}
