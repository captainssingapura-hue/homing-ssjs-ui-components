package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentModule;
import hue.captains.singapura.js.homing.design.spec.BaseSpecModule;
import hue.captains.singapura.js.homing.design.spec.StandaloneEntry;
import hue.captains.singapura.js.homing.design.spec.Target;
import hue.captains.singapura.js.homing.ui.styles.Base;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSite;
import hue.captains.singapura.js.homing.ui.taxonomy.MpaComponentModule;

import java.util.List;

/** MpaComponentModule's standalones in Base, in the typed spec stack (RFC 0067, D53): its components', and those of their design a functional role places. */
public record MpaBaseSpec() implements BaseSpecModule<BaseScheme, Base> {

    public static final MpaBaseSpec INSTANCE = new MpaBaseSpec();

    @Override public Base style() { return Base.INSTANCE; }

    @Override public ComponentModule components() { return MpaComponentModule.INSTANCE; }

    @Override public List<StandaloneEntry<BaseScheme, Base, ?>> standalones() {
        return List.of(
                new StandaloneEntry<>(HouseSite.MpaChrome.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.MpaChromeDeclaration())),
                new StandaloneEntry<>(HouseControls.PreferencesButton.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.PreferencesButtonDeclaration())),
                new StandaloneEntry<>(HouseSite.ThemeWidget.INSTANCE, new Target.Declared<>(new hue.captains.singapura.js.homing.design.spec.styles.base.ThemeWidgetDeclaration())));
    }
}
