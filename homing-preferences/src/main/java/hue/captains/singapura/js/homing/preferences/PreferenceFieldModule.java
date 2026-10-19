package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.conformance.rules.ConformanceDebt;
import hue.captains.singapura.js.homing.conformance.rules.WidgetDrawsOnlyComponentsRule;
import hue.captains.singapura.js.homing.ui.elements.CaptionModule;
import hue.captains.singapura.js.homing.ui.elements.Heading4Module;
import hue.captains.singapura.js.homing.ui.elements.KickerModule;
import hue.captains.singapura.js.homing.ui.elements.LedeModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePreferences;

import hue.captains.singapura.js.homing.component.Widget;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.server.PreferenceSteward;
import hue.captains.singapura.js.homing.ui.elements.PlainButtonModule;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;

import java.util.List;

/**
 * What every setting's widget has in common: the header (kicker, title,
 * summary), the body its holder's control goes in, the note under it saying
 * where the value came from, and the way back to the site's default. A
 * widget, a setting's widget's sub-widget: {@code new PreferenceField(branch, params)}
 * with {@code hold(el)} for the control, {@code value()}, {@code set(v)}. Built on the
 * steward: it reads the value, writes a pick, forgets on reset, and follows the
 * store so a change made anywhere is drawn here - as its holder does, a member
 * of the same party, for its control.
 */
@ConformanceDebt(rule = WidgetDrawsOnlyComponentsRule.class, findings = 21,
        reason = "It makes elements by a companion, makes its own elements, places elements, wears classes of its own and touches components' elements: it waits for the designed containers and the content components to draw only components.")
public record PreferenceFieldModule() implements Widget<Widget._None, PreferenceFieldModule>, ImplFor<HousePreferences.PreferenceField> {

    /** The class. */
    public record PreferenceField() implements Widget._Class<Widget._None, PreferenceFieldModule> {
        @Override public String summary() { return "What every setting's widget has in common: the label, the summary, the control's place, the note and the reset."; }
    }

    public static final PreferenceFieldModule INSTANCE = new PreferenceFieldModule();

    @Override public String title() { return "Setting"; }

    @Override
    public ImportsFor<PreferenceFieldModule> imports() {
        return ImportsFor.<PreferenceFieldModule>builder()
                .add(new ModuleImports<>(List.of(new KickerModule.Kicker()), KickerModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Heading4Module.Heading4()), Heading4Module.INSTANCE))
                .add(new ModuleImports<>(List.of(new LedeModule.Lede()), LedeModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption()), CaptionModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.EVENT()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PreferenceSteward.PreferenceStewardInstance(),
                        new PreferenceSteward.PreferenceViewInstance()
                ), PreferenceSteward.INSTANCE))
                .add(new ModuleImports<>(List.of(new PlainButtonModule.PlainButton()), PlainButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PreferencesStyles.pv_kicker(),
                        new PreferencesStyles.pv_title(),
                        new PreferencesStyles.pv_summary(),
                        new PreferencesStyles.pv_note(),
                        new PreferencesStyles.pv_actions()
                ), PreferencesStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<PreferenceFieldModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new PreferenceField()));
    }
}
