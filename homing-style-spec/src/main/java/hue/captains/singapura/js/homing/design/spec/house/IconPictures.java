package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.ui.taxonomy.IconLeaf;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Every icon the instance registers and every icon palette, held together (RFC 0067, D56): base's palette drawing every
 * icon; a palette drawing only icons the instance registers, one palette a style. So every icon has its picture in every
 * style - its style's own, or base's.
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
        for (IconLeaf i : icons) if (!base.pictures().containsKey(i)) problems.add("base's palette draws no picture of " + i.token());
        var palettes = new ArrayList<IconPalette>(styles);
        palettes.add(base);
        var styled = new HashSet<DesignStyle<?>>();
        for (IconPalette p : palettes) {
            if (!styled.add(p.style())) problems.add(p.style().token() + " has two icon palettes");
            for (IconLeaf i : p.pictures().keySet())
                if (!icons.contains(i)) problems.add(p.style().token() + "'s palette draws " + i.token() + ", which is no icon the instance registers");
        }
        if (!problems.isEmpty()) throw new IllegalArgumentException("the icons' pictures: " + String.join("; ", problems));
    }

    /** What an icon is drawn as in a style: the style's own picture, else base's. */
    public IconPicture picture(DesignStyle<?> style, IconLeaf icon) {
        if (!icons.contains(icon)) throw new IllegalArgumentException(icon.token() + " is no icon the instance registers");
        IconPicture own = styles.stream().filter(p -> p.style().equals(style)).findFirst().map(p -> p.pictures().get(icon)).orElse(null);
        return own != null ? own : base.pictures().get(icon);
    }

    /** Whether a style draws an icon its own way: its palette has a picture of its own for it. */
    public boolean own(DesignStyle<?> style, IconLeaf icon) {
        return styles.stream().anyMatch(p -> p.style().equals(style) && p.pictures().containsKey(icon));
    }
}
