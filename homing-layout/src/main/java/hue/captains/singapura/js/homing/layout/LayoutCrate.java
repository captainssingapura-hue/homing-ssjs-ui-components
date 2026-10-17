package hue.captains.singapura.js.homing.layout;

import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;

import java.util.List;

/** The layout algebra's crate: one module of pure logic, requiring nothing. */
public final class LayoutCrate implements Crate {
    public static final LayoutCrate INSTANCE = new LayoutCrate();
    private LayoutCrate() {}

    @Override public String name() { return "homing-layout"; }

    @Override public List<Crate> requires() { return List.of(); }

    @Override public List<CrateEntry> entries() {
        return List.of(CrateEntry.of(LayoutModule.INSTANCE, StandardJsModuleType.PURE_LOGIC));
    }
}
