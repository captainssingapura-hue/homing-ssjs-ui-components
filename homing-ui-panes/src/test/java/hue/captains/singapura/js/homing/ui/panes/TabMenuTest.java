package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.core.util.ResourceReader;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseIcons;
import hue.captains.singapura.js.homing.ui.menu.ContextMenuRegistry;
import hue.captains.singapura.js.homing.ui.menu.tree.MenuTrees;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The tab menu: a kind this crate owns, named by the pane's declaration as
 * its need, and the one word the JS opens it by agreeing with the Java.
 */
class TabMenuTest {

    @Test
    void theKindIsTab_closeAlone_withItsIcon() {
        assertEquals("tab", TabMenu.INSTANCE.kind());
        assertEquals(List.of(), MenuTrees.validate(TabMenu.INSTANCE));
        assertEquals(List.of("close"), MenuTrees.rows(TabMenu.INSTANCE).stream().map(r -> r.id()).toList(),
                "Close alone (owner, 23 Oct): Detach, a move of the tab to another pane, waits on moving tabs between panes");
        assertEquals(HouseIcons.CloseIcon.INSTANCE, TabMenu.Close.INSTANCE.icon());
    }

    @Test
    void theSplitMenu_isSplit_partThenMergeThenClose() {
        assertEquals("split", SplitMenu.INSTANCE.kind());
        assertEquals(List.of(), MenuTrees.validate(SplitMenu.INSTANCE));
        assertEquals(List.of("beside", "below", "merge-left", "merge-right", "merge-up", "merge-down", "merge-into", "close"),
                MenuTrees.rows(SplitMenu.INSTANCE).stream().map(r -> r.id()).toList());
    }

    @Test
    void thePaneDeclaresTheNeed_andItsJsOpensTheSameKind() {
        assertEquals(java.util.Set.of(TabMenu.INSTANCE, SplitMenu.INSTANCE), new MultiTabPaneModule.MultiTabPane().required(),
                "a chip's menu, and its strip's own ground's - the room it sits in, answered by the desk's docking when it resides in a split grid");
        // the ported pane opens its tab menu through the steward it is handed (23 Oct): the same kind, by the same word
        String menus = String.join("\n", ResourceReader.INSTANCE.getStringsFromResource("homing/js/hue/captains/singapura/js/homing/ui/panes/MultiTabPaneMenuModule.js"));
        assertTrue(menus.contains("this._steward.open(\"tab\","), "and its menu opens the tab kind through the steward handed to the pane");
        // a site that serves the pane's crate holds the kind, listing nothing
        assertEquals(java.util.Set.of("tab", "split"), java.util.Set.copyOf(ContextMenuRegistry.requiredBy(List.of(UiPanesCrate.INSTANCE)).kinds().stream().map(k -> k.kind()).toList()));
        assertEquals(List.of(), ContextMenuRegistry.validate(List.of(UiPanesCrate.INSTANCE)));
    }
}
