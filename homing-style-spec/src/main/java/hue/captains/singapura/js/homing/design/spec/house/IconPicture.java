package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;

/**
 * A picture a style's icon palette draws an icon as (RFC 0067, D56): a character, or an SVG picture in a colour scheme,
 * its colours the palette's variables, written in it (owner, 21 Oct). Which, icon by icon, is the style's: an icon carries
 * its meaning alone, the same in every style, and says nothing of how it is drawn. An SVG picture imports its SVG
 * ({@link SvgWithColorScheme}) - its file found by {@link IconSvgs}, put in the icon's element as it is ({@link IconSvg});
 * where the file is not there yet, the page draws a placeholder in its place.
 *
 * @param <SC> the colour scheme it is drawn in: a character's any
 */
public sealed interface IconPicture<SC extends ColorScheme> extends ValueObject permits IconPicture.Glyph, IconPicture.Svg {

    /**
     * A picture drawn as a character: in every scheme, naming no colour.
     *
     * @param character what is drawn: something to see, no white space in it
     */
    record Glyph(String character) implements IconPicture<ColorScheme> {
        public Glyph {
            Objects.requireNonNull(character, "Glyph.character");
            if (character.isBlank() || character.codePoints().anyMatch(Character::isWhitespace))
                throw new IllegalArgumentException("Glyph: '" + character + "' is nothing to see");
        }
    }

    /**
     * A picture drawn as an SVG: the SVG it imports, in its scheme.
     *
     * @param svg the SVG, its colours typed by its scheme
     */
    record Svg<SC extends ColorScheme>(SvgWithColorScheme<SC> svg) implements IconPicture<SC> {
        public Svg {
            Objects.requireNonNull(svg, "Svg.svg");
        }
    }
}
