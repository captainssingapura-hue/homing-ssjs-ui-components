package hue.captains.singapura.js.homing.ui.controlpanel;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomySource;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;

import java.util.List;

/**
 * The house's control panel, as this module adds it to the house's taxonomy: the controls for a
 * set of options, each made as its means calls for. In the tree if and only if this module is on
 * the classpath.
 */
public record HouseControlPanel() implements TaxonomyExtension {

    public static final HouseControlPanel INSTANCE = new HouseControlPanel();

    @Override public Class<? extends TaxonomySource> extending() { return HouseTaxonomy.class; }

    /** Every component this module declares. */
    @Override public List<Component<?>> components() {
        return List.of(ControlPanel.INSTANCE);
    }

    /** The controls for a set of options: the degrees in one group of sliders, a button for each switch, one for each action and question. */
    public record ControlPanel() implements Component<HouseBranches.Container> {
        public static final ControlPanel INSTANCE = new ControlPanel();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseContainers.SliderGroup.INSTANCE).as(HouseDoing.Adjust.INSTANCE).optional(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Toggle.INSTANCE).any(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Action.INSTANCE).any());
        }
    }
}
