package hue.captains.singapura.js.homing.ui.menu;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomySource;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;

import java.util.List;

/**
 * The house's keeper of context menus, as this module adds it to the house's taxonomy. The
 * context menu itself is common, and the base tree's. In the tree if and only if this module is
 * on the classpath.
 */
public record HouseMenus() implements TaxonomyExtension {

    public static final HouseMenus INSTANCE = new HouseMenus();

    @Override public Class<? extends TaxonomySource> extending() { return HouseTaxonomy.class; }

    /** Every component this module declares. */
    @Override public List<Component<?>> components() {
        return List.of(ContextMenuSteward.INSTANCE);
    }

    /** The one keeper of the page's context menus: one open at a time, and a specimen of each kind. Its layer is its own. */
    public record ContextMenuSteward() implements Component<HouseBranches.Layer> {
        public static final ContextMenuSteward INSTANCE = new ContextMenuSteward();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Layer parent() { return HouseBranches.Layer.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseContainers.ContextMenu.INSTANCE).as(HouseShaping.Popup.INSTANCE).any(),
                           DSL.part(HouseContainers.ContextMenu.INSTANCE).as(HouseShaping.Specimen.INSTANCE).any());
        }
    }
}
