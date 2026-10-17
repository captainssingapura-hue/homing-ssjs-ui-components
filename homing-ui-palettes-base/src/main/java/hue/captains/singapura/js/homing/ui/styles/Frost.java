package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ConcreteColor;
import hue.captains.singapura.js.homing.design.color.Palettable;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.ui.styles.BaseColours.BaseColour;

import java.util.Optional;

/**
 * Frost: clear glass on a cool aurora; light, its every colour worked out by the house's rule for palettes ({@link HousePalettes}) from the old Frost's
 * colours. Glassmorphism's own; it suits Neo-futurism and Editorial too, as the old Frost said.
 *
 * <p>The old Frost was written word by word, not from seeds; its seeds here are its words: a
 * raised thing is glass, a third white; a box casts the smoke; the text is its ink; the house's own
 * colour - the primary button's ground - its blue, white on it; the accent - the secondary
 * button's ground, the ring - its violet, edged in the deeper violet, white on it; a hairline its
 * hair.</p>
 */
public record Frost() implements ColorPalette<BaseScheme> {

    @Override public BaseScheme scheme() { return BaseScheme.INSTANCE; }

    public static final Frost INSTANCE = new Frost();

    @Override public String inspiration() { return "An aurora behind plates of frosted glass — white at a fraction, rimmed in light."; }

    static final Seeds SEEDS = new Seeds("rgba(255, 255, 255, 0.32)", "#182040", "#1B2340", "#FFFFFF", "#5B7CFF", "#B85CFF", "#7A3FD6", "#FFFFFF",
                                         "rgba(27, 35, 64, 0.12)", "#DDE7F5", "rgba(255, 255, 255, 0.18)");

    @Override public Optional<ConcreteColor> color(Palettable<?, ?> color) {
        return color instanceof BaseColour c ? Optional.of(HousePalettes.INSTANCE.of(SEEDS, c)) : Optional.empty();
    }
}
