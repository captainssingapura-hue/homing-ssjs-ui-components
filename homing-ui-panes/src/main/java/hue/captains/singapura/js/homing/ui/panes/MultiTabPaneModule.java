package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.ui.elements.MarkBehaviorModule;
import hue.captains.singapura.js.homing.ui.elements.SimpleComponentModule;
import hue.captains.singapura.js.homing.component.taxonomy.ImplFor;
import hue.captains.singapura.js.homing.ui.elements.CaptionModule;
import hue.captains.singapura.js.homing.ui.taxonomy.HousePanes;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.Key;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.Modifier;
import hue.captains.singapura.js.homing.component.keyboard.NeedKeyboard;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.menu.NeedContextMenu;
import hue.captains.singapura.js.homing.ui.menu.tree.ContextMenuKind;

import java.util.List;
import java.util.Set;

/**
 * {@code MultiTabPane}, a pane of tabs: its bar over its slot. A tab is where a widget is shown (owner, 22 Oct): its
 * caller puts tabs in and takes them out, one at a time - {@code add(tab)}, {@code remove(id)} - and mounts in each tab's
 * own otherwise empty pane a widget's root, which the widget alone manages, its branch of the page grafted -
 * {@code mount(id, root, party?)}; the tab in use's pane shown, the others hidden, never detached. The bar is its own:
 * its caller hears the order of its tabs ({@code TabOrder}) once a drag has changed it, and its plus's presses, passed
 * on as its {@code TabAddControl}. It manages no tab in or out - its caller does (owner, 22 Oct). Its keys and its
 * menus come as they are ported; what it declares of them below waits for that.
 */
public record MultiTabPaneModule() implements DomModule<MultiTabPaneModule>, ImplFor<HousePanes.MultiTabPane> {

    /** The class. */
    public record MultiTabPane() implements BranchComponent<MultiTabPaneModule>, NeedContextMenu, NeedKeyboard {
        @Override public String summary() { return "Tabs in a strip over one panel each; a press selects, a drag reorders along the rail, a right-click offers the tab's menu."; }
        /** The tab menu: opened on a chip when the pane is given a steward ({@code menus}); the site serving the pane serves the kind. */
        @Override public Set<ContextMenuKind<?>> required() { return Set.of(TabMenu.INSTANCE, SplitMenu.INSTANCE); }
        /** The container's own, while the pane holds the keys: the tabs walked, reordered and detached; the widget entered; the pane yielded; the menu. */
        @Override public List<KeyBinding> keys() {
            return List.of(
                    KeyBinding.of(Key.ARROW_LEFT, "the active tab moves to the previous, at once"), KeyBinding.of(Key.ARROW_RIGHT, "the active tab moves to the next, at once"),
                    KeyBinding.of(Key.HOME, "the first tab"), KeyBinding.of(Key.END, "the last tab"),
                    KeyBinding.of(Key.ARROW_LEFT, Modifier.SHIFT, "the active tab moves one slot left along the rail, staying active"),
                    KeyBinding.of(Key.ARROW_RIGHT, Modifier.SHIFT, "the active tab moves one slot right along the rail, staying active"),
                    KeyBinding.of(Key.ARROW_DOWN, Modifier.SHIFT, "the active tab asked to detach and float: DetachRequested, for a holder with a desk"),
                    KeyBinding.of(Key.ENTER, "the active tab's widget activates itself - a claim; the pane never claims for it"),
                    KeyBinding.of(Key.ESCAPE, "taken and kept: the dock is where Escape stops, so nothing overshoots out of the room"),
                    KeyBinding.of(Key.CONTEXT_MENU, "the active tab's menu"), KeyBinding.of(Key.F10, Modifier.SHIFT, "the active tab's menu"),
                    // BrowserKeys, the other scheme a pane may be given. Declared here because the map is a PAGE's, and a
                    // reader of it wants every key the component may answer, not only the ones today's pane was built with.
                    KeyBinding.of(Key.TAB, Modifier.CTRL, "browser scheme: the next tab, wrapping"),
                    KeyBinding.of(Key.TAB, java.util.Set.of(Modifier.CTRL, Modifier.SHIFT), "browser scheme: the previous tab, wrapping"),
                    KeyBinding.of(Key.PAGE_DOWN, Modifier.CTRL, "browser scheme: the next tab"),
                    KeyBinding.of(Key.PAGE_UP, Modifier.CTRL, "browser scheme: the previous tab"),
                    KeyBinding.of(Key.ARROW_RIGHT, java.util.Set.of(Modifier.CTRL, Modifier.SHIFT), "browser scheme: the next tab, by a chord a browser does not keep for itself"),
                    KeyBinding.of(Key.ARROW_LEFT, java.util.Set.of(Modifier.CTRL, Modifier.SHIFT), "browser scheme: the previous tab, by a chord a browser does not keep for itself"));
        }
    }

    public static final MultiTabPaneModule INSTANCE = new MultiTabPaneModule();

    @Override
    public ImportsFor<MultiTabPaneModule> imports() {
        return ImportsFor.<MultiTabPaneModule>builder()
                .add(new ModuleImports<>(List.of(new SimpleComponentModule.SimpleComponent()), SimpleComponentModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new MarkBehaviorModule.MarkBehavior()), MarkBehaviorModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new TabStripModule.TabStrip()), TabStripModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new CaptionModule.Caption()), CaptionModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneStyles.mtp_tab_content(), new PaneStyles.mtp_tab_content_hidden(), new PaneStyles.mtp_pane(), new PaneStyles.mtp_content(), new PaneStyles.mtp_empty()), PaneStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<MultiTabPaneModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new MultiTabPane()));
    }
}
