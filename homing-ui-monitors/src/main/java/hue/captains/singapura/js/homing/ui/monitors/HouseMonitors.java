package hue.captains.singapura.js.homing.ui.monitors;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomySource;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;

import java.util.List;

/**
 * The house's monitors, as this module adds them to the house's taxonomy: where focus is on the
 * page, and where the keyboard's steward has it. In the tree if and only if this module is on the
 * classpath.
 */
public record HouseMonitors() implements TaxonomyExtension {

    public static final HouseMonitors INSTANCE = new HouseMonitors();

    @Override public Class<? extends TaxonomySource> extending() { return HouseTaxonomy.class; }

    /** Every component this module declares. */
    @Override public List<Component<?>> components() {
        return List.of(FocusMonitor.INSTANCE, StewardMonitor.INSTANCE);
    }

    /** Where focus is on the page, as a tree. */
    public record FocusMonitor() implements Component<HouseBranches.View> {
        public static final FocusMonitor INSTANCE = new FocusMonitor();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseItems.ListRow.INSTANCE).as(HouseShaping.Entry.INSTANCE).atLeast(1));
        }
    }

    /** Where the keyboard's steward has the focus, as one lamp. */
    public record StewardMonitor() implements Component<HouseBranches.View> {
        public static final StewardMonitor INSTANCE = new StewardMonitor();
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
    }
}
