package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.server.ServerCrate;

import java.util.List;

/**
 * The look's crate - the design's, with no component and no behaviour: {@code Look}, which names a leaf's
 * classes for a casting and puts them on; what it wears by, generated from the taxonomy and the styles - the
 * castings ({@code ROLE}, {@code CASTINGS}), the trees of states and events ({@code STATE}, {@code EVENT}), the
 * look's manifest ({@code LOOK_TARGETS}, {@code TREATED}) and its derived sheet; and the icons a page may show
 * ({@code ICON}, each a leaf of the Icon branch, its picture its look - D52). A component's crate requires it for
 * its look; so does any code that dresses an element of its own - a grid's cells - and takes nothing else.
 */
public final class UiLookCrate implements Crate {

    public static final UiLookCrate INSTANCE = new UiLookCrate();

    private UiLookCrate() {}

    @Override public String name() { return "homing-ui-look"; }

    @Override public List<Crate> requires() {
        return List.of(ServerCrate.INSTANCE);
    }

    @Override public List<CrateEntry> entries() {
        return List.of(
                CrateEntry.of(CastingsModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                CrateEntry.of(StatesAndEventsModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                CrateEntry.of(HouseStyleSheet.INSTANCE),
                CrateEntry.of(HousePaletteSheet.INSTANCE),
                CrateEntry.of(LookManifestModule.INSTANCE, StandardJsModuleType.PURE_LOGIC),
                CrateEntry.of(IconsModule.INSTANCE),
                CrateEntry.of(LookModule.INSTANCE));
    }
}
