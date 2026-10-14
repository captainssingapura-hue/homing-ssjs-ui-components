package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ColorScheme.Base;
import hue.captains.singapura.js.homing.design.color.ConcreteColor;
import hue.captains.singapura.js.homing.design.color.Palettable;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.HouseColour;

import java.util.Optional;

/**
 * Neo-futurism's own colours, a palette of their own: space blue-black and white, lit cyan, with a
 * violet; light, its every colour worked out by the house's rule for palettes ({@link HousePalettes}) from the colours the old Neo-futurism carried
 * inside itself. Its token is the old one, {@code neo-futurism}, so a theme named for the pair
 * means the same on both sides. Suits no other style: the old design offered its colours to none.
 *
 * <p>Its seeds are its words: a raised thing white; a box casts space; the text its ink; the
 * house's own colour - the primary button's ground - the cyan, the near-black on it; the accent the
 * violet, the pale ink on it; a hairline its border.</p>
 */
public record NeoFuturismColours() implements ColorPalette<Base> {

    public static final NeoFuturismColours INSTANCE = new NeoFuturismColours();

    @Override public String inspiration() { return "Deep space and one electric cyan, every edge a filament."; }

    static final Seeds SEEDS = new Seeds("#FFFFFF", "#0B1020", "#141B2D", "#04121A", "#00C2E0", "#6C4DFF", "#6C4DFF", "#E8F1FF", "#D6DEEB", "#F3F6FB", "#E8EDF6");

    @Override public String token() { return "neo-futurism"; }

    @Override public Optional<ConcreteColor> color(Palettable<?, ?> color) {
        return color instanceof HouseColour c ? Optional.of(HousePalettes.INSTANCE.of(SEEDS, c)) : Optional.empty();
    }
}
