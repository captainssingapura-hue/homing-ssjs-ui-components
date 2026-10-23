package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.menu.tree.ContextMenuKind;
import hue.captains.singapura.js.homing.ui.menu.tree.M1_Node;

import java.util.List;

/**
 * The menu of a tab: what a right-press on a chip offers — close it (owner,
 * 23 Oct: Detach, a move of the tab to another pane, waits on moving tabs
 * between panes). Owned here, by the crate whose component
 * opens it ({@link MultiTabPaneModule.MultiTabPane} names it as a need), and
 * derived into any site's registry that serves the pane. The object bound
 * while it is open is the pane's menu and the tab it was opened for; a pick
 * is the pane's - Close asks the tab's close, as its cross does.
 */
public record TabMenu() implements ContextMenuKind<TabMenu> {

    public static final TabMenu INSTANCE = new TabMenu();

    @Override public List<? extends M1_Node<TabMenu, ?>> children() { return List.of(Close.INSTANCE); }


    /** Close this tab: its close asked, as its cross asks it - whether it closes, the pane's caller decides. */
    public record Close() implements M1_Node<TabMenu, Close> {
        public static final Close INSTANCE = new Close();
        @Override public TabMenu parent() { return TabMenu.INSTANCE; }
        @Override public String label() { return "Close"; }
        @Override public Component<HouseBranches.Icon> icon() { return HouseIcons.CloseIcon.INSTANCE; }
    }
}
