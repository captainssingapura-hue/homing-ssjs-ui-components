package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.Implemented;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.UiDialogComponentModule;
import java.util.List;

/** UiDialogComponentModule's components implemented in Base, in the typed spec stack (RFC 0067, D53). */
public record UiDialogBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiDialogBaseSpec INSTANCE = new UiDialogBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiDialogComponentModule.INSTANCE; }

    @Override public List<Implemented<BaseScheme, Base, ?>> implementations() {
        return List.of(
                new Implemented<>(HouseContainers.Dialog.INSTANCE, new hue.captains.singapura.js.homing.design.spec.styles.base.Dialog()));
    }
}
