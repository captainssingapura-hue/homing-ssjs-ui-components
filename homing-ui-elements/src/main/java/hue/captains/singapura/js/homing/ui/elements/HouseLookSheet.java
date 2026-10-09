package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.SelfRenderedCss;

import java.util.List;

/**
 * The house's looks as a sheet: flattened, every class a component can wear with its rule, the
 * body copied from the node of the taxonomy that answers it in the theme worn - a house style over
 * Base, in its palette ({@code FlattenSheet}). It renders itself per theme, and a theme switch
 * fetches it again.
 */
public record HouseLookSheet() implements CssGroup<HouseLookSheet>, SelfRenderedCss {

    public static final HouseLookSheet INSTANCE = new HouseLookSheet();

    /** What a module imports the sheet by, so that it loads: no element wears it. */
    public record look_sheet() implements CssClass<HouseLookSheet> {
        @Override public String body() { return ""; }
    }

    @Override public List<CssClass<HouseLookSheet>> cssClasses() { return List.of(new look_sheet()); }

    @Override public String css(String themeSlug) { return HouseLooks.sheet(themeSlug).css(); }
}
