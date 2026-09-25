package hue.captains.singapura.js.homing.ui.docking;

import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.design.DesignCrate;
import hue.captains.singapura.js.homing.server.ServerCrate;
import hue.captains.singapura.js.homing.ui.floating.UiFloatingCrate;
import hue.captains.singapura.js.homing.ui.panes.UiPanesCrate;
import java.util.List;

/**
 * The docking crate: the Desk and its floats (RFC 0066 E3, appendix
 * "tab-panes"), on the runtime, the design targets, the floating crate whose
 * float layer the desk lies its floats on, and the panes crate whose tab-panes
 * the desk owns and whose multi-tab pane is the dock. Both are primitives,
 * owning the DOM on their branches. No styles of its own: the dock's
 * drop-target look is the pane's, a float's frame the layer's.
 */
public final class UiDockingCrate implements Crate {
    public static final UiDockingCrate INSTANCE = new UiDockingCrate();
    private UiDockingCrate() {}

    @Override public String name() { return "homing-ui-docking"; }

    @Override public List<Crate> requires() {
        return List.of(ServerCrate.INSTANCE, DesignCrate.INSTANCE, UiFloatingCrate.INSTANCE, UiPanesCrate.INSTANCE);
    }

    @Override public List<CrateEntry> entries() {
        return List.of(
                CrateEntry.of(DeskModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                CrateEntry.of(FloaterModule.INSTANCE, StandardJsModuleType.PRIMITIVE));
    }
}
