package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.C0_Components;
import hue.captains.singapura.js.homing.component.ComponentVehicle;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.core.StandardJsModuleType;
import hue.captains.singapura.js.homing.design.DesignCrate;
import hue.captains.singapura.js.homing.server.ServerCrate;
import hue.captains.singapura.js.homing.ui.dialog.UiDialogCrate;
import hue.captains.singapura.js.homing.ui.elements.UiElementsCrate;
import hue.captains.singapura.js.homing.ui.floating.UiFloatingCrate;
import hue.captains.singapura.js.homing.ui.icons.UiIconsCrate;
import hue.captains.singapura.js.homing.ui.menu.UiMenuCrate;
import hue.captains.singapura.js.homing.ui.split.UiSplitCrate;
import hue.captains.singapura.js.homing.ui.splitgrid.UiSplitGridCrate;

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
        return List.of(ServerCrate.INSTANCE, DesignCrate.INSTANCE, UiElementsCrate.INSTANCE, UiIconsCrate.INSTANCE,
                UiDialogCrate.INSTANCE, UiMenuCrate.INSTANCE, UiFloatingCrate.INSTANCE, UiSplitCrate.INSTANCE, UiSplitGridCrate.INSTANCE);
    }

    @Override public C0_Components<?> components() { return UiSpecimensComponents.INSTANCE; }

    @Override public List<CrateEntry> entries() {
        return List.of(
                CrateEntry.of(SpecimenStyles.INSTANCE),
                // the elements
                CrateEntry.of(ButtonSpecimenModule.INSTANCE),
                CrateEntry.of(IconSpecimenModule.INSTANCE),
                CrateEntry.of(SummaryCardSpecimenModule.INSTANCE),
                CrateEntry.of(SliderSpecimenModule.INSTANCE),
                CrateEntry.of(SliderGroupSpecimenModule.INSTANCE),
                CrateEntry.of(PanelSpecimenModule.INSTANCE),
                CrateEntry.of(EdgeStripSpecimenModule.INSTANCE),
                // overlays, menus and splits
                CrateEntry.of(DialogSpecimenModule.INSTANCE),
                CrateEntry.of(ContextMenuSpecimenModule.INSTANCE),
                CrateEntry.of(ContextMenuStewardSpecimenModule.INSTANCE),
                CrateEntry.of(FloatingPaneSpecimenModule.INSTANCE),
                CrateEntry.of(FloatLayerSpecimenModule.INSTANCE),
                CrateEntry.of(SplitPaneSpecimenModule.INSTANCE),
                CrateEntry.of(SplitGridSpecimenModule.INSTANCE),
                CrateEntry.of(SplitGridMirrorSpecimenModule.INSTANCE),
                // the registry: generated from HouseSpecimens
                CrateEntry.of(HouseSpecimensModule.INSTANCE, StandardJsModuleType.PURE_LOGIC));
    }
}
