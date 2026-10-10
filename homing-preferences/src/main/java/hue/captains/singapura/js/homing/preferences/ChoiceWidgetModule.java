package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.conformance.rules.ConformanceDebt;
import hue.captains.singapura.js.homing.conformance.rules.WidgetDrawsOnlyComponentsRule;
import hue.captains.singapura.js.homing.ui.elements.CaptionModule;
import hue.captains.singapura.js.homing.ui.elements.CastingsModule;

import hue.captains.singapura.js.homing.component.Widget;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * A preference that is one of a list: options with a value, a label and a
 * note, drawn as a listbox; the chosen one is written through the steward.
 * Params: {@code name, label, summary?, default?, options: [{value, label, note?}]}.
 */
@ConformanceDebt(rule = WidgetDrawsOnlyComponentsRule.class, findings = 16,
        reason = "It makes elements by a companion, makes its own elements, places elements, wears classes of its own, writes attributes and writes text of its own: it waits for the designed containers, the widget slot and the content components to draw only components.")
public record ChoiceWidgetModule() implements Widget<Widget._None, ChoiceWidgetModule>, ImplFor<HousePreferences.ChoiceWidget> {

    /** The class. */
    public record ChoiceWidget() implements Widget._Class<Widget._None, ChoiceWidgetModule> {}

    public static final ChoiceWidgetModule INSTANCE = new ChoiceWidgetModule();

    @Override public String title() { return "Choice"; }

    @Override
    public ImportsFor<ChoiceWidgetModule> imports() {
        return ImportsFor.<ChoiceWidgetModule>builder()
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption(), new CaptionModule.CaptionFactory()), CaptionModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PreferenceFieldModule.PreferenceField()), PreferenceFieldModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PreferencesStyles.pv_options(),
                        new PreferencesStyles.pv_option(),
                        new PreferencesStyles.pv_option_note()
                ), PreferencesStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ChoiceWidgetModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ChoiceWidget()));
    }
}
