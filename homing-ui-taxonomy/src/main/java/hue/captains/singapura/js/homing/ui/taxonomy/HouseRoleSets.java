package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.js.homing.component.taxonomy.Role;
import hue.captains.singapura.js.homing.component.taxonomy.RoleSet;

import java.util.List;

/**
 * The house's role sets (RFC 0067, D55): what each house component may be cast in, a role with a look of its own. One
 * set for each branch of the catalogue whose roles have one - its roles, as filed - and a union of them for a component
 * cast in several. A set is the most a component may be cast in, and every role of it is one base assigns the
 * component: a casting of its own, cast in yet or not.
 */
public final class HouseRoleSets {

    private HouseRoleSets() {}

    /** Naming, of saying: every role filed under it. */
    public record Naming() implements RoleSet {
        public static final Naming INSTANCE = new Naming();
        @Override public List<Role<?>> roles() {
            return List.of(HouseSaying.Title.INSTANCE, HouseSaying.Subtitle.INSTANCE, HouseSaying.Name.INSTANCE, HouseSaying.Category.INSTANCE, HouseSaying.Symbol.INSTANCE);
        }
    }

    /** Describing, of saying: every role filed under it. */
    public record Describing() implements RoleSet {
        public static final Describing INSTANCE = new Describing();
        @Override public List<Role<?>> roles() {
            return List.of(HouseSaying.Summary.INSTANCE, HouseSaying.Note.INSTANCE, HouseSaying.Tag.INSTANCE);
        }
    }

    /** Guiding, of saying: every role filed under it. */
    public record Guiding() implements RoleSet {
        public static final Guiding INSTANCE = new Guiding();
        @Override public List<Role<?>> roles() {
            return List.of(HouseSaying.Hint.INSTANCE, HouseSaying.Prompt.INSTANCE, HouseSaying.Placeholder.INSTANCE);
        }
    }

    /** Reporting, of saying: every role filed under it. */
    public record Reporting() implements RoleSet {
        public static final Reporting INSTANCE = new Reporting();
        @Override public List<Role<?>> roles() {
            return List.of(HouseSaying.Value.INSTANCE, HouseSaying.Level.INSTANCE, HouseSaying.Count.INSTANCE, HouseSaying.Status.INSTANCE, HouseSaying.Error.INSTANCE);
        }
    }

    /** Signalling, of saying: every role filed under it. */
    public record Signalling() implements RoleSet {
        public static final Signalling INSTANCE = new Signalling();
        @Override public List<Role<?>> roles() {
            return List.of(HouseSaying.Landing.INSTANCE, HouseSaying.Check.INSTANCE);
        }
    }

    /** Scaling, of saying: every role filed under it. */
    public record Scaling() implements RoleSet {
        public static final Scaling INSTANCE = new Scaling();
        @Override public List<Role<?>> roles() {
            return List.of(HouseSaying.Span.INSTANCE, HouseSaying.Rest.INSTANCE, HouseSaying.Notch.INSTANCE, HouseSaying.Figure.INSTANCE);
        }
    }

    /** Choosing, of doing: every role filed under it. */
    public record Choosing() implements RoleSet {
        public static final Choosing INSTANCE = new Choosing();
        @Override public List<Role<?>> roles() {
            return List.of(HouseDoing.Choice.INSTANCE, HouseDoing.Style.INSTANCE, HouseDoing.Palette.INSTANCE, HouseDoing.What.INSTANCE, HouseDoing.How.INSTANCE, HouseDoing.Where.INSTANCE, HouseDoing.Picker.INSTANCE, HouseDoing.Query.INSTANCE);
        }
    }

    /** Committing, of doing: every role filed under it. */
    public record Committing() implements RoleSet {
        public static final Committing INSTANCE = new Committing();
        @Override public List<Role<?>> roles() {
            return List.of(HouseDoing.Go.INSTANCE, HouseDoing.Cancel.INSTANCE, HouseDoing.Action.INSTANCE, HouseDoing.Skip.INSTANCE);
        }
    }

    /** Changing, of doing: every role filed under it. */
    public record Changing() implements RoleSet {
        public static final Changing INSTANCE = new Changing();
        @Override public List<Role<?>> roles() {
            return List.of(HouseDoing.Toggle.INSTANCE, HouseDoing.Adjust.INSTANCE, HouseDoing.Thumb.INSTANCE, HouseDoing.Splitter.INSTANCE, HouseDoing.Reset.INSTANCE, HouseDoing.Increase.INSTANCE, HouseDoing.Decrease.INSTANCE, HouseDoing.Clear.INSTANCE);
        }
    }

    /** Viewing, of doing: every role filed under it. */
    public record Viewing() implements RoleSet {
        public static final Viewing INSTANCE = new Viewing();
        @Override public List<Role<?>> roles() {
            return List.of(HouseDoing.ZoomIn.INSTANCE, HouseDoing.ZoomOut.INSTANCE, HouseDoing.Fit.INSTANCE);
        }
    }

    /** Managing, of doing: every role filed under it. */
    public record Managing() implements RoleSet {
        public static final Managing INSTANCE = new Managing();
        @Override public List<Role<?>> roles() {
            return List.of(HouseDoing.Close.INSTANCE, HouseDoing.Add.INSTANCE, HouseDoing.Remove.INSTANCE);
        }
    }

    /** Going, of doing: every role filed under it. */
    public record Going() implements RoleSet {
        public static final Going INSTANCE = new Going();
        @Override public List<Role<?>> roles() {
            return List.of(HouseDoing.Open.INSTANCE, HouseDoing.Home.INSTANCE, HouseDoing.Preferences.INSTANCE, HouseDoing.Path.INSTANCE, HouseDoing.Step.INSTANCE, HouseDoing.Previous.INSTANCE, HouseDoing.Next.INSTANCE, HouseDoing.First.INSTANCE, HouseDoing.Last.INSTANCE, HouseDoing.More.INSTANCE);
        }
    }

    // ── unions, for a component cast in several branches' roles ──

    /** A plain button: committing, changing, choosing or viewing - whatever its owner has it do. */
    public record PlainButton() implements RoleSet.Union {
        public static final PlainButton INSTANCE = new PlainButton();
        @Override public List<RoleSet> sets() { return List.of(Committing.INSTANCE, Changing.INSTANCE, Choosing.INSTANCE, Viewing.INSTANCE); }
    }

    /** A kicker: naming, guiding or reporting. */
    public record Kicker() implements RoleSet.Union {
        public static final Kicker INSTANCE = new Kicker();
        @Override public List<RoleSet> sets() { return List.of(Naming.INSTANCE, Guiding.INSTANCE, Reporting.INSTANCE); }
    }

    /** A caption: describing, guiding, reporting, or a figure on a scale. */
    public record Caption() implements RoleSet.Union {
        public static final Caption INSTANCE = new Caption();
        @Override public List<RoleSet> sets() { return List.of(Describing.INSTANCE, Guiding.INSTANCE, Reporting.INSTANCE, Scaling.INSTANCE); }
    }

    /** A text cell: naming its row, or reporting its value. */
    public record TextCell() implements RoleSet.Union {
        public static final TextCell INSTANCE = new TextCell();
        @Override public List<RoleSet> sets() { return List.of(Naming.INSTANCE, Reporting.INSTANCE); }
    }
}
