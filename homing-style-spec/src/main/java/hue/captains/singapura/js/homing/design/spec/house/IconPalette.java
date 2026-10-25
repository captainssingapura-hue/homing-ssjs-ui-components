package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.ui.taxonomy.IconLeaf;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A style's icon palette (RFC 0067, D56): the picture the style draws each icon as - a character, or an SVG in a scheme
 * the style's sees, by the compiler. It draws what it wants; base's palette draws every icon, and a style's draws only
 * where it differs.
 *
 * @param style    the style it is the palette of
 * @param pictures each icon it draws, with the picture it draws it as
 * @param <SC>     its style's colour scheme
 */
public record IconPalette<SC extends ColorScheme>(DesignStyle<SC> style, Map<IconLeaf, IconPicture<? super SC>> pictures) implements ValueObject {

    public IconPalette {
        Objects.requireNonNull(style, "IconPalette.style");
        Objects.requireNonNull(pictures, "IconPalette.pictures");
        for (var e : pictures.entrySet()) {
            Objects.requireNonNull(e.getKey(), "IconPalette.pictures: an icon");
            Objects.requireNonNull(e.getValue(), "IconPalette.pictures: " + e.getKey().token() + "'s picture");
        }
        pictures = java.util.Collections.unmodifiableMap(new LinkedHashMap<>(pictures));
    }
}
