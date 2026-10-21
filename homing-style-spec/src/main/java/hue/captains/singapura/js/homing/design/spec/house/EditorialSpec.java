package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.design.spec.RoleAssignment;
import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/**
 * editorial's assignments, in the typed spec stack (RFC 0067, D53, D54): each house component it draws its own way, and
 * the implementation each of its castings wears - every casting of it; the components it does not assign are Base's,
 * whole.
 */
public record EditorialSpec() implements SpecModule<BaseScheme, Editorial> {

    public static final EditorialSpec INSTANCE = new EditorialSpec();

    @Override public Editorial style() { return Editorial.INSTANCE; }

    @Override public List<ComponentModule> modules() { return HouseTaxonomy.MODULES; }

    @Override public List<ComponentAssignment<BaseScheme, Editorial, ?>> assignments() {
        return List.of(
                new ComponentAssignment<>(HouseControls.PlainButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Go.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Cancel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Action.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Skip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.ZoomIn.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.ZoomOut.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Fit.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainButton()))),
                new ComponentAssignment<>(HouseControls.PrimaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PrimaryButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Go.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PrimaryButton()),
                        new RoleAssignment<>(HouseDoing.Cancel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PrimaryButton()),
                        new RoleAssignment<>(HouseDoing.Action.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PrimaryButton()),
                        new RoleAssignment<>(HouseDoing.Skip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PrimaryButton()))),
                new ComponentAssignment<>(HouseControls.SecondaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SecondaryButton(), List.of()),
                new ComponentAssignment<>(HouseControls.DangerButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.DangerButton(), List.of()),
                new ComponentAssignment<>(HouseControls.WarningButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.WarningButton(), List.of()),
                new ComponentAssignment<>(HouseControls.SuccessButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SuccessButton(), List.of()),
                new ComponentAssignment<>(HouseControls.ToggleButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleButton(), List.of()),
                new ComponentAssignment<>(HouseControls.CloseButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.CloseButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Close.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.CloseButton()),
                        new RoleAssignment<>(HouseDoing.Add.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.CloseButton()),
                        new RoleAssignment<>(HouseDoing.Remove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.CloseButton()))),
                new ComponentAssignment<>(HouseControls.IconButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.IconButton(), List.of()),   // no role set: placed in the void role, its look its own (21 Oct)
                new ComponentAssignment<>(HouseText.Heading1.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading1(), List.of()),
                new ComponentAssignment<>(HouseText.Heading2.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading2(), List.of()),
                new ComponentAssignment<>(HouseText.Heading3.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading3(), List.of()),
                new ComponentAssignment<>(HouseText.Heading4.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading4(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading4()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading4()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading4()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading4()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading4()))),
                new ComponentAssignment<>(HouseText.Heading5.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading5(), List.of()),
                new ComponentAssignment<>(HouseText.Heading6.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Heading6(), List.of()),
                new ComponentAssignment<>(HouseText.Kicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker()),
                        new RoleAssignment<>(HouseSaying.Hint.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker()),
                        new RoleAssignment<>(HouseSaying.Prompt.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker()),
                        new RoleAssignment<>(HouseSaying.Placeholder.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker()),
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Kicker()))),
                new ComponentAssignment<>(HouseText.Caption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption(), List.of(
                        new RoleAssignment<>(HouseSaying.Summary.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                        new RoleAssignment<>(HouseSaying.Note.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                        new RoleAssignment<>(HouseSaying.Tag.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                        new RoleAssignment<>(HouseSaying.Hint.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                        new RoleAssignment<>(HouseSaying.Prompt.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                        new RoleAssignment<>(HouseSaying.Placeholder.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Caption()))),
                new ComponentAssignment<>(HouseText.Lede.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Lede(), List.of(
                        new RoleAssignment<>(HouseSaying.Summary.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Lede()),
                        new RoleAssignment<>(HouseSaying.Note.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Lede()),
                        new RoleAssignment<>(HouseSaying.Tag.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Lede()))),
                new ComponentAssignment<>(HouseText.Label.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Label(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.LabelTitle()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Label()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Label()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Label()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Label()))),
                new ComponentAssignment<>(HouseText.Readout.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Readout(), List.of(
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Readout()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Readout()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Readout()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Readout()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Readout()))),
                new ComponentAssignment<>(HouseText.Badge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Badge(), List.of(
                        new RoleAssignment<>(HouseSaying.Summary.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Badge()),
                        new RoleAssignment<>(HouseSaying.Note.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Badge()),
                        new RoleAssignment<>(HouseSaying.Tag.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Badge()))),
                new ComponentAssignment<>(HouseText.Pill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Pill(), List.of(
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Pill()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Pill()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Pill()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Pill()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Pill()))),
                new ComponentAssignment<>(HouseText.Code.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Code(), List.of()),
                new ComponentAssignment<>(HouseText.SuccessBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SuccessBadge(), List.of()),
                new ComponentAssignment<>(HouseText.WarningBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.WarningBadge(), List.of()),
                new ComponentAssignment<>(HouseText.DangerBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.DangerBadge(), List.of()),
                new ComponentAssignment<>(HouseText.Wordmark.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Wordmark(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Wordmark()))),
                new ComponentAssignment<>(HouseControls.Link.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Link(), List.of(
                        new RoleAssignment<>(HouseDoing.Open.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Link()),
                        new RoleAssignment<>(HouseDoing.Home.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Link()),
                        new RoleAssignment<>(HouseDoing.Preferences.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Link()),
                        new RoleAssignment<>(HouseDoing.Path.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Link()),
                        new RoleAssignment<>(HouseDoing.Step.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Link()),
                        new RoleAssignment<>(HouseDoing.Previous.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Link()),
                        new RoleAssignment<>(HouseDoing.Next.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Link()),
                        new RoleAssignment<>(HouseDoing.First.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Link()),
                        new RoleAssignment<>(HouseDoing.Last.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Link()),
                        new RoleAssignment<>(HouseDoing.More.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Link()))),
                new ComponentAssignment<>(HouseControls.Crumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Crumb(), List.of(
                        new RoleAssignment<>(HouseDoing.Open.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Crumb()),
                        new RoleAssignment<>(HouseDoing.Home.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Crumb()),
                        new RoleAssignment<>(HouseDoing.Preferences.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Crumb()),
                        new RoleAssignment<>(HouseDoing.Path.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Crumb()),
                        new RoleAssignment<>(HouseDoing.Step.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Crumb()),
                        new RoleAssignment<>(HouseDoing.Previous.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Crumb()),
                        new RoleAssignment<>(HouseDoing.Next.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Crumb()),
                        new RoleAssignment<>(HouseDoing.First.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Crumb()),
                        new RoleAssignment<>(HouseDoing.Last.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Crumb()),
                        new RoleAssignment<>(HouseDoing.More.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Crumb()))),
                new ComponentAssignment<>(HouseControls.TextBox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.TextBox(), List.of()),
                new ComponentAssignment<>(HouseControls.Checkbox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Checkbox(), List.of()),
                new ComponentAssignment<>(HouseControls.Range.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Range(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Range()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Range()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Range()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Range()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Range()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Range()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Range()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Range()))),
                new ComponentAssignment<>(HouseControls.Select.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Select(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Select()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Select()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Select()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Select()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Select()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Select()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Select()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Select()))),
                new ComponentAssignment<>(HouseContainers.Scrim.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Scrim(), List.of()),
                new ComponentAssignment<>(HouseMarks.Separator.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Separator(), List.of()),
                new ComponentAssignment<>(HouseMarks.Lamp.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Lamp(), List.of()),
                new ComponentAssignment<>(HouseMarks.Detent.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Detent(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Detent()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Detent()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Detent()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Detent()))),
                new ComponentAssignment<>(HouseMarks.Tick.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Tick(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Tick()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Tick()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Tick()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Tick()))),
                new ComponentAssignment<>(HouseItems.MenuItem.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.MenuItem(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.MenuItem()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.MenuItem()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.MenuItem()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.MenuItem()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.MenuItem()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.MenuItem()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.MenuItem()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.MenuItem()))),
                new ComponentAssignment<>(HouseItems.ListRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ListRow(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ListRow()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ListRow()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ListRow()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ListRow()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ListRow()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ListRow()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ListRow()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ListRow()))),
                new ComponentAssignment<>(HouseItems.ChoiceOption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ChoiceOption(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ChoiceOption()))),
                new ComponentAssignment<>(HouseContainers.PlainCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PlainCard(), List.of()),
                new ComponentAssignment<>(HouseContainers.ClickableCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ClickableCard(), List.of()),
                new ComponentAssignment<>(HouseMarks.SliderRail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderRail(), List.of()),
                new ComponentAssignment<>(HouseMarks.SliderGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderGroove(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderGroove()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderGroove()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderGroove()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderGroove()))),
                new ComponentAssignment<>(HouseMarks.Fill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Fill(), List.of(
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Fill()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Fill()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Fill()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Fill()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Fill()))),
                new ComponentAssignment<>(HouseControls.SliderKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderKnob(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SliderKnob()))),
                new ComponentAssignment<>(HouseMarks.ToggleGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleGroove(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleGroove()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleGroove()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleGroove()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleGroove()))),
                new ComponentAssignment<>(HouseControls.ToggleKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleKnob(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ToggleKnob()))),
                new ComponentAssignment<>(HouseControls.Grip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Grip(), List.of()),
                new ComponentAssignment<>(HouseControls.Switch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Switch(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Switch()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Switch()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Switch()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Switch()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Switch()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Switch()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Switch()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Switch()))),
                new ComponentAssignment<>(HouseControls.PaneThumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.PaneThumb(), List.of()),
                new ComponentAssignment<>(HouseControls.Slider.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Slider(), List.of()),
                new ComponentAssignment<>(HouseContainers.EdgeStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.EdgeStrip(), List.of()),
                new ComponentAssignment<>(HouseContainers.Ground.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Ground(), List.of()),
                new ComponentAssignment<>(HouseContainers.SiteBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.SiteBar(), List.of()),
                new ComponentAssignment<>(HouseControls.Brand.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Brand(), List.of(
                        new RoleAssignment<>(HouseDoing.Open.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Brand()),
                        new RoleAssignment<>(HouseDoing.Home.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Brand()),
                        new RoleAssignment<>(HouseDoing.Preferences.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Brand()),
                        new RoleAssignment<>(HouseDoing.Path.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Brand()),
                        new RoleAssignment<>(HouseDoing.Step.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Brand()),
                        new RoleAssignment<>(HouseDoing.Previous.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Brand()),
                        new RoleAssignment<>(HouseDoing.Next.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Brand()),
                        new RoleAssignment<>(HouseDoing.First.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Brand()),
                        new RoleAssignment<>(HouseDoing.Last.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Brand()),
                        new RoleAssignment<>(HouseDoing.More.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Brand()))),
                new ComponentAssignment<>(HouseRegions.Quote.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Quote(), List.of()),
                new ComponentAssignment<>(HouseRegions.CodeBlock.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.CodeBlock(), List.of()),
                new ComponentAssignment<>(HouseContainers.FloatingPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.FloatingPane(), List.of()),
                new ComponentAssignment<>(HouseContainers.FloatLayer.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.FloatLayer(), List.of()),
                new ComponentAssignment<>(HouseContainers.ContextMenu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ContextMenu(), List.of()),
                new ComponentAssignment<>(HouseContainers.TabStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.TabStrip(), List.of()),
                new ComponentAssignment<>(HouseControls.Tab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.Tab(), List.of()),
                new ComponentAssignment<>(HouseContainers.ActionBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.ActionBar(), List.of()),
                new ComponentAssignment<>(HouseContainers.TitleBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.editorial.TitleBar(), List.of()));
    }
}
