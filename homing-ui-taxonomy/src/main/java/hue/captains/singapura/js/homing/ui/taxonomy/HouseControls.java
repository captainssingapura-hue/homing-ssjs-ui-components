package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Cardinality;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Composite;
import hue.captains.singapura.js.homing.component.taxonomy.EventType;
import hue.captains.singapura.js.homing.component.taxonomy.ExtentAxis;
import hue.captains.singapura.js.homing.component.taxonomy.Leaf;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.component.taxonomy.StateType;
import hue.captains.singapura.js.homing.component.taxonomy.WithRoles;
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

    /** What every button has: it may be off; how many times it was clicked; and the browser's - the pointer over it, its focus shown, the hand down on it. */
    private static final List<StateType<?>> A_BUTTON = List.of(HouseStates.Enabled.INSTANCE, HouseStates.Clicks.INSTANCE,
            HouseStates.Hovered.INSTANCE, HouseStates.Focused.INSTANCE, HouseStates.Down.INSTANCE);

    /** What a toggle has instead of a count: it may be on - what its click flips. */
    private static final List<StateType<?>> A_TOGGLE = List.of(HouseStates.Enabled.INSTANCE, HouseStates.Pressed.INSTANCE,
            HouseStates.Hovered.INSTANCE, HouseStates.Focused.INSTANCE, HouseStates.Down.INSTANCE);

    /** A crumb's states: whether it is the step that is this page; how many times it was clicked. */
    private static final List<StateType<?>> A_CRUMB = List.of(HouseStates.Current.INSTANCE, HouseStates.Clicks.INSTANCE,
            HouseStates.Hovered.INSTANCE, HouseStates.Focused.INSTANCE);

    /** What the browser's own inputs have: they may be off; their focus shown. */
    private static final List<StateType<?>> AN_INPUT = List.of(HouseStates.Enabled.INSTANCE, HouseStates.Focused.INSTANCE);

    /** What a box ticked, or a switch, has besides: it may be on. */
    private static final List<StateType<?>> A_CHECK = List.of(HouseStates.Enabled.INSTANCE, HouseStates.Checked.INSTANCE,
            HouseStates.Focused.INSTANCE);

    /** What the range has: it may be off; its degree; whether the hand holds it; its focus shown. */
    private static final List<StateType<?>> A_RANGE = List.of(HouseStates.Enabled.INSTANCE, HouseStates.Degree.INSTANCE,
            HouseStates.Dragging.INSTANCE, HouseStates.Focused.INSTANCE);

    /** What a text box has besides: what is typed so far, and what the user is done typing. */
    private static final List<StateType<?>> A_TEXT_BOX = List.of(HouseStates.Enabled.INSTANCE, HouseStates.DraftText.INSTANCE,
            HouseStates.CommittedText.INSTANCE, HouseStates.Focused.INSTANCE);

    /** What a select has besides: the option chosen. */
    private static final List<StateType<?>> A_SELECT = List.of(HouseStates.Enabled.INSTANCE, HouseStates.ChosenOption.INSTANCE,
            HouseStates.Focused.INSTANCE);

    /** What the slider's knob has: it may be off; it stands up or lies along; its focus shown; the current of a group; dragged. */
    private static final List<StateType<?>> A_SLIDER_KNOB = List.of(HouseStates.Enabled.INSTANCE, HouseStates.Orientation.INSTANCE,
            HouseStates.Focused.INSTANCE, HouseStates.Current.INSTANCE, HouseStates.Dragging.INSTANCE);

    /** What a way somewhere has: how many times it was clicked; the browser's - the pointer over it, its focus shown. */
    private static final List<StateType<?>> A_WAY = List.of(HouseStates.Clicks.INSTANCE, HouseStates.Hovered.INSTANCE, HouseStates.Focused.INSTANCE);

    /** What the brand has: its focus shown. */
    private static final List<StateType<?>> A_BRAND = List.of(HouseStates.Focused.INSTANCE);

    // ── buttons ──────────────────────────────────────────────────────────

    /** A button with no emphasis. */
    public record PlainButton() implements Leaf<Button>, WithRoles<HouseRoleSets.PlainButton> {
        public static final PlainButton INSTANCE = new PlainButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public HouseRoleSets.PlainButton roleSet() { return HouseRoleSets.PlainButton.INSTANCE; }
    }

    /** The one action a view leads with. */
    public record PrimaryButton() implements Leaf<Button>, WithRoles<HouseRoleSets.Committing> {
        public static final PrimaryButton INSTANCE = new PrimaryButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public HouseRoleSets.Committing roleSet() { return HouseRoleSets.Committing.INSTANCE; }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.COLOUR); }
    }

    /** An action beside the primary one. */
    public record SecondaryButton() implements Leaf<Button> {
        public static final SecondaryButton INSTANCE = new SecondaryButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.COLOUR); }
    }

    /** An action that destroys or cannot be undone. */
    public record DangerButton() implements Leaf<Button> {
        public static final DangerButton INSTANCE = new DangerButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.COLOUR); }
    }

    /** An action that needs a second thought. */
    public record WarningButton() implements Leaf<Button> {
        public static final WarningButton INSTANCE = new WarningButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.COLOUR); }
    }

    /** An action that confirms what went well. */
    public record SuccessButton() implements Leaf<Button> {
        public static final SuccessButton INSTANCE = new SuccessButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.COLOUR); }
    }

    /** A button that is on or off. */
    public record ToggleButton() implements Leaf<Button> {
        public static final ToggleButton INSTANCE = new ToggleButton();
        @Override public List<StateType<?>> states() { return A_TOGGLE; }
        @Override public Button parent() { return Button.INSTANCE; }
    }

    /**
     * The function of a button, the look of an icon: its face an icon, its part - which icon, chosen once in its fixed
     * object. It has no role set: what holds it places it, in the void role, its icon saying what it does (owner, 21 Oct);
     * its icon is placed in the void role too.
     */
    public record IconButton() implements Composite<Button> {
        public static final IconButton INSTANCE = new IconButton();
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.anyOf(HouseBranches.Icon.INSTANCE).as(HouseShaping.VoidRole.INSTANCE).one());
        }
    }

    /** The button that closes what holds it. */
    public record CloseButton() implements Composite<Button>, WithRoles<HouseRoleSets.Managing> {
        public static final CloseButton INSTANCE = new CloseButton();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public List<StateType<?>> states() { return A_BUTTON; }
        @Override public Button parent() { return Button.INSTANCE; }
        @Override public HouseRoleSets.Managing roleSet() { return HouseRoleSets.Managing.INSTANCE; }
        /** Its face the close icon: the same cross wherever something closes, drawn once in each style. */
        @Override public List<Slot<?>> parts() { return List.of(DSL.part(HouseIcons.CloseIcon.INSTANCE).as(HouseShaping.Face.INSTANCE).one()); }
    }

    // ── handles ──────────────────────────────────────────────────────────

    /** The slider's knob: what is grabbed to move its value along its groove; upright it is a cap. Never alone: a slider's part. */
    public record SliderKnob() implements Leaf<Handle>, WithRoles<HouseRoleSets.Changing> {
        public static final SliderKnob INSTANCE = new SliderKnob();
        @Override public Handle parent() { return Handle.INSTANCE; }
        @Override public HouseRoleSets.Changing roleSet() { return HouseRoleSets.Changing.INSTANCE; }
        @Override public List<StateType<?>> states() { return A_SLIDER_KNOB; }
        /** Grown by its slider's size, as its slider is. */
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.SIZE); }
    }

    /** The toggle's knob: what shows which way a switch stands, sliding in its groove. Never alone: a switch's part. */
    public record ToggleKnob() implements Leaf<Handle>, WithRoles<HouseRoleSets.Changing> {
        public static final ToggleKnob INSTANCE = new ToggleKnob();
        @Override public Handle parent() { return Handle.INSTANCE; }
        @Override public HouseRoleSets.Changing roleSet() { return HouseRoleSets.Changing.INSTANCE; }
    }

    /** What is grabbed to move the thing it is on. */
    public record Grip() implements Leaf<Handle> {
        public static final Grip INSTANCE = new Grip();
        @Override public Handle parent() { return Handle.INSTANCE; }
        @Override public List<StateType<?>> states() { return AN_INPUT; }
    }

    /** The thin band at an edge the hand comes to: reaching it shows the strip it belongs to. */
    public record Lip() implements Leaf<Handle> {
        public static final Lip INSTANCE = new Lip();
        @Override public Handle parent() { return Handle.INSTANCE; }
    }


    // ── other controls ───────────────────────────────────────────────────

    /** A way to somewhere else. */
    public record Link() implements Leaf<Way>, WithRoles<HouseRoleSets.Going> {
        public static final Link INSTANCE = new Link();
        @Override public Way parent() { return Way.INSTANCE; }
        @Override public HouseRoleSets.Going roleSet() { return HouseRoleSets.Going.INSTANCE; }
        @Override public List<StateType<?>> states() { return A_WAY; }
        /** At a size, as its owner gives it. */
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.SIZE); }
    }

    /** One step of a trail back. */
    public record Crumb() implements Leaf<Way>, WithRoles<HouseRoleSets.Going> {
        public static final Crumb INSTANCE = new Crumb();
        @Override public Way parent() { return Way.INSTANCE; }
        @Override public HouseRoleSets.Going roleSet() { return HouseRoleSets.Going.INSTANCE; }
        @Override public List<StateType<?>> states() { return A_CRUMB; }
    }

    /** A degree picked along the browser's own slider: what it is read as - a volume, a gain - is its caller's. */
    public record Range() implements Leaf<Input>, WithRoles<HouseRoleSets.Changing> {
        public static final Range INSTANCE = new Range();
        @Override public List<StateType<?>> states() { return A_RANGE; }
        @Override public Input parent() { return Input.INSTANCE; }
        @Override public HouseRoleSets.Changing roleSet() { return HouseRoleSets.Changing.INSTANCE; }
    }

    /** A value picked from a list that drops down. */
    public record Select() implements Leaf<Input>, WithRoles<HouseRoleSets.Choosing> {
        public static final Select INSTANCE = new Select();
        @Override public List<StateType<?>> states() { return A_SELECT; }
        @Override public Input parent() { return Input.INSTANCE; }
        @Override public HouseRoleSets.Choosing roleSet() { return HouseRoleSets.Choosing.INSTANCE; }
        /** At a size, as its owner gives it. */
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.SIZE); }
    }

    /** Words the user types: a line of them, or as many as they write - the browser's own editing. */
    public record TextBox() implements Leaf<Input> {
        public static final TextBox INSTANCE = new TextBox();
        @Override public List<StateType<?>> states() { return A_TEXT_BOX; }
        @Override public Input parent() { return Input.INSTANCE; }
        /** At a size, as its owner gives it. */
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.SIZE); }
    }

    /** One thing on or off, by a mark in a box - the browser's own. */
    public record Checkbox() implements Leaf<Input> {
        public static final Checkbox INSTANCE = new Checkbox();
        @Override public List<StateType<?>> states() { return A_CHECK; }
        @Override public Input parent() { return Input.INSTANCE; }
    }

    /** A pane, picked by a small picture of where it is, with its name: a thumbnail's, a grid's mirror's. */
    public record PaneThumb() implements Composite<Selector> {
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
    public record Tab() implements Composite<Selector> {
        public static final Tab INSTANCE = new Tab();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Selector parent() { return Selector.INSTANCE; }
        /** The pointer over it; the one in use; where the keys are - held while the cursor is on its strip, lent while what it holds has them; in the hand; the hand down on it. */
        @Override public List<StateType<?>> states() { return List.of(HouseStates.Hovered.INSTANCE, HouseStates.Selected.INSTANCE,
                HouseStates.Keyboard.INSTANCE, HouseStates.Dragging.INSTANCE, HouseStates.Down.INSTANCE); }
        /** The hand down on it, not on its cross: what selects it; its cross pressed: it asks to go. */
        @Override public List<EventType<?>> events() { return List.of(HouseEvents.Press.INSTANCE, HouseEvents.Dismiss.INSTANCE); }
        @Override public List<ExtentAxis> extents() { return List.of(ExtentAxis.SIZE, ExtentAxis.ASPECT); }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseIcons.WithinIcon.INSTANCE).as(HouseShaping.Keys.INSTANCE).one(),
                           DSL.part(CloseButton.INSTANCE).as(HouseDoing.Close.INSTANCE).optional());
        }
    }

    /** A site's mark and name, a way home. */
    public record Brand() implements Composite<Way>, WithRoles<HouseRoleSets.Going> {
        public static final Brand INSTANCE = new Brand();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Way parent() { return Way.INSTANCE; }
        @Override public HouseRoleSets.Going roleSet() { return HouseRoleSets.Going.INSTANCE; }
        @Override public List<StateType<?>> states() { return A_BRAND; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.BrandMark.INSTANCE).as(HouseSaying.Symbol.INSTANCE).one(),
                           DSL.part(HouseText.Wordmark.INSTANCE).as(HouseSaying.Name.INSTANCE).one());
        }
    }

    /** A setting that is on or off: a knob in a groove. Its word is its own. */
    public record Switch() implements Composite<Input>, WithRoles<HouseRoleSets.Changing> {
        public static final Switch INSTANCE = new Switch();
        @Override public List<StateType<?>> states() { return A_CHECK; }
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Input parent() { return Input.INSTANCE; }
        @Override public HouseRoleSets.Changing roleSet() { return HouseRoleSets.Changing.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.ToggleGroove.INSTANCE).as(HouseSaying.Span.INSTANCE).one(),
                           DSL.part(ToggleKnob.INSTANCE).as(HouseDoing.Thumb.INSTANCE).one());
        }
    }

    /** A value picked along a track, with its label, its scale and its readout. The rail is its own. */
    public record Slider() implements Composite<Input> {
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
                           DSL.part(HouseMarks.Tick.INSTANCE).as(HouseSaying.Notch.INSTANCE).any(Cardinality.DYNAMIC),
                           DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Figure.INSTANCE).any(Cardinality.DYNAMIC),
                           DSL.part(SliderKnob.INSTANCE).as(HouseDoing.Thumb.INSTANCE).one(),
                           DSL.part(HouseIcons.GripIcon.INSTANCE, HouseIcons.SizeIcon.INSTANCE, HouseIcons.AspectIcon.INSTANCE,
                                    HouseIcons.ExtentIcon.INSTANCE, HouseIcons.LevelIcon.INSTANCE).as(HouseShaping.Measure.INSTANCE).optional(),
                           DSL.part(HouseText.Readout.INSTANCE).as(HouseSaying.Value.INSTANCE).one());
        }
    }
}
