package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Implementation;

import java.util.List;
import java.util.Set;

/**
 * Marker: black marker on white paper, and one red pen for what is being pointed at; light,
 * written by the {@link HouseRule} from the old Marker's seeds. Sketchy's own - and black on white
 * is the classic brutalism, so it suits Neo-brutalism, and Editorial, too.
 */
public record Marker() implements ColorPalette {

    public static final Marker INSTANCE = new Marker();

    static final Seeds SEEDS = new Seeds("#FFFFFF", "#333333", "#212529", "#FFFFFF", "#212529", "#E03131", "#B02626", "#FFFFFF", "#333333", "#FFFFFF");

    private static final List<Implementation<? extends Target.Color>> ANSWERS = HouseRule.answers(SEEDS);

    @Override public List<Implementation<? extends Target.Color>> implementations() { return ANSWERS; }

    @Override public Set<Class<? extends DesignStyle>> suits() { return Set.of(NeoBrutalism.class, Editorial.class); }
}
