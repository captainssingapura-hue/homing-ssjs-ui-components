package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.AnimationEffect;
import hue.captains.singapura.js.homing.design.Target;

import java.util.List;

/**
 * The house's animation effects (RFC 0050 · Episode 2, appendix The Copied Range): what moves, each a whole, one for
 * each variant with no parameters. A design names one for what it draws; its CSS is sealed here and the framework
 * writes it - its class, its still dual, its keyframes - and its colours are its element's, borrowed.
 */
public final class HouseEffects {

    private HouseEffects() {}

    /** A dashed line whose dashes travel along it, round and round: worn by a line, drawn in its stroke's ink. Still, the dashes stand. */
    public record MarchingAnts() implements AnimationEffect {
        public static final MarchingAnts INSTANCE = new MarchingAnts();
        @Override public String meaning() { return "a dashed line whose dashes travel along it, round and round"; }
        @Override public List<Target.Color> drawnIn() { return List.of(Target.Color.Stroke.INSTANCE); }
        @Override public String moving() { return "stroke-width: 1.5px; stroke-dasharray: 5 4;"; }
        @Override public String still() { return "stroke-width: 1.5px; stroke-dasharray: 5 4;"; }
        @Override public String frames() { return "to { stroke-dashoffset: -9; }"; }
        @Override public String timing() { return "0.6s linear infinite"; }
    }

    /** A band of tint sweeping across a box, again and again: worn by a box, drawn in its background's ramp. Still, no band. */
    public record MovingGradient() implements AnimationEffect {
        public static final MovingGradient INSTANCE = new MovingGradient();
        @Override public String meaning() { return "a band of tint sweeping across a box, again and again"; }
        @Override public List<Target.Color> drawnIn() { return List.of(Target.Color.Background.INSTANCE); }
        @Override public String moving() {
            return "background-image: linear-gradient(90deg, transparent, var(--background-ramp-via), transparent);"
                 + " background-size: 200% 100%; background-repeat: no-repeat;";
        }
        @Override public String still() { return ""; }
        @Override public String frames() { return "from { background-position: 150% 0; }\nto { background-position: -50% 0; }"; }
        @Override public String timing() { return "1.6s linear infinite"; }
    }

    /** Every effect the house has, in the order a picker shows them. */
    public static final List<AnimationEffect> ALL = List.of(MarchingAnts.INSTANCE, MovingGradient.INSTANCE);
}
