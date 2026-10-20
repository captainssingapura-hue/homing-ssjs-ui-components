package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.semantic.DesignStyle;
import hue.captains.singapura.js.homing.ui.taxonomy.IconContent;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A style's icon palette (RFC 0067, D56): the pictures the style draws its icons' contents as - for each glyph it draws,
 * the character. It draws what it wants; base's palette draws every glyph, and a style's draws only where it differs. An
 * SVG's picture is none of a palette's yet: the page draws a placeholder in its place.
 *
 * @param style  the style it is the palette of
 * @param glyphs each glyph it draws, with the character it draws it as
 */
public record IconPalette(DesignStyle<?> style, Map<IconContent.Glyph, String> glyphs) implements ValueObject {

    public IconPalette {
        Objects.requireNonNull(style, "IconPalette.style");
        Objects.requireNonNull(glyphs, "IconPalette.glyphs");
        glyphs = java.util.Collections.unmodifiableMap(new LinkedHashMap<>(glyphs));
        for (var e : glyphs.entrySet()) {
            Objects.requireNonNull(e.getKey(), "IconPalette.glyphs: a content");
            if (e.getValue() == null || e.getValue().isBlank() || e.getValue().codePoints().anyMatch(Character::isWhitespace))
                throw new IllegalArgumentException(style.token() + "'s icon palette draws " + e.getKey().name() + " as nothing to see: '" + e.getValue() + "'");
        }
    }
}
