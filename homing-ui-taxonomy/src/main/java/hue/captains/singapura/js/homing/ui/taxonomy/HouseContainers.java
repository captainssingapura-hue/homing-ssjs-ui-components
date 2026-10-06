package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Role;

import java.util.List;

/**
 * The house's containers: what holds other components and gives them their place - cards and
 * panels, panes and layers, splits and bars, the fields and views of the preferences. Each names
 * the roles its parts play and the component that plays each: a card's title is a
 * {@link HouseText.Heading}, a dialog's frame a {@link FloatingPane}.
 */
public final class HouseContainers {

    private HouseContainers() {}

    // ── containers ───────────────────────────────────────────────────────

    /** A raised box of its own measure: a head with a title and a badge, a body, a foot with a link. */
    public record Card() implements Component<HouseKinds.Container> {
        public static final Card INSTANCE = new Card();
        @Override public HouseKinds.Container parent() { return HouseKinds.Container.INSTANCE; }
        @Override public List<Role<?>> roles() {
            return List.of(Head.INSTANCE, Title.INSTANCE, Badge.INSTANCE, Body.INSTANCE, Text.INSTANCE, Foot.INSTANCE, Link.INSTANCE);
        }

        public record Head()  implements Role<HouseRegions.Section> { public static final Head INSTANCE = new Head();   @Override public HouseRegions.Section base() { return HouseRegions.Section.INSTANCE; } }
        public record Title() implements Role<HouseText.Heading>    { public static final Title INSTANCE = new Title(); @Override public HouseText.Heading base()    { return HouseText.Heading.INSTANCE; } }
        public record Badge() implements Role<HouseText.Badge>      { public static final Badge INSTANCE = new Badge(); @Override public HouseText.Badge base()      { return HouseText.Badge.INSTANCE; } }
        public record Body()  implements Role<HouseRegions.Section> { public static final Body INSTANCE = new Body();   @Override public HouseRegions.Section base() { return HouseRegions.Section.INSTANCE; } }
        public record Text()  implements Role<HouseText.Lede>       { public static final Text INSTANCE = new Text();   @Override public HouseText.Lede base()       { return HouseText.Lede.INSTANCE; } }
        public record Foot()  implements Role<HouseRegions.Section> { public static final Foot INSTANCE = new Foot();   @Override public HouseRegions.Section base() { return HouseRegions.Section.INSTANCE; } }
        public record Link()  implements Role<HouseControls.Link>   { public static final Link INSTANCE = new Link();   @Override public HouseControls.Link base()   { return HouseControls.Link.INSTANCE; } }
    }

    /** Sliders gathered under one header. */
    public record SliderGroup() implements Component<HouseKinds.Container> {
        public static final SliderGroup INSTANCE = new SliderGroup();
        @Override public HouseKinds.Container parent() { return HouseKinds.Container.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Header.INSTANCE, Title.INSTANCE, Member.INSTANCE); }

        public record Header() implements Role<HouseRegions.Section> { public static final Header INSTANCE = new Header(); @Override public HouseRegions.Section base() { return HouseRegions.Section.INSTANCE; } }
        public record Title()  implements Role<HouseText.Heading>    { public static final Title INSTANCE = new Title();   @Override public HouseText.Heading base()    { return HouseText.Heading.INSTANCE; } }
        public record Member() implements Role<HouseControls.Slider> { public static final Member INSTANCE = new Member(); @Override public HouseControls.Slider base() { return HouseControls.Slider.INSTANCE; } }
    }

    /** A panel that fills what holds it: a head with a title, and a body. */
    public record Panel() implements Component<HouseKinds.Container> {
        public static final Panel INSTANCE = new Panel();
        @Override public HouseKinds.Container parent() { return HouseKinds.Container.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Head.INSTANCE, Title.INSTANCE, Body.INSTANCE); }

        public record Head()  implements Role<HouseRegions.Section> { public static final Head INSTANCE = new Head();   @Override public HouseRegions.Section base() { return HouseRegions.Section.INSTANCE; } }
        public record Title() implements Role<HouseText.Heading>    { public static final Title INSTANCE = new Title(); @Override public HouseText.Heading base()    { return HouseText.Heading.INSTANCE; } }
        public record Body()  implements Role<HouseRegions.Section> { public static final Body INSTANCE = new Body();   @Override public HouseRegions.Section base() { return HouseRegions.Section.INSTANCE; } }
    }

    /** A floating pane over a scrim, with a bar of actions. */
    public record Dialog() implements Component<HouseKinds.Container> {
        public static final Dialog INSTANCE = new Dialog();
        @Override public HouseKinds.Container parent() { return HouseKinds.Container.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Backdrop.INSTANCE, Frame.INSTANCE, Actions.INSTANCE); }

        public record Backdrop() implements Role<HouseContainers.Scrim>        { public static final Backdrop INSTANCE = new Backdrop(); @Override public HouseContainers.Scrim base()        { return HouseContainers.Scrim.INSTANCE; } }
        public record Frame()    implements Role<HouseContainers.FloatingPane> { public static final Frame INSTANCE = new Frame();       @Override public HouseContainers.FloatingPane base() { return HouseContainers.FloatingPane.INSTANCE; } }
        public record Actions()  implements Role<HouseContainers.ActionBar>    { public static final Actions INSTANCE = new Actions();   @Override public HouseContainers.ActionBar base()    { return HouseContainers.ActionBar.INSTANCE; } }
    }

    /** The thumbnails of the panes one may open. */
    public record PaneThumbs() implements Component<HouseKinds.Container> {
        public static final PaneThumbs INSTANCE = new PaneThumbs();
        @Override public HouseKinds.Container parent() { return HouseKinds.Container.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Thumb.INSTANCE); }

        public record Thumb() implements Role<HouseControls.PaneThumb> { public static final Thumb INSTANCE = new Thumb(); @Override public HouseControls.PaneThumb base() { return HouseControls.PaneThumb.INSTANCE; } }
    }

    /** The way to add a tab: a picker of pane thumbnails. */
    public record AddTab() implements Component<HouseKinds.Container> {
        public static final AddTab INSTANCE = new AddTab();
        @Override public HouseKinds.Container parent() { return HouseKinds.Container.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Thumbs.INSTANCE); }

        public record Thumbs() implements Role<HouseContainers.PaneThumbs> { public static final Thumbs INSTANCE = new Thumbs(); @Override public HouseContainers.PaneThumbs base() { return HouseContainers.PaneThumbs.INSTANCE; } }
    }

    /** What an empty pane offers to open. */
    public record TabOpener() implements Component<HouseKinds.Container> {
        public static final TabOpener INSTANCE = new TabOpener();
        @Override public HouseKinds.Container parent() { return HouseKinds.Container.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Note.INSTANCE, Pick.INSTANCE); }

        public record Note() implements Role<HouseText.Caption>       { public static final Note INSTANCE = new Note(); @Override public HouseText.Caption base()       { return HouseText.Caption.INSTANCE; } }
        public record Pick() implements Role<HouseControls.PaneThumb> { public static final Pick INSTANCE = new Pick(); @Override public HouseControls.PaneThumb base() { return HouseControls.PaneThumb.INSTANCE; } }
    }

    /** The list of a pane's tabs, to pick from. */
    public record TabPicker() implements Component<HouseKinds.Container> {
        public static final TabPicker INSTANCE = new TabPicker();
        @Override public HouseKinds.Container parent() { return HouseKinds.Container.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Choice.INSTANCE); }

        public record Choice() implements Role<HouseItems.ListRow> { public static final Choice INSTANCE = new Choice(); @Override public HouseItems.ListRow base() { return HouseItems.ListRow.INSTANCE; } }
    }

    /** The site's chrome: its brand, its trail, its preferences, and the page's room. */
    public record MpaChrome() implements Component<HouseKinds.Container> {
        public static final MpaChrome INSTANCE = new MpaChrome();
        @Override public HouseKinds.Container parent() { return HouseKinds.Container.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Brand.INSTANCE, Trail.INSTANCE, Preferences.INSTANCE, Main.INSTANCE); }

        public record Brand()       implements Role<HouseControls.Brand>             { public static final Brand INSTANCE = new Brand();             @Override public HouseControls.Brand base()             { return HouseControls.Brand.INSTANCE; } }
        public record Trail()       implements Role<HouseContainers.Trail>           { public static final Trail INSTANCE = new Trail();             @Override public HouseContainers.Trail base()           { return HouseContainers.Trail.INSTANCE; } }
        public record Preferences() implements Role<HouseControls.PreferencesButton> { public static final Preferences INSTANCE = new Preferences(); @Override public HouseControls.PreferencesButton base() { return HouseControls.PreferencesButton.INSTANCE; } }
        public record Main()        implements Role<HouseRegions.Section>            { public static final Main INSTANCE = new Main();               @Override public HouseRegions.Section base()            { return HouseRegions.Section.INSTANCE; } }
    }

    // ── panes ────────────────────────────────────────────────────────────

    /** A pane that floats: a head with a glyph, a title and a close, a grip, and a body. */
    public record FloatingPane() implements Component<HouseKinds.Pane> {
        public static final FloatingPane INSTANCE = new FloatingPane();
        @Override public HouseKinds.Pane parent() { return HouseKinds.Pane.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Head.INSTANCE, Glyph.INSTANCE, Title.INSTANCE, Close.INSTANCE, Grip.INSTANCE, Body.INSTANCE); }

        public record Head()  implements Role<HouseRegions.Section>     { public static final Head INSTANCE = new Head();   @Override public HouseRegions.Section base()     { return HouseRegions.Section.INSTANCE; } }
        public record Glyph() implements Role<HouseMarks.Icon>          { public static final Glyph INSTANCE = new Glyph(); @Override public HouseMarks.Icon base()          { return HouseMarks.Icon.INSTANCE; } }
        public record Title() implements Role<HouseText.Heading>        { public static final Title INSTANCE = new Title(); @Override public HouseText.Heading base()        { return HouseText.Heading.INSTANCE; } }
        public record Close() implements Role<HouseControls.CloseButton> { public static final Close INSTANCE = new Close(); @Override public HouseControls.CloseButton base() { return HouseControls.CloseButton.INSTANCE; } }
        public record Grip()  implements Role<HouseControls.Grip>       { public static final Grip INSTANCE = new Grip();   @Override public HouseControls.Grip base()       { return HouseControls.Grip.INSTANCE; } }
        public record Body()  implements Role<HouseRegions.Section>     { public static final Body INSTANCE = new Body();   @Override public HouseRegions.Section base()     { return HouseRegions.Section.INSTANCE; } }
    }

    /** A pane of many tabs: its strip, its picker, its content, where a dragged tab would land. */
    public record MultiTabPane() implements Component<HouseKinds.Pane> {
        public static final MultiTabPane INSTANCE = new MultiTabPane();
        @Override public HouseKinds.Pane parent() { return HouseKinds.Pane.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Strip.INSTANCE, Picker.INSTANCE, Content.INSTANCE, Landing.INSTANCE); }

        public record Strip()   implements Role<HouseContainers.TabStrip>  { public static final Strip INSTANCE = new Strip();     @Override public HouseContainers.TabStrip base()  { return HouseContainers.TabStrip.INSTANCE; } }
        public record Picker()  implements Role<HouseContainers.TabPicker> { public static final Picker INSTANCE = new Picker();   @Override public HouseContainers.TabPicker base() { return HouseContainers.TabPicker.INSTANCE; } }
        public record Content() implements Role<HouseRegions.Section>      { public static final Content INSTANCE = new Content(); @Override public HouseRegions.Section base()      { return HouseRegions.Section.INSTANCE; } }
        public record Landing() implements Role<HouseMarks.DropMark>       { public static final Landing INSTANCE = new Landing(); @Override public HouseMarks.DropMark base()       { return HouseMarks.DropMark.INSTANCE; } }
    }

    /** A pane of one tab: its title and its content. */
    public record SingleTabPane() implements Component<HouseKinds.Pane> {
        public static final SingleTabPane INSTANCE = new SingleTabPane();
        @Override public HouseKinds.Pane parent() { return HouseKinds.Pane.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Title.INSTANCE, Content.INSTANCE); }

        public record Title()   implements Role<HouseText.Heading>     { public static final Title INSTANCE = new Title();     @Override public HouseText.Heading base()     { return HouseText.Heading.INSTANCE; } }
        public record Content() implements Role<HouseRegions.Section> { public static final Content INSTANCE = new Content(); @Override public HouseRegions.Section base() { return HouseRegions.Section.INSTANCE; } }
    }

    /** What one tab of a pane holds. */
    public record TabPane() implements Component<HouseKinds.Pane> {
        public static final TabPane INSTANCE = new TabPane();
        @Override public HouseKinds.Pane parent() { return HouseKinds.Pane.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Content.INSTANCE); }

        public record Content() implements Role<HouseRegions.Section> { public static final Content INSTANCE = new Content(); @Override public HouseRegions.Section base() { return HouseRegions.Section.INSTANCE; } }
    }

    // ── menus and layers ─────────────────────────────────────────────────

    /** A menu opened where it is asked: its items, and the lines between groups of them. */
    public record ContextMenu() implements Component<HouseKinds.Menu> {
        public static final ContextMenu INSTANCE = new ContextMenu();
        @Override public HouseKinds.Menu parent() { return HouseKinds.Menu.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Choice.INSTANCE, Rule.INSTANCE); }

        public record Choice() implements Role<HouseItems.MenuItem>   { public static final Choice INSTANCE = new Choice(); @Override public HouseItems.MenuItem base()   { return HouseItems.MenuItem.INSTANCE; } }
        public record Rule()   implements Role<HouseMarks.Separator> { public static final Rule INSTANCE = new Rule();     @Override public HouseMarks.Separator base() { return HouseMarks.Separator.INSTANCE; } }
    }

    /** The layer floating panes are stacked on. */
    public record FloatLayer() implements Component<HouseKinds.Layer> {
        public static final FloatLayer INSTANCE = new FloatLayer();
        @Override public HouseKinds.Layer parent() { return HouseKinds.Layer.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Floater.INSTANCE); }

        public record Floater() implements Role<HouseContainers.FloatingPane> { public static final Floater INSTANCE = new Floater(); @Override public HouseContainers.FloatingPane base() { return HouseContainers.FloatingPane.INSTANCE; } }
    }

    /** The veil over the page behind a dialog. */
    public record Scrim() implements Component<HouseKinds.Layer> {
        public static final Scrim INSTANCE = new Scrim();
        @Override public HouseKinds.Layer parent() { return HouseKinds.Layer.INSTANCE; }
    }

    /** The one keeper of the page's context menus. */
    public record ContextMenuSteward() implements Component<HouseKinds.Layer> {
        public static final ContextMenuSteward INSTANCE = new ContextMenuSteward();
        @Override public HouseKinds.Layer parent() { return HouseKinds.Layer.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Opened.INSTANCE); }

        public record Opened() implements Role<HouseContainers.ContextMenu> { public static final Opened INSTANCE = new Opened(); @Override public HouseContainers.ContextMenu base() { return HouseContainers.ContextMenu.INSTANCE; } }
    }

    // ── splits ───────────────────────────────────────────────────────────

    /** Two regions and the divider between them. */
    public record SplitPane() implements Component<HouseKinds.Split> {
        public static final SplitPane INSTANCE = new SplitPane();
        @Override public HouseKinds.Split parent() { return HouseKinds.Split.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Child.INSTANCE, Seam.INSTANCE); }

        public record Child() implements Role<HouseRegions.Section> { public static final Child INSTANCE = new Child(); @Override public HouseRegions.Section base() { return HouseRegions.Section.INSTANCE; } }
        public record Seam()  implements Role<HouseControls.Divider> { public static final Seam INSTANCE = new Seam();  @Override public HouseControls.Divider base() { return HouseControls.Divider.INSTANCE; } }
    }

    /** A grid of cells split in both directions, its dividers between them. */
    public record SplitGrid() implements Component<HouseKinds.Split> {
        public static final SplitGrid INSTANCE = new SplitGrid();
        @Override public HouseKinds.Split parent() { return HouseKinds.Split.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Cell.INSTANCE, Seam.INSTANCE); }

        public record Cell() implements Role<HouseRegions.GridCell> { public static final Cell INSTANCE = new Cell(); @Override public HouseRegions.GridCell base() { return HouseRegions.GridCell.INSTANCE; } }
        public record Seam() implements Role<HouseControls.Divider> { public static final Seam INSTANCE = new Seam(); @Override public HouseControls.Divider base() { return HouseControls.Divider.INSTANCE; } }
    }

    /** A split grid's shape, drawn small. */
    public record SplitGridMirror() implements Component<HouseKinds.Split> {
        public static final SplitGridMirror INSTANCE = new SplitGridMirror();
        @Override public HouseKinds.Split parent() { return HouseKinds.Split.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Cell.INSTANCE); }

        public record Cell() implements Role<HouseRegions.GridCell> { public static final Cell INSTANCE = new Cell(); @Override public HouseRegions.GridCell base() { return HouseRegions.GridCell.INSTANCE; } }
    }

    /** A split grid whose cells are tabbed panes: the dock. */
    public record DockGrid() implements Component<HouseKinds.Split> {
        public static final DockGrid INSTANCE = new DockGrid();
        @Override public HouseKinds.Split parent() { return HouseKinds.Split.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Grid.INSTANCE, Docked.INSTANCE); }

        public record Grid()   implements Role<HouseContainers.SplitGrid>    { public static final Grid INSTANCE = new Grid();     @Override public HouseContainers.SplitGrid base()    { return HouseContainers.SplitGrid.INSTANCE; } }
        public record Docked() implements Role<HouseContainers.MultiTabPane> { public static final Docked INSTANCE = new Docked(); @Override public HouseContainers.MultiTabPane base() { return HouseContainers.MultiTabPane.INSTANCE; } }
    }

    // ── bars ─────────────────────────────────────────────────────────────

    /** A strip along an edge, revealed by its lip. */
    public record EdgeStrip() implements Component<HouseKinds.Bar> {
        public static final EdgeStrip INSTANCE = new EdgeStrip();
        @Override public HouseKinds.Bar parent() { return HouseKinds.Bar.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Lip.INSTANCE); }

        public record Lip() implements Role<HouseControls.Grip> { public static final Lip INSTANCE = new Lip(); @Override public HouseControls.Grip base() { return HouseControls.Grip.INSTANCE; } }
    }

    /** A pane's tabs in a row, with a way to add one, to close the pane, and a count of the hidden. */
    public record TabStrip() implements Component<HouseKinds.Bar> {
        public static final TabStrip INSTANCE = new TabStrip();
        @Override public HouseKinds.Bar parent() { return HouseKinds.Bar.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Chip.INSTANCE, Add.INSTANCE, Close.INSTANCE, Count.INSTANCE); }

        public record Chip()  implements Role<HouseControls.Tab>         { public static final Chip INSTANCE = new Chip();   @Override public HouseControls.Tab base()         { return HouseControls.Tab.INSTANCE; } }
        public record Add()   implements Role<HouseControls.IconButton>  { public static final Add INSTANCE = new Add();     @Override public HouseControls.IconButton base()  { return HouseControls.IconButton.INSTANCE; } }
        public record Close() implements Role<HouseControls.CloseButton> { public static final Close INSTANCE = new Close(); @Override public HouseControls.CloseButton base() { return HouseControls.CloseButton.INSTANCE; } }
        public record Count() implements Role<HouseText.Pill>            { public static final Count INSTANCE = new Count(); @Override public HouseText.Pill base()            { return HouseText.Pill.INSTANCE; } }
    }

    /** A picture's zoom, read out, with buttons to change it. */
    public record PanZoomBar() implements Component<HouseKinds.Bar> {
        public static final PanZoomBar INSTANCE = new PanZoomBar();
        @Override public HouseKinds.Bar parent() { return HouseKinds.Bar.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Zoom.INSTANCE, Step.INSTANCE); }

        public record Zoom() implements Role<HouseText.Readout>        { public static final Zoom INSTANCE = new Zoom(); @Override public HouseText.Readout base()        { return HouseText.Readout.INSTANCE; } }
        public record Step() implements Role<HouseControls.IconButton> { public static final Step INSTANCE = new Step(); @Override public HouseControls.IconButton base() { return HouseControls.IconButton.INSTANCE; } }
    }

    /** A row of actions, at the foot of a dialog or a view. */
    public record ActionBar() implements Component<HouseKinds.Bar> {
        public static final ActionBar INSTANCE = new ActionBar();
        @Override public HouseKinds.Bar parent() { return HouseKinds.Bar.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Action.INSTANCE); }

        public record Action() implements Role<HouseControls.PlainButton> { public static final Action INSTANCE = new Action(); @Override public HouseControls.PlainButton base() { return HouseControls.PlainButton.INSTANCE; } }
    }

    /** The way back, crumb by crumb. */
    public record Trail() implements Component<HouseKinds.Bar> {
        public static final Trail INSTANCE = new Trail();
        @Override public HouseKinds.Bar parent() { return HouseKinds.Bar.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Step.INSTANCE, Between.INSTANCE); }

        public record Step()    implements Role<HouseControls.Crumb>  { public static final Step INSTANCE = new Step();       @Override public HouseControls.Crumb base()  { return HouseControls.Crumb.INSTANCE; } }
        public record Between() implements Role<HouseMarks.Separator> { public static final Between INSTANCE = new Between(); @Override public HouseMarks.Separator base() { return HouseMarks.Separator.INSTANCE; } }
    }

    // ── fields ───────────────────────────────────────────────────────────

    /** One setting: its label and a note on it. */
    public record PreferenceField() implements Component<HouseKinds.Field> {
        public static final PreferenceField INSTANCE = new PreferenceField();
        @Override public HouseKinds.Field parent() { return HouseKinds.Field.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Label.INSTANCE, Note.INSTANCE); }

        public record Label() implements Role<HouseText.Label>   { public static final Label INSTANCE = new Label(); @Override public HouseText.Label base()   { return HouseText.Label.INSTANCE; } }
        public record Note()  implements Role<HouseText.Caption> { public static final Note INSTANCE = new Note();   @Override public HouseText.Caption base() { return HouseText.Caption.INSTANCE; } }
    }

    /** A setting chosen from a few options. */
    public record ChoiceWidget() implements Component<HouseKinds.Field> {
        public static final ChoiceWidget INSTANCE = new ChoiceWidget();
        @Override public HouseKinds.Field parent() { return HouseKinds.Field.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Setting.INSTANCE, Option.INSTANCE); }

        public record Setting() implements Role<HouseContainers.PreferenceField> { public static final Setting INSTANCE = new Setting(); @Override public HouseContainers.PreferenceField base() { return HouseContainers.PreferenceField.INSTANCE; } }
        public record Option()  implements Role<HouseItems.ChoiceOption>         { public static final Option INSTANCE = new Option();   @Override public HouseItems.ChoiceOption base()         { return HouseItems.ChoiceOption.INSTANCE; } }
    }

    /** The theme chosen. */
    public record ThemeWidget() implements Component<HouseKinds.Field> {
        public static final ThemeWidget INSTANCE = new ThemeWidget();
        @Override public HouseKinds.Field parent() { return HouseKinds.Field.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Setting.INSTANCE, Option.INSTANCE); }

        public record Setting() implements Role<HouseContainers.PreferenceField> { public static final Setting INSTANCE = new Setting(); @Override public HouseContainers.PreferenceField base() { return HouseContainers.PreferenceField.INSTANCE; } }
        public record Option()  implements Role<HouseItems.ChoiceOption>         { public static final Option INSTANCE = new Option();   @Override public HouseItems.ChoiceOption base()         { return HouseItems.ChoiceOption.INSTANCE; } }
    }

    /** A setting picked along a scale, read out. */
    public record ScaleWidget() implements Component<HouseKinds.Field> {
        public static final ScaleWidget INSTANCE = new ScaleWidget();
        @Override public HouseKinds.Field parent() { return HouseKinds.Field.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Setting.INSTANCE, Scale.INSTANCE, Value.INSTANCE); }

        public record Setting() implements Role<HouseContainers.PreferenceField> { public static final Setting INSTANCE = new Setting(); @Override public HouseContainers.PreferenceField base() { return HouseContainers.PreferenceField.INSTANCE; } }
        public record Scale()   implements Role<HouseControls.Range>             { public static final Scale INSTANCE = new Scale();     @Override public HouseControls.Range base()             { return HouseControls.Range.INSTANCE; } }
        public record Value()   implements Role<HouseText.Readout>               { public static final Value INSTANCE = new Value();     @Override public HouseText.Readout base()               { return HouseText.Readout.INSTANCE; } }
    }

    /** A setting that is on or off. */
    public record ToggleWidget() implements Component<HouseKinds.Field> {
        public static final ToggleWidget INSTANCE = new ToggleWidget();
        @Override public HouseKinds.Field parent() { return HouseKinds.Field.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Setting.INSTANCE, Flip.INSTANCE); }

        public record Setting() implements Role<HouseContainers.PreferenceField> { public static final Setting INSTANCE = new Setting(); @Override public HouseContainers.PreferenceField base() { return HouseContainers.PreferenceField.INSTANCE; } }
        public record Flip()    implements Role<HouseControls.Switch>            { public static final Flip INSTANCE = new Flip();       @Override public HouseControls.Switch base()            { return HouseControls.Switch.INSTANCE; } }
    }

    /** A list of settings, one shown beside it. */
    public record ListMasterWidget() implements Component<HouseKinds.Field> {
        public static final ListMasterWidget INSTANCE = new ListMasterWidget();
        @Override public HouseKinds.Field parent() { return HouseKinds.Field.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Row.INSTANCE); }

        public record Row() implements Role<HouseItems.ListRow> { public static final Row INSTANCE = new Row(); @Override public HouseItems.ListRow base() { return HouseItems.ListRow.INSTANCE; } }
    }

    /** A group of settings, its children listed. */
    public record OverviewWidget() implements Component<HouseKinds.Field> {
        public static final OverviewWidget INSTANCE = new OverviewWidget();
        @Override public HouseKinds.Field parent() { return HouseKinds.Field.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Child.INSTANCE); }

        public record Child() implements Role<HouseItems.ListRow> { public static final Child INSTANCE = new Child(); @Override public HouseItems.ListRow base() { return HouseItems.ListRow.INSTANCE; } }
    }

    // ── views ────────────────────────────────────────────────────────────

    /** A picture that pans and zooms. */
    public record SvgPanZoom() implements Component<HouseKinds.View> {
        public static final SvgPanZoom INSTANCE = new SvgPanZoom();
        @Override public HouseKinds.View parent() { return HouseKinds.View.INSTANCE; }
    }

    /** The site's preferences: a master list, and the group picked, with its actions. */
    public record PreferencesView() implements Component<HouseKinds.View> {
        public static final PreferencesView INSTANCE = new PreferencesView();
        @Override public HouseKinds.View parent() { return HouseKinds.View.INSTANCE; }
        @Override public List<Role<?>> roles() {
            return List.of(Master.INSTANCE, Kicker.INSTANCE, Title.INSTANCE, Summary.INSTANCE, Note.INSTANCE, Actions.INSTANCE);
        }

        public record Master()  implements Role<HouseRegions.Section>      { public static final Master INSTANCE = new Master();   @Override public HouseRegions.Section base()      { return HouseRegions.Section.INSTANCE; } }
        public record Kicker()  implements Role<HouseText.Kicker>          { public static final Kicker INSTANCE = new Kicker();   @Override public HouseText.Kicker base()          { return HouseText.Kicker.INSTANCE; } }
        public record Title()   implements Role<HouseText.Heading>         { public static final Title INSTANCE = new Title();     @Override public HouseText.Heading base()         { return HouseText.Heading.INSTANCE; } }
        public record Summary() implements Role<HouseText.Lede>            { public static final Summary INSTANCE = new Summary(); @Override public HouseText.Lede base()            { return HouseText.Lede.INSTANCE; } }
        public record Note()    implements Role<HouseText.Caption>         { public static final Note INSTANCE = new Note();       @Override public HouseText.Caption base()         { return HouseText.Caption.INSTANCE; } }
        public record Actions() implements Role<HouseContainers.ActionBar> { public static final Actions INSTANCE = new Actions(); @Override public HouseContainers.ActionBar base() { return HouseContainers.ActionBar.INSTANCE; } }
    }

    /** Where focus is on the page, as a tree. */
    public record FocusMonitor() implements Component<HouseKinds.View> {
        public static final FocusMonitor INSTANCE = new FocusMonitor();
        @Override public HouseKinds.View parent() { return HouseKinds.View.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Row.INSTANCE); }

        public record Row() implements Role<HouseItems.ListRow> { public static final Row INSTANCE = new Row(); @Override public HouseItems.ListRow base() { return HouseItems.ListRow.INSTANCE; } }
    }

    /** How the keyboard's steward is, as lamps. */
    public record StewardMonitor() implements Component<HouseKinds.View> {
        public static final StewardMonitor INSTANCE = new StewardMonitor();
        @Override public HouseKinds.View parent() { return HouseKinds.View.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Signal.INSTANCE); }

        public record Signal() implements Role<HouseMarks.Lamp> { public static final Signal INSTANCE = new Signal(); @Override public HouseMarks.Lamp base() { return HouseMarks.Lamp.INSTANCE; } }
    }
}
