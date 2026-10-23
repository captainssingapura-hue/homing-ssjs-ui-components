package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.icons.IconModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseItems;

import java.util.List;

/**
 * The menu's item: one choice of a menu - its name, its own icon leading it, chosen once, a check for a row that can be
 * checked, shown in its place while it is (owner, 23 Oct: option a), its hint a caption, its way into rows of its own - a
 * composed component on its own {@code <div role="menuitem">}, ported (RFC 0071). Its presses while usable are counted
 * as its {@code PickControl}, handled by none: what a pick does, its menu decides.
 */
public record MenuItemModule() implements DomModule<MenuItemModule>, ImplFor<HouseItems.MenuItem> {

    /** {@code new MenuItem(branch, new FixedMenuItem(leading?, check?, hint?, disclose?), place?)}; {@code onChange(StatePickControl | StatePointed | ..., listener)}; {@code label}, {@code hint}, {@code enabled}, {@code pointed}, {@code checked}, {@code expanded}, {@code picks}. */
    public record MenuItem() implements BranchComponent<MenuItemModule> {
        @Override public String summary() { return "One choice of a menu: its name, its icon or its check, its hint and its way into rows of its own; off, or where the menu's cursor is; its picks counted."; }
    }

    public static final MenuItemModule INSTANCE = new MenuItemModule();

    @Override
    public ImportsFor<MenuItemModule> imports() {
        return ImportsFor.<MenuItemModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ItemBehaviorModule.ItemBehavior()), ItemBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption()), CaptionModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ElementStyles.el_menu_item(), new ElementStyles.el_menu_item_label(),
                        new ElementStyles.el_menu_item_hint(), new ElementStyles.el_menu_item_disclose(), new ElementStyles.el_menu_item_lead(),
                        new ElementStyles.el_menu_item_off()), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<MenuItemModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new MenuItem()));
    }
}
