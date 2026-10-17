package hue.captains.singapura.js.homing.design.styles.retrofuturism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.BaseScheme;
import hue.captains.singapura.js.homing.ui.styles.BaseColours;
import hue.captains.singapura.js.homing.ui.styles.RetroFuturism;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The edge strip in retro-futurism: a strip along an edge, shown when its lip is reached - raised, ruled along the edge
 * it comes from, casting its shadow over what it covers. It shows no state of its own. Whole: every property its look
 * has (D9).
 */
public record EdgeStrip()
        implements CastingStyle<BaseScheme, RetroFuturism, HouseContainers.EdgeStrip, RoleRoot> {
    @Override public StylingRes<BaseScheme, HouseContainers.EdgeStrip> style() {
        return new StylingRes.CssContent<>(
                new BaseColours.Raised(), new BaseColours.Text(), new BaseColours.Border(),
                new BaseColours.ShadeDarker(), new BaseColours.GlowWarm());
    }
}
