package hue.captains.singapura.js.homing.ui.elements;

import hue.captains.singapura.js.homing.core.CssClass;
import hue.captains.singapura.js.homing.core.CssGroup;

import java.util.List;

/**
 * A site's page: its bar, the brand and the trail it carries - placed here, their looks the house's.
 */
public record SiteBarStyles() implements CssGroup<SiteBarStyles> {

    public static final SiteBarStyles INSTANCE = new SiteBarStyles();

    /** The site's bar: what it carries in a row, centred across it; its room, its ground and its line are its look. */
    public record el_site_bar() implements CssClass<SiteBarStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: center;
            box-sizing: border-box;
            """;
        }
    }

    /** What the bar carries at its start - the brand: never shrinking. */
    public record el_site_bar_start() implements CssClass<SiteBarStyles> {
        @Override public String body() { return "flex: 0 0 auto;"; }
    }

    /** What the bar carries across its middle - the trail: the room the others leave, and no less than nothing. */
    public record el_site_bar_way() implements CssClass<SiteBarStyles> {
        @Override public String body() { return """
            flex: 1 1 auto;
            min-width: 0;
            """;
        }
    }

    /** What the bar carries at its end - the preferences: pushed to it, never shrinking. */
    public record el_site_bar_end() implements CssClass<SiteBarStyles> {
        @Override public String body() { return """
            flex: 0 0 auto;
            margin-inline-start: auto;
            """;
        }
        /** A text's own rule clears its margins, so this follows that rule. */
        @Override public List<CssClass<?>> dependsOn() { return List.of(new ElementStyles.el_text()); }
    }

    /** The brand: its mark beside its name, the room between them its look. */
    public record el_brand() implements CssClass<SiteBarStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: center;
            """;
        }
    }

    /** The brand's mark: a square, until a site gives a logo; its colour is the brand's. */
    public record el_brand_mark() implements CssClass<SiteBarStyles> {
        @Override public String body() { return """
            width: 12px;
            height: 12px;
            flex: 0 0 auto;
            """;
        }
    }

    /** The trail: its steps in a row, the room between them its look. */
    public record el_trail() implements CssClass<SiteBarStyles> {
        @Override public String body() { return """
            display: flex;
            align-items: center;
            min-width: 0;
            """;
        }
    }

    /** A step of the trail: on one line. */
    public record el_trail_crumb() implements CssClass<SiteBarStyles> {
        @Override public String body() { return "white-space: nowrap;"; }
    }

    @Override
    public List<CssClass<SiteBarStyles>> cssClasses() {
        return List.of(new el_site_bar(), new el_site_bar_start(), new el_site_bar_way(), new el_site_bar_end(), new el_brand(), new el_brand_mark(),
                       new el_trail(), new el_trail_crumb());
    }
}
