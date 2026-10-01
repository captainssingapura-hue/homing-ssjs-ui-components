package hue.captains.singapura.js.homing.ui.panzoom;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentVehicle;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.design.DesignCrate;
import hue.captains.singapura.js.homing.server.ServerCrate;
import hue.captains.singapura.js.homing.ui.elements.UiElementsCrate;

import java.util.List;

/**
 * Zoom and pan's crate: the arithmetic (pure logic), the SVG view and the bar (primitives, each
 * owning the DOM on its branch), and their sheet; on the runtime, the design targets and the
 * elements, whose buttons the bar is made of.
 */
public final class UiPanZoomCrate implements Crate, ComponentVehicle {

    public static final UiPanZoomCrate INSTANCE = new UiPanZoomCrate();

    private UiPanZoomCrate() {}

    @Override public String name() { return "homing-ui-panzoom"; }

    @Override public List<Crate> requires() {
        return List.of(ServerCrate.INSTANCE, DesignCrate.INSTANCE, UiElementsCrate.INSTANCE);
    }

    @Override public C0_Components<?> components() { return UiPanZoomComponents.INSTANCE; }

    @Override public List<CrateEntry> entries() {
        return List.of(
                CrateEntry.of(PanZoomModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                CrateEntry.of(SvgPanZoomModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                CrateEntry.of(PanZoomBarModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                CrateEntry.of(PanZoomStyles.INSTANCE));
    }
}
