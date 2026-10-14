package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Button;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Handle;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Input;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Selector;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Way;

import java.util.List;

/**
 * The house's controls: what a user operates. A button's colour fixed by construction is a button
 * of its own - plain, primary, danger and the rest - and how much of it is worn is its extent. Each declares its parts as slots over the
 * house's role catalogue ({@link HouseRoleCatalogue}); a part a control draws for itself alone is
 * its own, and no slot.
 */
public final class HouseControls {

    private HouseControls() {}

    /** What every button has: it may be off; and the browser's - the pointer over it, its focus shown, the hand down on it. */
    private static final List<StateType<?>> A_BUTTON = List.of(HouseStates.Enabled.INSTANCE,
            HouseStates.Hovered.INSTANCE, HouseStates.Focused.INSTANCE, HouseStates.Down.INSTANCE);

    /** What a toggle has besides: it may be on. */
    private static final List<StateType<?>> A_TOGGLE = List.of(HouseStates.Enabled.INSTANCE, HouseStates.Pressed.INSTANCE,
            HouseStates.Hovered.INSTANCE, HouseStates.Focused.INSTANCE, HouseStates.Down.INSTANCE);

    /** What every button offers: it is clicked. */
    private static final List<EventType<?>> CLICKED = List.of(HouseEvents.Click.INSTANCE);

    /** A crumb's state: whether it is the step that is this page. */
    private static final List<StateType<?>> A_CRUMB = List.of(HouseStates.Current.INSTANCE,
            HouseStates.Hovered.INSTANCE, HouseStates.Focused.INSTANCE);

    /** What the browser's own inputs have: they may be off; their focus shown. */
    private static final List<StateType<?>> AN_INPUT = List.of(HouseStates.Enabled.INSTANCE, HouseStates.Focused.INSTANCE);

    /** What a box ticked, or a switch, has besides: it may be on. */
    private static final List<StateType<?>> A_CHECK = List.of(HouseStates.Enabled.INSTANCE, HouseStates.Checked.INSTANCE,
            HouseStates.Focused.INSTANCE);

    /** What is edited: its value changing, the user still at it, and the user done with it. */
    private static final List<EventType<?>> EDITED = List.of(HouseEvents.Input.INSTANCE, HouseEvents.Commit.INSTANCE);

    /** What is picked: the user done with it. */
    private static final List<EventType<?>> COMMITTED = List.of(HouseEvents.Commit.INSTANCE);

    /** What the slider's knob has: it may be off; it stands up or lies along; its focus shown; the current of a group; dragged. */
    private static final List<StateType<?>> A_SLIDER_KNOB = List.of(HouseStates.Enabled.INSTANCE, HouseStates.Orientation.INSTANCE,
            HouseStates.Focused.INSTANCE, HouseStates.Current.INSTANCE, HouseStates.Dragging.INSTANCE);

    /** What a way somewhere has: the browser's - the pointer over it, its focus shown. */
    private static final List<StateType<?>> A_WAY = List.of(HouseStates.Hovered.INSTANCE, HouseStates.Focused.INSTANCE);

    /** What the brand has: its focus shown. */
    private static final List<StateType<?>> A_BRAND = List.of(HouseStates.Focused.INSTANCE);

    // ── buttons ──────────────────────────────────────────────────────────

    /** A button with no emphasis. */
    public record PlainButton() implements Component<Button> {
        public static final PlainButton INSTANCE = new PlainButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public List<EventType<?>> events() { return CLICKED; }
        @Override public Button parent() { return Button.INSTANCE; }
    }

    /** The one action a view leads with. */
    public record PrimaryButton() implements Component<Button> {
        public static final PrimaryButton INSTANCE = new PrimaryButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public List<EventType<?>> events() { return CLICKED; }
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.COLOUR); }
    }

    /** An action beside the primary one. */
    public record SecondaryButton() implements Component<Button> {
        public static final SecondaryButton INSTANCE = new SecondaryButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public List<EventType<?>> events() { return CLICKED; }
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.COLOUR); }
    }

    /** An action that destroys or cannot be undone. */
    public record DangerButton() implements Component<Button> {
        public static final DangerButton INSTANCE = new DangerButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public List<EventType<?>> events() { return CLICKED; }
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.COLOUR); }
    }

    /** An action that needs a second thought. */
    public record WarningButton() implements Component<Button> {
        public static final WarningButton INSTANCE = new WarningButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public List<EventType<?>> events() { return CLICKED; }
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.COLOUR); }
    }

    /** An action that confirms what went well. */
    public record SuccessButton() implements Component<Button> {
        public static final SuccessButton INSTANCE = new SuccessButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public List<EventType<?>> events() { return CLICKED; }
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.COLOUR); }
    }

    /** A button that is on or off. */
    public record ToggleButton() implements Component<Button> {
        public static final ToggleButton INSTANCE = new ToggleButton();
        @Override public List<StateType<?>> states() { return A_TOGGLE; }
        @Override public List<EventType<?>> events() { return CLICKED; }
        @Override public Button parent() { return Button.INSTANCE; }
    }

    /** A button that is an icon alone: the icon its part, whichever icon it is. */
    public record IconButton() implements Component<Button> {
        public static final IconButton INSTANCE = new IconButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public List<EventType<?>> events() { return CLICKED; }
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.Icon.INSTANCE).as(HouseSaying.Symbol.INSTANCE).one());
        }
    }

    /** The button that closes what holds it. */
    public record CloseButton() implements Component<Button> {
        public static final CloseButton INSTANCE = new CloseButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public List<EventType<?>> events() { return CLICKED; }
        @Override public Button parent() { return Button.INSTANCE; }
    }

    // ── handles ──────────────────────────────────────────────────────────

    /** The slider's knob: what is grabbed to move its value along its groove; upright it is a cap. Never alone: a slider's part. */
    public record SliderKnob() implements Component<Handle> {
        public static final SliderKnob INSTANCE = new SliderKnob();
        @Override public Handle parent() { return Handle.INSTANCE; }
        @Override public List<StateType<?>> states() { return A_SLIDER_KNOB; }
    }

    /** The toggle's knob: what shows which way a switch stands, sliding in its groove. Never alone: a switch's part. */
    public record ToggleKnob() implements Component<Handle> {
        public static final ToggleKnob INSTANCE = new ToggleKnob();
        @Override public Handle parent() { return Handle.INSTANCE; }
    }

    /** What is grabbed to move the thing it is on. */
    public record Grip() implements Component<Handle> {
        public static final Grip INSTANCE = new Grip();
        @Override public Handle parent() { return Handle.INSTANCE; }
        @Override public List<StateType<?>> states() { return AN_INPUT; }
    }


    // ── other controls ───────────────────────────────────────────────────

    /** A way to somewhere else. */
    public record Link() implements Component<Way> {
        public static final Link INSTANCE = new Link();
        @Override public Way parent() { return Way.INSTANCE; }
        @Override public List<StateType<?>> states() { return A_WAY; }
        @Override public List<EventType<?>> events() { return CLICKED; }
    }

    /** One step of a trail back. */
    public record Crumb() implements Component<Way> {
        public static final Crumb INSTANCE = new Crumb();
        @Override public Way parent() { return Way.INSTANCE; }
        @Override public List<StateType<?>> states() { return A_CRUMB; }
        @Override public List<EventType<?>> events() { return CLICKED; }
    }

    /** A value picked along a scale. */
    public record Range() implements Component<Input> {
        public static final Range INSTANCE = new Range();
        @Override public List<StateType<?>> states() { return AN_INPUT; }
        @Override public List<EventType<?>> events() { return EDITED; }
        @Override public Input parent() { return Input.INSTANCE; }
    }

    /** A value picked from a list that drops down. */
    public record Select() implements Component<Input> {
        public static final Select INSTANCE = new Select();
        @Override public List<StateType<?>> states() { return AN_INPUT; }
        @Override public List<EventType<?>> events() { return COMMITTED; }
        @Override public Input parent() { return Input.INSTANCE; }
    }

    /** Words the user types: a line of them, or as many as they write - the browser's own editing. */
    public record TextBox() implements Component<Input> {
        public static final TextBox INSTANCE = new TextBox();
        @Override public List<StateType<?>> states() { return AN_INPUT; }
        @Override public List<EventType<?>> events() { return EDITED; }
        @Override public Input parent() { return Input.INSTANCE; }
    }

    /** One thing on or off, by a mark in a box - the browser's own. */
    public record Checkbox() implements Component<Input> {
        public static final Checkbox INSTANCE = new Checkbox();
        @Override public List<StateType<?>> states() { return A_CHECK; }
        @Override public List<EventType<?>> events() { return COMMITTED; }
        @Override public Input parent() { return Input.INSTANCE; }
    }

    /** A pane, picked by a small picture of where it is, with its name: a thumbnail's, a grid's mirror's. */
    public record PaneThumb() implements Component<Selector> {
        public static final PaneThumb INSTANCE = new PaneThumb();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Selector parent() { return Selector.INSTANCE; }
        /** The one its owner's cursor is at. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Current.INSTANCE); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Name.INSTANCE).one());
        }
    }

    /** One tab of a strip: what a pane holds, picked. Its label and its icon slot are its own. */
    public record Tab() implements Component<Selector> {
        public static final Tab INSTANCE = new Tab();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Selector parent() { return Selector.INSTANCE; }
        /** The pointer over it; the one in use; in the hand; the hand down on it. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Hovered.INSTANCE, HouseStates.Selected.INSTANCE,
                HouseStates.Dragging.INSTANCE, HouseStates.Down.INSTANCE); }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.SIZE, ExtentAxis.ASPECT); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.Icon.INSTANCE).as(HouseSaying.Keys.INSTANCE).one(),
                           DSL.part(CloseButton.INSTANCE).as(HouseDoing.Close.INSTANCE).optional());
        }
    }

    /** A site's mark and name, a way home. */
    public record Brand() implements Component<Way> {
        public static final Brand INSTANCE = new Brand();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Way parent() { return Way.INSTANCE; }
        @Override public List<StateType<?>> states() { return A_BRAND; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.BrandMark.INSTANCE).as(HouseSaying.Symbol.INSTANCE).one(),
                           DSL.part(HouseText.Wordmark.INSTANCE).as(HouseSaying.Name.INSTANCE).one());
        }
    }

    /** The button that opens a site's preferences in a dialog, a slot its page: what is shown there is a widget, the site's. */
    public record PreferencesButton() implements Component<Button> {
        public static final PreferencesButton INSTANCE = new PreferencesButton();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.Icon.INSTANCE).as(HouseSaying.Symbol.INSTANCE).one(),
                           DSL.part(HouseContainers.Dialog.INSTANCE).as(HouseShaping.Window.INSTANCE).optional(),
                           DSL.part(HouseRegions.WidgetSlot.INSTANCE).as(HouseShaping.Page.INSTANCE).optional());
        }
    }

    /** A setting that is on or off: a knob in a groove. Its word is its own. */
    public record Switch() implements Component<Input> {
        public static final Switch INSTANCE = new Switch();
        @Override public List<StateType<?>> states() { return A_CHECK; }
        @Override public List<EventType<?>> events() { return COMMITTED; }
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Input parent() { return Input.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.ToggleGroove.INSTANCE).as(HouseSaying.Span.INSTANCE).one(),
                           DSL.part(ToggleKnob.INSTANCE).as(HouseDoing.Thumb.INSTANCE).one());
        }
    }

    /** A value picked along a track, with its label, its scale and its readout. The rail is its own. */
    public record Slider() implements Component<Input> {
        public static final Slider INSTANCE = new Slider();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Input parent() { return Input.INSTANCE; }
        /** Where the page's keys are, held by the steward on its root (RFC 0067, D12). */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Enabled.INSTANCE, HouseStates.Keyboard.INSTANCE); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Label.INSTANCE).as(HouseSaying.Name.INSTANCE).one(),
                           DSL.part(HouseMarks.SliderRail.INSTANCE).as(HouseShaping.Editor.INSTANCE).one(),
                           DSL.part(HouseMarks.SliderGroove.INSTANCE).as(HouseSaying.Span.INSTANCE).one(),
                           DSL.part(HouseMarks.Fill.INSTANCE).as(HouseSaying.Level.INSTANCE).one(),
                           DSL.part(HouseMarks.Detent.INSTANCE).as(HouseSaying.Rest.INSTANCE).optional(),
                           DSL.part(HouseMarks.Tick.INSTANCE).as(HouseSaying.Notch.INSTANCE).any(),
                           DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Figure.INSTANCE).any(),
                           DSL.part(SliderKnob.INSTANCE).as(HouseDoing.Thumb.INSTANCE).one(),
                           DSL.part(HouseMarks.Icon.INSTANCE).as(HouseSaying.Symbol.INSTANCE).one(),
                           DSL.part(HouseText.Readout.INSTANCE).as(HouseSaying.Value.INSTANCE).one());
        }
    }
}
