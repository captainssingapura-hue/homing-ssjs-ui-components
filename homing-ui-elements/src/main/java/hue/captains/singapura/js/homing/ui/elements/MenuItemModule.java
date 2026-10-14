package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.icons.IconModule;
import hue.captains.singapura.js.homing.ui.look.CastingsModule;
import hue.captains.singapura.js.homing.ui.look.StatesAndEventsModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

import java.util.List;

/** The menu's item: one choice of a menu - its glyph an icon, its name, its hint a caption, its way into a submenu - a simple component on its own {@code <div role="menuitem">}. */
public record MenuItemModule() implements DomModule<MenuItemModule>, ImplFor<HouseItems.MenuItem> {

    /** {@code new MenuItem(branch, { label, icon, hint, expanded, enabled, pointed, checked }, role)}; {@code root}, {@code mark}; its setters. */
    public record MenuItem() implements BranchComponent<MenuItemModule> {
        @Override public String summary() { return "One choice of a menu: its glyph, its name, its hint and its way into a submenu; off, or where the menu's cursor is."; }
    }

    public static final MenuItemModule INSTANCE = new MenuItemModule();

    @Override
    public ImportsFor<MenuItemModule> imports() {
        return ImportsFor.<MenuItemModule>builder()
                .add(new ModuleImports<>(List.of(new StatesAndEventsModule.STATE(), new StatesAndEventsModule.EVENT()), StatesAndEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CastingsModule.ROLE()), CastingsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ItemBehaviorModule.ItemBehavior()), ItemBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption()), CaptionModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ElementStyles.el_menu_item(), new ElementStyles.el_menu_item_label(),
                        new ElementStyles.el_menu_item_hint(), new ElementStyles.el_menu_item_disclose()), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<MenuItemModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new MenuItem()));
    }
}
