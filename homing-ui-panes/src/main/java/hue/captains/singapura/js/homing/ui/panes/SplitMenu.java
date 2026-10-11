package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.taxonomy.Word;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseWords;
import hue.captains.singapura.js.homing.ui.menu.tree.ContextMenuKind;
import hue.captains.singapura.js.homing.ui.menu.tree.M1_Node;

import java.util.List;

/**
 * The menu of a region's own ground: what a right-click on the room a dock's
 * chips leave offers — part the region beside or below, merge it into the
 * region across a splitter of its own or into one chosen, or close it, its
 * tabs going where its room goes. Owned here, by the crate whose component
 * opens it ({@link MultiTabPaneModule.MultiTabPane} names it as a need, beside
 * the tab menu), answered by the desk's docking when the dock resides in a
 * split grid, and derived into any site's registry that serves the pane. The
 * object bound while it is open is the dock's {@code { pane, at }}.
 */
public record SplitMenu() implements ContextMenuKind<SplitMenu> {

    public static final SplitMenu INSTANCE = new SplitMenu();

    @Override public List<? extends M1_Node<SplitMenu, ?>> children() {
        return List.of(Beside.INSTANCE, Below.INSTANCE,
                       MergeLeft.INSTANCE, MergeRight.INSTANCE, MergeUp.INSTANCE, MergeDown.INSTANCE, MergeInto.INSTANCE,
                       Close.INSTANCE);
    }

    public record Beside() implements M1_Node<SplitMenu, Beside> {
        public static final Beside INSTANCE = new Beside();
        @Override public SplitMenu parent() { return SplitMenu.INSTANCE; }
        @Override public String label() { return "Split beside"; }
        @Override public Word<?> icon() { return PaneWords.Column.INSTANCE; }
        @Override public String hint() { return "a region of its own, to the right"; }
    }
    public record Below() implements M1_Node<SplitMenu, Below> {
        public static final Below INSTANCE = new Below();
        @Override public SplitMenu parent() { return SplitMenu.INSTANCE; }
        @Override public String label() { return "Split below"; }
        @Override public Word<?> icon() { return PaneWords.Row.INSTANCE; }
        @Override public String hint() { return "a region of its own, underneath"; }
    }
    public record MergeLeft() implements M1_Node<SplitMenu, MergeLeft> {
        public static final MergeLeft INSTANCE = new MergeLeft();
        @Override public SplitMenu parent() { return SplitMenu.INSTANCE; }
        @Override public String label() { return "Merge left"; }
        @Override public Word<?> icon() { return PaneWords.Merge.INSTANCE; }
        @Override public int section() { return 1; }
        @Override public String hint() { return "its tabs and its room to the pane beside it"; }
    }
    public record MergeRight() implements M1_Node<SplitMenu, MergeRight> {
        public static final MergeRight INSTANCE = new MergeRight();
        @Override public SplitMenu parent() { return SplitMenu.INSTANCE; }
        @Override public String label() { return "Merge right"; }
        @Override public Word<?> icon() { return PaneWords.Merge.INSTANCE; }
        @Override public int section() { return 1; }
        @Override public String hint() { return "its tabs and its room to the pane beside it"; }
    }
    public record MergeUp() implements M1_Node<SplitMenu, MergeUp> {
        public static final MergeUp INSTANCE = new MergeUp();
        @Override public SplitMenu parent() { return SplitMenu.INSTANCE; }
        @Override public String label() { return "Merge up"; }
        @Override public Word<?> icon() { return PaneWords.Merge.INSTANCE; }
        @Override public int section() { return 1; }
        @Override public String hint() { return "its tabs and its room to the pane above it"; }
    }
    public record MergeDown() implements M1_Node<SplitMenu, MergeDown> {
        public static final MergeDown INSTANCE = new MergeDown();
        @Override public SplitMenu parent() { return SplitMenu.INSTANCE; }
        @Override public String label() { return "Merge down"; }
        @Override public Word<?> icon() { return PaneWords.Merge.INSTANCE; }
        @Override public int section() { return 1; }
        @Override public String hint() { return "its tabs and its room to the pane under it"; }
    }
    /** Offered only when the holder can ask which region: the grid's {@code chooseRegion}. */
    public record MergeInto() implements M1_Node<SplitMenu, MergeInto> {
        public static final MergeInto INSTANCE = new MergeInto();
        @Override public SplitMenu parent() { return SplitMenu.INSTANCE; }
        @Override public String label() { return "Merge into…"; }
        @Override public Word<?> icon() { return PaneWords.Merge.INSTANCE; }
        @Override public int section() { return 1; }
        @Override public String hint() { return "choose the region its tabs go to"; }
    }
    public record Close() implements M1_Node<SplitMenu, Close> {
        public static final Close INSTANCE = new Close();
        @Override public SplitMenu parent() { return SplitMenu.INSTANCE; }
        @Override public String label() { return "Close this region"; }
        @Override public Word<?> icon() { return HouseWords.Close.INSTANCE; }
        @Override public int section() { return 2; }
        @Override public String hint() { return "its tabs go where its room goes"; }
    }
}
