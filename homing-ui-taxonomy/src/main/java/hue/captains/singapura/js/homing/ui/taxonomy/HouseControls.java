package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.ComponentPartDSL;
import hue.captains.singapura.js.homing.component.taxonomy.Slot;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Button;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Control;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Handle;

import java.util.List;

/**
 * The house's controls: what a user operates. A button's look fixed by construction is a button
 * of its own - plain, primary, danger and the rest. Each declares its parts as slots over the
 * house's role catalogue ({@link HouseRoleCatalogue}); a part a control draws for itself alone is
 * its own, and no slot.
 */
public final class HouseControls {

    private HouseControls() {}

    // ── buttons ──────────────────────────────────────────────────────────

    /** A button with no emphasis. */
    public record PlainButton() implements Component<Button> {
        public static final PlainButton INSTANCE = new PlainButton();
        @Override public Button parent() { return Button.INSTANCE; }
    }

    /** The one action a view leads with. */
    public record PrimaryButton() implements Component<Button> {
        public static final PrimaryButton INSTANCE = new PrimaryButton();
        @Override public Button parent() { return Button.INSTANCE; }
    }

    /** An action beside the primary one. */
    public record SecondaryButton() implements Component<Button> {
        public static final SecondaryButton INSTANCE = new SecondaryButton();
        @Override public Button parent() { return Button.INSTANCE; }
    }

    /** An action that destroys or cannot be undone. */
    public record DangerButton() implements Component<Button> {
        public static final DangerButton INSTANCE = new DangerButton();
        @Override public Button parent() { return Button.INSTANCE; }
    }

    /** An action that needs a second thought. */
    public record WarningButton() implements Component<Button> {
        public static final WarningButton INSTANCE = new WarningButton();
        @Override public Button parent() { return Button.INSTANCE; }
    }

    /** An action that confirms what went well. */
    public record SuccessButton() implements Component<Button> {
        public static final SuccessButton INSTANCE = new SuccessButton();
        @Override public Button parent() { return Button.INSTANCE; }
    }

    /** A button that is on or off. */
    public record ToggleButton() implements Component<Button> {
        public static final ToggleButton INSTANCE = new ToggleButton();
        @Override public Button parent() { return Button.INSTANCE; }
    }

    /** A button that is an icon alone. */
    public record IconButton() implements Component<Button> {
        public static final IconButton INSTANCE = new IconButton();
        @Override public Button parent() { return Button.INSTANCE; }
    }

    /** The button that closes what holds it. */
    public record CloseButton() implements Component<Button> {
        public static final CloseButton INSTANCE = new CloseButton();
        @Override public Button parent() { return Button.INSTANCE; }
    }

    // ── handles ──────────────────────────────────────────────────────────

    /** What is grabbed to move a value along a track; its face is its own, and upright it is a cap. */
    public record Knob() implements Component<Handle> {
        public static final Knob INSTANCE = new Knob();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Handle parent() { return Handle.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.Icon.INSTANCE).as(HouseSaying.Symbol.INSTANCE).one());
        }
    }

    /** What is grabbed to move the thing it is on. */
    public record Grip() implements Component<Handle> {
        public static final Grip INSTANCE = new Grip();
        @Override public Handle parent() { return Handle.INSTANCE; }
    }

    /** What is grabbed to move the line between two regions. */
    public record Divider() implements Component<Handle> {
        public static final Divider INSTANCE = new Divider();
        @Override public Handle parent() { return Handle.INSTANCE; }
    }

    // ── other controls ───────────────────────────────────────────────────

    /** A way to somewhere else. */
    public record Link() implements Component<Control> {
        public static final Link INSTANCE = new Link();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** One step of a trail back. */
    public record Crumb() implements Component<Control> {
        public static final Crumb INSTANCE = new Crumb();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** One tab of a strip: what a pane holds, picked. Its label and its icon slot are its own. */
    public record Tab() implements Component<Control> {
        public static final Tab INSTANCE = new Tab();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Control parent() { return Control.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.Icon.INSTANCE).as(HouseSaying.Keys.INSTANCE).one(),
                           DSL.part(CloseButton.INSTANCE).as(HouseDoing.Close.INSTANCE).optional());
        }
    }

    /** A value picked along a scale. */
    public record Range() implements Component<Control> {
        public static final Range INSTANCE = new Range();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** A value picked from a list that drops down. */
    public record Select() implements Component<Control> {
        public static final Select INSTANCE = new Select();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** A setting that is on or off: a knob in a groove. Its word is its own. */
    public record Switch() implements Component<Control> {
        public static final Switch INSTANCE = new Switch();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Control parent() { return Control.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.Groove.INSTANCE).as(HouseSaying.Span.INSTANCE).one(),
                           DSL.part(Knob.INSTANCE).as(HouseDoing.Thumb.INSTANCE).one());
        }
    }

    /** A pane, picked by its thumbnail. */
    public record PaneThumb() implements Component<Control> {
        public static final PaneThumb INSTANCE = new PaneThumb();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Control parent() { return Control.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Kicker.INSTANCE).as(HouseSaying.Name.INSTANCE).one());
        }
    }

    /** The site's mark and name, a way home. */
    public record Brand() implements Component<Control> {
        public static final Brand INSTANCE = new Brand();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Control parent() { return Control.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.Icon.INSTANCE).as(HouseSaying.Symbol.INSTANCE).one(),
                           DSL.part(HouseText.Wordmark.INSTANCE).as(HouseSaying.Name.INSTANCE).one());
        }
    }

    /** The button that opens the site's preferences in a dialog, the preferences' view its page. */
    public record PreferencesButton() implements Component<Control> {
        public static final PreferencesButton INSTANCE = new PreferencesButton();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Control parent() { return Control.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseMarks.Icon.INSTANCE).as(HouseSaying.Symbol.INSTANCE).one(),
                           DSL.part(HouseContainers.Dialog.INSTANCE).as(HouseShaping.Window.INSTANCE).optional(),
                           DSL.part(HouseContainers.PreferencesView.INSTANCE).as(HouseShaping.Page.INSTANCE).optional());
        }
    }

    /** A value picked along a track, with its label, its scale and its readout. The rail is its own. */
    public record Slider() implements Component<Control> {
        public static final Slider INSTANCE = new Slider();
        private static final ComponentPartDSL DSL = ComponentPartDSL.INSTANCE;
        @Override public Control parent() { return Control.INSTANCE; }
        @Override public List<Slot<?>> parts() {
            return List.of(DSL.part(HouseText.Label.INSTANCE).as(HouseSaying.Name.INSTANCE).one(),
                           DSL.part(HouseMarks.Groove.INSTANCE).as(HouseSaying.Span.INSTANCE).one(),
                           DSL.part(HouseMarks.Fill.INSTANCE).as(HouseSaying.Level.INSTANCE).one(),
                           DSL.part(HouseMarks.Detent.INSTANCE).as(HouseSaying.Rest.INSTANCE).optional(),
                           DSL.part(HouseMarks.Tick.INSTANCE).as(HouseSaying.Notch.INSTANCE).any(),
                           DSL.part(HouseText.Caption.INSTANCE).as(HouseSaying.Figure.INSTANCE).any(),
                           DSL.part(Knob.INSTANCE).as(HouseDoing.Thumb.INSTANCE).one(),
                           DSL.part(HouseText.Readout.INSTANCE).as(HouseSaying.Value.INSTANCE).one());
        }
    }
}
