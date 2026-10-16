package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.semantic.Derives;
import hue.captains.singapura.js.homing.design.semantic.Implementation;
import hue.captains.singapura.js.homing.design.semantic.Writing;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseWords;

import java.util.List;

/** Neo-futurism: a hairline, a lit rim that glows under the hand, a long eased glide. */
public record NeoFuturism() implements Derives<BaseScheme, Base> {

    public static final NeoFuturism INSTANCE = new NeoFuturism();

    @Override public String group() { return "Expressive"; }

    @Override public String inspiration() { return "Cool glass over deep space — one electric cyan, every edge a filament."; }

    @Override public Base base() { return Base.INSTANCE; }

    /** None: its look is in its classes (RFC 0067, appendix The Style Jars); the walk's typed answers retire. */
    @Override public List<Implementation<?>> implementations() { return List.of(); }

    @Override public List<Writing> writings() { return WRITINGS; }

    private static final List<Writing> WRITINGS = List.of(
            new Writing(HouseWords.Close.INSTANCE, "⨯"));
}
