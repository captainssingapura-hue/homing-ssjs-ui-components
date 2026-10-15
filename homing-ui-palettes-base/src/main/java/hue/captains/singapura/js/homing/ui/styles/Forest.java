package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ColorScheme.Base;
import hue.captains.singapura.js.homing.design.color.ConcreteColor;
import hue.captains.singapura.js.homing.design.color.Palettable;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.HouseColour;

import java.util.Optional;

/**
 * Forest: green and earth, with honey for the accents, light - its every colour worked out by the house's rule for palettes ({@link HousePalettes})
 * from its seeds, as Harbour is. A box casts deep pine, lit white, glowing in the forest green; the
 * ring is honey. No style's own: written for Editorial with the house rule, and opaque and flat, so
 * it suits Neumorphism, Sketchy and Neo-brutalism too, as the old Forest said.
 */
public record Forest() implements ColorPalette<Base> {

    public static final Forest INSTANCE = new Forest();

    @Override public String inspiration() { return "Green and earth tones, with honey for the accents."; }

    static final Seeds SEEDS = new Seeds("#FFFFFF", "#1A3829", "#2A3D2E", "#FFFFFF", "#2D5F3F", "#D4A04C", "#A6781E", "#1A3829", "#D4DFCC", "#F6F8F3", "#EDF1EA");

    @Override public Optional<ConcreteColor> color(Palettable<?, ?> color) {
        return color instanceof HouseColour c ? Optional.of(HousePalettes.INSTANCE.of(SEEDS, c)) : Optional.empty();
    }
}
