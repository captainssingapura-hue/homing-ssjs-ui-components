package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.Implementation;

import java.util.List;

/** Editorial, the house's own: a serif voice, a fine rule, a soft lift under the hand. */
public record Editorial() implements Derives<ColorScheme.Base, Base> {

    public static final Editorial INSTANCE = new Editorial();

    @Override public String group() { return "Neutral"; }

    @Override public String inspiration() { return "The house design — serif headings, hairline rules, a whisper of shadow."; }

    @Override public Base base() { return Base.INSTANCE; }

    /** None: its look is in its classes (RFC 0067, appendix The Style Jars); the walk's typed answers retire. */
    @Override public List<Implementation<?>> implementations() { return List.of(); }
}
