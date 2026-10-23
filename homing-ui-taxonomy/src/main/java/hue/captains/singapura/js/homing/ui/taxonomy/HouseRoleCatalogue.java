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
 * ninety-one roles. Whole, as the trees of states and of events are: it has the words the parts of
 * a common library's components need, whether or not a component of the house says them yet, so a
 * component - the house's or a downstream's - is built from them. A library adds a word of its own
 * under any branch only where none here says what it means; the catalogue refuses two of one name.
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
                HouseSaying.Hint.INSTANCE, HouseSaying.Prompt.INSTANCE, HouseSaying.Placeholder.INSTANCE,
                HouseSaying.Value.INSTANCE, HouseSaying.Level.INSTANCE, HouseSaying.Count.INSTANCE, HouseSaying.Status.INSTANCE,
                HouseSaying.Error.INSTANCE,
                HouseSaying.Landing.INSTANCE, HouseSaying.Check.INSTANCE,
                HouseSaying.Span.INSTANCE, HouseSaying.Rest.INSTANCE, HouseSaying.Notch.INSTANCE, HouseSaying.Figure.INSTANCE,
                // Doing: choosing, committing, changing, viewing, managing, going
                HouseDoing.Choice.INSTANCE, HouseDoing.Style.INSTANCE, HouseDoing.Palette.INSTANCE, HouseDoing.What.INSTANCE,
                HouseDoing.How.INSTANCE, HouseDoing.Where.INSTANCE, HouseDoing.Picker.INSTANCE, HouseDoing.Query.INSTANCE,
                HouseDoing.Go.INSTANCE, HouseDoing.Cancel.INSTANCE, HouseDoing.Action.INSTANCE, HouseDoing.Skip.INSTANCE,
                HouseDoing.Toggle.INSTANCE, HouseDoing.Adjust.INSTANCE, HouseDoing.Thumb.INSTANCE, HouseDoing.Splitter.INSTANCE, HouseDoing.Reset.INSTANCE,
                HouseDoing.Increase.INSTANCE, HouseDoing.Decrease.INSTANCE, HouseDoing.Clear.INSTANCE,
                HouseDoing.ZoomIn.INSTANCE, HouseDoing.ZoomOut.INSTANCE, HouseDoing.Fit.INSTANCE,
                HouseDoing.Close.INSTANCE, HouseDoing.Add.INSTANCE, HouseDoing.Remove.INSTANCE,
                HouseDoing.Open.INSTANCE, HouseDoing.Home.INSTANCE, HouseDoing.Preferences.INSTANCE, HouseDoing.Path.INSTANCE,
                HouseDoing.Step.INSTANCE, HouseDoing.Previous.INSTANCE, HouseDoing.Next.INSTANCE, HouseDoing.First.INSTANCE,
                HouseDoing.Last.INSTANCE, HouseDoing.More.INSTANCE,
                // Shaping: no role at all, then bands, layers, contents, members, dividers, handles
                HouseShaping.VoidRole.INSTANCE,
                HouseShaping.Head.INSTANCE, HouseShaping.Actions.INSTANCE, HouseShaping.Tabs.INSTANCE, HouseShaping.Foot.INSTANCE,
                HouseShaping.Window.INSTANCE, HouseShaping.Veil.INSTANCE, HouseShaping.Float.INSTANCE, HouseShaping.Popup.INSTANCE,
                HouseShaping.Page.INSTANCE, HouseShaping.Host.INSTANCE, HouseShaping.Index.INSTANCE, HouseShaping.Detail.INSTANCE,
                HouseShaping.Specimen.INSTANCE, HouseShaping.Setting.INSTANCE, HouseShaping.Layout.INSTANCE,
                HouseShaping.Body.INSTANCE, HouseShaping.Media.INSTANCE, HouseShaping.Fallback.INSTANCE, HouseShaping.Editor.INSTANCE,
                HouseShaping.Member.INSTANCE, HouseShaping.Entry.INSTANCE, HouseShaping.Dock.INSTANCE, HouseShaping.Cell.INSTANCE,
                HouseShaping.Group.INSTANCE, HouseShaping.Column.INSTANCE,
                HouseShaping.Seam.INSTANCE, HouseShaping.Break.INSTANCE,
                HouseShaping.Reveal.INSTANCE, HouseShaping.Chip.INSTANCE, HouseShaping.Opener.INSTANCE, HouseShaping.Pull.INSTANCE,
                HouseShaping.Scroll.INSTANCE,
                HouseShaping.Face.INSTANCE, HouseShaping.Leading.INSTANCE, HouseShaping.Measure.INSTANCE, HouseShaping.Keys.INSTANCE,
                HouseShaping.Chosen.INSTANCE, HouseShaping.Disclose.INSTANCE);
    }

    /** The catalogue, read on its own - its branches reached through the roles - or refused with every problem in it. */
    public RoleCatalogue read() { return new ReadTaxonomy().read(List.of(), roles()).catalogue(); }
}
