package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;

/**
 * The house's containers: what holds other components and gives them their place - cards and
 * panels, panes and layers, splits and bars, the fields and views of the preferences. Their parts
 * are to be declared as slots over the house's role catalogue ({@link HouseRoleCatalogue}); the
 * first cut's roles nested in their owners are gone.
 */
public final class HouseContainers {

    private HouseContainers() {}

    // ── containers ───────────────────────────────────────────────────────

    /** A raised box of its own measure: a head with a title and a badge, a body, a foot with a link. */
    public record Card() implements Component<HouseBranches.Container> {
        public static final Card INSTANCE = new Card();
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
    }

    /** Sliders gathered under one header. */
    public record SliderGroup() implements Component<HouseBranches.Container> {
        public static final SliderGroup INSTANCE = new SliderGroup();
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
    }

    /** A panel that fills what holds it: a head with a title, and a body. */
    public record Panel() implements Component<HouseBranches.Container> {
        public static final Panel INSTANCE = new Panel();
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
    }

    /** A floating pane over a scrim, with a bar of actions. */
    public record Dialog() implements Component<HouseBranches.Container> {
        public static final Dialog INSTANCE = new Dialog();
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
    }

    /** The thumbnails of the panes one may open. */
    public record PaneThumbs() implements Component<HouseBranches.Container> {
        public static final PaneThumbs INSTANCE = new PaneThumbs();
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
    }

    /** The way to add a tab: a picker of pane thumbnails. */
    public record AddTab() implements Component<HouseBranches.Container> {
        public static final AddTab INSTANCE = new AddTab();
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
    }

    /** What an empty pane offers to open. */
    public record TabOpener() implements Component<HouseBranches.Container> {
        public static final TabOpener INSTANCE = new TabOpener();
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
    }

    /** The list of a pane's tabs, to pick from. */
    public record TabPicker() implements Component<HouseBranches.Container> {
        public static final TabPicker INSTANCE = new TabPicker();
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
    }

    /** The site's chrome: its brand, its trail, its preferences, and the page's room. */
    public record MpaChrome() implements Component<HouseBranches.Container> {
        public static final MpaChrome INSTANCE = new MpaChrome();
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
    }

    // ── panes ────────────────────────────────────────────────────────────

    /** A pane that floats: a head with a glyph, a title and a close, a grip, and a body. */
    public record FloatingPane() implements Component<HouseBranches.Pane> {
        public static final FloatingPane INSTANCE = new FloatingPane();
        @Override public HouseBranches.Pane parent() { return HouseBranches.Pane.INSTANCE; }
    }

    /** A pane of many tabs: its strip, its picker, its content, where a dragged tab would land. */
    public record MultiTabPane() implements Component<HouseBranches.Pane> {
        public static final MultiTabPane INSTANCE = new MultiTabPane();
        @Override public HouseBranches.Pane parent() { return HouseBranches.Pane.INSTANCE; }
    }

    /** A pane of one tab: its title and its content. */
    public record SingleTabPane() implements Component<HouseBranches.Pane> {
        public static final SingleTabPane INSTANCE = new SingleTabPane();
        @Override public HouseBranches.Pane parent() { return HouseBranches.Pane.INSTANCE; }
    }

    /** What one tab of a pane holds. */
    public record TabPane() implements Component<HouseBranches.Pane> {
        public static final TabPane INSTANCE = new TabPane();
        @Override public HouseBranches.Pane parent() { return HouseBranches.Pane.INSTANCE; }
    }

    // ── menus and layers ─────────────────────────────────────────────────

    /** A menu opened where it is asked: its items, and the lines between groups of them. */
    public record ContextMenu() implements Component<HouseBranches.Menu> {
        public static final ContextMenu INSTANCE = new ContextMenu();
        @Override public HouseBranches.Menu parent() { return HouseBranches.Menu.INSTANCE; }
    }

    /** The layer floating panes are stacked on. */
    public record FloatLayer() implements Component<HouseBranches.Layer> {
        public static final FloatLayer INSTANCE = new FloatLayer();
        @Override public HouseBranches.Layer parent() { return HouseBranches.Layer.INSTANCE; }
    }

    /** The veil over the page behind a dialog. */
    public record Scrim() implements Component<HouseBranches.Layer> {
        public static final Scrim INSTANCE = new Scrim();
        @Override public HouseBranches.Layer parent() { return HouseBranches.Layer.INSTANCE; }
    }

    /** The one keeper of the page's context menus. */
    public record ContextMenuSteward() implements Component<HouseBranches.Layer> {
        public static final ContextMenuSteward INSTANCE = new ContextMenuSteward();
        @Override public HouseBranches.Layer parent() { return HouseBranches.Layer.INSTANCE; }
    }

    // ── splits ───────────────────────────────────────────────────────────

    /** Two regions and the divider between them. */
    public record SplitPane() implements Component<HouseBranches.Split> {
        public static final SplitPane INSTANCE = new SplitPane();
        @Override public HouseBranches.Split parent() { return HouseBranches.Split.INSTANCE; }
    }

    /** A grid of cells split in both directions, its dividers between them. */
    public record SplitGrid() implements Component<HouseBranches.Split> {
        public static final SplitGrid INSTANCE = new SplitGrid();
        @Override public HouseBranches.Split parent() { return HouseBranches.Split.INSTANCE; }
    }

    /** A split grid's shape, drawn small. */
    public record SplitGridMirror() implements Component<HouseBranches.Split> {
        public static final SplitGridMirror INSTANCE = new SplitGridMirror();
        @Override public HouseBranches.Split parent() { return HouseBranches.Split.INSTANCE; }
    }

    /** A split grid whose cells are tabbed panes: the dock. */
    public record DockGrid() implements Component<HouseBranches.Split> {
        public static final DockGrid INSTANCE = new DockGrid();
        @Override public HouseBranches.Split parent() { return HouseBranches.Split.INSTANCE; }
    }

    // ── bars ─────────────────────────────────────────────────────────────

    /** A strip along an edge, revealed by its lip. */
    public record EdgeStrip() implements Component<HouseBranches.Bar> {
        public static final EdgeStrip INSTANCE = new EdgeStrip();
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
    }

    /** A pane's tabs in a row, with a way to add one, to close the pane, and a count of the hidden. */
    public record TabStrip() implements Component<HouseBranches.Bar> {
        public static final TabStrip INSTANCE = new TabStrip();
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
    }

    /** A picture's zoom, read out, with buttons to change it. */
    public record PanZoomBar() implements Component<HouseBranches.Bar> {
        public static final PanZoomBar INSTANCE = new PanZoomBar();
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
    }

    /** A row of actions, at the foot of a dialog or a view. */
    public record ActionBar() implements Component<HouseBranches.Bar> {
        public static final ActionBar INSTANCE = new ActionBar();
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
    }

    /** The way back, crumb by crumb. */
    public record Trail() implements Component<HouseBranches.Bar> {
        public static final Trail INSTANCE = new Trail();
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
    }

    // ── fields ───────────────────────────────────────────────────────────

    /** One setting: its label and a note on it. */
    public record PreferenceField() implements Component<HouseBranches.Field> {
        public static final PreferenceField INSTANCE = new PreferenceField();
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
    }

    /** A setting chosen from a few options. */
    public record ChoiceWidget() implements Component<HouseBranches.Field> {
        public static final ChoiceWidget INSTANCE = new ChoiceWidget();
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
    }

    /** The theme chosen. */
    public record ThemeWidget() implements Component<HouseBranches.Field> {
        public static final ThemeWidget INSTANCE = new ThemeWidget();
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
    }

    /** A setting picked along a scale, read out. */
    public record ScaleWidget() implements Component<HouseBranches.Field> {
        public static final ScaleWidget INSTANCE = new ScaleWidget();
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
    }

    /** A setting that is on or off. */
    public record ToggleWidget() implements Component<HouseBranches.Field> {
        public static final ToggleWidget INSTANCE = new ToggleWidget();
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
    }

    /** A list of settings, one shown beside it. */
    public record ListMasterWidget() implements Component<HouseBranches.Field> {
        public static final ListMasterWidget INSTANCE = new ListMasterWidget();
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
    }

    /** A group of settings, its children listed. */
    public record OverviewWidget() implements Component<HouseBranches.Field> {
        public static final OverviewWidget INSTANCE = new OverviewWidget();
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
    }

    // ── views ────────────────────────────────────────────────────────────

    /** A picture that pans and zooms. */
    public record SvgPanZoom() implements Component<HouseBranches.View> {
        public static final SvgPanZoom INSTANCE = new SvgPanZoom();
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
    }

    /** The site's preferences: a master list, and the group picked, with its actions. */
    public record PreferencesView() implements Component<HouseBranches.View> {
        public static final PreferencesView INSTANCE = new PreferencesView();
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
    }

    /** Where focus is on the page, as a tree. */
    public record FocusMonitor() implements Component<HouseBranches.View> {
        public static final FocusMonitor INSTANCE = new FocusMonitor();
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
    }

    /** How the keyboard's steward is, as lamps. */
    public record StewardMonitor() implements Component<HouseBranches.View> {
        public static final StewardMonitor INSTANCE = new StewardMonitor();
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
    }
}
