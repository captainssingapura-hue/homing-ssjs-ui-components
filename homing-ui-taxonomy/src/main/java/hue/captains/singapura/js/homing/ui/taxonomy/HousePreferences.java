package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;

import java.util.List;

/**
 * The house's preferences: a setting's row, listed with its value. The views and the widgets that edit a setting are
 * widgets, no components (owner, 20 Oct). Defined here, with the house's other components, whether or not its implementation is on the
 * classpath; implemented - and drawn by Base - in {@code homing-preferences} (RFC 0067, appendix The Style Jars, D40).
 */
public final class HousePreferences {

    private HousePreferences() {}

    /** One setting of a group, listed with its value. Its name is its own. */
    public record SettingRow() implements Composite<HouseBranches.Item> {
        public static final SettingRow INSTANCE = new SettingRow();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Item parent() { return HouseBranches.Item.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Value.INSTANCE).one());
        }
    }
}
