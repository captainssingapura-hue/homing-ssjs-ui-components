package hue.captains.singapura.js.homing.design.styles.neofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.NeoFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The panel in neo-futurism: a named region that fills what holds it - raised, framed, its corner the style's; lifted
 * or set in as the app says; its edge in the accent while it is the current one. Whole: every property its look has
 * (D9).
 */
public record Panel() implements CastingStyle<BaseScheme, NeoFuturism, HouseContainers.Panel, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.Panel> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Text(), new HouseColours.Border(), new HouseColours.Accent(),
                new HouseColours.ShadeDense(), new HouseColours.ShadeDark(), new HouseColours.GlowSoft());
    }
}
