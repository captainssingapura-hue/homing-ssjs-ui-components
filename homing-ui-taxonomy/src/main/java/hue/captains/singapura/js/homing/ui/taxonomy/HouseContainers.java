package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;

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

    /** The basic card: a frame and a body the caller fills, no part fixed. The cases are built on it. */
    public record PlainCard() implements Component<HouseBranches.Card> {
        public static final PlainCard INSTANCE = new PlainCard();
        @Override public HouseBranches.Card parent() { return HouseBranches.Card.INSTANCE; }
    }

    /** A card that sums something up: a title, a tag beside it, a line of summary, a way to the thing. */
    public record SummaryCard() implements Component<HouseBranches.Card> {
        public static final SummaryCard INSTANCE = new SummaryCard();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Card parent() { return HouseBranches.Card.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Heading.INSTANCE).as(HouseSaying.Title.INSTANCE).one(),
                           DSL.part(HouseText.Badge.INSTANCE).as(HouseSaying.Tag.INSTANCE).optional(),
                           DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Summary.INSTANCE).optional(),
                           DSL.part(HouseControls.Link.INSTANCE).as(HouseDoing.Open.INSTANCE).optional());
        }
    }

    // ── containers ───────────────────────────────────────────────────────

    /** Sliders gathered under one header. */
    public record SliderGroup() implements Component<HouseBranches.Container> {
        public static final SliderGroup INSTANCE = new SliderGroup();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Heading.INSTANCE).as(HouseSaying.Title.INSTANCE).one(),
                           DSL.part(HouseControls.Slider.INSTANCE).as(HouseShaping.Member.INSTANCE).any());
        }
    }

    /** A panel that fills what holds it: a head with a title, and a body. Its controls are the caller's. */
    public record Panel() implements Component<HouseBranches.Container> {
        public static final Panel INSTANCE = new Panel();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseRegions.Section.INSTANCE).as(HouseShaping.Head.INSTANCE).optional(),
                           DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Title.INSTANCE).optional());
        }
    }

    /** A floating pane over a scrim, with a bar of actions. Its title and close are the pane's, its content the caller's. */
    public record Dialog() implements Component<HouseBranches.Container> {
        public static final Dialog INSTANCE = new Dialog();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(Scrim.INSTANCE).as(HouseShaping.Veil.INSTANCE).optional(),
                           DSL.part(FloatingPane.INSTANCE).as(HouseShaping.Window.INSTANCE).one(),
                           DSL.part(ActionBar.INSTANCE).as(HouseShaping.Actions.INSTANCE).optional());
        }
    }

    // ── panes ────────────────────────────────────────────────────────────

    /** A pane that floats: a head with a title and a close. Its glyph slot, its body and its grip are its own. */
    public record FloatingPane() implements Component<HouseBranches.Pane> {
        public static final FloatingPane INSTANCE = new FloatingPane();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Pane parent() { return HouseBranches.Pane.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseRegions.Section.INSTANCE).as(HouseShaping.Head.INSTANCE).optional(),
                           DSL.part(HouseText.Label.INSTANCE).as(HouseSaying.Title.INSTANCE).optional(),
                           DSL.part(HouseControls.CloseButton.INSTANCE).as(HouseDoing.Close.INSTANCE).optional());
        }
    }

    // ── menus and layers ─────────────────────────────────────────────────

    /** A menu opened where it is asked: its items, and the lines between groups of them. Submenus are its own frames. */
    public record ContextMenu() implements Component<HouseBranches.Menu> {
        public static final ContextMenu INSTANCE = new ContextMenu();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Menu parent() { return HouseBranches.Menu.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseItems.MenuItem.INSTANCE).as(HouseDoing.Choice.INSTANCE).atLeast(1),
                           DSL.part(HouseMarks.Separator.INSTANCE).as(HouseShaping.Break.INSTANCE).any());
        }
    }

    /** The veil over the page behind a dialog. */
    public record Scrim() implements Component<HouseBranches.Layer> {
        public static final Scrim INSTANCE = new Scrim();
        @Override public HouseBranches.Layer parent() { return HouseBranches.Layer.INSTANCE; }
        @Override public List<EventType<?>> events() { return List.of(HouseEvents.Press.INSTANCE); }
    }

    // ── bars ─────────────────────────────────────────────────────────────

    /** A strip along an edge, revealed by its lip. Its controls are the caller's. */
    public record EdgeStrip() implements Component<HouseBranches.Bar> {
        public static final EdgeStrip INSTANCE = new EdgeStrip();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseRegions.Section.INSTANCE).as(HouseShaping.Reveal.INSTANCE).one());
        }
    }

    /** A row of actions, at the foot of a dialog or a view. */
    public record ActionBar() implements Component<HouseBranches.Bar> {
        public static final ActionBar INSTANCE = new ActionBar();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.PrimaryButton.INSTANCE).as(HouseDoing.Go.INSTANCE).optional(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Action.INSTANCE).atLeast(1));
        }
    }

    /** The way back, crumb by crumb. */
    public record Trail() implements Component<HouseBranches.Bar> {
        public static final Trail INSTANCE = new Trail();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.Crumb.INSTANCE).as(HouseDoing.Step.INSTANCE).any(),
                           DSL.part(HouseMarks.Delimiter.INSTANCE).as(HouseShaping.Break.INSTANCE).any());
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
                           DSL.part(HouseMarks.DropMark.INSTANCE).as(HouseSaying.Landing.INSTANCE).optional());
        }
    }
}
