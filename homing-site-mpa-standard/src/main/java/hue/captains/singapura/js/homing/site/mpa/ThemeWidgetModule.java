package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.component.Widget;
import hue.captains.singapura.js.homing.conformance.rules.ConformanceDebt;
import hue.captains.singapura.js.homing.conformance.rules.WidgetDrawsOnlyComponentsRule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.preferences.PreferenceFieldModule;
import hue.captains.singapura.js.homing.preferences.PreferencesStyles;
import hue.captains.singapura.js.homing.server.PreferenceSteward;
import hue.captains.singapura.js.homing.ui.elements.CaptionModule;
import hue.captains.singapura.js.homing.ui.elements.ChoiceOptionModule;
import hue.captains.singapura.js.homing.ui.elements.KickerModule;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookModule;

import java.util.List;

/**
 * The theme as a preference widget, its two preferences each its own - the
 * style and the palette (RFC 0067, appendix Two Classes per Leaf): the styles
 * the site offers, then the palettes the chosen one is offered in, from
 * {@code /themes}; each pick written through its own field like any other
 * setting, and the page's sheets follow the store, the style's and the
 * palette's apart. Built on the generic field, so the header, the note and the
 * reset are the same as every other setting's.
 */
@ConformanceDebt(rule = WidgetDrawsOnlyComponentsRule.class, findings = 13,
        reason = "It makes elements by a companion, makes its own elements, places elements, wears classes of its own, writes attributes, writes text of its own and touches components' elements: it waits for the designed containers, the widget slot and the content components to draw only components.")
public record ThemeWidgetModule() implements Widget<Widget._None, ThemeWidgetModule> {

    /** The class. */
    public record ThemeWidget() implements Widget._Class<Widget._None, ThemeWidgetModule> {}

    public static final ThemeWidgetModule INSTANCE = new ThemeWidgetModule();

    @Override public String title() { return "Theme"; }

    @Override
    public ImportsFor<ThemeWidgetModule> imports() {
        return ImportsFor.<ThemeWidgetModule>builder()
                .add(new ModuleImports<>(List.of(new KickerModule.Kicker()), KickerModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PreferenceFieldModule.PreferenceField()), PreferenceFieldModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PreferenceSteward.PreferenceViewInstance()), PreferenceSteward.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PreferencesStyles.pv_kicker(),
                        new PreferencesStyles.pv_options(),
                        new PreferencesStyles.pv_option()
                ), PreferencesStyles.INSTANCE))
                .add(new ModuleImports<>(List.of(new ChoiceOptionModule.ChoiceOption()), ChoiceOptionModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ThemeWidgetModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ThemeWidget()));
    }
}
