package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;

import java.util.List;

/**
 * homing-site-mpa-standard's component module, defined beside the components it lists (D40, D46): those the jar implements - a flat list; their parts come in with them.
 * A definition alone - what a style jar's check needs of the jar - while the jar keeps the implementation.
 */
public record MpaComponentModule() implements ComponentModule {

    public static final MpaComponentModule INSTANCE = new MpaComponentModule();

    @Override public List<? extends Component<?>> components() {
        return List.of(HouseControls.PreferencesButton.INSTANCE);
    }
}
