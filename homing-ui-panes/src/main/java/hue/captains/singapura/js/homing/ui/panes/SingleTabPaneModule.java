package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.BranchComponent;
import hue.captains.singapura.js.homing.component.keyboard.FocusPartyModule;
import hue.captains.singapura.js.homing.component.keyboard.focusParty;
import hue.captains.singapura.js.homing.component.keyboard.KeysModule;
import hue.captains.singapura.js.homing.core.DomModule;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.ImportsFor;
import hue.captains.singapura.js.homing.core.ModuleImports;
import hue.captains.singapura.js.homing.ui.menu.NeedContextMenu;
import hue.captains.singapura.js.homing.ui.menu.tree.ContextMenuKind;

import java.util.List;
import java.util.Set;

/**
 * {@code SingleTabPane}, a pane of one tab: the vehicle a desk floats a tab
 * in, where {@link MultiTabPaneModule.MultiTabPane} is the dock. It holds a
 * {@link TabPaneModule.TabPane} exactly as the dock does — placed by
 * {@link PaneTabsModule.PaneTabs}, its menu by {@link PaneMenusModule.PaneMenus},
 * the law by {@link PaneKeysModule.PaneKeys} — and takes no second one. Its
 * bar is the chip on the dock's strip, and the whole bar is its window's
 * handle. It has no keys of its own: a press in it lands in the tab.
 */
public record SingleTabPaneModule() implements DomModule<SingleTabPaneModule> {

    /** The class. */
    public record SingleTabPane() implements BranchComponent<SingleTabPaneModule>, NeedContextMenu {
        @Override public String summary() { return "One tab in transit: its chip is the bar, the whole bar moves the window, and a press lands in the tab."; }
        /** The tab menu, the dock's own: opened on the chip when the pane is given a steward ({@code menus}). */
        @Override public Set<ContextMenuKind<?>> required() { return Set.of(TabMenu.INSTANCE); }
    }

    public static final SingleTabPaneModule INSTANCE = new SingleTabPaneModule();

    @Override
    public ImportsFor<SingleTabPaneModule> imports() {
        return ImportsFor.<SingleTabPaneModule>builder()
                .add(new ModuleImports<>(List.of(new PaneKeysModule.PaneKeys()), PaneKeysModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneMenusModule.PaneMenus()), PaneMenusModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneTabsModule.PaneTabs()), PaneTabsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new KeysModule.Keys()), KeysModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new focusParty()), FocusPartyModule.INSTANCE))
                .add(new ModuleImports<>(List.of(new PaneEventsModule.PaneEvents()), PaneEventsModule.INSTANCE))
                .add(new ModuleImports<>(List.of(
                        new PaneStyles.mtp_pane(),
                        new PaneStyles.mtp_strip(),
                        new PaneStyles.mtp_strip_current(),
                        new PaneStyles.mtp_chip_mark_on(),
                        new PaneStyles.mtp_content(),
                        new PaneStyles.mtp_tab_content_hidden()
                ), PaneStyles.INSTANCE))
                .build();
    }

    @Override
    public ExportsOf<SingleTabPaneModule> exports() {
        return new ExportsOf<>(INSTANCE, List.of(new SingleTabPane()));
    }
}
