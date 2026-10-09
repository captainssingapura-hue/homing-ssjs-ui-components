package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.Implementation;

import java.util.List;

/**
 * Harbour, the house colours: navy and gold on a cool white page, light - written by the
 * {@link HouseRule} from its seeds. A box casts navy, lit white, glowing in the house navy; the
 * ring is gold.
 */
public record Harbour() implements ColorPalette {

    public static final Harbour INSTANCE = new Harbour();

    static final Seeds SEEDS = new Seeds("#FFFFFF", "#111936", "#3B4A6B", "#FFFFFF", "#1E2761", "#F4B942", "#C8921E", "#111936", "#E2E8F0");

    private static final List<Implementation<? extends Target.Color>> ANSWERS = HouseRule.answers(SEEDS);

    @Override public List<Implementation<? extends Target.Color>> implementations() { return ANSWERS; }
}
