package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Implementation;

import java.util.List;
import java.util.Set;

/**
 * Synthwave: magenta and cyan on a violet sky; light, written by the {@link HouseRule} from the old
 * Synthwave's colours. Retro-futurism's own; it suits Neo-futurism too, as the old Synthwave said.
 *
 * <p>Its seeds are its words: a raised thing the white plate; a box casts the void; the text the
 * void's violet; the house's own colour its magenta, white on it; the accent its cyan, deep ink on
 * it; a hairline the magenta, faint.</p>
 */
public record Synthwave() implements ColorPalette {

    public static final Synthwave INSTANCE = new Synthwave();

    static final Seeds SEEDS = new Seeds("#FFFFFF", "#2A0A4A", "#2A0A4A", "#FFFFFF", "#E6007E", "#0092A8", "#0092A8", "#0B0221",
                                         "rgba(230, 0, 126, 0.18)");

    private static final List<Implementation<? extends Target.Color>> ANSWERS = HouseRule.answers(SEEDS);

    @Override public List<Implementation<? extends Target.Color>> implementations() { return ANSWERS; }

    @Override public Set<Class<? extends DesignStyle>> suits() { return Set.of(NeoFuturism.class); }
}
