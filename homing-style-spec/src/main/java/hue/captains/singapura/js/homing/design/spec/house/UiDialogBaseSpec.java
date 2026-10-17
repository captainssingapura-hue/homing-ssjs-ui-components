package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.StandaloneEntry;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.UiDialogComponentModule;

import java.util.List;

/** UiDialogComponentModule's standalones in Base, in the typed spec stack (RFC 0067, D53): its components', and those of their design a functional role places. */
public record UiDialogBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiDialogBaseSpec INSTANCE = new UiDialogBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiDialogComponentModule.INSTANCE; }

    @Override public List<StandaloneEntry<BaseScheme, Base, ?>> standalones() {
        return List.of(
                new StandaloneEntry<>(HouseContainers.Dialog.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.DialogDeclaration())),
                new StandaloneEntry<>(HouseContainers.FloatingPane.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.FloatingPaneDeclaration())),
                new StandaloneEntry<>(HouseContainers.ActionBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ActionBarDeclaration())),
                new StandaloneEntry<>(HouseContainers.TitleBar.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.TitleBarDeclaration())));
    }
}
