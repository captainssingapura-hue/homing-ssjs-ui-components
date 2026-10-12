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
 * {@link Base}, Editorial first and so the default; each with a palette of its own - the one it is
 * worn in unless another is asked for - and the palettes, each saying which other styles it suits
 * ({@link ColorPalette#suits()}). Any style may be worn in any palette.
 *
 * <p>A theme's slug is the style's token alone when it is worn in its own palette, and the style's
 * then the palette's, joined by an underscore, when not: {@code glassmorphism} is Glassmorphism in
 * Frost, {@code glassmorphism_harbour} in Harbour - as the old designs named their crosses, so one
 * slug means the same theme on both sides.</p>
 *
 * <p>When the style is changed, the palette worn is kept if it suits the new style - its own, or one
 * that names it - and the new style's own palette is taken if not ({@link #switchTo}).</p>
 */
public record HouseStyles() {

    public static final HouseStyles INSTANCE = new HouseStyles();

    /** The styles, the house's own first: it is the default. */
    public static final List<DesignStyle> STYLES = List.of(
            Editorial.INSTANCE, NeoBrutalism.INSTANCE, NeoFuturism.INSTANCE, Neumorphism.INSTANCE,
            Glassmorphism.INSTANCE, RetroFuturism.INSTANCE, Sketchy.INSTANCE);

    /** Each style's own palette, the one it is worn in unless another is asked for - the old designs' own colours. */
    public static final Map<DesignStyle, ColorPalette> OWN = Map.of(
            Editorial.INSTANCE, Harbour.INSTANCE,
            NeoBrutalism.INSTANCE, NeoBrutalismColours.INSTANCE,
            NeoFuturism.INSTANCE, NeoFuturismColours.INSTANCE,
            Neumorphism.INSTANCE, Clay.INSTANCE,
            Glassmorphism.INSTANCE, Frost.INSTANCE,
            RetroFuturism.INSTANCE, Synthwave.INSTANCE,
            Sketchy.INSTANCE, Marker.INSTANCE);

    /** The palettes: the styles' own in the styles' order, then those that are no style's own. */
    public static final List<ColorPalette> PALETTES = List.of(
            Harbour.INSTANCE, NeoBrutalismColours.INSTANCE, NeoFuturismColours.INSTANCE, Clay.INSTANCE,
            Frost.INSTANCE, Synthwave.INSTANCE, Marker.INSTANCE, Forest.INSTANCE);

    /** Every style in every palette, by its slug, each style in its own palette first and the default first of all. */
    public static final Map<String, Dressed> THEMES = themes();

    private static Map<String, Dressed> themes() {
        var out = new LinkedHashMap<String, Dressed>();
        for (DesignStyle s : STYLES) out.put(slug(s, ownOf(s)), new Dressed(s, ownOf(s)));
        for (DesignStyle s : STYLES)
            for (ColorPalette p : PALETTES) out.putIfAbsent(slug(s, p), new Dressed(s, p));
        return Collections.unmodifiableMap(out);
    }

    /** A style's own palette. */
    public ColorPalette own(DesignStyle style) { return ownOf(style); }

    /**
     * The seeds a house palette is written from: the colours a design outside the styles draws in, palette by
     * palette - the house's design of the grid's frame. Refused for a palette that is not the house's.
     */
    public Seeds seeds(ColorPalette palette) {
        Seeds s = SEEDS.get(palette);
        if (s == null) throw new IllegalArgumentException(palette.token() + " is no house palette");
        return s;
    }

    private static final Map<ColorPalette, Seeds> SEEDS = Map.of(
            Harbour.INSTANCE, Harbour.SEEDS, NeoBrutalismColours.INSTANCE, NeoBrutalismColours.SEEDS,
            NeoFuturismColours.INSTANCE, NeoFuturismColours.SEEDS, Clay.INSTANCE, Clay.SEEDS, Frost.INSTANCE, Frost.SEEDS,
            Synthwave.INSTANCE, Synthwave.SEEDS, Marker.INSTANCE, Marker.SEEDS, Forest.INSTANCE, Forest.SEEDS);

    private static ColorPalette ownOf(DesignStyle style) {
        ColorPalette p = OWN.get(style);
        if (p == null) throw new IllegalArgumentException("the house has no palette of its own for " + style.token());
        return p;
    }

    private static String slug(DesignStyle s, ColorPalette p) {
        return p == ownOf(s) ? s.token() : s.token() + "_" + p.token();
    }

    /** A theme's slug: {@code glassmorphism}, {@code glassmorphism_harbour}. */
    public String slug(Dressed theme) { return slug(theme.style(), theme.palette()); }

    /** Whether a palette suits a style: the style's own always, another when it names the style. */
    public boolean fits(DesignStyle style, ColorPalette palette) {
        return own(style) == palette || palette.suits().contains(style.getClass());
    }

    /** The theme a change of style lands on: the new style in the palette worn now if it suits it, else in its own. */
    public Dressed switchTo(Dressed worn, DesignStyle next) {
        return new Dressed(next, fits(next, worn.palette()) ? worn.palette() : own(next));
    }

    /**
     * The theme a page is worn in, from its slug: the style its first word names, in the palette its
     * second names - {@code glassmorphism}, {@code glassmorphism_harbour}. A style the house has not
     * is the default style; a palette it has not, or none named, the style's own.
     */
    public Dressed dressed(String themeSlug) {
        String[] words = (themeSlug == null ? "" : themeSlug.strip()).split("_", 2);
        DesignStyle style = STYLES.stream().filter(s -> s.token().equals(words[0])).findFirst().orElse(STYLES.get(0));
        Dressed found = words.length == 2 ? THEMES.get(style.token() + "_" + words[1]) : null;
        return found != null ? found : THEMES.get(style.token());
    }
}
