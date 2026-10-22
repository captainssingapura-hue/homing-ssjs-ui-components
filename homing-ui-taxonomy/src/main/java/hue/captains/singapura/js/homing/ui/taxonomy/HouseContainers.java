package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Cardinality;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Leaf;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.WithRoles;

import hue.captains.singapura.js.homing.component.taxonomy.Room;

import java.util.List;

/**
 * The house's common containers: what holds other components and gives them their place - cards
 * and panels, a dialog and the pane it floats, a menu, a split, the bars. Each declares
 * its parts as slots over the house's role catalogue ({@link HouseRoleCatalogue}): the role, the
 * independent component that plays it, how many. What a container holds but does not make - the
 * caller's content, a pane's widget - is no part of it.
 */
public final class HouseContainers {

    private HouseContainers() {}

    // ── cards ────────────────────────────────────────────────────────────

    /** The basic card: a frame the caller fills, no part fixed - raised, of its own measure, still under the hand. */
    public record PlainCard() implements Leaf<HouseBranches.Card> {
        public static final PlainCard INSTANCE = new PlainCard();
        @Override public HouseBranches.Card parent() { return HouseBranches.Card.INSTANCE; }
    }

    /** A card that is a button: pressed - by the hand, or Enter or Space - it does its one thing. What is in it is its caller's, as in the plain card. */
    public record ClickableCard() implements Leaf<HouseBranches.Card> {
        public static final ClickableCard INSTANCE = new ClickableCard();
        @Override public HouseBranches.Card parent() { return HouseBranches.Card.INSTANCE; }
        /**
         * How many times it was pressed - its one thing; the pointer over it; the focus on it; where the page's keys are,
         * held by the steward on its root (D12); the hand down on it.
         */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Clicks.INSTANCE, HouseStates.Hovered.INSTANCE, HouseStates.Focused.INSTANCE,
                HouseStates.Keyboard.INSTANCE, HouseStates.Down.INSTANCE); }
    }

    // ── views ────────────────────────────────────────────────────────────

    /**
     * Sums one thing up, laid out whole: a title, a tag beside it, a line of summary, a way to the
     * thing. No frame of its own: shown on whatever holds it - a card, a pane, a dialog. Its texts
     * sized together.
     */
    public record SummaryView() implements Composite<HouseBranches.View> {
        public static final SummaryView INSTANCE = new SummaryView();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.SIZE); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Heading4.INSTANCE).as(HouseSaying.Title.INSTANCE).one(),
                           DSL.part(HouseText.Badge.INSTANCE).as(HouseSaying.Tag.INSTANCE).optional(Cardinality.DYNAMIC),
                           DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Summary.INSTANCE).optional(Cardinality.DYNAMIC),
                           DSL.part(HouseControls.Link.INSTANCE).as(HouseDoing.Open.INSTANCE).optional(Cardinality.DYNAMIC));
        }
    }

    // ── containers ───────────────────────────────────────────────────────

    /** A container that shows nothing of itself: a layout's umbrella, or a sub-container at its positions - its look its spacing alone. */
    public record PlainContainer() implements Leaf<HouseBranches.Surface> {
        public static final PlainContainer INSTANCE = new PlainContainer();
        @Override public HouseBranches.Surface parent() { return HouseBranches.Surface.INSTANCE; }
    }

    /** One cell of the split grid, the sub-container at each of its positions: what it holds draws, its room its own. */
    public record GridCell() implements Leaf<HouseBranches.Surface> {
        public static final GridCell INSTANCE = new GridCell();
        @Override public HouseBranches.Surface parent() { return HouseBranches.Surface.INSTANCE; }
    }

    /**
     * The layouts' styling (owner, 21 Oct): a component the design alone sees. A layout is no component - strictly
     * style-agnostic, one of the flat list of layouts (HouseLayouts) - so this owns what layouts place, for their design:
     * the umbrella containers, the sub-containers and the splitter, each cast by its parts and designed as any part is.
     * It has no casting and no look of its own, and no module implements it.
     */
    public record LayoutStyling() implements hue.captains.singapura.js.homing.component.taxonomy.LayoutStyling<HouseBranches.Container> {
        public static final LayoutStyling INSTANCE = new LayoutStyling();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(PlainContainer.INSTANCE, PlainCard.INSTANCE, ClickableCard.INSTANCE).as(HouseShaping.Umbrella.INSTANCE).any(),
                           DSL.part(PlainContainer.INSTANCE, GridCell.INSTANCE).as(HouseShaping.SubContainer.INSTANCE).any(),
                           DSL.part(HouseMarks.BoundaryLine.INSTANCE).as(HouseDoing.Splitter.INSTANCE).any(),
                           DSL.part(HouseMarks.BoundaryLine.INSTANCE).as(HouseShaping.Seam.INSTANCE).any());
        }
    }

    /** A floating pane over a scrim, with a bar of actions. Its title and close are the pane's, its content the caller's. */
    public record Dialog() implements Composite<HouseBranches.Pane> {
        public static final Dialog INSTANCE = new Dialog();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Pane parent() { return HouseBranches.Pane.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(Scrim.INSTANCE).as(HouseShaping.Veil.INSTANCE).optional(Cardinality.DYNAMIC),
                           DSL.part(FloatingPane.INSTANCE).as(HouseShaping.Window.INSTANCE).one(),
                           DSL.part(ActionBar.INSTANCE).as(HouseShaping.Actions.INSTANCE).optional());
        }
    }

    // ── panes ────────────────────────────────────────────────────────────

    /** A pane that floats: a head with a title and a close. Its glyph slot, its body and its grip are its own. */
    public record FloatingPane() implements Composite<HouseBranches.Pane> {
        public static final FloatingPane INSTANCE = new FloatingPane();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Pane parent() { return HouseBranches.Pane.INSTANCE; }
        /**
         * The current one - its caller's to say; lit under the hand; in the hand, moved by its head; and the closes asked of
         * it, its head's close's passed on, handled by none: what a close is, its caller decides (D12; owner, 23 Oct).
         */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Current.INSTANCE, HouseStates.Highlighted.INSTANCE, HouseStates.Dragging.INSTANCE,
                HouseStates.FloatCloseControl.INSTANCE); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(TitleBar.INSTANCE).as(HouseShaping.Head.INSTANCE).optional());
        }
        /** Its body: the room what it shows is mounted in by its caller - its own, no part (23 Oct). */
        @Override public List<Room> rooms() { return List.of(new Room("body")); }
    }

    /** The layer floating panes are stacked on: a desk of its own, recessed, or laid over what it is on. */
    public record FloatLayer() implements Composite<HouseBranches.Layer> {
        public static final FloatLayer INSTANCE = new FloatLayer();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Layer parent() { return HouseBranches.Layer.INSTANCE; }
        /** Where the page's keys are, held by the steward on its root (RFC 0067, D12). */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Keyboard.INSTANCE); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(FloatingPane.INSTANCE).as(HouseShaping.Float.INSTANCE).any(Cardinality.DYNAMIC));
        }
    }

    /** Heads a pane that floats: its title and its close, and where the hand takes it to move it. */
    public record TitleBar() implements Composite<HouseBranches.Bar> {
        public static final TitleBar INSTANCE = new TitleBar();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        /**
         * In the hand while its pane is moved by it (D12); held by the hand, as it has it - what its pane hears and moves
         * by; and the closes asked of its pane, its close's clicks passed on, handled by none (owner, 23 Oct).
         */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Dragging.INSTANCE, HouseStates.Held.INSTANCE, HouseStates.FloatCloseControl.INSTANCE); }
        /** What names its pane - its title, or the chip of the one tab its pane holds (23 Oct) - and its close. */
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Label.INSTANCE).as(HouseSaying.Title.INSTANCE).optional(),
                           DSL.part(HouseControls.Tab.INSTANCE).as(HouseShaping.Chip.INSTANCE).optional(),
                           DSL.part(HouseControls.CloseButton.INSTANCE).as(HouseDoing.Close.INSTANCE).optional());
        }
    }

    // ── menus and layers ─────────────────────────────────────────────────

    /** A menu opened where it is asked: its items, and the lines between groups of them. Submenus are its own frames. */
    public record ContextMenu() implements Composite<HouseBranches.Menu> {
        public static final ContextMenu INSTANCE = new ContextMenu();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Menu parent() { return HouseBranches.Menu.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseItems.MenuItem.INSTANCE).as(HouseDoing.Choice.INSTANCE).atLeast(1),
                           DSL.part(HouseMarks.Separator.INSTANCE).as(HouseShaping.Break.INSTANCE).any());
        }
    }

    /** The veil over the page behind a dialog. */
    public record Scrim() implements Leaf<HouseBranches.Layer> {
        public static final Scrim INSTANCE = new Scrim();
        @Override public HouseBranches.Layer parent() { return HouseBranches.Layer.INSTANCE; }
        /** Held by the hand: its press, and its release - what its owner makes of it is its owner's. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Held.INSTANCE); }
    }

    // ── bars ─────────────────────────────────────────────────────────────

    /** A strip along an edge, revealed by its lip. Its controls are the caller's. */
    public record EdgeStrip() implements Composite<HouseBranches.Bar> {
        public static final EdgeStrip INSTANCE = new EdgeStrip();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.Lip.INSTANCE).as(HouseShaping.Reveal.INSTANCE).one());
        }
    }

    /**
     * The surface a page stands on: its ground, the ink and the face of what is written on it, and its
     * scrollbar. One to a document, worn on its own element: everything on the page stands on it.
     */
    public record Ground() implements Leaf<HouseBranches.Surface> {
        public static final Ground INSTANCE = new Ground();
        @Override public HouseBranches.Surface parent() { return HouseBranches.Surface.INSTANCE; }
    }

    /**
     * A document table's cell that heads its column, or its row: a container, room for the words that say what its
     * column holds - never the table's lines, which are the table's, nor the look of its words, which wear their own.
     */
    public record HeaderCell() implements Composite<HouseBranches.Surface> {
        public static final HeaderCell INSTANCE = new HeaderCell();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Surface parent() { return HouseBranches.Surface.INSTANCE; }
        /** Its words, a text cell naming the column or the row it heads (D42). */
        @Override public List<Slot<?>> parts() { return List.of(DSL.part(HouseText.TextCell.INSTANCE).as(HouseSaying.Title.INSTANCE).optional()); }
    }

    /**
     * A document table's cell that holds one fact: a container, room for what it holds - never the table's lines, which
     * are the table's, nor the look of its words, which wear their own.
     */
    public record DataCell() implements Composite<HouseBranches.Surface> {
        public static final DataCell INSTANCE = new DataCell();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Surface parent() { return HouseBranches.Surface.INSTANCE; }
        /** Its words, a text cell stating the fact it holds (D42). */
        @Override public List<Slot<?>> parts() { return List.of(DSL.part(HouseText.TextCell.INSTANCE).as(HouseSaying.Value.INSTANCE).optional()); }
    }

    /** Heads a site's pages: its brand, the way to where the user is, and what its owner hands it at its end, in the ink written on it. */
    public record SiteBar() implements Composite<HouseBranches.Bar> {
        public static final SiteBar INSTANCE = new SiteBar();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.Brand.INSTANCE).as(HouseDoing.Home.INSTANCE).one(),
                           DSL.part(Trail.INSTANCE).as(HouseDoing.Path.INSTANCE).one());
        }
    }

    /** A row of actions, at the foot of a dialog or a view. */
    public record ActionBar() implements Composite<HouseBranches.Bar> {
        public static final ActionBar INSTANCE = new ActionBar();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.PrimaryButton.INSTANCE).as(HouseDoing.Go.INSTANCE).optional(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Action.INSTANCE).atLeast(1));
        }
    }

    /** The way back, crumb by crumb. */
    public record Trail() implements Composite<HouseBranches.Bar>, WithRoles<HouseRoleSets.Going> {
        public static final Trail INSTANCE = new Trail();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        @Override public HouseRoleSets.Going roleSet() { return HouseRoleSets.Going.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.Crumb.INSTANCE).as(HouseDoing.Step.INSTANCE).any(Cardinality.DYNAMIC),
                           DSL.part(HouseMarks.Delimiter.INSTANCE).as(HouseShaping.Break.INSTANCE).any(Cardinality.DYNAMIC));
        }
    }

    /** A pane's tabs in a row, with a way to add one, to close the pane, a count of the hidden, and where a dragged tab would land. */
    public record TabStrip() implements Composite<HouseBranches.Bar> {
        public static final TabStrip INSTANCE = new TabStrip();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        /**
         * Lit while its pane is the one being worked in (D12); the adds asked of it - its plus pressed, passed on and
         * handled by none: what is added is its owner's caller's to decide; which of its tabs is in use; the order its
         * tabs stand in; the closes asked of each, its chips' crosses' passed on, handled by none; and the tab the hand
         * carries off it, torn off - its owner's to take on (23 Oct). Its chips are its own, and so are their states (owner,
         * 22 Oct).
         */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Current.INSTANCE, HouseStates.TabAddControl.INSTANCE,
                HouseStates.ActiveTab.INSTANCE, HouseStates.TabOrder.INSTANCE, HouseStates.TabCloseControl.INSTANCE, HouseStates.TabCarry.INSTANCE); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.Tab.INSTANCE).as(HouseShaping.Chip.INSTANCE).any(Cardinality.DYNAMIC),
                           DSL.part(HouseControls.IconButton.INSTANCE).as(HouseShaping.VoidRole.INSTANCE).one(),   // its one plus, a regular button: its icon says it adds (21, 22 Oct)
                           DSL.part(HouseText.Pill.INSTANCE).as(HouseSaying.Count.INSTANCE).one(),
                           DSL.part(HouseMarks.DropMark.INSTANCE).as(HouseSaying.Landing.INSTANCE).optional(Cardinality.DYNAMIC));
        }
        /** Its tail: the room at its end its count sits in - its own. A floating pane's close is its own, no bar's (22 Oct). */
        @Override public List<Room> rooms() { return List.of(new Room("tail")); }
    }
}
