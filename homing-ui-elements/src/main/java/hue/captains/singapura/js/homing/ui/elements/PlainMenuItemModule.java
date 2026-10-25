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
 * A menu's plain item (owner, 23 Oct): one choice that does something, or opens the rows it leads to - its own icon, its
 * hint, its way into its rows, each fixed for its life; never checked. Its presses while usable are counted as its
 * {@code PickControl}, handled by none: what a pick does, its menu decides.
 */
public record PlainMenuItemModule() implements DomModule<PlainMenuItemModule>, ImplFor<HouseItems.PlainMenuItem> {

    /** {@code new PlainMenuItem(branch, new FixedPlainMenuItem(leading?, hint?, disclose?), place?)}; {@code expanded}, and the base's. */
    public record PlainMenuItem() implements BranchComponent<PlainMenuItemModule> {
        @Override public String summary() { return "new PlainMenuItem(branch, new FixedPlainMenuItem(leading?, hint?, disclose?), place?); expanded, and the base's."; }
    }

    public static final PlainMenuItemModule INSTANCE = new PlainMenuItemModule();

    @Override
    public ImportsFor<PlainMenuItemModule> imports() {
        return ImportsFor.<PlainMenuItemModule>builder()
                .add(new ModuleImports<>(List.of(new MenuItemModule.MenuItem()), MenuItemModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption()), CaptionModule.INSTANCE))   // its hint, made by the base: imported where its leaf is
                .add(new ModuleImports<>(List.of(new ElementStyles.el_menu_item_disclose()), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<PlainMenuItemModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new PlainMenuItem()));
    }
}
