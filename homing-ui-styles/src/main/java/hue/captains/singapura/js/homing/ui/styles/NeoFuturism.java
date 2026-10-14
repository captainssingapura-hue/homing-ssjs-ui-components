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

/** Neo-futurism: a hairline, a lit rim that glows under the hand, a long eased glide. */
public record NeoFuturism() implements Derives<ColorScheme.Base, Base> {

    public static final NeoFuturism INSTANCE = new NeoFuturism();

    /** The components it draws its own way, on their own and in every role they are cast in, each casting a whole file; every other casting is Base's. */
    private static final Set<Component<?>> DRAWN = HouseFamilies.BUTTONS;

    @Override public String group() { return "Expressive"; }

    @Override public String inspiration() { return "Cool glass over deep space — one electric cyan, every edge a filament."; }

    @Override public Base base() { return Base.INSTANCE; }

    /** None: its look is in its files (RFC 0067, appendix Two Classes per Leaf); the walk's typed answers retire. */
    @Override public List<Implementation<?>> implementations() { return List.of(); }

    @Override public StylingRes style(Casting casting) {
        return DRAWN.contains(casting.cast()) ? StylingRes.CssContent.INSTANCE : StylingRes.Absence.INSTANCE;
    }

    @Override public List<Writing> writings() { return WRITINGS; }

    private static final List<Writing> WRITINGS = List.of(
            new Writing(HouseWords.Close.INSTANCE, "⨯"));
}
