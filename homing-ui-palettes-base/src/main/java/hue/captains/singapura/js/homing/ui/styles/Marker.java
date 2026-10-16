package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ConcreteColor;
import hue.captains.singapura.js.homing.design.color.Palettable;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.ui.styles.BaseColours.BaseColour;

import java.util.Optional;

/**
 * Marker: black marker on white paper, and one red pen for what is being pointed at; light,
 * its every colour worked out by the house's rule for palettes ({@link HousePalettes}) from the old Marker's seeds. Sketchy's own - and black on white
 * is the classic brutalism, so it suits Neo-brutalism, and Editorial, too.
 */
public record Marker() implements ColorPalette<BaseScheme> {

    public static final Marker INSTANCE = new Marker();

    @Override public String inspiration() { return "Black marker on white paper, and a red pen for what is being pointed at."; }

    static final Seeds SEEDS = new Seeds("#FFFFFF", "#333333", "#212529", "#FFFFFF", "#212529", "#E03131", "#B02626", "#FFFFFF", "#333333", "#FFFFFF", "#F7F7F9");

    @Override public Optional<ConcreteColor> color(Palettable<?, ?> color) {
        return color instanceof BaseColour c ? Optional.of(HousePalettes.INSTANCE.of(SEEDS, c)) : Optional.empty();
    }
}
