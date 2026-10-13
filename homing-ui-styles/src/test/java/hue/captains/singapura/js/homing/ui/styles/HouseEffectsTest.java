package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.AnimationEffect;
import hue.captains.singapura.js.homing.design.semantic.Effects;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house's animation effects: each a whole, its CSS its own to write, what it takes named, and a still dual that
 * still shows what it marks - the ants' dashes standing, the gradient's no band.
 */
class HouseEffectsTest {

    @Test
    void everyEffect_isItsOwnToWrite_andMoves() {
        for (AnimationEffect e : HouseEffects.ALL) {
            Effects.INSTANCE.check(e);
            assertTrue(e.moves(), e.token());
        }
        assertEquals("marching-ants", HouseEffects.MarchingAnts.INSTANCE.token());
        assertEquals("moving-gradient", HouseEffects.MovingGradient.INSTANCE.token());
    }

    @Test
    void theAnts_takeAStrokesDashes_andStandStill_asDashes() {
        var ants = HouseEffects.MarchingAnts.INSTANCE;
        assertEquals(Set.of("stroke-width", "stroke-dasharray", "stroke-dashoffset"), Effects.INSTANCE.takes(ants));
        assertEquals("stroke-width: 1.5px;\nstroke-dasharray: 5 4;\nanimation: none;", Effects.INSTANCE.still(ants));
        assertTrue(Effects.INSTANCE.moving(ants).contains("animation: effect-marching-ants 0.6s linear infinite;"));
    }

    @Test
    void theGradient_takesABackgroundsImage_andStillShowsNoBand() {
        var gradient = HouseEffects.MovingGradient.INSTANCE;
        assertEquals(Set.of("background-image", "background-size", "background-repeat", "background-position"), Effects.INSTANCE.takes(gradient));
        assertEquals("animation: none;", Effects.INSTANCE.still(gradient));
        assertTrue(Effects.INSTANCE.moving(gradient).contains("var(--background-ramp-via)"), "drawn in its box's ramp, borrowed");
    }
}
