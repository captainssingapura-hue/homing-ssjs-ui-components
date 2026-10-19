package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

import java.util.List;

/** The choice's option: one choice of a setting, with a note on what it means - a simple component on its own {@code <button role="option">}, the one picked as its owner says. */
public record ChoiceOptionModule() implements DomModule<ChoiceOptionModule>, ImplFor<HouseItems.ChoiceOption> {

    /** {@code new ChoiceOption(branch, new FixedChoiceOption(), place?)}; {@code root}, {@code label}, {@code selected}; its setters. */
    public record ChoiceOption() implements BranchComponent<ChoiceOptionModule> {
        @Override public String summary() { return "One choice of a setting, with a note on what it means: the one picked, as its owner says."; }
    }

    public static final ChoiceOptionModule INSTANCE = new ChoiceOptionModule();

    @Override
    public ImportsFor<ChoiceOptionModule> imports() {
        return ImportsFor.<ChoiceOptionModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ItemBehaviorModule.ItemBehavior()), ItemBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption()), CaptionModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ElementStyles.el_choice_option(), new ElementStyles.el_choice_option_note()), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ChoiceOptionModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ChoiceOption()));
    }
}
