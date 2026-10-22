package hue.captains.singapura.js.homing.ui.menu;

import hue.captains.singapura.js.homing.ui.look.IconsModule;
import hue.captains.singapura.js.homing.ui.icons.IconModule;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.elements.MenuItemModule;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.LookModule;
import hue.captains.singapura.js.homing.ui.elements.SeparatorModule;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;

import java.util.List;

/**
 * {@code ContextMenu}, one kind's instance: the frame, its rows and the
 * submenu frames of its submenu rows, built once on the branch given and
 * kept; bound to an object while shown, to none after; the cursor by the
 * geometry; a submenu beside its row. The steward makes one per kind at the
 * kind's first open. A primitive, owning the DOM on its branch.
 */
public record ContextMenuModule() implements DomModule<ContextMenuModule>, ImplFor<HouseContainers.ContextMenu> {

    /** The class. */
    public record ContextMenu() implements BranchComponent<ContextMenuModule> {
        @Override public String summary() { return "One kind's instance: its tree of frames, bound to an object while open; three levels at most."; }
    }

    public static final ContextMenuModule INSTANCE = new ContextMenuModule();

    @Override
    public ImportsFor<ContextMenuModule> imports() {
        return ImportsFor.<ContextMenuModule>builder()
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconsModule.ICON()), IconsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.STATE()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MenuItemModule.MenuItem()), MenuItemModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SeparatorModule.Separator()), SeparatorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new LookModule.Look()), LookModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new MenuStyles.cm_frame(),
                        new MenuStyles.cm_frame_static(),
                        new MenuStyles.cm_item_hidden(),
                        new MenuStyles.cm_separator()
                ), MenuStyles.INSTANCE))
                .add(new ModuleImports<>(List.of(new MenuGeometryModule.MenuGeometry()), MenuGeometryModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MenuTreeModule.MenuTree()), MenuTreeModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ContextMenuModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ContextMenu()));
    }
}
