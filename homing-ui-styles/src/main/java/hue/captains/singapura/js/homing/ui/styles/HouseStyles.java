package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Dressed;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The house's styles and palettes as a site wears them: the seven styles, each written over {@link Base}, Editorial
 * first and so the default; each with a palette of its own - the one it is worn in unless another is asked for, chosen
 * by hand (RFC 0067, appendix Semantic Colours and Flavours, D22) - and the palettes, every one of the base scheme, so
 * any style may be worn in any of them.
 *
 * <p>A style and a palette are worn each on its own (appendix Two Classes per Leaf): a style's sheet, the same in every
 * palette, and a palette's, the same under every style. The themes - each style in each palette, by a slug - remain for
 * what still names a theme by one word: a theme's slug is the style's token alone when it is worn in its own palette,
 * and the style's then the palette's, joined by an underscore, when not: {@code glassmorphism} is Glassmorphism in
 * Frost, {@code glassmorphism_harbour} in Harbour.</p>
 */
public record HouseStyles() {

    public static final HouseStyles INSTANCE = new HouseStyles();

    /** The styles, the house's own first: it is the default. */
    public static final List<DesignStyle<ColorScheme.Base>> STYLES = List.of(
            Editorial.INSTANCE, NeoBrutalism.INSTANCE, NeoFuturism.INSTANCE, Neumorphism.INSTANCE,
            Glassmorphism.INSTANCE, RetroFuturism.INSTANCE, Sketchy.INSTANCE);

    /** Each style's own palette, the one it is worn in unless another is asked for - the old designs' own colours. */
    public static final Map<DesignStyle<ColorScheme.Base>, ColorPalette<ColorScheme.Base>> OWN = Map.of(
            Editorial.INSTANCE, Harbour.INSTANCE,
            NeoBrutalism.INSTANCE, NeoBrutalismColours.INSTANCE,
            NeoFuturism.INSTANCE, NeoFuturismColours.INSTANCE,
            Neumorphism.INSTANCE, Clay.INSTANCE,
            Glassmorphism.INSTANCE, Frost.INSTANCE,
            RetroFuturism.INSTANCE, Synthwave.INSTANCE,
            Sketchy.INSTANCE, Marker.INSTANCE);

    /** The palettes: the styles' own in the styles' order, then those that are no style's own. */
    public static final List<ColorPalette<ColorScheme.Base>> PALETTES = List.of(
            Harbour.INSTANCE, NeoBrutalismColours.INSTANCE, NeoFuturismColours.INSTANCE, Clay.INSTANCE,
            Frost.INSTANCE, Synthwave.INSTANCE, Marker.INSTANCE, Forest.INSTANCE);

    /** Every style in every palette, by its slug, each style in its own palette first and the default first of all. */
    public static final Map<String, Dressed> THEMES = themes();

    private static Map<String, Dressed> themes() {
        var out = new LinkedHashMap<String, Dressed>();
        for (DesignStyle<ColorScheme.Base> s : STYLES) out.put(slugOf(s, ownOf(s)), new Dressed(HouseColours.TREE, s, ownOf(s)));
        for (DesignStyle<ColorScheme.Base> s : STYLES)
            for (ColorPalette<ColorScheme.Base> p : PALETTES) out.putIfAbsent(slugOf(s, p), new Dressed(HouseColours.TREE, s, p));
        return Collections.unmodifiableMap(out);
    }

    /** A style's own palette. */
    public ColorPalette<ColorScheme.Base> own(DesignStyle<?> style) { return ownOf(style); }

    private static ColorPalette<ColorScheme.Base> ownOf(DesignStyle<?> style) {
        ColorPalette<ColorScheme.Base> p = OWN.get(style);
        if (p == null) throw new IllegalArgumentException("the house has no palette of its own for " + style.token());
        return p;
    }

    /** A style in a palette, by its slug: {@code glassmorphism}, {@code glassmorphism_harbour}. */
    public String slug(DesignStyle<?> s, ColorPalette<?> p) { return slugOf(s, p); }

    private static String slugOf(DesignStyle<?> s, ColorPalette<?> p) {
        return p == ownOf(s) ? s.token() : s.token() + "_" + p.token();
    }

    /** A theme's slug: {@code glassmorphism}, {@code glassmorphism_harbour}. */
    public String slug(Dressed theme) { return slugOf(theme.style(), theme.palette()); }

    /** Whether a palette may dress a style: by its scheme (D22) - every house palette is of the base, as every house style. */
    public boolean fits(DesignStyle<?> style, ColorPalette<?> palette) { return PALETTES.contains(palette) && STYLES.contains(style); }

    /** The theme a change of style lands on: the new style in the palette worn now. */
    public Dressed switchTo(Dressed worn, DesignStyle<ColorScheme.Base> next) {
        return THEMES.get(slugOf(next, worn.palette()));
    }

    /**
     * The theme a page is worn in, from its slug: the style its first word names, in the palette its second names -
     * {@code glassmorphism}, {@code glassmorphism_harbour}. A style the house has not is the default style; a palette it
     * has not, or none named, the style's own.
     */
    public Dressed dressed(String themeSlug) {
        String[] words = (themeSlug == null ? "" : themeSlug.strip()).split("_", 2);
        DesignStyle<ColorScheme.Base> style = STYLES.stream().filter(s -> s.token().equals(words[0])).findFirst().orElse(STYLES.get(0));
        Dressed found = words.length == 2 ? THEMES.get(style.token() + "_" + words[1]) : null;
        return found != null ? found : THEMES.get(style.token());
    }
}
