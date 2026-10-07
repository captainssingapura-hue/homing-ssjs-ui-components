package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Button;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Control;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseBranches.Handle;

/**
 * The house's controls: what a user operates. A button's look fixed by construction is a button
 * of its own - plain, primary, danger and the rest. Their parts are to be declared as slots over
 * the house's role catalogue ({@link HouseRoleCatalogue}); the first cut's roles nested in their
 * owners are gone.
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

    /** What is grabbed to move a value along a track. */
    public record Knob() implements Component<Handle> {
        public static final Knob INSTANCE = new Knob();
        @Override public Handle parent() { return Handle.INSTANCE; }
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

    /** One tab of a strip: what a pane holds, picked. */
    public record Tab() implements Component<Control> {
        public static final Tab INSTANCE = new Tab();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** A value picked along a scale. */
    public record Range() implements Component<Control> {
        public static final Range INSTANCE = new Range();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** A setting that is on or off. */
    public record Switch() implements Component<Control> {
        public static final Switch INSTANCE = new Switch();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** A pane, picked by its thumbnail. */
    public record PaneThumb() implements Component<Control> {
        public static final PaneThumb INSTANCE = new PaneThumb();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** The site's mark and name, a way home. */
    public record Brand() implements Component<Control> {
        public static final Brand INSTANCE = new Brand();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** The button that opens the site's preferences in a dialog. */
    public record PreferencesButton() implements Component<Control> {
        public static final PreferencesButton INSTANCE = new PreferencesButton();
        @Override public Control parent() { return Control.INSTANCE; }
    }

    /** A value picked along a track, with its label, its scale and its readout. */
    public record Slider() implements Component<Control> {
        public static final Slider INSTANCE = new Slider();
        @Override public Control parent() { return Control.INSTANCE; }
    }
}
