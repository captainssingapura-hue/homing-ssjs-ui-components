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
    void theKindIsTab_detachThenClose_withTheirIcons() {
        assertEquals("tab", TabMenu.INSTANCE.kind());
        assertEquals(List.of(), MenuTrees.validate(TabMenu.INSTANCE));
        assertEquals(List.of("detach", "close"), MenuTrees.rows(TabMenu.INSTANCE).stream().map(r -> r.id()).toList());
        assertEquals(HouseIcons.DetachIcon.INSTANCE, TabMenu.Detach.INSTANCE.icon());
        assertEquals(HouseIcons.CloseIcon.INSTANCE, TabMenu.Close.INSTANCE.icon());
        assertEquals(1, TabMenu.Close.INSTANCE.section(), "a divider before Close");
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
        // the pane ported a step at a time (22 Oct): it opens no menu yet - its menus come with the context menu's port
        String menus = String.join("\n", ResourceReader.INSTANCE.getStringsFromResource("homing/js/hue/captains/singapura/js/homing/ui/panes/PaneMenusModule.js"));
        assertTrue(menus.contains("pane._menus.open(kind,"), "and PaneMenus opens the kind it was handed, never one of its own");
        // a site that serves the pane's crate holds the kind, listing nothing
        assertEquals(java.util.Set.of("tab", "split"), java.util.Set.copyOf(ContextMenuRegistry.requiredBy(List.of(UiPanesCrate.INSTANCE)).kinds().stream().map(k -> k.kind()).toList()));
        assertEquals(List.of(), ContextMenuRegistry.validate(List.of(UiPanesCrate.INSTANCE)));
    }
}
