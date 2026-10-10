package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.conformance.rules.ConformanceDebt;
import hue.captains.singapura.js.homing.conformance.rules.WidgetDrawsOnlyComponentsRule;
import hue.captains.singapura.js.homing.ui.elements.KickerModule;
import hue.captains.singapura.js.homing.ui.elements.CaptionModule;
import hue.captains.singapura.js.homing.ui.elements.CastingsModule;

import hue.captains.singapura.js.homing.component.Widget;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.preferences.PreferenceFieldModule;
import hue.captains.singapura.js.homing.preferences.PreferencesStyles;
import hue.captains.singapura.js.homing.server.PreferenceSteward;

import java.util.List;

/**
 * The theme as a preference widget: the designs the site offers, then the
 * colours the chosen design is offered in, from {@code /themes}; a pick is
 * written through the field like any other setting, and the CSS manager
 * follows the store as it always has. Built on the generic field, so the
 * header, the note and the reset are the same as every other setting's.
 */
@ConformanceDebt(rule = WidgetDrawsOnlyComponentsRule.class, findings = 22,
        reason = "It makes elements by a companion, makes its own elements, places elements, wears classes of its own, writes attributes, writes text of its own and touches components' elements: it waits for the designed containers, the widget slot and the content components to draw only components.")
public record ThemeWidgetModule() implements Widget<Widget._None, ThemeWidgetModule>, ImplFor<HouseSite.ThemeWidget> {

    /** The class. */
    public record ThemeWidget() implements Widget._Class<Widget._None, ThemeWidgetModule> {}

    public static final ThemeWidgetModule INSTANCE = new ThemeWidgetModule();

    @Override public String title() { return "Theme"; }

    @Override
    public ImportsFor<ThemeWidgetModule> imports() {
        return ImportsFor.<ThemeWidgetModule>builder()
                .add(new ModuleImports<>(List.of(new KickerModule.Kicker(), new KickerModule.KickerFactory()), KickerModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption(), new CaptionModule.CaptionFactory()), CaptionModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PreferenceFieldModule.PreferenceField()), PreferenceFieldModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PreferenceSteward.PreferenceViewInstance()), PreferenceSteward.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PreferencesStyles.pv_kicker(),
                        new PreferencesStyles.pv_options(),
                        new PreferencesStyles.pv_option(),
                        new PreferencesStyles.pv_option_note()
                ), PreferencesStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ThemeWidgetModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ThemeWidget()));
    }
}
