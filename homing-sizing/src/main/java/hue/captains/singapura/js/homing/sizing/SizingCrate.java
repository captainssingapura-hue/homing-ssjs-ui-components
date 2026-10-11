package hue.captains.singapura.js.homing.sizing;

import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.server.ServerCrate;

import java.util.List;

/** Sizing's crate: the rail, pure logic; the handle, its behaviour on a boundary; and the handle's styles. */
public final class SizingCrate implements Crate {
    public static final SizingCrate INSTANCE = new SizingCrate();
    private SizingCrate() {}

    @Override public String name() { return "homing-sizing"; }

    @Override public List<Crate> requires() { return List.of(ServerCrate.INSTANCE); }

    @Override public List<CrateEntry> entries() {
        return List.of(
                CrateEntry.of(RailModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                CrateEntry.of(SizingHandleModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                CrateEntry.of(SizingStyles.INSTANCE));
    }
}
