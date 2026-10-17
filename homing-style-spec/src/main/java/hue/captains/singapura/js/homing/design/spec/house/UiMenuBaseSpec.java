package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.Implemented;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMenus;
import hue.captains.singapura.js.homing.ui.taxonomy.UiMenuComponentModule;
import java.util.List;

/** UiMenuComponentModule's components implemented in Base, in the typed spec stack (RFC 0067, D53). */
public record UiMenuBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiMenuBaseSpec INSTANCE = new UiMenuBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiMenuComponentModule.INSTANCE; }

    @Override public List<Implemented<BaseScheme, Base, ?>> implementations() {
        return List.of(
                new Implemented<>(HouseMenus.ContextMenuSteward.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ContextMenuSteward()),
                new Implemented<>(HouseContainers.ContextMenu.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.ContextMenu()));
    }
}
