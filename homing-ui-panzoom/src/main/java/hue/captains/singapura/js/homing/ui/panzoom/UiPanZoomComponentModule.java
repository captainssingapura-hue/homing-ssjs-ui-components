package hue.captains.singapura.js.homing.ui.panzoom;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanZoom;

import java.util.List;

/**
 * This jar's components, for the components taxonomy an instance registers at bootstrap: those it implements - a flat list; their parts come in with them.
 */
public record UiPanZoomComponentModule() implements ComponentModule {

    public static final UiPanZoomComponentModule INSTANCE = new UiPanZoomComponentModule();

    @Override public List<? extends Component<?>> components() {
        return List.of(HousePanZoom.SvgPanZoom.INSTANCE, HousePanZoom.PanZoomBar.INSTANCE);
    }
}
