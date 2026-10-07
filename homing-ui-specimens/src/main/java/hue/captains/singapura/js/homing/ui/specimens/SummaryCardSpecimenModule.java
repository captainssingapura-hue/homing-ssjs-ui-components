package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.Key;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;

import java.util.List;

/** {@code SummaryCardSpecimen}: the house's summary card in action - opened by the pointer or the keys - at its size and aspect. */
public record SummaryCardSpecimenModule() implements DomModule<SummaryCardSpecimenModule> {

    public static final SummaryCardSpecimenModule INSTANCE = new SummaryCardSpecimenModule();

    public record SummaryCardSpecimen() implements BranchComponent<SummaryCardSpecimenModule>, NeedKeyboard {
        @Override public String summary() { return "The house's summary card in action: a title, a tag, a summary and an action, opened by the pointer or by the keys handed on; its size and aspect set by number."; }
        @Override public List<KeyBinding> keys() { return KeyBinding.each("the card's action, where it has the focus", Key.ENTER, Key.SPACE); }
    }

    @Override
    public ImportsFor<SummaryCardSpecimenModule> imports() {
        return ImportsFor.<SummaryCardSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new Elements.CardBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_row()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<SummaryCardSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new SummaryCardSpecimen())); }
}
