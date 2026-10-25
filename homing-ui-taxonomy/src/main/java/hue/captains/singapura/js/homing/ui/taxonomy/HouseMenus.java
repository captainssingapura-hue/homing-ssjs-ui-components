package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Cardinality;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;

import java.util.List;

/**
 * The house's keeper of context menus. The context menu itself is common, and the base tree's. Defined here, with the
 * house's other components, whether or not its implementation is on the classpath; implemented - and drawn by Base - in
 * {@code homing-ui-menu} (RFC 0067, appendix The Style Jars, D40).
 */
public final class HouseMenus {

    private HouseMenus() {}

    /** The one keeper of the page's context menus: one open at a time, and a specimen of each kind. Its layer is its own. */
    public record ContextMenuSteward() implements Composite<HouseBranches.Layer> {
        public static final ContextMenuSteward INSTANCE = new ContextMenuSteward();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Layer parent() { return HouseBranches.Layer.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseContainers.ContextMenu.INSTANCE).as(HouseShaping.Popup.INSTANCE).any(Cardinality.DYNAMIC),
                           DSL.part(HouseContainers.ContextMenu.INSTANCE).as(HouseShaping.Specimen.INSTANCE).any(Cardinality.DYNAMIC));
        }
    }
}
