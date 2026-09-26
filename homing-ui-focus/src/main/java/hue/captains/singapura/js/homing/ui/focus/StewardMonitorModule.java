package hue.captains.singapura.js.homing.ui.focus;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.FocusPartyModule;
import hue.captains.singapura.js.homing.component.keyboard.focusParty;
import hue.captains.singapura.js.homing.component.keyboard.KeyboardStewardModule;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;

import java.util.List;

/**
 * The steward monitor: where the focus is, on view, as one lamp — the
 * steward's marker (RFC 0066 E3, keyboard §17.5), held, lent or away, whose,
 * the member a walk offers the keys to, and the first of the steward's
 * invariants broken, in the danger colour. Redrawn on every event of the
 * steward and every move of the native focus. It takes no keys.
 */
public record StewardMonitorModule() implements DomModule<StewardMonitorModule> {

    /** A branch component: {@code new StewardMonitor(branch, { host })}; {@code lamp()}, {@code broken()}, {@code refresh()}, {@code dispose()}. */
    public record StewardMonitor() implements BranchComponent<StewardMonitorModule> {
        @Override public String summary() { return "Where the focus is, as one lamp: the steward's marker — held, lent or away — and its invariants, the first broken in the danger colour."; }
    }

    public static final StewardMonitorModule INSTANCE = new StewardMonitorModule();

    @Override
    public ImportsFor<StewardMonitorModule> imports() {
        return ImportsFor.<StewardMonitorModule>builder()
                .add(new ModuleImports<>(List.of(new focusParty()), FocusPartyModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new KeyboardStewardModule.KeyboardSteward(), new KeyboardStewardModule.KeyboardStewardInstance()), KeyboardStewardModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new FocusStyles.sm_lamp(),
                        new FocusStyles.sm_lamp_dormant(),
                        new FocusStyles.sm_lamp_broken()
                ), FocusStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<StewardMonitorModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new StewardMonitor()));
    }
}
