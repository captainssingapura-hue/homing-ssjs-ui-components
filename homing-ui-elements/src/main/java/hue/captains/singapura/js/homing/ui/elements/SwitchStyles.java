package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;

import java.util.List;

/**
 * The switch's classes: its groove and its words in a row, the knob placed in the groove. Its look holds the rest.
 */
public record SwitchStyles() implements CssGroup<SwitchStyles> {

    public static final SwitchStyles INSTANCE = new SwitchStyles();

    /** The switch: its groove and its words in a row, the room between them its look. */
    public record el_switch() implements CssClass<SwitchStyles> {
        @Override public String body() { return """
            display: inline-flex;
            align-items: center;
            """;
        }
    }

    /** The switch's groove: its own size, the knob placed in it. */
    public record el_switch_groove() implements CssClass<SwitchStyles> {
        @Override public String body() { return """
            display: inline-block;
            vertical-align: middle;
            box-sizing: content-box;
            width: 36px;
            height: 20px;
            position: relative;
            flex: none;
            """;
        }
    }

    /** The switch's knob: at the start of its groove, at its end while the switch is on. */
    public record el_switch_knob() implements CssClass<SwitchStyles> {
        @Override public String body() { return """
            position: absolute;
            box-sizing: border-box;
            top: 2px;
            left: 2px;
            width: 16px;
            height: 16px;
            [aria-checked="true"] & { translate: 16px 0; }
            """;
        }
    }

    @Override
    public List<CssClass<SwitchStyles>> cssClasses() {
        return List.of(new el_switch(), new el_switch_groove(), new el_switch_knob());
    }
}
