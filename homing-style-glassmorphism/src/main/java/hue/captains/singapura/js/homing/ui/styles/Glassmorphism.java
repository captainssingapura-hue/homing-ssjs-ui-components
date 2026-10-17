package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.Implementation;

import java.util.List;

/** Glassmorphism: a rounded pane with a lit top edge, floating on a wide soft shadow. */
public record Glassmorphism() implements Derives<BaseScheme, Base> {

    @Override public BaseScheme scheme() { return BaseScheme.INSTANCE; }

    public static final Glassmorphism INSTANCE = new Glassmorphism();

    @Override public String group() { return "Expressive"; }

    @Override public String inspiration() { return "Plates of frosted glass over an aurora — blurred, rimmed in light, lifted on soft shadow."; }

    @Override public Base base() { return Base.INSTANCE; }

    /** None: its look is in its classes (RFC 0067, appendix The Style Jars); the walk's typed answers retire. */
    @Override public List<Implementation<?>> implementations() { return List.of(); }
}
