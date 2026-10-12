package hue.captains.singapura.js.homing.ui.icons;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentVehicle;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.server.ServerCrate;
import hue.captains.singapura.js.homing.ui.look.UiLookCrate;

import java.util.List;

/**
 * The icons' crate: the {@code Icon} element component and the mark's box. The
 * words it shows are the lexicon's - their table and the sheet each style writes
 * them in are the look's ({@code UiLookCrate}), the design's, which a component
 * that shows a word on a mark of its own requires instead; every house style
 * writes every word.
 */
public final class UiIconsCrate implements Crate, ComponentVehicle {
    public static final UiIconsCrate INSTANCE = new UiIconsCrate();
    private UiIconsCrate() {}

    @Override public String name() { return "homing-ui-icons"; }

    @Override public List<Crate> requires() {
        return List.of(ServerCrate.INSTANCE, UiLookCrate.INSTANCE);
    }

    @Override public C0_Components<?> components() { return UiIconsComponents.INSTANCE; }

    @Override public List<CrateEntry> entries() {
        return List.of(
                CrateEntry.of(IconModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                CrateEntry.of(IconStyles.INSTANCE));
    }
}
