package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.design.spec.styles.base.*;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;
import hue.captains.singapura.js.homing.ui.taxonomy.UiElementsComponentModule;

import java.util.List;
import java.util.stream.Stream;

/**
 * UiElementsComponentModule's components in Base, in the typed spec stack (RFC 0067, D53, D54): each component it brings - its own, and
 * the parts that come in with them - and the implementation each of its castings wears, on its own and in each role it
 * is cast in.
 */
public record UiElementsBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiElementsBaseSpec INSTANCE = new UiElementsBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiElementsComponentModule.INSTANCE; }

    @Override public List<ComponentAssignment<BaseScheme, Base, ?>> assignments() {
        return Stream.concat(buttonsAndWords().stream(), theRest().stream()).toList();
    }

    /** The table's first part: the buttons, then the words. */
    private List<ComponentAssignment<BaseScheme, Base, ?>> buttonsAndWords() {
        return List.of(
                new ComponentAssignment<>(HouseControls.PlainButton.INSTANCE, new PlainButton()),
                new ComponentAssignment<>(HouseControls.PrimaryButton.INSTANCE, new PrimaryButton()),
                new ComponentAssignment<>(HouseControls.SecondaryButton.INSTANCE, new SecondaryButton()),
                new ComponentAssignment<>(HouseControls.DangerButton.INSTANCE, new DangerButton()),
                new ComponentAssignment<>(HouseControls.WarningButton.INSTANCE, new WarningButton()),
                new ComponentAssignment<>(HouseControls.SuccessButton.INSTANCE, new SuccessButton()),
                new ComponentAssignment<>(HouseControls.ToggleButton.INSTANCE, new ToggleButton()),
                new ComponentAssignment<>(HouseControls.CloseButton.INSTANCE, new CloseButton()),
                new ComponentAssignment<>(HouseControls.IconButton.INSTANCE, new IconButton()),
                new ComponentAssignment<>(HouseText.Heading1.INSTANCE, new Heading1()),
                new ComponentAssignment<>(HouseText.Heading2.INSTANCE, new Heading2()),
                new ComponentAssignment<>(HouseText.Heading3.INSTANCE, new Heading3()),
                new ComponentAssignment<>(HouseText.Heading4.INSTANCE, new Heading4()),
                new ComponentAssignment<>(HouseText.Heading5.INSTANCE, new Heading5()),
                new ComponentAssignment<>(HouseText.Heading6.INSTANCE, new Heading6()),
                new ComponentAssignment<>(HouseText.Kicker.INSTANCE, new Kicker()),
                new ComponentAssignment<>(HouseText.Caption.INSTANCE, new Caption()),
                new ComponentAssignment<>(HouseText.Lede.INSTANCE, new Lede()),
                new ComponentAssignment<>(HouseText.Label.INSTANCE, new Label()),
                new ComponentAssignment<>(HouseText.TextCell.INSTANCE, new TextCell()),
                new ComponentAssignment<>(HouseText.Readout.INSTANCE, new Readout()),
                new ComponentAssignment<>(HouseText.Badge.INSTANCE, new Badge()),
                new ComponentAssignment<>(HouseText.Pill.INSTANCE, new Pill()),
                new ComponentAssignment<>(HouseText.Code.INSTANCE, new Code()),
                new ComponentAssignment<>(HouseText.SuccessBadge.INSTANCE, new SuccessBadge()),
                new ComponentAssignment<>(HouseText.WarningBadge.INSTANCE, new WarningBadge()),
                new ComponentAssignment<>(HouseText.DangerBadge.INSTANCE, new DangerBadge()),
                new ComponentAssignment<>(HouseText.Wordmark.INSTANCE, new Wordmark()));
    }

    /** The table's second part, in its order. */
    private List<ComponentAssignment<BaseScheme, Base, ?>> theRest() {
        return List.of(
                new ComponentAssignment<>(HouseControls.Link.INSTANCE, new Link()),
                new ComponentAssignment<>(HouseControls.Crumb.INSTANCE, new Crumb()),
                new ComponentAssignment<>(HouseControls.TextBox.INSTANCE, new TextBox()),
                new ComponentAssignment<>(HouseControls.Checkbox.INSTANCE, new Checkbox()),
                new ComponentAssignment<>(HouseControls.Range.INSTANCE, new Range()),
                new ComponentAssignment<>(HouseControls.Select.INSTANCE, new Select()),
                new ComponentAssignment<>(HouseContainers.Scrim.INSTANCE, new Scrim()),
                new ComponentAssignment<>(HouseMarks.Separator.INSTANCE, new Separator()),
                new ComponentAssignment<>(HouseMarks.Delimiter.INSTANCE, new Delimiter()),
                new ComponentAssignment<>(HouseMarks.BrandMark.INSTANCE, new BrandMark()),
                new ComponentAssignment<>(HouseMarks.Lamp.INSTANCE, new Lamp()),
                new ComponentAssignment<>(HouseMarks.DropMark.INSTANCE, new DropMark()),
                new ComponentAssignment<>(HouseMarks.BoundaryLine.INSTANCE, new BoundaryLine()),
                new ComponentAssignment<>(HouseItems.PlainMenuItem.INSTANCE, new PlainMenuItem()),
                new ComponentAssignment<>(HouseItems.CheckMenuItem.INSTANCE, new CheckMenuItem()),
                new ComponentAssignment<>(HouseItems.ListRow.INSTANCE, new ListRow()),
                new ComponentAssignment<>(HouseItems.ChoiceOption.INSTANCE, new ChoiceOption()),
                new ComponentAssignment<>(HouseContainers.PlainContainer.INSTANCE, new PlainContainer()),
                new ComponentAssignment<>(HouseContainers.GridCell.INSTANCE, new GridCell()),
                new ComponentAssignment<>(HouseContainers.PlainCard.INSTANCE, new PlainCard()),
                new ComponentAssignment<>(HouseContainers.ClickableCard.INSTANCE, new ClickableCard()),
                new ComponentAssignment<>(HouseContainers.SummaryView.INSTANCE, new SummaryView()),
                new ComponentAssignment<>(HouseMarks.ToggleGroove.INSTANCE, new ToggleGroove()),
                new ComponentAssignment<>(HouseControls.ToggleKnob.INSTANCE, new ToggleKnob()),
                new ComponentAssignment<>(HouseControls.Grip.INSTANCE, new Grip()),
                new ComponentAssignment<>(HouseControls.Switch.INSTANCE, new Switch()),
                new ComponentAssignment<>(HouseControls.PaneThumb.INSTANCE, new PaneThumb()),
                new ComponentAssignment<>(HouseControls.Slider.INSTANCE, new Slider()),
                new ComponentAssignment<>(HouseContainers.StatusFooter.INSTANCE, new StatusFooter()),
                new ComponentAssignment<>(HouseContainers.ExpandableFooterArea.INSTANCE, new ExpandableFooterArea()),
                new ComponentAssignment<>(HouseContainers.Ground.INSTANCE, new Ground()),
                new ComponentAssignment<>(HouseContainers.SiteBar.INSTANCE, new SiteBar()),
                new ComponentAssignment<>(HouseControls.Brand.INSTANCE, new Brand()),
                new ComponentAssignment<>(HouseContainers.Trail.INSTANCE, new Trail()),
                new ComponentAssignment<>(HouseRegions.Quote.INSTANCE, new Quote()),
                new ComponentAssignment<>(HouseRegions.CodeBlock.INSTANCE, new CodeBlock()),
                new ComponentAssignment<>(HouseRegions.Table.INSTANCE, new Table()),
                new ComponentAssignment<>(HouseText.Strong.INSTANCE, new Strong()),
                new ComponentAssignment<>(HouseText.Muted.INSTANCE, new Muted()),
                new ComponentAssignment<>(HouseMarks.Indicator.INSTANCE, new Indicator()),
                new ComponentAssignment<>(HouseContainers.HeaderCell.INSTANCE, new HeaderCell()),
                new ComponentAssignment<>(HouseContainers.DataCell.INSTANCE, new DataCell()));
    }
}
