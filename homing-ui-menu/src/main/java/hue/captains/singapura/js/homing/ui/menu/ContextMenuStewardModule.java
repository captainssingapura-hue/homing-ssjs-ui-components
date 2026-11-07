package hue.captains.singapura.js.homing.ui.menu;

import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMenus;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.Key;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;

import java.util.List;

/**
 * {@code ContextMenuSteward}, one per page: the registry of kinds — a Java
 * {@link ContextMenuRegistry}'s stamped {@code MENUS}, held, or kinds defined
 * through its builder — one context menu per kind minted at the kind's first
 * open, its layer its own element, and the one menu active, which is the
 * fundamental invariant. Lazy: nothing listened to while no menu is open. The
 * press that closes a menu is swallowed whole. Ported largely unchanged (RFC
 * 0071, owner 23 Oct): a composed component on its own branch, its kinds and
 * its keys handed in after it is made, its picks heard through the menu's count.
 */
public record ContextMenuStewardModule() implements DomModule<ContextMenuStewardModule>, ImplFor<HouseMenus.ContextMenuSteward> {

    /** The class. */
    public record ContextMenuSteward() implements BranchComponent<ContextMenuStewardModule>, NeedKeyboard {
        @Override public String summary() { return "One per page: the kinds, one instance each, the layer, and the one menu active."; }
        /** Claimed on open, given back on close, closed when taken: the open menu's keys. */
        @Override public List<KeyBinding> keys() {
            return List.of(
                new KeyBinding(Key.ESCAPE, "the menu closed"), new KeyBinding(Key.TAB, "held: the focus stays in the menu"),
                new KeyBinding(Key.ARROW_DOWN, "the next row"), new KeyBinding(Key.ARROW_UP, "the previous row"),
                new KeyBinding(Key.HOME, "the first row"), new KeyBinding(Key.END, "the last row"),
                new KeyBinding(Key.ARROW_RIGHT, "into the row's submenu"), new KeyBinding(Key.ARROW_LEFT, "back out of a submenu"),
                new KeyBinding(Key.ENTER, "the row activated"), new KeyBinding(Key.SPACE, "the row activated"));
        }
    }

    public static final ContextMenuStewardModule INSTANCE = new ContextMenuStewardModule();

    @Override
    public ImportsFor<ContextMenuStewardModule> imports() {
        return new ImportsFor._Builder<ContextMenuStewardModule>()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new ContextMenuStewardBehaviorModule.ContextMenuStewardBehavior()), ContextMenuStewardBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MenuStyles.cm_layer(), new MenuStyles.cm_specimen()), MenuStyles.INSTANCE))
                .add(new ModuleImports<>(List.of(new ContextMenuModule.ContextMenu()), ContextMenuModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MenuEventsModule.MenuEvents()), MenuEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MenuTreeModule.MenuTree()), MenuTreeModule.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<ContextMenuStewardModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new ContextMenuSteward()));
    }
}
