package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;

import java.util.List;

/**
 * The house's control panel: the controls for a set of options, each made as its means calls for. Defined here, with
 * the house's other components, whether or not its implementation is on the classpath; implemented - and drawn by Base
 * - in {@code homing-ui-control-panel} (RFC 0067, appendix The Style Jars, D40).
 */
public final class HouseControlPanel {

    private HouseControlPanel() {}

    /** The controls for a set of options: a slider for each degree, a button for each switch, one for each action and question. */
    public record ControlPanel() implements Composite<HouseBranches.Cluster> {
        public static final ControlPanel INSTANCE = new ControlPanel();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Cluster parent() { return HouseBranches.Cluster.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.Slider.INSTANCE).as(HouseShaping.Member.INSTANCE).any(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Toggle.INSTANCE).any(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Action.INSTANCE).any());
        }
    }
}
