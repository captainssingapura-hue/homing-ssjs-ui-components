package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.taxonomy.ImplForBranch;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.icons.IconModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;

import java.util.List;

/**
 * What the two kinds of a menu's item share (owner, 23 Oct): a composed component on its own {@code <div role="menuitem">},
 * its leading place with its own icon in it, its name, its hint; usable or off, where its menu's cursor is - never an off
 * one - and its picks. Never made on its own: each kind is, with its own fixed type, binding and rooms. Stamped with both
 * kinds' headers (RFC 0071, D19), the rooms of one name left to each kind.
 */
public record MenuItemModule() implements DomModule<MenuItemModule>, ImplForBranch<HouseBranches.MenuItem> {

    /** The base: {@code class XMenuItem extends MenuItem}; {@code label}, {@code hint}, {@code enabled}, {@code pointed}, {@code picks}. */
    public record MenuItem() implements Exportable._Class<MenuItemModule> {}

    public static final MenuItemModule INSTANCE = new MenuItemModule();

    @Override
    public ImportsFor<MenuItemModule> imports() {
        return ImportsFor.<MenuItemModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ItemBehaviorModule.ItemBehavior()), ItemBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption()), CaptionModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ElementStyles.el_menu_item(), new ElementStyles.el_menu_item_label(),
                        new ElementStyles.el_menu_item_hint(), new ElementStyles.el_menu_item_lead()), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<MenuItemModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new MenuItem()));
    }
}
