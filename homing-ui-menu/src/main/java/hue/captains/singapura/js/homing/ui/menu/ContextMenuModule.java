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
 * {@code ContextMenu}, level 0 (owner, 23 Oct): one kind's instance, opened where it is asked - its rows made once from the
 * kind's tree, a level-1 menu beside each row that opens rows; bound to an object while shown; shown at a point in its
 * steward's layer, given the keys while open. The rest is the levels' base.
 */
public record ContextMenuModule() implements DomModule<ContextMenuModule>, ImplFor<HouseContainers.ContextMenu> {

    /** The class. */
    public record ContextMenu() implements BranchComponent<ContextMenuModule> {
        @Override public String summary() { return "One kind's menu, opened where it is asked: its rows, and the rows beside each that opens rows; three levels at most."; }
    }

    public static final ContextMenuModule INSTANCE = new ContextMenuModule();

    @Override
    public ImportsFor<ContextMenuModule> imports() {
        return ImportsFor.<ContextMenuModule>builder()
                .add(new ModuleImports<>(List.of(new MenuModule.Menu()), MenuModule.INSTANCE))
                // its rows and its lines, made by the base: imported where its leaf is
                .add(new ModuleImports<>(List.of(new PlainMenuItemModule.PlainMenuItem()), PlainMenuItemModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CheckMenuItemModule.CheckMenuItem()), CheckMenuItemModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SeparatorModule.Separator()), SeparatorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new L1MenuModule.L1Menu()), L1MenuModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MenuGeometryModule.MenuGeometry()), MenuGeometryModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ContextMenuModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ContextMenu()));
    }
}
