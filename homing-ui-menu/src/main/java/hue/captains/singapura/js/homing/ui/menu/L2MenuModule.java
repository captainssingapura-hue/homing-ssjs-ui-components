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
 * {@code L2Menu}, level 2, the last (owner, 23 Oct): the rows a row of a level-1 menu leads to, only ever its part,
 * beside its row while open; its rows open none.
 */
public record L2MenuModule() implements DomModule<L2MenuModule>, ImplFor<HouseContainers.L2Menu> {

    /** The class. */
    public record L2Menu() implements BranchComponent<L2MenuModule> {
        @Override public String summary() { return "The rows a row of a level-1 menu leads to, beside it: the last level, only ever a part."; }
    }

    public static final L2MenuModule INSTANCE = new L2MenuModule();

    @Override
    public ImportsFor<L2MenuModule> imports() {
        return ImportsFor.<L2MenuModule>builder()
                .add(new ModuleImports<>(List.of(new MenuModule.Menu()), MenuModule.INSTANCE))
                // its rows and its lines, made by the base: imported where its leaf is
                .add(new ModuleImports<>(List.of(new PlainMenuItemModule.PlainMenuItem()), PlainMenuItemModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CheckMenuItemModule.CheckMenuItem()), CheckMenuItemModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SeparatorModule.Separator()), SeparatorModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<L2MenuModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new L2Menu()));
    }
}
