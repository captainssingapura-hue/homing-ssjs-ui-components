package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.ComponentAssignment;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;
import hue.captains.singapura.js.homing.ui.taxonomy.UiPanZoomComponentModule;

import java.util.List;

/**
 * UiPanZoomComponentModule's components in Base, in the typed spec stack (RFC 0067, D53, D54): each component it brings - its own, and
 * the parts that come in with them - and the implementation each of its castings wears, on its own and in each role it
 * is cast in.
 */
public record UiPanZoomBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiPanZoomBaseSpec INSTANCE = new UiPanZoomBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiPanZoomComponentModule.INSTANCE; }

    @Override public List<ComponentAssignment<BaseScheme, Base, ?>> assignments() {
        return List.of(
                new ComponentAssignment<>(HousePanZoom.SvgPanZoom.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.SvgPanZoom(), List.of()),
                new ComponentAssignment<>(HousePanZoom.PanZoomBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PanZoomBar(), List.of()));
    }
}
