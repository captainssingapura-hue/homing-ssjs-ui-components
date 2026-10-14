package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.component.taxonomy.ComponentLeaf;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.design.semantic.StylingRes;

import java.util.List;
import java.util.Set;

/** Editorial, the house's own: a serif voice, a fine rule, a soft lift under the hand. */
public record Editorial() implements Derives<ColorScheme.Base, Base> {

    public static final Editorial INSTANCE = new Editorial();

    /** The leaves it draws its own way, each in a whole file of its own; every other leaf is Base's. */
    private static final Set<ComponentLeaf> DRAWN = HouseFamilies.BUTTONS;

    @Override public String group() { return "Neutral"; }

    @Override public String inspiration() { return "The house design — serif headings, hairline rules, a whisper of shadow."; }

    @Override public Base base() { return Base.INSTANCE; }

    /** None: its look is in its files (RFC 0067, appendix Two Classes per Leaf); the walk's typed answers retire. */
    @Override public List<Implementation<?>> implementations() { return List.of(); }

    @Override public StylingRes style(ComponentLeaf leaf) {
        return DRAWN.contains(leaf) ? StylingRes.CssContent.INSTANCE : StylingRes.Absence.INSTANCE;
    }
}
