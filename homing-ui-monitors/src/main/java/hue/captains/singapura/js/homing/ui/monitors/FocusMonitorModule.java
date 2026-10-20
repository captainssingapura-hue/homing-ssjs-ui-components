package hue.captains.singapura.js.homing.ui.monitors;

import hue.captains.singapura.js.homing.component.Widget;
import hue.captains.singapura.js.homing.component.keyboard.FocusPartyModule;
import hue.captains.singapura.js.homing.component.keyboard.KeyboardStewardModule;
import hue.captains.singapura.js.homing.component.keyboard.focusParty;
import hue.captains.singapura.js.homing.conformance.rules.ConformanceDebt;
import hue.captains.singapura.js.homing.conformance.rules.WidgetDrawsOnlyComponentsRule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.ListRowModule;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookModule;

import java.util.List;

/**
 * The focus monitor: the page's logical-focus tree as a tree view — every
 * branch, every member, the holder of the keys marked — redrawn on every
 * notice of the party and every event of the steward. It says exactly which
 * component is in focus and under whom; a holder outside the tree, a member
 * by id, is named below it. It takes no keys; the steward's own state is the
 * {@link StewardMonitorModule.StewardMonitor}'s.
 */
@ConformanceDebt(rule = WidgetDrawsOnlyComponentsRule.class, findings = 32,
        reason = "It makes its own elements, reaches the document, places elements, wears classes of its own, writes attributes, writes styles and writes text of its own: it waits for the designed containers and the content components to draw only components, and for a party that says where the browser's focus moves.")
public record FocusMonitorModule() implements Widget<Widget._None, FocusMonitorModule> {

    /** A widget: {@code new FocusMonitor(branch, params)}; {@code refresh()}, {@code holderRow()}, {@code candidateRow()}, {@code dispose()}. */
    public record FocusMonitor() implements Widget._Class<Widget._None, FocusMonitorModule> {
        @Override public String summary() { return "The logical-focus tree as a tree view, the holder of the keys marked; redrawn as the party and the steward speak."; }
    }

    public static final FocusMonitorModule INSTANCE = new FocusMonitorModule();

    @Override public String title() { return "Logical focus"; }

    @Override
    public ImportsFor<FocusMonitorModule> imports() {
        return ImportsFor.<FocusMonitorModule>builder()
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ListRowModule.ListRow()), ListRowModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new focusParty()), FocusPartyModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new KeyboardStewardModule.KeyboardSteward(), new KeyboardStewardModule.KeyboardStewardInstance()), KeyboardStewardModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new FocusStyles.fm_tree(),
                        new FocusStyles.fm_row(),
                        new FocusStyles.fm_row_holder(),
                        new FocusStyles.fm_kind(),
                        new FocusStyles.fm_name(),
                        new FocusStyles.fm_component(),
                        new FocusStyles.fm_outside()
                ), FocusStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<FocusMonitorModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new FocusMonitor()));
    }
}
