package hue.captains.singapura.js.homing.ui.taxonomy;

import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * What an icon shows (RFC 0067, D56): which picture it is, named - the same in every style. It says nothing of how the
 * picture is drawn: each style's icon palette draws it, a palette drawing what it wants and base's every one, and the
 * page's style manager realizes it on the icon's element as the page runs, through the content provider it is handed. An
 * icon's look is the Icon branch's, the same for every icon; so the icons are alike but for what they show.
 *
 * <ul>
 *   <li>{@link Glyph} - a picture each palette draws as a character;</li>
 *   <li>{@link Svg} - a picture each palette draws as an SVG resource of its own, its shapes coloured by the palette's
 *       classes. None is drawn yet: the page draws a placeholder in their place.</li>
 * </ul>
 */
public sealed interface IconContent extends ValueObject permits IconContent.Glyph, IconContent.Svg {

    /** A content's name: lower-case words joined by hyphens - {@code check}, {@code split-beside}. */
    Pattern NAME = Pattern.compile("[a-z][a-z0-9]*(-[a-z0-9]+)*");

    /** Its name: unique among the icons the instance registers. */
    String name();

    /**
     * A picture drawn as a character.
     *
     * @param name its name
     */
    record Glyph(String name) implements IconContent {
        public Glyph { named(name, "Glyph"); }
    }

    /**
     * A picture drawn as an SVG resource.
     *
     * @param name its name
     */
    record Svg(String name) implements IconContent {
        public Svg { named(name, "Svg"); }
    }

    private static void named(String name, String kind) {
        Objects.requireNonNull(name, kind + ".name");
        if (!NAME.matcher(name).matches()) throw new IllegalArgumentException(kind + ": '" + name + "' is no content's name - lower-case words joined by hyphens");
    }
}
