package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.KeyboardStewardModule;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.dialog.DialogModule;
import hue.captains.singapura.js.homing.ui.elements.Elements;

import java.util.List;

/**
 * {@code DialogSpecimen}: the house's dialog in action - modal or not, settled by an action, Enter or
 * Escape. It hands the dialog the page's one keyboard steward, as the page gives every component.
 */
public record DialogSpecimenModule() implements DomModule<DialogSpecimenModule> {

    public static final DialogSpecimenModule INSTANCE = new DialogSpecimenModule();

    public record DialogSpecimen() implements BranchComponent<DialogSpecimenModule> {
        @Override public String summary() { return "The house's dialog in action: one matter put above the page, modal - the page held back - or not; settled by an action, by Enter for the primary one, or by Escape; the keys taken while it is open and given back."; }
    }

    @Override
    public ImportsFor<DialogSpecimenModule> imports() {
        return ImportsFor.<DialogSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new DialogModule.Dialog()), DialogModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new KeyboardStewardModule.KeyboardStewardInstance()), KeyboardStewardModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_row(), new SpecimenStyles.sp_text()),
                        SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<DialogSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new DialogSpecimen())); }
}
