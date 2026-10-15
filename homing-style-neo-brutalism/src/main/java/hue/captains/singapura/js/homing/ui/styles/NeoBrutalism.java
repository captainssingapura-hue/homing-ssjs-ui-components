package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.design.semantic.Writing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseWords;

import java.util.List;

/** Neo-brutalism: square, a heavy rule, a hard offset shadow, and a press that drops into it. */
public record NeoBrutalism() implements Derives<ColorScheme.Base, Base> {

    public static final NeoBrutalism INSTANCE = new NeoBrutalism();

    @Override public String group() { return "Expressive"; }

    @Override public String inspiration() { return "A riso-print order form — ink rules, offset shadows, one loud yellow."; }

    @Override public Base base() { return Base.INSTANCE; }

    /** None: its look is in its classes (RFC 0067, appendix The Style Jars); the walk's typed answers retire. */
    @Override public List<Implementation<?>> implementations() { return List.of(); }

    @Override public List<Writing> writings() { return WRITINGS; }

    private static final List<Writing> WRITINGS = List.of(
            new Writing(HouseWords.Close.INSTANCE, "╳"));
}
