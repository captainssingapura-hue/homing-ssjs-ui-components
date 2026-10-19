package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.design.spec.RoleAssignment;
import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/**
 * retro-futurism's assignments, in the typed spec stack (RFC 0067, D53, D54): each house component it draws its own way, and
 * the implementation each of its castings wears - every casting of it; the components it does not assign are Base's,
 * whole.
 */
public record RetroFuturismSpec() implements SpecModule<BaseScheme, RetroFuturism> {

    public static final RetroFuturismSpec INSTANCE = new RetroFuturismSpec();

    @Override public RetroFuturism style() { return RetroFuturism.INSTANCE; }

    @Override public List<ComponentModule> modules() { return HouseTaxonomy.MODULES; }

    @Override public List<ComponentAssignment<BaseScheme, RetroFuturism, ?>> assignments() {
        return List.of(
                new ComponentAssignment<>(HouseControls.PlainButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PlainButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Action.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Cancel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Go.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.ZoomOut.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.ZoomIn.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Fit.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Reset.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PlainButton()),
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PlainButton()))),
                new ComponentAssignment<>(HouseControls.PrimaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PrimaryButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Go.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PrimaryButton()))),
                new ComponentAssignment<>(HouseControls.SecondaryButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SecondaryButton(), List.of()),
                new ComponentAssignment<>(HouseControls.DangerButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.DangerButton(), List.of()),
                new ComponentAssignment<>(HouseControls.WarningButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.WarningButton(), List.of()),
                new ComponentAssignment<>(HouseControls.SuccessButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SuccessButton(), List.of()),
                new ComponentAssignment<>(HouseControls.ToggleButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ToggleButton(), List.of()),
                new ComponentAssignment<>(HouseControls.CloseButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.CloseButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Close.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.CloseButton()))),
                new ComponentAssignment<>(HouseControls.IconButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.IconButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Add.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.IconButton()))),
                new ComponentAssignment<>(HouseText.Heading1.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Heading1(), List.of()),
                new ComponentAssignment<>(HouseText.Heading2.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Heading2(), List.of()),
                new ComponentAssignment<>(HouseText.Heading3.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Heading3(), List.of()),
                new ComponentAssignment<>(HouseText.Heading4.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Heading4(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Heading4()))),
                new ComponentAssignment<>(HouseText.Heading5.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Heading5(), List.of()),
                new ComponentAssignment<>(HouseText.Heading6.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Heading6(), List.of()),
                new ComponentAssignment<>(HouseText.Kicker.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Kicker(), List.of(
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Prompt.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Status.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Kicker()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Kicker()))),
                new ComponentAssignment<>(HouseText.Caption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Caption(), List.of(
                        new RoleAssignment<>(HouseSaying.Hint.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Note.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Summary.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Figure.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Caption()),
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Caption()))),
                new ComponentAssignment<>(HouseText.Lede.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Lede(), List.of(
                        new RoleAssignment<>(HouseSaying.Summary.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Lede()))),
                new ComponentAssignment<>(HouseText.Label.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Label(), List.of(
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Label()),
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.LabelTitle()))),
                new ComponentAssignment<>(HouseText.Readout.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Readout(), List.of(
                        new RoleAssignment<>(HouseSaying.Value.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Readout()))),
                new ComponentAssignment<>(HouseText.Badge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Badge(), List.of(
                        new RoleAssignment<>(HouseSaying.Tag.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Badge()))),
                new ComponentAssignment<>(HouseText.Pill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Pill(), List.of(
                        new RoleAssignment<>(HouseSaying.Count.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Pill()))),
                new ComponentAssignment<>(HouseText.Code.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Code(), List.of()),
                new ComponentAssignment<>(HouseText.SuccessBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SuccessBadge(), List.of()),
                new ComponentAssignment<>(HouseText.WarningBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.WarningBadge(), List.of()),
                new ComponentAssignment<>(HouseText.DangerBadge.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.DangerBadge(), List.of()),
                new ComponentAssignment<>(HouseText.Wordmark.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Wordmark(), List.of(
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Wordmark()))),
                new ComponentAssignment<>(HouseControls.Link.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Link(), List.of(
                        new RoleAssignment<>(HouseDoing.Open.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Link()))),
                new ComponentAssignment<>(HouseControls.Crumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Crumb(), List.of(
                        new RoleAssignment<>(HouseDoing.Step.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Crumb()))),
                new ComponentAssignment<>(HouseControls.TextBox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.TextBox(), List.of()),
                new ComponentAssignment<>(HouseControls.Checkbox.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Checkbox(), List.of()),
                new ComponentAssignment<>(HouseControls.Range.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Range(), List.of(
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Range()))),
                new ComponentAssignment<>(HouseControls.Select.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Select(), List.of(
                        new RoleAssignment<>(HouseDoing.What.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Select()),
                        new RoleAssignment<>(HouseDoing.How.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Select()))),
                new ComponentAssignment<>(HouseContainers.Scrim.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Scrim(), List.of()),
                new ComponentAssignment<>(HouseMarks.Separator.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Separator(), List.of()),
                new ComponentAssignment<>(HouseMarks.Lamp.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Lamp(), List.of()),
                new ComponentAssignment<>(HouseMarks.Detent.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Detent(), List.of(
                        new RoleAssignment<>(HouseSaying.Rest.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Detent()))),
                new ComponentAssignment<>(HouseMarks.Tick.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Tick(), List.of(
                        new RoleAssignment<>(HouseSaying.Notch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Tick()))),
                new ComponentAssignment<>(HouseItems.MenuItem.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.MenuItem(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.MenuItem()))),
                new ComponentAssignment<>(HouseItems.ListRow.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ListRow(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ListRow()))),
                new ComponentAssignment<>(HouseItems.ChoiceOption.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ChoiceOption(), List.of(
                        new RoleAssignment<>(HouseDoing.Choice.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Style.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ChoiceOption()),
                        new RoleAssignment<>(HouseDoing.Palette.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ChoiceOption()))),
                new ComponentAssignment<>(HouseContainers.PlainCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PlainCard(), List.of()),
                new ComponentAssignment<>(HouseContainers.ClickableCard.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ClickableCard(), List.of()),
                new ComponentAssignment<>(HouseMarks.SliderRail.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SliderRail(), List.of()),
                new ComponentAssignment<>(HouseMarks.SliderGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SliderGroove(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SliderGroove()))),
                new ComponentAssignment<>(HouseMarks.Fill.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Fill(), List.of(
                        new RoleAssignment<>(HouseSaying.Level.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Fill()))),
                new ComponentAssignment<>(HouseControls.SliderKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SliderKnob(), List.of(
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SliderKnob()))),
                new ComponentAssignment<>(HouseMarks.ToggleGroove.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ToggleGroove(), List.of(
                        new RoleAssignment<>(HouseSaying.Span.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ToggleGroove()))),
                new ComponentAssignment<>(HouseControls.ToggleKnob.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ToggleKnob(), List.of(
                        new RoleAssignment<>(HouseDoing.Thumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ToggleKnob()))),
                new ComponentAssignment<>(HouseControls.Grip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Grip(), List.of()),
                new ComponentAssignment<>(HouseControls.Switch.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Switch(), List.of(
                        new RoleAssignment<>(HouseDoing.Toggle.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Switch()))),
                new ComponentAssignment<>(HouseControls.PaneThumb.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PaneThumb(), List.of()),
                new ComponentAssignment<>(HouseControls.Slider.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Slider(), List.of()),
                new ComponentAssignment<>(HouseContainers.SliderGroup.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SliderGroup(), List.of(
                        new RoleAssignment<>(HouseDoing.Adjust.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SliderGroup()))),
                new ComponentAssignment<>(HouseContainers.Panel.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Panel(), List.of()),
                new ComponentAssignment<>(HouseContainers.EdgeStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.EdgeStrip(), List.of()),
                new ComponentAssignment<>(HouseContainers.Ground.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Ground(), List.of()),
                new ComponentAssignment<>(HouseContainers.SiteBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SiteBar(), List.of()),
                new ComponentAssignment<>(HouseControls.Brand.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Brand(), List.of(
                        new RoleAssignment<>(HouseDoing.Home.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Brand()))),
                new ComponentAssignment<>(HouseRegions.Quote.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Quote(), List.of()),
                new ComponentAssignment<>(HouseRegions.CodeBlock.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.CodeBlock(), List.of()),
                new ComponentAssignment<>(HouseIcons.CloseIcon.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.CloseIcon(), List.of()),
                new ComponentAssignment<>(HouseContainers.FloatingPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.FloatingPane(), List.of()),
                new ComponentAssignment<>(HouseContainers.FloatLayer.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.FloatLayer(), List.of()),
                new ComponentAssignment<>(HouseContainers.ContextMenu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ContextMenu(), List.of()),
                new ComponentAssignment<>(HouseContainers.TabStrip.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.TabStrip(), List.of()),
                new ComponentAssignment<>(HouseControls.Tab.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Tab(), List.of()),
                new ComponentAssignment<>(HouseControls.PreferencesButton.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PreferencesButton(), List.of(
                        new RoleAssignment<>(HouseDoing.Preferences.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PreferencesButton()))),
                new ComponentAssignment<>(HouseRegions.SliderRack.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.SliderRack(), List.of()),
                new ComponentAssignment<>(HouseContainers.PanelBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.PanelBar(), List.of()),
                new ComponentAssignment<>(HouseRegions.Section.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.Section(), List.of()),
                new ComponentAssignment<>(HouseContainers.ActionBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.ActionBar(), List.of()),
                new ComponentAssignment<>(HouseContainers.TitleBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.retrofuturism.TitleBar(), List.of()));
    }
}
