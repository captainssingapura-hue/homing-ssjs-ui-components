package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.core.Theme;
import hue.captains.singapura.js.homing.design.semantic.ColorPalette;
import hue.captains.singapura.js.homing.design.semantic.Dressed;
import hue.captains.singapura.js.homing.design.styling.Styling;
import hue.captains.singapura.js.homing.server.ThemeRegistry;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * An instance's themes, as its styling has them (RFC 0067, appendix The Style Jars): every style it serves in every
 * palette that fits it, each by the slug the styling gives it - the style's token alone in its own palette, the style's
 * then the palette's in another - the default first. A picker's two axes are the styles, each in its own palette, and
 * the palettes; a palette fits a style when the styling says it does. Nothing is rendered here: the look and the lexicon
 * render themselves, from the styling installed, for the theme a page is worn in.
 */
public final class StylingThemes implements ThemeRegistry {

    /** A style worn in a palette, by its slug; its own palette or another. */
    public record Worn(String slug, Dressed dressed, boolean own) implements Theme {
        @Override public String label() { return own ? dressed.style().label() : dressed.style().label() + " in " + dressed.palette().label(); }
        @Override public String group() { return own ? dressed.style().group() : "Composed"; }
        @Override public String inspiration() {
            return own ? dressed.style().inspiration() : dressed.style().label() + "'s shapes in " + dressed.palette().label() + "'s colours.";
        }
    }

    /** A palette, as the colours a picker offers a style. */
    public record Colours(ColorPalette<?> palette) implements Theme {
        @Override public String slug() { return palette.token(); }
        @Override public String label() { return palette.label(); }
        @Override public String group() { return "Colours"; }
        @Override public String inspiration() { return palette.inspiration(); }
    }

    private final Styling styling;

    /** Every theme by its slug, the default first; each style's own among them, the very ones the picker's rows are. */
    private final Map<String, Worn> worn;

    public StylingThemes(Styling styling) {
        this.styling = Objects.requireNonNull(styling, "StylingThemes.styling");
        var out = new LinkedHashMap<String, Worn>();
        styling.themes().forEach((slug, d) -> out.put(slug, new Worn(slug, d, d.palette().equals(styling.own(d.style())))));
        this.worn = Collections.unmodifiableMap(out);
    }

    /** The styling the themes are of. */
    public Styling styling() { return styling; }

    @Override public List<Theme> themes() { return List.copyOf(worn.values()); }

    @Override public List<Theme> bases() { return styling.styles().stream().<Theme>map(s -> worn.get(s.token())).toList(); }

    @Override public List<Theme> colours() { return styling.palettes().stream().<Theme>map(Colours::new).toList(); }

    /** The style a row is, in the colours asked for: the row itself in its own palette. */
    @Override public Theme dressed(Theme base, Theme colours) {
        if (!(base instanceof Worn w) || !(colours instanceof Colours c)) return base;
        if (!styling.fits(w.dressed().style(), c.palette())) return base;
        Worn found = worn.get(styling.slug(w.dressed().style(), c.palette()));
        return found != null ? found : base;
    }

    @Override public boolean fits(Theme base, Theme colours) {
        if (!(base instanceof Worn w) || !(colours instanceof Colours c)) return true;
        return styling.fits(w.dressed().style(), c.palette());
    }
}
