package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSplitGrid;
import hue.captains.singapura.js.homing.ui.taxonomy.UiSplitGridComponentModule;

import java.util.List;

/**
 * UiSplitGridComponentModule's components in Base, in the typed spec stack (RFC 0067, D53, D54): each component it brings - its own, and
 * the parts that come in with them - and the implementation each of its castings wears, on its own and in each role it
 * is cast in.
 */
public record UiSplitGridBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiSplitGridBaseSpec INSTANCE = new UiSplitGridBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiSplitGridComponentModule.INSTANCE; }

    @Override public List<ComponentAssignment<BaseScheme, Base, ?>> assignments() {
        return List.of(
                new ComponentAssignment<>(HouseSplitGrid.SplitGridMirror.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.SplitGridMirror(), List.of()));
    }
}
