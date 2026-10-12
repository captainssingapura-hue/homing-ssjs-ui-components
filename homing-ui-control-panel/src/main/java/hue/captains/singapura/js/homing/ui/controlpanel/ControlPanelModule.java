package hue.captains.singapura.js.homing.ui.controlpanel;

import hue.captains.singapura.js.homing.component.Widget;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.conformance.rules.ConformanceDebt;
import hue.captains.singapura.js.homing.conformance.rules.WidgetDrawsOnlyComponentsRule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import hue.captains.singapura.js.homing.ui.elements.PlainButtonModule;
import hue.captains.singapura.js.homing.ui.elements.SliderGroupModule;

import java.util.List;

/**
 * The control panel: the controls for a set of options, each made as its means calls for - a slider
 * for a degree, in one group that holds the keys; a toggle for a switch; a button for an action or
 * a question. It drives nothing itself: it says what was set or asked, and shows what it is told.
 *
 * <p>A widget, its holder's to show in a slot and to operate through the instance: it hears
 * nothing of the world but what its holder tells it, and tells its holder alone what was set or
 * asked - {@code onSet(fn)}, {@code onInvoke(fn)} - for its holder to say where it should.</p>
 */
@ConformanceDebt(rule = WidgetDrawsOnlyComponentsRule.class, findings = 14,
        reason = "It makes elements by a companion, makes its own elements, places elements, wears classes of its own, writes text of its own and touches components' elements: it waits for the designed containers and the content components to draw only components.")
public record ControlPanelModule() implements Widget<Widget._None, ControlPanelModule>, ImplFor<HouseControlPanel.ControlPanel> {

    public static final ControlPanelModule INSTANCE = new ControlPanelModule();

    /** The class: {@code new ControlPanel(branch, { options, nothing })}; {@code onSet}, {@code onInvoke}, {@code set}, {@code key}, {@code dispose}. */
    public record ControlPanel() implements Widget._Class<Widget._None, ControlPanelModule> {
        @Override public String summary() { return "The controls for a set of options: a slider for each degree, a button for each switch, action and question."; }
    }

    @Override public String title() { return "Control panel"; }

    @Override
    public ImportsFor<ControlPanelModule> imports() {
        return ImportsFor.<ControlPanelModule>builder()
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.EVENT()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ControlsModule.Controls()), ControlsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SliderGroupModule.SliderGroup()), SliderGroupModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PlainButtonModule.PlainButton(), new PlainButtonModule.PlainButtonFactory()), PlainButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ControlPanelStyles.cp_panel(), new ControlPanelStyles.cp_row(), new ControlPanelStyles.cp_note()),
                        ControlPanelStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<ControlPanelModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new ControlPanel())); }
}
