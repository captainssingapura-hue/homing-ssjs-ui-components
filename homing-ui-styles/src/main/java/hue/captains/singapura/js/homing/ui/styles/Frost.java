package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Implementation;

import java.util.List;
import java.util.Set;

/**
 * Frost: clear glass on a cool aurora; light, written by the {@link HouseRule} from the old Frost's
 * colours. Glassmorphism's own; it suits Neo-futurism and Editorial too, as the old Frost said.
 *
 * <p>The old Frost was written word by word, not from seeds; its seeds here are its words: a
 * raised thing is glass, a third white; a box casts the smoke; the text is its ink; the house's own
 * colour - the primary button's ground - its blue, white on it; the accent - the secondary
 * button's ground, the ring - its violet, edged in the deeper violet, white on it; a hairline its
 * hair.</p>
 */
public record Frost() implements ColorPalette {

    public static final Frost INSTANCE = new Frost();

    @Override public String inspiration() { return "An aurora behind plates of frosted glass — white at a fraction, rimmed in light."; }

    static final Seeds SEEDS = new Seeds("rgba(255, 255, 255, 0.32)", "#182040", "#1B2340", "#FFFFFF", "#5B7CFF", "#B85CFF", "#7A3FD6", "#FFFFFF",
                                         "rgba(27, 35, 64, 0.12)", "#DDE7F5", "rgba(255, 255, 255, 0.18)");

    private static final List<Implementation<? extends Target.Color>> ANSWERS = HouseRule.answers(SEEDS);

    @Override public List<Implementation<? extends Target.Color>> implementations() { return ANSWERS; }

    @Override public Set<Class<? extends DesignStyle>> suits() { return Set.of(NeoFuturism.class, Editorial.class); }
}
