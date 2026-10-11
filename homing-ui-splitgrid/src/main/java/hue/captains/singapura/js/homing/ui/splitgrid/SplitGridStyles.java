package hue.captains.singapura.js.homing.ui.splitgrid;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.CssVar;

import java.util.List;
import java.util.Set;

/**
 * The split grid's classes, every one of them placement. A split is a flex row or column; each
 * child takes its share - its track - through {@code --sg-ratio}, and keeps its least through
 * {@code --sg-least}, both set at runtime and read here; a cell is a box its owner fills, and the
 * grid never knows with what. The line where two cells meet is the grid's part in the house's look
 * - a boundary line, its thickness and its colour the theme's - so all that is said of it here is
 * where it goes; on a resizable grid the handle sizing arms on it brings its cursor and its reach.
 * The bodies hold nothing that could be a value.
 */
public record SplitGridStyles() implements CssGroup<SplitGridStyles> {

    public static final SplitGridStyles INSTANCE = new SplitGridStyles();

    /**
     * The root: fills its host by growing and stretching, so the host is a
     * flex box — a column or a row — and the grid is its item. Never a
     * percentage height, which a flex host cannot resolve and which would
     * keep the root from stretching. {@code --sg-min} is the smallest a cell
     * may be, set once here.
     */
    public record sg_root() implements CssClass<SplitGridStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sg-min")); }
        @Override public String body() { return """
            display: flex;
            width: 100%;
            flex: 1 1 auto;
            align-self: stretch;
            box-sizing: border-box;
            min-width: 0;
            min-height: 0;
            """;
        }
    }

    /** A split: its children in a row or a column, sharing its space. */
    public record sg_split() implements CssClass<SplitGridStyles> {
        @Override public String body() { return """
            display: flex;
            flex: 1 1 0%;
            min-width: 0;
            min-height: 0;
            """;
        }
    }

    public record sg_split_h() implements CssClass<SplitGridStyles> {
        @Override public String body() { return "flex-direction: row;"; }
    }

    public record sg_split_v() implements CssClass<SplitGridStyles> {
        @Override public String body() { return "flex-direction: column;"; }
    }

    /** A child of a split: its share of the space is its ratio, and no less than its least. */
    public record sg_child() implements CssClass<SplitGridStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sg-ratio")); }
        @Override public String body() { return """
            display: flex;
            flex: var(--sg-ratio, 1) 1 0%;
            min-width: 0;
            min-height: 0;
            overflow: hidden;
            """;
        }
    }

    /** Along a row: its least is its width - a cell's, or the cells a split in it holds across it. */
    public record sg_child_h() implements CssClass<SplitGridStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sg-least"), new CssVar("--sg-min")); }
        @Override public String body() { return "min-width: var(--sg-least, var(--sg-min, 40px));"; }
    }

    /** Along a column: its least is its height. */
    public record sg_child_v() implements CssClass<SplitGridStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sg-least"), new CssVar("--sg-min")); }
        @Override public String body() { return "min-height: var(--sg-least, var(--sg-min, 40px));"; }
    }

    /** A cell: the box the owner fills. It scrolls nothing itself; what is put in it decides. */
    public record sg_cell() implements CssClass<SplitGridStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            min-width: 0;
            min-height: 0;
            position: relative;
            isolation: isolate;
            display: flex;
            flex-direction: column;
            """;
        }
    }

    /**
     * The line where two cells meet, placed: between them, never shrinking or growing, as long as the
     * split is across it. How thick it is and what colour - or whether it shows - is its look's.
     */
    public record sg_line() implements CssClass<SplitGridStyles> {
        @Override public String body() { return """
            flex: 0 0 auto;
            align-self: stretch;
            box-sizing: border-box;
            """;
        }
    }

    /** A line no hand moves - the grid is not resizable: it takes no pointer, so nothing over it answers one. */
    public record sg_line_fixed() implements CssClass<SplitGridStyles> {
        @Override public String body() { return "pointer-events: none;"; }
    }

    // ── The mirror ────────────────────────────────────────────────────────

    /**
     * The mirror's box, placed: the grid's size at scale ({@code --sgm-w}, {@code --sgm-h}),
     * the cells drawn in it. It paints nothing - its look is the house's, its casting's.
     */
    public record sgm_root() implements CssClass<SplitGridStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sgm-w"), new CssVar("--sgm-h")); }
        @Override public String body() { return """
            position: relative;
            box-sizing: content-box;
            width: var(--sgm-w, 0px);
            height: var(--sgm-h, 0px);
            overflow: hidden;
            """;
        }
    }

    /**
     * A cell in the mirror, placed: at the cell's place and measure, scaled ({@code --sgm-x},
     * {@code --sgm-y}, {@code --sgm-w}, {@code --sgm-h}), its name in the middle. It paints nothing -
     * a cell is a pane's thumbnail, and wears that look; current is its aria-current.
     */
    public record sgm_cell() implements CssClass<SplitGridStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--sgm-x"), new CssVar("--sgm-y"), new CssVar("--sgm-w"), new CssVar("--sgm-h")); }
        @Override public String body() { return """
            position: absolute;
            box-sizing: border-box;
            left: var(--sgm-x);
            top: var(--sgm-y);
            width: var(--sgm-w);
            height: var(--sgm-h);
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
            """;
        }
    }

    /** A cell's name, placed: on one line, cut short where the cell is narrower than it. */
    public record sgm_name() implements CssClass<SplitGridStyles> {
        @Override public String body() { return """
            max-width: 100%;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            """;
        }
    }

    @Override
    public List<CssClass<SplitGridStyles>> cssClasses() {
        return List.of(new sg_root(), new sg_split(), new sg_split_h(), new sg_split_v(), new sg_child(), new sg_child_h(), new sg_child_v(),
                       new sg_cell(), new sg_line(), new sg_line_fixed(),
                       new sgm_root(), new sgm_cell(), new sgm_name());
    }
}
