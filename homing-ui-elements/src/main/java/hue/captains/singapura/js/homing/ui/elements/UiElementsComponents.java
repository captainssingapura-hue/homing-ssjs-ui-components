package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentEntry;

import java.util.List;

/** The crate's catalogue of components: what it delivers, as components rather than as files. */
public record UiElementsComponents() implements C0_Components<UiElementsComponents> {

    public static final UiElementsComponents INSTANCE = new UiElementsComponents();

    @Override public String name() { return "Elements"; }
    @Override public String summary() { return "The smallest things: the nine buttons, the nine texts, the summary card, a slider and its group, a panel and an edge strip."; }

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
                ComponentEntry.of(this, new HeadingModule.Heading()),
                ComponentEntry.of(this, new KickerModule.Kicker()),
                ComponentEntry.of(this, new CaptionModule.Caption()),
                ComponentEntry.of(this, new LedeModule.Lede()),
                ComponentEntry.of(this, new LabelModule.Label()),
                ComponentEntry.of(this, new ReadoutModule.Readout()),
                ComponentEntry.of(this, new BadgeModule.Badge()),
                ComponentEntry.of(this, new PillModule.Pill()),
                ComponentEntry.of(this, new WordmarkModule.Wordmark()),
                ComponentEntry.of(this, new SummaryCardModule.SummaryCard()),
                ComponentEntry.of(this, new SliderModule.Slider()),
                ComponentEntry.of(this, new SliderGroupModule.SliderGroup()),
                ComponentEntry.of(this, new PanelModule.Panel()),
                ComponentEntry.of(this, new EdgeStripModule.EdgeStrip()));
    }
}
