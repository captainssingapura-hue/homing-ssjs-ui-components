package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.Target;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.Implementation;

import java.util.List;

/**
 * Neo-brutalism's own colours, a palette of their own: black ink and rules on white paper, the
 * signal yellow and the riso red; light, written by the {@link HouseRule} from the colours the old
 * Neo-brutalism carried inside itself. Its token is the old one, {@code neo-brutalism}, so a theme
 * named for the pair means the same on both sides. Suits no other style: the old design offered
 * its colours to none.
 *
 * <p>Its seeds are its words: a raised thing the paper; a box casts black, hard; the text black;
 * the house's own colour - the primary button's ground - the signal yellow, black on it; the
 * accent the riso red, edged black, black on it; a hairline a black rule. The house rule sets a
 * heading in the title's colour blended into the ink, so a heading here is mustard where the old
 * design set it black - legible (4.7:1 on the paper), and a question for the rule, not the
 * palette.</p>
 */
public record NeoBrutalismColours() implements ColorPalette {

    public static final NeoBrutalismColours INSTANCE = new NeoBrutalismColours();

    static final Seeds SEEDS = new Seeds("#FFFFFF", "#000000", "#000000", "#000000", "#FFE800", "#FF3B21", "#000000", "#000000", "#000000", "#FFFFFF");

    private static final List<Implementation<? extends Target.Color>> ANSWERS = HouseRule.answers(SEEDS);

    @Override public String token() { return "neo-brutalism"; }

    @Override public List<Implementation<? extends Target.Color>> implementations() { return ANSWERS; }
}
