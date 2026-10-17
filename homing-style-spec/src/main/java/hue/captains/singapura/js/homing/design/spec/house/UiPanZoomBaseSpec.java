package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.StandaloneEntry;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;
import hue.captains.singapura.js.homing.ui.taxonomy.UiPanZoomComponentModule;

import java.util.List;

/** UiPanZoomComponentModule's standalones in Base, in the typed spec stack (RFC 0067, D53): its components', and those of their design a functional role places. */
public record UiPanZoomBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiPanZoomBaseSpec INSTANCE = new UiPanZoomBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiPanZoomComponentModule.INSTANCE; }

    @Override public List<StandaloneEntry<BaseScheme, Base, ?>> standalones() {
        return List.of(
                new StandaloneEntry<>(HousePanZoom.SvgPanZoom.INSTANCE, new Target.Impl<>(new hue.captains.singapura.js.homing.design.spec.styles.base.SvgPanZoom())),
                new StandaloneEntry<>(HousePanZoom.PanZoomBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PanZoomBarDeclaration())));
    }
}
