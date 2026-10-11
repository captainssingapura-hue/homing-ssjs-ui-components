package hue.captains.singapura.js.homing.site.mpa;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;
import hue.captains.singapura.js.homing.core.InLayer;
import hue.captains.singapura.js.homing.core.Layout;
import hue.captains.singapura.js.homing.core.Reset;
import hue.captains.singapura.js.homing.core.util.CssClassName;
import java.util.List;


/**
 * The chrome's classes: its layout's, and where the preferences button sits. The
 * chrome is a widget with no design of its own - the page's ground, the site's
 * bar, the brand and the trail are designed components, each in the house's
 * look - so nothing here wears a word or paints: the bodies hold only layout.
 *
 * <p>The words are the ones a page chrome needs and nothing more — the
 * inverted bar, the house word, the muted crumbs, a control for the
 * preferences button. A design that binds the studio's vocabulary binds all
 * of these; the preferences dialog wears its own.</p>
 */
public record MpaStyles() implements CssGroup<MpaStyles> {

    public static final MpaStyles INSTANCE = new MpaStyles();

    /** The document itself: no margin, the page fills the viewport. */
    public record mpa_page() implements CssClass<MpaStyles>, InLayer<Reset> {
        @Override public String selector() { return "html, body"; }
        @Override public String body() { return """
            margin: 0;
            padding: 0;
            min-height: 100vh;
            """;
        }
    }

    /** The page column: the head over the page's slot, at least the viewport tall. Its ground is the page's, which it stands on. */
    public record mpa_root() implements CssClass<MpaStyles>, InLayer<Layout> {
        @Override public String body() { return """
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            """;
        }
    }



    /** The head: along the top, kept in view as the page scrolls, over what the page holds. */
    public record mpa_head() implements CssClass<MpaStyles>, InLayer<Layout> {
        @Override public String body() { return """
            flex: 0 0 auto;
            position: sticky;
            top: 0;
            z-index: 50;
            @media print { & { position: static; } }
            """;
        }
    }

    /** The app's slot: a reading column by default. */
    public record mpa_main() implements CssClass<MpaStyles>, InLayer<Layout> {
        @Override public String body() { return """
            flex: 1;
            max-width: 1280px;
            width: 100%;
            margin: 0 auto;
            padding: 32px 28px 64px;
            box-sizing: border-box;
            @media print { & { max-width: none; padding: 12px 0; } }
            """;
        }
    }

    /**
     * The slot as a full-bleed column: no reading width, no padding, its
     * children stacked and the last one free to fill it. An app that lays
     * itself out — a shell of panes — adds this to the slot it is given.
     */
    public record mpa_main_full() implements CssClass<MpaStyles>, InLayer<Layout> {
        @Override public String body() { return """
            max-width: none;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 0;
            """;
        }
    }

    /**
     * The page an app fills. A main that lays itself out ({@link mpa_main_full})
     * says the page is an app, not a reading column: the document is then
     * exactly the viewport and does not scroll — the column is pinned to it —
     * and what scrolls is whatever the app scrolls inside itself. A rule over
     * the elements no class of ours reaches, keyed off the word the app wears.
     *
     * <p>Twice, because the slot is not always inside the chrome. A page served
     * by the flat address has no chrome and no root: the app IS the document's
     * one element, and nothing above it was ever going to give it a height. So
     * the second rule pins the slot itself when there is no root to pin.</p>
     */
    public record mpa_page_app() implements CssClass<MpaStyles>, InLayer<Layout> {
        private static final String APP = "." + CssClassName.toCssName(mpa_main_full.class);
        private static final String ROOT = "." + CssClassName.toCssName(mpa_root.class);
        @Override public String selector() { return "html:has(" + APP + ")"; }
        @Override public String body() { return """
            overflow: hidden;
            & body { overflow: hidden; }
            & %1$s { position: fixed; inset: 0; min-height: 0; }
            & body:not(:has(%1$s)) %2$s { position: fixed; inset: 0; min-height: 0; }
            """.formatted(ROOT, APP);
        }
    }

    // ── Preferences ───────────────────────────────────────────────────────────

    /** The anchor the menu hangs from; where it sits on the bar is the bar's. */
    public record mpa_prefs() implements CssClass<MpaStyles> {
        @Override public String body() { return """
            position: relative;
            """;
        }
    }

    /** The button on the bar: a control drawn in the bar's ink. */
    public record mpa_prefs_btn() implements CssClass<MpaStyles> {
        @Override public String body() { return """
            display: inline-flex;
            align-items: center;
            """;
        }
    }

    @Override
    public List<CssClass<MpaStyles>> cssClasses() {
        return List.of(
                new mpa_page(), new mpa_root(), new mpa_head(), new mpa_main(), new mpa_main_full(), new mpa_page_app(),
                new mpa_prefs(), new mpa_prefs_btn());
    }
}
