package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;

import java.util.List;

/**
 * The house's containers: what holds other components and gives them their place - cards and
 * panels, panes and layers, splits and bars, the fields and views of the preferences. Each declares
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

    /** The site's chrome: its brand, its trail, its preferences, and the page's room - which is the app's. */
    public record MpaChrome() implements Component<HouseBranches.Container> {
        public static final MpaChrome INSTANCE = new MpaChrome();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Container parent() { return HouseBranches.Container.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseRegions.Section.INSTANCE).as(HouseShaping.Head.INSTANCE).one(),
                           DSL.part(HouseControls.Brand.INSTANCE).as(HouseDoing.Home.INSTANCE).one(),
                           DSL.part(Trail.INSTANCE).as(HouseDoing.Path.INSTANCE).one(),
                           DSL.part(HouseControls.PreferencesButton.INSTANCE).as(HouseDoing.Preferences.INSTANCE).one());
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
            return List.of(DSL.part(HouseControls.Tab.INSTANCE).as(HouseShaping.Chip.INSTANCE).one());
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

    /** The layer floating panes are stacked on. */
    public record FloatLayer() implements Component<HouseBranches.Layer> {
        public static final FloatLayer INSTANCE = new FloatLayer();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Layer parent() { return HouseBranches.Layer.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(FloatingPane.INSTANCE).as(HouseShaping.Float.INSTANCE).any());
        }
    }

    /** The veil over the page behind a dialog. */
    public record Scrim() implements Component<HouseBranches.Layer> {
        public static final Scrim INSTANCE = new Scrim();
        @Override public HouseBranches.Layer parent() { return HouseBranches.Layer.INSTANCE; }
    }

    /** The one keeper of the page's context menus: one open at a time, and a specimen of each kind. Its layer is its own. */
    public record ContextMenuSteward() implements Component<HouseBranches.Layer> {
        public static final ContextMenuSteward INSTANCE = new ContextMenuSteward();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Layer parent() { return HouseBranches.Layer.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(ContextMenu.INSTANCE).as(HouseShaping.Popup.INSTANCE).any(),
                           DSL.part(ContextMenu.INSTANCE).as(HouseShaping.Specimen.INSTANCE).any());
        }
    }

    // ── splits ───────────────────────────────────────────────────────────

    /** Regions split in a tree, and the dividers between them. Its cells are its own. */
    public record SplitPane() implements Component<HouseBranches.Split> {
        public static final SplitPane INSTANCE = new SplitPane();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Split parent() { return HouseBranches.Split.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.Divider.INSTANCE).as(HouseShaping.Seam.INSTANCE).any());
        }
    }

    /** A grid of cells split in both directions, its dividers between them. Its cells are its own. */
    public record SplitGrid() implements Component<HouseBranches.Split> {
        public static final SplitGrid INSTANCE = new SplitGrid();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Split parent() { return HouseBranches.Split.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.Divider.INSTANCE).as(HouseShaping.Seam.INSTANCE).any());
        }
    }

    /** A split grid's shape, drawn small. */
    public record SplitGridMirror() implements Component<HouseBranches.Split> {
        public static final SplitGridMirror INSTANCE = new SplitGridMirror();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Split parent() { return HouseBranches.Split.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseRegions.GridCell.INSTANCE).as(HouseShaping.Cell.INSTANCE).any());
        }
    }

    /** A split grid whose cells are tabbed panes: the dock. */
    public record DockGrid() implements Component<HouseBranches.Split> {
        public static final DockGrid INSTANCE = new DockGrid();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Split parent() { return HouseBranches.Split.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(SplitGrid.INSTANCE).as(HouseShaping.Layout.INSTANCE).one(),
                           DSL.part(MultiTabPane.INSTANCE).as(HouseShaping.Dock.INSTANCE).atLeast(1));
        }
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

    /** A picture's zoom, read out, with buttons to change it. It drives a view it is given. */
    public record PanZoomBar() implements Component<HouseBranches.Bar> {
        public static final PanZoomBar INSTANCE = new PanZoomBar();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.ZoomOut.INSTANCE).one(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.ZoomIn.INSTANCE).one(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Fit.INSTANCE).one(),
                           DSL.part(HouseText.Readout.INSTANCE).as(HouseSaying.Value.INSTANCE).one());
        }
    }

    /** A row of actions, at the foot of a dialog or a view. */
    public record ActionBar() implements Component<HouseBranches.Bar> {
        public static final ActionBar INSTANCE = new ActionBar();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Action.INSTANCE).atLeast(1));
        }
    }

    /** The way back, crumb by crumb. */
    public record Trail() implements Component<HouseBranches.Bar> {
        public static final Trail INSTANCE = new Trail();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Bar parent() { return HouseBranches.Bar.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseControls.Crumb.INSTANCE).as(HouseDoing.Step.INSTANCE).any(),
                           DSL.part(HouseMarks.Separator.INSTANCE).as(HouseShaping.Break.INSTANCE).any());
        }
    }

    // ── fields ───────────────────────────────────────────────────────────

    /** One setting: what it is filed under, its title and summary, a note on it, and a way back to the default. Its body is the widget's. */
    public record PreferenceField() implements Component<HouseBranches.Field> {
        public static final PreferenceField INSTANCE = new PreferenceField();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Category.INSTANCE).one(),
                           DSL.part(HouseText.Heading.INSTANCE).as(HouseSaying.Title.INSTANCE).one(),
                           DSL.part(HouseText.Lede.INSTANCE).as(HouseSaying.Summary.INSTANCE).optional(),
                           DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Note.INSTANCE).one(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Reset.INSTANCE).one());
        }
    }

    /** A setting chosen from a few options. */
    public record ChoiceWidget() implements Component<HouseBranches.Field> {
        public static final ChoiceWidget INSTANCE = new ChoiceWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(PreferenceField.INSTANCE).as(HouseShaping.Setting.INSTANCE).one(),
                           DSL.part(HouseItems.ChoiceOption.INSTANCE).as(HouseDoing.Choice.INSTANCE).any());
        }
    }

    /** The theme chosen: a style, and a palette for it. */
    public record ThemeWidget() implements Component<HouseBranches.Field> {
        public static final ThemeWidget INSTANCE = new ThemeWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(PreferenceField.INSTANCE).as(HouseShaping.Setting.INSTANCE).one(),
                           DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Status.INSTANCE).optional(),
                           DSL.part(HouseItems.ChoiceOption.INSTANCE).as(HouseDoing.Style.INSTANCE).any(),
                           DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Subtitle.INSTANCE).optional(),
                           DSL.part(HouseItems.ChoiceOption.INSTANCE).as(HouseDoing.Palette.INSTANCE).any());
        }
    }

    /** A setting picked along a scale, read out. */
    public record ScaleWidget() implements Component<HouseBranches.Field> {
        public static final ScaleWidget INSTANCE = new ScaleWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(PreferenceField.INSTANCE).as(HouseShaping.Setting.INSTANCE).one(),
                           DSL.part(HouseControls.Range.INSTANCE).as(HouseDoing.Adjust.INSTANCE).one(),
                           DSL.part(HouseText.Readout.INSTANCE).as(HouseSaying.Value.INSTANCE).one());
        }
    }

    /** A setting that is on or off. */
    public record ToggleWidget() implements Component<HouseBranches.Field> {
        public static final ToggleWidget INSTANCE = new ToggleWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(PreferenceField.INSTANCE).as(HouseShaping.Setting.INSTANCE).one(),
                           DSL.part(HouseControls.Switch.INSTANCE).as(HouseDoing.Toggle.INSTANCE).one());
        }
    }

    /** The settings' tree as a flat list, depth as indentation: choosing a row is choosing what is shown beside it. */
    public record ListMasterWidget() implements Component<HouseBranches.Field> {
        public static final ListMasterWidget INSTANCE = new ListMasterWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseItems.ListRow.INSTANCE).as(HouseDoing.Choice.INSTANCE).atLeast(1));
        }
    }

    /** A group of settings: its head, its children listed with their values, and a way back to the defaults. */
    public record OverviewWidget() implements Component<HouseBranches.Field> {
        public static final OverviewWidget INSTANCE = new OverviewWidget();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.Field parent() { return HouseBranches.Field.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Category.INSTANCE).one(),
                           DSL.part(HouseText.Heading.INSTANCE).as(HouseSaying.Title.INSTANCE).one(),
                           DSL.part(HouseText.Lede.INSTANCE).as(HouseSaying.Summary.INSTANCE).optional(),
                           DSL.part(HouseItems.SettingRow.INSTANCE).as(HouseShaping.Entry.INSTANCE).any(),
                           DSL.part(HouseControls.PlainButton.INSTANCE).as(HouseDoing.Reset.INSTANCE).one());
        }
    }

    // ── views ────────────────────────────────────────────────────────────

    /** A picture that pans and zooms. The drawing is the caller's. */
    public record SvgPanZoom() implements Component<HouseBranches.View> {
        public static final SvgPanZoom INSTANCE = new SvgPanZoom();
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
    }

    /** The site's preferences: the index of its settings, and the one chosen. The widgets are held in the slots. */
    public record PreferencesView() implements Component<HouseBranches.View> {
        public static final PreferencesView INSTANCE = new PreferencesView();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseRegions.WidgetSlot.INSTANCE).as(HouseShaping.Index.INSTANCE).one(),
                           DSL.part(HouseRegions.WidgetSlot.INSTANCE).as(HouseShaping.Detail.INSTANCE).one());
        }
    }

    /** Where focus is on the page, as a tree. */
    public record FocusMonitor() implements Component<HouseBranches.View> {
        public static final FocusMonitor INSTANCE = new FocusMonitor();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseItems.ListRow.INSTANCE).as(HouseShaping.Entry.INSTANCE).atLeast(1));
        }
    }

    /** Where the keyboard's steward has the focus, as one lamp. */
    public record StewardMonitor() implements Component<HouseBranches.View> {
        public static final StewardMonitor INSTANCE = new StewardMonitor();
        @Override public HouseBranches.View parent() { return HouseBranches.View.INSTANCE; }
    }
}
