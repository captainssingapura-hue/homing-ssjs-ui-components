package hue.captains.singapura.js.homing.ui.controlpanel;

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
 * The control panel's crate: the house's controllability as a page has it - the generated
 * catalogue and the helper that asks it - and the control panel, which makes each option's control.
 */
public final class UiControlPanelCrate implements Crate, ComponentVehicle {

    public static final UiControlPanelCrate INSTANCE = new UiControlPanelCrate();

    private UiControlPanelCrate() {}

    @Override public String name() { return "homing-ui-control-panel"; }

    @Override public List<Crate> requires() { return List.of(ServerCrate.INSTANCE, DesignCrate.INSTANCE, UiElementsCrate.INSTANCE); }

    @Override public C0_Components<?> components() { return UiControlPanelComponents.INSTANCE; }

    @Override public List<CrateEntry> entries() {
        return List.of(
                CrateEntry.of(ControlCatalogueModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                CrateEntry.of(ControlsModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                CrateEntry.of(ControlPanelModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                CrateEntry.of(ControlPanelStyles.INSTANCE));
    }
}
