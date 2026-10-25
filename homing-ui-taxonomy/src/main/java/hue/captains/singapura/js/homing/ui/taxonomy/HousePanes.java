package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Cardinality;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.FixedValue;
import hue.captains.singapura.js.homing.component.taxonomy.Room;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.WithRoles;

import java.util.List;

/**
 * The house's tabbed panes: the panes of many tabs and of one, a tab whole, the thumbnails of the panes and the way to
 * add a tab. What a new tab is to hold is its caller's to decide - a workspace, an app - by a widget of its own, the
 * pane dumb (owner, 22 Oct): no picker or opener is a component. The tab, the strip it sits in and where a dragged tab would land are the common tree's, so the styles can
 * answer them. Defined here, with the house's other components, whether or not its implementation is on the classpath;
 * implemented - and drawn by Base - in {@code homing-ui-panes} (RFC 0067, appendix The Style Jars, D40).
 */
public final class HousePanes {

    private HousePanes() {}

    /** The thumbnails of the panes one may open: each a cell of the picture it draws, the house's thumbnail on its own. */
    public record PaneThumbs() implements Composite<HouseBranches.Chooser>, WithRoles<HouseRoleSets.Choosing> {
        public static final PaneThumbs INSTANCE = new PaneThumbs();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Chooser parent() { return HouseBranches.Chooser.INSTANCE; }
        @Override public HouseRoleSets.Choosing roleSet() { return HouseRoleSets.Choosing.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.PaneThumb.INSTANCE).as(HouseShaping.Cell.INSTANCE).any(Cardinality.DYNAMIC));
        }
    }

    /** The way to add a tab: where, off a picture of the panes; what, off the kinds; how it arrives; and go. */
    public record AddTab() implements Composite<HouseBranches.Chooser> {
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



    /** A pane of many tabs: its strip, a note when it holds none. It holds its pages; what a new one is to hold is its caller's. */
    public record MultiTabPane() implements Composite<HouseBranches.Pane> {
        public static final MultiTabPane INSTANCE = new MultiTabPane();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Pane parent() { return HouseBranches.Pane.INSTANCE; }
        /**
         * Where the page's keys are, held by the steward on its root (RFC 0067, D12); the adds asked of it, its bar's
         * passed on, handled by none: it manages no tab in or out - its caller does; the order its tabs stand in, its
         * bar's passed on; the closes asked of each of its tabs, its bar's passed on, handled by none: what a close is,
         * its caller decides; and the detaches of each, its own - its virtual detach manager's (23 Oct). Which tab is in use,
         * and the tab the hand carries, are its own (owner, 22 Oct). Which of its tabs is in the hand in a floater of its,
         * and the tabs of others' it caught on its bar, told to its caller, who moves them over (owner, 23 Oct).
         */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Keyboard.INSTANCE, HouseStates.TabAddControl.INSTANCE,
                HouseStates.TabOrder.INSTANCE, HouseStates.TabCloseControl.INSTANCE, HouseStates.TabDetachControl.INSTANCE,
                HouseStates.TabInTransit.INSTANCE, HouseStates.TabCaughtControl.INSTANCE); }
        /** Where its floaters are drawn, immutable for its life: an element holding it and the panes it gives tabs to - none, itself (owner, 23 Oct). */
        @Override public List<FixedValue> fixedValues() { return List.of(new FixedValue.Element("desk")); }
        /** Its slot: where what it shows is mounted by its caller, one at a time - its own element, no part (22 Oct). */
        @Override public List<Room> rooms() { return List.of(new Room("slot")); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseContainers.TabStrip.INSTANCE).as(HouseShaping.Tabs.INSTANCE).one(),
                           DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Note.INSTANCE).optional(),
                           // a floater for each tab torn off its bar - its own, made and dropped as the hand carries them (23 Oct)
                           DSL.part(HouseContainers.FloatingPane.INSTANCE).as(HouseShaping.Float.INSTANCE).any(Cardinality.DYNAMIC));
        }
    }

    /** A pane of one tab: its strip, and the one page it holds. */
    public record SingleTabPane() implements Composite<HouseBranches.Pane> {
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
    public record TabPane() implements Composite<HouseBranches.Pane> {
        public static final TabPane INSTANCE = new TabPane();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Pane parent() { return HouseBranches.Pane.INSTANCE; }
        /**
         * The clicks of its close: one button, as its caller has it, pressed by its chip's cross and by its menu's close
         * alike. It handles nothing: its caller closes the widget it lends its panel to, then the tab pane - which never
         * closes itself (owner, 22 Oct).
         */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Clicks.INSTANCE); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.Tab.INSTANCE).as(HouseShaping.Chip.INSTANCE).one());
        }
    }

}
