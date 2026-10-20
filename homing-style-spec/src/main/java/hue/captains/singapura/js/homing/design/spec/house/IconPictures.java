package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.ui.taxonomy.IconContent;
import hue.captains.singapura.js.homing.ui.taxonomy.IconLeaf;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Every icon the instance registers and every icon palette, held together (RFC 0067, D56): no two icons showing the same
 * content; base's palette drawing every glyph; a palette drawing only glyphs some icon shows, one palette a style. So
 * every icon has its picture in every style - its style's own, or base's - or, for an SVG, the page's placeholder.
 *
 * @param icons  every leaf of the Icon branch, in the taxonomy's order
 * @param base   base's palette
 * @param styles each style's palette that draws something of its own
 */
public record IconPictures(List<IconLeaf> icons, IconPalette base, List<IconPalette> styles) implements ValueObject {

    public IconPictures {
        Objects.requireNonNull(icons, "IconPictures.icons");
        Objects.requireNonNull(base, "IconPictures.base");
        Objects.requireNonNull(styles, "IconPictures.styles");
        icons = List.copyOf(icons);
        styles = List.copyOf(styles);
        var problems = new ArrayList<String>();
        var shown = new HashSet<IconContent>();
        var names = new HashSet<String>();
        for (IconLeaf i : icons) if (!names.add(i.content().name())) problems.add(i.token() + " shows " + i.content().name() + ", as another icon does");
        for (IconLeaf i : icons) shown.add(i.content());
        for (IconLeaf i : icons)
            if (i.content() instanceof IconContent.Glyph g && !base.glyphs().containsKey(g)) problems.add("base's palette draws no " + g.name() + ", which " + i.token() + " shows");
        var palettes = new ArrayList<IconPalette>(styles);
        palettes.add(base);
        var styled = new HashSet<DesignStyle<?>>();
        for (IconPalette p : palettes) {
            if (!styled.add(p.style())) problems.add(p.style().token() + " has two icon palettes");
            for (IconContent.Glyph g : p.glyphs().keySet())
                if (!shown.contains(g)) problems.add(p.style().token() + "'s palette draws " + g.name() + ", which no icon shows as a glyph");
        }
        if (!problems.isEmpty()) throw new IllegalArgumentException("the icons' pictures: " + String.join("; ", problems));
    }

    /** What an icon is drawn as in a style: the style's own glyph, else base's; none for an SVG, which the page draws a placeholder for. */
    public Optional<String> glyph(DesignStyle<?> style, IconLeaf icon) {
        if (!(icon.content() instanceof IconContent.Glyph g)) return Optional.empty();
        String own = styles.stream().filter(p -> p.style().equals(style)).findFirst().map(p -> p.glyphs().get(g)).orElse(null);
        return Optional.of(own != null ? own : base.glyphs().get(g));
    }

    /** Whether a style draws an icon its own way: its palette has a picture of its own for it. */
    public boolean own(DesignStyle<?> style, IconLeaf icon) {
        return icon.content() instanceof IconContent.Glyph g && styles.stream().anyMatch(p -> p.style().equals(style) && p.glyphs().containsKey(g));
    }
}
