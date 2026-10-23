package hue.captains.singapura.js.homing.ui.menu;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.CheckMenuItemModule;
import hue.captains.singapura.js.homing.ui.elements.PlainMenuItemModule;
import hue.captains.singapura.js.homing.ui.elements.SeparatorModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

import java.util.List;

/**
 * {@code L1Menu}, level 1 (owner, 23 Oct): the rows a row of a context menu leads to, only ever its part, beside its row
 * while open; a level-2 menu beside each of its rows that opens rows.
 */
public record L1MenuModule() implements DomModule<L1MenuModule>, ImplFor<HouseContainers.L1Menu> {

    /** The class. */
    public record L1Menu() implements BranchComponent<L1MenuModule> {
        @Override public String summary() { return "The rows a row of a context menu leads to, beside it: only ever a part."; }
    }

    public static final L1MenuModule INSTANCE = new L1MenuModule();

    @Override
    public ImportsFor<L1MenuModule> imports() {
        return ImportsFor.<L1MenuModule>builder()
                .add(new ModuleImports<>(List.of(new MenuModule.Menu()), MenuModule.INSTANCE))
                // its rows and its lines, made by the base: imported where its leaf is
                .add(new ModuleImports<>(List.of(new PlainMenuItemModule.PlainMenuItem()), PlainMenuItemModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CheckMenuItemModule.CheckMenuItem()), CheckMenuItemModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SeparatorModule.Separator()), SeparatorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new L2MenuModule.L2Menu()), L2MenuModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<L1MenuModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new L1Menu()));
    }
}
