package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.conformance.rules.ConformanceDebt;
import hue.captains.singapura.js.homing.conformance.rules.WidgetDrawsOnlyComponentsRule;
import hue.captains.singapura.js.homing.ui.elements.KickerModule;
import hue.captains.singapura.js.homing.ui.elements.HeadingModule;
import hue.captains.singapura.js.homing.ui.elements.LedeModule;
import hue.captains.singapura.js.homing.ui.elements.CaptionModule;

import hue.captains.singapura.js.homing.component.Widget;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.server.PreferenceSteward;
import hue.captains.singapura.js.homing.ui.elements.StatesAndEventsModule;
import hue.captains.singapura.js.homing.ui.elements.CastingsModule;
import hue.captains.singapura.js.homing.ui.elements.PlainButtonModule;

import java.util.List;

/**
 * A group's page: its label and summary, the settings under it with the
 * value each has in force, and a reset for all of them at once. Params:
 * {@code label, summary?, settings: [{name, label}]} — the settings below
 * the group, flattened by the site when it declares the tree.
 */
@ConformanceDebt(rule = WidgetDrawsOnlyComponentsRule.class,
        reason = "It makes elements by a companion, makes its own elements, places elements, wears classes of its own and writes text of its own: it waits for the designed containers, the widget slot and the content components to draw only components.")
public record OverviewWidgetModule() implements Widget<Widget._None, OverviewWidgetModule>, ImplFor<HousePreferences.OverviewWidget> {

    /** The class. */
    public record OverviewWidget() implements Widget._Class<Widget._None, OverviewWidgetModule> {}

    public static final OverviewWidgetModule INSTANCE = new OverviewWidgetModule();

    @Override public String title() { return "Overview"; }

    @Override
    public ImportsFor<OverviewWidgetModule> imports() {
        return ImportsFor.<OverviewWidgetModule>builder()
                .add(new ModuleImports<>(List.of(new KickerModule.Kicker(), new KickerModule.KickerFactory()), KickerModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new HeadingModule.Heading(), new HeadingModule.HeadingFactory()), HeadingModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new LedeModule.Lede(), new LedeModule.LedeFactory()), LedeModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption(), new CaptionModule.CaptionFactory()), CaptionModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.EVENT()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PreferenceSteward.PreferenceStewardInstance(),
                        new PreferenceSteward.PreferenceViewInstance()
                ), PreferenceSteward.INSTANCE))
                .add(new ModuleImports<>(List.of(new PlainButtonModule.PlainButton(), new PlainButtonModule.PlainButtonFactory()), PlainButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PreferencesStyles.pv_kicker(),
                        new PreferencesStyles.pv_title(),
                        new PreferencesStyles.pv_summary(),
                        new PreferencesStyles.pv_children(),
                        new PreferencesStyles.pv_child(),
                        new PreferencesStyles.pv_option_note(),
                        new PreferencesStyles.pv_actions()
                ), PreferencesStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<OverviewWidgetModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new OverviewWidget()));
    }
}
