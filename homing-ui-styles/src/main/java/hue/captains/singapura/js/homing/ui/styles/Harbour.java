package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ColorScheme.Base;
import hue.captains.singapura.js.homing.design.color.ConcreteColor;
import hue.captains.singapura.js.homing.design.color.Palettable;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.ui.styles.HouseColours.HouseColour;

import java.util.Optional;

/**
 * Harbour, the house colours: navy and gold on a cool white page, light - written by the
 * {@link HousePalettes} from its seeds. A box casts navy, lit white, glowing in the house navy; the
 * ring is gold. Editorial's own; flat and quiet enough for the moulded, the drawn and the chromed,
 * as the old Harbour said - so it suits Neumorphism, Sketchy and Neo-futurism too.
 */
public record Harbour() implements ColorPalette<Base> {

    public static final Harbour INSTANCE = new Harbour();

    @Override public String inspiration() { return "The house colours — navy and gold on a cool white page."; }

    static final Seeds SEEDS = new Seeds("#FFFFFF", "#111936", "#3B4A6B", "#FFFFFF", "#1E2761", "#F4B942", "#C8921E", "#111936", "#E2E8F0", "#FAFBFD", "#F1F4F9");

    @Override public Optional<ConcreteColor> color(Palettable<?, ?> color) {
        return color instanceof HouseColour c ? Optional.of(HousePalettes.INSTANCE.of(SEEDS, c)) : Optional.empty();
    }
}
