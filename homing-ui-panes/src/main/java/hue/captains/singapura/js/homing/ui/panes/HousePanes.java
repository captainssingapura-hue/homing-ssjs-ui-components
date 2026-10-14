package hue.captains.singapura.js.homing.ui.panes;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomyExtension;
import hue.captains.singapura.js.homing.component.taxonomy.TaxonomySource;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseStates;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseControls;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseDoing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseSaying;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseShaping;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseTaxonomy;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseText;

import java.util.List;

/**
 * The house's tabbed panes, as this module adds them to the house's taxonomy: the panes of
 * many tabs and of one, a tab whole, the thumbnails of the panes and the ways to open a tab. The tab,
 * the strip it sits in and where a dragged tab would land are the common tree's, so the styles can
 * answer them. In the tree if and
 * only if this module is on the classpath.
 */
public record HousePanes() implements TaxonomyExtension {

    public static final HousePanes INSTANCE = new HousePanes();

    @Override public Class<? extends TaxonomySource> extending() { return HouseTaxonomy.class; }

    /** Every component this module declares. */
    @Override public List<Component<?>> components() {
        return List.of(PaneThumbs.INSTANCE, AddTab.INSTANCE, TabOpener.INSTANCE,
                       TabPicker.INSTANCE, MultiTabPane.INSTANCE, SingleTabPane.INSTANCE, TabPane.INSTANCE);
    }

    /** The thumbnails of the panes one may open. */
    public record PaneThumbs() implements Component<HouseBranches.Chooser> {
        public static final PaneThumbs INSTANCE = new PaneThumbs();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Chooser parent() { return HouseBranches.Chooser.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.PaneThumb.INSTANCE).as(HouseDoing.Choice.INSTANCE).any());
        }
    }

    /** The way to add a tab: where, off a picture of the panes; what, off the kinds; how it arrives; and go. */
    public record AddTab() implements Component<HouseBranches.Chooser> {
        public static final AddTab INSTANCE = new AddTab();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Chooser parent() { return HouseBranches.Chooser.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(PaneThumbs.INSTANCE).as(HouseDoing.Where.INSTANCE).one(),
                           DSL.part(HouseControls.Select.INSTANCE).as(HouseDoing.What.INSTANCE).one(),
                           DSL.part(HouseControls.Select.INSTANCE).as(HouseDoing.How.INSTANCE).optional(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Go.INSTANCE).one());
        }
    }

    /** What an empty pane offers to open. */
    public record TabOpener() implements Component<HouseBranches.Chooser> {
        public static final TabOpener INSTANCE = new TabOpener();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Chooser parent() { return HouseBranches.Chooser.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Prompt.INSTANCE).one(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Choice.INSTANCE).any());
        }
    }

    /** The list of a pane's tabs, to pick from. */
    public record TabPicker() implements Component<HouseBranches.Chooser> {
        public static final TabPicker INSTANCE = new TabPicker();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Chooser parent() { return HouseBranches.Chooser.INSTANCE; }
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
        /** Where the page's keys are, held by the steward on its root (RFC 0067, D12). */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Keyboard.INSTANCE); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseContainers.TabStrip.INSTANCE).as(HouseShaping.Tabs.INSTANCE).one(),
                           DSL.part(TabPicker.INSTANCE).as(HouseDoing.Picker.INSTANCE).optional(),
                           DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Note.INSTANCE).optional());
        }
    }

    /** A pane of one tab: its strip, and the one page it holds. */
    public record SingleTabPane() implements Component<HouseBranches.Pane> {
        public static final SingleTabPane INSTANCE = new SingleTabPane();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Pane parent() { return HouseBranches.Pane.INSTANCE; }
        /** Where the page's keys are, held by the steward on its root (RFC 0067, D12). */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Keyboard.INSTANCE); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseContainers.TabStrip.INSTANCE).as(HouseShaping.Tabs.INSTANCE).one());
        }
    }

    /** One open tab, whole: the chip that names it on a strip. Its panel and its widget are its own. */
    public record TabPane() implements Component<HouseBranches.Pane> {
        public static final TabPane INSTANCE = new TabPane();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Pane parent() { return HouseBranches.Pane.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.Tab.INSTANCE).as(HouseShaping.Chip.INSTANCE).one());
        }
    }

}
