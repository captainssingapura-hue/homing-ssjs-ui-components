package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.Implemented;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.UiFloatingComponentModule;
import java.util.List;

/** UiFloatingComponentModule's components implemented in Base, in the typed spec stack (RFC 0067, D53). */
public record UiFloatingBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiFloatingBaseSpec INSTANCE = new UiFloatingBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiFloatingComponentModule.INSTANCE; }

    @Override public List<Implemented<BaseScheme, Base, ?>> implementations() {
        return List.of(
                new Implemented<>(HouseContainers.FloatingPane.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.FloatingPane()),
                new Implemented<>(HouseContainers.FloatLayer.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.FloatLayer()));
    }
}
