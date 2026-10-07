package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentVehicle;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.design.DesignCrate;
import hue.captains.singapura.js.homing.server.ServerCrate;
import hue.captains.singapura.js.homing.ui.elements.UiElementsCrate;
import hue.captains.singapura.js.homing.ui.icons.UiIconsCrate;

import java.util.List;

/**
 * The specimens' crate: a specimen for each realized leaf of the house it can show, their
 * layout, and the registry a page looks them up in - on the components they show.
 */
public final class UiSpecimensCrate implements Crate, ComponentVehicle {

    public static final UiSpecimensCrate INSTANCE = new UiSpecimensCrate();

    private UiSpecimensCrate() {}

    @Override public String name() { return "homing-ui-specimens"; }

    @Override public List<Crate> requires() {
        return List.of(ServerCrate.INSTANCE, DesignCrate.INSTANCE, UiElementsCrate.INSTANCE, UiIconsCrate.INSTANCE);
    }

    @Override public C0_Components<?> components() { return UiSpecimensComponents.INSTANCE; }

    @Override public List<CrateEntry> entries() {
        return List.of(
                CrateEntry.of(SpecimenStyles.INSTANCE),
                CrateEntry.of(ButtonSpecimenModule.INSTANCE),
                CrateEntry.of(IconSpecimenModule.INSTANCE),
                CrateEntry.of(SummaryCardSpecimenModule.INSTANCE),
                CrateEntry.of(SliderSpecimenModule.INSTANCE),
                CrateEntry.of(SliderGroupSpecimenModule.INSTANCE),
                CrateEntry.of(PanelSpecimenModule.INSTANCE),
                CrateEntry.of(EdgeStripSpecimenModule.INSTANCE),
                // the registry: generated from HouseSpecimens
                CrateEntry.of(HouseSpecimensModule.INSTANCE, StandardJsModuleType.PURE_LOGIC));
    }
}
