package hue.captains.singapura.js.homing.ui.icons;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentVehicle;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.server.ServerCrate;

import java.util.List;

/**
 * The icons' crate: the {@code Icon} element component, the table of the
 * house's words generated from its lexicon, the mark's box, and the sheet each
 * style writes the words in. A component that shows a mark requires this crate
 * and asks for a word; every house style writes every word.
 */
public final class UiIconsCrate implements Crate, ComponentVehicle {
    public static final UiIconsCrate INSTANCE = new UiIconsCrate();
    private UiIconsCrate() {}

    @Override public String name() { return "homing-ui-icons"; }

    @Override public List<Crate> requires() {
        return List.of(ServerCrate.INSTANCE);
    }

    @Override public C0_Components<?> components() { return UiIconsComponents.INSTANCE; }

    @Override public List<CrateEntry> entries() {
        return List.of(
                CrateEntry.of(IconModule.INSTANCE, StandardJsModuleType.PRIMITIVE),
                CrateEntry.of(IconsModule.INSTANCE),
                CrateEntry.of(IconStyles.INSTANCE),
                CrateEntry.of(LexiconSheet.INSTANCE));
    }
}
