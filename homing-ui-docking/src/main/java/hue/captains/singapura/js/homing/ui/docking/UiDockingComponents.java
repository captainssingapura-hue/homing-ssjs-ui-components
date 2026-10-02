package hue.captains.singapura.js.homing.ui.docking;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentEntry;

import java.util.List;

/** The crate's catalogue of components: what it delivers, as components rather than as files. The desk and the floater are statics a page holds, not components. */
public record UiDockingComponents() implements C0_Components<UiDockingComponents> {

    public static final UiDockingComponents INSTANCE = new UiDockingComponents();

    @Override public String name() { return "Docking"; }
    @Override public String summary() { return "Docks in a split grid over a desk: regions parted, merged and closed from their tab bars."; }

    @Override public List<ComponentEntry<UiDockingComponents>> leaves() {
        return List.of(ComponentEntry.of(this, new DockGridModule.DockGrid()));
    }
}
