package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;

import java.util.List;

/**
 * homing-ui-elements's component module, defined beside the components it lists (D40, D46): those the jar implements, then
 * those its owners realize inline and it gives its look to - a flat list; their parts come in with them.
 * A definition alone - what a style jar's check needs of the jar - while the jar keeps the implementation.
 */
public record UiElementsComponentModule() implements ComponentModule {

    public static final UiElementsComponentModule INSTANCE = new UiElementsComponentModule();

    @Override public List<? extends Component<?>> components() {
        return List.of(HouseControls.PlainButton.INSTANCE, HouseControls.PrimaryButton.INSTANCE,
                       HouseControls.SecondaryButton.INSTANCE, HouseControls.DangerButton.INSTANCE,
                       HouseControls.WarningButton.INSTANCE, HouseControls.SuccessButton.INSTANCE,
                       HouseControls.ToggleButton.INSTANCE, HouseControls.CloseButton.INSTANCE,
                       HouseControls.IconButton.INSTANCE, HouseText.Heading1.INSTANCE, HouseText.Heading2.INSTANCE,
                       HouseText.Heading3.INSTANCE, HouseText.Heading4.INSTANCE, HouseText.Heading5.INSTANCE,
                       HouseText.Heading6.INSTANCE, HouseText.Kicker.INSTANCE, HouseText.Caption.INSTANCE,
                       HouseText.Lede.INSTANCE, HouseText.Label.INSTANCE, HouseText.TextCell.INSTANCE,
                       HouseText.Readout.INSTANCE, HouseText.Badge.INSTANCE, HouseText.Pill.INSTANCE,
                       HouseText.Code.INSTANCE, HouseText.SuccessBadge.INSTANCE, HouseText.WarningBadge.INSTANCE,
                       HouseText.DangerBadge.INSTANCE, HouseText.Wordmark.INSTANCE, HouseControls.Link.INSTANCE,
                       HouseControls.Crumb.INSTANCE, HouseControls.TextBox.INSTANCE, HouseControls.Checkbox.INSTANCE,
                       HouseControls.Range.INSTANCE, HouseControls.Select.INSTANCE, HouseContainers.Scrim.INSTANCE,
                       HouseMarks.Separator.INSTANCE, HouseMarks.Delimiter.INSTANCE, HouseMarks.BrandMark.INSTANCE,
                       HouseMarks.Lamp.INSTANCE, HouseMarks.DropMark.INSTANCE, HouseMarks.Detent.INSTANCE,
                       HouseMarks.Tick.INSTANCE, HouseMarks.BoundaryLine.INSTANCE, HouseItems.MenuItem.INSTANCE,
                       HouseItems.ListRow.INSTANCE, HouseItems.ChoiceOption.INSTANCE,
                       HouseContainers.PlainCard.INSTANCE, HouseContainers.ClickableCard.INSTANCE,
                       HouseContainers.SummaryView.INSTANCE, HouseMarks.SliderRail.INSTANCE,
                       HouseMarks.SliderGroove.INSTANCE, HouseMarks.Fill.INSTANCE, HouseControls.SliderKnob.INSTANCE,
                       HouseMarks.ToggleGroove.INSTANCE, HouseControls.ToggleKnob.INSTANCE,
                       HouseControls.Grip.INSTANCE, HouseControls.Switch.INSTANCE, HouseControls.PaneThumb.INSTANCE,
                       HouseControls.Slider.INSTANCE, HouseContainers.SliderGroup.INSTANCE,
                       HouseContainers.Panel.INSTANCE, HouseContainers.PanelBar.INSTANCE, HouseContainers.EdgeStrip.INSTANCE,
                       HouseContainers.Ground.INSTANCE, HouseContainers.SiteBar.INSTANCE, HouseControls.Brand.INSTANCE,
                       HouseContainers.Trail.INSTANCE, HouseRegions.Quote.INSTANCE, HouseRegions.CodeBlock.INSTANCE,
                       HouseRegions.Table.INSTANCE, HouseText.Strong.INSTANCE, HouseText.Muted.INSTANCE,
                       HouseMarks.Indicator.INSTANCE);
    }
}
