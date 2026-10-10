package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomySource;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseMarks;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/**
 * The house's tabbed panes, as this module adds them to the house's taxonomy: the panes of
 * many tabs and of one, a tab whole and the chip that names it, the strip they sit in and where a
 * dragged tab would land, the thumbnails of the panes and the ways to open a tab. In the tree if and
 * only if this module is on the classpath.
 */
public record HousePanes() implements TaxonomyExtension {

    public static final HousePanes INSTANCE = new HousePanes();

    @Override public Class<? extends TaxonomySource> extending() { return HouseTaxonomy.class; }

    /** Every component this module declares. */
    @Override public List<Component<?>> components() {
        return List.of(Tab.INSTANCE, PaneThumbs.INSTANCE, AddTab.INSTANCE, TabOpener.INSTANCE,
                       TabPicker.INSTANCE, MultiTabPane.INSTANCE, SingleTabPane.INSTANCE, TabPane.INSTANCE,
                       TabStrip.INSTANCE, DropMark.INSTANCE);
    }

    /** One tab of a strip: what a pane holds, picked. Its label and its icon slot are its own. */
    public record Tab() implements Component<HouseBranches.Control> {
        public static final Tab INSTANCE = new Tab();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Control parent() { return HouseBranches.Control.INSTANCE; }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.SIZE, ExtentAxis.ASPECT); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.Icon.INSTANCE).as(HouseSaying.Keys.INSTANCE).one(),
                           DSL.part(HouseControls.CloseButton.INSTANCE).as(HouseDoing.Close.INSTANCE).optional());
        }
    }

    /** The thumbnails of the panes one may open. */
    public record PaneThumbs() implements Component<HouseBranches.Container> {
        public static final PaneThumbs INSTANCE = new PaneThumbs();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.PaneThumb.INSTANCE).as(HouseDoing.Choice.INSTANCE).any());
        }
    }

    /** The way to add a tab: where, off a picture of the panes; what, off the kinds; how it arrives; and go. */
    public record AddTab() implements Component<HouseBranches.Container> {
        public static final AddTab INSTANCE = new AddTab();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(PaneThumbs.INSTANCE).as(HouseDoing.Where.INSTANCE).one(),
                           DSL.part(HouseControls.Select.INSTANCE).as(HouseDoing.What.INSTANCE).one(),
                           DSL.part(HouseControls.Select.INSTANCE).as(HouseDoing.How.INSTANCE).optional(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Go.INSTANCE).one());
        }
    }

    /** What an empty pane offers to open. */
    public record TabOpener() implements Component<HouseBranches.Container> {
        public static final TabOpener INSTANCE = new TabOpener();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Prompt.INSTANCE).one(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Choice.INSTANCE).any());
        }
    }

    /** The list of a pane's tabs, to pick from. */
    public record TabPicker() implements Component<HouseBranches.Container> {
        public static final TabPicker INSTANCE = new TabPicker();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Prompt.INSTANCE).one(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Choice.INSTANCE).any(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Cancel.INSTANCE).one());
        }
    }

    /** A pane of many tabs: its strip, its picker, a note when it holds none. It holds its pages. */
    public record MultiTabPane() implements Component<HouseBranches.Pane> {
        public static final MultiTabPane INSTANCE = new MultiTabPane();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Pane parent() { return HouseBranches.Pane.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(TabStrip.INSTANCE).as(HouseShaping.Tabs.INSTANCE).one(),
                           DSL.part(TabPicker.INSTANCE).as(HouseDoing.Picker.INSTANCE).optional(),
                           DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Note.INSTANCE).optional());
        }
    }

    /** A pane of one tab: its strip, and the one page it holds. */
    public record SingleTabPane() implements Component<HouseBranches.Pane> {
        public static final SingleTabPane INSTANCE = new SingleTabPane();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Pane parent() { return HouseBranches.Pane.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(TabStrip.INSTANCE).as(HouseShaping.Tabs.INSTANCE).one());
        }
    }

    /** One open tab, whole: the chip that names it on a strip. Its panel and its widget are its own. */
    public record TabPane() implements Component<HouseBranches.Pane> {
        public static final TabPane INSTANCE = new TabPane();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Pane parent() { return HouseBranches.Pane.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(Tab.INSTANCE).as(HouseShaping.Chip.INSTANCE).one());
        }
    }

    /** A pane's tabs in a row, with a way to add one, to close the pane, a count of the hidden, and where a dragged tab would land. */
    public record TabStrip() implements Component<HouseBranches.Bar> {
        public static final TabStrip INSTANCE = new TabStrip();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.IconButton.INSTANCE).as(HouseDoing.Add.INSTANCE).optional(),
                           DSL.part(HouseText.Pill.INSTANCE).as(HouseSaying.Count.INSTANCE).one(),
                           DSL.part(HouseControls.CloseButton.INSTANCE).as(HouseDoing.Close.INSTANCE).optional(),
                           DSL.part(DropMark.INSTANCE).as(HouseSaying.Landing.INSTANCE).optional());
        }
    }

    /** Where a dragged thing would land. */
    public record DropMark() implements Component<HouseBranches.Mark> {
        public static final DropMark INSTANCE = new DropMark();
        @Override public HouseBranches.Mark parent() { return HouseBranches.Mark.INSTANCE; }
    }
}
