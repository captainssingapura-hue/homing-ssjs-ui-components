package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Implementation;

import java.util.List;
import java.util.Set;

/**
 * Forest: green and earth, with honey for the accents, light - written by the {@link HouseRule}
 * from its seeds, as Harbour is. A box casts deep pine, lit white, glowing in the forest green; the
 * ring is honey. No style's own: written for Editorial with the house rule, and opaque and flat, so
 * it suits Neumorphism, Sketchy and Neo-brutalism too, as the old Forest said.
 */
public record Forest() implements ColorPalette {

    public static final Forest INSTANCE = new Forest();

    static final Seeds SEEDS = new Seeds("#FFFFFF", "#1A3829", "#2A3D2E", "#FFFFFF", "#2D5F3F", "#D4A04C", "#A6781E", "#1A3829", "#D4DFCC", "#F6F8F3", "#EDF1EA");

    private static final List<Implementation<? extends Target.Color>> ANSWERS = HouseRule.answers(SEEDS);

    @Override public List<Implementation<? extends Target.Color>> implementations() { return ANSWERS; }

    @Override public Set<Class<? extends DesignStyle>> suits() { return Set.of(Editorial.class, Neumorphism.class, Sketchy.class, NeoBrutalism.class); }
}
