package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The panel in retro-futurism: a named region that fills what holds it - raised, framed, its corner the style's; lifted
 * or set in as the app says; its edge in the accent while it is the current one. Whole: every property its look has
 * (D9).
 */
public record Panel() implements CastingStyle<BaseScheme, RetroFuturism, HouseContainers.Panel, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.Panel> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Text(), new HouseColours.Border(), new HouseColours.Accent(),
                new HouseColours.ShadeDarkest(), new HouseColours.GlowWarm());
    }
}
