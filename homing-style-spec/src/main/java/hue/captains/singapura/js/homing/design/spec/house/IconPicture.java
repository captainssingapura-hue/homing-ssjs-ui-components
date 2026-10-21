package hue.captains.singapura.js.homing.design.spec.house;

import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * A picture a style's icon palette draws an icon as (RFC 0067, D56): a character, or an SVG resource of the style's, its
 * colours the palette's variables, written in it (owner, 21 Oct). Which, icon by icon, is the style's: an icon carries its
 * meaning alone, the same in every style, and says nothing of how it is drawn. An SVG picture is its style's resource
 * ({@link IconSvgs}), put in the icon's element as it is ({@link IconSvg}); where it is not there yet, the page draws a
 * placeholder in its place.
 */
public sealed interface IconPicture extends ValueObject permits IconPicture.Glyph, IconPicture.Svg {

    /**
     * A picture drawn as a character.
     *
     * @param character what is drawn: something to see, no white space in it
     */
    record Glyph(String character) implements IconPicture {
        public Glyph {
            Objects.requireNonNull(character, "Glyph.character");
            if (character.isBlank() || character.codePoints().anyMatch(Character::isWhitespace))
                throw new IllegalArgumentException("Glyph: '" + character + "' is nothing to see");
        }
    }

    /**
     * A picture drawn as an SVG resource.
     *
     * @param resource its name: lower-case words joined by hyphens
     */
    record Svg(String resource) implements IconPicture {
        private static final Pattern NAME = Pattern.compile("[a-z][a-z0-9]*(-[a-z0-9]+)*");

        public Svg {
            Objects.requireNonNull(resource, "Svg.resource");
            if (!NAME.matcher(resource).matches())
                throw new IllegalArgumentException("Svg: '" + resource + "' is no resource's name - lower-case words joined by hyphens");
        }
    }
}
