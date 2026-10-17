package hue.captains.singapura.js.homing.ui.monitors;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.CssVar;
import java.util.List;
import java.util.Set;


/** The monitors' classes: the focus monitor's tree of rows, each indented by its depth, the holder's row lit; the steward monitor's lamp. */
public record FocusStyles() implements CssGroup<FocusStyles> {

    public static final FocusStyles INSTANCE = new FocusStyles();

    /** The tree: rows in a column, in a code face. */
    public record fm_tree() implements CssClass<FocusStyles> {
        @Override public String body() { return """
            display: flex;
            flex-direction: column;
            gap: 2px;
            min-width: 0;
            """;
        }
    }

    /** A row: a node of the tree, set in by its depth through the runtime variable the monitor sets; its look - a listed row's, as an entry, ringed where the keys are - is the house's. */
    public record fm_row() implements CssClass<FocusStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--fm-depth")); }
        @Override public String body() { return """
            display: flex;
            align-items: baseline;
            margin-inline-start: calc(var(--fm-depth, 0) * 18px);
            white-space: nowrap;
            """;
        }
    }

    /** The row of the one that holds the keys, marked: its look is the current one's (aria-current), its keys' mark the ring a control wears (data-keys). */
    public record fm_row_holder() implements CssClass<FocusStyles> {
        @Override public String body() { return ""; }
    }

    /** The node's kind: root, branch (a holder of one), leaf. */
    public record fm_kind() implements CssClass<FocusStyles> {
        @Override public String body() { return "flex: none; inline-size: 5.5em; opacity: 0.7;"; }
    }

    /** The node's name. */
    public record fm_name() implements CssClass<FocusStyles> {
        @Override public String body() { return "flex: none;"; }
    }

    /** The component's class, after the name. */
    public record fm_component() implements CssClass<FocusStyles> {
        @Override public String body() { return "opacity: 0.6; overflow: hidden; text-overflow: ellipsis;"; }
    }

    /** A holder that is not in the tree, a member by id the older way, named below it. */
    public record fm_outside() implements CssClass<FocusStyles> {
        @Override public String body() { return "margin-top: 6px; padding: 2px 8px; font-style: italic;"; }
    }

    // ── the steward monitor ───────────────────────────────────────────────

    /** The lamp, on one line; its look is a lamp's - quiet, lit while the keys are held or lent (aria-current), the danger colour while broken (aria-invalid). */
    public record sm_lamp() implements CssClass<FocusStyles> {
        @Override public String body() { return "white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"; }
    }

    @Override
    public List<CssClass<FocusStyles>> cssClasses() {
        return List.of(new fm_tree(), new fm_row(), new fm_row_holder(), new fm_kind(), new fm_name(), new fm_component(), new fm_outside(), new sm_lamp());
    }
}
