package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Kind;
import hue.captains.singapura.js.homing.component.taxonomy.Root;

/**
 * The house's kinds: how its components are classified, from the root down. Abstract, every one -
 * never realized, never worn on its own; the components are the leaves under them.
 */
public final class HouseKinds {

    private HouseKinds() {}

    /** What a user operates: buttons, links, tabs, sliders and their handles. */
    public record Control() implements Kind<Root> {
        public static final Control INSTANCE = new Control();
        @Override public Root parent() { return Root.INSTANCE; }
    }

    /** A control that does one thing when pressed, in one of the looks a button has. */
    public record Button() implements Kind<Control> {
        public static final Button INSTANCE = new Button();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** A control that is grabbed and moved: a knob, a grip, a divider. */
    public record Handle() implements Kind<Control> {
        public static final Handle INSTANCE = new Handle();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** One of many alike in a list or a menu. */
    public record Item() implements Kind<Root> {
        public static final Item INSTANCE = new Item();
        @Override public Root parent() { return Root.INSTANCE; }
    }

    /** What holds other components and gives them their place. */
    public record Container() implements Kind<Root> {
        public static final Container INSTANCE = new Container();
        @Override public Root parent() { return Root.INSTANCE; }
    }

    /** A container with a frame of its own, holding content. */
    public record Pane() implements Kind<Container> {
        public static final Pane INSTANCE = new Pane();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A container of choices that opens and closes. */
    public record Menu() implements Kind<Container> {
        public static final Menu INSTANCE = new Menu();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A container stacked over the page. */
    public record Layer() implements Kind<Container> {
        public static final Layer INSTANCE = new Layer();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A container that divides its room between others. */
    public record Split() implements Kind<Container> {
        public static final Split INSTANCE = new Split();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A thin container along an edge: a strip, a bar of actions. */
    public record Bar() implements Kind<Container> {
        public static final Bar INSTANCE = new Bar();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A container that edits one setting. */
    public record Field() implements Kind<Container> {
        public static final Field INSTANCE = new Field();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A container that shows something whole: a picture, a set of settings, a monitor. */
    public record View() implements Kind<Container> {
        public static final View INSTANCE = new View();
        @Override public Container parent() { return Container.INSTANCE; }
    }

    /** A stretch of a container's room, with no frame of its own. */
    public record Region() implements Kind<Root> {
        public static final Region INSTANCE = new Region();
        @Override public Root parent() { return Root.INSTANCE; }
    }

    /** Words, and the numbers read as words. */
    public record Text() implements Kind<Root> {
        public static final Text INSTANCE = new Text();
        @Override public Root parent() { return Root.INSTANCE; }
    }

    /** A line something moves along, or fills. */
    public record Track() implements Kind<Root> {
        public static final Track INSTANCE = new Track();
        @Override public Root parent() { return Root.INSTANCE; }
    }

    /** A small sign: an icon, a tick, a lamp, an indicator. */
    public record Mark() implements Kind<Root> {
        public static final Mark INSTANCE = new Mark();
        @Override public Root parent() { return Root.INSTANCE; }
    }
}
