package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.core.DerivedCss;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.SelfRenderedCss;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The house's style-aware sheet, per style (RFC 0067, appendix Two Classes per Leaf): every house leaf's one class, the
 * style's own file or Base's, flattened the first time the style is asked for. The same in every palette, so the theme's
 * owner fetches it again only when the style changes; Editorial unless the address or a pick says otherwise.
 */
public record HouseStyleSheet() implements DerivedCss<HouseStyleSheet>, SelfRenderedCss {

    public static final HouseStyleSheet INSTANCE = new HouseStyleSheet();

    /** What a module imports the sheet by, so that it loads: no element wears it. */
    public record style_sheet() implements Exportable._Constant<HouseStyleSheet> {}

    private static final Map<String, String> SHEETS = new ConcurrentHashMap<>();

    @Override public ExportsOf<HouseStyleSheet> exports() { return new ExportsOf<>(INSTANCE, List.of(new style_sheet())); }

    @Override public VariesWith variesWith() { return VariesWith.STYLE; }

    @Override public Optional<String> fallback() { return Optional.of(HouseStyling.STYLING.defaultStyle().token()); }

    @Override public String css(String style) { return SHEETS.computeIfAbsent(style, HouseStyling.STYLING::styleSheet); }
}
