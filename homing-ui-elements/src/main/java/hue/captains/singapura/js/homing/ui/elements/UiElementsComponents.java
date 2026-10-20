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
                ComponentEntry.of(this, new PlainButtonModule.PlainButton()),
                ComponentEntry.of(this, new PrimaryButtonModule.PrimaryButton()),
                ComponentEntry.of(this, new SecondaryButtonModule.SecondaryButton()),
                ComponentEntry.of(this, new DangerButtonModule.DangerButton()),
                ComponentEntry.of(this, new WarningButtonModule.WarningButton()),
                ComponentEntry.of(this, new SuccessButtonModule.SuccessButton()),
                ComponentEntry.of(this, new ToggleButtonModule.ToggleButton()),
                ComponentEntry.of(this, new CloseButtonModule.CloseButton()),
                ComponentEntry.of(this, new IconButtonModule.IconButton()),
                ComponentEntry.of(this, new Heading1Module.Heading1()),
                ComponentEntry.of(this, new Heading2Module.Heading2()),
                ComponentEntry.of(this, new Heading3Module.Heading3()),
                ComponentEntry.of(this, new Heading4Module.Heading4()),
                ComponentEntry.of(this, new Heading5Module.Heading5()),
                ComponentEntry.of(this, new Heading6Module.Heading6()),
                ComponentEntry.of(this, new KickerModule.Kicker()),
                ComponentEntry.of(this, new CaptionModule.Caption()),
                ComponentEntry.of(this, new LedeModule.Lede()),
                ComponentEntry.of(this, new LabelModule.Label()),
                ComponentEntry.of(this, new TextCellModule.TextCell()),
                ComponentEntry.of(this, new ReadoutModule.Readout()),
                ComponentEntry.of(this, new BadgeModule.Badge()),
                ComponentEntry.of(this, new PillModule.Pill()),
                ComponentEntry.of(this, new CodeModule.Code()),
                ComponentEntry.of(this, new SuccessBadgeModule.SuccessBadge()),
                ComponentEntry.of(this, new WarningBadgeModule.WarningBadge()),
                ComponentEntry.of(this, new DangerBadgeModule.DangerBadge()),
                ComponentEntry.of(this, new WordmarkModule.Wordmark()),
                ComponentEntry.of(this, new TextBoxModule.TextBox()),
                ComponentEntry.of(this, new CheckboxModule.Checkbox()),
                ComponentEntry.of(this, new RangeModule.Range()),
                ComponentEntry.of(this, new SelectModule.Select()),
                ComponentEntry.of(this, new LinkModule.Link()),
                ComponentEntry.of(this, new CrumbModule.Crumb()),
                ComponentEntry.of(this, new ScrimModule.Scrim()),
                ComponentEntry.of(this, new SeparatorModule.Separator()),
                ComponentEntry.of(this, new DelimiterModule.Delimiter()),
                ComponentEntry.of(this, new BrandMarkModule.BrandMark()),
                ComponentEntry.of(this, new LampModule.Lamp()),
                ComponentEntry.of(this, new DropMarkModule.DropMark()),
                ComponentEntry.of(this, new DetentModule.Detent()),
                ComponentEntry.of(this, new TickModule.Tick()),
                ComponentEntry.of(this, new BoundaryLineModule.BoundaryLine()),
                ComponentEntry.of(this, new MenuItemModule.MenuItem()),
                ComponentEntry.of(this, new ListRowModule.ListRow()),
                ComponentEntry.of(this, new ChoiceOptionModule.ChoiceOption()),
                ComponentEntry.of(this, new PlainCardModule.PlainCard()),
                ComponentEntry.of(this, new ClickableCardModule.ClickableCard()),
                ComponentEntry.of(this, new SummaryViewModule.SummaryView()),
                ComponentEntry.of(this, new SliderRailModule.SliderRail()),
                ComponentEntry.of(this, new SliderGrooveModule.SliderGroove()),
                ComponentEntry.of(this, new FillModule.Fill()),
                ComponentEntry.of(this, new SliderKnobModule.SliderKnob()),
                ComponentEntry.of(this, new ToggleGrooveModule.ToggleGroove()),
                ComponentEntry.of(this, new ToggleKnobModule.ToggleKnob()),
                ComponentEntry.of(this, new GripModule.Grip()),
                ComponentEntry.of(this, new SwitchModule.Switch()),
                ComponentEntry.of(this, new PaneThumbModule.PaneThumb()),
                ComponentEntry.of(this, new SliderModule.Slider()),
                ComponentEntry.of(this, new SliderGroupModule.SliderGroup()),
                ComponentEntry.of(this, new PanelModule.Panel()),
                ComponentEntry.of(this, new EdgeStripModule.EdgeStrip()),
                ComponentEntry.of(this, new GroundModule.Ground()),
                ComponentEntry.of(this, new SiteBarModule.SiteBar()),
                ComponentEntry.of(this, new BrandModule.Brand()),
                ComponentEntry.of(this, new PanelBarModule.PanelBar()),
                ComponentEntry.of(this, new TrailModule.Trail()));
    }
}
