package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.StandaloneEntry;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControlPanel;
import hue.captains.singapura.js.homing.ui.taxonomy.UiControlPanelComponentModule;

import java.util.List;

/** UiControlPanelComponentModule's standalones in Base, in the typed spec stack (RFC 0067, D53): its components', and those of their design a functional role places. */
public record UiControlPanelBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final UiControlPanelBaseSpec INSTANCE = new UiControlPanelBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return UiControlPanelComponentModule.INSTANCE; }

    @Override public List<StandaloneEntry<BaseScheme, Base, ?>> standalones() {
        return List.of(
                new StandaloneEntry<>(HouseControlPanel.ControlPanel.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ControlPanelDeclaration())));
    }
}
