package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;

import java.util.List;

/**
 * homing-ui-splitgrid's component module, defined beside the components it lists (D40, D46): those the jar implements, then
 * those its owners realize inline and it gives its look to - a flat list; their parts come in with them.
 * A definition alone - what a style jar's check needs of the jar - while the jar keeps the implementation.
 */
public record UiSplitGridComponentModule() implements ComponentModule {

    public static final UiSplitGridComponentModule INSTANCE = new UiSplitGridComponentModule();

    @Override public List<? extends Component<?>> components() {
        return List.of(HouseSplitGrid.SplitGridMirror.INSTANCE);
    }
}
