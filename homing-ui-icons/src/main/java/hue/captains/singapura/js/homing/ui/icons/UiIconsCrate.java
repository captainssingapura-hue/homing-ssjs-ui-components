package hue.captains.singapura.js.homing.ui.icons;

import hue.captains.singapura.js.homing.core.js.CoreJsCrate;
import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentVehicle;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.server.ServerCrate;
import hue.captains.singapura.js.homing.ui.look.UiLookCrate;

import java.util.List;

/**
 * The icons' crate: the {@code Icon} element component, which realizes every
 * leaf of the Icon branch, its box, and the room an owner mounts its icons in
 * one at a time (D52). Which icons there are is the instance's ({@code ICON},
 * the look's); each one's picture is its look, the style worn's.
 */
public final class UiIconsCrate implements Crate, ComponentVehicle {
    public static final UiIconsCrate INSTANCE = new UiIconsCrate();
    private UiIconsCrate() {}

    @Override public String name() { return "homing-ui-icons"; }

    @Override public List<Crate> requires() {
        return List.of(ServerCrate.INSTANCE, CoreJsCrate.INSTANCE, UiLookCrate.INSTANCE);
    }

    @Override public C0_Components<?> components() { return UiIconsComponents.INSTANCE; }

    @Override public List<CrateEntry> entries() {
        return List.of(
                CrateEntry.of(IconModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                CrateEntry.of(IconRoomModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                CrateEntry.of(IconStyles.INSTANCE));
    }
}
