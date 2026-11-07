package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;

import java.util.List;

/**
 * The slider's classes: how its label, its range and its readout sit in a row. How they look is theirs.
 */
public record SliderStyles() implements CssGroup<SliderStyles> {

    public static final SliderStyles INSTANCE = new SliderStyles();

    /** The slider: a row of label, range and readout — {@code Control.Slider} for its air and its gap. */
    public record el_slider() implements CssClass<SliderStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: center;
            box-sizing: border-box;
            """;
        }
    }

    /** The label before the range: as wide as its words. */
    public record el_slider_label() implements CssClass<SliderStyles> {
        @Override public String body() { return "flex: none;\n"; }
    }

    /** The range between them: the rest of the row (23 Oct). */
    public record el_slider_range() implements CssClass<SliderStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            min-inline-size: 0;
            """;
        }
    }

    /** The readout after the range: the value as the caller formats it, in a steady face, right-aligned. */
    public record el_slider_readout() implements CssClass<SliderStyles> {
        // its steady width a flex basis, not a min-width: how small a text may be is its look's
        @Override public String body() { return """
            flex: 0 0 4ch;
            text-align: end;
            white-space: nowrap;
            """;
        }
    }

    @Override
    public List<CssClass<SliderStyles>> cssClasses() {
        return List.of(new el_slider(), new el_slider_label(), new el_slider_range(), new el_slider_readout());
    }
}
