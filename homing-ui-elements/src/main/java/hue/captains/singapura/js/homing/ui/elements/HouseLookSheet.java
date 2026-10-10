package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.DerivedCss;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.SelfRenderedCss;

import java.util.List;

/**
 * The house's looks as a sheet: flattened, every class a component can wear with its rule, the
 * body copied from the node of the taxonomy that answers it in the theme worn - a house style over
 * Base, in its palette ({@code FlattenSheet}). A derived sheet (RFC 0064, D25): it renders itself
 * per theme, and the theme's owner fetches it again on a switch - never the CSS manager.
 */
public record HouseLookSheet() implements DerivedCss<HouseLookSheet>, SelfRenderedCss {

    public static final HouseLookSheet INSTANCE = new HouseLookSheet();

    /** What a module imports the sheet by, so that it loads: no element wears it. */
    public record look_sheet() implements Exportable._Constant<HouseLookSheet> {}

    @Override public ExportsOf<HouseLookSheet> exports() { return new ExportsOf<>(INSTANCE, List.of(new look_sheet())); }

    @Override public String css(String themeSlug) { return HouseLooks.sheet(themeSlug).css(); }
}
