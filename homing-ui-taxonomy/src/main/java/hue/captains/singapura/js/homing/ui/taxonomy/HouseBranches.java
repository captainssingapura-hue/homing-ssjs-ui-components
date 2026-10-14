package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.FamilyHead;
import hue.captains.singapura.js.homing.component.taxonomy.L1_ComponentBranch;
import hue.captains.singapura.js.homing.component.taxonomy.L2_ComponentBranch;
import hue.captains.singapura.js.homing.component.taxonomy.Root;

import java.util.List;

/**
 * The house's branches: how its components are classified, levelled from the root down - level 1
 * under the root, level 2 under those. Abstract, every one - never realized, never worn on its own;
 * the components are the leaves under them. What each means is in {@code meanings/.../HouseBranches.md};
 * the axes a branch declares, every leaf under it varies along. A branch that heads a family says so
 * ({@link FamilyHead}), at one level: its components' CSS is written in its family's file.
 */
public final class HouseBranches {

    private HouseBranches() {}

    /** What a user operates: buttons, links, tabs, sliders and their handles. */
    public record Control() implements L1_ComponentBranch<Root> {
        public static final Control INSTANCE = new Control();
        @Override public Root parent() { return Root.INSTANCE; }
    }

    /** A control that does one thing when pressed, in one of the looks a button has. */
    public record Button() implements L2_ComponentBranch<Control>, FamilyHead {
        public static final Button INSTANCE = new Button();
        @Override public Control parent() { return Control.INSTANCE; }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.SIZE); }
    }

    /** A control that is grabbed and moved: a knob, a grip. */
    public record Handle() implements L2_ComponentBranch<Control>, FamilyHead {
        public static final Handle INSTANCE = new Handle();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** A control by which the user sets a value: words, on or off, a value along a scale or from a list. */
    public record Input() implements L2_ComponentBranch<Control>, FamilyHead {
        public static final Input INSTANCE = new Input();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** A control that takes the user somewhere else: a link, a step of a trail back, the brand home. */
    public record Way() implements L2_ComponentBranch<Control>, FamilyHead {
        public static final Way INSTANCE = new Way();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** A control that picks one of several panes, or what a pane holds: a tab, a pane's thumbnail, a grid's mirror. */
    public record Selector() implements L2_ComponentBranch<Control>, FamilyHead {
        public static final Selector INSTANCE = new Selector();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** One of many alike in a list or a menu. */
    public record Item() implements L1_ComponentBranch<Root>, FamilyHead {
        public static final Item INSTANCE = new Item();
        @Override public Root parent() { return Root.INSTANCE; }
    }

    /** What holds other components and gives them their place. */
    public record Container() implements L1_ComponentBranch<Root> {
        public static final Container INSTANCE = new Container();
        @Override public Root parent() { return Root.INSTANCE; }
    }

    /** A raised box of its own measure: the plain one a caller fills, and the cases built on it. */
    public record Card() implements L2_ComponentBranch<Container>, FamilyHead {
        public static final Card INSTANCE = new Card();
        @Override public Container parent() { return Container.INSTANCE; }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.SIZE, ExtentAxis.ASPECT); }
    }

    /** A container with a frame of its own, holding content. */
    public record Pane() implements L2_ComponentBranch<Container>, FamilyHead {
        public static final Pane INSTANCE = new Pane();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A container of choices that opens and closes. */
    public record Menu() implements L2_ComponentBranch<Container>, FamilyHead {
        public static final Menu INSTANCE = new Menu();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A container stacked over the page. */
    public record Layer() implements L2_ComponentBranch<Container>, FamilyHead {
        public static final Layer INSTANCE = new Layer();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A container that divides its room between others. */
    public record Split() implements L2_ComponentBranch<Container>, FamilyHead {
        public static final Split INSTANCE = new Split();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A thin container along an edge: a strip, a bar of actions. */
    public record Bar() implements L2_ComponentBranch<Container>, FamilyHead {
        public static final Bar INSTANCE = new Bar();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A container that edits one setting. */
    public record Field() implements L2_ComponentBranch<Container>, FamilyHead {
        public static final Field INSTANCE = new Field();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A container that shows something whole: a picture, a set of settings, a monitor. */
    public record View() implements L2_ComponentBranch<Container>, FamilyHead {
        public static final View INSTANCE = new View();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A container that fills what holds it, with no frame of its own: the page's ground, a panel. */
    public record Surface() implements L2_ComponentBranch<Container>, FamilyHead {
        public static final Surface INSTANCE = new Surface();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A container that holds a set of controls as one: a group of sliders, a control panel. */
    public record Cluster() implements L2_ComponentBranch<Container>, FamilyHead {
        public static final Cluster INSTANCE = new Cluster();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A container that offers what to open or pick: the panes, the kinds of tab, a pane's tabs. */
    public record Chooser() implements L2_ComponentBranch<Container>, FamilyHead {
        public static final Chooser INSTANCE = new Chooser();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A stretch of a container's room, with no frame of its own. */
    public record Region() implements L1_ComponentBranch<Root>, FamilyHead {
        public static final Region INSTANCE = new Region();
        @Override public Root parent() { return Root.INSTANCE; }
    }

    /** Words, and the numbers read as words. */
    public record Text() implements L1_ComponentBranch<Root>, FamilyHead {
        public static final Text INSTANCE = new Text();
        @Override public Root parent() { return Root.INSTANCE; }
    }

    /** Words that say what a container holds, read first: a leaf for each level of a page's outline. */
    public record Heading() implements L2_ComponentBranch<Text> {
        public static final Heading INSTANCE = new Heading();
        @Override public Text parent() { return Text.INSTANCE; }
    }

    /** A line something moves along, or fills. */
    public record Track() implements L1_ComponentBranch<Root>, FamilyHead {
        public static final Track INSTANCE = new Track();
        @Override public Root parent() { return Root.INSTANCE; }
    }

    /** A small sign: an icon, a tick, a lamp, an indicator. */
    public record Mark() implements L1_ComponentBranch<Root>, FamilyHead {
        public static final Mark INSTANCE = new Mark();
        @Override public Root parent() { return Root.INSTANCE; }
    }
}
