package hue.captains.singapura.js.homing.ui.specimens;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.KeyboardStewardModule;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.Elements;
import hue.captains.singapura.js.homing.ui.menu.ContextMenuStewardModule;

import java.util.List;

/** {@code ContextMenuStewardSpecimen}: the house's keeper of context menus in action - two kinds, one menu active at a time. */
public record ContextMenuStewardSpecimenModule() implements DomModule<ContextMenuStewardSpecimenModule> {

    public static final ContextMenuStewardSpecimenModule INSTANCE = new ContextMenuStewardSpecimenModule();

    public record ContextMenuStewardSpecimen() implements BranchComponent<ContextMenuStewardSpecimenModule> {
        @Override public String summary() { return "The house's keeper of context menus in action: a file's kind and a folder's, one instance each, and one menu active - opening one closes the other - with what is open, and for what, said when asked."; }
    }

    @Override
    public ImportsFor<ContextMenuStewardSpecimenModule> imports() {
        return ImportsFor.<ContextMenuStewardSpecimenModule>builder()
                .add(new ModuleImports<>(List.of(new ContextMenuStewardModule.ContextMenuSteward()), ContextMenuStewardModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new KeyboardStewardModule.KeyboardStewardInstance()), KeyboardStewardModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new Elements.ButtonBuilder()), Elements.INSTANCE))
                .add(new ModuleImports<>(List.of(new SpecimenStyles.sp_stage(), new SpecimenStyles.sp_row(), new SpecimenStyles.sp_host(),
                        new SpecimenStyles.sp_text()), SpecimenStyles.INSTANCE))
                .build();
    }

    @Override public ExportsOf<ContextMenuStewardSpecimenModule> exports() { return new ExportsOf<>(INSTANCE, List.of(new ContextMenuStewardSpecimen())); }
}
