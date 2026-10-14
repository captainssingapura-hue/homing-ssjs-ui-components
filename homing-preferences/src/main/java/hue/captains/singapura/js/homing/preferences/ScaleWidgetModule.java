package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import hue.captains.singapura.js.homing.conformance.rules.ConformanceDebt;
import hue.captains.singapura.js.homing.conformance.rules.WidgetDrawsOnlyComponentsRule;
import hue.captains.singapura.js.homing.ui.elements.RangeModule;
import hue.captains.singapura.js.homing.ui.elements.ReadoutModule;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;

import hue.captains.singapura.js.homing.component.Widget;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.server.PreferenceSteward;

import java.util.List;

/**
 * A preference that is a number in a range: a slider with a readout.
 * Params: {@code name, label, summary?, default?, min, max, step?, unit?}.
 */
@ConformanceDebt(rule = WidgetDrawsOnlyComponentsRule.class, findings = 6,
        reason = "It makes elements by a companion, makes its own elements, places elements, wears classes of its own and writes attributes: it waits for the designed containers, the widget slot and the content components to draw only components.")
public record ScaleWidgetModule() implements Widget<Widget._None, ScaleWidgetModule>, ImplFor<HousePreferences.ScaleWidget> {

    /** The class. */
    public record ScaleWidget() implements Widget._Class<Widget._None, ScaleWidgetModule> {}

    public static final ScaleWidgetModule INSTANCE = new ScaleWidgetModule();

    @Override public String title() { return "Scale"; }

    @Override
    public ImportsFor<ScaleWidgetModule> imports() {
        return ImportsFor.<ScaleWidgetModule>builder()
                .add(new ModuleImports<>(List.of(new RangeModule.Range()), RangeModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.EVENT()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ReadoutModule.Readout(), new ReadoutModule.ReadoutFactory()), ReadoutModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PreferenceFieldModule.PreferenceField()), PreferenceFieldModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PreferenceSteward.PreferenceViewInstance()), PreferenceSteward.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PreferencesStyles.pv_range(),
                        new PreferencesStyles.pv_readout()
                ), PreferencesStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ScaleWidgetModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ScaleWidget()));
    }
}
