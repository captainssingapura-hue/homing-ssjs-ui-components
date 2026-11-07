package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.ui.icons.IconStyles;

import java.util.List;

/**
 * The items' classes: a menu item and a choice's option, each a row of what it holds. How a row looks is its own look's.
 */
public record ItemStyles() implements CssGroup<ItemStyles> {

    public static final ItemStyles INSTANCE = new ItemStyles();

    /** A menu's item: its glyph, its name, its hint and its way on in a row, on one line. */
    public record el_menu_item() implements CssClass<ItemStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: center;
            white-space: nowrap;
            """;
        }
    }

    /** Its name: the room its glyph and its hint leave. */
    public record el_menu_item_label() implements CssClass<ItemStyles> {
        @Override public String body() { return "flex: 1 1 auto;"; }
    }

    public record el_menu_item_hint() implements CssClass<ItemStyles> {
        @Override public String body() { return "flex: none;"; }
    }

    /** Its way into its rows, at its end. */
    public record el_menu_item_disclose() implements CssClass<ItemStyles> {
        @Override public String body() { return "margin-inline-start: auto;"; }
    }

    /** A menu item's leading room: an icon's box, kept whether an icon is mounted or not, so the names beside it align. */
    public record el_menu_item_lead() implements CssClass<ItemStyles> {
        @Override public String body() { return """
            display: inline-flex;
            align-items: center;
            justify-content: center;
            flex: none;
            inline-size: 1.25em;
            block-size: 1em;
            """;
        }
    }

    /** What of a menu item's leading place is not shown: its own icon while it is checked, its check while it is not (23 Oct). */
    public record el_menu_item_off() implements CssClass<ItemStyles> {
        @Override public String body() { return "display: none;"; }
        /** It hides an icon, so it follows the icon's own display. */
        @Override public List<CssClass<?>> dependsOn() { return List.of(new IconStyles.ic_base()); }
    }

    /** A choice's option: its name and, at its end, its note, on a line; its words from its start. */
    public record el_choice_option() implements CssClass<ItemStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: baseline;
            text-align: left;
            box-sizing: border-box;
            """;
        }
    }

    /** Its note, at its end, cut short where the option ends. */
    public record el_choice_option_note() implements CssClass<ItemStyles> {
        @Override public String body() { return """
            margin-left: auto;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            """;
        }
        /** A text's own rule clears its margins, so this follows that rule. */
        @Override public List<CssClass<?>> dependsOn() { return List.of(new ElementStyles.el_text()); }
    }

    @Override
    public List<CssClass<ItemStyles>> cssClasses() {
        return List.of(new el_menu_item(), new el_menu_item_label(), new el_menu_item_hint(), new el_menu_item_disclose(), new el_menu_item_lead(),
                       new el_menu_item_off(), new el_choice_option(), new el_choice_option_note());
    }
}
