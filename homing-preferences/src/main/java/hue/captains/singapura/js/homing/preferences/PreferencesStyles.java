package hue.captains.singapura.js.homing.preferences;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.CssVar;
import java.util.List;
import java.util.Set;


/**
 * The view's two panes and the widgets' fields. Words worn, layout in the
 * bodies; the master's rows are the tree view's own and wear nothing here.
 */
public record PreferencesStyles() implements CssGroup<PreferencesStyles> {

    public static final PreferencesStyles INSTANCE = new PreferencesStyles();

    /** Master beside detail; the master as wide as its names, the detail the rest. */
    public record pv_root() implements CssClass<PreferencesStyles> {
        @Override public String body() { return """
            display: flex;
            min-height: 360px;
            flex: 1 1 auto;
            align-self: stretch;
            min-width: 0;
            """;
        }
    }

    /** The master pane: divided from the detail. */
    public record pv_master() implements CssClass<PreferencesStyles> {
        @Override public String body() { return """
            padding: 8px 8px 8px 0;
            min-width: 0;
            overflow: auto;
            """;
        }
    }

    /** The detail pane: the chosen node's widget. */
    public record pv_detail() implements CssClass<PreferencesStyles> {
        @Override public String body() { return """
            padding: 4px 0 8px 24px;
            min-width: 0;
            overflow: auto;
            display: flex;
            flex-direction: column;
            """;
        }
    }

    // ── A widget's furniture ──────────────────────────────────────────────────

    public record pv_kicker() implements CssClass<PreferencesStyles> {
        @Override public String body() { return "margin: 0 0 4px;"; }
    }

    public record pv_title() implements CssClass<PreferencesStyles> {
        @Override public String body() { return "margin: 0 0 6px;"; }
    }

    public record pv_summary() implements CssClass<PreferencesStyles> {
        @Override public String body() { return "margin: 0 0 18px;"; }
    }

    /** A line about the value: what it is, where it came from. */
    public record pv_note() implements CssClass<PreferencesStyles> {
        @Override public String body() { return "margin: 12px 0 0;"; }
    }

    /** The row of what a widget lets you do below its control. */
    public record pv_actions() implements CssClass<PreferencesStyles> {
        @Override public String body() { return """
            display: flex;
            gap: 10px;
            margin-top: 16px;
            """;
        }
    }

    // ── Choice ────────────────────────────────────────────────────────────────

    public record pv_options() implements CssClass<PreferencesStyles> {
        @Override public String body() { return """
            display: flex;
            flex-direction: column;
            gap: 2px;
            margin: 0;
            padding: 0;
            list-style: none;
            max-width: 480px;
            """;
        }
    }

    /** An option, across the list; it lays out its name and its note itself - the house's choice option. */
    public record pv_option() implements CssClass<PreferencesStyles> {
        @Override public String body() { return "width: 100%;"; }
    }

    // ── Scale ─────────────────────────────────────────────────────────────────

    /** The range: as wide as the field allows, drawn in the ink its look sets - the accent - by the browser's own slider. */
    public record pv_range() implements CssClass<PreferencesStyles> {
        @Override public String body() { return """
            width: 100%;
            max-width: 420px;
            accent-color: currentColor;
            """;
        }
    }

    public record pv_readout() implements CssClass<PreferencesStyles> {
        @Override public String body() { return "margin: 8px 0 0;"; }
    }

    // ── The list master ───────────────────────────────────────────────────────

    /** The flat tree: a listbox with a focus ring of its own. */
    public record pv_list() implements CssClass<PreferencesStyles> {
        @Override public String body() { return """
            display: flex;
            flex-direction: column;
            gap: 2px;
            outline-offset: 2px;
            """;
        }
    }

    /** A row of the flat tree, its words set in by its depth, which is data on the row; its look - a listed row's - is the house's. */
    public record pv_list_row() implements CssClass<PreferencesStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--pv-depth")); }
        @Override public String body() { return """
            text-indent: calc(var(--pv-depth, 0) * 16px);
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            """;
        }
    }

    // ── Overview ──────────────────────────────────────────────────────────────

    public record pv_children() implements CssClass<PreferencesStyles> {
        @Override public String body() { return """
            margin: 0;
            padding: 0;
            list-style: none;
            display: flex;
            flex-direction: column;
            gap: 6px;
            """;
        }
    }

    /** A setting's row: its name and, at its end, its value, in a row - the setting row's own layout; its look is its leaf's. */
    public record pv_setting_row() implements CssClass<PreferencesStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: baseline;
            """;
        }
    }

    /** A setting row's value, at its end, cut short where the row ends. */
    public record pv_setting_row_value() implements CssClass<PreferencesStyles> {
        @Override public String body() { return """
            margin-left: auto;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            """;
        }
    }

    @Override
    public List<CssClass<PreferencesStyles>> cssClasses() {
        return List.of(new pv_root(), new pv_master(), new pv_detail(),
                       new pv_kicker(), new pv_title(), new pv_summary(), new pv_note(), new pv_actions(),
                       new pv_options(), new pv_option(),
                       new pv_range(), new pv_readout(),
                       new pv_list(), new pv_list_row(),
                       new pv_children(), new pv_setting_row(), new pv_setting_row_value());
    }
}
