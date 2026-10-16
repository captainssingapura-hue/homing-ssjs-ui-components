package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.design.semantic.Writing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseWords;

import java.util.List;

/**
 * Sketchy: drawn by hand - a wobbling outline, a marker's rule, a tilt under the hand. Ink casts no
 * shadow, so it says nothing of the shadow: the base's is none.
 */
public record Sketchy() implements Derives<BaseScheme, Base> {

    public static final Sketchy INSTANCE = new Sketchy();

    @Override public String group() { return "Expressive"; }

    @Override public String inspiration() { return "Drawn by hand in marker on paper — 2px lines, corners that wobble, nothing casts a shadow."; }

    @Override public Base base() { return Base.INSTANCE; }

    /** None: its look is in its classes (RFC 0067, appendix The Style Jars); the walk's typed answers retire. */
    @Override public List<Implementation<?>> implementations() { return List.of(); }

    @Override public List<Writing> writings() { return WRITINGS; }

    private static final List<Writing> WRITINGS = List.of(
            new Writing(HouseWords.Check.INSTANCE, "✔"),
            new Writing(HouseWords.Disclose.INSTANCE, "➤"),
            new Writing(HouseWords.Close.INSTANCE, "✖"),
            new Writing(HouseWords.Pin.INSTANCE, "⚲"),
            new Writing(HouseWords.Settings.INSTANCE, "⚒"),
            new Writing(HouseWords.Grip.INSTANCE, "⁘"));
}
