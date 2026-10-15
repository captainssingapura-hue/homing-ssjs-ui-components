package hue.captains.singapura.js.homing.design.styles.glassmorphism;

import hue.captains.singapura.js.homing.component.taxonomy.RoleRoot;
import hue.captains.singapura.js.homing.design.color.ColorScheme;
import hue.captains.singapura.js.homing.design.styles.CastingStyle;
import hue.captains.singapura.js.homing.design.styles.StylingRes;
import hue.captains.singapura.js.homing.ui.styles.Glassmorphism;
import hue.captains.singapura.js.homing.ui.styles.HouseColours;
import hue.captains.singapura.js.homing.ui.taxonomy.HouseContainers;

/**
 * The edge strip in glassmorphism: a strip along an edge, shown when its lip is reached - raised, ruled along the edge
 * it comes from, casting its shadow over what it covers. It shows no state of its own. Whole: every property its look
 * has (D9).
 */
public record EdgeStrip()
        implements CastingStyle<ColorScheme.Base, Glassmorphism, HouseContainers.EdgeStrip, RoleRoot> {
    @Override public StylingRes<ColorScheme.Base, HouseContainers.EdgeStrip> style() {
        return new StylingRes.CssContent<>(
                new HouseColours.Raised(), new HouseColours.Text(), new HouseColours.Border(),
                new HouseColours.ShadeThick());
    }
}
