package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.Implementation;

import java.util.List;

/**
 * Sketchy: drawn by hand - a wobbling outline, a marker's rule, a tilt under the hand. Ink casts no
 * shadow, so it says nothing of the shadow: the base's is none.
 */
public record Sketchy() implements Derives<BaseScheme, Base> {

    @Override public BaseScheme scheme() { return BaseScheme.INSTANCE; }

    public static final Sketchy INSTANCE = new Sketchy();

    @Override public String group() { return "Expressive"; }

    @Override public String inspiration() { return "Drawn by hand in marker on paper — 2px lines, corners that wobble, nothing casts a shadow."; }

    @Override public Base base() { return Base.INSTANCE; }

    /** None: its look is in its classes (RFC 0067, appendix The Style Jars); the walk's typed answers retire. */
    @Override public List<Implementation<?>> implementations() { return List.of(); }
}
