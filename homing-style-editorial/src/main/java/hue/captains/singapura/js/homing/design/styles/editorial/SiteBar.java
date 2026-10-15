package hue.captains.singapura.js.homing.design.styles.editorial;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Editorial;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The site bar in editorial: heads a site's pages - the inverted layer, the page's ground written on it, ruled along
 * its foot in the accent. Whole: every property its look has (D9).
 */
public record SiteBar() implements CastingStyle<ColorScheme.Base, Editorial, HouseContainers.SiteBar, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.SiteBar> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Inverted(), new HouseColours.OnBar(), new HouseColours.Accent());
    }
}
