package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentEntry;

import java.util.List;

/** The crate's catalogue of components: what it delivers, as components rather than as files. */
public record UiPanesComponents() implements C0_Components<UiPanesComponents> {

    public static final UiPanesComponents INSTANCE = new UiPanesComponents();

    @Override public String name() { return "Panes"; }
    @Override public String summary() { return "Tabs over panels, one tab in transit, the strip of chips on its own, and picking a pane off a small map of them."; }

    @Override public List<ComponentEntry<UiPanesComponents>> leaves() {
        return List.of(
                new ComponentEntry.OfComponent<>(this, new MultiTabPaneModule.MultiTabPane()),
                new ComponentEntry.OfComponent<>(this, new SingleTabPaneModule.SingleTabPane()),
                new ComponentEntry.OfComponent<>(this, new TabModule.Tab()),
                new ComponentEntry.OfComponent<>(this, new TabStripModule.TabStrip()),
                new ComponentEntry.OfComponent<>(this, new FloatingPaneModule.FloatingPane()),
                new ComponentEntry.OfComponent<>(this, new TitleBarModule.TitleBar()),
                new ComponentEntry.OfComponent<>(this, new TabPaneModule.TabPane()),
                new ComponentEntry.OfComponent<>(this, new TabOpenerModule.TabOpener()),
                new ComponentEntry.OfComponent<>(this, new TabPickerModule.TabPicker()));
    }
}
