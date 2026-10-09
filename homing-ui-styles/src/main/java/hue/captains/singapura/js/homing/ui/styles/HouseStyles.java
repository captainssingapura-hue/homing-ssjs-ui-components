package hue.captains.singapura.js.homing.ui.styles;

import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Dressed;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The house's styles and palettes as a site wears them: the seven styles, each written over
 * {@link Base}, Editorial first and so the default; each worn in {@link Harbour}, the one palette so
 * far.
 */
public record HouseStyles() {

    public static final HouseStyles INSTANCE = new HouseStyles();

    /** The styles, the house's own first: it is the default. */
    public static final List<DesignStyle> STYLES = List.of(
            Editorial.INSTANCE, NeoBrutalism.INSTANCE, NeoFuturism.INSTANCE, Neumorphism.INSTANCE,
            Glassmorphism.INSTANCE, RetroFuturism.INSTANCE, Sketchy.INSTANCE);

    /** The one palette so far: every style is worn in it. */
    public static final ColorPalette PALETTE = Harbour.INSTANCE;

    /** Every style dressed in the palette, by the style's token, the default first. */
    public static final Map<String, Dressed> THEMES = themes();

    private static Map<String, Dressed> themes() {
        var out = new LinkedHashMap<String, Dressed>();
        for (DesignStyle s : STYLES) out.put(s.token(), new Dressed(s, PALETTE));
        return java.util.Collections.unmodifiableMap(out);
    }

    /**
     * The theme a page is worn in, from its slug: the style its first word names - {@code sketchy}
     * and {@code sketchy_forest} alike - and the default for a slug that names none.
     */
    public Dressed dressed(String themeSlug) {
        String style = themeSlug == null ? "" : themeSlug.strip();
        int cut = style.indexOf('_');
        if (cut >= 0) style = style.substring(0, cut);
        Dressed found = THEMES.get(style);
        return found != null ? found : THEMES.get(STYLES.get(0).token());
    }
}
