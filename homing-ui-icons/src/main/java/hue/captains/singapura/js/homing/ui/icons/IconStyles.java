package hue.captains.singapura.js.homing.ui.icons;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;

import java.util.List;

/**
 * The icons' styles: the mark's box, and nothing else. The picture is the lexicon's - the word the
 * icon shows, written by the style worn ({@link LexiconSheet}) - so nothing here is a picture, and the
 * box is the same in every theme.
 */
public record IconStyles() implements CssGroup<IconStyles> {

    public static final IconStyles INSTANCE = new IconStyles();

    /**
     * The mark: an inline box of one em, a quarter wider than tall so the
     * widest glyph sits in it, the glyph centred; it takes the ink and the
     * size of the text it sits in, and it is never in the way of the hand.
     */
    public record ic_base() implements CssClass<IconStyles> {
        @Override public String body() { return """
            display: inline-flex;
            align-items: center;
            justify-content: center;
            flex: none;
            inline-size: 1.25em;
            block-size: 1em;
            line-height: 1;
            font-style: normal;
            font-weight: normal;
            vertical-align: -0.125em;
            pointer-events: none;
            user-select: none;
            """;
        }
    }

    @Override
    public List<CssClass<IconStyles>> cssClasses() { return List.of(new ic_base()); }
}
