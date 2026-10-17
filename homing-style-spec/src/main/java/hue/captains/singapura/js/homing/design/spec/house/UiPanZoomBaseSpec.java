package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.Implemented;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;
import hue.captains.singapura.js.homing.ui.taxonomy.UiPanZoomComponentModule;
import java.util.List;

/** UiPanZoomComponentModule's components implemented in Base, in the typed spec stack (RFC 0067, D53). */
public record UiPanZoomBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiPanZoomBaseSpec INSTANCE = new UiPanZoomBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiPanZoomComponentModule.INSTANCE; }

    @Override public List<Implemented<BaseScheme, Base, ?>> implementations() {
        return List.of(
                new Implemented<>(HousePanZoom.SvgPanZoom.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.SvgPanZoom()),
                new Implemented<>(HousePanZoom.PanZoomBar.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.PanZoomBar()));
    }
}
