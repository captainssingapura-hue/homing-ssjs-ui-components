package hue.captains.singapura.js.homing.ui.menu;

import hue.captains.singapura.js.homing.component.taxonomy.ImplForBranch;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.ui.elements.CheckMenuItemModule;
import hue.captains.singapura.js.homing.ui.elements.PlainMenuItemModule;
import hue.captains.singapura.js.homing.ui.elements.SeparatorModule;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;

import java.util.List;

/**
 * What the three levels of a context menu share (owner, 23 Oct): a composed component on its own branch, its rows made
 * once from its part of the kind's tree - the house's menu items, plain or checked or not - the separators between its
 * sections, the menu below each row that opens rows; its cursor, the pause before rows open, the keys, its picks by the
 * row. Never made on its own: each level is. Stamped with the three levels' headers (RFC 0071, D19).
 */
public record MenuModule() implements DomModule<MenuModule>, ImplForBranch<HouseBranches.Menu> {

    /** The base: {@code class XMenu extends Menu}; {@code hold}, {@code bind}, {@code key}, {@code contains}, {@code mount}, {@code picks}. */
    public record Menu() implements Exportable._Class<MenuModule> {}

    public static final MenuModule INSTANCE = new MenuModule();

    @Override
    public ImportsFor<MenuModule> imports() {
        return ImportsFor.<MenuModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MenuBehaviorModule.MenuBehavior()), MenuBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PlainMenuItemModule.PlainMenuItem()), PlainMenuItemModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CheckMenuItemModule.CheckMenuItem()), CheckMenuItemModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SeparatorModule.Separator()), SeparatorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MenuStyles.cm_frame(), new MenuStyles.cm_frame_static(), new MenuStyles.cm_item_hidden(),
                        new MenuStyles.cm_separator()), MenuStyles.INSTANCE))
                .add(new ModuleImports<>(List.of(new MenuGeometryModule.MenuGeometry()), MenuGeometryModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MenuTreeModule.MenuTree()), MenuTreeModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<MenuModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new Menu()));
    }
}
