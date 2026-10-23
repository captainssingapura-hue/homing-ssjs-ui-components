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
 * A menu's check item (owner, 23 Oct): one choice that is checked or not - its own icon, or none, and its check in its
 * leading place, the check shown in place of the icon while it is checked (option a); its hint. It opens no rows. Its
 * presses while usable are counted as its {@code PickControl}: whether a pick checks it, its menu decides.
 */
public record CheckMenuItemModule() implements DomModule<CheckMenuItemModule>, ImplFor<HouseItems.CheckMenuItem> {

    /** {@code new CheckMenuItem(branch, new FixedCheckMenuItem(leading?, hint?), place?)}; {@code checked}, and the base's. */
    public record CheckMenuItem() implements BranchComponent<CheckMenuItemModule> {
        @Override public String summary() { return "new CheckMenuItem(branch, new FixedCheckMenuItem(leading?, hint?), place?); checked, and the base's."; }
    }

    public static final CheckMenuItemModule INSTANCE = new CheckMenuItemModule();

    @Override
    public ImportsFor<CheckMenuItemModule> imports() {
        return ImportsFor.<CheckMenuItemModule>builder()
                .add(new ModuleImports<>(List.of(new MenuItemModule.MenuItem()), MenuItemModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new IconModule.Icon()), IconModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption()), CaptionModule.INSTANCE))   // its hint, made by the base: imported where its leaf is
                .add(new ModuleImports<>(List.of(new ElementStyles.el_menu_item_off()), ElementStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<CheckMenuItemModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new CheckMenuItem()));
    }
}
