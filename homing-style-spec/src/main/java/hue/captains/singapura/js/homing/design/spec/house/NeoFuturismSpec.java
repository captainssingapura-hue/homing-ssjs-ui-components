package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.design.spec.RoleAssignment;
import hue.captains.singapura.js.homing.design.spec.SpecModule;
import hue.captains.singapura.js.homing.design.spec.styles.neofuturism.*;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseRegions;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;
import java.util.stream.Stream;

/**
 * neo-futurism's assignments, in the typed spec stack (RFC 0067, D53, D54): each house component it draws its own way, and
 * the implementation each of its castings wears - every casting of it; the components it does not assign are Base's,
 * whole.
 */
public record NeoFuturismSpec() implements SpecModule<BaseScheme, NeoFuturism> {

    public static final NeoFuturismSpec INSTANCE = new NeoFuturismSpec();

    @Override public NeoFuturism style() { return NeoFuturism.INSTANCE; }

    @Override public List<ComponentModule> modules() { return HouseTaxonomy.INSTANCE.modules(); }

    @Override public List<ComponentAssignment<BaseScheme, NeoFuturism, ?>> assignments() {
        return Stream.concat(buttonsAndWords().stream(), theRest().stream()).toList();
    }

    /** The table's first part: the buttons, then the words. */
    private List<ComponentAssignment<BaseScheme, NeoFuturism, ?>> buttonsAndWords() {
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
                new ComponentAssignment<>(HouseText.Label.INSTANCE, new Label(), List.of(
                        new RoleAssignment<>(HouseSaying.Title.INSTANCE, new LabelTitle()),
                        new RoleAssignment<>(HouseSaying.Subtitle.INSTANCE, new Label()),
                        new RoleAssignment<>(HouseSaying.Name.INSTANCE, new Label()),
                        new RoleAssignment<>(HouseSaying.Category.INSTANCE, new Label()),
                        new RoleAssignment<>(HouseSaying.Symbol.INSTANCE, new Label()))),
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
    private List<ComponentAssignment<BaseScheme, NeoFuturism, ?>> theRest() {
        return List.of(
                new ComponentAssignment<>(HouseControls.Link.INSTANCE, new Link()),
                new ComponentAssignment<>(HouseControls.Crumb.INSTANCE, new Crumb()),
                new ComponentAssignment<>(HouseControls.TextBox.INSTANCE, new TextBox()),
                new ComponentAssignment<>(HouseControls.Checkbox.INSTANCE, new Checkbox()),
                new ComponentAssignment<>(HouseControls.Range.INSTANCE, new Range()),
                new ComponentAssignment<>(HouseControls.Select.INSTANCE, new Select()),
                new ComponentAssignment<>(HouseContainers.Scrim.INSTANCE, new Scrim()),
                new ComponentAssignment<>(HouseMarks.Separator.INSTANCE, new Separator()),
                new ComponentAssignment<>(HouseMarks.Lamp.INSTANCE, new Lamp()),
                new ComponentAssignment<>(HouseItems.PlainMenuItem.INSTANCE, new PlainMenuItem()),
                new ComponentAssignment<>(HouseItems.CheckMenuItem.INSTANCE, new CheckMenuItem()),
                new ComponentAssignment<>(HouseItems.ListRow.INSTANCE, new ListRow()),
                new ComponentAssignment<>(HouseItems.ChoiceOption.INSTANCE, new ChoiceOption()),
                new ComponentAssignment<>(HouseContainers.PlainCard.INSTANCE, new PlainCard()),
                new ComponentAssignment<>(HouseContainers.ClickableCard.INSTANCE, new ClickableCard()),
                new ComponentAssignment<>(HouseMarks.ToggleGroove.INSTANCE, new ToggleGroove()),
                new ComponentAssignment<>(HouseControls.ToggleKnob.INSTANCE, new ToggleKnob()),
                new ComponentAssignment<>(HouseControls.Grip.INSTANCE, new Grip()),
                new ComponentAssignment<>(HouseControls.Switch.INSTANCE, new Switch()),
                new ComponentAssignment<>(HouseControls.PaneThumb.INSTANCE, new PaneThumb()),
                new ComponentAssignment<>(HouseControls.Slider.INSTANCE, new Slider()),
                new ComponentAssignment<>(HouseContainers.StatusFooter.INSTANCE, new StatusFooter()),
                new ComponentAssignment<>(HouseContainers.Ground.INSTANCE, new Ground()),
                new ComponentAssignment<>(HouseContainers.SiteBar.INSTANCE, new SiteBar()),
                new ComponentAssignment<>(HouseControls.Brand.INSTANCE, new Brand()),
                new ComponentAssignment<>(HouseRegions.Quote.INSTANCE, new Quote()),
                new ComponentAssignment<>(HouseRegions.CodeBlock.INSTANCE, new CodeBlock()),
                new ComponentAssignment<>(HouseContainers.FloatingPane.INSTANCE, new FloatingPane()),
                new ComponentAssignment<>(HouseContainers.FloatLayer.INSTANCE, new FloatLayer()),
                new ComponentAssignment<>(HouseContainers.ContextMenu.INSTANCE, new ContextMenu()),
                new ComponentAssignment<>(HouseContainers.L1Menu.INSTANCE, new L1Menu()),
                new ComponentAssignment<>(HouseContainers.L2Menu.INSTANCE, new L2Menu()),
                new ComponentAssignment<>(HouseContainers.TabStrip.INSTANCE, new TabStrip()),
                new ComponentAssignment<>(HouseControls.Tab.INSTANCE, new Tab()),
                new ComponentAssignment<>(HouseContainers.ActionBar.INSTANCE, new ActionBar()),
                new ComponentAssignment<>(HouseContainers.TitleBar.INSTANCE, new TitleBar()));
    }
}
