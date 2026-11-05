package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.core.DerivedCss;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.design.styling.StyledSheet;
import hue.captains.singapura.js.homing.design.styling.Styling;

import java.util.List;

/**
 * The house's style-aware sheet, per style (RFC 0067, appendix Two Classes per Leaf): every casting's one class, as the
 * central manager of the styling served aggregates it (appendix The Style Jars) - the style's own implementation or its
 * base layer's. The same in every palette, so the theme's owner fetches it again only when the style changes; the
 * styling's default style unless the address or a pick says otherwise.
 */
public record HouseStyleSheet() implements DerivedCss<HouseStyleSheet>, StyledSheet {

    public static final HouseStyleSheet INSTANCE = new HouseStyleSheet();

    /** What a module imports the sheet by, so that it loads: no element wears it. */
    public record style_sheet() implements Exportable._Constant<HouseStyleSheet> {}

    @Override public ExportsOf<HouseStyleSheet> exports() { return new ExportsOf<>(INSTANCE, List.of(new style_sheet())); }

    @Override public VariesWith variesWith() { return VariesWith.STYLE; }

    @Override public String fallback(Styling styling) { return styling.defaultStyle().token(); }

    @Override public String css(Styling styling, String style) { return styling.styleSheet(style); }
}
