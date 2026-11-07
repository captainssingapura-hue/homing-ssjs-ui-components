package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentEntry;

import java.util.List;

/** The crate's catalogue of components: what it delivers, as components rather than as files. */
public record UiElementsComponents() implements C0_Components<UiElementsComponents> {

    public static final UiElementsComponents INSTANCE = new UiElementsComponents();

    @Override public String name() { return "Elements"; }
    @Override public String summary() { return "The smallest things: the nine buttons, the nine texts, a link, a crumb, a scrim, a separator, a delimiter, the summary card, a slider and its group, a panel and an edge strip."; }

    @Override public List<ComponentEntry<UiElementsComponents>> leaves() {
        return List.of(
                new ComponentEntry.OfComponent<>(this, new PlainButtonModule.PlainButton()),
                new ComponentEntry.OfComponent<>(this, new PrimaryButtonModule.PrimaryButton()),
                new ComponentEntry.OfComponent<>(this, new SecondaryButtonModule.SecondaryButton()),
                new ComponentEntry.OfComponent<>(this, new DangerButtonModule.DangerButton()),
                new ComponentEntry.OfComponent<>(this, new WarningButtonModule.WarningButton()),
                new ComponentEntry.OfComponent<>(this, new SuccessButtonModule.SuccessButton()),
                new ComponentEntry.OfComponent<>(this, new ToggleButtonModule.ToggleButton()),
                new ComponentEntry.OfComponent<>(this, new CloseButtonModule.CloseButton()),
                new ComponentEntry.OfComponent<>(this, new IconButtonModule.IconButton()),
                new ComponentEntry.OfComponent<>(this, new Heading1Module.Heading1()),
                new ComponentEntry.OfComponent<>(this, new Heading2Module.Heading2()),
                new ComponentEntry.OfComponent<>(this, new Heading3Module.Heading3()),
                new ComponentEntry.OfComponent<>(this, new Heading4Module.Heading4()),
                new ComponentEntry.OfComponent<>(this, new Heading5Module.Heading5()),
                new ComponentEntry.OfComponent<>(this, new Heading6Module.Heading6()),
                new ComponentEntry.OfComponent<>(this, new KickerModule.Kicker()),
                new ComponentEntry.OfComponent<>(this, new CaptionModule.Caption()),
                new ComponentEntry.OfComponent<>(this, new LedeModule.Lede()),
                new ComponentEntry.OfComponent<>(this, new LabelModule.Label()),
                new ComponentEntry.OfComponent<>(this, new TextCellModule.TextCell()),
                new ComponentEntry.OfComponent<>(this, new ReadoutModule.Readout()),
                new ComponentEntry.OfComponent<>(this, new BadgeModule.Badge()),
                new ComponentEntry.OfComponent<>(this, new PillModule.Pill()),
                new ComponentEntry.OfComponent<>(this, new CodeModule.Code()),
                new ComponentEntry.OfComponent<>(this, new SuccessBadgeModule.SuccessBadge()),
                new ComponentEntry.OfComponent<>(this, new WarningBadgeModule.WarningBadge()),
                new ComponentEntry.OfComponent<>(this, new DangerBadgeModule.DangerBadge()),
                new ComponentEntry.OfComponent<>(this, new WordmarkModule.Wordmark()),
                new ComponentEntry.OfComponent<>(this, new TextBoxModule.TextBox()),
                new ComponentEntry.OfComponent<>(this, new CheckboxModule.Checkbox()),
                new ComponentEntry.OfComponent<>(this, new RangeModule.Range()),
                new ComponentEntry.OfComponent<>(this, new SelectModule.Select()),
                new ComponentEntry.OfComponent<>(this, new LinkModule.Link()),
                new ComponentEntry.OfComponent<>(this, new CrumbModule.Crumb()),
                new ComponentEntry.OfComponent<>(this, new ScrimModule.Scrim()),
                new ComponentEntry.OfComponent<>(this, new SeparatorModule.Separator()),
                new ComponentEntry.OfComponent<>(this, new DelimiterModule.Delimiter()),
                new ComponentEntry.OfComponent<>(this, new BrandMarkModule.BrandMark()),
                new ComponentEntry.OfComponent<>(this, new LampModule.Lamp()),
                new ComponentEntry.OfComponent<>(this, new DropMarkModule.DropMark()),
                new ComponentEntry.OfComponent<>(this, new BoundaryLineModule.BoundaryLine()),
                new ComponentEntry.OfComponent<>(this, new PlainMenuItemModule.PlainMenuItem()),
                new ComponentEntry.OfComponent<>(this, new CheckMenuItemModule.CheckMenuItem()),
                new ComponentEntry.OfComponent<>(this, new ListRowModule.ListRow()),
                new ComponentEntry.OfComponent<>(this, new ChoiceOptionModule.ChoiceOption()),
                new ComponentEntry.OfComponent<>(this, new PlainCardModule.PlainCard()),
                new ComponentEntry.OfComponent<>(this, new ClickableCardModule.ClickableCard()),
                new ComponentEntry.OfComponent<>(this, new SummaryViewModule.SummaryView()),
                new ComponentEntry.OfComponent<>(this, new ToggleGrooveModule.ToggleGroove()),
                new ComponentEntry.OfComponent<>(this, new ToggleKnobModule.ToggleKnob()),
                new ComponentEntry.OfComponent<>(this, new GripModule.Grip()),
                new ComponentEntry.OfComponent<>(this, new SwitchModule.Switch()),
                new ComponentEntry.OfComponent<>(this, new PaneThumbModule.PaneThumb()),
                new ComponentEntry.OfComponent<>(this, new SliderModule.Slider()),
                new ComponentEntry.OfComponent<>(this, new StatusFooterModule.StatusFooter()),
                new ComponentEntry.OfComponent<>(this, new ExpandableFooterAreaModule.ExpandableFooterArea()),
                new ComponentEntry.OfComponent<>(this, new SiteBarModule.SiteBar()),
                new ComponentEntry.OfComponent<>(this, new BrandModule.Brand()),
                new ComponentEntry.OfComponent<>(this, new TrailModule.Trail()));
    }
}
