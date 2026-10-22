package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.ui.elements.KickerModule;

import hue.captains.singapura.js.homing.component.Widget;
import hue.captains.singapura.js.homing.conformance.rules.ConformanceDebt;
import hue.captains.singapura.js.homing.conformance.rules.WidgetDrawsOnlyComponentsRule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.PlainButtonModule;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;

import java.util.List;

/**
 * {@code TabPicker}, a transient widget (owner, 22 Oct): the kinds that can be
 * opened, a button each, and Cancel - made by its caller, a workspace or an app,
 * which shows it in a pane, waits for what is picked, and puts the widget picked
 * in the pane; the pane is dumb, and knows nothing of what is opened. Picked, it ASKS and opens nothing itself -
 * whoever asked the host for it turns the pick into a request, and the host
 * lets it go. Nothing of a register's: no tab, no widget. It is what a
 * {@link TabOpenerModule.TabOpener} was, without becoming what it opens - a
 * widget is locked to its pane, so no pane turns into another.
 *
 * <p>Its choices are native buttons: the browser's own keys walk and press
 * them, and the first takes the focus when the picker is shown.</p>
 */
@ConformanceDebt(rule = WidgetDrawsOnlyComponentsRule.class, findings = 12,
        reason = "It makes elements of its own and by a companion, places them, wears classes of its own and touches its components' elements: a component until 22 Oct, it waits for its port as a widget - the designed containers, and the widget slot - to draw only components.")
public record TabPickerModule() implements Widget<Widget._None, TabPickerModule> {

    /** The class. */
    public record TabPicker() implements Widget._Class<Widget._None, TabPickerModule> {}

    public static final TabPickerModule INSTANCE = new TabPickerModule();

    @Override public String title() { return "Tab picker"; }

    @Override
    public ImportsFor<TabPickerModule> imports() {
        return ImportsFor.<TabPickerModule>builder()
                .add(new ModuleImports<>(List.of(new KickerModule.Kicker()), KickerModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PaneStyles.mtp_opener(),
                        new PaneStyles.mtp_opener_note(),
                        new PaneStyles.mtp_opener_grid()
                ), PaneStyles.INSTANCE))
                .add(new ModuleImports<>(List.of(new PlainButtonModule.PlainButton()), PlainButtonModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<TabPickerModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new TabPicker())); }
}
