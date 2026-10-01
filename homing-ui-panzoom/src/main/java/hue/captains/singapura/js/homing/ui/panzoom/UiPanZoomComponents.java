package hue.captains.singapura.js.homing.ui.panzoom;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentEntry;

import java.util.List;

/** The crate's catalogue of components: what it delivers, as components rather than as files. */
public record UiPanZoomComponents() implements C0_Components<UiPanZoomComponents> {

    public static final UiPanZoomComponents INSTANCE = new UiPanZoomComponents();

    @Override public String name() { return "Pan and zoom"; }
    @Override public String summary() { return "Content that zooms and pans wherever it is shown, and the bar that drives it."; }

    @Override public List<ComponentEntry<UiPanZoomComponents>> leaves() {
        return List.of(
                ComponentEntry.of(this, new SvgPanZoomModule.SvgPanZoom()),
                ComponentEntry.of(this, new PanZoomBarModule.PanZoomBar()));
    }
}
