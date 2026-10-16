package hue.captains.singapura.js.homing.components.conformance;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModules;
import hue.captains.singapura.js.homing.component.taxonomy.Impl;
import hue.captains.singapura.js.homing.component.taxonomy.Taxon;
import hue.captains.singapura.js.homing.component.taxonomy.Taxonomy;
import hue.captains.singapura.js.homing.core.Crate;
import hue.captains.singapura.js.homing.core.CrateEntry;
import hue.captains.singapura.js.homing.ui.taxonomy.UiPreferencesComponentModule;
import hue.captains.singapura.js.homing.preferences.UiPreferencesCrate;
import hue.captains.singapura.js.homing.ui.taxonomy.MpaComponentModule;
import hue.captains.singapura.js.homing.site.mpa.MpaCrate;
import hue.captains.singapura.js.homing.ui.taxonomy.UiControlPanelComponentModule;
import hue.captains.singapura.js.homing.ui.controlpanel.UiControlPanelCrate;
import hue.captains.singapura.js.homing.ui.taxonomy.UiDialogComponentModule;
import hue.captains.singapura.js.homing.ui.dialog.UiDialogCrate;
import hue.captains.singapura.js.homing.ui.taxonomy.UiElementsComponentModule;
import hue.captains.singapura.js.homing.ui.elements.UiElementsCrate;
import hue.captains.singapura.js.homing.ui.taxonomy.UiFloatingComponentModule;
import hue.captains.singapura.js.homing.ui.floating.UiFloatingCrate;
import hue.captains.singapura.js.homing.ui.taxonomy.UiIconsComponentModule;
import hue.captains.singapura.js.homing.ui.icons.UiIconsCrate;
import hue.captains.singapura.js.homing.ui.taxonomy.UiMenuComponentModule;
import hue.captains.singapura.js.homing.ui.menu.UiMenuCrate;
import hue.captains.singapura.js.homing.ui.taxonomy.UiMonitorsComponentModule;
import hue.captains.singapura.js.homing.ui.monitors.UiMonitorsCrate;
import hue.captains.singapura.js.homing.ui.taxonomy.UiPanesComponentModule;
import hue.captains.singapura.js.homing.ui.panes.UiPanesCrate;
import hue.captains.singapura.js.homing.ui.taxonomy.UiPanZoomComponentModule;
import hue.captains.singapura.js.homing.ui.panzoom.UiPanZoomCrate;
import hue.captains.singapura.js.homing.ui.taxonomy.UiSplitGridComponentModule;
import hue.captains.singapura.js.homing.ui.splitgrid.UiSplitGridCrate;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house's components taxonomy read from its jars' component modules (the components taxonomy by module): a flat
 * list of twelve, registered explicitly, reads the house whole - each jar's module listing every component its crate
 * implements, the rest coming in as parts. There is no other list of the house's components (D51).
 */
class HouseComponentModulesTest {

    /** Each house jar's crate, and its component module. */
    private static final Map<Crate, ComponentModule> JARS = jars();

    private static Map<Crate, ComponentModule> jars() {
        var out = new LinkedHashMap<Crate, ComponentModule>();
        out.put(UiElementsCrate.INSTANCE, UiElementsComponentModule.INSTANCE);
        out.put(UiIconsCrate.INSTANCE, UiIconsComponentModule.INSTANCE);
        out.put(UiDialogCrate.INSTANCE, UiDialogComponentModule.INSTANCE);
        out.put(UiFloatingCrate.INSTANCE, UiFloatingComponentModule.INSTANCE);
        out.put(UiMenuCrate.INSTANCE, UiMenuComponentModule.INSTANCE);
        out.put(UiMonitorsCrate.INSTANCE, UiMonitorsComponentModule.INSTANCE);
        out.put(UiPanesCrate.INSTANCE, UiPanesComponentModule.INSTANCE);
        out.put(UiPanZoomCrate.INSTANCE, UiPanZoomComponentModule.INSTANCE);
        out.put(UiSplitGridCrate.INSTANCE, UiSplitGridComponentModule.INSTANCE);
        out.put(UiPreferencesCrate.INSTANCE, UiPreferencesComponentModule.INSTANCE);
        out.put(MpaCrate.INSTANCE, MpaComponentModule.INSTANCE);
        out.put(UiControlPanelCrate.INSTANCE, UiControlPanelComponentModule.INSTANCE);
        return out;
    }

    /** The flat list an instance of the whole house registers at bootstrap: the taxonomy's, each jar's in its turn. */
    private static final List<ComponentModule> HOUSE = HouseTaxonomy.MODULES;

    @Test
    void theHousesList_isEachJarsModule_inTheJarsOrder() {
        assertEquals(List.copyOf(JARS.values()), HOUSE, "each module defined in the taxonomy, one for each jar that implements it");
    }

    @Test
    void theTwelveModules_readTheHouseWhole() {
        Taxonomy byModules = HouseTaxonomy.INSTANCE.read(HOUSE);
        int listed = HOUSE.stream().mapToInt(m -> m.components().size()).sum();
        assertEquals(116, byModules.components().size());
        assertEquals(105, listed, "a hundred and five listed - ninety-seven implemented, eight realized inline by their owners");
        var bringers = ComponentModules.INSTANCE.bringers(HOUSE, byModules);
        assertEquals(116, bringers.size(), "every component brought by a module: its own, or its owner's by a part");
        assertEquals(UiElementsComponentModule.INSTANCE, bringers.get(hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers.HeaderCell.INSTANCE),
                "a header cell, never listed: a part of the table ui-elements lists");
    }

    @Test
    void eachJarsModule_listsEveryComponentItsCrateImplements() {
        for (var jar : JARS.entrySet()) {
            Set<Component<?>> implemented = new HashSet<>();
            for (CrateEntry e : jar.getKey().entries())
                if (e.module() instanceof Impl impl) {
                    Taxon anchor = impl.anchor();
                    if (anchor instanceof Component<?> c) implemented.add(c);
                }
            List<? extends Component<?>> listed = jar.getValue().components();
            for (Component<?> c : implemented)
                assertTrue(listed.contains(c), jar.getKey().name() + " implements " + c.token() + ", and its module does not list it");
        }
    }
}
