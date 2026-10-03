package hue.captains.singapura.js.homing.ui.docking;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentVehicle;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.design.DesignCrate;
import hue.captains.singapura.js.homing.server.ServerCrate;
import hue.captains.singapura.js.homing.ui.floating.UiFloatingCrate;
import hue.captains.singapura.js.homing.ui.panes.UiPanesCrate;
import hue.captains.singapura.js.homing.ui.splitgrid.UiSplitGridCrate;
import java.util.List;

/**
 * The docking crate: the Desk and its floats (RFC 0066 E3, appendix
 * "tab-panes"), on the runtime, the design targets, the floating crate whose
 * float layer the desk lies its floats on, and the panes crate whose tab-panes
 * the desk owns and whose multi-tab pane is the dock; and the DockGrid, the
 * desk's docks in a split grid, on the split grid crate - the one component
 * it catalogues, since the desk and the floater are statics a page holds.
 * All three are primitives, owning the DOM on their branches. No styles of
 * its own: the dock's drop-target look is the pane's, a float's frame the
 * layer's, the lines the grid's.
 */
public final class UiDockingCrate implements Crate, ComponentVehicle {
    public static final UiDockingCrate INSTANCE = new UiDockingCrate();
    private UiDockingCrate() {}

    @Override public String name() { return "homing-ui-docking"; }

    @Override public List<Crate> requires() {
        return List.of(ServerCrate.INSTANCE, DesignCrate.INSTANCE, UiFloatingCrate.INSTANCE, UiPanesCrate.INSTANCE, UiSplitGridCrate.INSTANCE);
    }

    @Override public C0_Components<?> components() { return UiDockingComponents.INSTANCE; }

    @Override public List<CrateEntry> entries() {
        return List.of(
                CrateEntry.of(DeskModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                CrateEntry.of(FloaterModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                CrateEntry.of(DeskHandModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                CrateEntry.of(DockGridModule.INSTANCE, StandardJsModuleType.PRIMITIVE));
    }
}
