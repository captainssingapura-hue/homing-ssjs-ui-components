package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.core.Theme;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.design.semantic.Dressed;
import hue.captains.singapura.js.homing.server.ThemeRegistry;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The house's themes, as a site hands them to its MPA: {@code StandardMpa.of(brand, HouseThemes.INSTANCE,
 * crates…)}. Every house style in every house palette, each by the slug {@link HouseStyles} gives it - the
 * style's token alone in its own palette, the style's then the palette's in another - the default first. A
 * picker's two axes are the styles, each in its own palette, and the palettes; a palette fits a style when it
 * is the style's own or names it. Nothing is rendered here: the house's look and its lexicon render
 * themselves for the theme a page is worn in.
 */
public record HouseThemes() implements ThemeRegistry {

    public static final HouseThemes INSTANCE = new HouseThemes();

    /** A house style worn in a palette, by its slug. */
    public record Worn(Dressed dressed) implements Theme {
        @Override public String slug() { return HouseStyles.INSTANCE.slug(dressed); }
        @Override public String label() { return own() ? dressed.style().label() : dressed.style().label() + " in " + dressed.palette().label(); }
        @Override public String group() { return own() ? dressed.style().group() : "Composed"; }
        @Override public String inspiration() {
            return own() ? dressed.style().inspiration() : dressed.style().label() + "'s shapes in " + dressed.palette().label() + "'s colours.";
        }
        private boolean own() { return HouseStyles.INSTANCE.own(dressed.style()) == dressed.palette(); }
    }

    /** A house palette, as the colours a picker offers a style. */
    public record Colours(ColorPalette<?> palette) implements Theme {
        @Override public String slug() { return palette.token(); }
        @Override public String label() { return palette.label(); }
        @Override public String group() { return "Colours"; }
        @Override public String inspiration() { return palette.inspiration(); }
    }

    /** Every theme by its slug, the default first; each style's own among them, the very ones the picker's rows are. */
    private static final Map<String, Worn> WORN = worn();

    private static Map<String, Worn> worn() {
        var out = new LinkedHashMap<String, Worn>();
        HouseStyles.THEMES.forEach((slug, dressed) -> out.put(slug, new Worn(dressed)));
        return java.util.Collections.unmodifiableMap(out);
    }

    @Override public List<Theme> themes() { return List.copyOf(WORN.values()); }

    @Override public List<Theme> bases() {
        return HouseStyles.STYLES.stream().<Theme>map(s -> WORN.get(s.token())).toList();
    }

    @Override public List<Theme> colours() {
        return HouseStyles.PALETTES.stream().<Theme>map(Colours::new).toList();
    }

    /** The style a row is, in the colours asked for: the row itself in its own palette. */
    @Override public Theme dressed(Theme base, Theme colours) {
        if (!(base instanceof Worn w) || !(colours instanceof Colours c)) return base;
        Worn found = WORN.get(HouseStyles.INSTANCE.slug(w.dressed().style(), c.palette()));
        return found != null ? found : base;
    }

    @Override public boolean fits(Theme base, Theme colours) {
        if (!(base instanceof Worn w) || !(colours instanceof Colours c)) return true;
        DesignStyle<?> style = w.dressed().style();
        return HouseStyles.INSTANCE.fits(style, c.palette());
    }
}
