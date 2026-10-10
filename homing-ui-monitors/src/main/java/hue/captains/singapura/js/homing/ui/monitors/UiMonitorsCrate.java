package hue.captains.singapura.js.homing.ui.monitors;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentVehicle;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.design.DesignCrate;
import hue.captains.singapura.js.homing.server.ServerCrate;

import java.util.List;

/**
 * The monitors crate: the monitor that shows the page's logical-focus tree and
 * who holds the keys, the one that shows the steward's activeness, and the
 * styles they wear. Tooling for a page, not components that take keys: they
 * read the focus party and the steward.
 */
public final class UiMonitorsCrate implements Crate, ComponentVehicle {
    public static final UiMonitorsCrate INSTANCE = new UiMonitorsCrate();
    private UiMonitorsCrate() {}

    @Override public String name() { return "homing-ui-monitors"; }

    @Override public List<Crate> requires() {
        return List.of(ServerCrate.INSTANCE, DesignCrate.INSTANCE);
    }

    @Override public C0_Components<?> components() { return UiMonitorsComponents.INSTANCE; }

    @Override public List<CrateEntry> entries() {
        return List.of(
                CrateEntry.of(FocusMonitorModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                CrateEntry.of(StewardMonitorModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                CrateEntry.of(FocusStyles.INSTANCE));
    }
}
