package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.design.spec.RoleAssignment;
import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
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
 * neo-futurism's assignments, in the typed spec stack (RFC 0067, D53, D54): each house component it draws its own way, and
 * the implementation each of its castings wears - every casting of it; the components it does not assign are Base's,
 * whole.
 */
public record NeoFuturismSpec() implements SpecModule<BaseScheme, NeoFuturism> {

    public static final NeoFuturismSpec INSTANCE = new NeoFuturismSpec();

    @Override public NeoFuturism style() { return NeoFuturism.INSTANCE; }

    @Override public List<ComponentModule> modules() { return HouseTaxonomy.MODULES; }

    @Override public List<ComponentAssignment<BaseScheme, NeoFuturism, ?>> assignments() {
        return List.of(
                new ComponentAssignment<>(HouseControls.PlainButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Go.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Cancel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Action.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Skip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.ZoomIn.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.ZoomOut.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Fit.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainButton()))),
                new ComponentAssignment<>(HouseControls.PrimaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PrimaryButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Go.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PrimaryButton()),
                        new RoleAssignment<>(HouseDoing.Cancel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PrimaryButton()),
                        new RoleAssignment<>(HouseDoing.Action.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PrimaryButton()),
                        new RoleAssignment<>(HouseDoing.Skip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PrimaryButton()))),
                new ComponentAssignment<>(HouseControls.SecondaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SecondaryButton(), List.of()),
                new ComponentAssignment<>(HouseControls.DangerButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.DangerButton(), List.of()),
                new ComponentAssignment<>(HouseControls.WarningButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.WarningButton(), List.of()),
                new ComponentAssignment<>(HouseControls.SuccessButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SuccessButton(), List.of()),
                new ComponentAssignment<>(HouseControls.ToggleButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleButton(), List.of()),
                new ComponentAssignment<>(HouseControls.CloseButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CloseButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Close.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CloseButton()),
                        new RoleAssignment<>(HouseDoing.Add.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CloseButton()),
                        new RoleAssignment<>(HouseDoing.Remove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CloseButton()))),
                new ComponentAssignment<>(HouseControls.IconButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.IconButton(), List.of()),   // no role set: placed in the void role, its look its own (21 Oct)
                new ComponentAssignment<>(HouseText.Heading1.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading1(), List.of()),
                new ComponentAssignment<>(HouseText.Heading2.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading2(), List.of()),
                new ComponentAssignment<>(HouseText.Heading3.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading3(), List.of()),
                new ComponentAssignment<>(HouseText.Heading4.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading4(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading4()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading4()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading4()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading4()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading4()))),
                new ComponentAssignment<>(HouseText.Heading5.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading5(), List.of()),
                new ComponentAssignment<>(HouseText.Heading6.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Heading6(), List.of()),
                new ComponentAssignment<>(HouseText.Kicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Hint.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Prompt.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Placeholder.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Kicker()))),
                new ComponentAssignment<>(HouseText.Caption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption(), List.of(
                        new RoleAssignment<>(HouseSaying.Summary.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Note.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Tag.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Hint.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Prompt.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Placeholder.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Caption()))),
                new ComponentAssignment<>(HouseText.Lede.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Lede(), List.of(
                        new RoleAssignment<>(HouseSaying.Summary.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Lede()),
                        new RoleAssignment<>(HouseSaying.Note.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Lede()),
                        new RoleAssignment<>(HouseSaying.Tag.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Lede()))),
                new ComponentAssignment<>(HouseText.Label.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Label(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.LabelTitle()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Label()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Label()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Label()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Label()))),
                new ComponentAssignment<>(HouseText.Readout.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Readout(), List.of(
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Readout()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Readout()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Readout()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Readout()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Readout()))),
                new ComponentAssignment<>(HouseText.Badge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Badge(), List.of(
                        new RoleAssignment<>(HouseSaying.Summary.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Badge()),
                        new RoleAssignment<>(HouseSaying.Note.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Badge()),
                        new RoleAssignment<>(HouseSaying.Tag.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Badge()))),
                new ComponentAssignment<>(HouseText.Pill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Pill(), List.of(
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Pill()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Pill()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Pill()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Pill()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Pill()))),
                new ComponentAssignment<>(HouseText.Code.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Code(), List.of()),
                new ComponentAssignment<>(HouseText.SuccessBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SuccessBadge(), List.of()),
                new ComponentAssignment<>(HouseText.WarningBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.WarningBadge(), List.of()),
                new ComponentAssignment<>(HouseText.DangerBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.DangerBadge(), List.of()),
                new ComponentAssignment<>(HouseText.Wordmark.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Wordmark(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Wordmark()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Wordmark()))),
                new ComponentAssignment<>(HouseControls.Link.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Link(), List.of(
                        new RoleAssignment<>(HouseDoing.Open.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Link()),
                        new RoleAssignment<>(HouseDoing.Home.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Link()),
                        new RoleAssignment<>(HouseDoing.Preferences.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Link()),
                        new RoleAssignment<>(HouseDoing.Path.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Link()),
                        new RoleAssignment<>(HouseDoing.Step.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Link()),
                        new RoleAssignment<>(HouseDoing.Previous.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Link()),
                        new RoleAssignment<>(HouseDoing.Next.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Link()),
                        new RoleAssignment<>(HouseDoing.First.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Link()),
                        new RoleAssignment<>(HouseDoing.Last.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Link()),
                        new RoleAssignment<>(HouseDoing.More.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Link()))),
                new ComponentAssignment<>(HouseControls.Crumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Crumb(), List.of(
                        new RoleAssignment<>(HouseDoing.Open.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Crumb()),
                        new RoleAssignment<>(HouseDoing.Home.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Crumb()),
                        new RoleAssignment<>(HouseDoing.Preferences.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Crumb()),
                        new RoleAssignment<>(HouseDoing.Path.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Crumb()),
                        new RoleAssignment<>(HouseDoing.Step.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Crumb()),
                        new RoleAssignment<>(HouseDoing.Previous.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Crumb()),
                        new RoleAssignment<>(HouseDoing.Next.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Crumb()),
                        new RoleAssignment<>(HouseDoing.First.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Crumb()),
                        new RoleAssignment<>(HouseDoing.Last.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Crumb()),
                        new RoleAssignment<>(HouseDoing.More.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Crumb()))),
                new ComponentAssignment<>(HouseControls.TextBox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.TextBox(), List.of()),
                new ComponentAssignment<>(HouseControls.Checkbox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Checkbox(), List.of()),
                new ComponentAssignment<>(HouseControls.Range.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Range(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Range()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Range()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Range()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Range()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Range()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Range()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Range()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Range()))),
                new ComponentAssignment<>(HouseControls.Select.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Select(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Select()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Select()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Select()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Select()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Select()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Select()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Select()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Select()))),
                new ComponentAssignment<>(HouseContainers.Scrim.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Scrim(), List.of()),
                new ComponentAssignment<>(HouseMarks.Separator.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Separator(), List.of()),
                new ComponentAssignment<>(HouseMarks.Lamp.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Lamp(), List.of()),
                new ComponentAssignment<>(HouseMarks.Detent.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Detent(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Detent()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Detent()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Detent()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Detent()))),
                new ComponentAssignment<>(HouseMarks.Tick.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Tick(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Tick()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Tick()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Tick()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Tick()))),
                new ComponentAssignment<>(HouseItems.PlainMenuItem.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainMenuItem(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainMenuItem()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainMenuItem()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainMenuItem()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainMenuItem()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainMenuItem()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainMenuItem()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainMenuItem()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainMenuItem()))),
                new ComponentAssignment<>(HouseItems.CheckMenuItem.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CheckMenuItem(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CheckMenuItem()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CheckMenuItem()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CheckMenuItem()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CheckMenuItem()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CheckMenuItem()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CheckMenuItem()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CheckMenuItem()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CheckMenuItem()))),
                new ComponentAssignment<>(HouseItems.ListRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ListRow(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ListRow()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ListRow()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ListRow()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ListRow()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ListRow()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ListRow()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ListRow()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ListRow()))),
                new ComponentAssignment<>(HouseItems.ChoiceOption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ChoiceOption(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Where.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Picker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Query.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ChoiceOption()))),
                new ComponentAssignment<>(HouseContainers.PlainCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PlainCard(), List.of()),
                new ComponentAssignment<>(HouseContainers.ClickableCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ClickableCard(), List.of()),
                new ComponentAssignment<>(HouseMarks.SliderRail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderRail(), List.of()),
                new ComponentAssignment<>(HouseMarks.SliderGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderGroove(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderGroove()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderGroove()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderGroove()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderGroove()))),
                new ComponentAssignment<>(HouseMarks.Fill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Fill(), List.of(
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Fill()),
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Fill()),
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Fill()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Fill()),
                        new RoleAssignment<>(HouseSaying.Error.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Fill()))),
                new ComponentAssignment<>(HouseControls.SliderKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderKnob(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderKnob()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SliderKnob()))),
                new ComponentAssignment<>(HouseMarks.ToggleGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleGroove(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleGroove()),
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleGroove()),
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleGroove()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleGroove()))),
                new ComponentAssignment<>(HouseControls.ToggleKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleKnob(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleKnob()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ToggleKnob()))),
                new ComponentAssignment<>(HouseControls.Grip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Grip(), List.of()),
                new ComponentAssignment<>(HouseControls.Switch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Switch(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Switch()),
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Switch()),
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Switch()),
                        new RoleAssignment<>(HouseDoing.Splitter.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Switch()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Switch()),
                        new RoleAssignment<>(HouseDoing.Increase.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Switch()),
                        new RoleAssignment<>(HouseDoing.Decrease.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Switch()),
                        new RoleAssignment<>(HouseDoing.Clear.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Switch()))),
                new ComponentAssignment<>(HouseControls.PaneThumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.PaneThumb(), List.of()),
                new ComponentAssignment<>(HouseControls.Slider.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Slider(), List.of()),
                new ComponentAssignment<>(HouseContainers.EdgeStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.EdgeStrip(), List.of()),
                new ComponentAssignment<>(HouseContainers.Ground.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Ground(), List.of()),
                new ComponentAssignment<>(HouseContainers.SiteBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.SiteBar(), List.of()),
                new ComponentAssignment<>(HouseControls.Brand.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Brand(), List.of(
                        new RoleAssignment<>(HouseDoing.Open.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Brand()),
                        new RoleAssignment<>(HouseDoing.Home.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Brand()),
                        new RoleAssignment<>(HouseDoing.Preferences.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Brand()),
                        new RoleAssignment<>(HouseDoing.Path.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Brand()),
                        new RoleAssignment<>(HouseDoing.Step.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Brand()),
                        new RoleAssignment<>(HouseDoing.Previous.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Brand()),
                        new RoleAssignment<>(HouseDoing.Next.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Brand()),
                        new RoleAssignment<>(HouseDoing.First.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Brand()),
                        new RoleAssignment<>(HouseDoing.Last.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Brand()),
                        new RoleAssignment<>(HouseDoing.More.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Brand()))),
                new ComponentAssignment<>(HouseRegions.Quote.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Quote(), List.of()),
                new ComponentAssignment<>(HouseRegions.CodeBlock.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.CodeBlock(), List.of()),
                new ComponentAssignment<>(HouseContainers.FloatingPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.FloatingPane(), List.of()),
                new ComponentAssignment<>(HouseContainers.FloatLayer.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.FloatLayer(), List.of()),
                new ComponentAssignment<>(HouseContainers.ContextMenu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ContextMenu(), List.of()),
                new ComponentAssignment<>(HouseContainers.L1Menu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.L1Menu(), List.of()),
                new ComponentAssignment<>(HouseContainers.L2Menu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.L2Menu(), List.of()),
                new ComponentAssignment<>(HouseContainers.TabStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.TabStrip(), List.of()),
                new ComponentAssignment<>(HouseControls.Tab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.Tab(), List.of()),
                new ComponentAssignment<>(HouseContainers.ActionBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.ActionBar(), List.of()),
                new ComponentAssignment<>(HouseContainers.TitleBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.neofuturism.TitleBar(), List.of()));
    }
}
