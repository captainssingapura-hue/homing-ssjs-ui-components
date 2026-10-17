package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;

import java.util.List;

/**
 * The house's monitors: where focus is on the page, and where the keyboard's steward has it. Defined here, with the
 * house's other components, whether or not its implementation is on the classpath; implemented - and drawn by Base - in
 * {@code homing-ui-monitors} (RFC 0067, appendix The Style Jars, D40).
 */
public final class HouseMonitors {

    private HouseMonitors() {}

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
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
        /** Its body one lamp: quiet, lit while the keys are held or lent, broken while the steward's invariants are. */
        @Override public List<Slot<?>> parts() { return List.of(DSL.part(HouseMarks.Lamp.INSTANCE).as(HouseShaping.Body.INSTANCE).one()); }
    }
}
