package hue.captains.singapura.js.homing.ui.splitgrid;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSplitGrid;

import java.util.List;

/**
 * This jar's components, for the components taxonomy an instance registers at bootstrap: those it implements, then those
 * its owners realize inline and it gives its look to - a flat list; their parts come in with them.
 */
public record UiSplitGridComponentModule() implements ComponentModule {

    public static final UiSplitGridComponentModule INSTANCE = new UiSplitGridComponentModule();

    @Override public List<? extends Component<?>> components() {
        return List.of(HouseSplitGrid.SplitGrid.INSTANCE, HouseSplitGrid.SplitGridMirror.INSTANCE,
                       HouseSplitGrid.GridCell.INSTANCE);
    }
}
