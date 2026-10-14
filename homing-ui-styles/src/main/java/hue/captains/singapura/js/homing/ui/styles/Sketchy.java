package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.component.taxonomy.Casting;
import hue.captains.singapura.js.homing.component.taxonomy.Component;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.design.semantic.StylingRes;
import hue.captains.singapura.js.homing.design.semantic.Writing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseWords;

import java.util.List;
import java.util.Set;

/**
 * Sketchy: drawn by hand - a wobbling outline, a marker's rule, a tilt under the hand. Ink casts no
 * shadow, so it says nothing of the shadow: the base's is none.
 */
public record Sketchy() implements Derives<ColorScheme.Base, Base> {

    public static final Sketchy INSTANCE = new Sketchy();

    /** The components it draws its own way, on their own and in every role they are cast in, each casting a whole file; every other casting is Base's. */
    private static final Set<Component<?>> DRAWN = HouseFamilies.DRAWN;

    @Override public String group() { return "Expressive"; }

    @Override public String inspiration() { return "Drawn by hand in marker on paper — 2px lines, corners that wobble, nothing casts a shadow."; }

    @Override public Base base() { return Base.INSTANCE; }

    /** None: its look is in its files (RFC 0067, appendix Two Classes per Leaf); the walk's typed answers retire. */
    @Override public List<Implementation<?>> implementations() { return List.of(); }

    @Override public StylingRes style(Casting casting) {
        return DRAWN.contains(casting.cast()) ? StylingRes.CssContent.INSTANCE : StylingRes.Absence.INSTANCE;
    }

    @Override public List<Writing> writings() { return WRITINGS; }

    private static final List<Writing> WRITINGS = List.of(
            new Writing(HouseWords.Check.INSTANCE, "✔"),
            new Writing(HouseWords.Disclose.INSTANCE, "➤"),
            new Writing(HouseWords.Close.INSTANCE, "✖"),
            new Writing(HouseWords.Pin.INSTANCE, "⚲"),
            new Writing(HouseWords.Settings.INSTANCE, "⚒"),
            new Writing(HouseWords.Grip.INSTANCE, "⁘"));
}
