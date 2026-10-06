package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseKinds.Button;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseKinds.Control;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseKinds.Handle;

import java.util.List;

/**
 * The house's controls: what a user operates. A button's look fixed by construction is a button
 * of its own - plain, primary, danger and the rest - and a role names the generic component that
 * plays each part: a slider's knob is a {@link Knob}, its track a {@link HouseMarks.Groove}.
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
        @Override public List<Role<?>> roles() { return List.of(Indicator.INSTANCE, Glyph.INSTANCE, Label.INSTANCE, Close.INSTANCE); }

        public record Indicator() implements Role<HouseMarks.Indicator> { public static final Indicator INSTANCE = new Indicator(); @Override public HouseMarks.Indicator base() { return HouseMarks.Indicator.INSTANCE; } }
        public record Glyph()     implements Role<HouseMarks.Icon>      { public static final Glyph INSTANCE = new Glyph();         @Override public HouseMarks.Icon base()      { return HouseMarks.Icon.INSTANCE; } }
        public record Label()     implements Role<HouseText.Label>      { public static final Label INSTANCE = new Label();         @Override public HouseText.Label base()      { return HouseText.Label.INSTANCE; } }
        public record Close()     implements Role<CloseButton>          { public static final Close INSTANCE = new Close();         @Override public CloseButton base()          { return CloseButton.INSTANCE; } }
    }

    /** A value picked along a scale. */
    public record Range() implements Component<Control> {
        public static final Range INSTANCE = new Range();
        @Override public Control parent() { return Control.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Track.INSTANCE, Fill.INSTANCE, Knob.INSTANCE); }

        public record Track() implements Role<HouseMarks.Groove> { public static final Track INSTANCE = new Track(); @Override public HouseMarks.Groove base() { return HouseMarks.Groove.INSTANCE; } }
        public record Fill()  implements Role<HouseMarks.Fill>   { public static final Fill INSTANCE = new Fill();   @Override public HouseMarks.Fill base()   { return HouseMarks.Fill.INSTANCE; } }
        public record Knob()  implements Role<HouseControls.Knob> { public static final Knob INSTANCE = new Knob();  @Override public HouseControls.Knob base() { return HouseControls.Knob.INSTANCE; } }
    }

    /** A setting that is on or off. */
    public record Switch() implements Component<Control> {
        public static final Switch INSTANCE = new Switch();
        @Override public Control parent() { return Control.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Track.INSTANCE, Knob.INSTANCE); }

        public record Track() implements Role<HouseMarks.Groove>  { public static final Track INSTANCE = new Track(); @Override public HouseMarks.Groove base()  { return HouseMarks.Groove.INSTANCE; } }
        public record Knob()  implements Role<HouseControls.Knob> { public static final Knob INSTANCE = new Knob();   @Override public HouseControls.Knob base() { return HouseControls.Knob.INSTANCE; } }
    }

    /** A pane, picked by its thumbnail. */
    public record PaneThumb() implements Component<Control> {
        public static final PaneThumb INSTANCE = new PaneThumb();
        @Override public Control parent() { return Control.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Name.INSTANCE); }

        public record Name() implements Role<HouseText.Caption> { public static final Name INSTANCE = new Name(); @Override public HouseText.Caption base() { return HouseText.Caption.INSTANCE; } }
    }

    /** The site's mark and name, a way home. */
    public record Brand() implements Component<Control> {
        public static final Brand INSTANCE = new Brand();
        @Override public Control parent() { return Control.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Mark.INSTANCE, Word.INSTANCE); }

        public record Mark() implements Role<HouseMarks.Icon>    { public static final Mark INSTANCE = new Mark(); @Override public HouseMarks.Icon base()    { return HouseMarks.Icon.INSTANCE; } }
        public record Word() implements Role<HouseText.Wordmark> { public static final Word INSTANCE = new Word(); @Override public HouseText.Wordmark base() { return HouseText.Wordmark.INSTANCE; } }
    }

    /** The button that opens the site's preferences in a dialog. */
    public record PreferencesButton() implements Component<Control> {
        public static final PreferencesButton INSTANCE = new PreferencesButton();
        @Override public Control parent() { return Control.INSTANCE; }
        @Override public List<Role<?>> roles() { return List.of(Glyph.INSTANCE, Dialog.INSTANCE, View.INSTANCE); }

        public record Glyph()  implements Role<HouseMarks.Icon>                  { public static final Glyph INSTANCE = new Glyph();   @Override public HouseMarks.Icon base()                  { return HouseMarks.Icon.INSTANCE; } }
        public record Dialog() implements Role<HouseContainers.Dialog>           { public static final Dialog INSTANCE = new Dialog(); @Override public HouseContainers.Dialog base()           { return HouseContainers.Dialog.INSTANCE; } }
        public record View()   implements Role<HouseContainers.PreferencesView>  { public static final View INSTANCE = new View();     @Override public HouseContainers.PreferencesView base()  { return HouseContainers.PreferencesView.INSTANCE; } }
    }

    /** A value picked along a track, with its label, its scale and its readout. */
    public record Slider() implements Component<Control> {
        public static final Slider INSTANCE = new Slider();
        @Override public Control parent() { return Control.INSTANCE; }
        @Override public List<Role<?>> roles() {
            return List.of(Label.INSTANCE, Track.INSTANCE, Fill.INSTANCE, Detent.INSTANCE, Knob.INSTANCE,
                           Tick.INSTANCE, TickLabel.INSTANCE, Readout.INSTANCE, Cap.INSTANCE);
        }

        public record Label()     implements Role<HouseText.Label>    { public static final Label INSTANCE = new Label();         @Override public HouseText.Label base()    { return HouseText.Label.INSTANCE; } }
        public record Track()     implements Role<HouseMarks.Groove>  { public static final Track INSTANCE = new Track();         @Override public HouseMarks.Groove base()  { return HouseMarks.Groove.INSTANCE; } }
        public record Fill()      implements Role<HouseMarks.Fill>    { public static final Fill INSTANCE = new Fill();           @Override public HouseMarks.Fill base()    { return HouseMarks.Fill.INSTANCE; } }
        public record Detent()    implements Role<HouseMarks.Detent>  { public static final Detent INSTANCE = new Detent();       @Override public HouseMarks.Detent base()  { return HouseMarks.Detent.INSTANCE; } }
        public record Knob()      implements Role<HouseControls.Knob> { public static final Knob INSTANCE = new Knob();           @Override public HouseControls.Knob base() { return HouseControls.Knob.INSTANCE; } }
        public record Tick()      implements Role<HouseMarks.Tick>    { public static final Tick INSTANCE = new Tick();           @Override public HouseMarks.Tick base()    { return HouseMarks.Tick.INSTANCE; } }
        public record TickLabel() implements Role<HouseText.Caption>  { public static final TickLabel INSTANCE = new TickLabel(); @Override public HouseText.Caption base()  { return HouseText.Caption.INSTANCE; } }
        public record Readout()   implements Role<HouseText.Readout>  { public static final Readout INSTANCE = new Readout();     @Override public HouseText.Readout base()  { return HouseText.Readout.INSTANCE; } }
        public record Cap()       implements Role<HouseMarks.Icon>    { public static final Cap INSTANCE = new Cap();             @Override public HouseMarks.Icon base()    { return HouseMarks.Icon.INSTANCE; } }
    }
}
