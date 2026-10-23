package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.design.spec.RoleAssignment;
import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.Neumorphism;
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
 * neumorphism's assignments, in the typed spec stack (RFC 0067, D53, D54): each house component it draws its own way, and
 * the implementation each of its castings wears - every casting of it; the components it does not assign are Base's,
 * whole.
 */
public record NeumorphismSpec() implements SpecModule<BaseScheme, Neumorphism> {

    public static final NeumorphismSpec INSTANCE = new NeumorphismSpec();

    @Override public Neumorphism style() { return Neumorphism.INSTANCE; }

    @Override public List<ComponentModule> modules() { return HouseTaxonomy.MODULES; }

    @Override public List<ComponentAssignment<BaseScheme, Neumorphism, ?>> assignments() {
        return List.of(
                new ComponentAssignment<>(HouseControls.PlainButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Go.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Cancel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Action.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Skip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.ZoomIn.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.ZoomOut.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Fit.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainButton()))),
                new ComponentAssignment<>(HouseControls.PrimaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PrimaryButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Go.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PrimaryButton()),
                        new RoleAssignment<>(HouseDoing.Cancel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PrimaryButton()),
                        new RoleAssignment<>(HouseDoing.Action.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PrimaryButton()),
                        new RoleAssignment<>(HouseDoing.Skip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PrimaryButton()))),
                new ComponentAssignment<>(HouseControls.SecondaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SecondaryButton(), List.of()),
                new ComponentAssignment<>(HouseControls.DangerButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.DangerButton(), List.of()),
                new ComponentAssignment<>(HouseControls.WarningButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.WarningButton(), List.of()),
                new ComponentAssignment<>(HouseControls.SuccessButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SuccessButton(), List.of()),
                new ComponentAssignment<>(HouseControls.ToggleButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleButton(), List.of()),
                new ComponentAssignment<>(HouseControls.CloseButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CloseButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Close.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CloseButton()),
                        new RoleAssignment<>(HouseDoing.Add.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CloseButton()),
                        new RoleAssignment<>(HouseDoing.Remove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CloseButton()))),
                new ComponentAssignment<>(HouseControls.IconButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.IconButton(), List.of()),   // no role set: placed in the void role, its look its own (21 Oct)
                new ComponentAssignment<>(HouseText.Heading1.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading1(), List.of()),
                new ComponentAssignment<>(HouseText.Heading2.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading2(), List.of()),
                new ComponentAssignment<>(HouseText.Heading3.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading3(), List.of()),
                new ComponentAssignment<>(HouseText.Heading4.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading4(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading4()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading4()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading4()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading4()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading4()))),
                new ComponentAssignment<>(HouseText.Heading5.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading5(), List.of()),
                new ComponentAssignment<>(HouseText.Heading6.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Heading6(), List.of()),
                new ComponentAssignment<>(HouseText.Kicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Hint.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Prompt.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Placeholder.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Kicker()))),
                new ComponentAssignment<>(HouseText.Caption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption(), List.of(
                        new RoleAssignment<>(HouseSaying.Summary.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()),
                        new RoleAssignment<>(HouseSaying.Note.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()),
                        new RoleAssignment<>(HouseSaying.Tag.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()),
                        new RoleAssignment<>(HouseSaying.Hint.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()),
                        new RoleAssignment<>(HouseSaying.Prompt.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()),
                        new RoleAssignment<>(HouseSaying.Placeholder.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()),
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()),
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Caption()))),
                new ComponentAssignment<>(HouseText.Lede.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Lede(), List.of(
                        new RoleAssignment<>(HouseSaying.Summary.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Lede()),
                        new RoleAssignment<>(HouseSaying.Note.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Lede()),
                        new RoleAssignment<>(HouseSaying.Tag.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Lede()))),
                new ComponentAssignment<>(HouseText.Label.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Label(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.LabelTitle()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Label()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Label()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Label()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Label()))),
                new ComponentAssignment<>(HouseText.Readout.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Readout(), List.of(
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Readout()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Readout()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Readout()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Readout()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Readout()))),
                new ComponentAssignment<>(HouseText.Badge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Badge(), List.of(
                        new RoleAssignment<>(HouseSaying.Summary.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Badge()),
                        new RoleAssignment<>(HouseSaying.Note.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Badge()),
                        new RoleAssignment<>(HouseSaying.Tag.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Badge()))),
                new ComponentAssignment<>(HouseText.Pill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Pill(), List.of(
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Pill()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Pill()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Pill()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Pill()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Pill()))),
                new ComponentAssignment<>(HouseText.Code.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Code(), List.of()),
                new ComponentAssignment<>(HouseText.SuccessBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SuccessBadge(), List.of()),
                new ComponentAssignment<>(HouseText.WarningBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.WarningBadge(), List.of()),
                new ComponentAssignment<>(HouseText.DangerBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.DangerBadge(), List.of()),
                new ComponentAssignment<>(HouseText.Wordmark.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Wordmark(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Wordmark()))),
                new ComponentAssignment<>(HouseControls.Link.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Link(), List.of(
                        new RoleAssignment<>(HouseDoing.Open.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Link()),
                        new RoleAssignment<>(HouseDoing.Home.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Link()),
                        new RoleAssignment<>(HouseDoing.Preferences.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Link()),
                        new RoleAssignment<>(HouseDoing.Path.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Link()),
                        new RoleAssignment<>(HouseDoing.Step.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Link()),
                        new RoleAssignment<>(HouseDoing.Previous.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Link()),
                        new RoleAssignment<>(HouseDoing.Next.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Link()),
                        new RoleAssignment<>(HouseDoing.First.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Link()),
                        new RoleAssignment<>(HouseDoing.Last.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Link()),
                        new RoleAssignment<>(HouseDoing.More.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Link()))),
                new ComponentAssignment<>(HouseControls.Crumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Crumb(), List.of(
                        new RoleAssignment<>(HouseDoing.Open.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Crumb()),
                        new RoleAssignment<>(HouseDoing.Home.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Crumb()),
                        new RoleAssignment<>(HouseDoing.Preferences.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Crumb()),
                        new RoleAssignment<>(HouseDoing.Path.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Crumb()),
                        new RoleAssignment<>(HouseDoing.Step.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Crumb()),
                        new RoleAssignment<>(HouseDoing.Previous.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Crumb()),
                        new RoleAssignment<>(HouseDoing.Next.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Crumb()),
                        new RoleAssignment<>(HouseDoing.First.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Crumb()),
                        new RoleAssignment<>(HouseDoing.Last.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Crumb()),
                        new RoleAssignment<>(HouseDoing.More.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Crumb()))),
                new ComponentAssignment<>(HouseControls.TextBox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.TextBox(), List.of()),
                new ComponentAssignment<>(HouseControls.Checkbox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Checkbox(), List.of()),
                new ComponentAssignment<>(HouseControls.Range.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Range(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Range()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Range()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Range()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Range()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Range()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Range()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Range()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Range()))),
                new ComponentAssignment<>(HouseControls.Select.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Select(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Select()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Select()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Select()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Select()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Select()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Select()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Select()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Select()))),
                new ComponentAssignment<>(HouseContainers.Scrim.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Scrim(), List.of()),
                new ComponentAssignment<>(HouseMarks.Separator.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Separator(), List.of()),
                new ComponentAssignment<>(HouseMarks.Lamp.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Lamp(), List.of()),
                new ComponentAssignment<>(HouseMarks.Detent.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Detent(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Detent()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Detent()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Detent()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Detent()))),
                new ComponentAssignment<>(HouseMarks.Tick.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Tick(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Tick()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Tick()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Tick()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Tick()))),
                new ComponentAssignment<>(HouseItems.PlainMenuItem.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainMenuItem(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainMenuItem()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainMenuItem()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainMenuItem()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainMenuItem()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainMenuItem()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainMenuItem()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainMenuItem()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainMenuItem()))),
                new ComponentAssignment<>(HouseItems.CheckMenuItem.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CheckMenuItem(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CheckMenuItem()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CheckMenuItem()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CheckMenuItem()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CheckMenuItem()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CheckMenuItem()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CheckMenuItem()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CheckMenuItem()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CheckMenuItem()))),
                new ComponentAssignment<>(HouseItems.ListRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ListRow(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ListRow()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ListRow()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ListRow()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ListRow()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ListRow()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ListRow()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ListRow()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ListRow()))),
                new ComponentAssignment<>(HouseItems.ChoiceOption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ChoiceOption(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ChoiceOption()))),
                new ComponentAssignment<>(HouseContainers.PlainCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PlainCard(), List.of()),
                new ComponentAssignment<>(HouseContainers.ClickableCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ClickableCard(), List.of()),
                new ComponentAssignment<>(HouseMarks.SliderRail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderRail(), List.of()),
                new ComponentAssignment<>(HouseMarks.SliderGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderGroove(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderGroove()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderGroove()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderGroove()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderGroove()))),
                new ComponentAssignment<>(HouseMarks.Fill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Fill(), List.of(
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Fill()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Fill()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Fill()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Fill()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Fill()))),
                new ComponentAssignment<>(HouseControls.SliderKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderKnob(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SliderKnob()))),
                new ComponentAssignment<>(HouseMarks.ToggleGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleGroove(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleGroove()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleGroove()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleGroove()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleGroove()))),
                new ComponentAssignment<>(HouseControls.ToggleKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleKnob(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ToggleKnob()))),
                new ComponentAssignment<>(HouseControls.Grip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Grip(), List.of()),
                new ComponentAssignment<>(HouseControls.Switch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Switch(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Switch()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Switch()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Switch()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Switch()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Switch()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Switch()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Switch()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Switch()))),
                new ComponentAssignment<>(HouseControls.PaneThumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.PaneThumb(), List.of()),
                new ComponentAssignment<>(HouseControls.Slider.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Slider(), List.of()),
                new ComponentAssignment<>(HouseContainers.EdgeStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.EdgeStrip(), List.of()),
                new ComponentAssignment<>(HouseContainers.Ground.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Ground(), List.of()),
                new ComponentAssignment<>(HouseContainers.SiteBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.SiteBar(), List.of()),
                new ComponentAssignment<>(HouseControls.Brand.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Brand(), List.of(
                        new RoleAssignment<>(HouseDoing.Open.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Brand()),
                        new RoleAssignment<>(HouseDoing.Home.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Brand()),
                        new RoleAssignment<>(HouseDoing.Preferences.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Brand()),
                        new RoleAssignment<>(HouseDoing.Path.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Brand()),
                        new RoleAssignment<>(HouseDoing.Step.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Brand()),
                        new RoleAssignment<>(HouseDoing.Previous.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Brand()),
                        new RoleAssignment<>(HouseDoing.Next.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Brand()),
                        new RoleAssignment<>(HouseDoing.First.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Brand()),
                        new RoleAssignment<>(HouseDoing.Last.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Brand()),
                        new RoleAssignment<>(HouseDoing.More.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Brand()))),
                new ComponentAssignment<>(HouseRegions.Quote.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Quote(), List.of()),
                new ComponentAssignment<>(HouseRegions.CodeBlock.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.CodeBlock(), List.of()),
                new ComponentAssignment<>(HouseContainers.FloatingPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.FloatingPane(), List.of()),
                new ComponentAssignment<>(HouseContainers.FloatLayer.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.FloatLayer(), List.of()),
                new ComponentAssignment<>(HouseContainers.ContextMenu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ContextMenu(), List.of()),
                new ComponentAssignment<>(HouseContainers.L1Menu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.L1Menu(), List.of()),
                new ComponentAssignment<>(HouseContainers.L2Menu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.L2Menu(), List.of()),
                new ComponentAssignment<>(HouseContainers.TabStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.TabStrip(), List.of()),
                new ComponentAssignment<>(HouseControls.Tab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.Tab(), List.of()),
                new ComponentAssignment<>(HouseContainers.ActionBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.ActionBar(), List.of()),
                new ComponentAssignment<>(HouseContainers.TitleBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neumorphism.TitleBar(), List.of()));
    }
}
