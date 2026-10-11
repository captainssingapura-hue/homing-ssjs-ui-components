package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Implementation;

import java.util.List;
import java.util.Set;

/**
 * Clay: one grey-blue clay, the raised layer the same colour as the page - which is what
 * Neumorphism moulds; light, written by the {@link HouseRule} from the old Clay's seeds.
 * Neumorphism's own; under Editorial's flat cards dull but coherent, so it suits Editorial too.
 *
 * <p>Two seeds are read as the house rule means them, not as the old seeds were named: what a
 * box casts is the clay's darkest ink, where the old palette's "inverted" was a light clay - the
 * old Neumorphism shaded with the page mixed toward black, the house's with the cast at a few
 * percent; and the ink on the title colour - the primary button's - is white.</p>
 */
public record Clay() implements ColorPalette {

    public static final Clay INSTANCE = new Clay();

    static final Seeds SEEDS = new Seeds("#E0E5EC", "#2D3A4B", "#3B4A5E", "#FFFFFF", "#2D3A4B", "#6C8CFF", "#4F6FE0", "#FFFFFF", "#CBD3DF", "#E0E5EC");

    private static final List<Implementation<? extends Target.Color>> ANSWERS = HouseRule.answers(SEEDS);

    @Override public List<Implementation<? extends Target.Color>> implementations() { return ANSWERS; }

    @Override public Set<Class<? extends DesignStyle>> suits() { return Set.of(Editorial.class); }
}
