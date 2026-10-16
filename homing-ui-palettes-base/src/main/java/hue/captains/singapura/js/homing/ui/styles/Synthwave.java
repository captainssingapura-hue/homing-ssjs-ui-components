package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ConcreteColor;
import hue.captains.singapura.js.homing.design.color.Palettable;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.ui.styles.BaseColours.BaseColour;

import java.util.Optional;

/**
 * Synthwave: magenta and cyan on a violet sky; light, its every colour worked out by the house's rule for palettes ({@link HousePalettes}) from the old
 * Synthwave's colours. Retro-futurism's own; it suits Neo-futurism too, as the old Synthwave said.
 *
 * <p>Its seeds are its words: a raised thing the white plate; a box casts the void; the text the
 * void's violet; the house's own colour its magenta, white on it; the accent its cyan, deep ink on
 * it; a hairline the magenta, faint.</p>
 */
public record Synthwave() implements ColorPalette<BaseScheme> {

    public static final Synthwave INSTANCE = new Synthwave();

    @Override public String inspiration() { return "A magenta sun over a cyan grid — the colours of a 1984 that never happened."; }

    static final Seeds SEEDS = new Seeds("#FFFFFF", "#2A0A4A", "#2A0A4A", "#FFFFFF", "#E6007E", "#0092A8", "#0092A8", "#0B0221",
                                         "rgba(230, 0, 126, 0.18)", "#F6EFFF", "#EFE6FF");

    @Override public Optional<ConcreteColor> color(Palettable<?, ?> color) {
        return color instanceof BaseColour c ? Optional.of(HousePalettes.INSTANCE.of(SEEDS, c)) : Optional.empty();
    }
}
