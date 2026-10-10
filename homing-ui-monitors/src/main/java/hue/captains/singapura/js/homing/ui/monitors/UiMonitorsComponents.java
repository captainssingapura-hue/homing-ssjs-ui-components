package hue.captains.singapura.js.homing.ui.monitors;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentEntry;

import java.util.List;

/** The crate's catalogue of components: what it delivers, as components rather than as files. */
public record UiMonitorsComponents() implements C0_Components<UiMonitorsComponents> {

    public static final UiMonitorsComponents INSTANCE = new UiMonitorsComponents();

    @Override public String name() { return "Monitors"; }
    @Override public String summary() { return "The logical-focus tree on view, the holder of the keys marked; the steward's activeness, and where each key went."; }

    @Override public List<ComponentEntry<UiMonitorsComponents>> leaves() {
        return List.of(
                ComponentEntry.of(this, new FocusMonitorModule.FocusMonitor()),
                ComponentEntry.of(this, new StewardMonitorModule.StewardMonitor()));
    }
}
