package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Dressed;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The house's styles and palettes as a site wears them: the seven styles, each written over
 * {@link Base}, Editorial first and so the default; the palettes, Harbour first - every style's own
 * until a style is bound to one of its own - and any style worn in any palette. A theme's slug is
 * the style's token, and the palette's after an underscore when it is not the style's own:
 * {@code sketchy}, {@code sketchy_forest}.
 */
public record HouseStyles() {

    public static final HouseStyles INSTANCE = new HouseStyles();

    /** The styles, the house's own first: it is the default. */
    public static final List<DesignStyle> STYLES = List.of(
            Editorial.INSTANCE, NeoBrutalism.INSTANCE, NeoFuturism.INSTANCE, Neumorphism.INSTANCE,
            Glassmorphism.INSTANCE, RetroFuturism.INSTANCE, Sketchy.INSTANCE);

    /** The palettes, every style's own first. */
    public static final List<ColorPalette> PALETTES = List.of(Harbour.INSTANCE, Forest.INSTANCE);

    /** Every style in every palette, by its slug, the default first. */
    public static final Map<String, Dressed> THEMES = themes();

    private static Map<String, Dressed> themes() {
        var out = new LinkedHashMap<String, Dressed>();
        for (ColorPalette p : PALETTES)
            for (DesignStyle s : STYLES) out.put(slug(s, p), new Dressed(s, p));
        return Collections.unmodifiableMap(out);
    }

    private static String slug(DesignStyle s, ColorPalette p) {
        return p == PALETTES.get(0) ? s.token() : s.token() + "_" + p.token();
    }

    /** A theme's slug: {@code sketchy}, {@code sketchy_forest}. */
    public String slug(Dressed theme) { return slug(theme.style(), theme.palette()); }

    /**
     * The theme a page is worn in, from its slug: the style its first word names, in the palette
     * its second names - {@code sketchy}, {@code sketchy_forest}. A style the house has not is the
     * default style; a palette it has not, the style's own.
     */
    public Dressed dressed(String themeSlug) {
        String[] words = (themeSlug == null ? "" : themeSlug.strip()).split("_", 2);
        String style = THEMES.containsKey(words[0]) ? words[0] : STYLES.get(0).token();
        Dressed found = words.length == 2 ? THEMES.get(style + "_" + words[1]) : null;
        return found != null ? found : THEMES.get(style);
    }
}
