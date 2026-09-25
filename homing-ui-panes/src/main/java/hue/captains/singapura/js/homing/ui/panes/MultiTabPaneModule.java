package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.FocusPartyModule;
import hue.captains.singapura.js.homing.component.keyboard.Key;
import hue.captains.singapura.js.homing.component.keyboard.KeyBinding;
import hue.captains.singapura.js.homing.component.keyboard.Modifier;
import hue.captains.singapura.js.homing.component.keyboard.focusParty;
import hue.captains.singapura.js.homing.component.keyboard.KeysModule;
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
 * {@code MultiTabPane}, one pane of tabs. {@code new MultiTabPane(branch,
 * {host, …})} builds a strip of chips over one content area on the
 * sub-branch the caller made for it, appends it to the host, and is a HOST
 * of a desk's tab-panes (RFC 0066 E3, appendix "tab-panes"): {@code take},
 * {@code letGo}, {@code removeTab} (a close), {@code switchTab},
 * {@code moveTab}, the readers, {@code dispose}. A branch component.
 *
 * <p>A tab is a {@link TabPaneModule.TabPane}: its chip and its pane are its
 * own, minted once under its desk, and placed here as they are — held, never
 * owned. The law: a tab's widget is logically focusable — a member of the
 * dock's branch of the focus party, exposing its membership as
 * {@code focus}, and answering {@code activate()}; what it contains
 * natively is its own, encapsulated; {@code take} refuses a tab-pane whose
 * widget is not, and adopts its membership from wherever it was. The pane
 * is a member holding the dock's branch; its keys, while it holds them, are
 * the container's own — the tabs walked, reordered and detached, the widget
 * entered, the pane yielded — and a chip takes no native focus. The pane
 * never calls {@code setActive}: that is the holder's, told through
 * {@code TabActivated}.</p>
 *
 * <p>Every mutation the pane makes is one {@link PaneEvent} on one sink,
 * {@code onEvent(ev)}: a frozen object tagged by kind whose fields are the
 * record's components, built by {@link PaneEventsModule} — removed, moved on
 * its rail, activated, add and detach requested; an arrival and a move from
 * host to host are the desk's. The shape is data, so an event goes into a
 * log or a checkpoint as it is. The strip, its chips and the drag that
 * reorders are {@link TabStrip}'s.</p>
 */
public record MultiTabPaneModule() implements DomModule<MultiTabPaneModule> {

    /** The class. */
    public record MultiTabPane() implements BranchComponent<MultiTabPaneModule>, NeedContextMenu, NeedKeyboard {
        @Override public String summary() { return "Tabs in a strip over one panel each; a press selects, a drag reorders along the rail, a right-click offers the tab's menu."; }
        /** The tab menu: opened on a chip when the pane is given a steward ({@code menus}); the site serving the pane serves the kind. */
        @Override public Set<ContextMenuKind<?>> required() { return Set.of(TabMenu.INSTANCE); }
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
                .add(new ModuleImports<>(List.of(new TabStripModule.TabStrip()), TabStripModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneKeysModule.PaneKeys(), new PaneKeysModule.PaneSchemes()), PaneKeysModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneMenusModule.PaneMenus()), PaneMenusModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneTabsModule.PaneTabs()), PaneTabsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new KeysModule.Keys()), KeysModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new focusParty()), FocusPartyModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneEventsModule.PaneEvents()), PaneEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PaneStyles.mtp_pane(),
                        new PaneStyles.mtp_content(),
                        new PaneStyles.mtp_tab_content_hidden(),
                        new PaneStyles.mtp_empty(),
                        new PaneStyles.mtp_dock_target()
                ), PaneStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<MultiTabPaneModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new MultiTabPane()));
    }
}
