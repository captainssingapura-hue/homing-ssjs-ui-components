package hue.captains.singapura.js.homing.ui.look;

import hue.captains.singapura.js.homing.core.DerivedCss;
import hue.captains.singapura.js.homing.core.Exportable;
import hue.captains.singapura.js.homing.core.ExportsOf;
import hue.captains.singapura.js.homing.core.SelfRenderedCss;
import hue.captains.singapura.js.homing.design.styling.InstalledStyling;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The house's palette sheet, per palette (RFC 0067, appendix Two Classes per Leaf): every palettable colour of the
 * house's tree as its {@code --fp-} variable, written once per palette. The same under every style, so the theme's owner
 * fetches it again only when the palette changes; the installed styling's default palette unless the address or a pick
 * says otherwise.
 */
public record HousePaletteSheet() implements DerivedCss<HousePaletteSheet>, SelfRenderedCss {

    public static final HousePaletteSheet INSTANCE = new HousePaletteSheet();

    /** What a module imports the sheet by, so that it loads: no element wears it. */
    public record palette_sheet() implements Exportable._Constant<HousePaletteSheet> {}

    private static final Map<String, String> SHEETS = new ConcurrentHashMap<>();

    @Override public ExportsOf<HousePaletteSheet> exports() { return new ExportsOf<>(INSTANCE, List.of(new palette_sheet())); }

    @Override public VariesWith variesWith() { return VariesWith.PALETTE; }

    @Override public Optional<String> fallback() { return InstalledStyling.installed().map(s -> s.defaultPalette().token()); }

    @Override public String css(String palette) { return SHEETS.computeIfAbsent(palette, InstalledStyling.get()::paletteSheet); }
}
