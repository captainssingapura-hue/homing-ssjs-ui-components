package hue.captains.singapura.js.homing.ui.menu;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.CssVar;

import java.util.List;
import java.util.Set;


/**
 * The context menus' styles: the layer they show in, the frame that is
 * {@code Container.Menu}, the rows that are {@code Control.Option} and
 * {@code Selectable}, the marks, the hint, the separator; the static frame
 * and the host of a specimen. The frame's place is the geometry's, carried
 * in runtime variables the steward sets. A row's marks are {@code Icon}
 * components, dressed by the icons' crate; here they only take their place.
 * Every look is a design word; the bodies hold layout only.
 */
public record MenuStyles() implements CssGroup<MenuStyles> {

    public static final MenuStyles INSTANCE = new MenuStyles();

    /** The layer: fixed over the page, in the document only while a menu is open; the hand passes through it except on a frame. */
    public record cm_layer() implements CssClass<MenuStyles> {
        @Override public String body() { return """
            position: fixed;
            inset: 0;
            z-index: 10021;
            pointer-events: none;
            """;
        }
    }

    /**
     * A frame: {@code Container.Menu} to the design — its corner, rule and
     * ring the container's, its inset, gap and least width its own —
     * raised — with the raised layer's filter, the glass's blur where a design has one — under the overlay shadow, at {@code --cm-x/--cm-y} in the
     * layer. Focusable, so the keys have somewhere to go; no ring drawn for
     * that, the cursor is the ring.
     */
    public record cm_frame() implements CssClass<MenuStyles> {
        @Override public Set<CssVar> runtimeVars() { return Set.of(new CssVar("--cm-x"), new CssVar("--cm-y")); }
        @Override public String body() { return """
            position: absolute;
            left: var(--cm-x, 0px);
            top: var(--cm-y, 0px);
            display: flex;
            flex-direction: column;
            box-sizing: border-box;
            pointer-events: auto;
            """;
        }
    }

    /**
     * A row: {@code Control.Option} to the design — its inset, its gap
     * between mark, label and hint, its corner — and {@code Selectable} for
     * its colour: the design's rest, hover, and the cursor as highlighted.
     */
    public record cm_item() implements CssClass<MenuStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: center;
            white-space: nowrap;
            """;
        }
    }

    /** The label of a row: takes the room the marks and the hint leave. */
    public record cm_item_label() implements CssClass<MenuStyles> {
        @Override public String body() { return "flex: 1 1 auto;"; }
    }


    /** The hint after the label, in a muted caption. */
    public record cm_item_hint() implements CssClass<MenuStyles> {
        @Override public String body() { return "flex: none;"; }
    }

    /** The mark after a row with rows: the disclose icon, muted, at the end. */
    public record cm_item_disclose() implements CssClass<MenuStyles> {
        @Override public String body() { return "margin-inline-start: auto;"; }
    }

    /** A row hidden for the bound object: not there. Last in the sheet, so it wins the display. */
    public record cm_item_hidden() implements CssClass<MenuStyles> {
        @Override public String body() { return "display: none;"; }
    }

    /** A separator between sections: the house's separator, placed - its line is its own look's, the room round it the menu's. */
    public record cm_separator() implements CssClass<MenuStyles> {
        @Override public String body() { return """
            flex: none;
            margin-block: 4px;
            """;
        }
    }

    /** A specimen's frame: in the flow of its host, not at a point — after the frame in the sheet, so it wins the place. */
    public record cm_frame_static() implements CssClass<MenuStyles> {
        @Override public String body() { return """
            position: relative;
            left: auto;
            top: auto;
            """;
        }
    }

    /** The host of a specimen: the frames of every level side by side, from the root, wrapping when the room runs out. */
    public record cm_specimen() implements CssClass<MenuStyles> {
        @Override public String body() { return """
            display: flex;
            flex-wrap: wrap;
            align-items: flex-start;
            gap: 24px;
            """;
        }
    }

    @Override
    public List<CssClass<MenuStyles>> cssClasses() {
        return List.of(new cm_layer(), new cm_frame(), new cm_frame_static(), new cm_specimen(), new cm_item(), new cm_item_label(), new cm_item_hint(),
                       new cm_item_disclose(), new cm_separator(), new cm_item_hidden());
    }
}
