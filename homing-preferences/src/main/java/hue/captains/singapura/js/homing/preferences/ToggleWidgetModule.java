package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookModule;

import hue.captains.singapura.js.homing.component.Widget;
import hue.captains.singapura.js.homing.conformance.rules.ConformanceDebt;
import hue.captains.singapura.js.homing.conformance.rules.WidgetDrawsOnlyComponentsRule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.server.PreferenceSteward;

import java.util.List;

/**
 * A preference that is on or off: a switch, {@code "true"} or {@code "false"}
 * in the store. Params: {@code name, label, summary?, default?, on?, off?} —
 * the last two the words shown beside the switch in each state.
 */
@ConformanceDebt(rule = WidgetDrawsOnlyComponentsRule.class, findings = 13,
        reason = "It makes its own elements, places elements, wears classes of its own, writes attributes and writes text of its own: it waits for the designed containers, the widget slot and the content components to draw only components.")
public record ToggleWidgetModule() implements Widget<Widget._None, ToggleWidgetModule>, ImplFor<HousePreferences.ToggleWidget> {

    /** The class. */
    public record ToggleWidget() implements Widget._Class<Widget._None, ToggleWidgetModule> {}

    public static final ToggleWidgetModule INSTANCE = new ToggleWidgetModule();

    @Override public String title() { return "Toggle"; }

    @Override
    public ImportsFor<ToggleWidgetModule> imports() {
        return ImportsFor.<ToggleWidgetModule>builder()
                .add(new ModuleImports<>(List.of(new PreferenceFieldModule.PreferenceField()), PreferenceFieldModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PreferenceSteward.PreferenceViewInstance()), PreferenceSteward.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new LookModule.Look()), LookModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PreferencesStyles.pv_switch(),
                        new PreferencesStyles.pv_switch_track(),
                        new PreferencesStyles.pv_switch_knob()
                ), PreferencesStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ToggleWidgetModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ToggleWidget()));
    }
}
