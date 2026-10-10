package hue.captains.singapura.js.homing.ui.floating;

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
 * The house's float layer, as this module adds it to the house's taxonomy: the layer floating
 * panes are stacked on. The floating pane itself is common, and the base tree's. In the tree if
 * and only if this module is on the classpath.
 */
public record HouseFloating() implements TaxonomyExtension {

    public static final HouseFloating INSTANCE = new HouseFloating();

    @Override public Class<? extends TaxonomySource> extending() { return HouseTaxonomy.class; }

    /** Every component this module declares. */
    @Override public List<Component<?>> components() {
        return List.of(FloatLayer.INSTANCE);
    }

    /** The layer floating panes are stacked on. */
    public record FloatLayer() implements Component<HouseBranches.Layer> {
        public static final FloatLayer INSTANCE = new FloatLayer();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Layer parent() { return HouseBranches.Layer.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseContainers.FloatingPane.INSTANCE).as(HouseShaping.Float.INSTANCE).any());
        }
    }
}
