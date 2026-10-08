package hue.captains.singapura.js.homing.ui.controlpanel;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentEntry;

import java.util.List;

/** The crate's catalogue of components: what it delivers, as components rather than as files. */
public record UiControlPanelComponents() implements C0_Components<UiControlPanelComponents> {

    public static final UiControlPanelComponents INSTANCE = new UiControlPanelComponents();

    @Override public String name() { return "Control panel"; }

    @Override public String summary() { return "Controllability on a page: the controls for a component's options, each made as its means calls for."; }

    @Override public List<ComponentEntry<UiControlPanelComponents>> leaves() {
        return List.of(ComponentEntry.of(this, new ControlPanelModule.ControlPanel()));
    }
}
