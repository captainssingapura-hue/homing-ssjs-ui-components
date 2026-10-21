package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.design.spec.RoleAssignment;
import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Sketchy;
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
 * sketchy's assignments, in the typed spec stack (RFC 0067, D53, D54): each house component it draws its own way, and
 * the implementation each of its castings wears - every casting of it; the components it does not assign are Base's,
 * whole.
 */
public record SketchySpec() implements SpecModule<BaseScheme, Sketchy> {

    public static final SketchySpec INSTANCE = new SketchySpec();

    @Override public Sketchy style() { return Sketchy.INSTANCE; }

    @Override public List<ComponentModule> modules() { return HouseTaxonomy.MODULES; }

    @Override public List<ComponentAssignment<BaseScheme, Sketchy, ?>> assignments() {
        return List.of(
                new ComponentAssignment<>(HouseControls.PlainButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Go.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Cancel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Action.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Skip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.ZoomIn.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.ZoomOut.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Fit.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainButton()))),
                new ComponentAssignment<>(HouseControls.PrimaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PrimaryButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Go.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PrimaryButton()),
                        new RoleAssignment<>(HouseDoing.Cancel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PrimaryButton()),
                        new RoleAssignment<>(HouseDoing.Action.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PrimaryButton()),
                        new RoleAssignment<>(HouseDoing.Skip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PrimaryButton()))),
                new ComponentAssignment<>(HouseControls.SecondaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SecondaryButton(), List.of()),
                new ComponentAssignment<>(HouseControls.DangerButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.DangerButton(), List.of()),
                new ComponentAssignment<>(HouseControls.WarningButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.WarningButton(), List.of()),
                new ComponentAssignment<>(HouseControls.SuccessButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SuccessButton(), List.of()),
                new ComponentAssignment<>(HouseControls.ToggleButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleButton(), List.of()),
                new ComponentAssignment<>(HouseControls.CloseButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.CloseButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Close.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.CloseButton()),
                        new RoleAssignment<>(HouseDoing.Add.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.CloseButton()),
                        new RoleAssignment<>(HouseDoing.Remove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.CloseButton()))),
                new ComponentAssignment<>(HouseControls.IconButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.IconButton(), List.of()),   // no role set: placed in the void role, its look its own (21 Oct)
                new ComponentAssignment<>(HouseText.Heading1.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading1(), List.of()),
                new ComponentAssignment<>(HouseText.Heading2.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading2(), List.of()),
                new ComponentAssignment<>(HouseText.Heading3.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading3(), List.of()),
                new ComponentAssignment<>(HouseText.Heading4.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading4(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading4()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading4()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading4()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading4()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading4()))),
                new ComponentAssignment<>(HouseText.Heading5.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading5(), List.of()),
                new ComponentAssignment<>(HouseText.Heading6.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Heading6(), List.of()),
                new ComponentAssignment<>(HouseText.Kicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker()),
                        new RoleAssignment<>(HouseSaying.Hint.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker()),
                        new RoleAssignment<>(HouseSaying.Prompt.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker()),
                        new RoleAssignment<>(HouseSaying.Placeholder.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker()),
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Kicker()))),
                new ComponentAssignment<>(HouseText.Caption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption(), List.of(
                        new RoleAssignment<>(HouseSaying.Summary.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                        new RoleAssignment<>(HouseSaying.Note.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                        new RoleAssignment<>(HouseSaying.Tag.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                        new RoleAssignment<>(HouseSaying.Hint.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                        new RoleAssignment<>(HouseSaying.Prompt.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                        new RoleAssignment<>(HouseSaying.Placeholder.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Caption()))),
                new ComponentAssignment<>(HouseText.Lede.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Lede(), List.of(
                        new RoleAssignment<>(HouseSaying.Summary.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Lede()),
                        new RoleAssignment<>(HouseSaying.Note.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Lede()),
                        new RoleAssignment<>(HouseSaying.Tag.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Lede()))),
                new ComponentAssignment<>(HouseText.Label.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Label(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.LabelTitle()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Label()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Label()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Label()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Label()))),
                new ComponentAssignment<>(HouseText.Readout.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Readout(), List.of(
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Readout()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Readout()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Readout()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Readout()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Readout()))),
                new ComponentAssignment<>(HouseText.Badge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Badge(), List.of(
                        new RoleAssignment<>(HouseSaying.Summary.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Badge()),
                        new RoleAssignment<>(HouseSaying.Note.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Badge()),
                        new RoleAssignment<>(HouseSaying.Tag.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Badge()))),
                new ComponentAssignment<>(HouseText.Pill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Pill(), List.of(
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Pill()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Pill()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Pill()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Pill()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Pill()))),
                new ComponentAssignment<>(HouseText.Code.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Code(), List.of()),
                new ComponentAssignment<>(HouseText.SuccessBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SuccessBadge(), List.of()),
                new ComponentAssignment<>(HouseText.WarningBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.WarningBadge(), List.of()),
                new ComponentAssignment<>(HouseText.DangerBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.DangerBadge(), List.of()),
                new ComponentAssignment<>(HouseText.Wordmark.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Wordmark(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Wordmark()))),
                new ComponentAssignment<>(HouseControls.Link.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Link(), List.of(
                        new RoleAssignment<>(HouseDoing.Open.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Link()),
                        new RoleAssignment<>(HouseDoing.Home.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Link()),
                        new RoleAssignment<>(HouseDoing.Preferences.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Link()),
                        new RoleAssignment<>(HouseDoing.Path.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Link()),
                        new RoleAssignment<>(HouseDoing.Step.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Link()),
                        new RoleAssignment<>(HouseDoing.Previous.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Link()),
                        new RoleAssignment<>(HouseDoing.Next.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Link()),
                        new RoleAssignment<>(HouseDoing.First.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Link()),
                        new RoleAssignment<>(HouseDoing.Last.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Link()),
                        new RoleAssignment<>(HouseDoing.More.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Link()))),
                new ComponentAssignment<>(HouseControls.Crumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Crumb(), List.of(
                        new RoleAssignment<>(HouseDoing.Open.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Crumb()),
                        new RoleAssignment<>(HouseDoing.Home.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Crumb()),
                        new RoleAssignment<>(HouseDoing.Preferences.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Crumb()),
                        new RoleAssignment<>(HouseDoing.Path.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Crumb()),
                        new RoleAssignment<>(HouseDoing.Step.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Crumb()),
                        new RoleAssignment<>(HouseDoing.Previous.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Crumb()),
                        new RoleAssignment<>(HouseDoing.Next.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Crumb()),
                        new RoleAssignment<>(HouseDoing.First.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Crumb()),
                        new RoleAssignment<>(HouseDoing.Last.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Crumb()),
                        new RoleAssignment<>(HouseDoing.More.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Crumb()))),
                new ComponentAssignment<>(HouseControls.TextBox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.TextBox(), List.of()),
                new ComponentAssignment<>(HouseControls.Checkbox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Checkbox(), List.of()),
                new ComponentAssignment<>(HouseControls.Range.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Range(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Range()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Range()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Range()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Range()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Range()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Range()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Range()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Range()))),
                new ComponentAssignment<>(HouseControls.Select.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Select(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Select()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Select()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Select()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Select()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Select()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Select()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Select()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Select()))),
                new ComponentAssignment<>(HouseContainers.Scrim.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Scrim(), List.of()),
                new ComponentAssignment<>(HouseMarks.Separator.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Separator(), List.of()),
                new ComponentAssignment<>(HouseMarks.Lamp.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Lamp(), List.of()),
                new ComponentAssignment<>(HouseMarks.Detent.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Detent(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Detent()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Detent()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Detent()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Detent()))),
                new ComponentAssignment<>(HouseMarks.Tick.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Tick(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Tick()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Tick()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Tick()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Tick()))),
                new ComponentAssignment<>(HouseItems.MenuItem.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.MenuItem(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.MenuItem()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.MenuItem()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.MenuItem()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.MenuItem()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.MenuItem()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.MenuItem()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.MenuItem()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.MenuItem()))),
                new ComponentAssignment<>(HouseItems.ListRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ListRow(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ListRow()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ListRow()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ListRow()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ListRow()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ListRow()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ListRow()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ListRow()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ListRow()))),
                new ComponentAssignment<>(HouseItems.ChoiceOption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ChoiceOption(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ChoiceOption()))),
                new ComponentAssignment<>(HouseContainers.PlainCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PlainCard(), List.of()),
                new ComponentAssignment<>(HouseContainers.ClickableCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ClickableCard(), List.of()),
                new ComponentAssignment<>(HouseMarks.SliderRail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderRail(), List.of()),
                new ComponentAssignment<>(HouseMarks.SliderGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderGroove(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderGroove()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderGroove()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderGroove()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderGroove()))),
                new ComponentAssignment<>(HouseMarks.Fill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Fill(), List.of(
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Fill()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Fill()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Fill()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Fill()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Fill()))),
                new ComponentAssignment<>(HouseControls.SliderKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderKnob(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SliderKnob()))),
                new ComponentAssignment<>(HouseMarks.ToggleGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleGroove(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleGroove()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleGroove()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleGroove()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleGroove()))),
                new ComponentAssignment<>(HouseControls.ToggleKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleKnob(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ToggleKnob()))),
                new ComponentAssignment<>(HouseControls.Grip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Grip(), List.of()),
                new ComponentAssignment<>(HouseControls.Switch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Switch(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Switch()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Switch()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Switch()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Switch()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Switch()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Switch()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Switch()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Switch()))),
                new ComponentAssignment<>(HouseControls.PaneThumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.PaneThumb(), List.of()),
                new ComponentAssignment<>(HouseControls.Slider.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Slider(), List.of()),
                new ComponentAssignment<>(HouseContainers.EdgeStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.EdgeStrip(), List.of()),
                new ComponentAssignment<>(HouseContainers.Ground.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Ground(), List.of()),
                new ComponentAssignment<>(HouseContainers.SiteBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.SiteBar(), List.of()),
                new ComponentAssignment<>(HouseControls.Brand.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Brand(), List.of(
                        new RoleAssignment<>(HouseDoing.Open.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Brand()),
                        new RoleAssignment<>(HouseDoing.Home.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Brand()),
                        new RoleAssignment<>(HouseDoing.Preferences.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Brand()),
                        new RoleAssignment<>(HouseDoing.Path.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Brand()),
                        new RoleAssignment<>(HouseDoing.Step.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Brand()),
                        new RoleAssignment<>(HouseDoing.Previous.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Brand()),
                        new RoleAssignment<>(HouseDoing.Next.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Brand()),
                        new RoleAssignment<>(HouseDoing.First.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Brand()),
                        new RoleAssignment<>(HouseDoing.Last.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Brand()),
                        new RoleAssignment<>(HouseDoing.More.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Brand()))),
                new ComponentAssignment<>(HouseRegions.Quote.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Quote(), List.of()),
                new ComponentAssignment<>(HouseRegions.CodeBlock.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.CodeBlock(), List.of()),
                new ComponentAssignment<>(HouseContainers.FloatingPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.FloatingPane(), List.of()),
                new ComponentAssignment<>(HouseContainers.FloatLayer.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.FloatLayer(), List.of()),
                new ComponentAssignment<>(HouseContainers.ContextMenu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ContextMenu(), List.of()),
                new ComponentAssignment<>(HouseContainers.TabStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.TabStrip(), List.of()),
                new ComponentAssignment<>(HouseControls.Tab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.Tab(), List.of()),
                new ComponentAssignment<>(HouseContainers.ActionBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.ActionBar(), List.of()),
                new ComponentAssignment<>(HouseContainers.TitleBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.sketchy.TitleBar(), List.of()));
    }
}
