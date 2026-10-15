package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.core.Theme;
import hue.captains.singapura.js.homing.ui.styles.HouseStyles;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The house's themes as a site offers them, its ready-made styling's (RFC 0067, appendix The Style Jars): every style in every palette by the slug the old designs gave
 * the same look, the default first; the picker's rows the styles in their own palettes, its columns the
 * palettes, a style's own palette the row itself; and /themes says which are a style's own and which fit.
 */
class HouseThemesTest {

    private static final StylingThemes THEMES = new StylingThemes(HouseStyles.INSTANCE.styling());

    @Test
    void everyStyleInEveryPalette_bySlug_theDefaultFirst() {
        assertEquals("editorial", THEMES.themes().get(0).slug());
        assertEquals(7 * 8, THEMES.themes().size(), "seven styles, eight palettes");
        assertEquals(List.of("editorial", "neo-brutalism", "neo-futurism", "neumorphism", "glassmorphism", "retro-futurism", "sketchy"),
                THEMES.bases().stream().map(Theme::slug).toList());
        assertEquals(List.of("harbour", "neo-brutalism", "neo-futurism", "clay", "frost", "synthwave", "marker", "forest"),
                THEMES.colours().stream().map(Theme::slug).toList());
    }

    @Test
    void aStyleInItsOwnPalette_isTheRowItself_inAnotherTheCross() {
        Theme editorial = THEMES.bases().get(0);
        Theme harbour = THEMES.colours().get(0), forest = THEMES.colours().get(7);
        assertSame(editorial, THEMES.dressed(editorial, harbour), "its own: the very row");
        Theme cross = THEMES.dressed(editorial, forest);
        assertEquals("editorial_forest", cross.slug());
        assertEquals("Editorial in Forest", cross.label());
        assertEquals("Neo-Brutalism", THEMES.bases().get(1).label());
        assertEquals("Expressive", THEMES.bases().get(1).group());
        assertTrue(THEMES.fits(editorial, harbour) && THEMES.fits(editorial, forest), "its own, and any other of its scheme");
        assertTrue(THEMES.fits(THEMES.bases().get(1), harbour), "the scheme decides (D22): every house palette, as every house style, is of the base");
    }

    @Test
    void theThemesRoute_saysWhichAreAStylesOwn_andWhichFit() {
        String json = new ThemesGetAction(THEMES).serialize();
        assertTrue(json.startsWith("{\"default\":\"editorial\""), json);
        assertTrue(json.contains("{\"palette\":\"harbour\",\"slug\":\"editorial\",\"own\":true,\"fits\":true}"), json);
        assertTrue(json.contains("{\"palette\":\"forest\",\"slug\":\"editorial_forest\",\"fits\":true}"), json);
    }
}
